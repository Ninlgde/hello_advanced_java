package com.ninlgde.advanced.reflect;

import com.alibaba.fastjson.annotation.JSONField;

import java.lang.reflect.Field;

/**
 * Created by zejian on 2017/5/1.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 */
public class ReflectField {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.ninlgde.advanced.reflect.Student");
        //获取指定字段名称的Field类,注意字段修饰符必须为public而且存在该字段,
        // 否则抛NoSuchFieldException
        Field field = clazz.getField("age");
        System.out.println("field:" + field);

        JSONField a = field.getAnnotation(JSONField.class);
        System.out.println(a.serialize());

        //获取所有修饰符为public的字段,包含父类字段,注意修饰符为public才会获取
        Field fields[] = clazz.getFields();
        for (Field f : fields) {
            System.out.println("f:" + f.getDeclaringClass());
        }

        System.out.println("================getDeclaredFields====================");
        //获取当前类所字段(包含private字段),注意不包含父类的字段
        Field fields2[] = clazz.getDeclaredFields();
        for (Field f : fields2) {
            System.out.println("f2:" + f.getDeclaringClass());
        }
        //获取指定字段名称的Field类,可以是任意修饰符的自动,注意不包含父类的字段
        Field field2 = clazz.getDeclaredField("desc");
        System.out.println("field2:" + field2);
    }
    /**
     输出结果:
     field:public int reflect.Person.age
     f:public java.lang.String reflect.Student.desc
     f:public int reflect.Person.age
     f:public java.lang.String reflect.Person.name

     ================getDeclaredFields====================
     f2:public java.lang.String reflect.Student.desc
     f2:private int reflect.Student.score
     field2:public java.lang.String reflect.Student.desc
     */
}

class Person {
    @JSONField
    public int age;
    public String name;
    //省略set和get方法

    public int getAge() {
        return age;
    }
}

class Student extends Person {
    public String desc;
    private int score;
    //省略set和get方法
}