<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>

</head> 
<body>

<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header"><i class="ace-icon fa fa-leaf green"></i>&nbsp;&nbsp;节点管理</h3>
    <form id="mainForm" name="mainForm" action="${ctx }/zy/saveBm" method="post" >
   		 <input type="hidden" name="PZyInfo.zyInfoId" value="${obj.PZyInfo.zyInfoId }">
    <h4  class="page-header"><i class="ace-icon fa fa-file-text-o green"></i>&nbsp;<b>节点分配</b></h4>
    <div class="row">
		    <div class="col-md-6 form-group">
		         委办局名称：<input type="text" id="zyName" name="PZyInfo.zyName"/>
		    </div>
		    <div class="col-md-6 form-group">
		           节点名称：&nbsp;&nbsp;&nbsp;
        		<select  id="tagList" name="PZyInfo.tagLists" class="select2">
        			 <option value="">请选择节点...</option>
           			 <option value="">人员基本信息</option>
           			 <option value="">房屋基本信息</option>
           			 <option value="">法人基本信息</option>
           			 <option value="">人房分离信息</option>
           			 <option value="">法人处罚信息</option>
        		</select>
		    </div>
	</div>
</table>
</div>
</form>
<h4  class="page-header"></h4>
	<div style="text-align: center;margin-top: 50px;">
		<input type="button" id="button"  class="button btn btn-warning" value="提   交" onclick="window.location.href='${ctx}/jk/jdgl'" /> 
		<input type="button" id="button"  class="button btn btn-warning" value="返   回" onclick="window.location.href='${ctx}/jk/jdgl'" />
</div>
</div>
 <jsp:include page="/cj/foot.jsp"/>
</body>
</html>