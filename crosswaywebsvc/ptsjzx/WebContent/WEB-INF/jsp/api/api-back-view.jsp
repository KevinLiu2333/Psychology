<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<c:set scope="request"  var="pageForm" value="MainForm" />
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
<title>API申请</title>
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
//查询数据  
function query(type){
	if(type == '1'){
		$('#pageNo').val('1');
	}
	$('#MainForm').submit();
}
function backStep(){
	$('#MainForm').attr('action', '${ctx}/api/toApiItemSelect');
	$('#MainForm').submit();
}
//查看申请批次详细信息
function showDetails(applyBatch){
	href = "${ctx}/api/showDetails?applyBatch="+applyBatch;
	window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=910px;center=yes");
}
function winclose(){
	window.close();
}
</script>
</head>
<body>
<form id="MainForm" action="${ctx}/api/toApiItemSelect" method="post">
<p style="text-align:left;font-size:20px;padding-bottom: 15px;padding-top: 10px;margin-left: 60px;"><b>API资源申请</b></p>
	<div>
		<p style="text-align:center;font-size:20px;font-weight: 15px;">被退回的申请</p></br>
		<p style="text-align:left;font-size:15px;font-weight: 15px;margin-left: 45px;"><font color="red">被退回的申请需要重新申请(点击申请主题查看详情)</font></p>
	</div></br>
	
	<div align="center">
		<table width="96%" class="table_list">
			<tr>
				<th width="10%">序号</th>
				<th width="20%">申请主题</th>
				<th width="15%">申请时间</th>
				<th width="10%">状态</th>
			</tr>
			<c:forEach items="${obj.apiApplyList}" var="api" varStatus="row">
				<tr>
					<td align="center">${row.index+1 }</td>
					<td align="center"><a href="#" onclick="showDetails('${api.applyBatch}')">${api.applyTopic}</a></td>
					<td align="center">
						<fmt:formatDate value="${api.applyDate }" pattern="yyyy年MM月dd日"/>
					</td>
					<td align="center"><wd:dicvalue dicId="1046" dicCode="${api.status}"/></td>
				</tr>
			</c:forEach>
		</table>
		<table width="96%" class="tables">
			<tr>
				<td>
					<jsp:include page="/common/pager.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
	
	<div style="text-align: center;margin-top: 20px;padding-bottom: 10px">
		<input type="button" id="button"  class="button" value="关闭" onclick="winclose();" /> 
	</div>
</form>
</body>
</html>