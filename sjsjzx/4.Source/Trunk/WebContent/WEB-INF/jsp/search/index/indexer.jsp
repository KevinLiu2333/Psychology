<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-创建索引 - 门户检索系统</title>
<link href="${ctx }/skins/search/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/skins/search/js/jquery.js"></script>
<script type="text/javascript">
function startIndex(){
	var indexType = $('input:radio:checked').val();
	$('#indexResult').show();
	$('#indexResult').html("索引进行中...");
	$.post("${ctx}/search/index/startIndex", 
			{ Action: "post",indexType:indexType},
			function (data, textStatus){
				if(data.result == 1){
					$('#indexResult').html("索引结果：   <span class='red'>成功</span>");
				}else{
					$('#indexResult').html("索引结果：   <span class='green'>失败</span>");
				}
			 }				
			, "json");
}
function config(){
	$('#config').toggle();
}
function showInfo(){
	$('#info').toggle();
}
</script>
</head>
<body>
<div align="center">
	<div class="div_dark" >
		<table>
			<tr>
				<td>
				<span class="tittle_1">创建索引</span>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center" >
						<div style="float: left"><input class="comment_button"  type="button" value="开始索引" onclick="startIndex()"/></div>
						<div style="float: left"><input class="comment_button" type="button" value="基本信息" onclick="showInfo()"/></div>
						<div style="float: left"><input class="comment_button" type="button" value="高级设置" onclick="config()"/></div>
						<div style="float: left"><input class="comment_button" type="button" value="使用帮助" onclick="javascript:window.open('${ctx}/search/index/index_help')"/></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div  id="info" style="display: none;" class="div_dark"  align="left">
						<ul class="nonePr">
							<li ><span>索引所在配置文件：</span>${obj.conf.configFileName}</li>
							<li ><span>索引列表：</span>
								<div>
									<ul>
										<c:forEach items="${obj.conf.indexes}" var="idx" varStatus="vs">
											<li class="nonePr">&gt;&gt; 索引${vs.index+1 }</li>
											<li>索引ID： ${idx.id}</li>
											<li>索引文件路径： ${idx.indexPath}</li>
											<li>分词器： ${idx.analyzerClass}</li>
										</c:forEach>
									</ul>
								</div>
							</li>
							<li><span>资源列表：</span>
								<div>
									<ul>
										<c:forEach items="${obj.conf.fetchers}" var="res" varStatus="vs">
											<li class="nonePr">&gt;&gt; 资源${vs.index+1 }</li>
											<li>资源ID： ${res.id}</li>
											<li>对应索引： ${res.indexId}</li>
											<%-- <c:if test="${res.fetcherType == fileFetcherType}">
											<li>资源路径： ${res.filePath}</li>
											</c:if> --%>
										</c:forEach>
									</ul>
								</div>
							</li>
						</ul>
	</div>
	<div id="config" class="div_dark" style="display: none;" >
		<ul class="nonePr">
			<li><span>索引类型：</span><input value="fullIndex" name="indexType" type="radio"/>完全索引
									   <input value="incrementIndex" name="indexType" type="radio" checked="checked"/>增量索引
			
			</li>
		</ul>
	</div>
	<div id="indexResult" class="div_dark" style="display: none;">
			<div class="result_item"></div>
	</div>
</div>
</body>
</html>