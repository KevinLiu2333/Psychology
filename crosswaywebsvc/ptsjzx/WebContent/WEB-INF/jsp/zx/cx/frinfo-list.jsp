<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>全文检索列表</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<style type="text/css">
.qwjs{
	font-size: 25px;
	color: blue;
}
.biaoti{
	font-size: 18px;
	color: blue;
}
.content{
	font-size: 15px;
	size: 10px;
}
.table{
	margin-left: 120px;
}
.xiaobiaoti{
	font-size: 15px;
	color: blue;
}

</style>
</head>
<body>
<form action="${ctx}/cx/fulltextRetrievalList" method="post" style="height: 600px;background:white;">
	<div>
		<table>
			<tr>
				<td></td>
				<td></td>
				<td class="qwjs">全文检索：<input type="text" style="width: 400px;height: 35px;" value=""></td>
				<td><input type="image" style="width: 60px;height: 60px;" src="${ctx}/skins/images/full-jiansuo.png"></td>
			</tr>
		</table>
	</div> 
	
	<div style="background-color: #F8F8F8;">
		<table>
			<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${ctx}/cx/toFrInfoList"><u>法人</u></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><a href="${ctx}/cx/toBuildingList">房屋</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><a href="#">人口</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><a href="#">资料</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><a href="#">地图</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><a href="${ctx}/cx/toNewsList">普陀新闻</a></td></tr>
		</table>
	</div><br>
	
	<div>
		<table class="table">
		<!-- 检索内容 -->
		<tr>
			<td><a href="#" class="biaoti">2015年<span style="color: red">普陀区</span>企业<span style="color: red">法人</span>登记情况</a></td>
			<td colspan="3"></td>
		</tr>
		<tr>
			<td class="content"><span style="color: red">普陀区</span>统计局组织参观漕河泾开发区普陀园区 [2015-11-06]近期,<span style="color: red">普陀区</span>统计局由局党组书记...</td>
			<td colspan="3"></td>
		</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
		
		<tr>
			<td><a href="#" class="biaoti"><span style="color: red">普陀法人</span>的最新相关信息</a></td>
			<td colspan="3"></td>
		</tr>
		<tr>
			<td class="content">易登<span style="color: red">普陀</span>分类信息网为您提供<span style="color: red">普陀</span>分类信息,您可以免费发布和查询求职招聘...</td>
			<td colspan="3"></td>
		</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
		
		<tr>
			<td><a href="#" class="biaoti"><span style="color: red">普陀非企业法人</span>登记最新新闻</a></td>
			<td colspan="3"></td>
		</tr>
		<tr>
			<td class="content">在分析总结成功选育“松早香1号”基础上,结合生产实际,经过多年探索,尤其是近三年努力,成功...</td>
			<td colspan="3"></td>
		</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
		
		<tr>
			<td><a href="#" class="biaoti"><span style="color: red">法人</span>变更登记情况</a></td>
			<td colspan="3"></td>
		</tr>
		<tr>
			<td class="content"><span style="color: red">普陀区</span>教育网是普陀区星宝宝集合了<span style="color: red">普陀区</span>所有的早教,幼儿园,小学机构信息和入园...</td>
			<td colspan="3"></td>
		</tr>
		
	</table>
	</div>
	<br><br>
	
	<div>
		<table class="table">
			<tr>
				<td class="content" style="font-size: 20px">相关搜索</td>
				<td colspan="3"></td>
			</tr>
			<tr>
				<td><a href="#" class="xiaobiaoti">普陀大学城</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><a href="#" class="xiaobiaoti">普陀教育</a>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
				<td><a href="#" class="xiaobiaoti">普陀旅游</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><a href="#" class="xiaobiaoti">普陀小吃</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td><a href="#" class="xiaobiaoti">普陀人口</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><a href="#" class="xiaobiaoti">普陀经济</a>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
				<td><a href="#" class="xiaobiaoti">普陀区事物受理中心</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><a href="#" class="xiaobiaoti">普陀房产</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
		</table>
	</div>
	<br><br>
	
	<div>
		<!-- （3）分页 -->
		<jsp:include page="/common/pager.jsp" flush="true"/>
	</div>
	<br>
</form> 
</body>
</html>