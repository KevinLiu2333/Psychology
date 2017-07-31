<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>松江区政务数据中心-行业专题基本情况</title>
<link href="${ctx}/skins/style/css/main_bumen.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/skins/style/css/jqtransform.css" type="text/css" />
<script type="text/javascript" src="${ctx}/tiles/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
<script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
<script type="text/javascript" src="${ctx}/tiles/data/zx/people/peopledata.js" ></script>
<style type="text/css">
.h500{
	position:relative;
	height:450px;
}
.tablebox01
{
	border:1px solid #d5d5d5;
	margin:0 auto;
}
.tablebox01 tr td{
	font-size:16px;
	clear:#333;
	line-height:30px;
	text-align:center;
}
.tablebox01 tr td{
	font-size:15px;
	clear:#333;
	line-height:25px;
	text-align:center;
}
.mima_tit dt span{
	background:url(${ctx}/skins/style/images/mima_pic_06.png) no-repeat center;
}
.mima_tit dd span{ 
	background:url(${ctx}/skins/style/images/mima_pic_06.png) no-repeat right;
}

</style>
<script type="text/javascript">
	//法人总数、登记数、差异数
	$(document).ready(function(){
		$.post("${ctx}/dp/piequery?info.sqlcode=20160503142025583",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					$('#frzs').html(data[0].value);
					$('#frdjs').html(data[1].value);
					$('#cys').html(data[2].value);
				}
			);
	});
</script>
</head>

<body>

<div class="mian_box">
	<!-- 法人总数 -->
	<dl class="main_01 mima_tit">
        	<dt>
            	<span>
                	<b>两化融合示范企业总数</b>
                    <h1>20<font size="3">&nbsp;家</font></h1>
                </span>
            </dt>
        	<dd>
            	<span>
                	<b>高企、小巨人企业总数</b>
                	<h1>469<font size="3">&nbsp;家</font></h1>
                </span>
            </dd>
        </dl>
	
<script type="text/javascript">
	require.config({
		paths: {
			echarts: '${ctx}/tiles/echarts'
		}
	}); 
	require([
				'echarts',
				'echarts/chart/pie',
				'echarts/chart/scatter',
				'echarts/chart/bar',
				'echarts/chart/line'
			],function (ec){
		//绘制统计图
		//法人分类统计
		drawQyfr(ec);
		drawFqyfr(ec);
		drawFrdjtj(ec);
		drawZczjfb(ec);		
	});
	
	</script>
</body>
</html>