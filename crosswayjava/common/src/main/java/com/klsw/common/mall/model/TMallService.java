package com.klsw.common.mall.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Table(name = "t_mall_service")
public class TMallService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ���񵥺�
     */
    @Column(name = "orderSerial")
    private String orderserial;
    
    
    @Column(name = "productSerial")
    private String  productserial;
    
    
    public String getProductserial() {
		return productserial;
	}

	public void setProductserial(String productserial) {
		this.productserial = productserial;
	}


	/**
     * ���񵥺�
     */
    @Column(name = "userId")
    private Integer userid;

    public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	/**
     * �˻���1��������2��ά�ޣ�3��
     */
    @Column(name = "serviceType")
    private String  servicetype;

    /**
     * ��������
     */
    @Column(name = "serviceDescribe")
    private String servicedescribe;

    /**
     * �ϴ�ͼƬ
     */
    @Column(name = "serviceImg")
    private String serviceimg;

    /**
     * ����ˣ�1�������ͨ��2�����δͨ��3�������У�4������ɣ�5
     */
    @Column(name = "serviceStatus")
    private String  servicestatus;

    @Column(name = "apply_time")
    private Date applyTime;

    @Column(name = "handle_time")
    private Date handleTime;

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
     * ��ȡ���񵥺�
     *
     * @return orderSerial - ���񵥺�
     */
    public String getOrderserial() {
        return orderserial;
    }

    /**
     * ���÷��񵥺�
     *
     * @param orderserial ���񵥺�
     */
    public void setOrderserial(String orderserial) {
        this.orderserial = orderserial;
    }

    /**
     * ��ȡ�˻���1��������2��ά�ޣ�3��
     *
     * @return serviceType - �˻���1��������2��ά�ޣ�3��
     */
    public String getServicetype() {
        return servicetype;
    }

    /**
     * �����˻���1��������2��ά�ޣ�3��
     *
     * @param servicetype �˻���1��������2��ά�ޣ�3��
     */
    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    /**
     * ��ȡ��������
     *
     * @return serviceDescribe - ��������
     */
    public String getServicedescribe() {
        return servicedescribe;
    }

    /**
     * ������������
     *
     * @param servicedescribe ��������
     */
    public void setServicedescribe(String servicedescribe) {
        this.servicedescribe = servicedescribe;
    }

    /**
     * ��ȡ�ϴ�ͼƬ
     *
     * @return serviceImg - �ϴ�ͼƬ
     */
    public String getServiceimg() {
        return serviceimg;
    }

    /**
     * �����ϴ�ͼƬ
     *
     * @param serviceimg �ϴ�ͼƬ
     */
    public void setServiceimg(String serviceimg) {
        this.serviceimg = serviceimg;
    }

    /**
     * ��ȡ����ˣ�1�������ͨ��2�����δͨ��3�������У�4������ɣ�5
     *
     * @return serviceStatus - ����ˣ�1�������ͨ��2�����δͨ��3�������У�4������ɣ�5
     */
    public String getServicestatus() {
        return servicestatus;
    }

    /**
     * ���ô���ˣ�1�������ͨ��2�����δͨ��3�������У�4������ɣ�5
     *
     * @param servicestatus ����ˣ�1�������ͨ��2�����δͨ��3�������У�4������ɣ�5
     */
    public void setServicestatus(String servicestatus) {
        this.servicestatus = servicestatus;
    }

    /**
     * @return apply_time
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * @param applyTime
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * @return handle_time
     */
    public Date getHandleTime() {
        return handleTime;
    }

    /**
     * @param handleTime
     */
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
    
    
    @Transient
	private List<TMallProduct> productList;
    

	public List<TMallProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<TMallProduct> productList) {
		this.productList = productList;
	}

	@Transient
	private String productname;
	
	@Transient
	private String productcolor;
	
	
	
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductcolor() {
		return productcolor;
	}

	public void setProductcolor(String productcolor) {
		this.productcolor = productcolor;
	}

	@Override
	public String toString() {
		return "TMallService [id=" + id + ", orderserial=" + orderserial + ", userid=" + userid + ", servicetype="
				+ servicetype + ", servicedescribe=" + servicedescribe + ", serviceimg=" + serviceimg
				+ ", servicestatus=" + servicestatus + ", applyTime=" + applyTime + ", handleTime=" + handleTime
				+ ", productList=" + productList + "]";
	}

	

}