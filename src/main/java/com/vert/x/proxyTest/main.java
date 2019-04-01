package com.vert.x.proxyTest;

import java.lang.reflect.Proxy;

public class main {
    public static void main(String[] args) {
        Myproxy<TestInterface> myproxy = new Myproxy<>(new TestInterfaceImp());
        TestInterface testInterface = (TestInterface) Proxy.newProxyInstance(main.class.getClassLoader(),new Class[]{TestInterface.class},myproxy);
        testInterface.say("hehehe");
    }
}
