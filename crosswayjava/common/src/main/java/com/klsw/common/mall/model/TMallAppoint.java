package com.klsw.common.mall.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_appoint")
public class TMallAppoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String telephone;

    private String email;

    private String address;

    /**
     * Ԥ����Ʒ���к�
     */
    @Column(name = "productSerial")
    private String productserial;

    /**
     * Ԥ����Ʒ����
     */
    @Column(name = "productCount")
    private String productcount;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * ��ȡԤ����Ʒ���к�
     *
     * @return productSerial - Ԥ����Ʒ���к�
     */
    public String getProductserial() {
        return productserial;
    }

    /**
     * ����Ԥ����Ʒ���к�
     *
     * @param productserial Ԥ����Ʒ���к�
     */
    public void setProductserial(String productserial) {
        this.productserial = productserial;
    }

    /**
     * ��ȡԤ����Ʒ����
     *
     * @return productCount - Ԥ����Ʒ����
     */
    public String getProductcount() {
        return productcount;
    }

    /**
     * ����Ԥ����Ʒ����
     *
     * @param productcount Ԥ����Ʒ����
     */
    public void setProductcount(String productcount) {
        this.productcount = productcount;
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