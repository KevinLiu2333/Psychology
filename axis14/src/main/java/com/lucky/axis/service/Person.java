package com.lucky.axis.service;

import java.util.Map;

public class Person {
    private String name;
    private int age;
    private Map skill;
    private Book mybook;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Map getSkill() {
        return skill;
    }
    public void setSkill(Map skill) {
        this.skill = skill;
    }
    public Book getMybook() {
        return mybook;
    }
    public void setMybook(Book mybook) {
        this.mybook = mybook;
    }

}