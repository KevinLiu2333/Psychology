<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增监控</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>

<body>
<div id="addForm" style="padding: 10px">
	<table class="form-table" border="0" cellpadding="2" cellspacing="0" style="width:auto;table-layout:fixed;" >
		<tr>
			<td align="right" style="width:120px;">监控对象大类<font color="red">*</font>：</td>
			<td style="width:225px;">
				<input id="iftype" name="iftype" class="mini-combobox" required="true" style="width:65%;" data="DIC_IFTYPE" onvaluechanged="onDeptChanged"/>
			</td>
			<td align="right" style="width:100px;">监控对象名称<font color="red">*</font>：</td>
			<td style="width:225px;">
				<input id="ifdefineid" name="ifdefineid" class="mini-combobox" required="true" style="width:65%;"/>
			</td>
		</tr>
	</table>
</div>
<div>
		<table border="0" align="center" cellpadding="0" cellspacing="0">
			<tr align="center">
	        	<td align="center">
					<a class="mini-button" iconCls="icon-save"
					onclick="doSave()">保存</a> &nbsp;&nbsp;&nbsp; <a class="mini-button"
					iconCls="icon-close" onclick="closeWin()">关闭</a>
	        	</td>
	    	</tr>
	 	</table>
	</div>
<script type="text/javascript">

mini.parse();
form = new mini.Form("#addForm");


function doSave(){

     form.validate();
		if (form.isValid() == false) {
			mini.alert('请检查必填项！');
			return false;
		}		
		
	try{
		MiniUtils.maskwin("提交中...");
		var data = form.getData();//获取表单多个控件的数据
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m420101/m420101Action!add.action",
			data: data,
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
		mini.alert("未知异常："+e.responseText);
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
		mini.alert("未知异常："+e.responseText);
	}
}


function onDeptChanged(e) {
	try{
		MiniUtils.maskwin("提交中...");
	    var iftype = mini.get("iftype").getValue();
	    var ifdefineid = mini.get("ifdefineid");
	    ifdefineid.setValue("");
	    var url = "${pageContext.request.contextPath}/dispatch/m420101/m420101Action!getIfDefine.action?iftype=" + iftype;
	    ifdefineid.setUrl(url);  
		MiniUtils.unmaskwin();	
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.responseText);
	}         
}

</script>
</body>
</html>