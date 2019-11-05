package cn.itsource.ssj.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {

	String andOr() default "and";
	
	String columnName();
	
	String operation() default "=";
}
