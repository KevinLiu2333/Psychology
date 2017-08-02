<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>数据采集与服务系统</title>
	<meta name="description" content="overview &amp; stats" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/fonts.googleapis.com.css" />
	<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace.min.css" />
	
	<!--[if lte IE 9]>
		<link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace-part2.min.css" class="ace-main-stylesheet" />
	<![endif]-->
	
	<!--[if lte IE 9]>
	  <link rel="stylesheet" href="${ctx }/wdac/data-deal/css/ace-ie.min.css" />
	<![endif]-->
	<script src="${ctx }/wdac/data-deal/js/ace-extra.min.js"></script>
	<!--[if lte IE 8]>
		<script src="${ctx }/wdac/data-deal/js/html5shiv.min.js"></script>
		<script src="${ctx }/wdac/data-deal/js/respond.min.js"></script>
	<![endif]-->
</head>
<body class="no-skin">
<jsp:include page="/wdac/cj/sjclyy_header.jsp"></jsp:include>
<input type="hidden" id="js_ctx" value="${ctx }">
<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
	<jsp:include page="/wdac/cj/sjclyy_slider.jsp"></jsp:include>
	<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">

						<div class="page-header">
							<h1>
								数据标签管理
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									标签热度统计
								</small>
							</h1>
						  </div><!-- /.page-header -->
                          			<!-- 按标签分类统计 -->
							<div align="center" style="margin-top: 5px;height:450px">
	                        <div id="fltjbt" style="float:left;height:450px;width:55%"></div>
	                        <div id="d2" style="float:left;height:450px;width:40%">
	                            <div class="wrapper" style="margin-top:20px">
	                                <div class="row">
	                                    <div class="row">
	                                        <div  class="width-100 label label-info label-xlg arrowed-in arrowed-right">
	                                            <span align="center" style="font-family:'微软雅黑';font-size:20px">数据标签热度排名</span>
	                                        </div>
	                                    </div>
	                                    <div align="left">
	                                            <ul class="list-unstyled spaced">
	                                            <c:forEach items="${obj.taglist }" var="tag" varStatus="row">
	                                                <li style="border-bottom:1px dashed #BBB;">
	                                                    <span class="badge badge-danger" style="font-size:18px">${row.index+1 }</span>&nbsp;&nbsp;
	                                                    <span  style="font-size:18px">${tag.tagName }</span>
	                                                    <span  style="font-size:15px;float:right;margin-right: 25px">${tag.tAccess }</span>
	                                                </li>
	                                             </c:forEach>  
	                                                
	                                            </ul>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
						
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
			<jsp:include page="/wdac/cj/sjclyy_foot.jsp"></jsp:include>
	</div>
	<!--[if !IE]> -->
		<script src="${ctx }/wdac/data-deal/js/jquery.2.1.1.min.js"></script>
	<!-- <![endif]-->
	
	<!--[if IE]>
        <script src="${ctx }/wdac/data-deal/js/jquery.1.11.1.min.js"></script>
    <![endif]-->
    
    <!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery || document.write("<script src='${ctx }/wdac/data-deal/js/jquery.min.js'>"+"<"+"/script>");
		
	</script>
	<!-- <![endif]-->
	
	<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${ctx }/wdac/data-deal/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
	<![endif]-->
	
	<script src="${ctx }/wdac/data-deal/js/bootstrap.min.js"></script>
	<!--[if lte IE 8]>
	  <script src="${ctx }/wdac/data-deal/js/excanvas.min.js"></script>
	<![endif]-->
	<script src="${ctx }/wdac/data-deal/js/ace-elements.min.js"></script>
	<script src="${ctx }/wdac/data-deal/js/ace.min.js"></script>
	<script src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
	<script>
		$("#bqgl").attr("class","active open");
		$("#bqrd").attr("class","active");
		
	</script>
	<script type="text/javascript">
	    var data = ${obj.data};
        //分类统计饼图
        var myChart = echarts.init(document.getElementById('fltjbt')); 
        
		var option = {
			    title : {
	        text: '标签访问热度统计',
	        
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:[data.date]
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : data.name
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} 次'
	            }
	        }
	    ],
	    series : [
	        {
	            name:data.date,
	            type:'line',
	            data:data.value,
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        }
	    ]
	};
	                    
                    
		myChart.setOption(option);
    </script>
</body>
</html>