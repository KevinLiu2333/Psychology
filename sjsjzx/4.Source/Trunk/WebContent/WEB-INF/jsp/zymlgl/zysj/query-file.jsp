<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base target="_self">
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/skins/css/form.css">
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link href="${ctx}/skins/blue/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/adapter/bar.js"></script>
<style type="text/css">
.itab{height:36px; border-bottom:solid 1px #d0dee5; position:relative; border-left:solid 1px #d3dbde;}
.itab ul li{float:left;height:37px; line-height:37px; background:url(${ctx}/skins/images/itabbg.png) repeat-x; border-right:solid 1px #d3dbde;}
.itab ul li a{font-size:14px; color:#000; padding-left:25px; padding-right:25px;}
.itab ul li a.selected{ height:37px; display:block; background:url(${ctx}/skins/images/itabbg1.png) repeat-x; font-weight:bold;}
 }
</style>
<script language="javascript" >
    var table="${obj.t_table}";
    var fileId="${obj.fileId}";
    var resourceId="${obj.resourceId}";
    var a='${fn:length(obj.resourceDetailsList)}';
    $.post("${ctx}/zy/templet/countquery?table="+table+"&fileId="+fileId+"&resourceId="+resourceId,
			{ Action: "post"},
			function(data, textStatus){
				data = eval('('+data+')');
				 //alert(data);
				var  dataFile=tobar_xAxisdata(data[0]);
				  //alert(dataFile);
				  var count=new Array(dataFile.length);

					for(var j=6;j<dataFile.length;j++)
					{
						count[j]=dataFile[j];
						$("#th").append('<th class="label_1" style="text-align:center;">'+count[j]+'</th>');
					}

					//大循环
					var str;
					for(var p=0;p<data.length;p++)
					{
					 str += '<tr>';
					var b =tobar_data(data[p]);
				
					var count1=new Array(b.length);
					
					for(var i=6;i<b.length;i++){
						
						count1[i]=b[i];
						str += '<td align="center"  class="fontClass">'+count1[i]+'</td>';
					}
					str += '</tr>';
					}
					
					$("#test").append(str);
					
			}
		);
</script>

</head>
<body>
<div style="text-align: center;margin-top:10px;font-size:20px;">数据预览</div>
<div class="pageHeader" style="width:96%; text-align:center; margin-top:20px;">
	 <table width="96%"  id="starlist" class="table_multilist" style="margin-left:25px;">
	    	    <thead style="background-color: silver;" id="th">
	           		
	           	</thead>
	           	<tbody id="test"> 
	           	
	           	</tbody>
	  </table>
	  </div>
	<div style="padding-left:41.5%;padding-top:20px;">
		<button type="button" class="midButton close" onclick="window.close()">关  闭</button>
	</div>
</body>