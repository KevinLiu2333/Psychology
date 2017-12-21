package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "JCPT_QUERY_LOG")
public class JcptQueryLog {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "DEPT_NAME")
    private BigDecimal deptName;

    @Column(name = "CTIME")
    private Date ctime;

    @Column(name = "YYQD_ID")
    private BigDecimal yyqdId;

    @Column(name = "CORP_NAME")
    private String corpName;

    @Column(name = "STATUS")
    private BigDecimal status;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "USER_ID")
    private BigDecimal userId;

    @Column(name = "APPLY_ITEM")
    private String applyItem;

    @Column(name = "MSG_CLASS")
    private String msgClass;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return DEPT_NAME
     */
    public BigDecimal getDeptName() {
        return deptName;
    }

    /**
     * @param deptName
     */
    public void setDeptName(BigDecimal deptName) {
        this.deptName = deptName;
    }

    /**
     * @return CTIME
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * @return YYQD_ID
     */
    public BigDecimal getYyqdId() {
        return yyqdId;
    }

    /**
     * @param yyqdId
     */
    public void setYyqdId(BigDecimal yyqdId) {
        this.yyqdId = yyqdId;
    }

    /**
     * @return CORP_NAME
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * @param corpName
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * @return STATUS
     */
    public BigDecimal getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    /**
     * @return USERNAME
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return USER_ID
     */
    public BigDecimal getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    /**
     * @return APPLY_ITEM
     */
    public String getApplyItem() {
        return applyItem;
    }

    /**
     * @param applyItem
     */
    public void setApplyItem(String applyItem) {
        this.applyItem = applyItem;
    }

    /**
     * @return MSG_CLASS
     */
    public String getMsgClass() {
        return msgClass;
    }

    /**
     * @param msgClass
     */
    public void setMsgClass(String msgClass) {
        this.msgClass = msgClass;
    }
}