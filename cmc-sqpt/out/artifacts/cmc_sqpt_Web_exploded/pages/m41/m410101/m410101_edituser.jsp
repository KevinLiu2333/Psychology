<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改接口</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>

<body>
<div id="editForm" style="padding: 10px">
	<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
		<tr>
			<td align="right" style="width:120px;">用户代码<font color="red">*</font>：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifusercode" name="ifusercode" class="mini-textbox" maxlength="25"  required="true" style="width:100%;"/>
			</td>
			<input id="ifuserid" name="ifuserid" class="mini-hidden"/>
		</tr>
		<tr>
			<td align="right" style="width:120px;">用户名<font color="red">*</font>：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifusername" name="ifusername" class="mini-textbox" maxlength="50" required="true" style="width:100%;"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width:120px;">token<font color="red">*</font>：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifusertoken" name="ifusertoken" class="mini-textbox" maxlength="25" required="true" style="width:100%;"/>
			</td>
		</tr>
		<tr>
			<td align="right" style="width:120px;">aeskey<font color="red">*</font>：</td>
			<td style="width:550px;" colspan="3">
				<input id="ifuseraeskey" name="ifuseraeskey" class="mini-textbox" maxlength="43" required="true" style="width:100%;"/>
			</td>
		</tr>
	</table>
</div>
<div>
	<table border="0" align="center" cellpadding="0" cellspacing="0">
		<tr align="center">
        	<td align="center">      	
				<a class="mini-button" iconCls="icon-save" onclick="doSave()">保存</a> &nbsp;&nbsp;&nbsp; 
				<a class="mini-button" iconCls="icon-reload" onclick="doReset">重置</a> &nbsp;&nbsp;&nbsp;
				<a class="mini-button" iconCls="icon-close" onclick="closeWin()">关闭</a>	        	
				</td>
    	</tr>
 	</table>
</div>
<script type="text/javascript">
mini.parse();
form = new mini.Form("#editForm");
function SetData(data) {
	try{
		//跨页面传递的数据对象，克隆后才可以安全使用
		MiniUtils.maskwin("导入中...");
		data = mini.clone(data);
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!loaduser.action",
			data: { ifuserid: data.ifuserid },
			success: function (text) {
				form.setData(text);
				MiniUtils.unmaskwin();	
			},
			error: function (text) {
				MiniUtils.unmaskwin();	
				mini.alert("提交失败，返回结果:" + text);
			}
		});
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.message);
	}
}


function doSave(){
	 form.validate();
		if (form.isValid() == false) {
			mini.alert("请检查必填项！");
			return false;
		}
	try{
		MiniUtils.maskwin("提交中...");
		var data = form.getData();//获取表单多个控件的数据
		data = mini.encode(data);//序列化成JSON
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!edituser.action",
			data: {jsonData: data},
			success: function (text) {
				MiniUtils.unmaskwin();	
				MiniUtils.info("保存成功!",closeWin);
			},
			error: function (text) {
				MiniUtils.unmaskwin();	
				mini.alert("提交失败，返回结果:" + text);
			}
		});
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.message);
	}
}

function closeWin(){
	try{
		if (window.CloseOwnerWindow){
			return window.CloseOwnerWindow();
		}else{
			window.close();
		}
	}catch(e){
		mini.alert("未知异常："+e.message);
	}
}

function doReset() {
	try{
		//跨页面传递的数据对象，克隆后才可以安全使用
		MiniUtils.maskwin("导入中...");
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!loaduser.action",
			data: { ifuserid: mini.get("ifuserid").getValue()},
			success: function (text) {
				form.setData(text);
				MiniUtils.unmaskwin();	
			},
			error: function (text) {
				MiniUtils.unmaskwin();	
				mini.alert(text);
			}
		});
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.message);
	}
}
</script>
</body>
</html>