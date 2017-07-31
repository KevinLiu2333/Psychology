<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/tiles/cj/title_setting.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ page import="com.wonders.Constants" %>
<div class="row">
<div class="col-md-12">
        <!--statistics start-->
        <div class="row state-overview">
       <c:if test="${(obj.user.userId eq '999999999' ||  obj.user.userId eq'1616161616' )}">
            <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel purple link-pannel" onclick="javaScript:toSj()">
                    <div class="symbol">
                        <i class="fa fa-database"></i>
                    </div>
                    <div class="state-value">
                        <div class="value">资源共享-数据汇聚</div>
                        <div style="font-size:14px;">已接入<span class="state-count" id="zyAllCount">0</span></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel red link-pannel" onclick="javaScript:toYy()">
                    <div class="symbol">
                        <i class="fa fa-jsfiddle"></i>
                    </div>
                    <div class="state-value">
                        <div class="value">资源共享-数据申请</div>
                        <div  style="font-size:14px;">已申请<span class="state-count" id="fwCount">0</span></div>
                    </div>
                </div>
            </div>
</c:if>
<c:if test="${(obj.user.userId !='999999999' && obj.user.userId !='1616161616')}">
		 <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel purple link-pannel" onclick="javaScript:toSj()">
                    <div class="symbol">
                        <i class="fa fa-database"></i>
                    </div>
                    <div class="state-value">
                        <div class="value"><a href="${ctx}/home/toJh" target="_blank">资源共享-数据汇聚</a></div>
                        <div style="font-size:14px;">已接入<span class="state-count" id="zyAllCount">0</span></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel red link-pannel" onclick="javaScript:toYy()">
                    <div class="symbol">
                        <i class="fa fa-jsfiddle"></i>
                    </div>
                    <div class="state-value">
                        <div class="value"><a href="${ctx}/home/toJh?byid=1" target="_blank">资源共享-数据申请</a></div>
                        <div  style="font-size:14px;">已申请<span class="state-count" id="fwCount">0</span></div>
                    </div>
                </div>
            </div>
</c:if>
            <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel blue link-pannel" onclick="javaScript:toLy()">
                    <div class="symbol">
                        <i class="fa fa-cogs"></i>
                    </div>
                    <div class="state-value">
                        <div class="value"><a href="${ctx}/home/toZx" target="_blank">信息综合利用平台</a></div>
                        <div  style="font-size:14px;">分析项<span class="state-count"></span></div>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-xs-6 col-sm-3">
                <div class="panel green link-pannel" onclick="javaScript:toJk()">
                    <div class="symbol">
                        <i class="fa fa-headphones"></i>
                    </div>
                    <div class="state-value">
                        <div class="value"><a href="${ctx}/home/toJk" target="_blank">实时安全监控平台</a></div>
                        <div  style="font-size:14px;">监控项 <span class="state-count"></span></div>
                    </div>
                </div>
            </div>
        </div>
        <!--statistics end-->
    </div>

<script type="text/javascript">

$.post("${ctx}/home/to12",
		{ Action: "post"},
		function(data, textStatus){
			data = eval('('+data+')');
			$("#zyAllCount").text(data['countML']);
			$("#fwCount").text(data['countSQ']);
			
		});
</script>

</div>
