<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx }/tiles/jquery-easyui/themes/default/easyui.css">  
<link rel="stylesheet" type="text/css" href="${ctx }/tiles/jquery-easyui/themes/icon.css">  
<script type="text/javascript" src="${ctx }/tiles/jquery-easyui/jquery-1.8.0.min.js"></script>  
<script type="text/javascript" src="${ctx }/tiles/jquery-easyui/jquery.easyui.min.js"></script>
<meta http-equiv="Cache-Control" content="no-cache">
<title>松江区政务数据中心-角色列表</title>
<script type="text/javascript">
var editIndex = undefined;  
function endEditing(){  
    if (editIndex == undefined){return true}
    if ($('#dg').datagrid('validateRow', editIndex)){  
        $('#dg').datagrid('endEdit', editIndex);  
        editIndex = undefined;  
        return true;  
    } else {  
        return false;  
    }  
}  
function onClickRow(index){  
    if (editIndex != index){  
        if (endEditing()){  
            $('#dg').datagrid('selectRow', index)  
                    .datagrid('beginEdit', index);  
            editIndex = index;  
        } else {  
            $('#dg').datagrid('selectRow', editIndex);  
        }  
    }  
}  
function append(){  
    if (endEditing()){
        $('#dg').datagrid('appendRow',{status:'P'});  
        editIndex = $('#dg').datagrid('getRows').length-1;  
        $('#dg').datagrid('selectRow', editIndex)  
                .datagrid('beginEdit', editIndex);  
    }  
}  
function remove(){  
    if (editIndex == undefined){return}  
    $('#dg').datagrid('cancelEdit', editIndex)  
            .datagrid('deleteRow', editIndex);  
    editIndex = undefined;  
}  
function accept(){  
    if (endEditing()){  
    	saveChanges();
    }  
}  
function reject(){  
    $('#dg').datagrid('rejectChanges');  
    editIndex = undefined;  
}
function getChanges(){  
    var rows = $('#dg').datagrid('getChanges');  
    alert(rows.length+' rows are changed!');  
}
function saveChanges() {
	var rows = $('#dg').datagrid('getChanges');
	var data = O2String(rows);
	//var data = "[{nodeId:'1',nodeDesc:'2',nodeLevel:'1',nodeOrder:'2'},{nodeId:'1',nodeDesc:'2',nodeLevel:'1',nodeOrder:'2'}]";
	$.ajax({
        type: "POST",   //访问WebService使用Post方式请求
        contentType: "application/json", 
        url: "update", //调用WebService的地址和方法名称组合 ---- WsURL/方法名
        data: data,  //这里是要传递的参数，格式为 data: "{paraName:paraValue}",下面将会看到       
        dataType: 'json',   //WebService 会返回Json类型
        success: function(result) {     //回调函数，result，返回值
            if (result == true) {
            	$('#dg').datagrid('acceptChanges');
            }
        }
    });
}
var O2String = function (O) {
	 //return JSON.stringify(jsonobj);
	 var S = [];
	 var J = "";
	 if (Object.prototype.toString.apply(O) === '[object Array]') {
	     for (var i = 0; i < O.length; i++)
	         S.push(O2String(O[i]));
	     J = '[' + S.join(',') + ']';
	 }
	 else if (Object.prototype.toString.apply(O) === '[object Date]') {
	     J = "new Date(" + O.getTime() + ")";
	 }
	 else if (Object.prototype.toString.apply(O) === '[object RegExp]' || Object.prototype.toString.apply(O) === '[object Function]') {
	     J = O.toString();
	 }
	 else if (Object.prototype.toString.apply(O) === '[object Object]') {
	     for (var i in O) {
	         var temp = typeof (O[i]) == 'string' ? '\'' + O[i] + '\'' : (typeof (O[i]) === 'object' ? O2String(O[i]) : O[i]);
	         S.push(i + ':' + temp);
	     }
	     J = '{' + S.join(',') + '}';
	 }
	 return J;
};
</script>
</head>
<body>
	<form action="list" method="get">
	<div class="datagrid-wrap" style="width: 1024px; height: 500px;">
	    <div id="toolbar" style="height:auto">  
	        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>  
	        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="remove()">删除</a>  
	        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a>  
	        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤消</a>  
	    </div>
	    <table id="dg" class="easyui-datagrid" title="用户信息列表" style="width:1024px;height:469px"  
	            data-options="rownumbers:true,singleSelect:true,toolbar:'#toolbar',striped:true,onClickRow:onClickRow">  
	        <thead>  
	            <tr>  
	                <th data-options="field:'nodeId',width:80,editor:{type:'numberbox',options:{precision:0}}">节点编码</th>
	                <th data-options="field:'nodeDesc',width:80,editor:'text'">节点描述</th>
	                <th data-options="field:'nodeLevel',width:80,editor:{type:'numberbox',options:{precision:0}}">节点层次</th>
	                <th data-options="field:'nodePid',width:80,editor:{type:'numberbox',options:{precision:0}}">父节点编码</th>
	                <th data-options="field:'order',width:80,editor:{type:'numberbox',options:{precision:0}}">节点排序</th>  
	            </tr>  
	        </thead>
	        <tbody>
	        	<c:forEach items="${obj.list}" var="authority" >
	        	<tr>
	        		<td>${authority.nodeId }</td>
	        		<td>${authority.nodeDesc }</td>
	        		<td>${authority.nodeLevel }</td>
	        		<td>${authority.nodePid }</td>
	        		<td>${authority.order }</td>
	        	</tr>
				</c:forEach>
	        </tbody>
	    </table>
	    <jsp:include page="/common/page-info.jsp"></jsp:include>
	</div>
	</form>
</body>
</html>