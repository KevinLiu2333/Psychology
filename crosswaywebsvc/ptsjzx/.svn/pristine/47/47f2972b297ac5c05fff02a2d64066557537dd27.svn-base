<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" id="initPageNumber" value="${obj.pager.pageNumber }"/>
<div class="datagrid-pager pagination">
   <table cellspacing="0" cellpadding="0" border="0">
   	<tbody>
   		<tr>
   			<td>
   				<select class="pagination-page-list" id="pageSize" name="pageSize">
   					<c:forEach begin="10" end="50" step="10" var="i">
   						<option <c:if test="${obj.pager.pageSize == i }">selected</c:if>>${i }</option>
   					</c:forEach>
   				</select>
   				
   			</td>
   			<td>
   				<div class="pagination-btn-separator"></div>
   			</td>
  				<td><a href="javascript:void(0)" class="l-btn l-btn-plain <c:if test="${obj.pager.first }">l-btn-disabled</c:if>" id="firstPage">
   					<span class="l-btn-left">
   						<span class="l-btn-text">
   							<span class="l-btn-empty pagination-first">&nbsp;</span>
   						</span>
   					</span>
  					</a>
  				</td>
  				<td>
  					<a href="javascript:void(0)" class="l-btn l-btn-plain <c:if test="${obj.pager.first }">l-btn-disabled</c:if>" id="prevPage">
  						<span class="l-btn-left">
  							<span class="l-btn-text">
  								<span class="l-btn-empty pagination-prev">&nbsp;</span>
  							</span>
  						</span>
  					</a>
  				</td>
  				<td>
  					<div class="pagination-btn-separator"></div>
  				</td>
  				<td>
  					<span style="padding-left:6px;">当前</span>
  				</td>
  				<td>
  					<input class="pagination-num" id="pageNumber" name="pageNumber" type="text" value="${obj.pager.pageNumber}" size="2">
  				</td>
  				<td>
  					<span style="padding-right:6px;">共 ${obj.pager.pageCount} 页</span>
  				</td>
  				<td>
  					<div class="pagination-btn-separator"></div>
  				</td>
  				<td>
  					<a href="javascript:void(0)" class="l-btn l-btn-plain <c:if test="${obj.pager.last }">l-btn-disabled</c:if>" id="nextPage">
  						<span class="l-btn-left">
  							<span class="l-btn-text">
  								<span class="l-btn-empty pagination-next">&nbsp;</span>
  							</span>
  						</span>
  					</a>
  				</td>
  				<td>
  					<a href="javascript:void(0)" class="l-btn l-btn-plain <c:if test="${obj.pager.last }">l-btn-disabled</c:if>" id="lastPage">
  						<span class="l-btn-left">
  							<span class="l-btn-text">
  								<span class="l-btn-empty pagination-last">&nbsp;</span>
  							</span>
  						</span>
  					</a>
  				</td>
  				<td>
  					<div class="pagination-btn-separator"></div>
  				</td>
  				<td>
  					<a href="javascript:void(0)" class="l-btn l-btn-plain" id="loadPage">
  						<span class="l-btn-left">
  							<span class="l-btn-text">
  								<span class="l-btn-empty pagination-load">&nbsp;</span>
  							</span>
  						</span>
  					</a>
  				</td>
  			</tr>
  		</tbody>
  	</table>
  	<div class="pagination-info">显示 ${obj.pager.recordCount} 条数据中的 ${(obj.pager.pageNumber - 1)*obj.pager.pageSize + 1}  到  ${ ((obj.pager.pageSize * obj.pager.pageNumber) < obj.pager.recordCount)? obj.pager.pageSize * obj.pager.pageNumber : obj.pager.recordCount} </div><div style="clear:both;"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#pageSize").bind("change", function(){
		$("#pageNumber").val("1");
		$("form:first").submit();
	});
	$("#firstPage").bind("click",function(){
		if(!$(this).hasClass("l-btn-disabled")) {
			$("#pageNumber").val("1");
			$("form:first").submit();
		}
	});
	$("#prevPage").bind("click",function(){
		if(!$(this).hasClass("l-btn-disabled")) {
			var initPageNumber = $("#initPageNumber").val();
			$("#pageNumber").val(parseInt(initPageNumber) - 1);
			$("form:first").submit();
		}
	});
	$("#nextPage").bind("click",function(){
		if(!$(this).hasClass("l-btn-disabled")) {
			var initPageNumber = $("#initPageNumber").val();
			$("#pageNumber").val(parseInt(initPageNumber) + 1);
			$("form:first").submit();
		}
	});
	$("#lastPage").bind("click",function(){
		if(!$(this).hasClass("l-btn-disabled")) {
			$("#pageNumber").val("${obj.pager.pageCount}");
			$("form:first").submit();
		}
	});
});
</script>