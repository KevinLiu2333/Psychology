<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>松江区政务数据中心-资源目录</title>
<link href="${ctx}/skins/css/publicauto.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<style>
.tag {
 float:left;
 list-style:none;
 margin:0;
 padding:0;
 width:100%;
 background:url(../skins/images/tag_line.jpg) repeat-x bottom;
}
.tag li {
	background: url(../skins/images/tag_bg.jpg) repeat-x top;
	font-size:16px;
	border-radius:0;
	border-top:#ddd 1px solid;
	border-left:#ddd 1px solid;
	border-right:#ddd 1px solid;
	margin:0;
	float:left;
	height:39px;
	line-height:39px;
	width:150px;
	text-align:center;
	border-top-left-radius:5px;
	border-top-right-radius:5px;
	margin-right:5px;
	cursor:pointer;
}
.tag li a:link,.tag li a:visited {
	color:#FFF;
	text-decoration:none;
	font-family:"Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica, sans-serif;
	}
.tag li:first-child {
}
.tag li.active {
	color:#333;
	background:#FFF url(../skins/images/tag_active.jpg) repeat-x top;
}
.tag li.active a:link,.tag li.active a:visited {
	color:#333;
	}

.clearfix:after { content:"."; display:block; height:0; clear:both; visibility:hidden; }
.clearfix { display:inline-block; }
.clearfix {display:block;}

.tag_content {
	width:90%;
	margin:30px auto;
	}
#con_one_1,#con_one_2,#con_one_3,#con_one_4,#con_one_5 {
	border:1px solid #CCC;
	border-top:none;
	padding:30px 0;
	}
.tag_search {
	background:#e0f3fb;
	width:85%;
	text-align:center;
	padding:20px 0;
	margin:0 auto;
	font-size:14px;
	font-weight:bold;
	color:#2e97cd;
	}
.tag_search_input {
	border:1px solid #CCC;
	height:30px;
	line-height:30px;
	width:250px;
	padding:0 15px;
	border-top-left-radius:5px;
	border-bottom-left-radius:5px;
	vertical-align:middle;
	background:url(../skins/images/tag_active.jpg) repeat-x top;
	}
.tag_search_button {
	height:34px;
	line-height:34px;
	text-align:center;
	border:none;
	color:#FFF;
	font-size:14px;
	font-weight:bold;
	padding:0 15px;
	vertical-align:middle;
	border-top-right-radius:5px;
	border-bottom-right-radius:5px;
	background:url(../skins/images/tag_bg.jpg) repeat-x bottom;
	}
.tag_search_select {
	padding:7px 8px;
	}
.tag_list_tags span,.tag_list_tags span a:link,.tag_list_tags span a:visited {
	color:#f21717;
	}
.tag_list_title a:link,.tag_list_title a:visited {
	font-size:16px;
	font-weight:bold;
	color:#396fb8;
	text-decoration:none;
	}
.tag_list_title a:hover {
	color:#f21717;
	text-decoration:underline;
	}
.tag_list_title {
	height:30px;
	line-height:30px;
	}
.tag_list_title span {
	float:right;
	color:#747474;
	*margin-top:-30px;
	}
.tag_list {
	list-style:none;
	margin:20px auto 0 auto;
	background:#f6f7f7;
	border:1px solid #e3e3e3;
	padding:20px 2%;
	}
.tag_list li:first-child {
	margin-bottom:10px;
	}

