<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<c:set scope="request" var="pageForm" value="queryForm" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>资源目录</title>
<link href="${ctx}/skins/query/css/style.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<style>
.tag_content {
    float:left;
	width:80%;
	margin-left:20px;
	padding:30px 0;
	
	}
.leftmu{
    float:left;
    width:12%;
    height:300px;
    margin-left:30px;	
    margin-top:15px;
}
.borders {
     padding:0px; 
     border-top:1px solid #fff;
     border-bottom:1px solid #e5e5e5; 
}
#con_one_1,#con_one_2,#con_one_3,#con_one_4,#con_one_5 {
    float:left;
    width:90%;
	border:1px solid #CCC;
	/* border-top:none; */
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
	width:300px;
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
	font-size:14px;
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
	width:81%;
	margin:20px auto 0 auto;
	background:#f6f7f7;
	border:1px solid #e3e3e3;
	padding:20px 2%;
	}
.tag_list li:first-child {
	margin-bottom:10px;
	}
	
.leftmu dt { height:30px;width:80%; padding:0 15px; background:#37b; font:200 18px/30px Microsoft Yahei; color:#fff; display:inline-block;text-align:center; }
.leftmu dd { height:30px;text-align:center; margin-top:5px;margin-left:0; padding:0;background:#efffff;}
.leftmu dd a { line-height:30px;text-align:center; text-decoration:none;color:black;}
</style>
</head>
<body>
<div class="leftmu">
   <div class="borders">
   <dl>
      <dt>基础数据</dt>
        <dd id="d1">
               <a href="${ctx}/zymlgl/toContentIndex?applyFlag=1&dataflag=0">
			         人口数据(3)
			   </a>
		</dd>
		 <dd id="d1">
               <a href="${ctx}/zymlgl/toContentIndex?applyFlag=1&dataflag=1">
			       法人数据(7)
			   </a>
		</dd>
	</dl>
   </div>
   <div class="borders">
   <dl>
      <dt>专题类型</dt>
        <dd id="d1">
               <a href="${ctx}/zymlgl/toContentIndex?applyFlag=1&resourceType=r_frl">
			          法人类型(${obj.r_frl })
			   </a>
		</dd>
		 <dd id="d1">
               <a href="${ctx}/zymlgl/toContentIndex?applyFlag=1&resourceType=r_rkl">
			       人口类型(${obj.r_rkl })
			   </a>
		</dd>
		 <dd id="d1">
               <a href="${ctx}/zymlgl/toContentIndex?applyFlag=1&resourceType=r_cyjjl">
			        经济类型(${obj.r_cyjjl })
			   </a>
		</dd>
	</dl>
   </div>
   <div class="borders" id="left">
    <dl>
      <dt>部门资源</dt>
       <c:forEach items="${obj.countResult }" var="result">
        <dd id="d1">
               <a href="${ctx}/zymlgl/toContentIndex?applyFlag=1&dept=${result.dept}">
			       ${result.deptname }(${result.count })
			   </a>
		</dd>
		</c:forEach>
	</dl>  
   </div>

</div>
<div class="tag_content">
  
  <div id="con_one_1">
  <form id="queryForm" action="${ctx}/zymlgl/toContentIndex" method="post">
  <input type="hidden" name="resourceType" id="resourceType" value="${obj.resourceType }"/>
  <input type="hidden" name="dataflag" id="dataflag" value="${obj.dataflag }"/>
  <input type="hidden" name="dept" id="dept" value="${obj.name }"/>
  <div class="tag_search">
    ${obj.name1 }<wd:dicvalue dicId="1003" dicCode="${obj.name }"/><input type="text" name="resourcename" id="resourcename" class="tag_search_input" /><input type="button" name="button" class="tag_search_button" onclick="query(1)" value="提交" />
   &nbsp;&nbsp;&nbsp;&nbsp;
   排序：
   <%-- <select name="orderBy" class="tag_search_select"  id="orderBy" onchange="changeValue('1')">
                    <c:if test="${obj.orderBy eq 'updateDate'}">
			   			<option value="">---请选择---</option>
			   			<option value="updateDate" selected="selected">更新时间</option>
			   			<option value="browseCount">浏览次数</option>
			  				<option value="RESOURCE_NAME">资源名称</option>
			   		</c:if>
			   		<c:if test="${obj.orderBy eq 'browseCount'}">
			   			<option value="">---请选择---</option>
			   			<option value="browseCount" selected="selected">浏览次数</option>
			   			<option value="updateDate">更新时间</option>
			   			<option value="RESOURCE_NAME">资源名称</option>
			   		</c:if>
			   		<c:if test="${obj.orderBy eq 'dowloadCount'}">
			   			<option value="">---请选择---</option>
			   			<option value="updateDate">更新时间</option>
			  				<option value="browseCount">浏览次数</option>
			   			<option value="RESOURCE_NAME" selected="selected">资源名称</option>
			   		</c:if>
			   		<c:if test="${obj.orderBy eq null || obj.orderBy eq ''}">
			   			<option value="">---请选择---</option>
			   			<option value="updateDate">更新时间</option>
			  				<option value="browseCount">浏览次数</option>
			  				<option value="RESOURCE_NAME">资源名称</option>
			   		</c:if>
   </select> --%>
   <wd:select id="orderBy" name="orderBy" dicCode="2010" className="dfinput" initValue="---请选择---" defaultValue="${obj.orderBy }" onchange="changeValue('1')"/>
  </div>
  <!-- </form> -->
   <c:forEach items="${obj.resourcelist}" var="resource" varStatus="row">
  <ul class="tag_list">
  <li class="tag_list_title"><img src="../skins/images/icon_document.jpg" width="26" height="26" align="absmiddle" /> <a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&applyFlag=${obj.applyFlag}&isSy=${obj.isSy}" target="main_frame">${resource.resourceName}</a> <span><fmt:formatDate value="${resource.updateDate}" pattern="yyyy年MM月dd日"/>更新&nbsp;&nbsp;已有
  ${resource.browseCount}
  次浏览&nbsp;&nbsp;</span></li>
  <%-- <li class="tag_list_tags">关键字：<span>${resource.keyWord}</span> </li> --%>
  </ul>
  </c:forEach>
   <div>
		<table width="96%" class="tables">
			<tr>
				<td><jsp:include page="/common/pzgl/pager-iframe.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
</div>
</body>
<script type="text/javascript"> 

	//排序条件切换
	function changeValue(type){
		 if(type == '1'){
			  $('#orderBy').val($('#orderBy').val());
		  }
		  $('#queryForm').submit();
	}
	
	//查询数据  
    function query(type){
    	$('#queryForm').submit();
    }
    
</script>
</html>

