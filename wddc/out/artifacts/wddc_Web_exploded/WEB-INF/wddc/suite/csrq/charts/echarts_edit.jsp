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
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	
	<!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wdsp.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
</head>
<body>
<div class="body">
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container"  style="position:relative; width:70%; margin:0px auto">
    <div class="row">
			<form action="${ctx }/suite/chart/updateOrSaveEchart" method="post" class="form">
				<input type="hidden" name="echart.id" value="${obj.echart.id}" />
				<div class="col-md-12">
	            <h3 class="page-header">基于服务的chart配置</h3>
	            <p class="page-header"><label>获取服务数据的参数  ：</label></p>
	            <div class= "row">
	            	<div class="col-md-12 form-group"> 
				       <table class="table" style="margin:0 auto; padding:0px; border-spacing:0px; border-style:none; border-collapse:collapse;">
	            			<tr>
			            		<td width="50%">1. 请选择统计项参数   </td>
			            		<td width="50%">
							        <select   multiple="" id="fwDataList" name="echart.fwDataParameter" class="select2"  data-placeholder="请选择统计数据名称...">
						
							        </select>
							  	</td>
	            			</tr>
	            		</table> 
    				</div>
		            
	            </div>
	            <!-- option配置 -->
	            <label class="lable active-blue"  >标签信息及Option配置信息  ：</label>
	            
	            <div class= "row">
	            	<div class="col-md-12 form-group">
	            		<table class="table" style="margin:0 auto; padding:0px; border-spacing:0px; border-style:none; border-collapse:collapse;">
	            			<tr>
			            		<td width="50%">1. 图表打标签  </td>
			            		<td width="50%">
			            			<div class="row">
									  	<div class="col-md-12 form-group">
									        <select   multiple="" id="tagList" name="echart.tagList" class="select2" data-placeholder="请选择标签...">
								
									        </select>
								    	</div>
								  	</div>
							  	</td>
	            			</tr>
	            		</table>
	           			
	            		<table class="table" style="margin:0 auto; padding:0px; border-spacing:0px; border-style:none; border-collapse:collapse;">
	            			<tr>
			            		<td width="50%">2. 图表的标题 </td>
			            		<td ><input class="form-control" style="display:inline;width : 60% " name="echart.title" type="text" value="${obj.echart.title }"/></td>
	            			</tr>
	            		</table>
	            		
	           		
	            		<table class="table" style="margin:0 auto; padding:0px; border-spacing:0px; border-style:none; border-collapse:collapse;">
	            			<tr>
			            		<td width="50%">3. 图表的类型  </td>
			            		<td width="50%"></td>
		            		</tr>
	            		</table>
	            		
		        	</div>
		        	
	            	<div class="col-xs-3 col-sm-3">
						<div class="control-group" >
							<div class="radio">
								<label>
									<input name="echart.echartType" type="radio" class="ace" value="scatter" <c:if test="${obj.echart.echartType eq 'scatter' }"> checked</c:if>/>
									
									<span class="lbl"> 散点图</span>
								</label>
							</div>
		
							<div class="radio">
								<label>
									<input name="echart.echartType" type="radio" class="ace" value="line" <c:if test="${obj.echart.echartType eq 'line' }"> checked</c:if>/>
									<span class="lbl"> 折线图</span>
								</label>
							</div>
							
							<div class="radio">
								<label>
									<input name="echart.echartType" type="radio" class="ace" value="bar" <c:if test="${obj.echart.echartType eq 'bar' }"> checked</c:if>/>
									<span class="lbl"> 柱状图</span>
								</label>
							</div>
		
							<div class="radio">
								<label>
									<input name="echart.echartType" type="radio" class="ace" value="pie" <c:if test="${obj.echart.echartType eq 'pie' }"> checked</c:if>/>
									<span class="lbl"> 饼图</span>
								</label>
							</div>
		
							<div class="radio">
								<label>
									<input name="echart.echartType" type="radio" class="ace"  value="gauge" <c:if test="${obj.echart.echartType eq 'gauge' }"> checked</c:if>/>
									<span class="lbl "> 仪表盘</span>
								</label>
							</div>
							
							<div class="radio">
								<label>
									<input name="echart.echartType" type="radio" class="ace"  value="funnel" <c:if test="${obj.echart.echartType eq 'funnel' }"> checked</c:if>/>
									<span class="lbl "> 漏斗图</span>
								</label>
							</div>
						</div>
					</div>
		        	
	           	</div>
	            <p class="page-header">
	            	<a style="text-decoration:none;" href ="http://echarts.baidu.com/examples.html">4. 右键点击链接，获取需要的Option配置，改写并输入到下面的文本框中.</a>
	            </p>
	            <div class="widget-body">
					<div class="widget-main">
						<div>
							<textarea  name="echart.optionConfig" class="form-control" id="form-field-8" placeholder="" rows="50">${obj.echart.optionConfig }</textarea>
								
						</div>
					</div>
				</div>
	            
	            <p class="page-header">
	            	5. 请填写关于图表的描述
	            </p>
	            <div class="widget-body">
					<div class="widget-main">
						<div>
							<textarea  name="echart.description" class="form-control" id="form-field-8"  rows="1">${obj.echart.description }</textarea>
						</div>
					</div>
				</div>
	             <hr/>
	            <div style="margin:0 auto;width:150px;">
		           <button class="btn btn-warning" type="reset">重置</button>
		           <button class="btn btn-warning" type="submit">提交</button>
	        	</div> 
	            
	        </div>
			
		</form>
        
	</div>
</div>
<jsp:include page="/cj/foot.jsp"/>
</div>

<script type="text/javascript">

	//在按钮之后添加新内容，需要修改
	function addWhereCode(){
		$("#whereButton").after("<hr>"+$("#baseWhereCode").html());
	}
	//删除某个服务数据查询参数
	function toDeletQueryFwConfig(queryFwConfigId,id){
		window.location.href="${ctx }/suite/chart/toDeleteQueryFwConfig?queryFwConfigId="+queryFwConfigId+"&id="+id;
	}
    //给选择框加样式    
    jQuery(function($){
       $('.select2').css('width','290px').select2({allowClear:true});
       $('.select2').addClass('tag-input-style');

       
     //获取所有数据填报的数据资源信息
     $.ajax({
         type:"post",
         async:false,
         url:"${ctx}/suite/data/term/getAllStatterm",
         success:function(data){
             for(var term in data){
                 //alert(data[term]);
            	 $("#fwDataList").append("<option value='"+term+"' >"+data[term]+"</option>");
             }
         } 
     });
     
     //获取所有数据资源tag信息
     $.ajax({
         type:"post",
         async:false,
         url:"${ctx}/kernel/tag/tagList?catalog=13",
         success:function(data){
        	 for(var i=0;i<data.tagList.length;i++){
                 $("#tagList").append("<option value='"+data.tagList[i].showName+"'>"+data.tagList[i].showName+"</option>");
             }
         }
     });
     //填入选定的项（回填数据）
     var newCitys = new Array();
     var newParams = new Array(); 
     <c:forEach items="${obj.echart.tags }" var="tag">
     	newCitys.push("${tag }");
     </c:forEach>
     newParams.push('${obj.echart.fwDataParameter}');
     $('#tagList').val(newCitys).trigger("change");
     $('#fwDataList').val(newParams).trigger("change");

    });
 </script>
</body>
</html>
