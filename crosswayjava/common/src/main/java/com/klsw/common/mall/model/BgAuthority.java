package com.klsw.common.mall.model;

import javax.persistence.*;

@Table(name = "bg_Authority")
public class BgAuthority {
    @Column(name = "AuthorityID")
    private String authorityid;

    @Column(name = "AuthorityName")
    private String authorityname;

    /**
     * @return AuthorityID
     */
    public String getAuthorityid() {
        return authorityid;
    }

    /**
     * @param authorityid
     */
    public void setAuthorityid(String authorityid) {
        this.authorityid = authorityid;
    }

    /**
     * @return AuthorityName
     */
    public String getAuthorityname() {
        return authorityname;
    }

    /**
     * @param authorityname
     */
    public void setAuthorityname(String authorityname) {
        this.authorityname = authorityname;
    }
}