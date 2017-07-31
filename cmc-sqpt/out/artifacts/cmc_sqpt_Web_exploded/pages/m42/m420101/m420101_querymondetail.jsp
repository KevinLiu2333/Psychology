<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>监控明细</title>
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
							监控时间：
							<input id="startDate" name="startDate" class="mini-datepicker" emptyText="请输入开始时间" style="width:160px;" onenter="onKeyEnter" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true" />&nbsp;至
							<input id="endDate" name="endDate" class="mini-datepicker" emptyText="请输入结束时间" style="width:160px;" onenter="onKeyEnter" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true" />
							监控状态：
							<input id="lsmonstatus" name="lsmonstatus" class="mini-combobox" style="width:160px;" data="DIC_STATUS"/>
							<input id="ifmondefid" name="ifmondefid" class="mini-hidden"/> 
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
	        	url="${pageContext.request.contextPath}/dispatch/m420101/m420101Action!queryMonDetail.action"  idField="ifmondetailid" multiSelect="false" 
	        	frozenStartColumn="0" pageSize="20" >
				<div property="columns">
					<div type="indexcolumn"></div>
					<div field="ifdefcode" width="80px" headerAlign="left" allowSort="true">监控对象代码</div>
					<div field="ifdefname" width="120px" headerAlign="left" allowSort="true">监控对象名称</div>
					<div type="comboboxcolumn" field="iftype" width="70px" headerAlign="left" allowSort="true">监控对象类型<input property="editor" class="mini-combobox" data="DIC_IFTYPE"/></div>
					<div field="lsmontime" width="120px" headerAlign="left" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="true">监控时间</div>
					<div type="comboboxcolumn" field="lsmonstatus" width="100px" headerAlign="left" allowSort="true">监控状态<input property="editor" class="mini-combobox" data="DIC_STATUS"/></div>
					<div field="lsmondesc" width="150px" headerAlign="left" >监控状态备注</div>
				</div>
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
 		mini.get("ifmondefid").setValue(data.ifmondefid);
    	queryGrid.load({ifmondefid:data.ifmondefid});
		MiniUtils.unmaskwin();	
	}catch(e){
		mini.alert("未知异常："+e.responseText);
	}
}

function doQuery(){
	try{
		if((mini.get("startDate").getValue()&&mini.get("endDate").getValue())||(!mini.get("startDate").getValue()&&!mini.get("endDate").getValue())){
			MiniUtils.maskwin("查询中...");
			var form = new mini.Form("#div_cxtj");
	    	var form_json=mini.encode(form.getData());
	    	queryGrid.load(eval("("+form_json+")"));
			MiniUtils.unmaskwin();
		}else{
			mini.alert("请填写起始结束日期");
		}
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.responseText);
	}
} 


</script>
</body>
</html>
