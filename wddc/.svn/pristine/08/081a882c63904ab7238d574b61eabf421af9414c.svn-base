<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>

</head>
<script type="text/javascript">
function check(idJob){
	var href = "${ctx }/jh/jyjg?idJob="+idJob;
	var returnValue = window.showModalDialog( href,'window',"dialogHeight=1200px;dialogWidth=1200px;center=yes");
	 if (returnValue==1){
		 query();
	 }
}
</script>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">
		<div class="col-md-12">
			<form action="">
			<div align="right" style="margin-right: 20px">
			</div>
			<div class="content">
				<div class="panel-body">
					<div class="adv-table">
						<div class="col-md-6">
							<h2 class="page-header" style="margin-top: 5px">数据交换入</h2>
							<table  class="display table table-bordered table-striped" id="dynamic-table">
								<thead>
					              <tr>
					                <th>序号</th>
					                <th>委办局名称</th>
					                <th>交换入数据量</th>
					              </tr>
	           					</thead>
					            <tbody>
					              	<tr>
					              		<td>1</td>
					              		<td>房地局</td>
					              		<td>59324</td>
					              	</tr>
					              	<tr>
					              		<td>2</td>
					              		<td>民政局</td>
					              		<td>190482</td>
					              	</tr>
					              	<tr>
					              		<td>3</td>
					              		<td>司法局</td>
					              		<td>74021</td>
					              	</tr>
					            </tbody>
      						</table>
      						<div align="center">
      							<img src="${ctx }/wdac/image/dateIn.png"/>
      						</div>
						</div>
						<div class="col-md-6">
							<h2 class="page-header" style="margin-top: 5px">数据交换出</h2>
							<table  class="display table table-bordered table-striped" id="dynamic-table">
								<thead>
					              <tr>
					                <th>序号</th>
					                <th>委办局名称</th>
					                <th>交换出数据量</th>
					              </tr>
	           					</thead>
					            <tbody>
					              	<tr>
					              		<td>1</td>
					              		<td>房地局</td>
					              		<td>46127</td>
					              	</tr>
					              	<tr>
					              		<td>2</td>
					              		<td>民政局</td>
					              		<td>59031</td>
					              	</tr>
					              	<tr>
					              		<td>3</td>
					              		<td>司法局</td>
					              		<td>35127</td>
					              	</tr>
					            </tbody>
      						</table>
      						<div align="center">
     								<img src="${ctx }/wdac/image/dateOut.png"/>
     							</div>
						</div>
   					</div>
   				</div>
   			</div>
      		</form>
      		</div>
	</div>
<jsp:include page="/cj/foot.jsp"/>
</body>
</html>