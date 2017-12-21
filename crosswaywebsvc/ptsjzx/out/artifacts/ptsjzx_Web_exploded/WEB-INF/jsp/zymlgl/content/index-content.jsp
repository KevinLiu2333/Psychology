<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源目录</title>
<script type="text/javascript" src="${ctx }/tiles/scripts/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="${ctx}/tiles/scripts/jquery.idTabs.min.js"></script>
<style type="text/css">
*{font-size:12pt;border:1;margin:0;padding:0;}
.body{font-family:'微软雅黑'; margin:0 auto;min-width:980px;}
table{font-family:'微软雅黑';border-collapse:collapse;border-spacing: 0;width:580px;height: 80px;}
.midButton {
	position: relative;
	overflow: visible;
	display: inline-block;
	padding: 0.4em 0.8em;
	border: 1px solid #6495ED;
	margin: 0;
	width: 100px;
	text-decoration: none;
	text-shadow: 1px 1px 0 #fff;
	font-family: "Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica,
		sans-serif;
	font-size: 14px;
	color: #333;
	font-weight: bold;
	white-space: nowrap;
	cursor: pointer;
	outline: none;
	background-color: #fff;
	-webkit-background-clip: padding;
	-moz-background-clip: padding;
	-o-background-clip: padding-box;
	/*background-clip: padding-box;*/
	/* commented out due to Opera 11.10 bug */
	-webkit-border-radius: 0.2em;
	-moz-border-radius: 0.2em;
	/* IE hacks */
	zoom: 1;
	*display: inline;
}

.midButton:hover {
	background-color: #6495ED;
	color: #fff;
}

.minButton {
	position: relative;
	overflow: visible;
	display: inline-block;
	padding: 0.25em 0.5em;
	border: 1px solid #6495ED;
	margin: 0;
	width: 80px;
	text-decoration: none;
	text-shadow: 1px 1px 0 #fff;
	font-family: "Microsoft YaHei", "微软雅黑", "宋体", Arial, Helvetica,
		sans-serif;
	font-size: 14px;
	color: #333;
	font-weight: bold;
	white-space: nowrap;
	cursor: pointer;
	outline: none;
	background-color: #fff;
	-webkit-background-clip: padding;
	-moz-background-clip: padding;
	-o-background-clip: padding-box;
	/*background-clip: padding-box;*/
	/* commented out due to Opera 11.10 bug */
	-webkit-border-radius: 0.2em;
	-moz-border-radius: 0.2em;
	/* IE hacks */
	zoom: 1;
	*display: inline;
}

.minButton:hover {
	background-color: #6495ED;
	color: #fff;
}
</style>
</head>
<body> 
	<form id="queryForm" action="${ctx}/zymlgl/toContentCatalog" method="post">
		<input type="hidden" id="orderBy" name="orderBy">
		<input type="hidden" id="keyWord" name="keyWord">
		<input type="hidden" id="resourceType" name="resourceType" value="${obj.resourceType}">
	    <div align="center">
		    <input type="text" id="gjz" class="dfinput" style="margin-left: 30px;margin-top: 20px;width: 500px;height: 32px;" value="${obj.keyWord}">
		    &nbsp;&nbsp;<input type="button" class="minButton" style="width:80px;height: 32px;" onclick="query(1)" value="查  询"/>
	    </div>
	    
	    <div style="float:right;margin-right: 120px;margin-top: 20px;">
	    	<font style="font-size: 15px;">排序</font>：
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
	    
	    <p style="margin-bottom: 5px;margin-top:20px;margin-left: 120px;font-size: 15px;">${obj.count}个资源目录</p>
	    
	    <c:forEach items="${obj.resources}" var="resource" varStatus="row">
	    	<div style="margin-top: 20px;margin-left: 120px;margin-right: 120px;">
	    	<fieldset>
	    		<legend><a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}&applyFlag=${obj.applyFlag}" target="main_frame">${resource.resourceName}</a></legend>
	    		<table>   
			           <tr>
				           	<td style="font-size: 15px;"><fmt:formatDate value="${resource.updateDate}" pattern="yyyy年MM月dd日"/>更新&nbsp;&nbsp;
				           		已有<c:if test="${resource.browseCount eq null}">
				           			0
				           		</c:if>
				           		<c:if test="${resource.browseCount !=null}">
				           			${resource.browseCount}
				           		</c:if>
				           		次浏览&nbsp;&nbsp;
				           	</td>
			           	</tr>
			           <tr><td style="font-size: 15px;">关键字：${resource.keyWord}</td></tr>
			    </table>
	    	</fieldset> 
	    </div>
	    </c:forEach>
	    
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
</script>
</html>
