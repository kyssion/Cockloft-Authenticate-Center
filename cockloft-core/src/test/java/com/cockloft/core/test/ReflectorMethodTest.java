package com.cockloft.core.test;


public class ReflectorMethodTest {
    public static void main(String[] args) {
        new ReflectorMethodTest().test1();
    }

    public void test1(){

        ItemTestC itemTestC = new ItemTestC();
        itemTestC.setC(true);
        itemTestC.setAgeB(4433);
        itemTestC.setNameB("this is test 345");

    }
}
