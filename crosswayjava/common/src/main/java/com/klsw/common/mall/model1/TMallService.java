package com.klsw.common.mall.model1;

import java.util.Date;
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

    /**
     * �˻���1��������2��ά�ޣ�3��
     */
    @Column(name = "serviceType")
    private Integer servicetype;

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
     * ����ˣ�1�������ͨ����2�����δͨ����3�������У�4������ɣ�5
     */
    @Column(name = "serviceStatus")
    private Integer servicestatus;

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
    public Integer getServicetype() {
        return servicetype;
    }

    /**
     * �����˻���1��������2��ά�ޣ�3��
     *
     * @param servicetype �˻���1��������2��ά�ޣ�3��
     */
    public void setServicetype(Integer servicetype) {
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
     * ��ȡ����ˣ�1�������ͨ����2�����δͨ����3�������У�4������ɣ�5
     *
     * @return serviceStatus - ����ˣ�1�������ͨ����2�����δͨ����3�������У�4������ɣ�5
     */
    public Integer getServicestatus() {
        return servicestatus;
    }

    /**
     * ���ô���ˣ�1�������ͨ����2�����δͨ����3�������У�4������ɣ�5
     *
     * @param servicestatus ����ˣ�1�������ͨ����2�����δͨ����3�������У�4������ɣ�5
     */
    public void setServicestatus(Integer servicestatus) {
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
}