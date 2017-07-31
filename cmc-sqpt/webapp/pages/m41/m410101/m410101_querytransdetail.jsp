<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>接口交易明细</title>
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
						<td style="white-space:nowrap;">
							发送：
							<input id="reqstartDate" name="reqstartDate" class="mini-datepicker" emptyText="开始时间" style="width:160px;" onenter="onKeyEnter" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true" />&nbsp;至
							<input id="reqendDate" name="reqendDate" class="mini-datepicker" emptyText="结束时间" style="width:160px;" onenter="onKeyEnter" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true" />
							交易标识：
							<input id="transno" name="transno" class="mini-textbox" style="width:80px;" onenter="onKeyEnter" /> 
							发起方：
							<input id="requsrname" name="requsrname" class="mini-textbox" style="width:80px;" onenter="onKeyEnter" /> 
							交易明细方法：
							<input id="transsubfunc" name="transsubfunc" class="mini-textbox" style="width:80px;" onenter="onKeyEnter" /> 							
							<input id=ifdefineid name="ifdefineid" class="mini-hidden"/> 
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
					<div field="requsrname" width="80px" headerAlign="left" allowSort="true">发起方用户名</div>
					<div field="transsubfunc" width="80px" headerAlign="left" allowSort="true">交易明细方法</div>					
					<div field="rspusrname" width="80px" headerAlign="left" allowSort="true">接收方用户名</div>
					<div field="reqtime" width="130px" headerAlign="left" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">发送时间</div>
					<div field="rsptime" width="130px" headerAlign="left" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">接受时间</div>
					<div width="35px" headerAlign="left" renderer="onActionRenderer">报文</div>
					<div field="transtime" width="70px" headerAlign="left" allowSort="true">交易时常(s)</div>					
					<div field="transflag1" width="70px" headerAlign="left" allowSort="true">交易标志1</div>
					<div field="transflag2" width="70px" headerAlign="left" allowSort="true">交易标志2</div>					
					<div field="errmsg" width="80px" headerAlign="left" renderer="onErrRenderer">错误信息</div>
					<div field="transno" width="230px" headerAlign="left" allowSort="true">交易标识</div>
					<div field="reqcode" width="110px" headerAlign="left" allowSort="true">发起方代码</div>
					<div field="rspcode" width="90px" headerAlign="left" allowSort="true">接收方代码</div>
				</div>
			</div>
	</div>
</div> 
<script type="text/javascript">

mini.parse();
var queryGrid= mini.get('queryGrid');
function SetData(data) {
	try{
		MiniUtils.maskwin("导入中...");
		data = mini.clone(data);
 		mini.get("ifdefineid").setValue(data.ifdefineid);
    	queryGrid.load({ifdefineid:data.ifdefineid});
		MiniUtils.unmaskwin();	
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}

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
   	return "<a href=\"javascript:queryxml();\"><font color='blue'>查看</font></a>";
}

function onErrRenderer(e) {
	var record = e.record;
	var transflag2 = record.transflag2;
	var transflag1 = record.transflag1;
	if(transflag2!="000000"||transflag1!="00"){
   		return "<a href=\"javascript:queryErr();\"><font color='blue'>错误信息</font></a>";
	}
}

function queryErr(){
	try{
		var row = queryGrid.getSelected();
		if(row){
 		mini.open({
                url: "${pageContext.request.contextPath}/pages/m41/m410101/m410101_queryerr.jsp",
                title: "错误信息", width: 700, height: 400,
                onload: function () {
                    var data;                
                	data = { iftransdetailid: row.iftransdetailid}; 
                    var iframe = this.getIFrameEl();
                    iframe.contentWindow.SetData(data);
                }
            });
        }else{
			mini.alert("请选择要查看明细的接口");
		}
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
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
        }else{
			mini.alert("请选择要查看明细的接口");
		}
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}

</script>
</body>
</html>
