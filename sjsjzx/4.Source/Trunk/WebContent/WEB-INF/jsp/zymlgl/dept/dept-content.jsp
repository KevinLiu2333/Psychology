<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		<input type="hidden" id="orderBy" name="orderBy">
		<input type="hidden" id="keyWord" name="keyWord">
		<input type="hidden" id="provideDepartment" name="provideDepartment" value="${obj.provideDepartment}">
	    <div align="center">
		    <input type="text" id="gjz" class="dfinput" style="margin-left: 30px;margin-top: 20px;width: 400px;height: 32px;" value="${obj.keyWord}">
		    &nbsp;&nbsp;<input type="button" class="minButton" style="width:80px;height: 32px;" onclick="query(1)" value="查  询"/>
	    
	    	<font style="font-size: 15px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;排序</font>：
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
	    
	    <div style="margin-bottom: 5px;margin-top:20px;margin-left: 20px;font-size: 15px;">共&nbsp;${obj.count}&nbsp;个资源目录</div>
	    
	    <c:forEach items="${obj.resources}" var="resource" varStatus="row">
	    	
		    	<fieldset>
		    		<legend>&nbsp;&nbsp;
		    			<a href="${ctx}/zymlgl/toDetail?resourceId=${resource.resourceId}" target="main_frame">${resource.resourceName}</a>
		    		</legend>
		    		<table>
		    			<tr>
		    				<td height="40px">
		    					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    					关键字：${resource.keyWord}
		    				</td>
		    			</tr>
		    			<tr>
		    				<td><fmt:formatDate value="${resource.updateDate}" pattern="yyyy年MM月dd日"/>更新&nbsp;&nbsp;已有${resource.browseCount}次浏览&nbsp;&nbsp;</td>
		    			</tr>
		    		</table>
		    		
		    	</fieldset> 
	    	
	    </c:forEach>
	    

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

