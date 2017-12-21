<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- 本页面用于字典测试及例子 -->
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
    	<script type="text/javascript" src="${ctx}/wddc/tiles/js/dic.js"></script>
   		<link href="${ctx}/wddc/tiles/data-tables/css/demo_page.css" rel="stylesheet" />
    	<link href="${ctx}/wddc/tiles/data-tables/css/demo_table.css" rel="stylesheet" />
    	<link rel="stylesheet" href="${ctx}/wddc/tiles/data-tables/DT_bootstrap.css" />
    	<!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
	<input type="hidden" id="js_ctx" value="${ctx }" />
		<select id="s1"></select>
		<select id="s2"></select>
		<select id="s3"></select>
		<select id="s4"></select>
		<div>
			<select id="s12"></select>
			<select id="s13"></select>
			<select id="s14"></select>
		</div>
		<div>
			<select id="s22"></select>
			<select id="s23"></select>
			<select id="s24"></select>
		</div>
		<div>
			<span id="t1"></span>
			<input id="t2" />
		</div>
		<div>
			<span id="t3"></span>
		</div>
		<div>
			<span id="t4"></span>
		</div>
		<div>
			<span id="t5" dicvalue="DB00001"></span>
		</div>
		<div>
			<select id="s5"></select>
		</div>
	</body>
	<script type="text/javascript"> 
		$('#s1').jsondic({dicid:'1001',initvalue:"请选择",defaultvalue:"1"});
		$('#s2').jsondic({
				dicid:"1002",
				initvalue:"请选择",
				defaultvalue:'1',
				subdisplay:true,	
				subselect:[{id:'s3',initvalue:"请选择"},{id:"s4",initvalue:"请选择"}]
			});
		$('#s12').jsondic({
				dicid:"1002",
				initvalue:"请选择",
				defaultvalue:'1',
				subselect:[{id:'s13',initvalue:"请选择"},{id:"s14",initvalue:"请选择"}]
			});
		$('#s22').jsondic({
				dicid:"1002",
				initvalue:"请选择",
				defaultvalue:'1',
				subdisable:true,
				subselect:[{id:'s23',initvalue:"请选择"},{id:"s24",initvalue:"请选择"}]
			});
		$('#t1').jsondicvalue({
				dicid:1001,
				value:"2" 
			});
		$('#t2').jsondicvalue({
				dicid:1001,
				value:"1",
				prop:"value"
			});
		$("#t3").dicvalue({
			dic:"{'1':'男','2':'女'}",
			value:"1"
			});
		$("#t4").dicajaxvalue({
				url:"${ctx}/suite/data/db/getAllDbJson",
				value:"96abd0ca9a574c7c8c3fa64bb970e281"
			});
		$("#t5").dicajaxvalue({
				url:"${ctx}/suite/data/db/getAllDbJson",
				valueprop:"dicvalue"
			});
		$('#s5').dicajaxselect({
			url:"${ctx}/suite/data/db/getAllDbJson",
			initvalue:'请选择',
			initoptvalue:'',
			callback:function(data,options){//data 返回的字典  options 配置参数
				//do something
			}
		});
	</script>
</html>