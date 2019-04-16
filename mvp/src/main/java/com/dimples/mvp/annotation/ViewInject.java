package com.dimples.mvp.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * View注入
 *
 * @author zhongyj
 * @date 2019/4/16 23:02
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ViewInject {

    int LayoutId() default -1;

}



















