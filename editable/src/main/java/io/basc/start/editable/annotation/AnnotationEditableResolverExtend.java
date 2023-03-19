package io.basc.start.editable.annotation;

import io.basc.framework.core.annotation.AnnotatedElementUtils;
import io.basc.framework.mapper.ParameterDescriptor;
import io.basc.start.editable.EditableAttributes;
import io.basc.start.editable.EditableResolver;
import io.basc.start.editable.EditableType;
import io.basc.start.editable.ImageAttributes;
import io.basc.start.editable.support.EditableResolverExtend;

public class AnnotationEditableResolverExtend implements EditableResolverExtend {

	@Override
	public EditableAttributes getEditableAttributes(Class<?> entityClass, ParameterDescriptor descriptor,
			EditableResolver chain) {
		Editable editable = AnnotatedElementUtils.getMergedAnnotation(entityClass, Editable.class);
		if (editable != null) {
			EditableAttributes attributes = new EditableAttributes();
			attributes.setType(editable.type());
			if (editable.type() == EditableType.DEFAULT) {
				if (AnnotatedElementUtils.hasAnnotation(descriptor, Image.class)) {
					attributes.setType(EditableType.IMAGE);
				}
			}

			attributes.setReadonly(editable.readonly());
			return attributes;
		}

		return EditableResolverExtend.super.getEditableAttributes(entityClass, descriptor, chain);
	}

	@Override
	public boolean isEditable(Class<?> entityClass, EditableResolver chain) {
		if (AnnotatedElementUtils.hasAnnotation(entityClass, Editable.class)) {
			return true;
		}
		return EditableResolverExtend.super.isEditable(entityClass, chain);
	}

	@Override
	public ImageAttributes getImageAttributes(Class<?> entityClass, ParameterDescriptor descriptor,
			EditableResolver chain) {
		Image image = AnnotatedElementUtils.getMergedAnnotation(descriptor, Image.class);
		if (image != null) {
			ImageAttributes attribute = new ImageAttributes();
			attribute.setHeight(image.height());
			attribute.setWidth(image.height());
			attribute.setMultiple(image.multiple());
			return attribute;
		}

		return EditableResolverExtend.super.getImageAttributes(entityClass, descriptor, chain);
	}
}
