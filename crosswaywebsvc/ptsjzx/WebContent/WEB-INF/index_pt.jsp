<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>普陀数据中心</title>
<link href="${ctx }/skins/index/css/style.css" rel="stylesheet">
<script src="${ctx }/skins/index/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<form id="queryForm" name="queryForm" action="${ctx}/toIndex" method="post">
</form>
<div class="header">
	<div class="w1000">
    	<img class="logo" src="${ctx }/skins/index/images/logo_01.png">
        <ul class="header_fc">
        	<li><a href="#"><img src="${ctx }/skins/index/images/operation_01.png">欢迎您，${sessionScope.user.displayName }（${obj.dept }）</a></li>
        	<li><a href="${ctx }/logout"><img src="${ctx }/skins/index/images/operation_02.png">退出</a></li>
        </ul>
    </div>
</div>
<div class="nav">
	<div class="w1000">
    	<dl class="exchange">
        	<dt><img src="${ctx }/skins/index/images/exchange.png">数据交换</dt>
            <dd>总交换量 712361 条</dd>
            <dd>本月交换量 32000 条</dd>
        </dl>
    </div>
</div>
<div class="content ptb20">
	<div class="w1000">
		<dl class="con_frame">
			<dt >
            	<h1 class="tit" ><span>我的系统功能</span></h1>
                <ul class="pic_link" style="overflow-y:auto;height:195px;">
                	<li style="width: 150px;"><a href="${ctx}/config/query/toResult?saveId=1456811331397" target="_blank"><img src="${ctx }/skins/index/images/fc_icon_01.png" >人口基础数据查询</a></li>
                	<li style="width: 150px"><a href="${ctx}/config/query/toResult?saveId=1456885444561" target="_blank"><img src="${ctx }/skins/index/images/fc_icon_03.png" >房屋基础数据查询</a></li>
                	<li style="width: 150px"><a href="${ctx}/config/query/toResult?saveId=1456475836432" target="_blank"><img src="${ctx }/skins/index/images/fc_icon_04.png" >法人基本数据查询</a></li>
                	<li style="width: 150px"><a href="${ctx}/cx/toOneKeySearch" target="_blank"><img src="${ctx }/skins/index/images/fc_icon_05.png" >一键查询</a></li>
                	<li style="width: 150px"><a href="${ctx}/zx/toZtPeople" target="_blank"><img src="${ctx }/skins/index/images/fc_icon_02.png">人口基本情况</a></li>
                	<li style="width: 150px"><a href="${ctx}/zx/toZtBuilding" target="_blank"><img src="${ctx }/skins/index/images/fc_icon_02.png" >房屋基本情况</a></li>
                	<li style="width: 150px"><a href="${ctx}/zx/toFrjbqk" target="_blank"><img src="${ctx }/skins/index/images/fc_icon_02.png" >法人基本情况</a></li>
                	<li style="width: 150px"><a href="${ctx}/zx/toZtPeopledifferent" target="_blank"><img src="${ctx }/skins/index/images/fc_icon_02.png" >人口差异化情况</a></li>
                	<li style="width: 150px"><a href="${ctx}/zx/toFrcyhqk" target="_blank"><img src="${ctx }/skins/index/images/fc_icon_02.png" >法人差异化情况</a></li>
                </ul>
            </dt>
            <dd class="blue">
            	<h1>功能服务</h1>
                <span>
                	<ul class="text_link">
                    	<li><a href="#">人口信息统计</a></li>
                    	<li><a href="#">法人信息统计</a></li>
                    	<li><a href="#">房屋信息统计</a></li>
                    </ul>
                </span>
                <h2><a href="#">申请使用>></a></h2>
            </dd>
		</dl>
		<dl class="con_frame">
        	<dt>
            	<h1 class="tit"><span>我的数据资源服务</span></h1>
                <ul class="list_link">
                	<c:forEach items="${obj.sourceAppliey }" var="apply">
                		<li><a href="#">【${apply.stSourceProvider }】${ apply.stSourceName}</a>
                		<span><fmt:formatDate value="${apply.dtApply }" type="both" pattern="yyyy-MM-dd"/></span></li>
                	</c:forEach>
                </ul>
            </dt>
            <dd class="red">
            	<h1>数据资源服务</h1>
                <span>
                	<img src="${ctx }/skins/index/images/chart_01.jpg" style=" display:block;margin:0 auto;">
                </span>
                <h2><a href="#" onclick="addApplyTask()">申请使用>></a></h2>
            </dd>
        </dl>
        <dl class="con_frame">
        	<dt>
            	<h1 class="tit"><span>我的数据接口服务</span></h1>
                <ul class="list_link">
                	<c:forEach items="${obj.webservices }" var="webservices">
                		<li>
                			<c:choose>
                				<c:when test="${webservices.status == 0 && webservices.endTime > obj.nowtime}">
                					<b class="bg_blue">已申请</b><a >${webservices.serviceName }</a><input type="button" onclick="viewDetails('${webservices.userServiceId}')" value="查看详情">
                				</c:when> 
                				<c:otherwise>
                					<c:if test="${webservices.status == 4 && webservices.endTime > obj.nowtime }">
                						<b class="bg_green">使用中</b><a >${webservices.serviceName }</a><input type="button" onclick="viewDetails('${webservices.userServiceId}')" value="查看详情">
									</c:if>	
									<c:if test="${webservices.status == 2}">
										<b class="bg_red">已过期</b><a >${webservices.serviceName }</a><input type="button" onclick="toDataApply('${webservices.userServiceId}','2')" value="重新申请">
									</c:if>
									<c:if test="${webservices.status == 3}">
										<b class="bg_red">已退回</b><a>${webservices.serviceName }</a><input type="button" onclick="toDataApply('${webservices.userServiceId}','1')" value="修改申请">
									</c:if>
                				</c:otherwise>
                			</c:choose>
                		</li>
                	</c:forEach>
                </ul>
            </dt>
            <dd class="green">
            	<h1>功能服务</h1>
                <span>
                	<ul class="text_link">
                    	<li><a href="#">人口信息统计</a></li>
                    	<li><a href="#">法人信息统计</a></li>
                    	<li><a href="#">房屋信息统计</a></li>
                    </ul>
                </span>
                <h2><a href="#" onclick="addApplyservice()">申请使用</a></h2>
            </dd>
        </dl>
        <dl class="con_frame">
        	<dt>
            	<h1 class="tit"><span>标准规范</span></h1>
                <ul class="list_link">
                	<c:forEach items="${obj.doc }" var="doc">
                		<li><a href="${ctx}/db/file/fileDownById?fid=${doc.fid}">${doc.filenamelocal }</a>
                		<span><fmt:formatDate value="${doc.postedtime }" type="both" pattern="yyyy-MM-dd"/></span></li>
                	</c:forEach>
                </ul>
            </dt>
            <dd>
            	<h3 class="tit"><span>使用指南</span></h3>
                <ul class="guide">
                	<li><a href="#"><img src="${ctx }/skins/index/images/guide_01.png">功能服务</a></li>
                	<li><a href="#"><img src="${ctx }/skins/index/images/guide_02.png">资源服务</a></li>
                	<li><a href="#"><img src="${ctx }/skins/index/images/guide_03.png">数据服务</a></li>
                </ul>
            </dd>
        </dl>
	</div>
</div>
<div class="footer"><span><b>Copyright © 2015 znsj.com All Rights Reserved   普陀区科委  版权所有</b></span></div>
<script type="text/javascript">
function addApplyTask(){
	//$('#queryForm').attr("action","${ctx}/mlgx/toSourceApply");
	window.showModalDialog("${ctx}/mlgx/addApply",self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	$('#queryForm').submit();
}
function addApplyservice(){
	//$('#queryForm').attr("action","${ctx}/mlgx/toSourceApply");
	window.showModalDialog("${ctx}/jkfw/toJkfwApplyList",self,"dialogWidth=960px;dialogHeight=600px;resizable:no;status:no;scroll:no;");
	$('#queryForm').submit();
}
//查看详情 
function viewDetails(userServiceId){
	href = "${ctx }/jkfw/viewDetails?userServiceId="+userServiceId;
	window.showModalDialog( href,'window',"dialogHeight=450px;dialogWidth=910px;center=yes");
}
//服务申请 
function toDataApply(userServiceId,type){
	href = "${ctx }/jkfw/toDataApply?serviceId="+userServiceId+"&type="+type;
	window.showModalDialog( href,'window',"dialogHeight=450px;dialogWidth=910px;center=yes");
}
</script>
</body>
</html>