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
<style type="text/css">
.tag li {
    width:auto;
    padding:0px 5px 0px 5px;
}
</style>
</head>
<body >
<div>
<div style='left:5%'>
			<table border="0" cellpadding="" cellspacing="0">
			  <tr>
			    <td>&nbsp;</td>
			  </tr>
			</table>
				
				<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
				    <td width="405" class="info">
				    <div style="position:relative; z-index:1;">
				    <div style=" position:absolute;z-index:2;width: 100%;height: 100% ;background:url('${ctx}/watermark/${obj.userid}.png'); "> </div>
				    <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" >
				      <c:if test="${obj.person.syrklbdm!='03' }">
				      <tr>
				        <td width="23%" class="info_title">姓名：</td>
				        <td colspan="3">${obj.person.xm}</td>
				        </tr>
				      <tr>
				        <td class="info_title">性别： </td>
				        <td colspan="3"><wd:dicvalue dicId="1056" dicCode="${obj.person.xbdm}"/></td>
				        </tr>
				      <tr>
				        <td class="info_title">出生日期：</td>
				        <td colspan="3">${obj.person.csrq}</td>
				      </tr>
				      <tr>
				        <td class="info_title">婚姻状况： </td>
				        <td width="27%">${obj.person.hyzkhz}</td>
				        <td width="22%" class="info_title">民族：</td>
				        <td width="28%"><wd:dicvalue dicId="1055" dicCode="${obj.person.mzdm}"/></td>
				      </tr>
				      <tr>
				        <td valign="top" class="info_title">现住地址：</td>
				        <td colspan="3">${obj.person.jzdxzqhhz }${obj.person.jzdlmhz }${obj.person.jzdxz }</td>
				        </tr>
				      <tr>
				        <td valign="top" class="info_title">户籍地址：</td>
				        <td colspan="3">${obj.person.hjdxzqhhz }${obj.person.hjdlmhz }${obj.person.hjdxz }</td>
				        </tr>
				       </c:if>
				       <c:if test="${obj.person.syrklbdm=='03' }">
				       	<tr>
				        	<td width="23%" class="info_title">中文名：</td>
				       		<td colspan="3">${obj.person.xm}</td>
				        </tr>
				        <tr>
				        	<td width="23%" class="info_title">英文名：</td>
				       		<td colspan="3">${obj.person.ywm}&nbsp;${obj.person.ywx}</td>
				        </tr>
				        <tr>
				        	<td class="info_title">出生日期：</td>
				       	 <td colspan="3">${obj.person.csrq}</td>
				     	 </tr>
				        <tr>
				        	<td class="info_title">性别： </td>
				        	<td width="27%"><wd:dicvalue dicId="1056" dicCode="${obj.person.xbdm}"/></td>
				       		<td width="22%" class="info_title">国籍：</td>
				        	<td width="28%">${obj.person.gjhz}</td>
				     	 </tr>
				     	 <tr>
				        <td valign="top" class="info_title">现住地址：</td>
				        <td colspan="3">${obj.person.jzdxzqhhz }${obj.person.jzdlmhz }${obj.person.jzdxz }</td>
				        </tr>
				       </c:if>
				    </table>
				    </div>
				    </td>
				    <td width="*">&nbsp;</td>
				    <td width="369" align="center" class="map"><iframe width="100%" height="100%" src="${ctx }/query/gis/getPersonGis?rid=${obj.person.rid}"></iframe></td>
				  </tr>
				</table>
				<table width="20" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
				    <td>&nbsp;</td>
				  </tr>
				</table>
				</div>
