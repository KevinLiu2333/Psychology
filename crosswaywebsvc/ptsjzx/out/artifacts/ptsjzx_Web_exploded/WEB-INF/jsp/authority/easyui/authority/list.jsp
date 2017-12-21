<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${ctx}/tiles/scripts/frame.js"></script>
<script type="text/javascript">
var editIndex = undefined;  
function endEditing(){  
    if (editIndex == undefined){return true}
    if ($('#authorityGrid').datagrid('validateRow', editIndex)){  
        $('#authorityGrid').datagrid('endEdit', editIndex);  
        editIndex = undefined;  
        return true;  
    } else {  
        return false;  
    }  
}  
function onClickRow(index){  
    if (editIndex != index){  
        if (endEditing()){  
            $('#authorityGrid').datagrid('selectRow', index)  
                    .datagrid('beginEdit', index);  
            editIndex = index;  
        } else {  
            $('#authorityGrid').datagrid('selectRow', editIndex);  
        }  
    }  
}  
// 增加
function append(){  
    if (endEditing()){
        $('#authorityGrid').datagrid('appendRow',{status:'P'});  
        editIndex = $('#authorityGrid').datagrid('getRows').length-1;  
        $('#authorityGrid').datagrid('selectRow', editIndex)  
                .datagrid('beginEdit', editIndex);  
    }  
}
// 删除
function remove(){  
	if (editIndex == undefined){return;}
	$('#authorityGrid').datagrid('cancelEdit', editIndex);
	$.messager.confirm('提示信息', '是否确认删除所选数据?', function(r){  
        if (r){  
        	var row = $('#authorityGrid').datagrid("getSelected");
        	var json = '{"id":"' + row.nodeId+ '"}';
        	$.ajax({
        		url: '${ctx}/authority/delete',
        		data: json,
        		type: "POST",
        		dataType: 'json',
        		success: function(result) {
        			if (result == true) {
        				$("#authorityGrid").datagrid("reload");
        				slideMessage('成功删除权限');
        			} else {
        				slideMessage('删除权限失败');
        			}
        		}
        	});
            editIndex = undefined;
        }  
    });
      
}
// 接受
function accept(){  
    if (endEditing()){  
    	saveChanges();
    }  
}  
// 撤消
function reject(){  
    $('#authorityGrid').datagrid('rejectChanges');  
    editIndex = undefined;  
}
// 保存变化的内容
function saveChanges() {
	var rows = $('#authorityGrid').datagrid('getChanges');
	var data = json2String(rows);
	//var data = "[{nodeId:'1',nodeDesc:'2',nodeLevel:'1',nodeOrder:'2'},{nodeId:'1',nodeDesc:'2',nodeLevel:'1',nodeOrder:'2'}]";
	$.ajax({
        type: "POST",   //访问WebService使用Post方式请求
        contentType: "application/json", 
        url: "authority/update", //调用WebService的地址和方法名称组合 ---- WsURL/方法名
        data: data,  //这里是要传递的参数，格式为 data: "{paraName:paraValue}",下面将会看到       
        dataType: 'json',   //WebService 会返回Json类型
        success: function(result) {     //回调函数，result，返回值
            if (result == true) {
            	$('#authorityGrid').datagrid('acceptChanges');
            	slideMessage('增加权限成功');
            } else {
            	slideMessage('增加权限失败');
            }
        }
    });
}
</script>
<form action="list" method="get">
</form>
<div id="toolbar" style="height:auto">  
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>  
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="remove()">删除</a>  
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>  
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤消</a>  
</div>
<table id="authorityGrid" class="easyui-datagrid"
	data-options="singleSelect:true,border:0,toolbar:'#toolbar',collapsible:true,url:'authority/list',pagination:true,rownumbers:true,fit:true,onClickRow:onClickRow">  
  	<thead>  
    	<tr>
            <th data-options="field:'nodeCode',width:80,editor:{type:'numberbox',options:{precision:0}}">节点编码</th>
            <th data-options="field:'nodeDesc',width:80,editor:'text'">节点描述</th>
            <th data-options="field:'nodeLevel',width:80,editor:{type:'numberbox',options:{precision:0}}">节点层次</th>
            <th data-options="field:'nodePid',width:80,editor:{type:'numberbox',options:{precision:0}}">父节点编码</th>
            <th data-options="field:'order',width:80,editor:{type:'numberbox',options:{precision:0}}">节点排序</th>  
        </tr>  
	</thead>
</table>