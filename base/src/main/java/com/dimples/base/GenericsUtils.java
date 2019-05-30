package com.dimples.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author zhongyj
 * @date 2019/5/15
 */
public class GenericsUtils {

    static Class getSuperClassGenericType(Class clazz) {
        return getSuperClassGenericsType(clazz, 0);
    }

    public static Class getSuperClassGenericsType(Class clazz, int index) throws IndexOutOfBoundsException {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }
}

