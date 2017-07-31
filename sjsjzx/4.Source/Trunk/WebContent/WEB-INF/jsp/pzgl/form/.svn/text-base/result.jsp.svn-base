<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<script type="text/javascript" src="${ctx }/tiles/scripts/md5.js"></script>
<script type="text/javascript" src="${ctx }/tiles/scripts/dateUtils.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form1.css">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx }/tiles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
<link href="${ctx}/tiles/Validform/css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="watermark" style='position:fixed' >
		<c:forEach begin="1" end="4">
			<span style="font-size:40px;color:gray;margin:0 100px 0 100px;float:left">${obj.department}:${obj.userName}</span>
			<span style="font-size:40px;color:gray;margin:0 100px 0 100px;float:right">${obj.department}:${obj.userName}</span>
			<c:forEach begin="1" end="8">
				<br/>
			</c:forEach>
		</c:forEach>
</div>
<p style="text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px"><b>${obj.dreamForm.dreamformName}</b></p>
<form id="queryForm" name="queryForm" action="${ctx}/config/form/saveFormData" method="post">
	<input type="hidden" name="busId" id="busId" value="${obj.busId}"></input>
	<input type="hidden" name="dreamformId" id="dreamformId" value="${obj.dreamformId}"></input>
	<input type="hidden" name="taskNo" id="taskNo" value="${obj.taskNo}"></input>
	<input type="hidden" name="uuid" id="uuid" value="${obj.uuid }"></input>
		<!-- 用户基本信息 -->
		${obj.dreamForm.dreamformContent}
		
		<!-- 操作工具栏  -->
		<c:if test="${obj.bookType == 2}">
		<div style="width:100%;margin-top:10px" align="center">
		<iframe src="${ctx}/file/getUploadList?busId=${obj.busId}&uuid=${obj.uuid}&taskNo=${obj.taskNo}&op=${obj.op}" width="95%"></iframe>
		</div>
		</c:if>
		<div style="text-align: center;margin-top: 10px;padding-bottom: 10px;">
				<c:if test="${obj.op != 'view'}">
				<input type="submit" class="button"  id="button" onclick="pinjie()" value="保存" />
				</c:if>
				<input type="button" class="button" value="关  闭" onclick="window.close()"/>
		</div>
</form>
</body>
<script>
//页面初始化
$(document).ready(function(){
	//$("#container11").html("fsdfsfsfdsf");
	//ue.setContent('hello');
	//alert(ue11.getContent());
	if('${obj.op}' != 'view'){
		 //初始化日期
		 //$("input[dmplugin='dream-text']").each(function(){    
	     //日期类型    
	     //   if($(this).attr("dmctrtype") == 'date'){
	     //    $(this).click(function(){
	     //         var dateFmtValue = "yyyy-MM-dd";
	     //         if($(this).attr("dmdatefmt") != null){
	     //        	 dateFmtValue = $(this).attr("dmdatefmt");
	     //         }
	     //         WdatePicker({dateFmt:dateFmtValue});
	     //         });
	     //     }
	     //  });
	   
	     //初始化字典 
		 $(":input[dmplugin='dream-select']").each(function(){     
	         	//字典类型    
		        if($(this).attr("dmdicId") != null){
			 		fillDic($(this),$(this).attr("dmdicId"));
		        }
	     }); 

	     /*
	     //初始化多选字典项字典
		 $(":input[dmplugin='dream-multiple']").each(function(){     
	         	//字典类型    
		        if($(this).attr("dmdicId") != null){
		        	listDic($(this),$(this).attr("dmdicId"));
		        }
	     });
	     */	     

	     //初始化文本框
		 $(":input[dmplugin='dream-textarea']").each(function(){     
	         	//字典类型    
		        if($(this).attr("dmctrtype") == 'editor'){
			 		$(this).addClass("editor");
		        }
	     }); 
	     //初始化自动填充数据
         $('[dmsysparameter]').each(function(){
             var obj = $(this);
             var attrStr = $(obj).attr("dmsysparameter");
             if (attrStr == 'NOWDATE'){
                 <%
                 	Date date = new Date();
                 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 	String dateStr = sdf.format(date);
                 %>
                 $(obj).val("<%=dateStr%>");
             } else {
            	 $.ajax({
           			type:"post",
           			url:"config/form/getSessionValue",
           			async: false,
           			data:{"name":attrStr},
           			success:function(data){
               			data = eval("(" + data + ")");
               			$(obj).val(data.toString());
           			}
            	});
             }
         });
	    if('${obj.busId}' == '' && '${obj.personId}' != ''){
		    getPersonInitData('${obj.personId}');
	    } 
		//数据初始化
		if('${obj.busId}' != ''){
		 	getInitData('${obj.busId}','${obj.dreamformId}');
		}
		
		//初始化验证
		$(":input[dmplugin='dream-text']").each(function(){//文本框
			$(this).attr('datatype','*');
			var str = $(this).parent().prev().html();
			var strSub=str.substring(0,str.length-1);
			$(this).attr('nullmsg','请输入'+ strSub);
		});
		
		$("textarea[dmplugin='dream-textarea']").each(function(){//大文本框
			$(this).attr('datatype','*');
			var str = $(this).parent().prev().html();
			var strSub=str.substring(0,str.length-1);
			$(this).attr('nullmsg','请输入'+ strSub);
		});
		
		$("input[dmplugin='dream-date']").each(function(){//时间
			$(this).attr('datatype','*');
			var str = $(this).parent().prev().html();
			var strSub=str.substring(0,str.length-1);
			$(this).attr('nullmsg','请输入'+ strSub);
		});
		
		$("#queryForm").Validform({
			btnSubmit:"#button",
			callback:function(form){
				if(confirm("确定要保存吗？")){
					form[0].submit();
				}
				return false;
			}
		});
	}else{
		 //初始化字典 
		 $(":input[dmplugin='dream-select']").each(function(){    
	         	//字典类型    
		        if($(this).attr("dmdicId") != null){  
			 		fillDic($(this),$(this).attr("dmdicId"));
		        }
	     });
		 
	     /*
	     //初始化多选字典项字典
		 $(":input[dmplugin='dream-multiple']").each(function(){     
	         	//字典类型    
		        if($(this).attr("dmdicId") != null){
		        	listDic($(this),$(this).attr("dmdicId"));
		        }
	     });
	     */
	     
		getInitData2view('${obj.busId}','${obj.dreamformId}');
		$("#saveBtn").parent().remove();
	}
	
});


