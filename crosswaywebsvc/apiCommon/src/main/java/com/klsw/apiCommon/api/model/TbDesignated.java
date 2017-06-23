package com.klsw.apiCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_designated")
public class TbDesignated {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String studentid;

    private String teacherid;

    private Byte agree;

    private Long cost;

    @Column(name = "addDate")
    private Date adddate;

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
     * @return studentid
     */
    public String getStudentid() {
        return studentid;
    }

    /**
     * @param studentid
     */
    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    /**
     * @return teacherid
     */
    public String getTeacherid() {
        return teacherid;
    }

    /**
     * @param teacherid
     */
    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * @return agree
     */
    public Byte getAgree() {
        return agree;
    }

    /**
     * @param agree
     */
    public void setAgree(Byte agree) {
        this.agree = agree;
    }

    /**
     * @return cost
     */
    public Long getCost() {
        return cost;
    }

    /**
     * @param cost
     */
    public void setCost(Long cost) {
        this.cost = cost;
    }

    /**
     * @return addDate
     */
    public Date getAdddate() {
        return adddate;
    }

    /**
     * @param adddate
     */
    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }
}