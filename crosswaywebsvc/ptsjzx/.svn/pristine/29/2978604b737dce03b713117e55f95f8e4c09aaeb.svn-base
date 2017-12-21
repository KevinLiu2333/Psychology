<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自然人信息比对</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js"></script>
<link href="${ctx }/tiles/ligerUI/skins/Aqua/css/ligerui-dialog.css" rel="stylesheet" type="text/css" />  
<link href="${ctx }/skins/query/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/skins/css/form.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/My97DatePicker/WdatePicker.js"></script>
<script src="${ctx }/tiles/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
<script src="${ctx }/tiles/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script> 
<style type="text/css">
.loading {
	top: 250px;
	left: 350px;
	z-index: 10000;
	position: absolute;
	font-size: 30px;
	color: rgba(200, 0, 0, 0.5);
	filter: alpha(opacity = 80);
	opacity: 0.8;
	display: none;
}
#list_contrast_button {
	background:url(/ptsjzx/skins/query/images/list_contrast_button.png) no-repeat;
	width:79px;
	height:79px;
	border:none;
	cursor:pointer;
	}
</style>
</head>
<body>

	<div class="mian_box">
		<div class="main_01">
			<div class="h340" style="height:100%;">
	<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
  		<tr>
   	 		<td>&nbsp;</td>
  		</tr>
	</table>
	<form id="queryForm" name="queryForm"
		action="${ctx}/query/toPersoncontrast" method="post" onsubmit="beforesubmit()">
		<input id="newquery" type="hidden" name="newquery" value="0" />
		<table style="width:90%; border:0; align:left; cellpadding:1; cellspacing:0;padding-left:70px;">
		
  <tr>
    <td valign="top"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
      <table width="60%" align="left" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
      
    		<tr>
          		<td width="60%" colspan="2" class="info_title_a" style="font-weight:bold;">比对依据</td>
    		</tr>
    		<tr>
          		<td width="20%" style="background-color:#ffffff;text-align:center;">证件号码</td>
          		<td width="40%" bgcolor="#FFFFFF" align="left" style="padding-left:20px;"><input id="zjhm" type="text" name="zjhm" value="${obj.zjhm }"  style="width:90%;background-color:#ffffff;
          		border:1px solid #002a86;padding:2px 3px;font-size:18px;" /></td>
    		</tr>
    		<tr>
          		<td width="20%" style="background-color:#ffffff;text-align:center;">验证码</td>
          		<td width="40%" bgcolor="#FFFFFF" align="left" style="padding-left:20px;"><input id="yzm" type="text" name="" value=""  style="width:50%;background-color:#ffffff;
          		border:1px solid #002a86;padding:2px 3px;font-size:18px;" />
          		<img id="vimg"  title="点击更换" onclick="changeCode();" src="${ctx }/servlet/AuthImageServlet">	
          	</td>
    		</tr>
    	</table>
    	<div>
    	<table>
    	<input type="button" name="button" id="list_contrast_button" value=" " onclick="query(1)"/>
    	</table></div>
    	<table>
    		<tr>
    			<td height="20px">&nbsp;
    		</tr>
    	</table>
    	<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td colspan="2" class="info_title_a" style="font-weight:bold;">比对内容</td>
    			<td class="info_title_a" style="font-weight:bold;">比对结果</td>
    		</tr>
    		<tr>
          		<td width="20%" bgcolor="#FFFFFF" align="center">姓名</td>
          		<td width="40%" class="info_title_b"><input id="xm" type="text" class="list_search_input" name="xm" value="${obj.xm }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td width="40%" bgcolor="#FFFFFF" align="center">
          		<c:if test="${obj.xm == null || obj.xm == 'NULL'}">${obj.xm}</c:if>
          		<c:if test="${( obj.xm != obj.map.xm ) && obj.xm != null && obj.xm != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.xm == obj.map.xm ) && obj.xm != null && obj.xm != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    		<tr>
          		<td bgcolor="#FFFFFF" align="center">户籍地址</td>
          		<td class="info_title_b"><input id="hjdz" type="text" class="list_search_input" name="hjdz" value="${obj.hjdz }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td bgcolor="#FFFFFF" align="center">
				<c:if test="${obj.hjdz == null || obj.hjdz == 'NULL'}">${obj.hjdz}</c:if>
          		<c:if test="${( obj.hjdz != obj.map.huji ) && obj.hjdz != null && obj.hjdz != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.hjdz == obj.map.huji ) && obj.hjdz != null && obj.hjdz != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    		<tr>
          		<td bgcolor="#FFFFFF" align="center">居住地址</td>
          		<td class="info_title_b"><input id="jzdz" type="text" class="list_search_input" name="jzdz" value="${obj.jzdz }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td bgcolor="#FFFFFF" align="center">
				<c:if test="${obj.jzdz == null || obj.jzdz == 'NULL'}">${obj.jzdz}</c:if>
          		<c:if test="${( obj.jzdz != obj.map.juzhu ) && obj.jzdz != null && obj.jzdz != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.jzdz == obj.map.juzhu ) && obj.jzdz != null && obj.jzdz != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    	</table>
      <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table></td>
  </tr>
</table>
	<div style="height: 10px">&nbsp;</div>
		 
	</form>
	
	
		<table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
		
  <tr>
    <td valign="top" ><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
</table>
	<div style="height: 10px">&nbsp;</div>
		 
	
	<div id="loading" class="loading">
		<img src="${ctx }/skins/images/loading.gif" />&nbsp;查询中...
	</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
function beforesubmit(){
	$('#loading').show();
}
function query(type) {
	$.post("${ctx}/query/checkcode",{
		code:$('#yzm').val()
	},function(data){
		if(data=='1'){
			if( $('#zjhm').val()    == '' ){
				alert("请输入证件号码！！");
				return;
			}
			if( $('#zjhm').val()  != '' && $('#hjdz').val()    == '' && $('#xm').val() == '' &&
			$('#jzdz').val()  == '' 
			){
			alert("请输入需要比对的内容！！");
				return;
			}
			$('#queryForm').submit();
		}else{
			alert('验证码错误！');
		}
	});
}




function clearcondition(){
	$('#dmlx').val("");
	$('#dmh').val("");
	$('#frmc').val("");
	$('#fddbrmc').val("");
	$('#zcdz').val("");
	$('#zczj').val("");
}
function changeCode() { 
	var imgNode = document.getElementById("vimg");    
	imgNode.src = "${ctx}/servlet/AuthImageServlet?t=" + Math.random();  // 防止浏览器缓存的
}
</script>
</body>
</html>