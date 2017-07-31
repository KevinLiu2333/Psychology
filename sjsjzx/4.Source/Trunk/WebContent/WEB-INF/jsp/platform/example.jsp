<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>松江区政务数据中心-调用案例</title>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/tiles/bootstrap2.3.2/css/bootstrap.css" rel="stylesheet">
    
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/tiles/bootstrap2.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<!-- top of the page -->
<div class='container'>
    <h3 id="disable-responsive2" class="page-header">调用示例</h3>
    <div class="panel">
        <div class="panel-body">
            <ul class="note-info">
                <li>
                    <div class="title"><h4>访问地址</h4></div>
                    <div class="desk"><pre> ${obj.basePath}/sj/services?userKey=***&methodKey=***&可传参数=****</pre></div>
                </li>
                <li>
                    <div class="title"><h4>参数说明</h4></div>
                    <div class="desk"><pre>
                        <b>固定参数：</b>
                        userKey:SJ201600234 （服务key）<br>
                        methodKey:${obj.fwApply.methodKey}（服务接口访问KEY）<br>
                         <b>可传参数：</b>
                        <c:forEach  var="config"  items="${obj.fwConfigList}" >
                        ${config.enName}:${config.cnName}（传入参数）<br>
                        </c:forEach>
                        ${requestScope.getServerName}
                        </pre>
                    </div>
                </li>
                <li>
                    <div class="title"><h4>返回结果(json)</h4></div>
                    <div class="desk"><pre><xmp>
                    ${obj.resultJson}
                    </xmp></pre></div>
                </li>


            </ul>
        </div>
    </div>
   

</div>
</body>

</html>
