<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
 <link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css"/>
<title>松江区政务数据中心-服务申请</title>
<jsp:include page="/common/meta.jsp"/>
<script type="text/javascript">
function save(){
	window.close();
}
</script>
</head>
<body style="overflow-x:hidden">
	<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>功能服务申请</b></p>
	<table width="80%" class="table_multilist" align="center">
		<tr>
			<td width="20%" style="text-align:right; height:30px">功能服务名称：</td>
			<td width="30%">&nbsp;
				<select class="dfinput">
					<option>--请选择--</option>
					<option>人口信息统计</option>
					<option>法人信息统计</option>
					<option>房屋信息统计</option>
				</select>
			</td>
			<td width="20%" style="text-align:right; height:30px">申请期限：</td>
			<td width="30%">
				&nbsp;<select class="dfinput">
					<option>--请选择--</option>
					<option>一天</option>
					<option>两天</option>
					<option>三天</option>
					<option>四天</option>
					<option>五天</option>
					<option>六天</option>
					<option>七天</option>
				</select>
			</td>
		</tr>
		<tr>
			<td style="text-align:right;">申请原因：</td>
			<td colspan="3" align="left">
				<p style="padding-bottom: 15px;padding-top: 10px;padding-left:5px;padding-right:15px"><textarea style="height:120px;width:98%" class="dfinput" name="gzzEmployeeLevel.memo" ></textarea></p>
			</td>
		</tr>
  	</table>
	<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
		<input type="button" class="button"  onclick="save()" value="保存" />
	</div>
</body>
</html>