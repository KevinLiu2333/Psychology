package com.klsw.pianoCommon.api.model;

import javax.persistence.*;

@Table(name = "tb_CWKFavorite")
public class TbCwkfavorite {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CWKID")
    private Integer cwkid;

    @Column(name = "CWKNewsID")
    private Integer cwknewsid;

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
     * @return CWKID
     */
    public Integer getCwkid() {
        return cwkid;
    }

    /**
     * @param cwkid
     */
    public void setCwkid(Integer cwkid) {
        this.cwkid = cwkid;
    }

    /**
     * @return CWKNewsID
     */
    public Integer getCwknewsid() {
        return cwknewsid;
    }

    /**
     * @param cwknewsid
     */
    public void setCwknewsid(Integer cwknewsid) {
        this.cwknewsid = cwknewsid;
    }
}