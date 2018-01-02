package com.study.xuan.shapebuilder.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author : xuan.
 * Date : 2017/10/23.
 * Description :input the description of this file.
 */
@Documented()
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Shape {
    int value() default 0;
}
