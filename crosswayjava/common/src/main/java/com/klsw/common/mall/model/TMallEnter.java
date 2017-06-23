package com.klsw.common.mall.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_enter")
public class TMallEnter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String telephone;

    private String city;

    private Date ctime;

    private Date ltime;
    
    private String career;
    
    /**
     * @return carrer
     */
    public String getCareer() {
		return career;
	}
    
    /**
     * @param career
     */
	public void setCareer(String career) {
		this.career = career;
	}

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
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
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