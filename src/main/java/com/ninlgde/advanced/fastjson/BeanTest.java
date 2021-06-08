package com.ninlgde.advanced.fastjson;

import java.lang.reflect.Field;

public class BeanTest {
    public static class MyBean {
        public String id;
        public String name;
        public Integer score;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String json = "{ \"id\": \"A10001\", \"name\": \"Arthur.Zhang\", \"score\": 100 }";
        // 去掉头尾的 {}
        String str = json.substring(1, json.length() - 1);
        // 用 "," 分割字符串
        String[] fieldStrArray = str.split(",");
        MyBean bean = new MyBean();
        for (String item : fieldStrArray) {
            // 分隔 key value
            String[] parts = item.split(":");
            String key = parts[0].replaceAll("\"", "").trim();
            String value = parts[1].replaceAll("\"", "").trim();
            // 通过反射获取字段对应的 field
            Field field = MyBean.class.getDeclaredField(key);
            // 根据字段类型通过反射设置字段的值
            if (field.getType() == String.class) {
                field.set(bean, value);
            } else if (field.getType() == Integer.class) {
                field.set(bean, Integer.valueOf(value));
            }
        }
        System.out.println(bean);
    }
}
