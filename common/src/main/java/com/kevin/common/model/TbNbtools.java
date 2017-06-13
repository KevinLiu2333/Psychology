package com.kevin.common.model;

import javax.persistence.*;

@Table(name = "tb_nbtools")
public class TbNbtools {
    /**
     * 道具id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 道具名称
     */
    private String name;

    /**
     * 道具功能
     */
    private String desciption;

    /**
     * 获取道具id
     *
     * @return id - 道具id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置道具id
     *
     * @param id 道具id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取道具名称
     *
     * @return name - 道具名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置道具名称
     *
     * @param name 道具名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取道具功能
     *
     * @return desciption - 道具功能
     */
    public String getDesciption() {
        return desciption;
    }

    /**
     * 设置道具功能
     *
     * @param desciption 道具功能
     */
    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}