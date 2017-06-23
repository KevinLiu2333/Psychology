package com.klsw.common.mall.model1;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mall_service")
public class TMallService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 服务单号
     */
    @Column(name = "orderSerial")
    private String orderserial;

    /**
     * 退货：1；换货：2；维修：3；
     */
    @Column(name = "serviceType")
    private Integer servicetype;

    /**
     * 问题描述
     */
    @Column(name = "serviceDescribe")
    private String servicedescribe;

    /**
     * 上传图片
     */
    @Column(name = "serviceImg")
    private String serviceimg;

    /**
     * 待审核：1；审核已通过：2；审核未通过：3；处理中：4；已完成：5
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
     * 获取服务单号
     *
     * @return orderSerial - 服务单号
     */
    public String getOrderserial() {
        return orderserial;
    }

    /**
     * 设置服务单号
     *
     * @param orderserial 服务单号
     */
    public void setOrderserial(String orderserial) {
        this.orderserial = orderserial;
    }

    /**
     * 获取退货：1；换货：2；维修：3；
     *
     * @return serviceType - 退货：1；换货：2；维修：3；
     */
    public Integer getServicetype() {
        return servicetype;
    }

    /**
     * 设置退货：1；换货：2；维修：3；
     *
     * @param servicetype 退货：1；换货：2；维修：3；
     */
    public void setServicetype(Integer servicetype) {
        this.servicetype = servicetype;
    }

    /**
     * 获取问题描述
     *
     * @return serviceDescribe - 问题描述
     */
    public String getServicedescribe() {
        return servicedescribe;
    }

    /**
     * 设置问题描述
     *
     * @param servicedescribe 问题描述
     */
    public void setServicedescribe(String servicedescribe) {
        this.servicedescribe = servicedescribe;
    }

    /**
     * 获取上传图片
     *
     * @return serviceImg - 上传图片
     */
    public String getServiceimg() {
        return serviceimg;
    }

    /**
     * 设置上传图片
     *
     * @param serviceimg 上传图片
     */
    public void setServiceimg(String serviceimg) {
        this.serviceimg = serviceimg;
    }

    /**
     * 获取待审核：1；审核已通过：2；审核未通过：3；处理中：4；已完成：5
     *
     * @return serviceStatus - 待审核：1；审核已通过：2；审核未通过：3；处理中：4；已完成：5
     */
    public Integer getServicestatus() {
        return servicestatus;
    }

    /**
     * 设置待审核：1；审核已通过：2；审核未通过：3；处理中：4；已完成：5
     *
     * @param servicestatus 待审核：1；审核已通过：2；审核未通过：3；处理中：4；已完成：5
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