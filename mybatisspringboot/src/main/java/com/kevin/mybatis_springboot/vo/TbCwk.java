package com.kevin.mybatis_springboot.vo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/15
 * Time: 13:45
 */
public class TbCwk {
    @NotBlank(groups = NAME.class)
    private String name;
    @Min(value = 1,groups = ID.class)
    private Integer id;
    @NotBlank
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public interface NAME{};
    public interface ID{};
}
