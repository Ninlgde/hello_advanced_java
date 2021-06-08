package com.ninlgde.db.mybatis.entity;

public class Student {
    private Integer id;
    private String name;
    private Double sal;

    public Student() {
    }

    public Student(int id, String name, Double sal) {
        this.id = id;
        this.name = name;
        this.sal = sal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    @Override
    public String toString() {
        return getId() + "---" + getName() + "----" + getSal();
    }
}
