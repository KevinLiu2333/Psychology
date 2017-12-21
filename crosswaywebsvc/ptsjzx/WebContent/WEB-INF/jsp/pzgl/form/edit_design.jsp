<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
//台帐定义中下一步操作
function tzdyNext(url,tabId,tit){
	navTab.openTab(tabId, url, {title:tit});
}
//保存
function reSet(){
 		$("#dzbdForm").attr("action","${ctx}/config/form/resetDesign");
 		$("#dzbdForm").submit();
	
}
//刷新
function reFresh(){
		$("#dzbdForm").attr("action","${ctx}/config/form/refreshDesign");
		$("#dzbdForm").submit();

}
</script>
<div class="pageContent">
<form id="dzbdForm" method="post" action="${ctx}/config/form/finishDesign" class="pageForm required-validate" onsubmit="return navTabSearch(this);" name="dzbdForm">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/config/form/toDesign?dreamformId=${obj.dreamformId}" target="_blank"><span>台帐页面设计</span></a></li>
			<li><a class="add" href="javascript:reSet();"><span>重置为默认样式</span></a></li>
			<li><a class="add" href="javascript:reFresh();"><span>刷新</span></a></li>
		</ul>
	</div>
	<input type="hidden" name="dreamformId" value="${obj.dreamformId }"/>
	<div class="pageFormContent" layoutH="80" >
		${obj.content }
	</div>
	<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="button" onclick="tzdyNext('${ctx}/config/form/edit?id=${obj.dreamformId}','navTab_form_add','新增表单')">上一步</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="finishDesign();">完成</button></div></div></li>
			</ul>
		</div>
</form>
</div>
<script>
function finishDesign(){
	//$("#dzbdForm").attr("onsubmit","return validateCallback(this, navTabAjaxDone)");
	$("#dzbdForm").submit();
}
</script>