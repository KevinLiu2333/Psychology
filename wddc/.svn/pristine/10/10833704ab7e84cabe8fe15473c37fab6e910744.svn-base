<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    <!--chosen-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>

</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
	<input type="hidden" id="js_ctx" value="${ctx }" />
<div class='container'>
    <form method="post" id="publishForm" action="${ctx}/suite/log/saveConfig">
        <input type="hidden" name="config.logConfigId" value="${obj.config.logConfigId}">
    <h4  class="page-header"><b>操作日志配置</b></h4>
  <div class="row">
   <div class="col-md-12 form-group">
        配置名：<input type="text" name="config.logConfigName" style="width:400px" value="${obj.config.logConfigName}"/>
    </div>
   <div class="col-md-12 form-group">
        类路径：
        <select id="methodPath" name="config.methodPath" style="width:400px;height: 30px" onchange="updateMethod('')" ></select>
    </div>
    <div class="col-md-12 form-group">
        方法名： <select id="methodName" name="config.methodName" style="width:400px;height: 30px"></select>
    </div>
  <div class="col-md-12 form-group">
         大分类：
         <select id="catalog" name="config.catalog" style="width:400px;height: 30px"></select>
    </div>
    <div class="col-md-12 form-group">
         小分类：
         <select id="logType" name="config.logType" style="width:400px;height: 30px"></select>
    </div>
    <div class="col-md-12 form-group">
        推消息：
         <select id="isSms" name="config.isSms" style="width:400px;height: 30px"></select>
    </div>
  
</div>
  
</form>
    <p align="center">
        <button type="button" class="btn btn-warning" onclick="save()">保  存</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/suite/log/configList'">返回</button>
    </p>

</div>
</body>
<script type="text/javascript">
    function save() {
        $("#publishForm").submit();
    }
   

    jQuery(function($){

		$('#isSms').jsondic({dicid:'2005',initvalue:"--请选择--",defaultvalue:"${obj.config.isSms}"});
		
		$('#catalog').jsondic({
			dicid:"2006",
			initvalue:"---请选择---",
			defaultvalue:'${obj.config.catalog}',
			subdisplay:true,	
			subselect:[{id:'logType',initvalue:"---请选择---",defaultvalue:'${obj.config.logType}'}]
		});

		
    	 $('#methodPath').dicajaxselect({
				url:"${ctx}/suite/log/getClassDic",
				initvalue:'---请选择---',
				initoptvalue:'',
				defaultvalue:"${obj.config.methodPath}",
				callback:function(data,options){//data 返回的字典  options 配置参数
					//do something
				}
		});
		
    	 updateMethod('${obj.config.methodPath}');
    });

    function updateMethod(className){
    	 $('#methodName').html("");
    	 if(className == '' ){
    		 className = $('#methodPath').val();
         }
    	 $('#methodName').dicajaxselect({
				url:"${ctx}/suite/log/getMethodDic?className="+className,
				initvalue:'---请选择---',
				initoptvalue:'',
				defaultvalue:"${obj.config.methodName}",
				callback:function(data,options){//data 返回的字典  options 配置参数
					//do something
				}
		});
    }
   
 </script>
</html>
