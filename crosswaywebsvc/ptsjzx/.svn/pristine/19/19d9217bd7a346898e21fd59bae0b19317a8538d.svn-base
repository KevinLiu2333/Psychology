<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<style>
.guidestep {
	position: absolute;
	width: 150px;
	height: 75px;
	background-color: #006633;
	line-height: 75px;
	text-align: center;
	vertical-align: center;
	
}
</style>
<div id="queryGuideDiv">
	<div class="guidestep" style="top:50px;left:150px;">
		<a id="queryGuide_a1" href="query/detailquery/toQueryThemeCreate" target="navTab" rel="guideNavTab"><span style="font-size: 22px;color: #ffffff">1、新建主题</span></a>
	</div>
	<div class="guidestep" style="top:50px;left:350px;">
		<a id="queryGuide_a2" href="autocrud/toTableDataList?tableId=1004" target="navTab" rel="guideNavTab"><span style="font-size: 22px;color: #ffffff">2、编辑字段</span></a>
	</div>
	<div class="guidestep" style="top:200px;left:50px;">
		<a id="queryGuide_a3" href="query/detailquery/toDefine" target="navTab" rel="guideNavTab"><span style="font-size: 22px;color: #ffffff">3、新建查询</span></a>
	</div>
	<div class="guidestep" style="top:200px;left:250px;">
		<a id="queryGuide_a4" href="query/statForm/toDefine" target="navTab" rel="guideNavTab"><span style="font-size: 22px;color: #ffffff">3、新建表格</span></a>
	</div>
	<div class="guidestep" style="top:200px;left:450px;">
		<a id="queryGuide_a5" href="query/statChart/toDefine" target="navTab" rel="guideNavTab"><span style="font-size: 22px;color: #ffffff">3、新建图表</span></a>
	</div>
	<div class="guidestep" style="top:300px;left:50px;">
		<a id="queryGuide_a6" href="query/detailquery/toTheme" target="navTab" rel="guideNavTab"><span style="font-size: 22px;color: #ffffff">3、编辑查询</span></a>
	</div>
	<div class="guidestep" style="top:300px;left:250px;">
		<a id="queryGuide_a7" href="query/statForm/toTheme" target="navTab" rel="guideNavTab"><span style="font-size: 22px;color: #ffffff">3、编辑表格</span></a>
	</div>
	<div class="guidestep" style="top:300px;left:450px;">
		<a id="queryGuide_a8" href="query/statChart/toTheme" target="navTab" rel="guideNavTab"><span style="font-size: 22px;color: #ffffff">3、编辑图表</span></a>
	</div>		
</div>
<div style="position: absolute; left: 80px; top: 400px; width: 300px">
	<span style="font-size: 16px">提示：除了新建主题外，其它操作需要输入主题ID，请从右侧列表中选择</span>
</div>

<div style="position: absolute;left: 720px; top:50px">
	<span style="font-size: 20px">当前配置的主题ID:</span>
	<input type="text" id="queryThemeId" value="" name="queryThemeId" readonly="readonly"></input>
</div>
<div style="position: absolute; left: 720px; top: 100px">
	<span style="font-size:20px">当前存在的主题<br/></span>
	<ul id="allThemesUl" style="cursor: pointer;">
	<c:forEach var="theme" items="${obj.themeList}">
		<li themeId="${theme.themeId}"><span style="font-size:16px">${theme.themeId} 【${theme.name}】 ${theme.viewName}</span></li>
	</c:forEach>
	</ul>
</div>

<script>
$(function(){
	$('#queryGuide_a3,#queryGuide_a4,#queryGuide_a5,#queryGuide_a6,#queryGuide_a7,#queryGuide_a8').click(function(){
		var queryThemeId = $('#queryThemeId').val();
		if (queryThemeId != null) {
			var href = $(this).attr('href');
			var index = href.indexOf('?');
			if (index > 0) {
				href = href.substring(0, index); //防止重复拼接参数
			}
			$(this).attr('href', href + '?themeId=' + queryThemeId);
		}
	});
	$('#queryGuide_a2').click(function(){
		var queryThemeId = $('#queryThemeId').val();
		if (queryThemeId != null) {
			var href = $(this).attr('href');
			var index = href.indexOf('&');
			if (index > 0) {
				href = href.substring(0, index); //防止重复拼接参数
			}
			$(this).attr('href', href + '&fkId=' + queryThemeId);
		}
	});
	$('#allThemesUl li').click(function(){
		var themeId = $(this).attr('themeId');
		$('#queryThemeId').val(themeId);
	});
});
</script>