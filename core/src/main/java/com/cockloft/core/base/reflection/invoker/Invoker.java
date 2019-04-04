package com.cockloft.core.base.reflection.invoker;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
public interface Invoker {
    Object invoke(Object target, Object[] args) throws IllegalAccessException, InvocationTargetException;

    Class<?> getType();
    Class<?>[] getParamType();
    Class<?> getReturnType();
    Annotation[] getAllAnnotation();
}
