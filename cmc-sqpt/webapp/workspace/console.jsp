<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>控制面板</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
</head>

<body style="margin: 0;padding: 0;border: 0;width: 100%;height: 100%;">
	<div id="xmlForm" class="mini-fit" align="center">
		<div class="mini-tabs" activeIndex="0"  style="width:100%;height:100%;">	
		    <div title="初始加载菜单" showCloseButton="true">
			    <table align="center">
			        <tr>
			            <td >
			                <div id="listbox1" class="mini-listbox" style="width:350px;height:500px;"
			                    textField="text" valueField="id" showCheckBox="true" multiSelect="true"
			                    url="${pageContext.request.contextPath}/m38/m380101/m380101Action!queryItems.action">
			                
			                </div>
			            </td>
			            <td style="width:120px;text-align:center;">
			                <input type="button" value=">" onclick="add()" style="width:40px;"/><br />
			                <input type="button" value=">>" onclick="addAll()" style="width:40px;"/><br />
			                <input type="button" value="&lt;&lt;" onclick="removeAll()" style="width:40px;"/><br />
			                <input type="button" value="&lt;" onclick="removes()" style="width:40px;"/><br />
			 
			            </td>
			            <td>
			                <div id="listbox2" class="mini-listbox" style="width:350px;height:500px;"                     
			                     showCheckBox="true" multiSelect="true" valueField="id">     
			                    <div property="columns">
			                        <div header="菜单名称" field="text"></div>
			                    </div>
			                </div>
			            </td>
			            <td style="width:50px;text-align:center;">
			                <input type="button" value="Up" onclick="upItem()" style="width:55px;"/>
			                <input type="button" value="Down" onclick="downItem()" style="width:55px;"/>
			 
			            </td>
			        </tr>
			        <tr>
			        	<td colspan="3" style="text-align:center;">
			        	 	<input type="button" align="center" value="保存" onclick="saveData()" style="width:55px;"/>
			        	</td>
			        </tr>			    
			    </table> 
		    </div>
		    <div title="修改密码" showCloseButton="true">
				<jsp:include page="/pages/m38/m380101/changePsd.jsp"></jsp:include> 				
		    </div>	
		</div>	
	</div> 
	
<script type="text/javascript">
mini.parse();
form = new mini.Form("#addForm");

function doSave(){
    form.validate();
	if (form.isValid() == false) {
		mini.alert('请检查必填项！');
		return false;
	}		
	try{
		MiniUtils.maskwin("提交中...");
		var psd = mini.get("password").getValue();
		var oldPassword = mini.get("oldPassword").getValue();
	    var url = "${pageContext.request.contextPath}/m38/m380101/m380101Action!changePsd.action";
		MiniUtils.request({
			url : url,
			data: {password:psd,oldPassword:oldPassword},
			success: function (text) {
				MiniUtils.unmaskwin();
				if (text.flag == '0'){
					MiniUtils.alert("请输入正确的旧密码!");
				} else {
					MiniUtils.info("保存成功!");
				}
			},
			error: function (text) {
				MiniUtils.unmaskwin();	
				mini.alert("提交失败，返回结果:" + text);
			}
		});
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.responseText);
	}
}


function checkPassword(){
	var p1 = mini.get("password").getValue();
	var p2 = mini.get("password2").getValue();
	if (p1 != p2){
		$('#alertTr').show();
		mini.get("saveBtn").disable();
	} else {
		$('#alertTr').hide();
		mini.get("saveBtn").enable();
	}
}

var listbox1 = mini.get("listbox1");
var listbox2 = mini.get("listbox2");

initList();
function initList(){
	try{
		MiniUtils.maskwin("提交中...");
		var url = "${pageContext.request.contextPath}/m38/m380101/m380101Action!getExt1.action";
		MiniUtils.request({
			url : url,
			success: function (text) {
				MiniUtils.unmaskwin();
    			var items = listbox1.findItems(text.ext1);
    			listbox1.removeItems(items);
    			listbox2.addItems(items);
			},
			error: function (text) {
				MiniUtils.unmaskwin();	
				mini.alert("提交失败，返回结果:" + text);
			}
		});
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.responseText);
	}
}

function add() {
    var items = listbox1.getSelecteds();
    listbox1.removeItems(items);
    listbox2.addItems(items);
}
function addAll() {        
    var items = listbox1.getData();
    listbox1.removeItems(items);
    listbox2.addItems(items);
}
function removes() {
    var items = listbox2.getSelecteds();
    listbox2.removeItems(items);
    listbox1.addItems(items);
}
function removeAll() {
    var items = listbox2.getData();
    listbox2.removeItems(items);
    listbox1.addItems(items);
}
function upItem() {
    var items = listbox2.getSelecteds();
    for (var i = 0, l = items.length; i < l; i++) {
        var item = items[i];
        var index = listbox2.indexOf(item);
        listbox2.moveItem(item, index-1);
    }
}
function downItem() {            
    var items = listbox2.getSelecteds();            
    for (var i = items.length-1; i >=0; i--) {
        var item = items[i];
        var index = listbox2.indexOf(item);
        listbox2.moveItem(item, index + 1);
    }
}
function saveData() {
	listbox2.selectAll();
    var data = listbox2.getValue();
	var dataArr = data.split(",");
	if(dataArr.length>5){
		mini.alert("初始加载菜单最多5个");
		return;
	};
    try{
		MiniUtils.maskwin("提交中...");
	    var url = "${pageContext.request.contextPath}/m38/m380101/m380101Action!saveItems.action";
		MiniUtils.request({
			url : url,
			data: {jsonData:data},
			success: function (text) {
				MiniUtils.unmaskwin();	
			},
			error: function (text) {
				MiniUtils.unmaskwin();	
				mini.alert("提交失败，返回结果:" + text);
			}
		});
	}catch(e){
		MiniUtils.unmaskwin();	
		mini.alert("未知异常："+e.responseText);
	}
}

</script>
	
</body>
</html>
