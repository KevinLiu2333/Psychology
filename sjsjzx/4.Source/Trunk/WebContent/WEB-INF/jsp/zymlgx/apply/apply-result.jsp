<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base target="_self">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/common/meta.jsp"/>
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/uploadify/scripts/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/uploadify/css/uploadify.css">
<link href="${ctx}/skins/blue/css/sjsjzx.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/tiles/Validform/css/style.css"/> 
<title>松江区政务数据中心-资源申请</title>
<style type="text/css">
.button{
	position: relative; 
    overflow: visible; 
    display: inline-block; 
    padding: 0.5em 1em; 
    border: 1px solid #6495ED; 
    margin: 0;
    width:90px;
    text-decoration: none; 
    text-shadow: 1px 1px 0 #fff; 
    font-family:"Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica, sans-serif;
	font-size: 15px;
    color: #333; 
    font-weight:bold;
    white-space: nowrap; 
    cursor: pointer; 
    outline: none; 
    background-color: #fff;
    -webkit-background-clip: padding;
    -moz-background-clip: padding;
    -o-background-clip: padding-box; 
    /*background-clip: padding-box;*/ /* commented out due to Opera 11.10 bug */
    -webkit-border-radius: 0.2em; 
    -moz-border-radius: 0.2em; 
    border-radius: 0.2em; 
    /* IE hacks */
    zoom: 1; 
    *display: inline; 
}
.button_tr1{
	background:#4F89BB;color:aliceblue;font-size:16px;
	height：40px;
	 
}
.dfinput{width:345px; height:25px; line-height:25px; border-top:solid 1px #a7b5bc; border-left:solid 1px #a7b5bc; border-right:solid 1px #ced9df; border-bottom:solid 1px #ced9df; background:url(../images/inputbg.gif) repeat-x; text-indent:10px;}
</style>
<script type="text/javascript">	 
//上一步
function backStep(){
	window.history.back(-1); 
}
//下一步
function nextStep(){
	$('#MainForm').submit();
}
//打印
function printInfo(applyId){
	var href = "${ctx}/zymlgx/reloadAgreement?applyId="+applyId+"&isPrint=1";
	window.showModalDialog(href,'window',"dialogHeight=600px;dialogWidth=800px;center=yes");
	//window.print();
}

$(document).ready(function(){
	$('#tijiao').attr('disabled',true);
});

function isShow(){
	if($("input[type='checkbox']").is(':checked')){
		$('#tijiao').attr('disabled',false);		
	}else{ 
		$('#tijiao').attr('disabled',true);
	}
	
}
//提交
function tijiaoCheck(applyId){
	$('#MainForm').attr('action', '${ctx}/zymlgx/reloadAgreement?applyId='+applyId);
	$('#MainForm').submit();
}


</script>
</head>
<body>
<jsp:include page="/common/index_public.jsp"/>
<form id="MainForm" action="${ctx}/zymlgx/toApiItemSelect" method="post">
	<br>
	<table width="60%" class="table_multilist" align="center" style="color: navy;">
		<tr class="button_tr1"><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;资源申请</td></tr>
		<tr>
			<td style="font-size: 15px;margin-left: 20px;margin-right: 8px;">
			&nbsp;&nbsp;申请单位：<wd:dicvalue dicId="<%=Constants.DIC_DEPT_NAME %>" dicCode="${obj.applyDept}"/><br>
			&nbsp;&nbsp;由于${obj.resourceApply.applyReason}原因<br>
			&nbsp;&nbsp;您已申请&nbsp;<font color="red"><c:if test="${obj.resourceType != '' && obj.resourceType != null}">${obj.resourceType }。</c:if></font><br>
			&nbsp;&nbsp;所申请的数据项为：&nbsp;<font color="red">${obj.resourceItems}。</font><br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="green">普通共享：</font><font color="red"><c:if test="${obj.ptgx != '' && obj.axgx != null}">${obj.ptgx}。</c:if></font><br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="green">按需共享：</font><font color="red"><c:if test="${obj.axgx != '' && obj.axgx != null}">${obj.axgx}。</c:if></font><br>
			&nbsp;&nbsp;所提供的附件为：${obj.file}<br>
			&nbsp;&nbsp;请遵守以下协议要求：<br>
			&nbsp;&nbsp;<wd:dicvalue dicId="1055" dicCode="${obj.resourceApply.resourceType}"/>使用协议范本文字:<br>
			<html xmlns:v="urn:schemas-microsoft-com:vml"
xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:w="urn:schemas-microsoft-com:office:word"
xmlns:m="http://schemas.microsoft.com/office/2004/12/omml"
xmlns="http://www.w3.org/TR/REC-html40">

<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Word.Document>
<meta name=Generator content="Microsoft Word 12">
<meta name=Originator content="Microsoft Word 12">
<link rel=File-List href="松江区政务数据中心数据使用安全保密协议20160926.files/filelist.xml">
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author>user</o:Author>
  <o:LastAuthor>郭光伟</o:LastAuthor>
  <o:Revision>2</o:Revision>
  <o:TotalTime>43</o:TotalTime>
  <o:Created>2016-11-11T02:39:00Z</o:Created>
  <o:LastSaved>2016-11-11T02:39:00Z</o:LastSaved>
  <o:Pages>3</o:Pages>
  <o:Words>224</o:Words>
  <o:Characters>1283</o:Characters>
  <o:Lines>10</o:Lines>
  <o:Paragraphs>3</o:Paragraphs>
  <o:CharactersWithSpaces>1504</o:CharactersWithSpaces>
  <o:Version>12.00</o:Version>
 </o:DocumentProperties>
 <o:OfficeDocumentSettings>
  <o:RelyOnVML/>
  <o:AllowPNG/>
 </o:OfficeDocumentSettings>
</xml><![endif]-->
<link rel=themeData href="松江区政务数据中心数据使用安全保密协议20160926.files/themedata.thmx">
<link rel=colorSchemeMapping
href="松江区政务数据中心数据使用安全保密协议20160926.files/colorschememapping.xml">
<!--[if gte mso 9]><xml>
 <w:WordDocument>
  <w:TrackMoves>false</w:TrackMoves>
  <w:TrackFormatting/>
  <w:PunctuationKerning/>
  <w:DrawingGridVerticalSpacing>7.8 磅</w:DrawingGridVerticalSpacing>
  <w:DisplayHorizontalDrawingGridEvery>0</w:DisplayHorizontalDrawingGridEvery>
  <w:DisplayVerticalDrawingGridEvery>2</w:DisplayVerticalDrawingGridEvery>
  <w:ValidateAgainstSchemas/>
  <w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>
  <w:IgnoreMixedContent>false</w:IgnoreMixedContent>
  <w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>
  <w:DoNotPromoteQF/>
  <w:LidThemeOther>EN-US</w:LidThemeOther>
  <w:LidThemeAsian>ZH-CN</w:LidThemeAsian>
  <w:LidThemeComplexScript>X-NONE</w:LidThemeComplexScript>
  <w:Compatibility>
   <w:SpaceForUL/>
   <w:BalanceSingleByteDoubleByteWidth/>
   <w:DoNotLeaveBackslashAlone/>
   <w:ULTrailSpace/>
   <w:DoNotExpandShiftReturn/>
   <w:AdjustLineHeightInTable/>
   <w:BreakWrappedTables/>
   <w:SnapToGridInCell/>
   <w:WrapTextWithPunct/>
   <w:UseAsianBreakRules/>
   <w:DontGrowAutofit/>
   <w:SplitPgBreakAndParaMark/>
   <w:DontVertAlignCellWithSp/>
   <w:DontBreakConstrainedForcedTables/>
   <w:DontVertAlignInTxbx/>
   <w:Word11KerningPairs/>
   <w:CachedColBalance/>
   <w:UseFELayout/>
  </w:Compatibility>
  <m:mathPr>
   <m:mathFont m:val="Cambria Math"/>
   <m:brkBin m:val="before"/>
   <m:brkBinSub m:val="--"/>
   <m:smallFrac/>
   <m:dispDef/>
   <m:lMargin m:val="0"/>
   <m:rMargin m:val="0"/>
   <m:defJc m:val="centerGroup"/>
   <m:wrapIndent m:val="1440"/>
   <m:intLim m:val="subSup"/>
   <m:naryLim m:val="undOvr"/>
  </m:mathPr></w:WordDocument>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <w:LatentStyles DefLockedState="false" DefUnhideWhenUsed="true"
  DefSemiHidden="true" DefQFormat="false" DefPriority="99"
  LatentStyleCount="267">
  <w:LsdException Locked="false" Priority="0" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Normal"/>
  <w:LsdException Locked="false" Priority="9" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="heading 1"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 2"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 3"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 4"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 5"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 6"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 7"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 8"/>
  <w:LsdException Locked="false" Priority="9" QFormat="true" Name="heading 9"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 1"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 2"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 3"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 4"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 5"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 6"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 7"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 8"/>
  <w:LsdException Locked="false" Priority="39" Name="toc 9"/>
  <w:LsdException Locked="false" Priority="35" QFormat="true" Name="caption"/>
  <w:LsdException Locked="false" Priority="0" Name="page number"/>
  <w:LsdException Locked="false" Priority="10" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Title"/>
  <w:LsdException Locked="false" Priority="1" Name="Default Paragraph Font"/>
  <w:LsdException Locked="false" Priority="11" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Subtitle"/>
  <w:LsdException Locked="false" Priority="22" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Strong"/>
  <w:LsdException Locked="false" Priority="20" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Emphasis"/>
  <w:LsdException Locked="false" Priority="59" SemiHidden="false"
   UnhideWhenUsed="false" Name="Table Grid"/>
  <w:LsdException Locked="false" UnhideWhenUsed="false" Name="Placeholder Text"/>
  <w:LsdException Locked="false" Priority="1" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="No Spacing"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 1"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 1"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 1"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 1"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 1"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 1"/>
  <w:LsdException Locked="false" UnhideWhenUsed="false" Name="Revision"/>
  <w:LsdException Locked="false" Priority="34" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="List Paragraph"/>
  <w:LsdException Locked="false" Priority="29" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Quote"/>
  <w:LsdException Locked="false" Priority="30" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Intense Quote"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 1"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 1"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 1"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 1"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 1"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 1"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 1"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 1"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 2"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 2"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 2"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 2"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 2"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 2"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 2"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 2"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 2"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 2"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 2"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 2"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 2"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 2"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 3"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 3"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 3"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 3"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 3"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 3"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 3"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 3"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 3"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 3"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 3"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 3"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 3"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 3"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 4"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 4"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 4"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 4"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 4"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 4"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 4"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 4"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 4"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 4"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 4"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 4"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 4"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 4"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 5"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 5"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 5"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 5"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 5"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 5"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 5"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 5"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 5"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 5"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 5"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 5"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 5"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 5"/>
  <w:LsdException Locked="false" Priority="60" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Shading Accent 6"/>
  <w:LsdException Locked="false" Priority="61" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light List Accent 6"/>
  <w:LsdException Locked="false" Priority="62" SemiHidden="false"
   UnhideWhenUsed="false" Name="Light Grid Accent 6"/>
  <w:LsdException Locked="false" Priority="63" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 1 Accent 6"/>
  <w:LsdException Locked="false" Priority="64" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Shading 2 Accent 6"/>
  <w:LsdException Locked="false" Priority="65" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 1 Accent 6"/>
  <w:LsdException Locked="false" Priority="66" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium List 2 Accent 6"/>
  <w:LsdException Locked="false" Priority="67" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 1 Accent 6"/>
  <w:LsdException Locked="false" Priority="68" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 2 Accent 6"/>
  <w:LsdException Locked="false" Priority="69" SemiHidden="false"
   UnhideWhenUsed="false" Name="Medium Grid 3 Accent 6"/>
  <w:LsdException Locked="false" Priority="70" SemiHidden="false"
   UnhideWhenUsed="false" Name="Dark List Accent 6"/>
  <w:LsdException Locked="false" Priority="71" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Shading Accent 6"/>
  <w:LsdException Locked="false" Priority="72" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful List Accent 6"/>
  <w:LsdException Locked="false" Priority="73" SemiHidden="false"
   UnhideWhenUsed="false" Name="Colorful Grid Accent 6"/>
  <w:LsdException Locked="false" Priority="19" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Subtle Emphasis"/>
  <w:LsdException Locked="false" Priority="21" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Intense Emphasis"/>
  <w:LsdException Locked="false" Priority="31" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Subtle Reference"/>
  <w:LsdException Locked="false" Priority="32" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Intense Reference"/>
  <w:LsdException Locked="false" Priority="33" SemiHidden="false"
   UnhideWhenUsed="false" QFormat="true" Name="Book Title"/>
  <w:LsdException Locked="false" Priority="37" Name="Bibliography"/>
  <w:LsdException Locked="false" Priority="39" QFormat="true" Name="TOC Heading"/>
 </w:LatentStyles>
</xml><![endif]-->
<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:Wingdings;
	panose-1:5 0 0 0 0 0 0 0 0 0;
	mso-font-charset:2;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:0 268435456 0 0 -2147483648 0;}
