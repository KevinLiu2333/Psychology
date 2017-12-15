package com.kevin.springboot.utils.code;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2017/12/5
 * Time: 14:00
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -8973319212661151502L;
    private String name;

    private int age;

    private Date birth;

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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
