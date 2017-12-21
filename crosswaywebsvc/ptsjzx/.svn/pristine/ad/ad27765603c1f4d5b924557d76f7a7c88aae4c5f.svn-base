<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源目录</title>
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
	width:81%;
	margin:20px auto 0 auto;
	background:#f6f7f7;
	border:1px solid #e3e3e3;
	padding:20px 2%;
	}
.tag_list li:first-child {
	margin-bottom:10px;
	}


</style>

<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<script>
function setTab(m,n){
 var tli=document.getElementById("menu"+m).getElementsByTagName("li");
 var mli=document.getElementById("main"+m).getElementsByTagName("ul");
 for(i=0;i<tli.length;i++){
  tli[i].className=i==n?"hover":"";
  mli[i].style.display=i==n?"block":"none";
 }
}
</script>
</head>
<body>
	<form id="queryForm" action="${ctx}/zymlgl/toDeptIndex" method="post">
		<input type="hidden" id="orderBy" name="orderBy">
		<input type="hidden" id="keyWord" name="keyWord">
		<input type="hidden" id="provideDepartment" name="provideDepartment" value="${obj.provideDepartment}">
		<br>
		<div id="contentOrDept" align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			资源分类：&nbsp;&nbsp;&nbsp;
			<input name="searchType" type="radio" value="content" class="searchType">&nbsp;按专题&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="searchType" type="radio" checked="checked" value="dept" class="searchType">&nbsp;按部门
		</div>
		<br>
		
	<div class="tag_content">

 <ul class="tag" >
  <li id="one1" onclick="setTab1('one',1,3)" class="active"><a href="#tab${row.index+1}">普陀区科委</a></li>
 </ul>
 
 <div class="clearfix"></div>
 
  <div id="con_one_1">
  
  <div class="tag_search">
   <input type="text" name="textfield" id="gjz" class="tag_search_input" /><input type="submit" name="button" class="tag_search_button" onclick="query(1)" value="提交" />
   &nbsp;&nbsp;&nbsp;&nbsp;
   排序：
  <select id="orderByValue" name="orderByValue" onchange="changeValue()">
	    		<c:if test="${obj.orderBy eq 'updateDate'}">
	    			<option value="">---请选择---</option>
	    			<option value="updateDate" selected="selected">更新时间</option>
	    			<option value="browseCount">浏览次数</option>
    				<option value="dowloadCount">下载量</option>
	    		</c:if>
	    		<c:if test="${obj.orderBy eq 'browseCount'}">
	    			<option value="">---请选择---</option>
	    			<option value="browseCount" selected="selected">浏览次数</option>
	    			<option value="updateDate">更新时间</option>
	    			<option value="dowloadCount">下载量</option>
	    		</c:if>
	    		<c:if test="${obj.orderBy eq 'dowloadCount'}">
	    			<option value="">---请选择---</option>
	    			<option value="updateDate">更新时间</option>
    				<option value="browseCount">浏览次数</option>
	    			<option value="dowloadCount" selected="selected">下载量</option>
	    		</c:if>
	    		<c:if test="${obj.orderBy eq null || obj.orderBy eq ''}">
	    			<option value="">---请选择---</option>
	    			<option value="updateDate">更新时间</option>
    				<option value="browseCount">浏览次数</option>
    				<option value="dowloadCount">下载量</option>
	    		</c:if>
	    	</select>
  </div>
    <c:forEach items="${obj.resources}" var="resource" varStatus="row">
  <ul class="tag_list">
  <li class="tag_list_title"><img src="../skins/images/icon_document.jpg" width="26" height="26" align="absmiddle" /> <a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&isSy=${obj.isSy}" target="main_frame">${resource.resourceName}</a> <span><fmt:formatDate value="${resource.updateDate}" pattern="yyyy年MM月dd日"/>更新&nbsp;&nbsp;已有${resource.browseCount}次浏览&nbsp;&nbsp;</span></li>
  <li class="tag_list_tags">关键字：<span>${resource.keyWord}</span>等 </li>
  </ul>
  </c:forEach>
</div>
 
</div>

</form>




</body>
<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
      
      //查询数据  
      function query(type){
      	if(type == '1'){
      		$('#pageNo').val('1');
      	}
      	$('#keyWord').val($('#gjz').val());
      	$('#queryForm').submit();
      }
      
      function changeValue(){
    	  $('#orderBy').val($('#orderByValue').val());
    	  $('#queryForm').submit();
      }
      
      $(document).ready(function(){
    	  $(".searchType").click(function(){
    		  var searchTypeVar = $('#contentOrDept input[name="searchType"]:checked ').val(); 
    		  if(searchTypeVar == "content"){
    			  $('#queryForm').attr('action','${ctx}/zymlgl/toContentIndex');
    			  $('#queryForm').submit();
    		  }
    		  if(searchTypeVar == "dept"){
    			  $('#queryForm').attr('action','${ctx}/zymlgl/toDeptIndex');
    			  $('#queryForm').submit();
    		  }
    	  });
    	});
</script>

</html>