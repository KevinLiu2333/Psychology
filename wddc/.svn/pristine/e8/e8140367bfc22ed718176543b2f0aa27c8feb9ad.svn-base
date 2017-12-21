<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--self-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!--select2-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/select2/select2.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/select2/select2_locale_zh-CN.js"></script>
    <!--chosen-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>

</head>
<body>
<!-- top of the page -->
<jsp:include page="/cj/header.jsp"/>
	<input type="hidden" id="js_ctx" value="${ctx }" />
<div class='container'>
    <form method="post" id="publishForm" action="${ctx}/zyapply/saveApplyAudit">
        <input type="hidden" name="zyApplyId" value="${obj.zyApply.zyApplyId}">
    <h4  class="page-header"><b>申请信息</b></h4>
  <div class="row">
  
   <div class="col-md-6 form-group">
        <b>申请编号：</b>${obj.zyApply.appApplyNum}
    </div>
    <div class="col-md-6 form-group">
        <b>申请日期：</b><fmt:formatDate pattern="yyyy-MM-dd" value="${obj.zyApply.applyDate}"/>
    </div>
    
    <div class="col-md-6 form-group">
        <b>申请人：</b>${obj.zyApply.userName}
    </div>
    
    <div class="col-md-6 form-group">
        <b>申请人联系电话：</b>${obj.zyApply.linkmanPhone}
    </div>
    
     <div class="col-md-6 form-group">
        <b>申请主题：</b>${obj.zyApply.applyTopic}
    </div>
      <div class="col-md-6 form-group">
      <b>资源提供单位： </b>${obj.zyApply.resourceProvider}
    </div>
    <div class="col-md-6 form-group">
        <b>资源名称：</b> ${obj.zyApply.resourceName}
    </div>
      <div class="col-md-6 form-group">
       <b> 资源类型：</b> ${obj.zyApply.resourceType}
    </div>
  
    
      <div class="col-md-12 form-group">
      <b>申请原因：</b>${obj.zyApply.applyReason}
    </div>
      <div class="col-md-12 form-group">
      <b>申请资源项：</b>
      <br/> 
       [普遍共享]：
       <c:forEach items="${obj.zyApplyDetailsList}" var="item" varStatus="row" >
       <c:if test="${item.openType == '普遍共享'}">
       	${item.cnName}&nbsp;&nbsp;&nbsp;
       </c:if>
       </c:forEach>
       <br/>
      [按需共享]：
         <c:forEach items="${obj.zyApplyDetailsList}" var="item" varStatus="row" >
       <c:if test="${item.openType == '按需共享'}">
       	${item.cnName}&nbsp;&nbsp;&nbsp;
       </c:if>
       </c:forEach>
    </div>
  
</div>
  
  <c:if test="${obj.zyApply.status == '1' }">
 <h4  class="page-header"><b>申请授权信息</b></h4>
  <div class="row">
   <div class="col-md-12 form-group">
       申请授权结果：<select id="accessResult" name="zyApply.status" style="width:200px;height: 30px"></select>
    </div>
    <div class="col-md-12 form-group">
        申请授权意见：<textarea name="zyApply.checkReason"  class="form-control" rows="3" style="width:865px;"></textarea>
    </div>
  
</div>
</c:if>
 <c:if test="${obj.zyApply.status == '2' &&  empty obj.zyApply.fwCode}">
 <h4  class="page-header"><b>服务发布信息</b></h4>
  <div class="row">
	   <div class="col-md-12 form-group">
	       绑定已有服务：
	       <select class="chosen-select form-control" id="fwCode"  name="zyApply.fwCode" onchange="changeZy()" data-placeholder="请选择服务...">
                <option value="">  </option>
            </select>
	    </div>
		<div class="col-md-6 form-group">
	        服务授权类型：<select id="accessType" name="fwAccess.accessType" style="width:200px;height: 30px"></select>
	    </div>
	    
	      <div class="col-md-6 form-group">
	        服务授权状态：<select id="usedStatus" name="fwAccess.usedStatus" style="width:200px;height: 30px"></select>
	    </div>
	    
	    <div class="col-md-12 form-group">
	      临时授权截止时间：<input type="text" name="fwAccess.accessEndDate" style="width:200px" value="${obj.fwAccess.accessEndDate}"/>
	    </div>
	  <div class="col-md-12 form-group">
	       <div class="alert alert-success" role="alert">
		        <strong>如果未有可使用服务，请点击按钮发布!</strong> 
	        <button type="button" class="btn btn-sm btn-warning" onclick="fabu()">发布新服务</button>
		    </div>
	  </div>
</div>
  </c:if>
  
</form>
    <p align="center">
        <button type="button" class="btn btn-warning" onclick="save(${obj.zyApply.status})">保  存</button>&nbsp;&nbsp;&nbsp;&nbsp;
        <button type="button" class="btn btn-warning" onclick="window.location.href='${ctx}/zyapply/toApplyList'">返回</button>
    </p>

</div>
</body>

<jsp:include page="/cj/foot.jsp"/>
<script type="text/javascript">
	//保存数据
    function save(status) {
        if(status == '1'){
            if($("#accessResult").val() == null || $("#accessResult").val() == '' ){
                alert("请选择申请授权结果");
                return;
            }
            $("#publishForm").submit();
        }else{
	    	$.ajax({
	            type:"post",
	            async:false,
	            url:"${ctx}/fw/apply2FwAccess", 
	            data:{"fwCode":$("#fwCode").val(),
	            	"fwAccess.accessType":$("#accessType").val(),
	            	"fwAccess.usedStatus":$("#usedStatus").val(),
	            	"fwAccess.accessEndDate":$("#accessEndDate").val(),
	                "unitId":'${obj.zyApply.unitId}'},
	            success:function(data){
	                if(data.result ==1){
	                 $("#publishForm").submit();
	                }else{
	               	 alert("服务授权失败，请核实授权信息");
	                }
	                    
	            }
	        });
        }
        
    }
    //服务发布
    function fabu() {
        window.location.href=' ${ctx}/fw/toPublish';
    }

    jQuery(function($){

		$('#accessResult').jsondic({dicid:'2010',initvalue:"--请选择--"});

		$('#usedStatus').jsondic({dicid:'2002',initvalue:"--请选择--",defaultvalue:"${obj.fwAccess.usedStatus}"});
		$('#accessType').jsondic({dicid:'2003',initvalue:"--请选择--",defaultvalue:"${obj.fwAccess.accessType}"});

	    //调取服务信息
	    $.ajax({
	        type:"post",
	        async:false,
	        url:"${ctx}/fw/allFwData",
	        success:function(data){
		    	for(var i=0;i<data.result.length;i++){
	                $("#fwCode").append("<option value='"+data.result[i].fwCode+"'>"+data.result[i].fwName+"("+data.result[i].fwCode+")"+"</option>");
	            }
	            $('.chosen-select').chosen({allow_single_deselect:true});
	            $(window).off('resize.chosen').on('resize.chosen', function() {
	                $('.chosen-select').each(function() {
	                    var $this = $(this);
	                    $this.next().css({'width': '600px'});
	                })
	            }).trigger('resize.chosen');
	        }
	    });
		
    });
   
 </script>
</html>
