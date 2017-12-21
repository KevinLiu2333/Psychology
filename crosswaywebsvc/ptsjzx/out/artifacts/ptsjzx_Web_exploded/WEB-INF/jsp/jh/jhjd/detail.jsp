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
			<li><span class="deptNo" id="${deptNo }">监控对象：</span>区政务中心</li>
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
					总接收量<span id="srcv" class="srcv">78930</span>
				</div>
				<div class="sentData">
					总发送量<span id="ssnd" class="ssnd">0</span>
				</div>
			</div>
		</div>
	</div>
	<div class="subTitle titleIcon2">发送监控</div>
	<div class="detailTable">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<thead>
				<tr>
					<td width="14%">所属业务</td> <td>业务表</td><td width="14%">当日新增</td><td width="14%">当日更新</td><td width="14%">当日删除</td><td width="14%">发送总量</td>
				</tr>
			</thead>
			<tbody class="send"></tbody>
		</table>
	</div>
	<div class="subTitle titleIcon3">接收监控</div>
	<div class="detailTable">
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<thead>
				<tr>
					<td width="14%">所属业务</td><td>业务表</td><td width="14%">当日新增</td><td width="14%">当日更新</td><td width="14%">当日删除</td><td width="14%">接收总量</td>
				</tr>
			</thead>
			<tbody class="recv"></tbody>
		</table>
	</div>
</div>
</body>
</html>