</div>
<div style='width:100%;height:20px;overflow:hidden; left:250px'>
</div>
<div>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td><ul class="tag">
        <li id="one1" onclick="setTab('one',1,${fn:length(obj.deptlist)+6})" class="active"><a href="javascript:void(0)">基本信息</a></li>
        <li id="one2" onclick="setTab('one',2,${fn:length(obj.deptlist)+6})"><a href="javascript:void(0)">信用信息</a></li>
        <li id="one3" onclick="setTab('one',3,${fn:length(obj.deptlist)+6})"><a href="javascript:void(0)">社区事务</a></li>
        <li id="one4" onclick="setTab('one',4,${fn:length(obj.deptlist)+6})"><a href="javascript:void(0)">医疗救助</a></li>
        <li id="one5" onclick="setTab('one',5,${fn:length(obj.deptlist)+6})"><a href="javascript:void(0)">双公示许可</a></li>
        <li id="one6" onclick="setTab('one',6,${fn:length(obj.deptlist)+6})"><a href="javascript:void(0)">双公示处罚</a></li>
   		<c:forEach items="${obj.deptlist}" var="dept" varStatus="row">
   			<li id="one${row.index+7}" onclick="setTab('one',${row.index+7},${fn:length(obj.deptlist)+6})"><a href="javascript:void(0)"><wd:dicvalue dicId="1069" dicCode="${dept }"/></a></li>
   		</c:forEach>
    </ul></td>
  </tr>
  <tr>
    <td valign="top" id="info_list">
	
    <div id="con_one_1" style="position:absolute:" >
    	<div style="position:relative; z-index:1;">
    	 <div style=" position:absolute;z-index:2;width: 100%;height: 100% ;background:url('${ctx}/watermark/${obj.userid}.png'); "> </div>
      <table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#CCCCCC" >
      <c:if test="${obj.person.syrklbdm!='03' }">
        <tr>
          <td width="15%" class="info_title_a">证件类别 </td>
          <td width="35%" bgcolor="#FFFFFF">${obj.person.zjzlhz }</td>
          <td width="15%" class="info_title_a">证件号码 </td>
          <td width="35%" bgcolor="#FFFFFF">${obj.person.zjhm}</td>
        </tr>
        <tr>
          <td class="info_title_b">户主关系</td>
          <td bgcolor="#FFFFFF">${obj.person.yhzgxhz}</td>
          <td class="info_title_b">曾用名</td>
          <td bgcolor="#FFFFFF">${obj.person.cym}</td>
        </tr>
        <tr>
          <td class="info_title_a">宗教信仰</td>
          <td bgcolor="#FFFFFF"><wd:dicvalue dicId="1057" dicCode="${obj.person.zjxydm}"/></td>
          <td class="info_title_a">文化程度</td>
          <td bgcolor="#FFFFFF">${obj.person.whcdhz}</td>
        </tr>
        <tr>
          <td class="info_title_b">籍贯</td>
          <td bgcolor="#FFFFFF">${obj.person.jghz}</td>
          <td class="info_title_b">婚姻状况</td>
          <td bgcolor="#FFFFFF">${obj.person.hyzkhz}</td>
        </tr>
         <tr>
          <td class="info_title_a">职业</td>
          <td bgcolor="#FFFFFF">${obj.person.zy}</td>
          <td class="info_title_a">实有人口类别</td>
          <td bgcolor="#FFFFFF"><wd:dicvalue dicId="1058" dicCode="${obj.person.syrklbdm}"/>&nbsp;&nbsp;
          	<c:if test="${obj.person.syrkzlbhz!=null&&$obj.person.syrkzlbhz!=''  }">(${obj.person.syrkzlbhz })</c:if>
          	
          	</td>
          
        </tr>
        <tr>
          <td class="info_title_b">居住地街道</td>
          <td bgcolor="#FFFFFF">${obj.person.jzdjdhz}</td>
          <td class="info_title_b">居住地居委会</td>
          <td bgcolor="#FFFFFF">${obj.person.jzdjwhhz}</td>
          
        </tr>
        <tr>
          <td class="info_title_a">户籍地街道</td>
          <td bgcolor="#FFFFFF">${obj.person.hjdjdhz}</td>
          <td class="info_title_a">户籍地居委会</td>
          <td bgcolor="#FFFFFF">${obj.person.hjdjwhhz}</td>
          
        </tr>
        <tr>
        	<td class="info_title_b">居住地派出所</td>
         	<td bgcolor="#FFFFFF">${obj.person.jzdzzjghz}</td>
        	<td class="info_title_b">户籍地派出所</td>
         	<td bgcolor="#FFFFFF">${obj.person.hjdzzjghz}</td>
        </tr>
        </c:if>
        <c:if test="${obj.person.syrklbdm=='03' }">
        		<tr>
          		<td width="15%" class="info_title_a">证件类别</td>
         		 <td width="35%" bgcolor="#FFFFFF">${obj.person.zjzlhz }</td>
        	 	 <td width="15%" class="info_title_a">证件号码</td>
         	 	<td width="35%" bgcolor="#FFFFFF">${obj.person.zjhm}</td>
       	 	</tr>	
        	<tr>
        		<td class="info_title_b">境外人员身份</td>
         	 	<td bgcolor="#FFFFFF">${obj.person.jwrysfhz}</td>
          		
          		<td class="info_title_b">与主居住人关系</td>
         	 	<td bgcolor="#FFFFFF">${obj.person.yzjzrgxhz}</td>
        	</tr>
        	<tr>
        		<td class="info_title_a">停留事由</td>
         	 	<td bgcolor="#FFFFFF">${obj.person.tlsyhz}</td>
        		<td class="info_title_a">所属派出所</td>
         	 	<td bgcolor="#FFFFFF">${obj.person.zzjghz}</td>
        	</tr>
        	<tr>
        		<td class="info_title_b">实有人口类别</td>
         		 <td bgcolor="#FFFFFF"><wd:dicvalue dicId="1058" dicCode="${obj.person.syrklbdm}"/>
         		 	<c:if test="${obj.person.syrkzlbhz!=null&&$obj.person.syrkzlbhz!=''  }">(${obj.person.syrkzlbhz })</c:if>
         		 </td>
         		 <td class="info_title_b">入境时间</td>
          		<td bgcolor="#FFFFFF">${obj.person.rjsj}</td>
        	</tr>
        	
        </c:if>
        </table>
        </div>
        </div>
        <div id="con_one_2" style="display:none;"><iframe width="100%" height="600px" src="${ctx}/query/toPeopleXyxx?zjhm=${obj.person.zjhm}"></iframe></div>
        <div id="con_one_3" style="display:none;"><iframe width="100%" height="600px" src="${ctx}/querybq/toSqsw?zjhm=${obj.person.zjhm}"></iframe></div>
        <div id="con_one_4" style="display:none;"><iframe width="100%" height="600px" src="${ctx}/querybq/toYljzList?zjhm=${obj.person.zjhm}"></iframe></div>
        <div id="con_one_5" style="display:none;"><iframe width="100%" height="600px" src="${ctx}/querySGS/toPeopleXkList?zjhm=${obj.person.zjhm}"></iframe></div>
        <div id="con_one_6" style="display:none;"><iframe width="100%" height="600px" src="${ctx}/querySGS/toPeopleCfList?zjhm=${obj.person.zjhm}"></iframe></div>
    	<c:forEach items="${obj.deptlist}" var="dept" varStatus="row">
    		<div id="con_one_${row.index+7}" style="display:none;height: 200px">
    			<iframe src="${ctx}/sjtb/getpersondatalist?zjhm=${obj.person.zjhm}&dept=${dept}" width="100%" height="100%" ></iframe>
    		</div>
    	</c:forEach>
    </td>
  </tr>