//列出字典项
function listDic(obj,dicId){
	$.post(
		"${ctx}/config/form/getDic?dicId="+dicId,
		{Action:"post"},
		function(data,textStatus){
			$.each(eval(data),function(index,item){
				//var ddd = obj;
				//alert(obj[0].name);
				obj.after("<input type='checkbox' name='"+obj[0].name+"' value='"+ item.dicKey+"' dmneedwrite='true' dmplugin='dream-multiple' dmdicid='"+dicId+"'/>" +""+ item.dicValue+"");
				//return false
			});
			obj.hide();
			obj.attr("disabled",true);
		},
		"json"
	);
}

//填充字典
function fillDic(obj,dicId) {
	$.post("${ctx}/config/form/getDic?dicId="+dicId, 
            { Action: "post"},
            function (data, textStatus){
            	$.each(eval(data),function(index,item){
       			 obj.append("<option value='"+item.dicKey+"'>"+item.dicValue+"</option>");
            	});
             }
            , "json");  
}

//初始化数据
function getInitData(busId,dreamformId) {
	$.post("${ctx}/config/form/getInitData?busId="+busId+"&dreamformId="+dreamformId, 
            { Action: "post"},
            function (data, textStatus){
            	var result = eval(data);
            	 $("input[dmplugin='dream-text']").each(function(){    
                	 //alert( $(this).attr("id"));
            		 //fillDic($(this),$(this).attr("name"));
            		 $(this).val(result[0][$(this).attr("name").toUpperCase()]);
                 });
            	 $("select[dmplugin='dream-select']").each(function(){
            		 if($(this).attr("dmdicId") != null){ 
            		 	$(this).val(result[0][$(this).attr("name").toUpperCase()]);
            		 }
                 }); 
            	 $("textarea[dmplugin='dream-textarea']").each(function(){    
            		 $(this).val(result[0][$(this).attr("name").toUpperCase()]);
                 });
            	 $("input[dmplugin='dream-date']").each(function(){    
            		 $(this).val(result[0][$(this).attr("name").toUpperCase()]);
                 });
            	 
            	 $("input[type='checkbox']").each(function(){
            		var resultList = new Array();
            		var checkIds = result[0][$(this).attr("name").toUpperCase()];
            		if(checkIds != null && checkIds != ''){
            			resultList = checkIds.split(',');
            			for (var i=0;i<resultList.length;i++){
             				var idValue = resultList[i];
             				if(idValue == $(this).val()){
             					$(this).attr('checked', true);
             				}
             			}
            		}
                 });
            	 
                 //初始化子表数据
                 if (!jQuery.isEmptyObject(result[0].childList)){
                	 for (var i=0;i<result[0].childList.length;i++){
                         if (i==0){
                        	 $('#subTable').find("input[dmplugin='dream-text']").each(function(){    
                            	 $(this).val((result[0].childList[0])[$(this).attr("name").toUpperCase()]);
                             });
                        	 $('#subTable').find("textarea[dmplugin='dream-textarea']").each(function(){ 
                        		 $(this).val((result[0].childList[0])[$(this).attr("name").toUpperCase()]);   
                             });
                        	 $('#subTable').find("select[dmplugin='dream-select']").each(function(){    
                        		 if($(this).attr("dmdicId") != null){ 
                         		 	$(this).val((result[0].childList[0])[$(this).attr("name").toUpperCase()]);
                         		 }
                             }); 
                        	 $('#subTable').find("input[dmplugin='dream-date']").each(function(){    
                        		 $(this).val((result[0].childList[0])[$(this).attr("name").toUpperCase()]);
                             });
                         } else {
                             var cloneTr = $("#subTable tr:eq(1)").clone();
                             $(cloneTr).find("input[dmplugin='dream-text']").each(function(){    
                        			$(this).val((result[0].childList[i])[$(this).attr("name").toUpperCase()]);
                             });
                        	 $(cloneTr).find("textarea[dmplugin='dream-textarea']").each(function(){  
                        		 $(this).val((result[0].childList[i])[$(this).attr("name").toUpperCase()]);
                             });
                        	 $(cloneTr).find("select[dmplugin='dream-select']").each(function(){    
                        		 if($(this).attr("dmdicId") != null){ 
                          		 	$(this).val((result[0].childList[i])[$(this).attr("name").toUpperCase()]);
                          		 }
                             }); 
                        	 $('#subTable').find("input[dmplugin='dream-date']").each(function(){    
                        		 $(this).val((result[0].childList[i])[$(this).attr("name").toUpperCase()]);
                             });
                             $(cloneTr).appendTo($("#subTable"));
                         }
                     }
                 }
                
             }
            , "json");  
}
//初始化基础人口信息
function getPersonInitData(personId){
	$.post("${ctx}/config/form/getPersonInitData?personId="+personId,
			{Action:"post"},
			function (data,textStatus){
				var result = eval(data);
				$("[dmbase]").each(function(){
					var attr = $(this).attr("dmbase");
					if($(this).attr('dmplugin')=='dream-date'){
						$(this).val(StringToDate(result[attr]).Format($(this).attr("dmdatefmt")));
					}else{
						$(this).val(result[attr]);
					}
					});
			}
			,"json");
}
//初始化数据，并设置产看页面
function getInitData2view(busId,dreamformId) {
	$.post("${ctx}/config/form/getInitData?busId="+busId+"&dreamformId="+dreamformId, 
            { Action: "post"},
            function (data, textStatus){
            	var result = eval(data);
            	if (!jQuery.isEmptyObject(result[0].childList)){
	            	for (var j=0;j<result[0].childList.length;j++){
		             	var cloneTr = $("#subTable tr:eq(1)").clone();
		                $(cloneTr).find("input[dmplugin='dream-text']").each(function(){ 
		                	//alert((result[0].childList[j])[$(this).attr("name").toUpperCase()]);
		                  	if((result[0].childList[j])[$(this).attr("name").toUpperCase()] == null){
		                  		$(this).parent().html("&nbsp;");
		              		 }else{
		                  	 	$(this).parent().html((result[0].childList[j])[$(this).attr("name").toUpperCase()]);
		              		} 
		                });
		              	$(cloneTr).find("textarea[dmplugin='dream-textarea']").each(function(){
		              		if((result[0].childList[j])[$(this).attr("name").toUpperCase()] == null){
		                  		$(this).parent().html("&nbsp;");
		              		}else{
		                  		$(this).parent().html((result[0].childList[j])[$(this).attr("name").toUpperCase()]);
		              		} 
		                });
		              	$(cloneTr).find("select[dmplugin='dream-select']").each(function(){  
		              		$(this).val((result[0].childList[j])[$(this).attr("name").toUpperCase()]);
		              		$(this).parent().html($(this).find("option:selected").text());
		                });
		              	$(cloneTr).find("input[dmplugin='dream-date']").each(function(){
		              	if((result[0].childList[j])[$(this).attr("name").toUpperCase()] == null){
								 $(this).parent().html("&nbsp;");
		            		 }else{
		                		 $(this).parent().html((result[0].childList[j])[$(this).attr("name").toUpperCase()]);
		            		 }
		                 }); 
		              
		                $(cloneTr).appendTo($("#subTable"));
	                 }
	                if (result[0].childList.length>0){
	                 	$("#subTable tr:eq(1)").remove();
	                 	$("#subTable").find("tr").each(function(){
	                     	$(this).find("td:last").remove();
	                 	});
	                }
            	}
            	 $("input[dmplugin='dream-text']").each(function(){    
            		 if(result[0][$(this).attr("name").toUpperCase()] == null){
                		 $(this).parent().html("&nbsp;");
            		 }else{
                		 $(this).parent().html(result[0][$(this).attr("name").toUpperCase()]);
            		 }
                 });
            	 $("textarea[dmplugin='dream-textarea']").each(function(){    
            		 if(result[0][$(this).attr("name").toUpperCase()] == null){
                		 $(this).parent().html("&nbsp;");
            		 }else{
                		 $(this).parent().html(result[0][$(this).attr("name").toUpperCase()]);
            		 }
                 });
            	 $("select[dmplugin='dream-select']").each(function(){    
            		 $(this).val(result[0][$(this).attr("name").toUpperCase()]);
            		 $(this).parent().html($(this).find("option:selected").text());
                 }); 
                 $("input[dmplugin='dream-date']").each(function(){
                	 if(result[0][$(this).attr("name").toUpperCase()] == null){
                		 $(this).parent().html("&nbsp;");
            		 }else{
                		 $(this).parent().html(result[0][$(this).attr("name").toUpperCase()]);
            		 }
                 });
                 
                 $("input[type='checkbox']").each(function(){
             		var resultList = new Array();
             		var checkIds = result[0][$(this).attr("name").toUpperCase()];
             		if(checkIds != null && checkIds != ''){
             			resultList = checkIds.split(',');
             			for (var i=0;i<resultList.length;i++){
              				var idValue = resultList[i];
              				if(idValue == $(this).val()){
              					$(this).attr('checked', true);
              				}
              					$(this).attr('disabled',true);
              			}
             		}
                  });
                 
                 $("input[type='button']").each(function(){
            		 $(this).parent().remove();
            	 }); 
             }
            , "json");  
}

