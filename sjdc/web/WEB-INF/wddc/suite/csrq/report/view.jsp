<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${sys_title}</title>
<%@ include file="/cj/meta.jsp"%>
<!-- Loading Bootstrap -->
<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/wddc/tiles/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<!--self-->
<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css" />
<!--font-awesome-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css" />
<link href="${ctx}/wddc/tiles/easydialog/easydialog.css"
	rel="stylesheet">
<!-- Loading jquery -->
<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css"
	rel="stylesheet" />
<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>



</head>
<body>

	
	<input id="reportId" type="hidden" value="${obj.info.id }"/>
	<input id="upDateTime" type="hidden" value="${obj.upDateTime}" />
	<div class='container'>
	<h2 id="disable-responsive2" class="page-header">${obj.info.name }
	<span style="float: right;">
		<button id="changeButton" class="btn btn-warning  " onclick="refreshReport('${obj.info.id }')">更新报表</button> 
	</span>
	</h2>
		<div class="row">
			<!-- <div class="col-md-12 form-group">
				更新日期：&nbsp;<input size="10" id="form_date" type="text" value=""  class="form_datetime input-show-control"/>
				<span id="message" style="display:none;">请稍等。。。。。</span>
			</div> -->
			<div class="col-md-12 form-group">
	             <p style="float:left;margin-top:5px">选择日期：</p> 
	             <div class='col-sm-3'>  
                    <div class="form-group">   
                    <!--指定 date标记-->  
                        <div class='input-group date' id='datetimepicker'>  
                          <input type='text' class="form-control" value="${obj.upDateTime }"/>  
                         <span class="input-group-addon">  
                         <span class="glyphicon glyphicon-calendar"></span>  
                        </span>  
                       </div>  
                   </div>  
              </div>
               <p style="float:left;margin-top:5px;font-size:16px;">${obj.hms }</p>   
          </div> 
          <div  class="col-md-12 form-group ">
					<div class="alert alert-info" style="width:100%">
				       <strong>提示：</strong>报表采用缓存机制，如与预期不一致，请更新报表。
				       由于更新数据较慢，请稍等片刻 ！ 
				    </div>
				</div>
			<c:if test="${obj.result==null }">
				<div class="col-md-12 form-group">
					当前日期没有进行统计！
				</div>
			</c:if>
			
			<c:if test="${obj.result!=null }">
				<div class="col-md-12 form-group">
					${obj.result.result }
				</div>
			</c:if>
			<div class="col-md-12 form-group">${obj.html }</div>
		</div>
	</div>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					数据更新中。。。
				</h4>
			</div>
			<div class="modal-body">
				<div class="progress progress-striped active">
					<div class="progress-bar progress-bar-success" role="progressbar"
						 aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
						 style="width: 80%;">
					</div>
				</div>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div> 
</body>
<!-- Bootstrap-datetimepicker -->


<script type="text/javascript" src="${ctx}/wddc/tiles/easydialog/easydialog.js"></script>
<script type="text/javascript">
$("#datetimepicker").datetimepicker({
	 format: 'yyyy-mm-dd',  
	 language: 'zh-CN',
	 startDate: '2016-01-31',
	 endDate : new Date(),
     minView: "month",
	 autoclose: true
}).on('changeDate',function(ev){ 
	var reportId =$("#reportId").val();
	var upDate = curentTime(ev.date);
		//ev.date.getFullYear().toString() + "-"+ (ev.date.getMonth()+01).toString()+ "-" + (ev.date.getDate()+01).toString();
	//alert(reportId);
	//alert(upDate);
	window.location.href = "${ctx}/suite/csrq/report/viewResult?id="+reportId+"&date="+upDate;
});

function curentTime(date)
{
	var now = date;

	var year = now.getFullYear();       //年
	var month = now.getMonth() + 1;     //月
	var day = now.getDate();            //日

	var hh = now.getHours();            //时
	var mm = now.getMinutes();          //分

	var clock = year + "-";

	if(month < 10)
		clock += "0";

	clock += month + "-";

	if(day < 10)
		clock += "0";

	clock += day + " ";
	return(clock);
}

function toSpecialReport(){
	window.location.href= "${ctx}/suite/csrq/report/specialViewResult1";
} 

function refreshReport(id){
	$('#myModal').modal({
		backdrop: 'static', keyboard: false
    });
	$.post("${ctx}/suite/csrq/report/refreshdata",{id:id},function(){
		window.location.href= "${ctx}/suite/csrq/report/viewResult?id="+id;
	});
}

</script>
</html>