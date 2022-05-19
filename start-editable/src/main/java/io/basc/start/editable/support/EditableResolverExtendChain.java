package io.basc.start.editable.support;

import io.basc.framework.core.parameter.ParameterDescriptor;
import io.basc.framework.lang.Nullable;
import io.basc.framework.util.Assert;
import io.basc.start.editable.EditableAttributes;
import io.basc.start.editable.EditableResolver;
import io.basc.start.editable.ImageAttribute;

import java.util.Iterator;

public class EditableResolverExtendChain implements EditableResolver {
	private final Iterator<EditableResolverExtend> iterator;
	private final EditableResolver nextChain;

	public EditableResolverExtendChain(Iterator<EditableResolverExtend> iterator) {
		this(iterator, null);
	}

	public EditableResolverExtendChain(
			Iterator<EditableResolverExtend> iterator,
			@Nullable EditableResolver nextChain) {
		Assert.requiredArgument(iterator != null, "iterator");
		this.iterator = iterator;
		this.nextChain = nextChain;
	}

	public static EditableResolver build(
			Iterator<EditableResolverExtend> iterator) {
		return new EditableResolverExtendChain(iterator);
	}

	@Override
	public ImageAttribute getImageAttribute(Class<?> entityClass,
			ParameterDescriptor descriptor) {
		if (iterator.hasNext()) {
			return iterator.next().getImageAttribute(entityClass, descriptor,
					this);
		}
		return nextChain == null ? null : nextChain.getImageAttribute(
				entityClass, descriptor);
	}

	@Override
	public boolean isEditable(Class<?> entityClass) {
		if (iterator.hasNext()) {
			return iterator.next().isEditable(entityClass, this);
		}
		return nextChain == null ? false : nextChain.isEditable(entityClass);
	}

	@Override
	public EditableAttributes getEditableAttributes(Class<?> entityClass,
			ParameterDescriptor descriptor) {
		if (iterator.hasNext()) {
			return iterator.next().getEditableAttributes(entityClass,
					descriptor, this);
		}
		return nextChain == null ? null : nextChain.getEditableAttributes(
				entityClass, descriptor);
	}
}
