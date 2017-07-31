<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>分发接口管理</title>
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
							<a class="mini-button" iconCls="icon-addnew" plain="true" onclick="toAdd();">新增接口</a>
							<a class="mini-button" iconCls="icon-edit" plain="true" onclick="toEdit();">修改接口</a>
							<a class="mini-button" iconCls="icon-remove" plain="true" onclick="doDelete();">删除接口</a>
							<a class="mini-button" iconCls="icon-node" plain="true" onclick="doTest();">测试接口</a>
							<a class="mini-button" iconCls="icon-user" plain="true" onclick="doShowUser();">接口用户维护</a>
						</td>
						<td style="white-space:nowrap;">
							<input id="queryText" name="queryText" class="mini-textbox" emptyText="请输入接口代码或接口名称" style="width:150px;" onenter="onKeyEnter" /> 
							<input id="iftype" name="iftype" class="mini-hidden" value="01"/>
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
				url="${pageContext.request.contextPath}/dispatch/m410101/m410101Action!query.action"  idField="ifdefineid" multiSelect="false" 
				frozenStartColumn="0" pageSize="20" >
				<div property="columns">
					<div type="checkcolumn" width="20px" ></div>
					<div type="indexcolumn" width="25px" ></div>
					<div field="ifdefcode" width="85px" headerAlign="left" allowSort="true">接口代码</div>
					<div field="ifdefname" width="85px" headerAlign="left" allowSort="true">接口名称</div>
					<div type="comboboxcolumn" name="ifsubtype" field="ifsubtype" width="55px" headerAlign="left">接口类型<input property="editor" class="mini-combobox" data="DIC_IFSUBTYPE"/></div>
					<div field="ifuri" width="280px" headerAlign="left" >访问地址</div>
					<div field="edtime" width="130px" headerAlign="left" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">更新时间</div>
 					<div field="transsum" width="70px" headerAlign="left" allowSort="true">总交易笔数</div>
					<div field="transsuc" width="55px" headerAlign="left" allowSort="true">成功笔数</div>
					<div field="transfail" width="55px" headerAlign="left" allowSort="true">失败笔数</div>
					<div width="90px" headerAlign="left" renderer="onActionRenderer">操作</div>
					<div field="status" width="110px" headerAlign="left" renderer="toDIC_STATUS">状态</div>
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
				url: "${pageContext.request.contextPath}/pages/m41/m410101/m410101_add.jsp",
				title: "新增接口", width: 800, height: 360,
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
				url: "${pageContext.request.contextPath}/pages/m41/m410101/m410101_edit.jsp",
				title: "修改接口", width: 800, height: 360,
				onload: function () {
					var iframe = this.getIFrameEl();
					var data = { ifdefineid: row.ifdefineid};
					iframe.contentWindow.SetData(data);
				},
				ondestroy: function (action) {
					queryGrid.reload();
				}
			});
		}else{
			mini.alert("请选择要修改的接口");
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
							url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!delete.action",
							data: { ifdefineid: row.ifdefineid},
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
			mini.alert("请选择要删除的接口");
		}
	}catch(e){
		mini.alert("未知异常："+e.message);
	}
}

function doTest(){
	try{
		
		var row = queryGrid.getSelected();
		if(row){
			MiniUtils.maskwin("测试中...");
			MiniUtils.request({
				url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!test.action",
				data: { ifdefineid: row.ifdefineid},
				success: function (text) {
					MiniUtils.unmaskwin();
					queryGrid.beginEditRow(row);
					row.status=text.code;
					queryGrid.commitEditRow(row);
					if(text.detailmsg){
						mini.alert("测试结果："+text.detailmsg);
					}
				},
				error: function (text) {
					MiniUtils.unmaskwin();
					mini.alert("提交失败，返回结果:" + text);
				}
			});			
		}else{
			mini.alert("请选择要测试的接口");
		}
	}catch(e){
		MiniUtils.unmaskwin();
		mini.alert("未知异常："+e.message);
	}
}

function doShowUser(){
	try{
		mini.open({
				allowDrag: false,
				allowResize: false,
				url: "${pageContext.request.contextPath}/pages/m41/m410101/m410101_queryusers.jsp",
				title: "接口用户列表", width: 800, height: 360,
				ondestroy: function (action) {
					queryGrid.reload();
				}
			});
	}catch(e){
		mini.alert("未知异常："+e.message);
	}
}

function onActionRenderer(e) {
   	return "<a href='javascript:doTest();'><font color='blue'>测试</font></a>&nbsp;&nbsp;<a href='javascript:querytransdetail();'><font color='blue'>交易明细</font></a>";
}

function querytransdetail(){
	try{
		var row = queryGrid.getSelected();
		if(row){
			mini.open({
				url: "${pageContext.request.contextPath}/pages/m41/m410101/m410101_querytransdetail.jsp",
				title: "接口交易明细", width: 1000, height: 590,
				onload: function () {
					var iframe = this.getIFrameEl();
					var data = { ifdefineid: row.ifdefineid};
					iframe.contentWindow.SetData(data);
				}
			});
		}else{
			mini.alert("请选择要查看明细的监控");
		}
	}catch(e){
		mini.alert("未知异常："+e.message);
	}
}
</script>
</body>
</html>