package com.cockloft.core.test;

import com.cockloft.core.base.reflection.DefaultReflectorFactory;
import com.cockloft.core.base.reflection.Reflector;
import com.cockloft.core.base.reflection.meta.MetaClass;

public class RouterTest {
    public static void main(String[] args) {
        Reflector reflector = new Reflector(Supper.class);
        String name=MetaClass.forClass(TestTTT.class,new DefaultReflectorFactory()).findProperty("test.supper.number");
        System.out.println(name);
    }
}
class Base {
    private Number number;
    private boolean isup;

    public boolean isIsup() {
        return isup;
    }

    public void setIsup(boolean isup) {
        this.isup = isup;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }
}
class Supper extends Base{
    public Integer getNumber(){
        return (Integer) super.getNumber();
    }
}

class Test{
    private Supper supper;

    public Supper getSupper() {
        return supper;
    }

    public void setSupper(Supper supper) {
        this.supper = supper;
    }
}

class TestTTT{
    private Test test;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}