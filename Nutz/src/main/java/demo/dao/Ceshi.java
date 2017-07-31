package demo.dao;

import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

/**
 * dao测试类
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/25 0025
 * Time: 8:31
 */
@Table("ceshi20170725")
public class Ceshi {
    @Id
    @Column("ID")
    private Integer id;

    @Name   //表示该字段可以用来标识此对象,或者是字符型主键
    @Column("Name")
    private String name;

    @Column("sex")
    //插入操作之前设值 DB指定方言
    @Prev(@SQL(db = DB.MYSQL,value = "select sex from ceshi20170725 where name = @name"))
    private String sex;

    @Column("Age")
    private Integer age;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
