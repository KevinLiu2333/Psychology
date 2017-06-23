package com.klsw.common.mall.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_receipt")
public class TMallReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "orderSerial")
    private String orderserial;

    private String header;

    @Column(name = "receiptSerial")
    private String receiptserial;

    private Date ctime;

    private Date ltime;

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
     * @return orderSerial
     */
    public String getOrderserial() {
        return orderserial;
    }

    /**
     * @param orderserial
     */
    public void setOrderserial(String orderserial) {
        this.orderserial = orderserial;
    }

    /**
     * @return header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @return receiptSerial
     */
    public String getReceiptserial() {
        return receiptserial;
    }

    /**
     * @param receiptserial
     */
    public void setReceiptserial(String receiptserial) {
        this.receiptserial = receiptserial;
    }

    /**
     * @return ctime
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
     * @return ltime
     */
    public Date getLtime() {
        return ltime;
    }

    /**
     * @param ltime
     */
    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }
}