<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${ctx}/tiles/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/tiles/bootstrap/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/tiles/bootstrap/css/jquery-labelauty.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    /* scroll 样式*/
    ::-webkit-scrollbar{width:10px;background-color:#e1e5ea}
    ::-webkit-scrollbar-thumb{background-color:#c3cad4;border-radius:10px;border:2px solid #e1e5ea;}
    ::-webkit-scrollbar-thumb:hover{background-color:#aab1bc;}
    ::-webkit-scrollbar-thumb:active{border:0;border-radius:0;background-color:#737ed7}
    ::-webkit-scrollbar-thumb:window-inactive{background-color:#4c97da}

    #ul > li{
        border: solid 1px #a29e9e;
    }
    .sub-menu-list > li{
        border-top: solid 1px #a29e9e;
    }
    .panel-default>.panel-heading {
        color: #fff;
        background-color: #2e97cd;
        border-color: #ddd;
    }
    .div-center{
        margin: 20px auto;
    }
    .ul1 {
        list-style-type: none;
        margin-bottom: 0px;
        height: 40px;
        padding-left: 0px;
    }
    li {
        display: inline-block;
    }
    .li-title{
        float:left;
        margin-top:3px;
       /* padding-right: 10px;*/
    }
    .dowebok{
        padding-left: 50px;
    }
    .div-scroll{
        height:350px;
        overflow:scroll;
        overflow-x:hidden;
    }
	input.labelauty + label > span.labelauty-unchecked-image
	{
		background-image: url( ${ctx}/tiles/bootstrap/images/input-unchecked.png );
	}
	
	input.labelauty + label > span.labelauty-checked-image
	{
		background-image: url( ${ctx}/tiles/bootstrap/images/input-checked.png );
	}

    input.labelauty + label { font: 12px "Microsoft Yahei";padding: 5px;}
</style>
</head>
<body>
<form id="queryForm" name="queryForm" action="${ctx}/tz/toSaveRule" method="post" >
<input type="hidden" name="ruleId" value="${obj.info.ruleid }"/>
<div class="col-md-12" style="margin-top:40px;">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">规则新增</h3>
      </div> <!-- end of panel-title  -->

      <div class="panel-body" style="margin-top:20px">
        <div class="col-md-12">
            <div class="col-sm-2"  style="text-align:right;margin-top:8px">
                <label class="control-label">规则名称：</label>
            </div>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="input1" name="ruleName" placeholder="请输入规则名称" value="${obj.info.rulename}">
            </div>
        </div>

        <div class="col-md-12" style="margin-top:20px">
            <div class="col-sm-2"  style="text-align:right;margin-top:8px">
                <label class="control-label">执行方式：</label>
            </div>
            <div class="col-sm-4">
                 <div class="form-group">
                     <select name="executeType" class="form-control" defalutvalue="${obj.info.executeType }">
                         <!-- <option value="0">手动执行</option>
                         <option value="1">立即执行</option> -->
                     </select>
                 </div>
            </div>
        </div>  <!-- end of select -->

        <div class="col-md-12" align="center" style="margin-top:20px">
            <p style="margin:0px"><strong>————————————</strong></p>
            <p style="margin:0px">标签云</p>
            <p><strong>————————</strong></p>
        </div>
        <div class="col-md-12">
            <div class="panel panel-default panel1">
              <div class="panel-body div-scroll" style="padding-left: 0px;padding-right: 0px;">
              <c:forEach items="${obj.dataList}" var="data" varStatus="row1">
                <ul class="dowebok col-md-12">
                    <div class="li-title" style="width:85px">
                        <span>${data.tag.tagName}：</span>
                    </div>
                    <div class="col-md-10">
                    <c:forEach items="${data.list}" var="tag" varStatus="row2">
                    	<c:if test="${obj.info == null}">
	                   	 	<c:if test="${tag.tagStatus == '0'}">
	                    		<li><input type="checkbox" tag="${tag.id}" name="tagId" development="true" disabled data-labelauty="${tag.tagName }" value='${tag.tagPreId}|${tag.id}'></li>
	                    	</c:if>
	                    	<c:if test="${tag.tagStatus == '1'}">
	                    		<li><input type="checkbox" tag="${tag.id}" name="tagId" development="true" data-labelauty="${tag.tagName }" value='${tag.tagPreId}|${tag.id}'></li>
	                    	</c:if>
                    	</c:if>
                    	<c:if test="${obj.info != null}">
                    		<c:if test="${tag.tagStatus == '0'}">
	                    		<li><input type="checkbox" tag="${tag.id}" name="tagId" development="true" disabled data-labelauty="${tag.tagName }" value='${tag.tagPreId}|${tag.id}'></li>
	                    	</c:if>
	                    	<c:if test="${tag.tagStatus == '1'}">
	                    		<li><input type="checkbox" tag="${tag.id}" name="tagId" development="true" data-labelauty="${tag.tagName }" value='${tag.tagPreId}|${tag.id}'></li>
	                    	</c:if>
                    	</c:if>
                    </c:forEach>
                    </div>
                </ul>
                </c:forEach>
              </div>
            </div>
        </div>


        <div class="col-md-12 div-center" align="center" style="margin-top:0px">
            <button type="button" class="btn btn-primary" onclick="save()">保 存</button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-primary" onclick="javascript:history.back(-1);">取 消</button>
        </div>

      </div>  <!-- end of panel-body  -->
    </div>
</div>
</form>
<script type="text/javascript" src="${ctx}/tiles/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/bootstrap/js/jquery-labelauty.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/dic.js"></script>
<script type="text/javascript">
	var isnotdic="{'0':'手动执行','1':'立即执行'}";
	$("select[name='executeType']").each(function(){
		var defaultval=$(this).attr("defalutvalue");
		$(this).dicselect({
			dic:isnotdic,
			defaultvalue:defaultval
		});
	});
	$(function(){
	    $(':input').labelauty({
	        // 可在此自定义labelauty的各种属性
	    	force_random_id:false
	    });
	});

	var arr = eval('('+'${obj.childIdList}'+')');
	arr.forEach(function(result){
		$("input[tag="+result+"]").attr("checked","");
	});

	function save(){
		if($("#input1").val() == ""){
			alert("规则名称不能为空！");
			return;
		}
		
		if(!$(".labelauty").is(':checked')){
			alert("请至少选择一项标签！");
			return;
		}
		
		$('#queryForm').submit();
	}

</script>
</body>
</html>