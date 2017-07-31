<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务调度配置</title>
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>
<body style="width:100%;height:99.5%;">
<div class="mini-layout" style="width:100%;height:100%;border:0;">
	<div title="center" region="center" style="border: 0;">
		<div class="mini-fit" style="width: 99%;margin-left:5px;">
			<div class="mini-fit">
				<div class="mini-fit">
					<div class="mini-panel" title="调度列表" style="width:100%;height: 100%;" showCollapseButton="true" bodyStyle="padding:0;" expanded="true">
						<div id="triggerGrid" class="mini-datagrid" url="${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!findAllQuartzTrigger.action"
							sizeList="[10,20,50,100]" style="width: 100%;height: 100%;" borderstyle="border:0;" showReloadButton="true">
							<div property="columns">
								<div type="indexcolumn" width="35px"></div>
								<div field="triggerName" name="triggerName" width="0" headerAlign="center" allowSort="false"></div>
								<div field="beanId" name="beanId" width="0" headerAlign="center" allowSort="false"></div>
								<div field="beanDesc" width="20%" headerAlign="center" allowSort="false" align="center" renderer="onDescRenderer">任务名称</div>
								<div field="nextFireTime" width="20%" headerAlign="center" allowSort="true" renderer="formateDate" align="center" dateFormat="yyyy-MM-dd HH:mm:ss">下次执行时间</div>
								<div field="triggerType" headerAlign="center" align="center" width="5%" allowSort="false" renderer="onTriggerType">类型</div>
								<div field="triggerState" width="5%" headerAlign="center" allowSort="false" renderer="onStatus" align="center">状态</div>
								<div field="cronExp" width="12%" headerAlign="center" allowSort="false" align="center">调度表达式</div>
								<div field="ext1" width="5%" headerAlign="center" allowSort="false" align="center">说明</div>
								<div field="createrName" headerAlign="center" align="center" width="8%" allowSort="false">创建人</div>
								<div field="createTime" headerAlign="center" align="center" width="10%" allowSort="false" dateFormat="yyyy-MM-dd">创建日期</div>
								<div field="triggerEvent" headerAlign="center" align="center" width="10%" allowSort="false" renderer="onActionRenderer">操作</div>
							</div>
						</div>
					</div>
				</div>
				<div id="outlookbar_trigger" class="mini-outlookbar mini-outlookbar-view3" activeIndex="0"
					style="margin-top: 5px;margin-bottom : 5px; width: 100%; height: 170px;" autoCollapse="true">
					<div title="新增周期性调度" iconCls="icon-add">
						<table id="triggerForm2" style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
							<tr height="30">
								<td style="width: 15%; text-align: right;"><font color=red>*</font>任务类型：</td>
								<td style="width: 35%;">
									<input id="beanDesc" name="beanDesc" style="width: 90%" class="mini-combobox" textField="beanDesc" valueField="id"
										url="${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!queryTaskBeanBy.action" required="true"/>
								</td>
								<td style="width: 15%; text-align: right;"><font color=red>*</font>参数设置：</td>
								<td style="width: 35%;">
									<input id="cronExp" name="cronExp" class="mini-textbox" required="true" style="width: 60%;"/>
									<span><a href="javascript:void(0);" onclick="direction()"><u>参数配置说明</u></a></span>
								</td>
							</tr>
							<tr height="30">
								<td align="right">调度说明：</td>
								<td colspan="3">
									<input id="ext1" name="ext1" class="mini-textarea" vtype="maxLength:50" emptyText="在此输入调度说明，最大50个字符" style="width: 84%"/>
								</td>
							</tr>
							<tr>
								<td>
									<input id="type" name="type" value="2" class="mini-textbox" style="display: none" />
								</td>
							</tr>
							<tr height="30">
								<td colspan="4" align="center">
									<a class="mini-button" onclick="doAdd('triggerForm2')">确认添加</a>
								</td>
							</tr>
						</table>
					</div>
					<div title="新增临时调度" iconCls="icon-add" headerStyle="">
						<table id="triggerForm1" style="width: 100%;" border="0" cellpadding="0" cellspacing="0">
							<tr height="30">
								<td style="width: 15%; text-align: right;"><font color=red>*</font>任务类型：</td>
								<td style="width: 35%;">
									<input id="beanDesc" name="beanDesc" style="width: 90%" class="mini-combobox" textField="beanDesc" valueField="id"
										url="${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!queryTaskBeanBy.action" required="true"/>
								</td>
								<td style="width: 15%; text-align: right;"><font color="red">*</font>开始时间：</td>
								<td style="width: 35%;">
									<input id="startTime_date" name="startTime_date" class="mini-datepicker" style="width: 60%;"
									required="true" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true" showOkButton="true" showClearButton="false" />
								</td>
							</tr>
							<tr height="30">
								<td align="right">调度说明：</td>
								<td colspan="4">
									<input id="ext1" name="ext1" class="mini-textarea" vtype="maxLength:50" emptyText="在此输入调度任务说明，最大50个汉字" style="width: 84%"/>
								</td>
							</tr>
							<tr>
								<td>
									<input id="type" name="type" value="1" class="mini-textbox" style="display: none" />
								</td>
							</tr>
							<tr height="30">
								<td colspan="4" align="center">
									<a class="mini-button" onclick="doAdd('triggerForm1')">确认添加</a>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	mini.parse(); //mini标签解析
	
	var grid = mini.get("triggerGrid");
	var timer_interval=null;
	grid.load();//加载数据
	timer_interval = setInterval(doQuery,60000);
	//渲染调度类型
	function onTriggerType(e) {
		if(e.value == "CRON") {
			return "周期";
		} else if(e.value == "SIMPLE") {
			return "临时";
		}
	}
	
	//渲染调度状态
	function onStatus(e) {
		if (e.value == "WAITING") {
			e.cellStyle = "color:blue; ";
			return "等待";
		} else if(e.value == "ACQUIRED") {
			e.cellStyle = "color:green;";
			return "执行";
		} else if(e.value == "PAUSED"){
			e.cellStyle = "color:red;";
			return "暂停";
		} else if(e.value == "ERROR"){
			e.cellStyle = "color:red;";
			return "错误";
		} else if(e.value == "COMPLETE"){
			e.cellStyle = "color:green;";
			return "完成";
		}
	}
	
	//渲染任务名称
	function onDescRenderer(e){
		var record = e.record;
		var uid = record._uid;
		var html="";
		html = "<div class='titlelink'><a href='javascript:void(0);' onclick='doView("+uid+")'>"+record.beanDesc+"</a></div>";
		return html;
	}

	//渲染下次执行时间
	function formateDate(e){
		if(e.value == "0" || e.value == "") {
			return "0";
		}
		var millisecond = e.value;
		var date = mini.formatDate(new Date(millisecond),"yyyy-MM-dd HH:mm:ss");
		return date;
	}
	
	//查看提示
	function direction(){
		mini.open({
			url: "${pageContext.request.contextPath}/pages/m42/m420202/returnDataRule.html",
			title: "Corn表达式说明", width: 800, height: 400
		});
	}
	
	//渲染操作按钮
	function onActionRenderer(e) {
		var record = e.record;
		var uid = record._uid;
		var html="";
		if(record.triggerState=="WAITING" || record.triggerState=="ACQUIRED"){
			html+="<a class='mini-button' title='暂停' style='width:26px;' href='javascript:void(0);' onclick='doUpdate("+uid+",0)'><span class='mini-button-text  mini-button-icon icon-split'>&nbsp;</span></a>&nbsp;&nbsp;";
		} else if(record.triggerState=="PAUSED"){
			html+="<a class='mini-button' title='恢复' style='width:26px;' href='javascript:void(0);' onclick='doUpdate("+uid+",1)'><span class='mini-button-text  mini-button-icon icon-ok'>&nbsp;</span></a>&nbsp;&nbsp;";
		}
		html+="<a class='mini-button' title='删除' style='width:26px;' href='javascript:void(0);' onclick='doRemove("+uid+")'><span class='mini-button-text  mini-button-icon icon-remove'>&nbsp;</span></a>";
		return html;
	}
	
	//新增
	function doAdd(frmid){
		var frm_submit=new mini.Form(frmid);
		//效验
		frm_submit.validate();
		if(!frm_submit.isValid()) {
			return;//效验不通过
		}
		var triggerConfigId = frm_submit.getData().beanDesc;
		MiniUtils.maskwin("校验中...");
		MiniUtils.request({
			url : "${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!checkTriggerExits.action",
			data:{triggerConfigId:triggerConfigId},
			success : function(result) {
				MiniUtils.unmaskwin();
				if(result.existsflag){
					exitsTrigger(frmid,result.triggerName);
				} else {
					doSave("",frmid,result.triggerName);
				}
			},
			error:function(e){
				MiniUtils.unmaskwin();
				MiniUtils.alert(e);
			}
		});
	}
	
	function exitsTrigger(frmid,triggerName){
		mini.confirm("已存在相同类型的调度（等待执行或执行中），确定将会自动暂停原有调度,是否确定?", "确定保存", function (action) {
			if (action == "ok") {
				doSave("pause",frmid,triggerName);
			}
		});
	}
	
	function doSave(action,frmid,triggerName){
		var frm_submit=new mini.Form(frmid);
		//效验
		frm_submit.validate();
		if(!frm_submit.isValid()) {
			return;//效验不通过
		}
		var data=frm_submit.getData(true);
		data.action = action;
		data.triggerName = triggerName;
		MiniUtils.maskwin("保存中...");
		MiniUtils.request({
			url : "${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!saveTrigger.action",
			data: data,
			success : function(result) {
				MiniUtils.unmaskwin();
				//完成添加后清空表单
				frm_submit.reset();
				MiniUtils.info("添加成功",function(action){
					doQuery();
				});
			},
			error:function(e){
				MiniUtils.unmaskwin();
				MiniUtils.alert(e);
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
	
	//更新
	function doUpdate(row_uid,status){
		var row = grid.getRowByUID(row_uid);
		var triggerConfigId = row.beanId;
		var triggerName = row.triggerName;
		var oldName = "";
		//如果是恢复则需要先检测是否存在同样调度的等待或运行中状态
		if(status=="1") {
			MiniUtils.maskwin("校验中...");
			MiniUtils.request({
				url : "${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!checkTriggerExits.action",
				data:{triggerConfigId:triggerConfigId},
				success : function(result) {
					MiniUtils.unmaskwin();
					if(result.existsflag){
						oldName = result.triggerName;
						reviewTrigger(triggerName,status,oldName,triggerConfigId);
					} else {
						changeStatus("",triggerName,status,"",triggerConfigId);
					}
				},
				error:function(e){
					MiniUtils.unmaskwin();
					MiniUtils.alert(e,function(action){
						doQuery();
					});
				}
			});
		} else {
			changeStatus("",triggerName,status,"",triggerConfigId);
		}
	}
	
	function changeStatus(action,name,status,oldName,triggerConfigId) {
		MiniUtils.maskwin("更新中...");
		MiniUtils.request({
			url : "${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!changeStatues.action",
			data: {triggerName:name,triggerState:status,action:action,ext2:oldName,beanId:triggerConfigId},
			success : function(result) {
				MiniUtils.unmaskwin();
				//完成添加后清空表单
				MiniUtils.info("更新成功",function(action){
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
	
	function reviewTrigger(triggerName,status,oldName,triggerConfigId) {
		mini.confirm("已存在相同类型的调度（等待执行或执行中），确定将会自动暂停原有调度,是否确定?", "确定更新", function (action) {
			if (action == "ok") {
				changeStatus("resume",triggerName,status,oldName,triggerConfigId);
			}
		});
	}
	
	//删除
	function doRemove(row_uid){
		var row = grid.getRowByUID(row_uid);
		var triggerConfigId = row.beanId;
		var triggerName = row.triggerName;
		mini.confirm("提示：确定删除？", "确定？", function (action) {
			if (action == "ok") {
				MiniUtils.maskwin("删除中...");
				MiniUtils.request({
					url : "${pageContext.request.contextPath}/quartz/config/quartzTriggerConfig!removeTrigger.action",
					data: {triggerName:triggerName},
					success : function(result) {
						MiniUtils.unmaskwin();
						//完成添加后清空表单
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
		});
	}
	
	//重新加载
	function doQuery(){
		grid.reload();
	}
</script>
</body>
</html>
