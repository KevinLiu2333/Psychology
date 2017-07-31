<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>接口交易明细</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
<script type="text/javascript" src="<c:url value="/resources/js/base64/base64.min.js?v=1"/>"></script>
</head>

<body style="margin: 0;padding: 0;border: 0;width: 100%;height: 100%;">
<div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" arrowPosition="side" showNavMenu="true">
 	<div name="tab1" title="重发测试" style="width:100%;height:100%;">
	 	<div id="div_cxtj" style="width:100%;">
			<div style="width: 100%;">
				<div id="div_cxjg"  class="mini-toolbar" style="border-bottom: 0; padding: 0px;" align="left">
					<table style="width:100%;">
						<tr>
							<td style="white-space:nowrap;">
								服务/条线：
								<input id="ifdefineid" name="ifdefineid" class="mini-combobox" style="width:100px;" showNullItem="true" 
								url="${pageContext.request.contextPath}/dispatch/m420101/m420101Action!getIfDefine.action?iftype=01&allabove=true"/>
								发送：
								<input id="reqstartDate" name="reqstartDate" class="mini-datepicker" emptyText="开始时间" style="width:160px;" onenter="onKeyEnter" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true" />&nbsp;至
								<input id="reqendDate" name="reqendDate" class="mini-datepicker" emptyText="结束时间" style="width:160px;" onenter="onKeyEnter" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true" />
								交易标识：
								<input id="transno" name="transno" class="mini-textbox" style="width:230px;" onenter="onKeyEnter" /> 
								发起方：
								<input id="requsrname" name="requsrname" class="mini-textbox" style="width:80px;" onenter="onKeyEnter" /> 
								交易明细方法：
								<input id="transsubfunc" name="transsubfunc" class="mini-textbox" style="width:80px;" onenter="onKeyEnter" /> 							
								<a class="mini-button" iconCls="icon-search" onclick="doQuery()">查询</a>
							</td>
						</tr>					
					</table>
				</div>
			</div>
		</div>
		
		<div class="mini-fit" align="center">
			<div id="queryGrid" class="mini-datagrid" style="width:100%;height:100%;" allowResize="false"
				url="${pageContext.request.contextPath}/dispatch/m410101/m410101Action!querytransdetail.action"  idField="iftransdetailid" multiSelect="false" 
				frozenStartColumn="0" pageSize="20" >
				<div property="columns">
					<div type="indexcolumn"></div>
					<div field="transno" width="230px" headerAlign="left" allowSort="true">交易标识</div>
					<div field="transsubfunc" width="80px" headerAlign="left" allowSort="true">交易明细方法</div>
					<div field="reqcode" width="110px" headerAlign="left" allowSort="true">发起方代码</div>
					<div field="requsrname" width="110px" headerAlign="left" allowSort="true">发起方用户名</div>
					<div field="rspcode" width="110px" headerAlign="left" allowSort="true">接收方代码</div>
					<div field="rspusrname" width="110px" headerAlign="left" allowSort="true">接收方用户名</div>
					<div field="reqtime" width="130px" headerAlign="left" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">发送时间</div>
					<div field="rsptime" width="130px" headerAlign="left" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">接受时间</div>
					<div field="transtime" width="70px" headerAlign="left" allowSort="true">交易时常(s)</div>					
					<div field="transflag1" width="70px" headerAlign="left" allowSort="true">交易标志1</div>
					<div field="transflag2" width="70px" headerAlign="left" allowSort="true">交易标志2</div>					
					<div field="errmsg" width="380px" headerAlign="left">错误信息</div>
					<div width="120px" headerAlign="left" renderer="onActionRenderer">操作</div>
				</div>
			</div>
		</div>
	</div>
	<div title="接口内容发送测试" style="width:100%;height:100%;">
		<table style="width: 100%;">
			<tr>
				<td>
					服务/条线：
					<input id="service" name="ifdefineid" class="mini-combobox" style="width:100px;" showNullItem="true" valueField="code"
					url="${pageContext.request.contextPath}/dispatch/m420101/m420101Action!getIfDefine.action?iftype=01&allabove=true"/>
				</td>
			</tr>
			<tr>
				<td>
					<textarea id="req" class="mini-textarea" emptyText="请输入请求" style="width: 100%;height: 300px;"></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<textarea id="rsp" class="mini-textarea readonly" readonly="readonly" allowInput="false" style="width: 100%;height: 300px;"></textarea>
				</td>
			</tr>
			<tr>
				<td align="center">
					<a class="mini-button" iconCls="icon-node" onclick="doBulit();">格式化</a>
					&nbsp;
					<a class="mini-button" iconCls="icon-ok" onclick="doSend();">发送测试</a>
				</td>
			</tr>
		</table>
	</div>
