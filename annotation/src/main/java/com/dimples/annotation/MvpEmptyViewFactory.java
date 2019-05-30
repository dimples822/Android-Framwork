package com.dimples.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * apt注解
 * 自定义注解
 * 处理MVP架构中的EmptyView
 *
 * @author zhongyj
 * @date 2019/5/30 21:49
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface MvpEmptyViewFactory {
}
