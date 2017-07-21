<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>

<h3  class="page-header" >申请完成</h3>
<div class="alert alert-success" role="alert">
    <strong>服务已开通!</strong> 服务接口访问KEY：<span id="methodKey"></span>
</div>

<div class="panel">
    <div class="panel-body">
        <ul class="note-info">
                <div class="title" style="width: 80%">接口参数、返回结果格式及调用示例点击<button type="button" class="btn btn-sm btn-warning" onclick="toExample('${obj.fwInfo.fwInfoId}')">调用示例查看</button></div>

        </ul>
    </div>
</div>

<script type="text/javascript">
    //服务申请
    function toExample(fwInfoId){
        window.open("${ctx}/fw/example?fwInfoId="+fwInfoId);
    }
</script>