<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<jsp:include page="/common/meta.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>法人信息比对</title>
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
			<div style="height: 10px">&nbsp;</div>
	<form id="queryForm" name="queryForm"
		action="${ctx}/query/toCorpcontrast" method="post" onsubmit="beforesubmit()">
		<input id="newquery" type="hidden" name="newquery" value="0" />
		<table style="width:90%; border:0; align:left; cellpadding:1; cellspacing:0;padding-left:70px;">
		
  		<tr>
    	<td valign="top">
      	<table width="60%" align="left" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
      
    		<tr>
          		<td width="60%" colspan="2" class="info_title_a" style="font-weight:bold;">比对依据</td>
          		
    		</tr>
    		<tr>
          		<td width="20%" style="background-color:#ffffff;text-align:center;"><wd:select id="dmlx" name="dmlx" dicCode="1068" 
          		className="list_search_select" style="width:94%;" defaultValue="${obj.dmlx }" initValue="请选择代码类型" onchange="changedmlx()"/></td>
          		<td width="*" bgcolor="#FFFFFF" align="left" style="padding-left:20px;"><input id="dmh" type="text" name="dmh" value="${obj.dmh }"  style="width:94%;background-color:#ffffff;
          		border:1px solid #002a86;padding:2px 3px;" /></td>
    		</tr>
    	</table>
    	<div>
    	<table >
    	
    		<input type="button" name="button" id="list_contrast_button" value=" " onclick="query(1)"/>
    	
    	</table>
    	</div>
    	<table width="100%" align="center" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC">
    		<tr>
    			<td colspan="2" class="info_title_a" style="font-weight:bold;padding-left:0px;">比对内容</td>
    			<td class="info_title_a" style="font-weight:bold;padding-left:0px;">比对结果</td>
    		</tr>
    		<tr>
          		<td width="20%" bgcolor="#FFFFFF" align="center">统一社会信用代码</td>
          		<td width="40%" class="info_title_b"><input id="tyshxydm" type="text" class="list_search_input" name="tyshxydm" value="${obj.tyshxydm }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td width="40%" bgcolor="#FFFFFF" align="center" style="font-size:15px;">
          		<c:if test="${obj.tyshxydm == null || obj.tyshxydm == 'NULL'}">${obj.tyshxydm}</c:if>
          		<c:if test="${( obj.tyshxydm != obj.map.uniscid ) && obj.tyshxydm != null && obj.tyshxydm != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.tyshxydm == obj.map.uniscid ) && obj.tyshxydm != null && obj.tyshxydm != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    		<tr>
          		<td bgcolor="#FFFFFF" align="center">组织机构代码</td>
          		<td class="info_title_b"><input id="zzjgdm" type="text" class="list_search_input" name="zzjgdm" value="${obj.zzjgdm }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td bgcolor="#FFFFFF" align="center" style="font-size:15px;">
				<c:if test="${obj.zzjgdm == null || obj.zzjgdm == 'NULL'}">${obj.zzjgdm}</c:if>
          		<c:if test="${( obj.zzjgdm != obj.map.organcode ) && obj.zzjgdm != null && obj.zzjgdm != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.zzjgdm == obj.map.organcode ) && obj.zzjgdm != null && obj.zzjgdm != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    		<tr>
          		<td bgcolor="#FFFFFF" align="center">营业执照注册号</td>
          		<td class="info_title_b"><input id="yyzzzch" type="text" class="list_search_input" name="yyzzzch" value="${obj.yyzzzch }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td bgcolor="#FFFFFF" align="center">
				<c:if test="${obj.yyzzzch == null || obj.yyzzzch == 'NULL'}">${obj.zzjgdm}</c:if>
          		<c:if test="${( obj.yyzzzch != obj.map.regno ) && obj.yyzzzch != null && obj.yyzzzch != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.yyzzzch == obj.map.regno ) && obj.yyzzzch != null && obj.yyzzzch != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    		<tr>
          		<td bgcolor="#FFFFFF" align="center">纳税人识别号</td>
          		<td class="info_title_b"><input id="nsrsbh" type="text" class="list_search_input" name="nsrsbh" value="${obj.nsrsbh }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td bgcolor="#FFFFFF" align="center">
          		<c:if test="${obj.nsrsbh == null || obj.nsrsbh == 'NULL'}">${obj.nsrsbh}</c:if>
          		<c:if test="${( obj.nsrsbh != obj.map.taxpayerscode ) && obj.nsrsbh != null && obj.nsrsbh != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.nsrsbh == obj.map.taxpayerscode ) && obj.nsrsbh != null && obj.nsrsbh != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    		<tr>
    			<td bgcolor="#FFFFFF" align="center">法人名称</td>
          		<td class="info_title_b"><input id="frmc" type="text" class="list_search_input" name="frmc" value="${obj.frmc }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td bgcolor="#FFFFFF" align="center">
          		<c:if test="${obj.frmc == null || obj.frmc == 'NULL'}">${obj.frmc}</c:if>
          		<c:if test="${( obj.frmc != obj.map.corpname ) && obj.frmc != null && obj.frmc != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.frmc == obj.map.corpname ) && obj.frmc != null && obj.frmc != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    		<tr>
          		<td bgcolor="#FFFFFF" align="center">法定代表人名称</td>
          		<td class="info_title_b"><input id="fddbrmc" type="text" class="list_search_input" name="fddbrmc" value="${obj.fddbrmc }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td bgcolor="#FFFFFF" align="center">
				<c:if test="${obj.fddbrmc == null || obj.fddbrmc == 'NULL'}">${obj.fddbrmc}</c:if>
          		<c:if test="${( obj.fddbrmc != obj.map.personname ) && obj.fddbrmc != null && obj.fddbrmc != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.fddbrmc == obj.map.personname ) && obj.fddbrmc != null && obj.fddbrmc != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    		<tr>
          		<td bgcolor="#FFFFFF" align="center">注册资金</td>
          		<td class="info_title_b"><input id="zczj" type="text" class="list_search_input" name="zczj" value="${obj.zczj }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td bgcolor="#FFFFFF" align="center">
				<c:if test="${obj.zczj == null || obj.zczj == 'NULL'}">${obj.zczj}</c:if>
          		<c:if test="${( obj.zczj != obj.map.regcapital ) && obj.zczj != null && obj.zczj != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.zczj == obj.map.regcapital ) && obj.zczj != null && obj.zczj != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    		<tr>
          		<td bgcolor="#FFFFFF" align="center">注册地址</td>
          		<td class="info_title_b"><input id="zcdz" type="text" class="list_search_input" name="zcdz" value="${obj.zcdz }" 
          		style="width:90%;background-color:#ffffff;border:1px solid #002a86;padding:2px 3px;" /></td>
          		<td bgcolor="#FFFFFF" align="center">
				<c:if test="${obj.zcdz == null || obj.zcdz == 'NULL'}">${obj.zcdz}</c:if>
          		<c:if test="${( obj.zcdz != obj.map.address ) && obj.zcdz != null && obj.zcdz != '' }"><span style="color:red;">信息不一致</span></c:if>
          		<c:if test="${( obj.zcdz == obj.map.address ) && obj.zcdz != null && obj.zcdz != '' }"><span style="color:green;">信息一致</span></c:if>
          		</td>
    		</tr>
    	</table></td>
  </tr>
