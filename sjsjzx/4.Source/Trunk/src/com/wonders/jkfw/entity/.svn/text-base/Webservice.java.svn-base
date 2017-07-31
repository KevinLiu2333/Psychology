package com.wonders.jkfw.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 数据接口服务基本信息.
 */
@Table("T_WEBSERVICE")
public class Webservice {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
    @Column("SERVICE_ID")
	private String serviceId;
	/**接口名称**/
	@Column("SERVICE_NAME")
	private String serviceName;
	/**参数说明**/
	@Column("PARAM")
	private String param;
	/**返回值说明**/
	@Column("RETURN_MEMO")
	private String returnMemo;
	/**状态**/
	@Column("STATUS")
	private String status;
	/**文件下载链接**/
	@Column("DOC_URL")
	private String docUrl;
	/**服务链接**/
	@Column("SERVICE_URL")
	private String serviceUrl;
	/**服务接口类型**/
	@Column("TYPE")
	private String type;
	/**备注或功能描述**/
	@Column("FUNCTION_MEMO")
	private String functionMemo;
	/**服务创建时间**/
	@Column("CREATE_TIME")
	private Date createTime;
	/**服务提供部门**/
	@Column("FROM_DEPARTMENT")
	private String fromDepartment;
	
	public String initUUID() {
		if (Strings.isEmpty(serviceId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.serviceId;
	}
	
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getReturnMemo() {
		return returnMemo;
	}
	public void setReturnMemo(String returnMemo) {
		this.returnMemo = returnMemo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDocUrl() {
		return docUrl;
	}
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}
	public String getServiceUrl() {
		return serviceUrl;
	}
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFunctionMemo() {
		return functionMemo;
	}
	public void setFunctionMemo(String functionMemo) {
		this.functionMemo = functionMemo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFromDepartment() {
		return fromDepartment;
	}
	public void setFromDepartment(String fromDepartment) {
		this.fromDepartment = fromDepartment;
	}
}
