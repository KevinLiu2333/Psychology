<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/code.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
<div class='container'>
    <h3 class="page-header">调用示例</h3>
    <div class="panel">
        <div class="panel-body">
            <h4 class="page-header">访问地址</h4>
            <div  class="dp-highlighter">
                <ol class="dp-j" start="1">
                    <li>公开URL：<span>${obj.basePath}/fw/services?<code>unitKey</code>=***&<code>methodKey</code>=***<c:forEach  var="config"  items="${obj.fwConfigList}" ><c:if test="${config.configType == '1'}">&<code>${config.colName}</code>=***</c:if></c:forEach></span></li>
                </ol>
                 <ol class="dp-j" start="1">
                    <li>内部URL：<span>${obj.basePath}/fw/ptservices?<code>unitKey</code>=***&<code>fwCode</code>=***<c:forEach  var="config"  items="${obj.fwConfigList}" ><c:if test="${config.configType == '1'}">&<code>${config.colName}</code>=***</c:if></c:forEach></span></li>
                </ol>
            </div>
            <h4 class="page-header">参数说明</h4>
            <div  class="dp-highlighter">
                <div class="dp-highlighter">
                    <ol class="dp-j" start="1">
                        <li><span ><b>固定参数：</b></span></li>
                        <li><span><code>unitKey</code>:服务key（注册成功后发放）<br></span></li>
                        <li><span><code>methodKey</code>:服务接口访问KEY（申请成功后发放）<br></span></li>
                        <li><span><code>fwCode</code>:服务代码（仅限内部URL使用）<br></span></li>
                        <li><span><b>可选参数：</b></span></li>
                        <li><span>format:返回数据格式（默认json格式）<br></span></li>
                        <li><span>rsStyle:返回数据格式风格（simple:简单风格,complex;齐全风格。默认complex风格）<br></span></li>
                        <li><span>rsStructure:返回数据格式结构（SR:单一结果,KV;单条记录,LM:ListMap,MA;map数组。默认LM格式）<br></span></li>
                        <li><span> <b>传入参数：</b></span></li>
                        <c:forEach  var="config"  items="${obj.fwConfigList}" >
                            <c:if test="${config.configType == '1'}">
                            <li><span><code>${config.colName}</code>:${config.colComment}<br></span></li>
                            </c:if>
                        </c:forEach>
                    </ol>
                </div>
            </div>
            <h4 class="page-header">返回结果(<span class="alert-success">SUCCESS:调用是否成功；ERROR：错误信息；
            <c:if test="${obj.fwInfo.fwTypeCode == 'Jiekou1'}">
            	RESULT:结果（1存在；0不存在）
            </c:if>
             <c:if test="${obj.fwInfo.fwTypeCode == 'Jiekou4'}">
            	META:数据项解释；DATA返回数据值
            </c:if>
             <c:if test="${obj.fwInfo.fwTypeCode == 'Jiekou5'}">
            	META:数据项解释；DATA返回数据值
            </c:if>
            <c:if test="${obj.fwInfo.fwTypeCode == 'Jiekou3'}">
            	DATA返回数据值
            </c:if>
             <c:if test="${obj.fwInfo.fwTypeCode == 'Jiekou2'}">
            	DATA返回数据值
            </c:if>
            
            </span>)</h4>
            <div class="dp-highlighter">
                <ol class="dp-j" start="1">
                 <li><span>${obj.resultJson}</span></li>
                </ol>
            </div>

        </div>
    </div>

</div>
</body>

</html>
