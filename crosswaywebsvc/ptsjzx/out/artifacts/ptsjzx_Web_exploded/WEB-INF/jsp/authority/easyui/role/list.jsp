<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${ctx}/tiles/scripts/frame.js"></script>
<script type="text/javascript">
function stateFormater(v,row,index) {
	if (v != '1')
		return "<font color='red'>" + v +"</font>";
	return "<font color='blue'>" + v +"</font>";
}
var roleToolbar = [{  
    text:'增加',  
    iconCls:'icon-add',  
    handler:function(){
    	tab2Menu('centent_tabs','增加角色信息','${ctx}/role/edit');
   	}},'-',{  
    text:'修改',  
    iconCls:'icon-edit',  
    handler:function(){
    	var row = $("#roleGrid").datagrid('getSelected');
    	tab2Menu('centent_tabs','修改角色信息','${ctx}/role/edit?id=' + row.roleId);
    }},'-',{  
    text:'删除',  
    iconCls:'icon-remove',  
    handler:function(){
    	var row = $("#roleGrid").datagrid('getSelected');
    	var json = json2String(row);
    	$.ajax({
    		url: '${ctx}/role/delete',
    		data: json,
    		type: "POST",
    		dataType: 'json',
    		success: function(result) {
    			if (result == true) {
    				$("#roleGrid").datagrid("reload");
    				slideMessage('成功删除用户');
    			} else {
    				slideMessage('删除用户失败');
    			}
    		}
    	});
    }}];
$(function(){
	$("#roleGrid").datagrid({toolbar:roleToolbar});
});
</script>
<table id="roleGrid" class="easyui-datagrid"
	data-options="singleSelect:true,border:0,collapsible:true,url:'role/list',pagination:true,rownumbers:true,fit:true">  
  	<thead>  
    	<tr>  
        	<th data-options="field:'roleId',checkbox:true"></th>  
            <th data-options="field:'roleDesc',width:200">角色名称</th>
        </tr>  
	</thead>
</table>
