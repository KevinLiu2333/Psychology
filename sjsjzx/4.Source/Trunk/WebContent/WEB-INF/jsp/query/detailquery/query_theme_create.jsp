<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="pageContent">
	<form id="query_themeForm" name="query_themeForm" class="pageForm required-validate" action="query/detailQuery/saveQueryTheme" method="post" onsubmit="return navTabSearch(this);">
		<!-- 表单 -->
		<div class="pageFormContent" layoutH="55" >
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="30%" align="right" height="30">
					主题编号：(当前最大编号为${obj.maxThemeId})
				</td>
				<td width="70%" align="left" style="text-align:left" height="40">
					<input type="text" id="themeId" name="themeId" class="number required"/>
					<font color="red">(请输入四位的数字,不能与已有编号相同)</font>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right" height="30">
					主题描述：
				</td>
				<td width="70%" align="left" style="text-align:left" height="40">
					<input id="themeName" type="text" name="name" class="required"/>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right" height="30">
					主题对应表或视图：
				</td>
				<td width="70%" align="left" style="text-align:left" height="40">
					
					<select id="themeViewName" name="viewName" class="required">
						<option value="">-----</option>
						<c:forEach var="tableName" items="${obj.tableNameList}">
							<option value="${tableName}">${tableName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right" height="30">
					是否有效：
				</td>
				<td width="70%" align="left" style="text-align:left" height="40">
					<wd:select name="validity" dicCode="1004" className="required"/>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right" height="30">
					主题分类：
				</td>
				<td width="70%" align="left" style="text-align:left" height="40">
					<select id="catalog" name="catalog" class="required">
						    <option value="">-----</option>
							<option value="自然人专题">自然人专题</option>
							<option value="法人专题">法人专题</option>
							<option value="经济专题">经济专题</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right" height="30">
					是否自动生成字段：
				</td>
				<td width="70%" align="left" style="text-align:left" height="40">
					<wd:select name="isCreateColumn" dicCode="1004"/>
				</td>
			</tr>																
		</table>
		</div>
		<!-- 操作工具栏  -->
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button id="saveBtn" type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script>
//将新建主题带回到向导页面
$(function(){
	$('#saveBtn').click(function(){
		var themeId = $('#themeId').val();
		var themeName = $('#themeName').val();
		var themeViewName = $('#themeViewName option:selected').text();
		$('#queryThemeId').val(themeId);
		var appendHtml = '<li><span style="font-size:16px">' + themeId + ' 【' + themeName + '】 ' + themeViewName + '</span></li>';
		$('#allThemesUl').append(appendHtml);
	});
});
</script>
