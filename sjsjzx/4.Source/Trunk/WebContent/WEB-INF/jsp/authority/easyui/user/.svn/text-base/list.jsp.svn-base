<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${ctx}/tiles/scripts/frame.js"></script>
<script type="text/javascript">
function stateFormater(v,row,index) {
	if (v != '1')
		return "<font color='red'>" + v +"</font>";
	return "<font color='blue'>" + v +"</font>";
}
var toolbar = [{  
    text:'增加',  
    iconCls:'icon-add',  
    handler:function(){
    	tab2Menu('centent_tabs','增加用户信息','${ctx}/user/edit');
   	}},'-',{  
    text:'修改',  
    iconCls:'icon-edit',  
    handler:function(){
    	var row = $("#userGrid").datagrid('getSelected');
    	tab2Menu('centent_tabs','增加用户信息','${ctx}/user/edit?id=' + row.userId);
    }},'-',{  
    text:'删除',  
    iconCls:'icon-remove',  
    handler:function(){
    	var row = $("#userGrid").datagrid('getSelected');
    	var json = json2String(row);
    	$.ajax({
    		url: '${ctx}/user/delete',
    		data: json,
    		type: "POST",
    		dataType: 'json',
    		success: function(result) {
    			if (result == true) {
    				$("#userGrid").datagrid("reload");
    				slideMessage('成功删除用户');
    			} else {
    				slideMessage('删除用户失败');
    			}
    		}
    	});
    }}];
$(function(){
	$("#userGrid").datagrid({toolbar:toolbar});
})
</script>
<table id="userGrid" class="easyui-datagrid"
	data-options="singleSelect:true,border:0,collapsible:true,url:'user/list',pagination:true,rownumbers:true,fit:true">  
  	<thead>  
    	<tr>  
        	<th data-options="field:'userId',checkbox:true"></th>  
            <th data-options="field:'logonName',width:100">登陆名</th>
            <th data-options="field:'displayName',width:100">显示名称</th>
            <th data-options="field:'state',width:100,formatter:stateFormater">状态</th>
            <th data-options="field:'logonFailTimes',width:80,align:'right'">登陆失败次数</th>  
        </tr>  
	</thead>
</table>
