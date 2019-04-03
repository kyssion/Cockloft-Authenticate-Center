package com.cokloft.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RefenceTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, TestItem> map = new HashMap<>();
        TestItem testItem = new TestItem();
        testItem.setAge("123");
        testItem.setName("lislsi");
        map.put("item", testItem);
        String json = mapper.writeValueAsString(map);
        Map<String, TestItem> map1 = mapper.readValue(json, new TypeReference<Object>(){});
        System.out.println(map1.get("item").getName());
    }
}

class TestItem {
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
