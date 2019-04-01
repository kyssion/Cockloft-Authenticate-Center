package com.vert.x.proxyTest;

public class TestInterfaceImp implements TestInterface {
    @Override
    public String say(String he) {
        System.out.println("this is max one" + he);
        return he;
    }
}
