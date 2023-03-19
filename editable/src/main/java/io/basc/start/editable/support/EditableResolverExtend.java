package io.basc.start.editable.support;

import io.basc.framework.mapper.ParameterDescriptor;
import io.basc.start.editable.EditableAttributes;
import io.basc.start.editable.EditableResolver;
import io.basc.start.editable.ImageAttributes;

public interface EditableResolverExtend {
	default boolean isEditable(Class<?> entityClass, EditableResolver chain) {
		return chain.isEditable(entityClass);
	}

	default EditableAttributes getEditableAttributes(Class<?> entityClass,
			ParameterDescriptor descriptor, EditableResolver chain) {
		return chain.getEditableAttributes(entityClass, descriptor);
	}

	default ImageAttributes getImageAttributes(Class<?> entityClass,
			ParameterDescriptor descriptor, EditableResolver chain) {
		return chain.getImageAttributes(entityClass, descriptor);
	}
}