//取得业务所有勾选字典项，并拼接
function pinjie(){
	var dicValue = "";
	$(":input[dmplugin='dream-multiple']").each(function(){
		if($(this).attr("checked")){
			dicValue += $(this).val() +","; 
		}
	});
	dicValue = dicValue.substring(0,dicValue.length-1);
	$(":input[dmplugin='dream-multiple']").each(function(){
		$(this).val(dicValue);
		//alert($(this).val());
	});
}

</script> 
<script>
function addtr(){
	var cloneTr = $("#subTable tr:eq(1)").clone();
	$(cloneTr).find("input[type!='button'],textarea").each(function(){
		$(this).val("");
	});
	$(cloneTr).find("select").each(function(){
		$(this).find("option:first").prop("selected",'selected');
	});
    $(cloneTr).appendTo($("#subTable"));
}

function deltr(ctr){   
    var len=$("#subTable tr").length;   
    if(len <= 2){   
        alert("请至少保留一行!");   
    }else{   
        $(ctr).parent().parent().remove();//button->td->tr
    }   
}    
$(function(){
	var $node = $('#watermark');  
	//if是判断是否ie,IE8及一下版本只能用滤镜实现透明,ie9、else火狐等用opacity属性  
	if(!document.all){  
		$node.addClass('no_ie_style');
	}  
});
</script>
<style type="text/css">
#watermark{
	width:100%;
	height:110%;
	top:-200px;
	left:-100px;
	transform: rotate(40deg);
	-o-transform: rotate(40deg);
	-webkit-transform: rotate(40deg);
	-moz-transform: rotate(40deg);
	filter: progid:DXImageTransform.Microsoft.Matrix(sizingMethod='auto expand',M11=0.7660444431189777, M12=-0.6427876096865394, M21=0.6427876096865398, M22=0.7660444431189779)";
	filter: alpha(opacity=35);
}
</style>
</html>

