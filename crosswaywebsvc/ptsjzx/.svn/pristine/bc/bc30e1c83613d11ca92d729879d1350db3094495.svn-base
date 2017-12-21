<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
	<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
</head>
<body>
	<div id="usual1" class="usual" style="margin-top:20px;"> 
 		<div class="itab">
	  		<ul>
	    		<li>
	    			<a href="#tab1" class="selected">法人详情</a>
	    		</li> 
	    		<li><a href="#tab2">法人资质信息</a></li> 
	    		<li><a href="#tab3">法人处罚信息</a></li> 
	    	</ul>
	    </div> 
    
  		<div id="tab1" class="tabson">
  			<iframe width="100%" height="600px" src="${ctx}/config/form/result?dreamformId=${obj.dreamformId}&op=view&busId=${obj.busId}"></iframe>
			
  		</div> 
    
  		<div id="tab2" class="tabson">
  			<iframe width="100%" height="600px" src="${ctx}/config/query/toResult?saveId=1460688173671&onekey=1&CORP_INFO_ID=${obj.busId}"></iframe>
  		</div> 
  		<div id="tab3">
  			<iframe width="100%" height="600px" id="iframe3"></iframe>
  		</div>
	</div>
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
</script>
</body>

</html>