<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>监控对象配置</title>
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
							<a class="mini-button" iconCls="icon-addnew" plain="true" onclick="toAdd();">新增监控对象</a>
							<a class="mini-button" iconCls="icon-remove" plain="true" onclick="doDelete();">删除监控对象</a>
						</td>
						<td style="white-space:nowrap;">
							<input id="queryText" name="queryText" class="mini-textbox" emptyText="请输入监控对象代码或监控对象名称" style="width:200px;" onenter="onKeyEnter" /> 
							<input id="iftype" name="iftype" class="mini-combobox" data="DIC_IFTYPE" emptyText="请选择监控对象类型" style="width:140px;"/> 
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
	        	url="${pageContext.request.contextPath}/dispatch/m420101/m420101Action!query.action"  idField="ifmondefid" multiSelect="false" 
	        	frozenStartColumn="0" pageSize="20" >
				<div property="columns">
					<div type="checkcolumn" ></div>
					<div type="indexcolumn"></div>
					<div field="ifdefcode" width="80px" headerAlign="left" renderer="ifdefcodeRenderer" allowSort="true">监控对象代码</div>
					<div field="ifdefname" width="120px" headerAlign="left" allowSort="true">监控对象名称</div>
					<div type="comboboxcolumn" field="iftype" width="60px" headerAlign="left" allowSort="true">监控对象类型<input property="editor" class="mini-combobox" data="DIC_IFTYPE"/></div>
					<div field="lsmontime" width="120px" headerAlign="left" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">最近监控时间</div>
					<div type="comboboxcolumn" field="lsmonstatus" width="120px" headerAlign="left" allowSort="true">最近监控状态<input property="editor" class="mini-combobox" data="DIC_STATUS"/></div>
					<div field="lsmondesc" width="120px" headerAlign="left" allowSort="true">最近监控状态备注</div>
					<div width="60px" headerAlign="left"  renderer="onActionRenderer">操作</div>
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
		mini.alert("未知异常："+e.responseText);
	}
}

function toAdd(){
	try{
		mini.open({
                url: "${pageContext.request.contextPath}/pages/m42/m420101/m420101_add.jsp",
                title: "新增监控对象", width: 800, height: 360,
                ondestroy: function (action) {
                    queryGrid.reload();
                }
            });
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
							url: "${pageContext.request.contextPath}/dispatch/m420101/m420101Action!delete.action",
							data: { ifmondefid: row.ifmondefid},
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
		mini.alert("未知异常："+e.responseText);
	}
}

var timer_interval=null;
timer_interval = setInterval(doQuery,60000);

function onActionRenderer(e) {
   	var record=e.record;
   	return "<a href='javascript:querymondetail();'><font color='blue'>查看监控明细</font></a>";
}

function ifdefcodeRenderer(e) {
   	var record=e.record;
   	return "<a href='javascript:doview();'><font color='blue'>"+record.ifdefcode+"</font></a>";
}


function doview(){
	try{
		var row = queryGrid.getSelected();
		if(row){
			mini.open({
				allowDrag: false,
	            allowResize: false,
                url: "${pageContext.request.contextPath}/pages/m42/m420101/m420101_view.jsp",
                title: "查看接口", width: 800, height: 360,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { ifdefineid: row.ifdefineid,
                    				iftype:row.iftype};
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
		mini.alert("未知异常："+e.responseText);
	}
}


function querymondetail(){
	try{		
		var row = queryGrid.getSelected();
		if(row){
		mini.open({
                url: "${pageContext.request.contextPath}/pages/m42/m420101/m420101_querymondetail.jsp",
                title: "监控明细", width: 1000, height: 575,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { ifmondefid: row.ifmondefid};
                    iframe.contentWindow.SetData(data);
                }
            });
        }else{
			mini.alert("请选择要查看明细的监控");
		}
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}
</script>
</body>
</html>
