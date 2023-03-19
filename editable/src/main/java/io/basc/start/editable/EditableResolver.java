package io.basc.start.editable;

import io.basc.framework.mapper.ParameterDescriptor;

public interface EditableResolver {
	boolean isEditable(Class<?> entityClass);

	EditableAttributes getEditableAttributes(Class<?> entityClass,
			ParameterDescriptor descriptor);

	ImageAttributes getImageAttributes(Class<?> entityClass,
			ParameterDescriptor descriptor);
}
