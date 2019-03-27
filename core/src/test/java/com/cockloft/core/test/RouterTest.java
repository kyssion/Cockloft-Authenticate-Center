package com.cockloft.core.test;

import com.cockloft.core.base.reflection.Reflector;

public class RouterTest {
    public static void main(String[] args) {
        Reflector reflector = new Reflector(Supper.class);
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