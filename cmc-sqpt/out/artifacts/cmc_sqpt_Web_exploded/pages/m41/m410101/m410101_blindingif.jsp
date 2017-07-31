<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户接口授权</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>
<body style="width:100%;height:100%;">
<div style="width:100%;height:100%;">
	<div class="mini-fit">
		<table style="width:100%;height:100%;">
			<tr>
				<td>
					<div class="mini-toolbar" style="border-bottom:0;padding:5px;height:15px;"><b>未授权接口列表</b></div>
					<div class="mini-fit">
						<div id="outGrid" class="mini-datagrid" style="width:100%;height:100%;" multiSelect="true" pageSize="10"
						url="${pageContext.request.contextPath}/dispatch/m410101/m410101Action!listblindingif.action">
							<div property="columns">
								<div type="checkcolumn" headerAlign="center" class="mini-checkbox"></div>
								<div type="indexcolumn" headerAlign="center">标识</div>	
								<div field="ifdefcode" width="80" headerAlign="center" allowSort="false">接口代码</div> 
								<div field="ifdefname" width="120" headerAlign="center" allowSort="false">名称</div> 
							</div>
				 	 	</div>
			 	 	</div>
		 	 	</td>
	 	 		<td valign="middle">
	 	 			<table>
	 	 				<tr>
	 	 					<td><a class="mini-button" iconCls="icon-downgrade" plain="true" onclick="add()"></a></td>
	 	 				</tr>
		 					<tr>
							<td style="height:10px"></td>
						</tr>
						<tr>
	 	 					<td><a class="mini-button" iconCls="icon-upgrade" plain="true" onclick="remove()"></a></td>
	 	 				</tr>
	 	 			</table>
	 	 		</td>
				<td>
					<div class="mini-toolbar" style="border-bottom:0;padding:5px;height:15px;;"><b>已授权接口列表</b></div>
					<div class="mini-fit">	
						<div id="inGrid" class="mini-datagrid" style="width:100%;height:100%;" multiSelect="true" pageSize="10"
						url="${pageContext.request.contextPath}/dispatch/m410101/m410101Action!listblindingif.action">
							<div property="columns">
								<div type="checkcolumn" headerAlign="center" class="mini-checkbox"></div>
								<div type="indexcolumn" headerAlign="center">标识</div>	
								<div field="ifdefcode" width="80" headerAlign="center" allowSort="false">接口代码</div> 
								<div field="ifdefname" width="120" headerAlign="center" allowSort="false">名称</div> 
							</div>
				 	 	</div>
				 	 </div>
				</td>
			</tr>
		</table>
	</div>
</div>
<script type="text/javascript">
	mini.parse();
	var outGrid =mini.get("outGrid");
	var inGrid =mini.get("inGrid");
	
	var ifuserid;
	
	function SetData(data) {
		try{
			ifuserid = data.ifuserid;
			doQuery();
		}catch(e){
			MiniUtils.unmaskwin();	
			mini.alert("未知异常："+e.message);
		}
	}
	
	
	function doQuery(){
		outGrid.load({ifuserid:ifuserid,blind:"false"});
		inGrid.load({ifuserid:ifuserid,blind:"true"});
	}
	
	function add(){
		var rows =outGrid.getSelecteds();
		var ifdefineids =getIfdefineids(rows,"请先选择要授权的接口!");
		if(!ifdefineids){
			return;
		}
		MiniUtils.maskwin("提交中...");
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!addifuserauth.action",
			data: {ifuserid:ifuserid,ifdefineids:ifdefineids},
			success: function (text) {
				MiniUtils.unmaskwin();	
				doQuery();
			},
			error: function (text) {
				MiniUtils.unmaskwin();
				doQuery();
				mini.alert("提交失败，返回结果:" + text);
			}
		});
	}
	
	function remove(){
		var rows =inGrid.getSelecteds();
		var ifdefineids =getIfdefineids(rows,"请先选择要移除授权的接口!");
		if(!ifdefineids){
			return;
		}
		MiniUtils.maskwin("提交中...");
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!removeifuserauth.action",
			data: {ifuserid:ifuserid,ifdefineids:ifdefineids},
			success: function (text) {
				MiniUtils.unmaskwin();	
				doQuery();
			},
			error: function (text) {
				MiniUtils.unmaskwin();
				doQuery();
				mini.alert("提交失败，返回结果:" + text);
			}
		});
	}
	
	
	function getIfdefineids(rows,error){
		if(!rows ||rows.length <=0){ 
			mini.alert(error);	
			return false;
		}
		
		var ifdefineids = "";
		for(var i=rows.length-1;i>=0;i--){
			if(ifdefineids){
				ifdefineids +=","+rows[i].ifdefineid
			} else {
				ifdefineids = rows[i].ifdefineid;
			}
		}
		return ifdefineids;
	}
</script>
</body>
</html>