@font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-alt:SimSun;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 680460288 22 0 262145 0;}
@font-face
	{font-family:黑体;
	panose-1:2 1 6 9 6 1 1 1 1 1;
	mso-font-alt:SimHei;
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:-2147482945 953122042 22 0 262145 0;}
@font-face
	{font-family:"Cambria Math";
	panose-1:2 4 5 3 5 4 6 3 2 4;
	mso-font-charset:0;
	mso-generic-font-family:roman;
	mso-font-pitch:variable;
	mso-font-signature:-536870145 1107305727 0 0 415 0;}
@font-face
	{font-family:华文中宋;
	panose-1:2 1 6 0 4 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:647 135200768 16 0 262303 0;}
@font-face
	{font-family:仿宋_GB2312;
	mso-font-alt:仿宋;
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:微软雅黑;
	panose-1:2 11 5 3 2 2 4 2 2 4;
	mso-font-charset:134;
	mso-generic-font-family:swiss;
	mso-font-pitch:variable;
	mso-font-signature:-1610612089 684670034 22 0 262175 0;}
@font-face
	{font-family:楷体_GB2312;
	mso-font-alt:楷体;
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:"\@微软雅黑";
	panose-1:2 11 5 3 2 2 4 2 2 4;
	mso-font-charset:134;
	mso-generic-font-family:swiss;
	mso-font-pitch:variable;
	mso-font-signature:-1610612089 684670034 22 0 262175 0;}
