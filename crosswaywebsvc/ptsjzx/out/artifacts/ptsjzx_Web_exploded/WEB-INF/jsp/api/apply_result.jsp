<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/meta.jsp"/>
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx }/tiles/scripts/md5.js"></script>
<!-- 表单验证组件 --> 
 <script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/tiles/Validform/css/style.css"/>
<title>申请情况</title>
<style type="text/css">
.button{
	position: relative; 
    overflow: visible; 
    display: inline-block; 
    padding: 0.5em 1em; 
    border: 1px solid #6495ED; 
    margin: 0;
    width:120px;
    text-decoration: none; 
    text-shadow: 1px 1px 0 #fff; 
    font-family:"Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica, sans-serif;
	font-size: 16px;
    color: #333; 
    font-weight:bold;
    white-space: nowrap; 
    cursor: pointer; 
    outline: none; 
    background-color: #fff;
    -webkit-background-clip: padding;
    -moz-background-clip: padding;
    -o-background-clip: padding-box; 
    /*background-clip: padding-box;*/ /* commented out due to Opera 11.10 bug */
    -webkit-border-radius: 0.2em; 
    -moz-border-radius: 0.2em; 
    border-radius: 0.2em; 
    /* IE hacks */
    zoom: 1; 
    *display: inline; 
}
.dfinput{width:345px; height:25px; line-height:25px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}
</style>
<script type="text/javascript">
function winclose(){
	window.close();
}
</script>
</head>
<body>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>申请情况</b></p>
<table width="80%" class="table_multilist" align="center">
	<tr>
		<td class="label_1" >服务名称:</td>
		<td class="label_2"  colspan="3">&nbsp;${obj.serviceUser.serviceName }</td>
	</tr>
	<tr>
			<td class="label_1" width="20%">申请人：</td>
			<td class="label_2" width="30%">&nbsp;${obj.serviceUser.userDispalyname}</td>
			<td class="label_1" width="20%">所属部门：</td>
			<td class="label_2" width="30%">&nbsp;<wd:dicvalue dicId="1003" dicCode="${obj.serviceUser.deptId}"/></td>
	</tr>
	<tr>
		<td class="label_1">申请时限：</td>
		<td class="label_2">
			&nbsp;<wd:dicvalue dicId="1047" dicCode="${obj.serviceUser.applyDay }"/>
		</td>
		<td class="label_1">申请时间：</td>
		<td class="label_2">
			&nbsp;<fmt:formatDate value="${obj.serviceUser.applyDate }" pattern="yyyy年MM月dd日"/>
		</td>
		
	</tr>
	<tr >
		<td class="label_1">申请理由：</td>
		<td class="label_2" colspan="3">&nbsp;${obj.serviceUser.applayReason }</td>
	</tr>
	<tr >
		<td class="label_1">申请状态：</td>
		<td class="label_2" colspan="3" >
			<c:if test="${obj.serviceUser.state=='1' }">&nbsp;待审核</c:if>
			<c:if test="${obj.serviceUser.state=='2' }">&nbsp;已通过</c:if>
			<c:if test="${obj.serviceUser.state=='3' }">&nbsp;已退回</c:if>
		</td>
	</tr>
	<c:if test="${obj.serviceUser.state=='2'||obj.serviceUser.state=='3' }">
		<tr>
			<td class="label_1">审核时间：</td>
			<td class="label_2" colspan="3" >
				&nbsp;<fmt:formatDate value="${ obj.serviceUser.checkedDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td class="label_1">审核意见：</td>
			<td class="label_2" colspan="3" >&nbsp;${ obj.serviceUser.auditOpinion}</td>
		</tr>
	</c:if>
	<c:if test="${obj.serviceUser.state=='2' }">
		<tr>
			<td class="label_1">服务使用开始时间：</td>
			<td class="label_2" colspan="3">
				&nbsp;<fmt:formatDate value="${ obj.serviceUser.beginDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td class="label_1">服务使用结束时间：</td>
			<td class="label_2" colspan="3">
				&nbsp;<fmt:formatDate value="${ obj.serviceUser.endDate}" pattern="yyyy年MM月dd日 HH:mm:ss"/>
			</td>
		</tr>
		<c:if test="${sessionScope.user.userId==obj.serviceUser.userId }">
			<tr>
				<td class="label_1">服务许可序号key：</td>
				<td class="label_2" colspan="3">
					&nbsp;${ obj.serviceUser.key}
				</td>
			</tr>
		</c:if>
	</c:if>
</table>
<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="关闭" onclick="winclose();" /> 
	</div>
</body>
</html>