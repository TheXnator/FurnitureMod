package thexnator.furnituremod.access;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiImplementation {
	public boolean includeSuper() default true;

	public boolean cacheable() default true;
}
