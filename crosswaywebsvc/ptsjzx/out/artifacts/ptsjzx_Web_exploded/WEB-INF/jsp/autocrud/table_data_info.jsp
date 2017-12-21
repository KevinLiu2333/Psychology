<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<div class="pageContent">
	<form method="post" action="autocrud/addRow?tableId=${obj.tableId}" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<!-- 表单 -->
		<div class="pageFormContent" layoutH="55" >
		<table width="100%" cellspacing="1" cellpadding="0">
			<c:forEach var="column" items="${obj.columnList}">
				<!-- 循环所有表中的字段开始 -->
				<c:if test="${column.isShow == 0}">
					<!-- 不显示的字段开始 -->
					<c:if test="${column.isFk == 1}">
						<!-- 是fk开始 -->
						<c:if test="${empty obj.fkId}">
							<!-- fk为空 -->
							<input type="hidden" name="${column.colId}" value="${obj.recordMap[column.nameLetter]}">
						</c:if>
						<c:if test="${not empty obj.fkId}">
							<!-- fk不为空 -->
							<input type="hidden" name="${column.colId}" value="${obj.fkId}">
						</c:if>
						<!-- 是fk结束 -->
					</c:if>
					<c:if test="${column.isFk == 0}">
						<!-- 不是fk -->
						<input type="hidden" name="${column.colId}" value="${obj.recordMap[column.nameLetter]}">
					</c:if>
					<!-- 不显示的字段结束-->
				</c:if>
	  			<c:if test="${column.isShow == 1}">
					<!-- 显示的字段开始 -->
					<tr>
						<td width="30%" align="right" height="30">
							<!-- 必录项前打红星 -->
							<c:if test="${column.isMandatory == 1}">
								<font color='#FF0000'>*</font>
							</c:if>
							<c:if test="${column.isFk == 1}">
								(外键)
							</c:if>
							${column.name}
							<!-- 数值型字段加单位 -->
							<c:if test="${column.dataType == 2 && not empty column.dataUnit}">
								(${column.dataUnit})
							</c:if>
							：
						</td>
						<td width="70%" align="left" style="text-align:left" height="40">
							<c:if test="${column.dataType == 1}">
								<!-- 字符型字段开始 -->
								<c:if test="${column.isFk == 1}">
									<!-- 是fk -->
									<input type="text" class="required" name="${column.colId}" readonly="readonly" size="50"
											value="<c:if test="${empty obj.fkId}">${obj.recordMap[column.nameLetter]}</c:if><c:if test="${not empty obj.fkId}">${obj.fkId}</c:if>" />
									<a class="btnLook" href="autocrud/toTableDataLookUpList?childTableId=${obj.tableId}&fkName=${column.colId}" lookupGroup="">查找带回</a>
								</c:if>
								<c:if test="${column.isFk == 0}">
									<!-- 不是fk -->
									<input type="text" name="${column.colId}" maxlength="${column.dataLength}"
											class="<c:if test="${column.isMandatory == 1}">required</c:if>"
											value="${obj.recordMap[column.nameLetter]}" size="50" />
								</c:if>
								<!--字符型字段结束 -->
							</c:if>
							<c:if test="${column.dataType == 2}">
								<!-- 数值型字段开始 -->
								<input type="text" name="${column.colId}" maxlength="${column.dataLength}"
										class="<c:if test="${column.isMandatory == 1}">required</c:if> number"
										value="${obj.recordMap[column.nameLetter]}" size="50" />
								<!-- 数值型字段结束 -->
							</c:if>
							<c:if test="${column.dataType == 3}">
								<!-- 日期型字段开始 -->
								<input type="text" name="${column.colId}"
										class="<c:if test="${column.isMandatory == 1}">required</c:if> date"
										value="<fmt:formatDate pattern="yyyy-MM-dd" value="${obj.recordMap[column.nameLetter]}"/>"/>
								<!-- 日期型字段结束 -->
							</c:if>
							<c:if test="${column.dataType == 4}">
								<!-- 字典型字段开始 -->
								<wd:select name="${column.colId}" dicCode="${column.dicId}" defaultValue="${obj.recordMap[column.nameLetter]}" initValue="-----"/>
								<!-- 字典型字段结束 -->
							</c:if>						
						</td>
					</tr>
					<!-- 显示的字段结束 -->
				</c:if>
				<!-- 循环所有表中的字段结束 -->
			</c:forEach>
			<c:if test="${obj.table.nameLetter == 'SYS_APP_TABLES' && empty obj.recordMap}">
				<!-- 如果是配置表,则可以选择是否默认生成全部字段 -->
				<tr>
					<td width="30%" align="right" height="30">
						是否生成字段：
					</td>
					<td width="70%" align="left" style="text-align:left" height="40">
						<input type="radio" name="isGenAllCols" value="1" checked/>是
						<input type="radio" name="isGenAllCols" value="0" />否
					</td>
				</tr>
			</c:if>
		</table>
		</div>
		<!-- 操作工具栏  -->
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
