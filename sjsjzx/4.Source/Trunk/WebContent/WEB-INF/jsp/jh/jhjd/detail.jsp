<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<title>详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx}/skins/jk/css/default.css" />
<script type="text/javascript" src="${ctx}/skins/jk/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx}/skins/jk/js/nav.js"></script>
<script language="javascript" src="${ctx}/skins/jk/js/drawCircle.js"></script>
</head>
<body>
<div class="main" style="margin-left: 20px;">
	<div class="subTitle">节点数据交换详情</div>
	<div class="subContent">
			
		<ul>
			<li><span class="deptNo" id="">监控对象：</span><span id="dx">${obj.jiaohuan.jiankongduixiang }</span></li>
			<li class="net"><span>网络状态：</span><span class="normal">正常</span></li>
			<li class="node"><span>节点状态：</span><span class="normal">正常</span></li>
			<li class="ds"><span>数据源：</span><span class="normal">正常</span></li>
		</ul>
		<div class="detailColumn">
			<div class="clumnTitle">
				前置机监控(192.168.0.1)&nbsp;&nbsp;
			</div>
			<div class="detailBack">
				<ul>
					<li>硬盘大小</li>
					<li>内存</li>
					<li>CPU</li>
				</ul>
				<canvas id="disk" class="percent" width="70px" height="70px" data-role="disk">45%</canvas>
				<canvas id="memory" class="percent" width="70px" height="70px" data-role="memory">30%</canvas>
				<canvas id="cpu" class="percent" width="70px" height="70px" data-role="cpu">10%</canvas>	
			</div>
		</div>
		<div class="detailColumn">
			<div class="clumnTitle" id="exchange">
				交换量监控&nbsp;(待发数据总量:0)
			</div>
			<div class="detailBack">
				<div class="receiveData">
					总接收量<span id="srcv" class="srcv">${obj.zjsl }</span>
				</div>
				<div class="sentData">
					总发送量<span id="ssnd" class="ssnd">${obj.zfsl }</span>
				</div>
			</div>
		</div>
		
	</div>
	<div class="subTitle titleIcon2">发送监控</div>
	<div class="detailTable">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<thead>
				<tr>
					<td width="14%">所属业务</td>
					<td>业务表</td>
					<td width="14%">当日新增</td>
					<td width="14%">当日更新</td>
					<td width="14%">当日删除</td>
					<td width="14%">发送总量</td>
				</tr>
			</thead>
			<tbody class="send">
			<c:forEach items="${obj.fasong}" var="fasong">
				<tr>
					<td>${fasong.suoshuyewu }</td>
					<td>${fasong.yewubiao }</td>
					<td>${fasong.dangrixinzeng }</td>
					<td>${fasong.dangrigengxin }</td>
					<td>${fasong.dangrishanchu }</td>
					<td>${fasong.fasongzongliang }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="subTitle titleIcon3">接收监控</div>
	<div class="detailTable">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<thead>
				<tr>
					<td width="14%">所属业务</td>
					<td>业务表</td>
					<td width="14%">当日新增</td>
					<td width="14%">当日更新</td>
					<td width="14%">当日删除</td>
					<td width="14%">接收总量</td>
				</tr>
			</thead>
			<tbody class="recv">
			<c:forEach items="${obj.jieshou}" var="jieshou">
				<tr>
					<td>${jieshou.suoshuyewu }</td>
					<td>${jieshou.yewubiao }</td>
					<td>${jieshou.dangrixinzeng }</td>
					<td>${jieshou.dangrigengxin }</td>
					<td>${jieshou.dangrishanchu }</td>
					<td>${jieshou.jieshouzongliang }</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
<script type="text/javascript">
	//
	var bm = "${obj.duixiangbianma}";
	var mc = "${obj.jiaohuan.jiankongduixiang }";
	if( ( mc == '' || mc == null) )
		{
			if( bm == 'QZWZX'){
				$("#dx").html('区政务中心');
			}
			if( bm== 'SWJ'){
				$("#dx").html('税务局');			
			}
			if( bm== 'AJJ'){
				$("#dx").html('安监局');
			}
			if( bm== 'FGJ'){
				$("#dx").html('房管局');
			}
			if( bm== 'GTJ'){
				$("#dx").html('国土局');
			}
			if( bm== 'GAJ'){
				$("#dx").html('公安局');
			}
			if( bm== 'MZJ'){
				$("#dx").html('民政局');
			}
			if( bm== 'GSJ'){
				$("#dx").html('工商局');
			}
			if( bm== 'BB'){
				$("#dx").html('编办');
			}
			if( bm== 'JSW'){
				$("#dx").html('计生委');
			}
			if( bm== 'SBJ'){
				$("#dx").html('社保局');
			}
			if( bm== 'ZJJ'){
				$("#dx").html('质监局');
			}
			if( bm== 'JYJ'){
				$("#dx").html('教育局');
			}
			
		}



</script>
</html>