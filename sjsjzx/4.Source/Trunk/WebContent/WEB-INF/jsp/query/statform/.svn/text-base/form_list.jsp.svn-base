<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>松江区数据资源管理系统</title>
<link href="${ctx}/skins/style/css/style.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
ul{list-style-type:none; margin:0;width:100%; } 
ul li{ width:300px; float:left;margin: 30px;background-color: #F7F7F7;} 
</style>
</head>
<body>
<div align="center" style="padding-left: 30px;padding-top: 30px" >
  <c:if test="${obj.querySaveList== null || fn:length(obj.querySaveList) == 0}">
                         	<p style="font-size: 20px;color: red">暂无配置报表,请到"自定义查询统计"菜单进行报表配置。</p>
                          </c:if>
  <ul class="slides">
                          <c:forEach var="form" items="${obj.querySaveList}" varStatus="vs">
                            <li >
                              <a target="_blank" href="${ctx}/query/statForm/preview?saveId=${form.saveId}"><img src="${ctx}/tiles/query/img/form.png" width="300px" height="150px" >
                              <span><strong>${form.name}</strong><br/>${form.saveDesc}</span></a>
                            </li>
                            </c:forEach>
                          </ul>
                        </div>
</body>
</html>