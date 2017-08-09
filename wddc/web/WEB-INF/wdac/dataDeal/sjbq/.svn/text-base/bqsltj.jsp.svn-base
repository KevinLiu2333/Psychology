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
									对应数据量统计
								</small>
							</h1>
						  </div><!-- /.page-header -->
                          		<!-- 按标签分类统计 -->
						<div class="row">
						
							<!-- 按标签分类统计 -->
							
							<div class="col-xs-12">
								
								<div class="col-xs-12">	
								<br/>										
									<div id="fltjbq" style="float:center;height:1500px;width:100%">
								         
									</div>	
							    </div>
							</div>
						</div>
					</div><!-- /.page-content -->
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
	<script src="${ctx}/wddc/tiles/echarts/echarts_tree.js"></script>
	<script>
		$("#bqgl").attr("class","active open");
		$("#tysj").attr("class","active");
	</script>
	<script type="text/javascript">
	    var data = ${obj.data};
        var myChart = echarts.init(document.getElementById('fltjbq')); 
        var option = {
        	    title : {
        	        text: '对应数据量统计'
        	    },
        	    toolbox: {
        	        show : true,
        	        feature : {
        	            mark : {show: true},
        	            dataView : {show: true, readOnly: false},
        	            restore : {show: true},
        	            saveAsImage : {show: true}
        	        }
        	    },
        	    series : [
        	        {
        	            name:'树图',
        	            type:'tree',
        	            orient: 'horizontal',
        	            rootLocation: {x: 100,y:'center'},
        	            nodePadding: 40,
        	            layerPadding: 250,
        	            hoverable: false,
        	            roam: true,
        	            symbolSize: 6,
        	            itemStyle: {
        	                normal: {
        	                    color: '#4883b4',
        	                    label: {
        	                        show: true,
        	                        position: 'right',
        	                        formatter: "{b}",
        	                        textStyle: {
        	                            color: '#000',
        	                            fontSize: 14
        	                        }
        	                    },
        	                    lineStyle: {
        	                        color: '#ccc',
        	                        type: 'curve'

        	                    }
        	                },
        	                emphasis: {
        	                    color: '#4883b4',
        	                    label: {
        	                        show: false
        	                    },
        	                    borderWidth: 0
        	                }
        	            },
        	            
        	            data: [
        	              {"name":"标签分类","children":data,"value":100}
        	              ]
        	        }
        	    ]
        	};
        
        myChart.setOption(option);                   
    </script>
</body>
</html>