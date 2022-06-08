package io.basc.start.editable.support;

import io.basc.framework.core.parameter.ParameterDescriptor;
import io.basc.framework.factory.ConfigurableServices;
import io.basc.framework.factory.ServiceLoaderFactory;
import io.basc.framework.orm.support.DefaultObjectRelationalMapper;
import io.basc.start.editable.EditableAttributes;
import io.basc.start.editable.EditableMapper;
import io.basc.start.editable.ImageAttributes;
import io.basc.start.editable.annotation.AnnotationEditableResolverExtend;

public class DefaultEditableMapper extends DefaultObjectRelationalMapper implements EditableMapper {
	private final ConfigurableServices<EditableResolverExtend> editableResolverExtends = new ConfigurableServices<EditableResolverExtend>(
			EditableResolverExtend.class);

	public DefaultEditableMapper() {
		editableResolverExtends.setAfterService(new AnnotationEditableResolverExtend());
	}

	@Override
	public void configure(ServiceLoaderFactory serviceLoaderFactory) {
		editableResolverExtends.configure(serviceLoaderFactory);
		super.configure(serviceLoaderFactory);
	}

	public ConfigurableServices<EditableResolverExtend> getEditableResolverExtends() {
		return editableResolverExtends;
	}

	@Override
	public ImageAttributes getImageAttributes(Class<?> entityClass, ParameterDescriptor descriptor) {
		return EditableResolverExtendChain.build(editableResolverExtends.iterator()).getImageAttributes(entityClass,
				descriptor);
	}

	@Override
	public boolean isEditable(Class<?> entityClass) {
		return EditableResolverExtendChain.build(editableResolverExtends.iterator()).isEditable(entityClass);
	}

	@Override
	public EditableAttributes getEditableAttributes(Class<?> entityClass, ParameterDescriptor descriptor) {
		return EditableResolverExtendChain.build(editableResolverExtends.iterator()).getEditableAttributes(entityClass,
				descriptor);
	}
}
