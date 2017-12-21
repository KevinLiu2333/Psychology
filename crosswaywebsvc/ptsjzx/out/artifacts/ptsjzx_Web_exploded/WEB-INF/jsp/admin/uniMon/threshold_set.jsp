<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>阀值设置</title>
<link rel="stylesheet" type="text/css"
	href="${ctx}/gray/admin/uniMon/threshold_set/style/style.css" />
<script src="${ctx }/gray/common/js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<!-- 阀值设置Table -->
	<table class="age_tab" width="96%" border="0" height="60"
		align="center" cellpadding="0" cellspacing="0" class="query_search"
		style="margin-top: 20px;">
		<!-- 台头 -->
		<tr>
			<td>序号</td>
			<td>阀值详情</td>
			<td>阀值设置</td>
			<td>级别设置</td>
			<td>操作人</td>
			<td>修改时间</td>
			<td>操作</td>
		</tr>
		<!-- 判断传入的数值是否为空 -->
		<c:if test="${obj.data != null}">
			<!-- 循环遍历 -->
			<c:forEach var='data' items='${obj.data}' varStatus='id'>
				<!-- 将数据制作成标签 -->
				<tr id="${data.Id}">
					<!-- 序号 -->
					<td>${id.count}</td>
					<!-- 阀值详情 -->
					<td>${data.thresholdName}</td>
					<!-- 阀值 -->
					<td><input type='text' value='${data.threshold}' />&nbsp;&nbsp;${data.threshold_unit}</td>
					<!-- 报警等级 -->
					<td>
						<!-- 判断等级显示图片 --> 
						<!-- 等级1 --> 
						<c:if test="${data.alarmLevel == 1}">
							<img class="levelImg"
								src="${ctx}/gray/admin/uniMon/threshold_set/images/blue.png" />
						</c:if> 
						<!-- 等级2 --> 
						<c:if test="${data.alarmLevel == 2}">
							<img class="levelImg"
								src="${ctx}/gray/admin/uniMon/threshold_set/images/yellow.png" />
						</c:if> 
						<!-- 等级3 --> 
						<c:if test="${data.alarmLevel == 3}">
							<img class="levelImg"
								src="${ctx}/gray/admin/uniMon/threshold_set/images/red.png" />
						</c:if> 
						<!-- 等级下拉框 --> 
						<select onchange="change(this)">
							<!-- 循环生成下拉框 -->
							<c:forEach var="num" begin="1" end="3">
								<!-- 判断默认显示 -->
								<c:if test="${num == data.alarmLevel}">
									<option selected="true">${num}</option>
								</c:if>
								<c:if test="${num != data.alarmLevel}">
									<option>${num}</option>
								</c:if>
							</c:forEach>
					</select>
					</td>
					<!-- 操作人员 -->
					<td>${data.userName}</td>
					<!-- 更新时间 -->
					<td>${data.updateTime}</td>
					<!-- 保存按钮 -->
					<td><input type='button' value='保存' onclick="save(this)" /></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<script type="text/javascript">
		//获取主机之后的目录
		var pathName = window.document.location.pathname;
		var rootName = pathName.substring(0,
				pathName.substr(1).indexOf('/') + 1);
		//当下拉框被修改时
		function change(s) {
			//获取下拉框被修改的值
			var text = $(s).find("option:selected").text();
			//获取下拉框的图片
			var $img = $(s).parent().find("img");
			//判断值
			if (text == "1") {//如果是1
				//修改图片
				$img.attr("src", rootName
						+ "/gray/admin/uniMon/threshold_set/images/blue.png");
			}
			if (text == "2") {//如果是2
				//修改图片
				$img.attr("src", rootName
						+ "/gray/admin/uniMon/threshold_set/images/yellow.png");
			}
			if (text == "3") {//如果是3
				//修改图片
				$img.attr("src", rootName
						+ "/gray/admin/uniMon/threshold_set/images/red.png");
			}
		}

		//当保存按钮被单击时
		function save(b) {
			//获取父类tr
			var $tr = $(b).parent().parent();
			$.ajax({
				url : (rootName + "/admin/uniMon/alarmListTurn"),
				data : {
					"id" : $tr.attr("id"),
					"threshold" : $tr.children().eq(2).find("input").val(),
					"alarmLevel" : $tr.children().eq(3).find("select").find(
							"option:selected").text()
				},
				success : function() {
					alert("保存成功.");
				},
				error : function() {
					alert("服务器异常,请稍后再试！");
				}
			});
		}
	</script>
</body>
</html>