.search_wrap { height:60px; background:#37b; position:relative; z-index:10;}
.search { width:1006px; height:60px; margin:0 auto; background:url(../images/icon_search.gif) no-repeat 20px center; position:relative;  }
.search .text { width:760px; height:38px;margin:10px 10px; padding:0 12px;line-height:40px; margin-left:50px; background:#8DB6CD; border:none; color:#37b; font-size:14px; border-radius:3px;position:absolute;}
.search .text::-webkit-input-placeholder { color:#fff }
.search .text::-moz-placeholder { color:#fff }
.search .text:-moz-placeholder { color:#fff }
.search .text:-ms-input-placeholder { color:#fff }
.search span { height:38px; margin:10px 10px; padding:0 26px; background:#639dd8; border-radius:2px; line-height:40px; color:#fff; transition: color .3s; position:absolute; top:0; right:77px; cursor:pointer; }
.search span:hover { color:#ccc; }
.search em {  width:60px; height:5px; display:block; position:absolute; top:20px; right:14px;color:#fff; }

.ex{top:50px; left:100px; width:100px; height:100px; position:fixed;}
</style>

</head>

<body>
<c:if test="${(obj.user.type != '2')}">
<jsp:include page="/common/index_public.jsp"/>
</c:if>


<div class="search_wrap">
 <c:if test="${obj.resourceType=='r_rkl'}">
  <div class="search clear">
  
       <div id="expend_search">
		   <input class="text" type="text" value="" placeholder="请输入关键词..." id="gjz_rkl" name="gjz_fwl"/>
	   </div> 	   
		    <span  onclick="query(1)"  style="font-weight: 900;">搜索</span>
   </div>
   </c:if>
    <c:if test="${obj.resourceType=='r_frl'}">
 	 <div class="search clear">
  
       <div id="expend_search">
		   <input class="text" type="text" value="" placeholder="请输入关键词..."  id="gjz_frl" name="gjz_fwl"/>
	   </div> 	   
		    <span  onclick="query(2)"  style="font-weight: 900;">搜索</span>
   </div>
   </c:if>
    <c:if test="${obj.resourceType=='r_fwl'}">
 	 <div class="search clear">
  
       <div id="expend_search">
		   <input class="text" type="text" value="" placeholder="请输入关键词..."  id="gjz_fwl" name="gjz_fwl"/>
	   </div> 	   
		    <span  onclick="query(3)"  style="font-weight: 900;">搜索</span>
   </div>
   </c:if>
    <c:if test="${obj.resourceType=='r_zti'}">
 	 <div class="search clear">
  
       <div id="expend_search">
		   <input class="text" type="text" value="" placeholder="请输入关键词..."  id="gjz_ztl" name="gjz_fwl"/>
	   </div> 	   
		    <span  onclick="query(4)"  style="font-weight: 900;">搜索</span>
   </div>
   </c:if>
   </div>
<div id="wrap">
  <div class="container clear">
    <div id="side">
      <div class="borders" style="text-align:center;">
      <p class="picArea">
         <img src="${ctx}/skins/images/index/icon_nyhj.png" width="50" height="50"/>
          <span style="font:200 24px/50px Microsoft Yahei; color:#37b">
         资源目录
          </span>
        </p>  
      </div>
      <div class="borders">
        <dl>
          <dt style="width: 108px;text-align:center">基础</dt>
	          <dd>
			          <a href="#" onclick="query(1);">
			         
			      人口类（${obj.rklCount}）
			          </a>
		     </dd>
	          <dd>
			          <a href="#"   onclick="query(2);"
			          class="selected">
			         法人类（${obj.frlCount}）
			          </a>
		     </dd>
		     <dd>
			          <a href="#" onclick="query(3);"
			          class="selected">
			         房屋类（${obj.fwlCount}）
			          </a>
		     </dd>
        </dl>
      </div>
      <div class="borders">
          
          <dl>
             <dt style="width: 108px;text-align:center">专题</dt>
                      <dd>
                          <a href="#" onclick="query1('068001')">松江区科委（${obj.kwlCount}）</a></dd>
                          <dd>
                          <a href="#" onclick="query1('017001')" >松江区统计局（${obj.tjlCount}）</a></dd>
                          <dd>
                          <a href="#" onclick="query1('X07060')">松江区社治办（${obj.szblCount}）</a></dd>
                          <dd>
                           <a href="#" onclick="query1('035001')" >松江区规土局（${obj.gtlCount}）</a></dd>
                           <dd>
                           <a href="#" onclick="query1('037001')" >松江区房管局（${obj.fglCount}）</a></dd>
                           <dd>
                           <a href="#" >......</a></dd>
          </dl>
      </div>
    </div>
     <form id="queryForm" action="${ctx}/zymlgl/toContentIndex" method="post">
	<input type="hidden" id="orderBy" name="orderBy"/>
		<input type="hidden" id="keyWord" name="keyWord"/>
		<input type="hidden" id="resourceType" name="resourceType" value="${obj.resourceType}"/>
		<input type="hidden" id="provideDepId" name="provideDepId" value=""/>
    <div id="content" >
      <dl class="list">
      <c:if test="${obj.resourceType=='r_rkl'}">
    	 <c:forEach items="${obj.RkResourceList}" var="resource" varStatus="row">
		  <ul class="tag_list">
		  <li class="tag_list_title"><img src="../skins/images/icon_document.jpg" width="26" height="26" align="absmiddle" /> <a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&applyFlag=${obj.applyFlag}&isSy=${obj.isSy}" target="main_frame" target="main_frame">${resource.resourceName}</a> <span><fmt:formatDate value="${resource.updateDate}" pattern="yyyy年MM月dd日"/>更新&nbsp;&nbsp;已有${resource.browseCount}次浏览&nbsp;&nbsp;</span></li>
		  <li class="tag_list_tags">关键字：<span>${resource.keyWord}</span>等 </li>
		  </ul>
  		</c:forEach>
  </c:if>
   <c:if test="${obj.resourceType=='r_frl'}">
     <c:forEach items="${obj.FrResourceList}" var="resource" varStatus="row">
		  <ul class="tag_list">
		  <li class="tag_list_title"><img src="../skins/images/icon_document.jpg" width="26" height="26" align="absmiddle" /> <a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&applyFlag=${obj.applyFlag}&isSy=${obj.isSy}" target="main_frame" target="main_frame">${resource.resourceName}</a> <span><fmt:formatDate value="${resource.updateDate}" pattern="yyyy年MM月dd日"/>更新&nbsp;&nbsp;已有${resource.browseCount}次浏览&nbsp;&nbsp;</span></li>
		  <li class="tag_list_tags">关键字：<span>${resource.keyWord}</span>等 </li>
		  </ul>
  </c:forEach>
  </c:if>
   <c:if test="${obj.resourceType=='r_fwl'}">
     <c:forEach items="${obj.JjResourceList}" var="resource" varStatus="row">
		  <ul class="tag_list">
		  <li class="tag_list_title"><img src="../skins/images/icon_document.jpg" width="26" height="26" align="absmiddle" /> <a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&applyFlag=${obj.applyFlag}&isSy=${obj.isSy}" target="main_frame" target="main_frame">${resource.resourceName}</a> <span><fmt:formatDate value="${resource.updateDate}" pattern="yyyy年MM月dd日"/>更新&nbsp;&nbsp;已有${resource.browseCount}次浏览&nbsp;&nbsp;</span></li>
		  <li class="tag_list_tags">关键字：<span>${resource.keyWord}</span>等 </li>
		  </ul>
  </c:forEach>
  </c:if>
   <c:if test="${obj.resourceType=='r_zti'}">
   <c:forEach items="${obj.resources}" var="resource" varStatus="row">
  <ul class="tag_list">
  <li class="tag_list_title"><img src="../skins/images/icon_document.jpg" width="26" height="26" align="absmiddle" /> <a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&isSy=${obj.isSy}" target="main_frame">${resource.resourceName}</a> <span><fmt:formatDate value="${resource.updateDate}" pattern="yyyy年MM月dd日"/>更新&nbsp;&nbsp;已有${resource.browseCount}次浏览&nbsp;&nbsp;</span></li>
  <li class="tag_list_tags">关键字：<span>${resource.keyWord}</span>等 </li>
  </ul>
  </c:forEach>
  </c:if>
</dl>
    </div>
    </form>
  </div>
</div>

</body>
<script language="JavaScript">
//查询数据  
function query(type){
	if(type == '1'){
		 $('#keyWord').val($('#gjz_rkl').val());
		 $('#resourceType').val('r_rkl');
		
	}
	if(type == '2'){
		$('#keyWord').val($('#gjz_frl').val());
		 $('#resourceType').val('r_frl');
	}
	if(type == '3'){
		$('#keyWord').val($('#gjz_fwl').val());
		$('#resourceType').val('r_fwl');
	}
	if(type == '4'){
		$('#keyWord').val($('#gjz_ztl').val());
		$('#resourceType').val('r_zti');
	}
		$('#queryForm').submit();
}
function query1(type)
{
	if(type=='068001')
	{
		$('#keyWord').val($('#gjz_frl').val());
		$('#provideDepId').val('068001');
		$('#resourceType').val('r_zti');
		}
	if(type=='017001')
	{
		$('#keyWord').val($('#gjz_frl').val());
		$('#provideDepId').val('017001');
		$('#resourceType').val('r_zti');
		}
	if(type=='X07060')
	{
		$('#keyWord').val($('#gjz_frl').val());
		$('#provideDepId').val('X07060');
		$('#resourceType').val('r_zti');
		}
	if(type=='035001')
	{
		$('#keyWord').val($('#gjz_frl').val());
		$('#provideDepId').val('035001');
		$('#resourceType').val('r_zti');
		}
	if(type=='037001')
	{
		$('#keyWord').val($('#gjz_frl').val());
		$('#provideDepId').val('037001');
		$('#resourceType').val('r_zti');
		}

	$('#queryForm').submit();
	}

	</script>

</html>


