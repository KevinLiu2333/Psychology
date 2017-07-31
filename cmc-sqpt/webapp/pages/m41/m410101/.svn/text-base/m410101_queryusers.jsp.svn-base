<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>接口用户列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>
<body style="margin: 0;padding: 0;border: 0;width: 100%;height: 100%;">
<div style="width: 100%; height: 100%; ">
	<div id="div_cxtj" style="width:100%;">
		<div style="width: 100%;">
			<div id="div_cxjg"  class="mini-toolbar" style="border-bottom: 0; padding: 0px;" align="left">
				<table style="width:100%;">
					<tr>
						<td style="width:100%;">
							<a class="mini-button" iconCls="icon-addnew" plain="true" onclick="toAdd();">新增接口用户</a>
							<a class="mini-button" iconCls="icon-edit" plain="true" onclick="toEdit();">修改接口用户</a>
							<a class="mini-button" iconCls="icon-remove" plain="true" onclick="doDelete();">删除接口用户</a>
							<a class="mini-button" iconCls="icon-node" plain="true" onclick="doBlinding();">用户接口授权</a>
						</td>
						<td style="white-space:nowrap;">
							<input id="queryText" name="queryText" class="mini-textbox" emptyText="请输入用户代码或用户姓名" style="width:150px;" onenter="onKeyEnter" />
							<a class="mini-button" iconCls="icon-search" onclick="doQuery()">查询</a>
						</td>
					</tr>					
				</table>
			</div>
		</div>
	</div>
	
	<div class="mini-fit" align="center">
		<div class="mini-fit">
			<div id="queryGrid" class="mini-datagrid" style="width:100%;height:100%;" allowResize="false"
				url="${pageContext.request.contextPath}/dispatch/m410101/m410101Action!queryusers.action" multiSelect="false">
				<div property="columns">
					<div type="checkcolumn" width="20px" ></div>
					<div type="indexcolumn" width="25px" ></div>
					<div field="ifusercode" width="85px" headerAlign="left" allowSort="true">用户代码</div>
					<div field="ifusername" width="85px" headerAlign="left" allowSort="true">用户名称</div>
					<div field="edtime" width="130px" headerAlign="left" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">更新时间</div>
				</div>
			</div>
		</div>
	</div>
</div> 
<script type="text/javascript">
mini.parse();
var queryGrid= mini.get('queryGrid');
doQuery();
function doQuery(){
	try{
		var form = new mini.Form("#div_cxtj");
		queryGrid.load(form.getData());
	}catch(e){
		mini.alert("未知异常："+e.message);
	}
}

function toAdd(){
	try{
		mini.open({
				allowDrag: false,
				allowResize: false,
				url: "${pageContext.request.contextPath}/pages/m41/m410101/m410101_adduser.jsp",
				title: "新增接口用户", width: 800, height: 360,
				ondestroy: function (action) {
					queryGrid.reload();
				}
			});
	}catch(e){
		mini.alert("未知异常："+e.message);
	}
}

function toEdit(){
	try{
		var row = queryGrid.getSelected();
		if(row){
			mini.open({
				allowDrag: false,
				allowResize: false,
				url: "${pageContext.request.contextPath}/pages/m41/m410101/m410101_edituser.jsp",
				title: "修改接口用户", width: 800, height: 360,
				onload: function () {
					var iframe = this.getIFrameEl();
					var data = { ifuserid: row.ifuserid};
					iframe.contentWindow.SetData(data);
				},
				ondestroy: function (action) {
					queryGrid.reload();
				}
			});
		}else{
			mini.alert("请选择要修改的接口用户");
		}
	}catch(e){
		mini.alert("未知异常："+e.message);
	}
}

function doDelete(){
	try{
		var row = queryGrid.getSelected();
		if(row){
			mini.confirm("确定删除记录？", "确定？",
				function (action) {
					if (action == "ok") {
						MiniUtils.request({
							url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!deleteuser.action",
							data: { ifuserid: row.ifuserid},
							success: function (text) {
								mini.alert("删除成功");
								queryGrid.reload();
							},
							error: function (text) {
								mini.alert("提交失败，返回结果:" + text);
							}
						});
					} else {
						return;
					}
				}
			);
		}else{
			mini.alert("请选择要删除的接口用户");
		}
	}catch(e){
		mini.alert("未知异常："+e.message);
	}
}

function doBlinding(){
	try{
		var row = queryGrid.getSelected();
		if(row){
			mini.open({
				allowDrag: false,
				allowResize: false,
				url: "${pageContext.request.contextPath}/pages/m41/m410101/m410101_blindingif.jsp",
				title: "用户接口授权", width: 800, height: 360,
				onload: function () {
					var iframe = this.getIFrameEl();
					var data = { ifuserid: row.ifuserid};
					iframe.contentWindow.SetData(data);
				},
				ondestroy: function (action) {
					queryGrid.reload();
				}
			});
		}else{
			mini.alert("请选择要授权的接口用户");
		}
	}catch(e){
		mini.alert("未知异常："+e.message);
	}
}
</script>
</body>
</html>