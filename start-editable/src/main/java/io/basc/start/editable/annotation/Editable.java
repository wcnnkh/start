package io.basc.start.editable.annotation;

import io.basc.framework.orm.annotation.Entity;
import io.basc.start.editable.EditableType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Entity
public @interface Editable {
	EditableType type() default EditableType.DEFAULT;

	boolean readonly() default false;
}
