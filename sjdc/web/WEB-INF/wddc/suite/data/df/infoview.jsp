<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${sys_title}</title>
		<%@ include file="/cj/meta.jsp" %>
		<!-- Loading Bootstrap -->
   		<link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   		<link href="${ctx}/wddc/tiles/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
  	 	<!--self-->
   		<link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    	<!--font-awesome-->
    	<link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    	<!-- Loading jquery -->
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
    	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
		<style>
	    pre {outline: 1px solid #ccc; padding: 5px; margin: 5px; }
	    .string { color: green; }
	    .number { color: darkorange; }
	    .boolean { color: blue; }
	    .null { color: magenta; }
	    .key { color: red; }
		</style>
	</head>
<body>
    
	<div class='container'>
	<h3 id="disable-responsive2" class="page-header" style="margin-top:0px;">统计结果
	<span  style="float: right;">
		
	</span>
	</h3>
	<div class="row">
		<div class="col-md-12 form-group">
			统计名称：
			${obj.info.name }
		</div>
		<div class="col-md-12 form-group">
			服务调用id：
			${obj.info.id }
		</div>
		<div class="col-md-12 form-group">
			服务调用示例：
			http://[ip]:[端口]/wddc/fw/ptservices?unitKey=***&fwCode=***&id=${obj.info.id}
		</div>
		<div class="col-md-12 form-group">
			<c:if test="${obj.data!=null}">
			最近缓存时间：
			<fmt:formatDate value="${obj.data.updatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			<p></p>
			结果预览：
				<pre id="json"></pre>
			</c:if>
			<c:if test="${obj.data==null}">
			结果预览：
				<pre id="json1"><img src="${ctx }/wddc/skins/images/loading.gif"></img></pre>
			</c:if>
		</div>
	</div>
	</div>
	<p align="center">
       	<button type="button" class="btn btn-warning" onclick="window.close()" style="width: 100px">关闭</button>
	</p>
	
	
	
</body>
<script type="text/javascript">
	function syntaxHighlight(json) {
	    if (typeof json != 'string') {
	        json = JSON.stringify(json, undefined, 2);
	    }
	    json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
	    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
	        var cls = 'number';
	        if (/^"/.test(match)) {
	            if (/:$/.test(match)) {
	                cls = 'key';
	            } else {
	                cls = 'string';
	            }
	        } else if (/true|false/.test(match)) {
	            cls = 'boolean';
	        } else if (/null/.test(match)) {
	            cls = 'null';
	        }
	        return '<span class="' + cls + '">' + match + '</span>';
	    });
	}
	var url = "${ctx}/fw/ptservices?unitKey=SJ20161447233004873&fwCode=fw00013&id=${obj.info.id}";
	$.ajax({
		type:"get",
		url:url,
		dataType: 'text',
		success:function(result){
		$('#json1').html(syntaxHighlight(result));
			}
		});
</script>
<script type="text/javascript">
	function syntaxHighlight(json) {
	    if (typeof json != 'string') {
	        json = JSON.stringify(json, undefined, 2);
	    }
	    json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
	    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
	        var cls = 'number';
	        if (/^"/.test(match)) {
	            if (/:$/.test(match)) {
	                cls = 'key';
	            } else {
	                cls = 'string';
	            }
	        } else if (/true|false/.test(match)) {
	            cls = 'boolean';
	        } else if (/null/.test(match)) {
	            cls = 'null';
	        }
	        return '<span class="' + cls + '">' + match + '</span>';
	    });
	}
	$('#json').html(syntaxHighlight(${obj.data.data}));
</script>
</html>