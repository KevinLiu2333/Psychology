package com.klsw.apiCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_UserExperience")
public class TbUserexperience {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
}