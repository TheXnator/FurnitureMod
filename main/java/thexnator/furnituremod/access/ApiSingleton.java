package thexnator.furnituremod.access;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiSingleton {
	public boolean includeSuper() default true;
}