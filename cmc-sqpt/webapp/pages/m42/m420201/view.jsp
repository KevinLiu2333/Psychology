<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务预览</title>
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>
<body style="width:100%;height:100%;">
<div class="mini-layout" style="width:100%;height:100%;border:0;">
	<div title="center" region="center" style="border: 0;">
		<div id="frm_load">
			<table style="table-layout: fixed;margin: 10px;">
				<tr>
					<td width="16%" align="right">任务名称：</td>
					<td width="34%">
						<input id="beanDesc" name="beanDesc" class="mini-textbox asLabel" readonly="readonly" style="width: 100%" allowInput="false"/>
					</td>
					<td width="20%" align="right">注册Bean名称：</td>
					<td width="30%">
						<input id="beanName" name="beanName" class="mini-textbox asLabel" readonly="readonly" style="width: 100%" allowInput="false"/>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top">参数配置：</td>
					<td colspan="3">
						<input id="ext1" name="ext1" class="mini-textarea asLabel" readonly="readonly" style="width: 100%" allowInput="false"/>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top">说 明：</td>
					<td colspan="3" style="">
						<input id="notes" name="notes" class="mini-textarea asLabel" readonly="readonly" style="width: 100%" allowInput="false"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	mini.parse(); //mini标签解析
	var frm_load = new mini.Form("frm_load");
	$(function() {
		MiniUtils.maskwin("加载中...");
		MiniUtils.request({
			url : "${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!loadTaskBean.action",
			data : mini.getParams(),//提交参数
			success : function(result) {//成功回调
				MiniUtils.unmaskwin();
				frm_load.setData(result);
			},
			error:function(e){
				MiniUtils.unmaskwin();
				MiniUtils.alert(e);
			}
		});
	})
</script>
</body>
</html>