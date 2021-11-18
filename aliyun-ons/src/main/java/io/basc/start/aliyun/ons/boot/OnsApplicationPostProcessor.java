package io.basc.start.aliyun.ons.boot;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ExpressionType;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.MessageSelector;
import com.aliyun.openservices.ons.api.batch.BatchConsumer;
import com.aliyun.openservices.ons.api.batch.BatchMessageListener;

import io.basc.framework.boot.Application;
import io.basc.framework.boot.ApplicationPostProcessor;
import io.basc.framework.boot.ConfigurableApplication;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.convert.TypeDescriptor;
import io.basc.framework.core.Ordered;
import io.basc.framework.core.annotation.AnnotationUtils;
import io.basc.framework.core.reflect.MethodInvoker;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.util.ArrayUtils;
import io.basc.framework.util.CollectionFactory;
import io.basc.framework.util.StringUtils;
import io.basc.start.aliyun.ons.OnsException;

@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class OnsApplicationPostProcessor implements ApplicationPostProcessor {
	private static Logger logger = LoggerFactory.getLogger(OnsApplicationPostProcessor.class);

	@Override
	public void postProcessApplication(ConfigurableApplication application) throws Throwable {
		for (Class<?> clazz : application.getContextClasses()) {
			if (MessageListener.class.isAssignableFrom(clazz)) {
				MessageListenerMapping mapping = clazz.getAnnotation(MessageListenerMapping.class);
				if (mapping != null) {
					MessageListener messageListener = (MessageListener) application.getBeanFactory().getInstance(clazz);
					subscribe(application, mapping, messageListener);
				}
			}

			if (BatchMessageListener.class.isAssignableFrom(clazz)) {
				MessageListenerMapping mapping = clazz.getAnnotation(MessageListenerMapping.class);
				if (mapping != null) {
					BatchMessageListener messageListener = (BatchMessageListener) application.getBeanFactory()
							.getInstance(clazz);
					subscribe(application, mapping, messageListener);
				}
			}

			for (Method method : AnnotationUtils.getAnnoationMethods(clazz, true, true, MessageListenerMapping.class)) {
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (ArrayUtils.isEmpty(parameterTypes)) {
					throw new OnsException("The message parameter must exist: " + method.toString());
				}

				Type[] types = method.getGenericExceptionTypes();
				boolean accept = false;
				boolean batch = false;
				for (int i = 0; i < parameterTypes.length; i++) {
					TypeDescriptor typeDescriptor = TypeDescriptor.valueOf(types[i]);
					if (typeDescriptor.getType() == Message.class) {
						accept = true;
						break;
					}

					if ((typeDescriptor.isArray() || typeDescriptor.isCollection())
							&& Message.class == typeDescriptor.getElementTypeDescriptor().getType()) {
						accept = true;
						batch = true;
						break;
					}
				}

				if (!accept) {
					throw new OnsException("The message parameter must exist: " + method.toString());
				}

				MethodInvoker methodInvoker = application.getBeanFactory().getAop().getProxyMethod(clazz,
						application.getBeanFactory().getInstance(clazz), method);
				MessageListenerMapping mapping = method.getAnnotation(MessageListenerMapping.class);
				if (batch) {
					BatchMessageListener batchMessageListener = new BatchMessageListener() {

						@Override
						public Action consume(List<Message> messages, ConsumeContext context) {
							Object[] args = new Object[types.length];
							for (int i = 0; i < parameterTypes.length; i++) {
								TypeDescriptor typeDescriptor = TypeDescriptor.valueOf(types[i]);
								if (typeDescriptor.isArray()
										&& Message.class == typeDescriptor.getElementTypeDescriptor().getType()) {
									args[i] = messages.toArray(new Message[0]);
								} else if (typeDescriptor.isCollection()
										&& Message.class == typeDescriptor.getElementTypeDescriptor().getType()) {
									Collection<Message> collection = CollectionFactory
											.createCollection(typeDescriptor.getType(), Message.class, messages.size());
									collection.addAll(messages);
									args[i] = collection;
								} else if (typeDescriptor.getType() == ConsumeContext.class) {
									args[i] = context;
								}
							}

							try {
								Object value = methodInvoker.invoke(args);
								if (value != null && value instanceof Action) {
									return (Action) value;
								}
								return Action.CommitMessage;
							} catch (Throwable e) {
								logger.error(e, method.toString());
								return Action.ReconsumeLater;
							}
						}
					};
					subscribe(application, mapping, batchMessageListener);
				} else {
					MessageListener messageListener = new MessageListener() {

						@Override
						public Action consume(Message message, ConsumeContext context) {
							Object[] args = new Object[types.length];
							for (int i = 0; i < parameterTypes.length; i++) {
								TypeDescriptor typeDescriptor = TypeDescriptor.valueOf(types[i]);
								if (Message.class == typeDescriptor.getElementTypeDescriptor().getType()) {
									args[i] = message;
								} else if (typeDescriptor.getType() == ConsumeContext.class) {
									args[i] = context;
								}
							}

							try {
								Object value = methodInvoker.invoke(args);
								if (value != null && value instanceof Action) {
									return (Action) value;
								}
								return Action.CommitMessage;
							} catch (Throwable e) {
								logger.error(e, method.toString());
								return Action.ReconsumeLater;
							}
						}
					};
					subscribe(application, mapping, messageListener);
				}
			}
		}
	}

	private void subscribe(Application application, MessageListenerMapping mapping, MessageListener messageListener) {
		Consumer consumer;
		String consumerName = mapping.consumer();
		if (StringUtils.isEmpty(consumerName)) {
			consumer = application.getBeanFactory().getInstance(Consumer.class);
		} else {
			consumerName = application.getEnvironment().resolvePlaceholders(consumerName);
			consumer = application.getBeanFactory().getInstance(consumerName);
		}

		String topic = mapping.topic();
		topic = application.getEnvironment().resolvePlaceholders(topic);
		String subExpression = mapping.subExpression();
		subExpression = application.getEnvironment().resolvePlaceholders(subExpression);
		MessageSelector selector = mapping.expressionType() == ExpressionType.SQL92
				? MessageSelector.bySql(subExpression)
				: MessageSelector.byTag(subExpression);
		logger.info("Subscribe consumer[{}] topic[{}] expressionType[{}] subExpression[{}] bind:{}", consumer, topic,
				selector.getType(), selector.getSubExpression(), messageListener);
		consumer.subscribe(topic, selector, messageListener);
	}

	private void subscribe(Application application, MessageListenerMapping mapping,
			BatchMessageListener messageListener) {
		BatchConsumer consumer;
		String consumerName = mapping.consumer();
		if (StringUtils.isEmpty(consumerName)) {
			consumer = application.getBeanFactory().getInstance(BatchConsumer.class);
		} else {
			consumerName = application.getEnvironment().resolvePlaceholders(consumerName);
			consumer = application.getBeanFactory().getInstance(consumerName);
		}

		String topic = mapping.topic();
		topic = application.getEnvironment().resolvePlaceholders(topic);
		String subExpression = mapping.subExpression();
		subExpression = application.getEnvironment().resolvePlaceholders(subExpression);
		logger.info("Batch subscribe consumer[{}] topic[{}] subExpression[{}] bind:{}", consumer, topic, subExpression,
				messageListener);
		consumer.subscribe(topic, subExpression, messageListener);
	}
}
