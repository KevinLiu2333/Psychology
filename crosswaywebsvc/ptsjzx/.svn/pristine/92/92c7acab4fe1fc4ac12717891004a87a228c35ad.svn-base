<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set scope="request"  var="pageForm" value="queryForm" />
<head>
	<jsp:include page="/common/meta.jsp"/>
	<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<title>数据预览</title>
</head>
<body>
<div id="usual1" class="usual" style="margin-top:20px;"> 
 		${obj.html }
</div> 
</body>
<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
      
      function savePerson(){
    	  $('#personForm').submit();
      }
      
      function fanhui(){
    	  $('#personForm').attr('action','personList');
    	  $('#personForm').submit();
      }
      $.post("${ctx}/cx/getCorpOrgCode",{
    	  corpid : "${obj.busId}"
      },function(re){
    	  re=eval('('+re+')');
    	  $('#iframe3').attr("src","${ctx}/config/query/toResult?saveId=1460697837629&onekey=1&ORGAN_CODE="+re.result);
      });
      
      function view(id,sheetid){
      	var href="${ctx}/sjtb/viewHistory?type=${obj.type}&id="+id+"&sheetid="+sheetid;
      	window.showModalDialog( href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");	
      }
</script>
</html>