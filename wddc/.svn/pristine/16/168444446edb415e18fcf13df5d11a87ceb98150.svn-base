<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${sys_title}</title>
		<%@ include file="/cj/meta.jsp" %>
		<!-- Loading Bootstrap -->
   		<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   		<link href="${ctx}/wddc/tiles/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
  	 	<!--self-->
   		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    	<!--font-awesome-->
    	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    	<!-- Loading jquery -->
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
	</head>
<body>
	<div class='container'>
	<h3 id="disable-responsive2" class="page-header" style="margin-top:0px;">统计结果
	<span  style="float: right;">
		<a href="#" id="message_tip" class="btn btn-warning"  onclick="count()" style="width: 100px">数据更新</a> 
	</span>
	</h3>
	<div class="row">
		<div class="col-md-12 form-group">
			统计名称：
			${obj.info.name }
		</div>
		<c:if test="${obj.result==null }">
			<div class="col-md-12 form-group">
				当前日期没有进行统计！
			</div>
		</c:if>
	  <div class="col-md-12 form-group">
	  <p style="float:left;margin-top:5px">选择日期：</p> 
	   <div class='col-sm-3'>  
          <div class="form-group">   
            <!--指定 date标记-->  
            <div class='input-group date' id='datetimepicker'>  
                <input type='text' class="form-control" value="${obj.nowDate }" onchange="changeTheme('${obj.info.id}',this.value);"/>  
                <span class="input-group-addon">  
                    <span class="glyphicon glyphicon-calendar"></span>  
                </span>  
            </div>  
          </div>  
        </div>
        <p style="float:left;margin-top:5px;font-size:16px;">${obj.hms }</p>   
        </div>  
		<c:if test="${obj.result!=null }">
			<div class="col-md-12 form-group">
				统计结果：<br>
				<table class="display table table-bordered table-striped"  width="80%" cellpadding="3px" cellspacing="1" align="center" style="border-collapse:separate; border-spacing:0px 10px;">
					<c:forEach items="${obj.result.result }" var="re">
						<tr>
							<c:forEach var="item" items="${re}"> 
								<td>&nbsp;${item.key }:${item.value }&nbsp;</td>  
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
	</div>
	<p align="center">
       	<button type="button" class="btn btn-warning" onclick="window.close()" style="width: 100px">关闭</button>
	</p>
	
	
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
<script type="text/javascript">
$('#datetimepicker').datetimepicker({  
    format: 'yyyy-mm-dd',  
    language: 'zh-CN',
    startDate: '2016-01-31',
    endDate : new Date(),
    minView: "month",
    autoclose:true
  });  
//修改时间
function changeTheme(id,select){
	window.location.href="${ctx}/suite/data/mult/view?id="+id+"&countTime="+select;
}
//统计
function count(){
	$('#myModal').modal({
		backdrop: 'static', keyboard: false
    });
	$.post("${ctx}/suite/data/mult/countById",{
		"id":"${obj.info.id}"
	},function(){
		window.location.href="${ctx}/suite/data/mult/view?id=${obj.info.id}";
	});
}
</script>
</html>