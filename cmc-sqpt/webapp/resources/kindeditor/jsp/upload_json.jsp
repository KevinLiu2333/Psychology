<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper"%>
<%@page import="com.wondersgroup.cmc.model.bo.SmsMsgAttach"%>
<%@page import="com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils"%>
<%@page import="com.wondersgroup.cmc.m37.m370102.SmsUtils"%>
<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="com.wondersgroup.cmc.utils.UserContextUtils"%>
<%@page import="com.wondersgroup.wssip.application.SessionConstants"%>
<%@page import="com.wondersgroup.wssip.util.StringTools"%>
<%@page import="com.wondersgroup.cmc.edata.EdataConstants,com.wondersgroup.cmc.edata.upload.model.common.PutResponseData,com.wondersgroup.cmc.edata.utils.EdataUtils"%>
<%@ page import="org.json.simple.*"%>
<%
	//定义允许上传的文件扩展名
	String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
	//允许最大上传文件大小
	long maxSize = 1024000;
	
	//Struts2 请求 包装过滤器
	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
	//获得上传的文件名
	String fileName = wrapper.getFileNames("imgFile")[0];
	//获得文件过滤器
	File file = wrapper.getFiles("imgFile")[0];
	//得到上传文件的扩展名
	String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	//检查扩展名
	if (!Arrays.<String> asList(fileTypes).contains(fileExt)) {
		out.println(getError("上传文件扩展名是不允许的扩展名。"));
		return;
	}
	//检查文件大小
	if (file.length() > maxSize) {
		out.println(getError("上传文件大小超过限制。"));
		return;
	}
	
	String organid = (String) request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ORGAN);
	if(StringTools.isEmpty(organid)){
		organid = "none";
	}
	// 数据库保存一笔记录
	SmsMsgAttach attach = new SmsMsgAttach();
	Long id = Long.parseLong(CommonHibernateDaoUtils.getSequenceValue("SEQ_SMS_ATTACH"));//获得主键
	attach.setAttachid(id);	//附件ID
	attach.setMsgid(wrapper.getSession().getId());	//消息ID
	attach.setMsgtype(SmsUtils.MSGTYPE_P);	//消息类型
	attach.setOrganid(organid);	//机构ID
	String attachname = String.format("%s_%016d.%s",attach.getOrganid(), attach.getAttachid(),fileExt);
	attach.setAttachname(attachname);	//附件名称
	attach.setAttachsize(file.length());//附件大小
	attach.setAttachtype(SmsUtils.HTMLIMAGETYPE+fileExt);	//附件类型
	attach.setFilepath("/"); 	//存储路径
	String md5 = DigestUtils.md5Hex(attachname);
	attach.setMd5(md5);//MD5码
	attach.setAttachfromid(id);	//附件来源ID
	attach.setUserid((String) request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));	//用户ID
	attach.setUsername((String) request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_NAME));//用户姓名
	attach.setCreatetime(new Date());	//创建日期
	attach.setRemoved(SmsUtils.SMSFALSE);	//删除标志
	CommonHibernateDaoUtils.save(attach);
	//上传小文件
	PutResponseData putResponseData = EdataUtils.upload(file, EdataConstants.CATALOG_ATTACH, organid, EdataConstants.TYPE_F01);
	attach.setFilepath(putResponseData.getUrl());
	CommonHibernateDaoUtils.update(attach);
	//发送给 KE 
	JSONObject obj = new JSONObject();
	obj.put("error", 0);
	obj.put("url", "../../../m37/m370102/smsLoadFile.action?attachfromid="+attach.getAttachfromid()+"&organid="+attach.getOrganid()+"&md5="+md5);
	out.println(obj.toJSONString());
%>
<%!private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}%>