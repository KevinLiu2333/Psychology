<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>

<h5  class="page-header"><b>申请基本信息</b></h5>
<div class="row">
    <div class="col-md-12 form-group">
        申请单位：${sessionScope.session_user.unitName}
    </div>
    <div class="col-md-12 form-group">
        申请时间：<jsp:useBean id="now" class="java.util.Date" /><fmt:formatDate value="${now}" type="date" dateStyle="full"/>
    </div>
     <div class="col-md-12 form-group">
        申请主题：<input type="text" name="zyApply.applyTopic" style="width:785px"/>
    </div>
    <div class="col-md-12 form-group">
        联系电话：<input type="text" name="zyApply.linkmanPhone" style="width:785px"/>
    </div>
    <div class="col-md-12 form-group">
       申请原因： <textarea name="zyApply.applyReason"  class="form-control" rows="3" style="width:865px;"></textarea>
    </div>

</div>

<h5 class="page-header"><b>申请数据信息</b></h5>

<table class="table table-striped">
    <thead>
    <tr>
        <th>序号</th>
        <th>选取状态</th>
        <th>资源项名称</th>
        <th>公开类型</th>
        <th>资源项说明</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${obj.zyItemList != null }">
    	<c:forEach items="${obj.zyItemList}" var="zyItem" varStatus="row">
		   	<tr>
		       <td valign="middle">${row.index+1 }</td>
				<td>
				<c:if test="${zyItem.openType == '普遍共享' }">
		          <input type="checkbox" name="zyItemIds" value="${zyItem.zyItemId }" checked>
		        </c:if>
		        <c:if test="${zyItem.openType == '按需共享' }">
		        	<input type="checkbox" name="zyItemIds" value="${zyItem.zyItemId }" checked>
		        </c:if>
		       </td>
		       <td>${zyItem.cnName }</td>
		       <td>${zyItem.openType }</td>
		       <td>${zyItem.itemDesc }</td>
		   </tr>
    	</c:forEach>
    </c:if>
    </tbody>
</table>

