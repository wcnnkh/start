package io.basc.start.aliyun.ons.boot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ExpressionType;
import com.aliyun.openservices.ons.api.batch.BatchConsumer;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageListenerMapping {
	/**
	 * @see Consumer
	 * @see BatchConsumer
	 * @return
	 */
	String consumer() default "";

	String topic();

	ExpressionType expressionType() default ExpressionType.TAG;

	String subExpression();
}
