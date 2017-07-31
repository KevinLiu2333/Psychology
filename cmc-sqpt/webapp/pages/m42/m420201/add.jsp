<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务新增</title>
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>
<body style="width:100%;height:100%;">
<div class="mini-layout" style="width:100%;height:100%;border:0;">
	<div title="center" region="center" style="border: 0;">
		<div id="frm_submit">
			<table style="table-layout: fixed;margin: 10px;">
				<tr>
					<td width="16%" align="right"><font color=red>*</font>任务名称：</td>
					<td width="34%">
						<input id="beanDesc" name="beanDesc" vtype="maxLength:150" class="mini-textbox" required="true" style="width: 100%"/>
					</td>
					<td width="20%" align="right"><font color=red>*</font>注册Bean名称：</td>
					<td width="30%">
						<input id="beanName" name="beanName" vtype="maxLength:150" class="mini-textbox" required="true" style="width: 100%"/>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top">参数配置：</td>
					<td colspan="3">
						<input id="ext1" name="ext1" class="mini-textarea" vtype="maxLength:250" emptyText="在此输入参数，格式为json格式，最大250个字符" style="width: 100%"/>
					</td>
				</tr>
				<tr>
					<td align="right" valign="top">说 明：</td>
					<td colspan="3" style="">
						<input id="notes" name="notes" class="mini-textarea" vtype="maxLength:150" emptyText="在此输入备注，最大150个字符" style="width: 100%"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div region="south" height="50" showHeader="false" showSplit="false" splitSize="0" minHeight="10"
		style="border:0px;text-align:center;" bodyStyle="overflow:hidden;text-align:center;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<a class="mini-button" iconCls="icon-save" onclick="doSubmit">保存</a>
					&nbsp;&nbsp;&nbsp;
					<a class="mini-button" iconCls="icon-reload" onclick="doReset">重置</a>
				</td>
			</tr>
		</table>
	</div>
</div>
<script type="text/javascript">
	mini.parse(); //mini标签解析
	var frm_submit = new mini.Form("frm_submit");
	
	function doSubmit() {
		var data = frm_submit.getData();//获取表单数据   
		frm_submit.validate();//数据校验
		if (!frm_submit.isValid()){
			return; 
		}
		MiniUtils.maskwin("保存中...");
		MiniUtils.request({
			url : "${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!saveTaskBean.action",
			data: data,
			success : function(result) {
				MiniUtils.unmaskwin();
				MiniUtils.info("保存成功",function(action){
					doClose();
				});
			},
			error:function(e){
				MiniUtils.unmaskwin();
				MiniUtils.alert(e);
			}
        }); 
	}
	
	function doClose(){
		if (window.CloseOwnerWindow) {
			return window.CloseOwnerWindow();
		} else {
			window.close();
		}
	}

	function doReset() {
		frm_submit.reset();
	}
</script>
</body>
</html>