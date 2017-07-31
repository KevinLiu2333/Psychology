<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${ctx}/tiles/scripts/frame.js"></script>
<script type="text/javascript">
$(function(){
    $('#roleForm').submit(function() {
    	<c:if test="${obj.role != null}">
	    	var rows = $('#roleQxtree').tree('getChecked');
	    	var data = '';
	        for(var i=0; i<rows.length; i++){
	        	if (data != '') data += ',';  
	        	var element = '{nodeId:\'' + rows[i].id + '\',nodeDesc:\'' + rows[i].text + '\'}';
	        	data += element;
	        }
	        data = '{id:\'${obj.role.roleId}\',authority:[' + data + ']}';
		  	$.ajax({
	    		url: '${ctx}/role/authority/update',
	    		data: data,
	    		type: "POST",
	    		dataType: "json",
	    		success: function(result)	{
	    			if (result == true) {
	    				$('#roleForm').form('submit');
	    				//slideMessage('用户信息保存成功！');
	    			} else {
	    				slideMessage('角色信息保存失败！');
	    			}
	    		}
	  		});
    	</c:if>
    	<c:if test="${obj.role == null}">
    		$('#roleForm').form('submit');
    	</c:if>
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
           children:dataFilter(row.children)
        });
    }
    return nodes;
}
function showUser(){
	$('#roleAllUser').datagrid({  
	    url:'${ctx}/role/user/list/all?id=${obj.role.roleId}',
	    onLoadSuccess:function(data) {
	    	$('#roleUserDlg').dialog('open');
	    }
	}); 
}
function slideMessage(msg){  
    $.messager.show({  
        title:'提示信息',  
        msg:msg,  
        timeout:5000,  
        showType:'slide'  
    });  
}  
function addUser(){
	var rows = $('#roleAllUser').datagrid("getSelections");
	var json = json2String(rows);
	var data = '{id:"${obj.role.roleId}",user:'+json+'}';
	$.ajax({
		url: '${ctx}/role/user/update',
		data: data,
		type: "POST",
		dataType: 'json',
		success: function(result) {
			if (result == true) {
				$("#roleUserData").datagrid("reload");
				$('#roleUserDlg').dialog('close');
				slideMessage('成功增加' + rows.length + '条用户');
			} else {
				slideMessage('增加用户失败');
			}
		}
	});
}
function delUser(){
	$.messager.confirm('提示信息', '是否确认删除所选数据?', function(r){  
        if (r){  
        	var rows = $('#roleUserData').datagrid("getSelections");
        	var json = json2String(rows);
        	var data = '{id:"${obj.role.roleId}",user:'+json+'}';
        	$.ajax({
        		url: '${ctx}/role/user/delete',
        		data: data,
        		type: "POST",
        		dataType: 'json',
        		success: function(result) {
        			if (result == true) {
        				$("#roleUserData").datagrid("reload");
        				$('#roleUserDlg').dialog('close');
        				slideMessage('成功删除' + rows.length + '条用户');
        			} else {
        				slideMessage('删除用户失败');
        			}
        		}
        	});
        }  
    });
}
function doRoleSearch(value){
	$('#roleAllUser').datagrid('load', {displayName:value});
}
function submitForm(){
	$('#roleForm').submit();
	//var b = $('#ff').serialize();
	//alert(b);
	
}  
function clearForm(){
    $('#roleForm').form('clear');
}
</script>
<div class="easyui-layout" data-options="fit:true">
	<form id="roleForm" method="post" action="role/update">
		<input type="hidden" name="roleId" value="${obj.role.roleId}"/>
    	<div data-options="region:'<c:if test="${obj.role == null}">center</c:if><c:if test="${obj.role != null}">west</c:if>',split:true,border:false" style="width:500px;height:300px">
        	<div class="easyui-panel" title="角色信息管理" style="padding:10px;" data-options="fit:true">
				<table style="margin: 50px;">
			        <tr>  
			        	<td>角色名称:</td>
			            <td><input class="easyui-validatebox" style="width: 200px" type="text" name="roleDesc" data-options="required:true" value="${obj.role.roleDesc }"></input></td>  
			    	</tr>
				</table>
			</div>
        </div>
        <c:if test="${obj.role != null}">
        <div data-options="region:'center',border:false">
			<div class="easyui-panel" title="用户信息管理" data-options="fit:true,tools:'#userTool'">
               <table id=roleUserData class="easyui-datagrid"
                   data-options="border:false,singleSelect:false,fit:true,url:'${ctx }/role/user/list?id=${obj.role.roleId}'">  
               		<thead>  
                   		<tr>  
	                       <th data-options="field:'userId',checkbox:true" width="80"></th>  
	                       <th data-options="field:'displayName'" width="200">用户名称</th>
	                   </tr>  
              		 </thead>
           		</table>
			</div>
        </div>  
        <div data-options="region:'east',split:true,border:false" style="width:280px">
        	<div class="easyui-panel" title="权限信息管理" style="padding:10px;" data-options="fit:true">
               	<ul id="roleQxtree" class="easyui-tree" data-options="animate:true,checkbox:true,loadFilter:dataFilter,url:'${ctx}/role/authority?id=${obj.role.roleId }'"></ul> 
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
<div id="roleUserDlg" class="easyui-dialog" title="用户列表" style="width:600px;height:500px;"
     data-options="iconCls:'icon-save',toolbar:'#roleDlg-toolbar',buttons:'#roleDlg-buttons',modal:true,cache:false,closed:true">  
	<table id="roleAllUser" class="easyui-datagrid"
		data-options="border:false,singleSelect:false,fit:true">
		<thead>  
        	<tr>  
            	<th data-options="field:'userId',checkbox:true" width="80"></th>  
                <th data-options="field:'displayName'" width="200">用户名称</th>
            </tr>  
        </thead>  
	</table>
</div>
<div id="roleDlg-toolbar" style="padding:2px 0">  
    <table cellpadding="0" cellspacing="0" style="width:100%">  
        <tr>  
            <td style="text-align:left;padding-right:2px">  
                <input class="easyui-searchbox" data-options="prompt:'显示名称',searcher:doRoleSearch"" style="width:150px"></input>  
            </td>  
        </tr>
    </table>  
</div>
<!-- 用户列表Panel 工具栏 -->
<div id="userTool">
     <a href="javascript:void(0)" class="icon-add" onclick="showUser()"></a>
     <a href="javascript:void(0)" class="icon-remove" onclick="delUser()"></a>
 </div>
<!-- 增加角色列表 dialog 工具栏 -->
<div id="roleDlg-buttons">  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:addUser()">保存</a>  
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')">关闭</a>  
</div>
 