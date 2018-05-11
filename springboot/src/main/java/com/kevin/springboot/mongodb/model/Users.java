package com.kevin.springboot.mongodb.model;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: qxb-810
 * Date: 2018/5/11
 * Time: 8:51
 */
@Document(collection = "users")
//复合主键 name和age将作为复合索引，数字参数指定索引的方向，1为正序，-1为倒序。方向对单键索引和随机存不要紧，但如果你要执行分组和排序操作的时候，它就非常重要了。
@CompoundIndexes({
        @CompoundIndex(name = "age_idx", def = "{'name': 1, 'age': -1}")
})
public class Users implements Serializable {
    private static final long serialVersionUID = -4834565839056586479L;
    @Indexed
    private String uid;

    private String name;
    private int age;
    @Transient
    private String address;

    public Users(String uid, String name, int age) {
        super();
        this.uid = uid;
        this.name = name;
        this.age = age;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return String.format("Customer[name='%s', age='%s']",
                name, age);
    }
}