</table>
	<div style="height: 10px">&nbsp;</div>
		 
	</form>
	
	
		 
	
	<div id="loading" class="loading">
		<img src="${ctx }/skins/images/loading.gif" />&nbsp;查询中...
	</div>
			</div>
		</div>
<script type="text/javascript">
function beforesubmit(){
	$('#loading').show();
}
function query(type) {
	if( $('#dmh').val()    == '' ){
			alert("请输入比对依据！！");
			return;
		}
	if( $('#dmlx').val()    == '' ){
		alert("请输入比对依据类型！！");
		return;
	}
	if( $('#dmh').val()    != '' && $('#dmlx').val()    != '' && $('#tyshxydm').val() == '' &&
		$('#zzjgdm').val() == '' && $('#yyzzzch').val() == '' && $('#nsrsbh').val()   == '' &&
		$('#frmc').val()   == '' && $('#fddbrmc').val() == '' && $('#zczj').val()     == '' &&
		$('#zcdz').val()   == ''
		){
		alert("请输入需要比对的内容！！");
			return;
		}
	$('#queryForm').submit();
}




function clearcondition(){
	$('#dmlx').val("");
	$('#dmh').val("");
	$('#frmc').val("");
	$('#fddbrmc').val("");
	$('#zcdz').val("");
	$('#zczj').val("");
}
</script>
</body>
</html>