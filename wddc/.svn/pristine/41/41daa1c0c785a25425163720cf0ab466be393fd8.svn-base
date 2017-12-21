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
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>

</head> 
<input type="hidden" id="opFlag" name="opFlag" value="${obj.opFlag}">
<script type="text/javascript">
	if($('#opFlag').val() == "success"){
		alert("操作成功!");
		window.close();
	}

	function fanhui(){
		window.close();
	}
	
	function show_div(){ 
		var obj_div = document.getElementById("starlist"); 
			obj_div.style.display = (obj_div.style.display=='none')?'block':'none'; 
	} 
	
	$(document).ready(function(){
 	   $("#starlist").hide();
    });
	
	//审核意见
	function changeReason(){
		if($('#opnnType').val()=='4'){
			$('#noPassReasonTr').show();
		} else {
			$('#noPassReasonTr').hide();
		}
	}
	function submitAudit(){
		if(confirm("确定要保存吗？")){
				$("#mainForm").submit();
		}
	}
</script>

</head>
<body>
<input type="hidden" id="js_ctx" value="${ctx }" />
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class='container'>
    <h3 id="disable-responsive2" class="page-header"><i class="ace-icon fa fa-leaf green"></i>&nbsp;&nbsp;编制审批</h3>
    <form id="mainForm" name="mainForm" action="${ctx }/zy/saveAudit" method="post" >
   		 <input type="hidden" name="PZyInfo.zyInfoId" value="${obj.PZyInfo.zyInfoId }">
   		 <input type="hidden" name="PZyInfo.zyUnit" value="${obj.PZyInfo.zyUnit }">
   		 <input type="hidden" name="PZyInfo.unitId" value="${obj.PZyInfo.unitId }">
     <h4  class="page-header"><i class="ace-icon fa fa-fire green"></i>&nbsp;<b>目录基础信息</b></h4>
    <div class="row">
    		<div class="col-md-6 form-group">
		         数据提供单位：<span>${obj.PZyInfo.zyUnit}</span>
		    </div>
		    <div class="col-md-6 form-group">
		         数据来源：<span id="dataOrgin"></span>
		    </div>
		    <div class="col-md-12 form-group">
		         资源目录名称：<span>${obj.PZyInfo.zyName}</span>
		    </div>
		    <div class="col-md-12 form-group">
		         更新说明 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：<span>${obj.PZyInfo.updateDesc}</span>
		    </div>
		    <div class="col-md-6 form-group">
		        检索关键字 &nbsp;&nbsp;&nbsp;：<span>${obj.PZyInfo.tagLists}</span>
		    </div>
		    <div class="col-md-12 form-group">
		        资源目录摘要：<br/><span style="width:80%">${obj.PZyInfo.zyAbstract}</span>
		    </div>
	</div>
	<h4  class="page-header"><i class="ace-icon fa fa-expand green"></i>&nbsp;<b>数据源</b></h4>
	<div class="row">
		    <div class="col-md-6 form-group">
	数据源名称：<span>${obj.PZyInfo.zyTable}</span>
		    </div>
	</div>
<!-- 资源项 -->
	<h4  class="page-header"><i class="ace-icon fa fa-crosshairs green"></i>&nbsp;<b>目录资源项</b></h4>
		<div class="row">
		<div class="col-md-12 form-group">
		<b>主动公开资源项：</b><br/>
		<c:forEach items="${obj.zyItemList}" var="zyItem" varStatus="row">
			<c:if test="${zyItem.openType eq '主动公开'}">
				<span>${zyItem.cnName};&nbsp;&nbsp;</span>
			</c:if>
		</c:forEach>
		</div>
		<div class="col-md-12 form-group">
		<b>依申请公开资源项：</b><br/>
		<c:forEach items="${obj.zyItemList}" var="zyItem" varStatus="row">
			<c:if test="${zyItem.openType eq '依申请公开'}">
				<span>${zyItem.cnName}</span>
			</c:if>
		</c:forEach>
		</div>
	<h4  class="page-header">&nbsp;&nbsp;<i class="ace-icon fa fa-pencil green"></i>&nbsp;<b>审核结果</b></h4>
	<div class="col-md-12 form-group">
	<tr>
		<td>经办结果</td>
		<td style="text-align: center;">
				<select name="PZyInfo.auditResult" value="${obj.PZyInfo.auditResult }">
				<option value="">--请选择--</option>
					<option value="审核通过">审核通过</option> 
					<option value="审核不通过">审核不通过</option> 
				</select>
		</td>
	</tr>
	</div>
	<div class="col-md-12 form-group">
	<tr>
			<td class="label_1">经办意见</td>
			<td class="label_2" colspan="3">
				<textarea name="PZyInfo.auditReason" class="dftextarea" style="width:98%;height:60px;">${obj.PZyInfo.auditReason}</textarea>
			</td>
	</tr>
	</div>
</form>
<h4  class="page-header"></h4>
	<div style="text-align: center;margin-top: 50px;">
		<input type="button" id="button"  class="button btn btn-warning" value="提   交" onclick="submitAudit();" /> 
		<input type="button" id="button"  class="button btn btn-warning" value="返   回" onclick="window.location.href='${ctx}/zy/queryZyList'" />
	</div>
</div>
 <jsp:include page="/cj/foot.jsp"/>
</body>
<script type="text/javascript">
$('#dataOrgin').jsondicvalue({
	dicid:2001,
	value:"${obj.PZyInfo.dataOrgin }"
});
</script>
</html>