<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${ctx}/tiles/scripts/frame.js"></script>
<script type="text/javascript">
$(function(){
    $('#userForm').submit(function() {
    	var rows = $('#userQxtree').tree('getChecked');
    	var data = '';
        for(var i=0; i<rows.length; i++){
        	if (data != '') data += ',';  
        	var element = '{nodeId:\'' + rows[i].id + '\',nodeDesc:\'' + rows[i].text + '\'}';
        	data += element;
        }
        data = '{id:\'${obj.user.userId}\',authority:[' + data + ']}';
	  	$.ajax({
    		url: '${ctx}/user/authority/update',
    		data: data,
    		type: "POST",
    		dataType: "json",
    		success: function(result)	{
    			if (result == true) {
    				$('#userForm').form('submit');
    				//slideMessage('用户信息保存成功！');
    			} else {
    				slideMessage('用户信息保存失败！');
    			}
    		}
  		});
    	return false;
	});
});
function dataFilter(rows){
	var nodes = [];  
    for(var i=0; i<rows.length; i++){  
        var row = rows[i];
        nodes.push({  
       	   id:row.nodeId,
           text:row.nodeDesc,
           checked:row.checked,
           children:dataFilter(row.children),
           target:''
        });
    }
    return nodes;
}
function showRole(){
	$('#userAllRole').datagrid({  
	    url:'${ctx}/user/role/list/all?id=${obj.user.userId}',
	    onLoadSuccess:function(data) {
	    	$('#userRoleDlg').dialog('open');
	    }
	}); 
}
function addRole(){
	var rows = $('#userAllRole').datagrid("getSelections");
	var json = json2String(rows);
	var data = '{id:"${obj.user.userId}",role:'+json+'}';
	$.ajax({
		url: '${ctx}/user/role/update',
		data: data,
		type: "POST",
		dataType: 'json',
		success: function(result) {
			if (result == true) {
				$("#userJsdata").datagrid("reload");
				$('#userRoleDlg').dialog('close');
				slideMessage('成功增加' + rows.length + '条用户角色');
			} else {
				slideMessage('增加用户角色失败');
			}
		}
	});
}
function delRole(){
	$.messager.confirm('提示信息', '是否确认删除所选数据?', function(r){  
        if (r){  
        	var rows = $('#userJsdata').datagrid("getSelections");
        	var json = json2String(rows);
        	var data = '{id:"${obj.user.userId}",role:'+json+'}';
        	$.ajax({
        		url: '${ctx}/user/role/delete',
        		data: data,
        		type: "POST",
        		dataType: 'json',
        		success: function(result) {
        			if (result == true) {
        				$("#userJsdata").datagrid("reload");
        				$('#userRoleDlg').dialog('close');
        				slideMessage('成功删除' + rows.length + '条用户角色');
        			} else {
        				slideMessage('删除用户角色失败');
        			}
        		}
        	});
        }  
    });
}
function doRoleSearch(value){
	$('#userAllRole').datagrid('load', {roleDesc:value});
}
function submitForm(){
	$('#userForm').submit();
}  
function clearForm(){
    $('#userForm').form('clear');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<form id="userForm" method="post" action="user/update">
		<input type="hidden" name="userId" value="${obj.user.userId}"/>
    	<div data-options="region:'<c:if test="${obj.user == null}">center</c:if><c:if test="${obj.user != null}">west</c:if>',split:true,border:false" style="width:500px;height:300px">
        	<div class="easyui-panel" title="用户信息管理" style="padding:10px;" data-options="fit:true">
				<table style="margin: 50px;">
					<tr>  
				        <td><label>用户名:</label></td>  
			            <td><input class="easyui-validatebox" style="width: 200px" type="text" name="logonName" data-options="required:true" value="${obj.user.logonName }"></input></td>  
			    	</tr>
					<tr>  
			        	<td>密码:</td>
			            <td><input class="easyui-validatebox" style="width: 200px" type="password" name="password" data-options="required:true" value="${obj.user.password }"></input></td>  
			        </tr>
			        <tr>  
			        	<td>显示名称:</td>
			            <td><input class="easyui-validatebox" style="width: 200px" type="text" name="displayName" data-options="required:true" value="${obj.user.displayName }"></input></td>  
			    	</tr>
				</table>
			</div>
        </div>
        <c:if test="${obj.user != null}">
        <div data-options="region:'center',border:false">
			<div class="easyui-panel" title="角色信息管理" data-options="fit:true,tools:'#userRoleTool'">
               <table id="userJsdata" class="easyui-datagrid"
                   data-options="border:false,singleSelect:false,fit:true,url:'${ctx }/user/role/list?id=${obj.user.userId}'">  
               		<thead>  
                   		<tr>  
	                       <th data-options="field:'roleId',checkbox:true" width="80"></th>  
	                       <th data-options="field:'roleDesc'" width="200">角色描述</th>
	                   </tr>  
              		 </thead>
           		</table>
			</div>
        </div>  
        <div data-options="region:'east',split:true,border:false" style="width:280px">
        	<div class="easyui-panel" title="权限信息管理" style="padding:10px;" data-options="fit:true">
               	<ul id="userQxtree" class="easyui-tree" data-options="animate:true,checkbox:true,loadFilter:dataFilter,url:'${ctx}/user/authority?id=${obj.user.userId }'"></ul> 
			</div>
        </div>
        </c:if>  
        <div data-options="region:'south',split:false,border:false" style="height:50px">
        	<div class="easyui-panel" title="" style="padding:10px;background:#fafafa;" data-options="fit:true">
               	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a>
               	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清除</a> 
			</div>
        </div>
	</form>
</div>
<div id="userRoleDlg" class="easyui-dialog" title="角色列表" style="width:600px;height:500px;"
     data-options="iconCls:'icon-save',toolbar:'#userDlg-toolbar',buttons:'#userDlg-buttons',modal:true,cache:false,closed:true">  
	<table id="userAllRole" class="easyui-datagrid"
		data-options="border:false,singleSelect:false,fit:true">
		<thead>  
        	<tr>  
            	<th data-options="field:'roleId',checkbox:true" width="80"></th>  
                <th data-options="field:'roleDesc'" width="200">角色描述</th>
            </tr>  
        </thead>  
	</table>
</div>
<div id="userDlg-toolbar" style="padding:2px 0">  
    <table cellpadding="0" cellspacing="0" style="width:100%">  
        <tr>  
            <td style="text-align:left;padding-right:2px">  
                <input class="easyui-searchbox" data-options="prompt:'用户名',searcher:doRoleSearch"" style="width:150px"></input>  
            </td>  
        </tr>  
    </table>  
</div>
<!-- 角色列表Panel 工具栏 -->
<div id="userRoleTool">  
     <a href="javascript:void(0)" class="icon-add" onclick="showRole()"></a>
     <a href="javascript:void(0)" class="icon-remove" onclick="delRole()"></a>
 </div>
<!-- 增加角色列表 dialog 工具栏 -->
<div id="userDlg-buttons">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:addRole()">保存</a>  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">关闭</a>  
</div>
 