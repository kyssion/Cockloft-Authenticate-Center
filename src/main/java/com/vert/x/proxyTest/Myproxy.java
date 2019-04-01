package com.vert.x.proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Myproxy<T> implements InvocationHandler {

    private T testT;

    public Myproxy(T testT){
        this.testT=testT;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object tiem = method.invoke(testT,args);
        System.out.println("end");
        return tiem;
    }
}
