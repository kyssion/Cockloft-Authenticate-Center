package com.cockloft.core.base.reflection.invoker;

import com.cockloft.core.base.reflection.Reflector;

import java.lang.reflect.Field;

public class GetFieldInvoker implements Invoker {
    private final Field field;

    public GetFieldInvoker(Field field) {
        this.field = field;
    }

    /**
     * 针对java9+ 对反射的控制,使用canControlMemberAccessible进行反射能力的检查和校验
     * @param target
     * @param args
     * @return
     * @throws IllegalAccessException
     */
    @Override
    public Object invoke(Object target, Object[] args) throws IllegalAccessException {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            if (Reflector.canControlMemberAccessible()) {
                field.setAccessible(true);
                return field.get(target);
            } else {
                throw e;
            }
        }
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}