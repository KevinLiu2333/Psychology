<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增WEB应用</title>
<jsp:include page="/pages/include/include.jsp?v=2"></jsp:include>
<style type="text/css">
    body
    {
        font-size:12px;
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:hidden;
    }
    </style>
  </head>
  
  <body>    
    <form id="form" method="post">
        <fieldset style="border:solid 1px #aaa;padding:3px;">
            <legend >应用信息</legend>
            <div style="padding:5px;text-align:center;">
		        <table align="center">
		            <tr>
		                <td style="width:90px;" align="right">应用代码<font color="red">*</font>：</td>
		                <td style="width:350px;">    
		                    <input id="ifdefcode" name="ifdefcode" class="mini-textbox" required="true" width="100%"/>
		                	<input id="iftype" name="iftype" class="mini-hidden" value="02"/>
		                	<input id="ifdefineid" name="ifdefineid" class="mini-hidden" />
		                </td>
		            </tr>
		            <tr>
		                <td align="right">应用名称<font color="red">*</font>：</td>
		                <td style="width:350px;">    
		                    <input name="ifdefname" class="mini-textbox" required="true" width="100%"/>
		                </td>
		            </tr>
		            <tr>
		                <td align="right">访问地址<font color="red">*</font>：</td>
		                <td style="width:350px;">    
		                    <input id="ifuri" name="ifuri" class="mini-textbox" required="true" width="100%"/>
		                </td>
		            </tr> 
		             <tr>
		                <td align="right"><font color="red">提示</font>：</td>
		                <td align="left"> 
		                	<font color="red">请填入正确的URL，例如：http://www.baidu.com </font>
		                </td>
		            </tr> 
		            <tr style="display:none">
						<td align="right">接口类型：</td>
						<td >
							<input id="ifsubtype" name="ifsubtype" class="mini-textbox" value="03"/>
						</td>
					</tr>
		        </table>            
            </div>
        </fieldset>
        <div style="text-align:center;padding:10px;">      
        	<a id="test" class="mini-button" onclick="onTest" style="width:60px;margin-right:20px;">测试</a>         
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>
			<a class="mini-button" onclick="doReset" style="width:60px;margin-right:20px;">重置</a>
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>        
    </form>
    <script type="text/javascript">
        mini.parse();
        var form = new mini.Form("form");

        ////////////////////
        //标准方法接口定义
        var id="";
        var action = "";
        function SetData(data) {
        	id = data.ifdefineid;
        	action = data.action
            if (action == "edit") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
				mini.get("ifdefcode").setEnabled(false);
				MiniUtils.maskwin("导入中...");
				MiniUtils.request({
					url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!load.action",
					data: { ifdefineid: data.ifdefineid },
					success: function (text) {
						form.setData(text);
						mini.get("ifsubtype").setValue(text.ifsubtype);
						MiniUtils.unmaskwin();	
					},
					error: function (text) {
						MiniUtils.unmaskwin();	
						mini.alert("提交失败，返回结果:" + text);
					}
				});
            }
        }
        
        function SaveData() {
        	form.validate();
        	if (form.isValid() == false) {
            	mini.alert("请将数据填写完整！")
            	return;
			}
			try{
				MiniUtils.maskwin("提交中...");
				var data = form.getData();//获取表单多个控件的数据
				data = mini.encode(data);//序列化成JSON
				var ifsubtype = mini.get("ifsubtype").getValue();
			    var url = "";
				if(action=="new"){
						url = "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!add.action";
					}else{
						url = "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!edit.action";
					}
				MiniUtils.request({
					url : url,
					data: { jsonData: data,
							ifsubtype:ifsubtype },
					success: function (text) {
						MiniUtils.unmaskwin();	
						MiniUtils.info("保存成功!",CloseWindow);
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

        //自定义窗口关闭方法
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onTest(e){
        	try{
        		var data = form.getData();//获取表单多个控件的数据
				if(data.ifuri){	
					MiniUtils.maskwin("测试中...");		
					MiniUtils.request({
						url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!test.action",
						data: { ifdefineid: id,ifuri:data.ifuri,ifsubtype:data.ifsubtype},
						success: function (text) {
							MiniUtils.unmaskwin();
							if(text.detailmsg){
								mini.alert("测试结果："+text.detailmsg);
							}
						},
						error: function (text) {
							MiniUtils.unmaskwin();
							mini.alert("提交失败，返回结果:" + text);
						}
					});
				}else{
					mini.alert("请输入访问地址");
				}			
			}catch(e){
				MiniUtils.unmaskwin();
				mini.alert("未知异常："+e.responseText);
			}
        }
        
        function onOk(e) {
            SaveData();
        }
        
        function onCancel(e) {
            CloseWindow("cancel");
        }
        
function doReset() {
if (action == "edit") {
	try{
		//跨页面传递的数据对象，克隆后才可以安全使用
		MiniUtils.maskwin("导入中...");
		MiniUtils.request({
			url: "${pageContext.request.contextPath}/dispatch/m410101/m410101Action!load.action",
			data: { ifdefineid: mini.get("ifdefineid").getValue() },
			success: function (text) {
				form.setData(text);
				mini.get("ifsubtype").setValue(text.ifsubtype);
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
	}else{
		form.reset();	
	}
}
    </script>
</body>
</html>
