package com.klsw.apiCommon.api.model;

import javax.persistence.*;

@Table(name = "tb_MIDClass")
public class TbMidclass {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ClassName")
    private String classname;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return ClassName
     */
    public String getClassname() {
        return classname;
    }

    /**
     * @param classname
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }
}