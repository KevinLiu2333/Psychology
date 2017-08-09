<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	
</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">

    <div class="row">
        <div class="col-md-12">
        
            <form id="tagsForm" name="tagsForm" action="${ctx}/kernel/tag/addTagInfo" method="post" target="">
            <h2 class="page-header" style="margin-top: 5px">数据标签维护
                <span style="float: right;padding-right: 10px;font-size: 16px">
                <c:if test="${ empty obj.catalog}">
                <select name="catalog"  id="catalog"  onchange="changeCatalog()" style="width:200px;height: 35px">
		            <option value="">--标签分类--</option>
		            <option value="11">服务数据标签</option>
		            <option value="12">服务标签</option>
		            <option value="13">图表标签</option>
		            <option value="14">统计报表标签</option>
		            <option value="15">数据采集标签</option>
		        </select>
		        </c:if>
                <input type="text" class=" search-query" style="width:200px;height:35px" onkeypress="addType(event,this)" placeholder="添加新类型" />
                </span>
            </h2>
            	<c:if test="${ not empty obj.catalog}">
                <input type="hidden" name="catalog"  id="catalog"  value="${obj.catalog}">
                </c:if>
                <input type="hidden" name="typeName" id="typeName" value="">
                <input type="hidden" name="tagName"  id="tagName"  value="">
            </form>

			<c:forEach var="tagType" items="${obj.tagTypeList}">
			    <div class="panel panel-info">
			        <div class="panel-heading">
			            <h3 class="panel-title">${tagType.typeName}</h3>
			        </div>
			        <div class="panel-body">
			            <c:forEach var="tagInfo" items="${obj.tagInfoList}">
			                <c:if test="${tagInfo.type == tagType.typeName}">
				                <div class="btn-group">
				                    <button data-toggle="dropdown" class="btn btn-info btn-sm dropdown-toggle"  >
				                    ${tagInfo.showName}
				                        <span class="ace-icon fa fa-caret-down icon-on-right"></span>
				                    </button>
				                    <ul class="dropdown-menu dropdown-info dropdown-menu-right">
				                        <li><a href="${ctx}/kernel/tag/deleteTagInfo?catalog=${tagInfo.catalog}&tagId=${tagInfo.tagId}">删除</a></li>
				                        <li class="divider"></li>
				                        <li><a href="${ctx}/kernel/tag/movePosition?catalog=${tagInfo.catalog}&tagId=${tagInfo.tagId}&position=forward">前移动</a></li>
				                        <li><a href="${ctx}/kernel/tag/movePosition?catalog=${tagInfo.catalog}&tagId=${tagInfo.tagId}&position=backward">后移动</a></li>
				                    </ul>
				                </div>
			                </c:if>
			            </c:forEach>
	           <input type="text" class=" search-query" style="width:100px;height:35px" onkeypress="addTag(event,this,'${tagType.typeName}')"   placeholder="添加新标签" />
	       </div>
	   </div>
	   </c:forEach>

	</div>
  </div>

</div>
</body>
<script>
	function changeCatalog(){
		window.location.href="${ctx}/kernel/tag/mgr/"+$("#catalog").val();
	}
    function addTag(e,tag,typeName){ //传入 event
        var e = e || window.event;
        if(e.keyCode == 13){
            if($("#catalog").val() == ""){
                alert("请选择标签分类");
                return;
            }
            //alert("add tag:"+obj.value);
            $("#tagsForm").attr("action","${ctx}/kernel/tag/addTagInfo");
            $("#tagName").val(tag.value);
            $("#typeName").val(typeName);
            $("#tagsForm").submit();
        }
    }
    function addType(e,obj){ //传入 event
        var e = e || window.event;
        if(e.keyCode == 13){
        	if($("#catalog").val() == ""){
                alert("请选择标签分类");
                return;
            }
            //alert("add type:"+obj.value);
            $("#tagsForm").attr("action","${ctx}/kernel/tag/toAddType");
            $("#typeName").val(obj.value);
            $("#tagsForm").submit();
        }
    }
</script>
</html>
