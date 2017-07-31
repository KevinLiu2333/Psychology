package com.wondersgroup.cmc.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * AbstractOrgan entity provides the base persistence definition of the Organ entity. @author MyEclipse Persistence
 * Tools
 */
@MappedSuperclass
public abstract class BaseOrgan implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String organcode;
	private String organname;
	private String organlevel;
	private String organtype;
	private String organregion;
	private String address;
	private Double longitude;
	private Double latitude;
	private String telephone;
	private String opentime;
	private String ordertag;
	private String parentid;
    private String servicestype ; 
	// Property accessors
	@Id
	@GeneratedValue(generator="SEQ_ORGANCODE")
	@GenericGenerator(name="SEQ_ORGANCODE", strategy="native", parameters={@org.hibernate.annotations.Parameter(name="sequence", value="SEQ_ORGANCODE"), @org.hibernate.annotations.Parameter(name="unsaved-value", value="0")})
	@Column(name = "ORGANCODE", unique = true, nullable = false, length = 16)
	public String getOrgancode() {
		return this.organcode;
	}

	public void setOrgancode(String organcode) {
		this.organcode = organcode;
	}

	@Column(name = "ORGANNAME", nullable = false, length = 100)
	public String getOrganname() {
		return this.organname;
	}

	public void setOrganname(String organname) {
		this.organname = organname;
	}

	@Column(name = "ORGANLEVEL", nullable = false, length = 1)
	public String getOrganlevel() {
		return this.organlevel;
	}

	public void setOrganlevel(String organlevel) {
		this.organlevel = organlevel;
	}

	@Column(name = "ORGANTYPE", nullable = false, length = 1)
	public String getOrgantype() {
		return this.organtype;
	}

	public void setOrgantype(String organtype) {
		this.organtype = organtype;
	}

	@Column(name = "ORGANREGION", length = 12)
	public String getOrganregion() {
		return this.organregion;
	}

	public void setOrganregion(String organregion) {
		this.organregion = organregion;
	}
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "LONGITUDE")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(name = "LATITUDE")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name = "OPENTIME")
	public String getOpentime() {
		return opentime;
	}

	public void setOpentime(String opentime) {
		this.opentime = opentime;
	}
	@Column(name = "ORDERTAG")
	public String getOrdertag() {
		return ordertag;
	}

	public void setOrdertag(String ordertag) {
		this.ordertag = ordertag;
	}
	@Column(name = "PARENTID")
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Column(name = "SERVICESTYPE")
	public String getServicestype() {
		return servicestype;
	}

	public void setServicestype(String servicestype) {
		this.servicestype = servicestype;
	}

}
