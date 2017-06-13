package com.kevin.common.model;

import javax.persistence.*;

@Table(name = "wx_token")
public class WxToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    private String ticket;

    private Long utp;

    /**
     * @return id
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
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * @param ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * @return utp
     */
    public Long getUtp() {
        return utp;
    }

    /**
     * @param utp
     */
    public void setUtp(Long utp) {
        this.utp = utp;
    }
}