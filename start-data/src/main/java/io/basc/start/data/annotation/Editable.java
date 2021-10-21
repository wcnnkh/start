package io.basc.start.data.annotation;

import io.basc.framework.orm.annotation.Entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Entity
public @interface Editable {
	String name() default "";
	
	String title();
}