</div>
<script type="text/javascript">
mini.parse();
var queryGrid= mini.get('queryGrid');
doQuery();
function doQuery(){
	try{
		MiniUtils.maskwin("查询中...");
		var form = new mini.Form("#div_cxtj");
		var form_json=mini.encode(form.getData());
		queryGrid.load(eval("("+form_json+")"));
		MiniUtils.unmaskwin();
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.responseText);
	}
} 

function onActionRenderer(e) {
   	return "<a href=\"javascript:queryxml();\"><font color='blue'>查看</font></a>&nbsp;&nbsp;<a href=\"javascript:resendxml();\"><font color='blue'>重发测试</font></a>";
}

function queryxml(){
	try{
		var row = queryGrid.getSelected();
		if(row){
	 		mini.open({
				url: "${pageContext.request.contextPath}/pages/m41/m410101/m410101_queryxml.jsp",
				title: "报文内容", width: 700, height: 400,
				onload: function () {
					var data;				
					data = { iftransdetailid: row.iftransdetailid}; 
					var iframe = this.getIFrameEl();
					iframe.contentWindow.SetData(data);
				}
			});
		} else {
			mini.alert("请选择要查看明细的监控");
		}
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}

function resendxml(){
	try{
		var row = queryGrid.getSelected();
		if(row && row.iftransdetailid){
			MiniUtils.maskwin("重发中...");
			MiniUtils.request({
				url : "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!resendxml.action",
				data:{iftransdetailid:row.iftransdetailid},
				success : function(result) {
					MiniUtils.unmaskwin();
					mini.alert("重发成功！");
					doQuery();
				},
				error:function(e){
					MiniUtils.unmaskwin();
					MiniUtils.alert(e);
				}
			});
		} else {
			mini.alert("请选择要查看重发的报文");
		}
	}catch(e){
		MiniUtils.unmaskwin();
		mini.alert("未知异常："+e.responseText);
	}
}

function doSend(){
	var service = mini.get("service").getValue();
	if(!service){
		mini.alert("请选择服务/条线。");
		return;
	}
	var req = mini.get("req").getValue();
	if(!req){
		mini.alert("请输入请求。");
		return;
	}
	var message = Base64.encode(req);
	$.ajax({  
		type: "POST",  
		url: "${pageContext.request.contextPath}/rest/"+service,  
		data: {message:message},
		dataType: "text",
		success: function(text) {
			try {
				mini.get("rsp").setValue(Base64.decode(text));
			} catch (e) {
				mini.alert(e.message);
			}
		},  
		error: function(req, status, ex) {
			alert(ex);
		},  
		timeout:60000  
	});
}

function doBulit(){
	try {
		var v = mini.get("req").getValue();
		if(v){
			mini.get("req").setValue(JSON.stringify(mini.decode(v),null,4));
		}
	} catch (e) {}
	
	try {
		var v = mini.get("rsp").getValue();
		if(v){
			mini.get("rsp").setValue(JSON.stringify(mini.decode(v),null,4));
		}
	} catch (e) {}
}
</script>
</body>
</html>
