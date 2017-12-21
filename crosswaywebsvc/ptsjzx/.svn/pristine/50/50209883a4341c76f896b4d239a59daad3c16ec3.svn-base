<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function saveCancelAudit(){
		$('#cancelSourceForm').submit();
	}
</script>
<form method="post" id="cancelSourceForm" action="${ctx}/mlgl/saveCancelAudit">
<div align="center" style="padding-top:10px;">
	<div align="center">
		<jsp:include page="view_basic_information.jsp"/>
	</div>
	<div align="center">
		<jsp:include page="cancel_opnn.jsp"/>
	</div>
</div>
<div style="text-align:center;padding-top:10px;">
	<button type="button" class="minButton" onclick="saveCancelAudit()">提交</button>
	<button type="button" class="minButton" onclick="window.close()">关闭</button>
</div>
</form>
