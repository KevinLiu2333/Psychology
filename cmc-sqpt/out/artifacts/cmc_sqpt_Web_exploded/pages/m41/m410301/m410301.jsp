<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开放端口管理</title>
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	border: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
}

.header {
	background: url(../resources/images/header.gif) repeat-x 0 -1px;
}

.noborder .mini-textbox-border {
	border: 0px !important
}
</style>

</head>
	<body>
		<div id="div_cxtj" style="height: auto; width: 100%;">
			<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
				<table style="width:100%;">
					<tr>
						<td style="width:100%;">
							<a class="mini-button" iconCls="icon-addnew" plain="true" onclick="add()">新增开放端口</a>
							<a class="mini-button" iconCls="icon-edit" plain="true" onclick="edit()">编辑开放端口</a> 
							<a class="mini-button" iconCls="icon-remove" plain="true" onclick="remove()">删除开放端口</a> 
							<a class="mini-button" iconCls="icon-node" plain="true" onclick="test()">测试接口</a> 
						</td>
						<td style="white-space:nowrap;">
							<input id="iftype" name="iftype" class="mini-hidden" value="03"/>
							<input id="queryText" name="queryText" class="mini-textbox" emptyText="请输入开放端口代码" style="width:150px;" onenter="onKeyEnter" /> 
							<a class="mini-button" iconCls="icon-search" onclick="doQuery()">查询</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="mini-fit" align="center">
			<div class="mini-fit">
				<div id="queryGrid" class="mini-datagrid"
					style="height: 100%; width: 100%;" allowResize="false"
					url="${pageContext.request.contextPath}/dispatch/m410101/m410101Action!query.action"  idField="ifdefineid"
					multiSelect="false" frozenStartColumn="0" pageSize="20">
					<div property="columns">
						<div type="checkcolumn"></div>
						<div type="indexcolumn">序号</div>
						<div field="ifdefcode" width="120" headerAlign="center" align="center" allowSort="true">开放端口代码</div>
						<div field="ifdefname" width="120" headerAlign="center" align="center" allowSort="true">开放端口名称</div>
						<div field="ifuri" width="120" headerAlign="center" align="center" allowSort="true">访问地址</div>
						<div field="edtime" width="100" headerAlign="center" align="center" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">更新时间</div>
						<div style="display:none" field="ifsubtype" width="60px" headerAlign="center" align="center" type="comboboxcolumn" name="ifsubtype" >
							接口类型
							<input property="editor" class="mini-combobox" data="[{ id: '03', text: '应用' },{ id: '04', text: '开放端口' }]"/>
						</div>
						<div width="50px" headerAlign="center" renderer="onActionRenderer" align="center">操作</div>
						<div field="status" width="60px" headerAlign="center" align="center" renderer="toDIC_STATUS">状态</div>
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
					MiniUtils.maskwin("导入中...");
					var form = new mini.Form("#div_cxtj");
			    	var form_json=mini.encode(form.getData());
			    	queryGrid.load( eval("("+form_json+")"));
			    	MiniUtils.unmaskwin();
				}catch(e){
					MiniUtils.unmaskwin();
					mini.alert("未知异常："+e.responseText);
				}
			}
		
			function add() {
				try{
					mini.open({
						url : __CONTEXT_PATH__ + "/pages/m41/m410301/OpenPort.jsp",
						allowDrag: false,
						allowResize: false,
						title : "新增开放端口",
						width : 600,
						height : 220,
						onload : function() {
							var iframe = this.getIFrameEl();
							var data = {
								action : "new"
							};
							iframe.contentWindow.SetData(data);
						},
						ondestroy : function(action) {
							queryGrid.reload();
						}
					});
				}catch(e){
					mini.alert("未知异常："+e.responseText);
				}
			}
			
			function edit() {
				try{
					var row = queryGrid.getSelected();
					if (row) {
						mini.open({
							url :  __CONTEXT_PATH__ + "/pages/m41/m410301/OpenPort.jsp",
							allowDrag: false,
							allowResize: false,
							title : "编辑开放端口",
							width : 600,
							height : 220,
							onload : function() {
								var iframe = this.getIFrameEl();
								var data = {
									action : "edit",
									ifdefineid: row.ifdefineid
								};
								iframe.contentWindow.SetData(data);
			
							},
							ondestroy : function(action) {
								queryGrid.reload();
							}
						});
			
					} else {
						mini.alert("请选择要修改的开放端口");
					}
				}catch(e){
					mini.alert("未知异常："+e.responseText);
				}
			}
			
			function test() {
				try{
					var row = queryGrid.getSelected();
					if(row){	
						MiniUtils.maskwin("测试中...");		
						MiniUtils.request({
							url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!test.action",
							data: { ifdefineid: row.ifdefineid},
							success: function (text) {
								MiniUtils.unmaskwin();
								queryGrid.beginEditRow(row);//启动行编辑
								row.status=text.code;
								queryGrid.commitEditRow(row);//提交指定行编辑
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
					mini.alert("未知异常："+e.responseText);
				}
		
			}
			function remove() {
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
						mini.alert("请选择要删除的开放端口");
					}
				}catch(e){
					mini.alert("未知异常："+e.responseText);
				}
			}
			
			function onActionRenderer(e) {
			   	var record=e.record;
			   	return "<a href='javascript:test();'><font color='blue'>测试</font></a>";
			}
			
			function onKeyEnter(e) {
				doQuery();
			}
		</script>
	</body>
</html>
