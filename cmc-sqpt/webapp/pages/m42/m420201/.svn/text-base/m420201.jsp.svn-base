<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务配置</title>
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>
<body style="width:100%;height:100%;">
<div class="mini-layout" style="width:100%;height:100%;">
	<div title="center" region="center">
		<div class="mini-fit">
			<div class="mini-toolbar" style="border-left: 0;border-right :0; padding: 0px;">
				<table style="width: 100%;">
					<tr>
						<td style="width: 100%;">
							<a class="mini-button" iconCls="icon-add" plain="true" onclick="doAdd()">增加</a>
							<a class="mini-button" iconCls="icon-edit" plain="true" onclick="doEdit()">编辑</a>
							<a class="mini-button" iconCls="icon-remove" plain="true" onclick="doRemove()">删除</a>
						</td>
					</tr>
				</table>
			</div>
			<div class="mini-fit">
				<div id="taskGrid" class="mini-datagrid" url="${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!queryTaskBean.action"
					sizeList="[10,20,50,100]" style="width: 100%;height: 100%;" borderstyle="border:0;" showReloadButton="true">
					<div property="columns">
						<div type="checkcolumn"></div>
						<div type="indexcolumn" width="40px"></div>
						<div field="beanId" name="beanId" width="0" headerAlign="center" allowSort="false"></div>
						<div field="beanDesc" width="24%" headerAlign="center" allowSort="true" align="left" renderer="onDescRenderer">任务名称</div>
						<div field="beanName" width="18%" headerAlign="center" allowSort="true" align="left">注册bean名称</div>
						<div field="ext1" width="10%" headerAlign="center" allowSort="false" align="left">任务参数</div>
						<div field="createrName" width="10%" headerAlign="center" allowSort="true" align="center">创建人</div>
						<div field="createTime" width="18%" headerAlign="center" allowSort="true" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">创建时间</div>
						<div field="notes" width="10%" headerAlign="center" allowSort="false" align="left">备注</div>
						<div field="ext2" width="10%" headerAlign="center" allowSort="false" align="left">执行主机</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	mini.parse(); //mini标签解析
	var grid = mini.get("taskGrid");
	grid.load();//加载数据
	
	//渲染任务名称
	function onDescRenderer(e){
		var record = e.record;
		var uid = record._uid;
		var html="";
		html = "<div class='titlelink'><a href='javascript:void(0);' onclick='doView("+uid+")'>"+record.beanDesc+"</a></div>";
		return html;
	}
	
	//增加  
	function doAdd() { 
		mini.open({
			url : "${pageContext.request.contextPath}/pages/m42/m420201/add.jsp",
			title : "新增任务",width :550,height : 300,allowResize:false,allowDrag:false,
			ondestroy: function (action) { 
				doQuery();
			}
		}); 
	}
	
	//查看
	function doView(row_uid){
		var row = grid.getRowByUID(row_uid);
		var beanId = row.beanId;
		mini.open({
			url : "${pageContext.request.contextPath}/pages/m42/m420201/view.jsp?beanId="+beanId,
			title : "查看任务",width :550,height : 300,allowResize:false,allowDrag:false,
			ondestroy: function (action) { 
				doQuery();
			}
		});
	}
	
	function doEdit(){
		var row = grid.getSelected();
		if(row){
			var beanId = row.beanId;
			mini.open({
				url : "${pageContext.request.contextPath}/pages/m42/m420201/edit.jsp?beanId="+beanId,
				title : "编辑任务",width :550,height : 300,allowResize:false,allowDrag:false,
				ondestroy: function (action) { 
					doQuery();
				}
			});
		} else {
			MiniUtils.alert("请选择需要编辑的记录");
		}
	}
	
	function doRemove(){
		var row = grid.getSelected();
		if(row){
			mini.confirm("确定删除选中记录？","确定",function (action){
				if(action=="ok"){
					var beanId = row.beanId;
					MiniUtils.maskwin("删除...");
					MiniUtils.request({
						url : "${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!removeTaskBean.action",
						data : {beanId : beanId},//提交参数
						success : function(result) {//成功回调
							MiniUtils.unmaskwin();
							MiniUtils.info("删除成功",function(action){
								doQuery();
							});
						},
						error:function(e){
							MiniUtils.unmaskwin();
							MiniUtils.alert(e,function(action){
								doQuery();
							});
						}
					});
				}
			})
		} else {
			MiniUtils.alert("请选择需要删除的记录");
		}
	}
	
	//重新加载
	function doQuery(){
		grid.reload();
	}
</script>
</body>
</html>
