package com.ice.annotation;

import org.aspectj.lang.annotation.Around;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Package:com.ice.annotation
 * @ClassName:SystemLog
 * @Auther:iceee
 * @Date:2022/3/20
 * @Description:
 */

//@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
//@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD})


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SystemLog {
    String busnessName();
}
