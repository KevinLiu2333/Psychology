<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>权限管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>

<body style="margin: 0;padding: 0;border: 0;width: 100%;height: 100%;">
<div style="width: 100%; height: 100%; ">
	<div id="div_user" style="height: auto; width: 100%;">
			<div class="mini-toolbar" style="border-bottom:0;padding:0px;">
				<table style="width:100%;">
					<tr>
						<td style="width:100%;">
							<a class="mini-button" iconCls="icon-addnew" plain="true" onclick="toAdd()">新增用户</a>
							<a class="mini-button" iconCls="icon-edit" plain="true" onclick="toEdit()">编辑用户</a> 
							<a class="mini-button" iconCls="icon-remove" plain="true" onclick="doDelete()">删除用户</a> 
						</td>
						<td style="white-space:nowrap;">
							<input id="queryText" name="queryText" class="mini-textbox" emptyText="请输入用户名称" style="width:150px;" onenter="onKeyEnter" /> 
							<a class="mini-button" iconCls="icon-search" onclick="doQuery()">查询</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	
	<div class="mini-fit" align="center">
		<div class="mini-fit">
			<div id="queryGrid" class="mini-datagrid" style="width:100%;height:100%;" allowResize="false"
	        			url="${pageContext.request.contextPath}/m38/m380101/m380101Action!query.action"  idField="userid" multiSelect="false" 
	        			frozenStartColumn="0" pageSize="20" >
				<div property="columns">
					<!--<div type="indexcolumn"></div>        -->
					<div type="checkcolumn" ></div>
					<div type="indexcolumn" headerAlign="center" align="center">序号</div>
					<div field="loginname" width="80px" headerAlign="center" align="center">登录名</div>
					<div field="displayname" width="120px" headerAlign="center" align="center">显示名</div>
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
		MiniUtils.maskwin("导入中...");
		var form = new mini.Form("#div_user");
    	var form_json=mini.encode(form.getData());
    	queryGrid.load( eval("("+form_json+")"));
    	MiniUtils.unmaskwin();
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}

function toAdd(){
	try{
		mini.open({
                url: "${pageContext.request.contextPath}/pages/m38/m380101/add.jsp",
                allowDrag: false,
	            allowResize: false,
                title: "新增用户", width: 800, height: 360,
                onload: function () {
	                var iframe = this.getIFrameEl();
					var data = {
						action : "new"
					};
					iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    queryGrid.reload();
                }
            });
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}

function toEdit(){
	try{
		var row = queryGrid.getSelected();
		if(row){
			mini.open({
                url: "${pageContext.request.contextPath}/pages/m38/m380101/add.jsp",
                allowDrag: false,
	            allowResize: false,
                title: "修改用户", width: 800, height: 360,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = {
                    	userid: row.userid,
                    	action: "edit"
                    };
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    queryGrid.reload();
                }
            });
		}else{
			mini.alert("请选择要修改的节点");
		}
	}catch(e){
		mini.alert("未知异常："+e.responseText);
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
							url: "${pageContext.request.contextPath}/m38/m380101/m380101Action!delete.action",
							data: { userid: row.userid},
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
			mini.alert("请选择要删除的节点");
		}
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}


function onKeyEnter(e) {
	doQuery();
}
</script>
</body>
</html>
