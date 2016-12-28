package net.sppan.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要拥有资源的访问权限时，才能执行被该注解标注的方法。
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissions {
	
	 String[] key() default "";
	 Logical logical() default Logical.AND; 
	 
}
