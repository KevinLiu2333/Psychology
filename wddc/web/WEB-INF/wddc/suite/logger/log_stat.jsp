<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${sys_title}</title>
<%@ include file="/cj/meta.jsp"%>
<!-- Loading Bootstrap -->
<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!--self-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/skins/css/wddc.css" />
<!--font-awesome-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css" />
<!-- Loading jquery -->
<script type="text/javascript"
	src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
<!-- Loading jquery -->
<script type="text/javascript"
	src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- top of the page -->
	<jsp:include page="/cj/header.jsp" />
	<form action="${ctx }/suite/unit/saveUnit" name="fwsqForm"
		id="fwsqForm">
		<input type="hidden" name="userUnit.unitId"
			value="${obj.userUnit.unitId}" />
		<div class='container'>
			<h3 id="disable-responsive2" class="page-header">日志统计</h3>

			<table id="table" class="table table-bordered">
				<thead>
					<tr>
						<th width="100px">大类代码</th>
						<th width="100px">大类名称</th>
						<th>小类代码和名称</th>
					</tr>
				</thead>
				<tbody>

					<tr>
						<td>xt</td>
						<td>系统消息</td>
						<td>xt001:登录(<span id="jh001">0</span>) || xt002:登出(<span
							id="xt002">0</span>) || xt003:公告(<span id="xt003">0</span>) <br /> xt101:登录错误(<span
							id="xt101">0</span>) || xt102:服务调用错误(<span id="xt102">0</span>) <br />
							xt201:开关操作(<span id="xt201">0</span>)
						</td>
					</tr>

					<tr>
						<td>yj</td>
						<td>预警动态</td>
						<td>yj101：服务预警信息(<span id="yj101">0</span>) || yj102:服务预警设置(<span
							id="yj102">0</span>)
						</td>
					</tr>

					<tr>
						<td>jh</td>
						<td>交换动态</td>
						<td>jh001：webservice接口调用(<span id="jh001">0</span>)||
							jh002:http接口调用(<span id="jh002">0</span>) || jh003:RESTful接口调用(<span
							id="jh003">0</span>)
						</td>
					</tr>

					<tr>
						<td>fw</td>
						<td>服务动态</td>
						<td>fw001:服务申请(<span id="fw001">0</span>) || fw002:服务浏览(<span
							id="fw002">0</span>) || fw003:服务调用(<span id="fw003">0</span>) ||
							fw004:服务审核(<span id="fw004">0</span>) || fw005:服务发布(<span
							id="fw005">0</span>)<br /> fw101:实名验证第一步(<span id="fw101">0</span>)
							|| fw102:实名验证第一步(<span id="fw102">0</span>)
						</td>
					</tr>

					<tr>
						<td>cz</td>
						<td>操作日志</td>
						<td>cz001:目录编制(<span id="cz001">0</span>) || cz002:目录浏览(<span
							id="cz002">0</span>) || cz003:目录发布(<span id="cz003">0</span>) ||
							cz004:目录审核(<span id="cz004">0</span>)<br /> cz101:人口查询(<span
							id="cz101">0</span>) || cz102:法人查询(<span id="cz102">0</span>) ||
							cz103:房屋查询(<span id="cz103">0</span>) || cz104:法人资质查询(<span
							id="cz104">0</span>) || cz105:法人处罚查询(<span id="cz105">0</span>)
							|| cz106:专题库查询(<span id="cz106">0</span>) <br /> cz201:人口图表信息查看(<span
							id="cz201">0</span>) || cz202:志愿者图表信息查看(<span id="cz202">0</span>)
							|| cz203:残疾人图表信息查看(<span id="cz203">0</span>) || cz204:老龄人图表信息查看(<span
							id="cz204">0</span>)<br /> cz301:人口报表信息查看(<span id="cz301">0</span>)
							|| cz302:侨民报表信息查看(<span id="cz302">0</span>) || cz303:残疾人报表信息查看(<span
							id="cz303">0</span>) || cz304:老龄人报表信息查看(<span id="cz304">0</span>)
						</td>
					</tr>

					<tr>
						<td>ds</td>
						<td>定时任务</td>
						<td>ds001:定时器开关(<span id="ds001">0</span>) || ds002:定时器执行(<span
							id="ds002">0</span>)<br /> ds101:交换作业任务执行(<span id="ds101">0</span>)
						</td>
					</tr>


				</tbody>
			</table>
		</div>


	</form>
</body>
<script type="text/javascript">
	$(function() {
		$("#table span").css("color", "blue");
		$.post("${ctx}/fw/ptservices", {
			unitKey : "${unitKey}",
			fwCode : "fw00013",
			id : "F00058"
		}, function(data) {
			var data1 = eval('(' + data + ')');
			console.log(data1.DATA.sjl);
			var list = data1.DATA.sjl;
			for (var i = 0; i < list.length; i++) {
				$("#" + list[i].name).html(list[i].value)
			}

		});
	})
	function fwsq() {
		$("#fwsqForm").submit();
	}
</script>
</html>
