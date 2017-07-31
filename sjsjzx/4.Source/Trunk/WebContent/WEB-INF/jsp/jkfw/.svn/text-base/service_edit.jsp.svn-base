<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-接口服务管理</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>

<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
 <script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/tiles/Validform/css/style.css"/>
</head>
<script type="text/javascript">
//保存
function save() {
	var jk_name = $('#jkmc').val();
	var jk_cs = $('#cssm').val();
	var jk_fhz = $('#fhz').val();
	var jk_lx = $('#jklx').val();
	var jk_gnms = $('#gnms').val();
	var jkfw_url = $('#jkfwUrl').val();
	var jkfw_tgf = $('#jkfwtgf').val();
	var wjxz_url = $('#wjxzUrl').val();
	var flag = false;
	if(jk_name!="" && jk_cs!="" && jk_fhz!="" && jk_lx!="" && jk_gnms!="" && jkfw_url!="" && wjxz_url!="" && jkfw_tgf!=""){
		flag = true; 
	}else{
		if(jk_name == ""){
			alert("接口名称不能为空!");
			return;
		}
		if(jk_cs == ""){
			alert("参数说明不能为空!");
			return;
		} 
		if(jk_fhz == ""){
			alert("返回值说明不能为空!");
			return;
		}
		if(jk_lx == ""){
			alert("接口类型不能为空!");
			return;
		}
		if(jkfw_tgf == ""){
			alert("服务提供方不能为空!");
			return;
		}
		if(jkfw_url == ""){
			alert("接口服务URL不能为空!");
			return;
		}
		if(wjxz_url == ""){
			alert("文件下载URL不能为空!");
			return;
		}
		if(jk_gnms == ""){
			alert("功能描述不能为空!");
			return;
		} 
	}
	if(flag == true){
		if(confirm("确定要保存吗？")){
			$("#mainForm").submit();
		}
	}
} 
</script>
<style>
.dfinput{width:300px; height:25px; line-height:25px; 
border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; 
border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}
</style>
<body> 
<div>
	<form id="mainForm" name="mainForm" action="saveService" method="post">
	   <input type="hidden" name="service.serviceId" value="${obj.service.serviceId}"/>
	   <p style="text-align:center;font-size: 20px;padding-bottom: 15px;padding-top: 10px;font-weight: 80px">接口服务编辑</p>
		<table width="95%" border="0" class="table_multilist" align="center"> 
			<tr> 
				<td class="label_1" align="center" width="20%">接口名称：</td>
				<td class="label_2" align="left" width="30%"><input class="dfinput" id="jkmc" type="text" size="40" name="service.serviceName" value="${obj.service.serviceName }"/></td>
				<td class="label_1" align="center" width="20%">参数说明：</td>
				<td class="label_2" align="left" width="30%"><input class="dfinput" id="cssm" type="text" size="40" name="service.param" value="${obj.service.param }"/></td>
			</tr>
			<tr> 
				<td class="label_1" align="center">返回值：</td>
				<td class="label_2" align="left"><input class="dfinput" id="fhz" type="text" size="40" name="service.returnMemo" value="${obj.service.returnMemo}"/></td>
				<td class="label_1" align="center">接口类型：</td>
				<td class="label_2" align="left" width="30%"><wd:select className="selectInput" id="jklx" initValue="---请选择---" name="service.type" dicCode="1045" defaultValue="${obj.service.type }"/></td>
			</tr>
			<tr>
				<td class="label_1" align="center">提供单位：</td>
				<td class="label_2" align="left"><input class="dfinput" id="jkfwtgf" type="text" size="40" name="service.fromDepartment" value="${obj.service.fromDepartment}"/></td>
				<td class="label_1" align="center">服务URL：</td>
				<td class="label_2"  align="left"><input class="dfinput" id="jkfwUrl" type="text" size="40" name="service.serviceUrl" value="${obj.service.serviceUrl}"/></td>
			</tr>
			<tr>
				<td class="label_1" align="center">文件URL：</td>
				<td class="label_2" align="left" colspan="3">
					<select id="wjxzUrl" name="service.docUrl" width="10px">
						<option value="">---请选择---</option>
						<c:forEach items="${obj.fileList}" var="file" varStatus="row">
						 <c:choose> 
						 	<c:when test="${file.fid eq obj.service.docUrl}">
						 		<option value="${file.fid}" selected="selected">${file.filenamelocal }</option>
						 	</c:when>
						 	<c:otherwise>
						 		<option value="${file.fid}">${file.filenamelocal}</option>
						 	</c:otherwise>
						 </c:choose>
						</c:forEach> 
					</select>
				</td>
			</tr>
			<tr>
				<td class="label_1" align="center">功能描述：</td>
				<td class="label_2" align="left" colspan="3">
					<textarea rows="5" id="gnms" cols="90" name="service.functionMemo" >${obj.service.functionMemo}</textarea>
				</td>
			</tr>
		</table>
		<div style="text-align: center;margin-top: 20px;">
	      <input type="button" class="button" onclick="save()" value="保存" /> 
       </div>
	</form>
</div>
</body>

</html>