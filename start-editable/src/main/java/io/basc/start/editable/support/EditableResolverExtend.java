package io.basc.start.editable.support;

import io.basc.framework.core.parameter.ParameterDescriptor;
import io.basc.start.editable.EditableAttributes;
import io.basc.start.editable.EditableResolver;
import io.basc.start.editable.ImageAttribute;

public interface EditableResolverExtend {
	default boolean isEditable(Class<?> entityClass, EditableResolver chain) {
		return chain.isEditable(entityClass);
	}

	default EditableAttributes getEditableAttributes(Class<?> entityClass,
			ParameterDescriptor descriptor, EditableResolver chain) {
		return chain.getEditableAttributes(entityClass, descriptor);
	}

	default ImageAttribute getImageAttribute(Class<?> entityClass,
			ParameterDescriptor descriptor, EditableResolver chain) {
		return chain.getImageAttribute(entityClass, descriptor);
	}
}
