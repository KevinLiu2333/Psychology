<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/md5.js"></script>
<script type="text/javascript" src="${ctx }/tiles/scripts/dateUtils.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form1.css">
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx }/tiles/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/tiles/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<link href="${ctx}/tiles/Validform/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/skins/result/style.css" rel="stylesheet" type="text/css"/>
<meta name="viewport" content="initial-scale=1, maximum-scale=1,user-scalable=no"/>
<style type="text/css">
#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;background-color:rgb(244,242,239);}

.tag li {
    width:auto;
    padding:0px 0px 0px 0px;
}
</style>
</head>
<body>
<table width="80px" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
    <td><ul class="tag">
        <li id="one1" onclick="setTab('one',1,4)"><a href="javascript:void(0)">1111111111</a></li>
        <li id="one2" onclick="setTab('one',2,4)"><a href="javascript:void(0)">2222222222</a></li>
        <li id="one3" onclick="setTab('one',3,4)"><a href="javascript:void(0)">33333333</a></li>
        <li id="one4" onclick="setTab('one',4,4)"><a href="javascript:void(0)">44444444</a></li>
       
    </ul></td>
  </tr>
   <tr>
    <td valign="top" id="info_list">
    	<div id="con_one_1" style="display:none;"><iframe width="100%" height="300px" src="${ctx}/querySGS/toCorpCfList?corpinfoid=${obj.id}"></iframe></div>
    	<div id="con_one_2" style="display:none;"><iframe width="60%" height="300px" src="${ctx}/querySGS/toCorpCfList?corpinfoid=${obj.id}"></iframe></div>
   		<div id="con_one_3" style="display:none;"><iframe width="60%" height="300px" src="${ctx}/querySGS/toCorpCfList?corpinfoid=${obj.id}"></iframe></div>
   		<div id="con_one_4" style="display:none;"><iframe width="60%" height="300px" src="${ctx}/querySGS/toCorpCfList?corpinfoid=${obj.id}"></iframe></div>
    </td>
   </tr>
</table>

</body>
<script type="text/javascript"> 

function setTab(name,cursel,n){
    for(var i=1;i<=n;i++){
     var menu=document.getElementById(name+i);
     var con=document.getElementById("con_"+name+"_"+i);
     menu.className=i==cursel?"active":"";
     con.style.display=i==cursel?"block":"none";
	};
   }
      $("#usual1 ul").idTabs(); 

$(document).keydown(function (event) {
    if (event.keyCode == 27) {
    	window.close();
    }
});
$(document).ready(function(){
	  var lyxx='${obj.build}';
	  var xyxx='${obj.xyxx}';
	 if(lyxx=='0'){
		 $("#one6").hide();
	 }
	 if(xyxx=='0'){
		 $("#one5").hide();
	 }
	 if('${obj.wssp}'=='0'){
		 $("#one7").hide();
	 };
});
</script>
</html>