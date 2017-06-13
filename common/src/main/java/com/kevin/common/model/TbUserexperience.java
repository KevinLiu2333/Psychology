package com.kevin.common.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_userexperience")
public class TbUserexperience {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SerialNumber")
    private String serialnumber;

    @Column(name = "Time")
    private Date time;

    @Column(name = "Button")
    private String button;

    @Column(name = "CurPage")
    private String curpage;

    @Column(name = "NextPage")
    private String nextpage;

    @Column(name = "Ip")
    private String ip;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return SerialNumber
     */
    public String getSerialnumber() {
        return serialnumber;
    }

    /**
     * @param serialnumber
     */
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    /**
     * @return Time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return Button
     */
    public String getButton() {
        return button;
    }

    /**
     * @param button
     */
    public void setButton(String button) {
        this.button = button;
    }

    /**
     * @return CurPage
     */
    public String getCurpage() {
        return curpage;
    }

    /**
     * @param curpage
     */
    public void setCurpage(String curpage) {
        this.curpage = curpage;
    }

    /**
     * @return NextPage
     */
    public String getNextpage() {
        return nextpage;
    }

    /**
     * @param nextpage
     */
    public void setNextpage(String nextpage) {
        this.nextpage = nextpage;
    }

    /**
     * @return Ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}