@font-face
	{font-family:"\@黑体";
	panose-1:2 1 6 9 6 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:-2147482945 953122042 22 0 262145 0;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 680460288 22 0 262145 0;}
@font-face
	{font-family:"\@华文中宋";
	panose-1:2 1 6 0 4 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:647 135200768 16 0 262303 0;}
@font-face
	{font-family:"\@仿宋_GB2312";
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:"\@楷体_GB2312";
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:1 135135232 16 0 262144 0;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{mso-style-unhide:no;
	mso-style-qformat:yes;
	mso-style-parent:"";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	mso-pagination:none;
	font-size:10.5pt;
	mso-bidi-font-size:10.0pt;
	font-family:"Times New Roman","serif";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
p.MsoHeader, li.MsoHeader, div.MsoHeader
	{mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-link:"页眉 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:center;
	mso-pagination:none;
	tab-stops:center 207.65pt right 415.3pt;
	layout-grid-mode:char;
	border:none;
	mso-border-bottom-alt:solid windowtext .75pt;
	padding:0cm;
	mso-padding-alt:0cm 0cm 1.0pt 0cm;
	font-size:9.0pt;
	font-family:"Times New Roman","serif";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
p.MsoFooter, li.MsoFooter, div.MsoFooter
	{mso-style-priority:99;
	mso-style-link:"页脚 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	mso-pagination:none;
	tab-stops:center 207.65pt right 415.3pt;
	layout-grid-mode:char;
	font-size:9.0pt;
	font-family:"Times New Roman","serif";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
p.MsoDocumentMap, li.MsoDocumentMap, div.MsoDocumentMap
	{mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-link:"文档结构图 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	mso-pagination:none;
	font-size:9.0pt;
	font-family:宋体;
	mso-hansi-font-family:"Times New Roman";
	mso-bidi-font-family:"Times New Roman";
	mso-font-kerning:1.0pt;}
p.MsoListParagraph, li.MsoListParagraph, div.MsoListParagraph
	{mso-style-priority:34;
	mso-style-unhide:no;
	mso-style-qformat:yes;
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:21.0pt;
	mso-char-indent-count:2.0;
	mso-pagination:none;
	font-size:10.5pt;
	mso-bidi-font-size:10.0pt;
	font-family:"Times New Roman","serif";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
span.Char
	{mso-style-name:"页脚 Char";
	mso-style-priority:99;
	mso-style-unhide:no;
	mso-style-locked:yes;
	mso-style-link:页脚;
	mso-ansi-font-size:9.0pt;
	mso-bidi-font-size:9.0pt;
	font-family:"Times New Roman","serif";
	mso-ascii-font-family:"Times New Roman";
	mso-fareast-font-family:宋体;
	mso-hansi-font-family:"Times New Roman";
	mso-bidi-font-family:"Times New Roman";}
p.CharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharChar, li.CharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharChar, div.CharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharCharChar
	{mso-style-name:"Char Char Char Char Char Char Char Char Char Char Char Char Char Char Char Char Char Char Char Char Char Char";
	mso-style-update:auto;
	mso-style-unhide:no;
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:42.55pt;
	mso-pagination:none;
	tab-stops:list 78.55pt;
	font-size:12.0pt;
	font-family:"Times New Roman","serif";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
span.Char0
	{mso-style-name:"文档结构图 Char";
	mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-unhide:no;
	mso-style-locked:yes;
	mso-style-link:文档结构图;
	mso-ansi-font-size:9.0pt;
	mso-bidi-font-size:9.0pt;
	font-family:宋体;
	mso-ascii-font-family:宋体;
	mso-fareast-font-family:宋体;
	mso-hansi-font-family:"Times New Roman";
	mso-bidi-font-family:"Times New Roman";}
span.Char1
	{mso-style-name:"页眉 Char";
	mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-unhide:no;
	mso-style-locked:yes;
	mso-style-link:页眉;
	mso-ansi-font-size:9.0pt;
	mso-bidi-font-size:9.0pt;
	font-family:"Times New Roman","serif";
	mso-ascii-font-family:"Times New Roman";
	mso-fareast-font-family:宋体;
	mso-hansi-font-family:"Times New Roman";
	mso-bidi-font-family:"Times New Roman";}
.MsoChpDefault
	{mso-style-type:export-only;
	mso-default-props:yes;
	mso-bidi-font-family:"Times New Roman";
	mso-bidi-theme-font:minor-bidi;}
 /* Page Definitions */
 @page
	{mso-page-border-surround-header:no;
	mso-page-border-surround-footer:no;
	mso-footnote-separator:url("松江区政务数据中心数据使用安全保密协议20160926.files/header.htm") fs;
	mso-footnote-continuation-separator:url("松江区政务数据中心数据使用安全保密协议20160926.files/header.htm") fcs;
	mso-endnote-separator:url("松江区政务数据中心数据使用安全保密协议20160926.files/header.htm") es;
	mso-endnote-continuation-separator:url("松江区政务数据中心数据使用安全保密协议20160926.files/header.htm") ecs;}
@page Section1
	{size:595.3pt 841.9pt;
	margin:79.4pt 87.9pt 79.4pt 87.9pt;
	mso-header-margin:42.55pt;
	mso-footer-margin:49.6pt;
	mso-even-footer:url("松江区政务数据中心数据使用安全保密协议20160926.files/header.htm") ef1;
	mso-footer:url("松江区政务数据中心数据使用安全保密协议20160926.files/header.htm") f1;
	mso-paper-source:0;
	layout-grid:15.6pt;}
div.Section1
	{page:Section1;}
 /* List Definitions */
 @list l0
	{mso-list-id:1242329722;
	mso-list-type:hybrid;
	mso-list-template-ids:-1907825794 67698689 67698691 67698693 67698689 67698691 67698693 67698689 67698691 67698693;}
@list l0:level1
	{mso-level-number-format:bullet;
	mso-level-text:\F06C;
	mso-level-tab-stop:none;
	mso-level-number-position:left;
	margin-left:49.0pt;
	text-indent:-21.0pt;
	font-family:Wingdings;}
ol
	{margin-bottom:0cm;}
ul
	{margin-bottom:0cm;}
-->
</style>
<!--[if gte mso 10]>
<style>
 /* Style Definitions */
 table.MsoNormalTable
	{mso-style-name:普通表格;
	mso-tstyle-rowband-size:0;
	mso-tstyle-colband-size:0;
	mso-style-noshow:yes;
	mso-style-priority:99;
	mso-style-qformat:yes;
	mso-style-parent:"";
	mso-padding-alt:0cm 5.4pt 0cm 5.4pt;
	mso-para-margin:0cm;
	mso-para-margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:10.5pt;
	mso-bidi-font-size:11.0pt;
	font-family:"Calibri","sans-serif";
	mso-ascii-font-family:Calibri;
	mso-ascii-theme-font:minor-latin;
	mso-hansi-font-family:Calibri;
	mso-hansi-theme-font:minor-latin;
	mso-font-kerning:1.0pt;}
</style>
<![endif]--><!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="3074"/>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <o:shapelayout v:ext="edit">
  <o:idmap v:ext="edit" data="1"/>
 </o:shapelayout></xml><![endif]-->
</head>

<body lang=ZH-CN style='tab-interval:21.0pt;text-justify-trim:punctuation'>

<div class=Section1 style='layout-grid:15.6pt'>

<p class=MsoNormal align=center style='margin-right:-.55pt;text-align:center;
line-height:27.0pt;mso-line-height-rule:exactly;mso-outline-level:1;layout-grid-mode:
char'><b style='mso-bidi-font-weight:normal'><span style='font-size:18.0pt;
font-family:华文中宋;letter-spacing:1.6pt'>松江区政务数据中心<span lang=EN-US><o:p></o:p></span></span></b></p>

<p class=MsoNormal align=center style='margin-right:-.55pt;text-align:center;
line-height:27.0pt;mso-line-height-rule:exactly;mso-outline-level:1;layout-grid-mode:
char'><b style='mso-bidi-font-weight:normal'><span style='font-size:18.0pt;
font-family:华文中宋;letter-spacing:1.6pt'>政务数据资源安全保密使用协议书<span lang=EN-US><o:p></o:p></span></span></b></p>

<p class=MsoNormal align=center style='margin-right:-.55pt;text-align:center;
line-height:27.0pt;mso-line-height-rule:exactly;mso-outline-level:1;layout-grid-mode:
char'><b style='mso-bidi-font-weight:normal'><span style='font-size:18.0pt;
font-family:华文中宋;letter-spacing:1.6pt'>（试行）<span lang=EN-US><o:p></o:p></span></span></b></p>

<p class=MsoNormal style='line-height:27.0pt;mso-line-height-rule:exactly'><span
lang=EN-US><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:黑体;mso-hansi-font-family:宋体'>一、本协议签署方<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:仿宋_GB2312'>甲方<span lang=EN-US> (</span>提供方<span lang=EN-US>)</span></span><span
style='font-size:14.0pt;font-family:仿宋_GB2312;mso-hansi-font-family:宋体'>：上海市松江区科学技术委员会<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:仿宋_GB2312'>乙方<span lang=EN-US> (</span>使用方<span lang=EN-US>)</span></span><span
style='font-size:14.0pt;font-family:仿宋_GB2312;mso-hansi-font-family:宋体'>：<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312;mso-hansi-font-family:宋体'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:黑体;mso-hansi-font-family:"Times New Roman"'>二、本协议制定目的<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:仿宋_GB2312'>为规范和促进松江区政务数据资源的共享应用，建立健全政务数据资源共享交换机制，保障“松江区政务数据中心”平稳运行，推动政务数据资源优化配置和增值利用。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:仿宋_GB2312;mso-hansi-font-family:宋体'>松江区科学技术委员会</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>承担“松江区政务数据中心<a name="_GoBack"></a>”管理工作，为规范政务数据共享使用，保证数据应用安全，防止数据泄密，根据《</span><span
lang=EN-US><a href="http://10.15.34.207:168/golaw?dbnm=gjfg&amp;flid=111601198801"
target="_blank"><span lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312;
color:windowtext;text-decoration:none;text-underline:none'><span lang=EN-US>中华人民共和国保守国家秘密法</span></span></a></span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>》、《<span style='mso-bidi-font-weight:
bold'>上海市实有人口服务和管理若干规定（暂行）》、《上海市政务数据资源共享管理办法》和《松江区政务数据资源共享管理办法》</span>等相关法律法规及办法，特制定本协议书。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:黑体;mso-hansi-font-family:"Times New Roman"'>三、相关权利义务<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:仿宋_GB2312'>甲乙双方在松江区政务信息共享管理工作中享有的权利和应当履行的义务包括：<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>1</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>、甲方负责对松江区政务数据中心场地、人员及系统的安全建设和管理，建立健全管理制度、工作规范和应急处理机制，配备专人负责管理，完善身份认证、访问控制、信息审计跟踪、机房环境监控、容灾备份等技术防控措施。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>2</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>、甲方负责管理松江区政务数据中心平台及范围内所归集数据资源的维护和安全保障，同时乙方作为数据使用方，有义务及时发现并协调解决数据使用中的异常情况或者突发安全事件，将相关情况通报甲方。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>3</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>、乙方在访问政务数据中心系统时，必须使用政务网络，保证数据不流入互联网或其它网段。如乙方网络环境不符合政务数据中心数据使用安全保密标准的，甲方有权立刻停止对其共享</span><span
style='font-size:13.5pt;font-family:"微软雅黑","sans-serif";letter-spacing:.4pt;
background:white'>。<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>4</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>、乙方从政务数据中心获取的数据享有受限使用权，仅限于在本部门履职需要使用，不得用于任何其他目的。应当遵守相关法律、法规的规定，不得侵犯公共安全、商业秘密和个人隐私，不得利用政务信息牟取商业利益和其他非法所得。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal align=left style='text-align:left;text-indent:28.0pt;
mso-char-indent-count:2.0;line-height:28.0pt;mso-line-height-rule:exactly'><span
lang=EN-US style='font-size:14.0pt;font-family:仿宋_GB2312'>5</span><span
style='font-size:14.0pt;font-family:仿宋_GB2312'>、按照“谁使用，谁负责”的原则，乙方应根据履行职责需要依法依规使用数据资源，并做好政务数据资源安全和信息保密的监督和管理工作。乙方应建立本部门资源共享利用管理制度，指定专人负责共享资源共享利用相关工作。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>6</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>、建立疑义、错误信息快速校核机制，乙方对获取的数据资源有疑义或发现有明显错误的，应及时反馈甲方予以校核。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>7</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>、乙方有义务配合甲方在共享数据使用范围内的系统中开设管理权限临时用户，以便甲方对乙方相关业务系统进行定期检查。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>9</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>、乙方不得使用甲方所提供数据从事以下一些可能危害国家安全、社会公共利益和他人合法权益的活动，包括：<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>1</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>）反对宪法基本原则的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>2</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>）危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>3</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>）损害国家荣誉和利益的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>4</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>）煽动民族仇恨、民族歧视，破坏民族团结的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>5</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>）破坏国家宗教政策，宣扬邪教和封建迷信活动的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>6</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>）散布谣言，扰乱社会秩序，破坏社会稳定的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>7</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>）侮辱或者诽谤他人，侵害他人权益的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'>8</span><span style='font-size:
14.0pt;font-family:仿宋_GB2312'>）含有法律法规禁止的其他内容的；<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:28.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:仿宋_GB2312'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:27.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:黑体;mso-hansi-font-family:"Times New Roman"'>四、违约责任<span lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:150%'><span style='font-size:14.0pt;line-height:150%;font-family:
仿宋_GB2312'>乙方违反法律、法规和本协议规定，甲方有权责令乙方停止使用共享数据资源，并归还甲方，且将再复制的该数据资源及其衍生品全部删除。造成国家、法人、其他部门和个人损失的，依法追究乙方和直接责任人员的法律责任。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:27.0pt;mso-line-height-rule:exactly'><span style='font-size:14.0pt;
font-family:黑体;mso-hansi-font-family:"Times New Roman"'>五、本协议一式贰份，甲、乙双方各执壹份。本协议自签订之日起生效。<span
lang=EN-US><o:p></o:p></span></span></p>

<p class=MsoNormal style='text-indent:28.0pt;mso-char-indent-count:2.0;
line-height:27.0pt;mso-line-height-rule:exactly'><span lang=EN-US
style='font-size:14.0pt;font-family:黑体;mso-hansi-font-family:"Times New Roman"'><o:p>&nbsp;</o:p></span></p>

<p class=MsoNormal style='line-height:27.0pt;mso-line-height-rule:exactly;margin-left:15%'><b
style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;font-family:
楷体_GB2312'>甲方（盖章）：<span lang=EN-US><span
style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span>乙方（盖章）：<span lang=EN-US><o:p></o:p></span></span></b></p>

<p class=MsoNormal style='margin-left:21.0pt;line-height:27.0pt;mso-line-height-rule:
exactly'><b style='mso-bidi-font-weight:normal;margin-left:15%'><span lang=EN-US
style='font-size:14.0pt;font-family:楷体_GB2312'><o:p>&nbsp;</o:p></span></b></p>

<p class=MsoNormal style='line-height:27.0pt;mso-line-height-rule:exactly;margin-left:15%'><b
style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;font-family:
楷体_GB2312'>经办人：<span lang=EN-US><span
style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span>（签字）<span
lang=EN-US><span
style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span>经办人：<span
lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span>（签字）<span lang=EN-US><o:p></o:p></span></span></b></p>

<p class=MsoNormal style='line-height:27.0pt;mso-line-height-rule:exactly;margin-left:15%'><b
style='mso-bidi-font-weight:normal'><span style='font-size:14.0pt;font-family:
楷体_GB2312'>日期：<span lang=EN-US><span
style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</span></span>日期：<span lang=EN-US><o:p></o:p></span></span></b></p>

<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>

</div>

</body>

</html>
			</td>
		</tr>
	</table>
	<div style="height:20px;padding-top: 5px;text-align: center">
		<c:if test="${obj.resourceApply.isSubmit != '1'}">
			<input type="checkbox" id="xieyibox" onchange="isShow()"/>&nbsp;&nbsp;已阅读协议并同意协议条款
		</c:if>
	</div>
	<br>
	<div style="margin-left: 480px">
		<input type="button" id="fanhui"  class="button" value="上一步" onclick="backStep();"/>&nbsp;&nbsp;
		<input type="button" id="printView"  class="button" value="打印预览" onclick="printInfo('${obj.resourceApply.applyId}');"/>&nbsp;&nbsp;
		<c:if test="${obj.resourceApply.isSubmit != '1'}">
			<input type="button" id="tijiao"  class="button" value="提交审核" onclick="tijiaoCheck('${obj.resourceApply.applyId}');"/> 
		</c:if>
		<c:if test="${obj.resourceApply.isSubmit == '1'}">
			<span><font color="red">申请已提交，请等待审核结果！</font></span>
		</c:if>
	</div><br>
</form>
</body>
</html>