</table>
</div>

</body>
<script type="text/javascript">

$("#usual1 ul").idTabs();
$(function(){
	var $node = $('#watermark');  
	//if是判断是否ie,IE8及一下版本只能用滤镜实现透明,ie9、else火狐等用opacity属性  
	if(!document.all){  
		$node.addClass('no_ie_style');
	}  
});
function view(){
	$('#form1').submit();
}
function setTab(name,cursel,n){
	 for(var i=1;i<=n;i++){
	  var menu=document.getElementById(name+i);
	  var con=document.getElementById("con_"+name+"_"+i);
	  menu.className=i==cursel?"active":"";
	  con.style.display=i==cursel?"block":"none";
	 }
	}
$(document).keydown(function (event) {
    if (event.keyCode == 27) {
    	window.close();
    }
});
$(document).ready(function(){
	  var xyxx='${obj.count}';
	 if(xyxx=='0'){
		 $("#one2").hide();
	 } 
	 if('${obj.sqsw}'=='0'){
		 $("#one3").hide();
	 }
	 if('${obj.sgsrkxk}'=='0'){
		 $("#one5").hide();
	 }
	 if('${obj.sgsrkcf}'=='0'){
		 $("#one6").hide();
	 }
	 $.post("${ctx}/querybq/toYljz",{zjhm:'${obj.person.zjhm}'},function(data){
			data1 = eval('('+data+')');
			if(data1=='0'){
				 $("#one4").hide();
			}	 
	});
});	
	
</script>
</html>