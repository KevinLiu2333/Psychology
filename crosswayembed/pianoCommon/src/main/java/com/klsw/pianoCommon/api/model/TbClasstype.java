package com.klsw.pianoCommon.api.model;

import javax.persistence.*;

@Table(name = "tb_ClassType")
public class TbClasstype {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TypeName")
    private String typename;

    private Integer sort;

    private Byte isfree;

    private Long price;

    private Byte auditing;

    private String cwkuserid;

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
     * @return TypeName
     */
    public String getTypename() {
        return typename;
    }

    /**
     * @param typename
     */
    public void setTypename(String typename) {
        this.typename = typename;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return isfree
     */
    public Byte getIsfree() {
        return isfree;
    }

    /**
     * @param isfree
     */
    public void setIsfree(Byte isfree) {
        this.isfree = isfree;
    }

    /**
     * @return price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * @return auditing
     */
    public Byte getAuditing() {
        return auditing;
    }

    /**
     * @param auditing
     */
    public void setAuditing(Byte auditing) {
        this.auditing = auditing;
    }

    /**
     * @return cwkuserid
     */
    public String getCwkuserid() {
        return cwkuserid;
    }

    /**
     * @param cwkuserid
     */
    public void setCwkuserid(String cwkuserid) {
        this.cwkuserid = cwkuserid;
    }
}