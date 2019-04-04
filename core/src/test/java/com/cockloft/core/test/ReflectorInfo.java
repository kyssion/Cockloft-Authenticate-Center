package com.cockloft.core.test;

import com.cockloft.core.base.psql.script.conversion.VertxTypeConversion;
import io.vertx.ext.sql.ResultSet;

import java.util.Map;
import java.util.Set;

public class ReflectorInfo {
    public static void main(String[] args) {
        Item2 item2 = new Item2();
        item2.setAge("123");
        item2.setName("name2");
        Item item = new Item();
        item.setItem2(item2);
        item.setName("name");
        item.setAge("333");
        VertxTypeConversion<Item,ResultSet> vertxTypeConversion = new VertxTypeConversion<>();
        item = vertxTypeConversion.concersion(new ResultSet());
        System.out.println(item.getName());
    }
}

class Item{
    private String name;
    private String age;
    private Item2 item2;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setItem2(Item2 item2) {
        this.item2 = item2;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public Item2 getItem2() {
        return item2;
    }
}
class Item2{
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}