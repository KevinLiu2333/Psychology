<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.wonders.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>松江区政务数据中心</title>
<link href="${ctx }/skins/index/css/style.css" rel="stylesheet">
<script src="${ctx }/skins/index/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/tiles/js/jquery.jqtransform.js" ></script>
<script src="${ctx}/tiles/echarts/echarts.js" ></script>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$.post("${ctx}/dp/piequery?info.sqlcode=20160516160951884",
				{ Action: "post"},
				function(data, textStatus){
					data = eval('('+data+')');
					$('#rkk').html(	Math.round( (data[0].value/100) )/100 +'万');
					$('#fwk').html( Math.round( (data[1].value/100) )/100 +'万');
					$('#frk').html( Math.round( (data[2].value/100) )/100 +'万');
					$('#ztk').html(data[3].value);
				}
			);
			
		});
</script>
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
    <ul class="nav_menu w1000">
        <li class="nm_cur"><a href="#">首页</a></li>
        <li><a href="${ctx}/home/toJh" target="_blank">资源共享交换平台</a></li>
        <li><a href="${ctx}/home/toZx" target="_blank">信息综合利用平台</a></li>
        <li><a href="${ctx}/home/toJk" target="_blank">实时安全监控平台</a></li>
        <li><a href="${ctx}/home/toGis" target="_blank">地理信息服务</a></li>
    </ul>
</div>
<div class="content ptb20">
	<dl class="chart_ibox w1000 mb20">
    	<dt>
        	<a href="#">
            	<img src="${ctx }/skins/index/images/chart_icon_01.png">
                人口库
                <span id="rkk"></span>
            </a>
            <b><div id="rkzl" style="height:160px"></div></b>
        </dt>
        <dd>
        	<a href="#">
            	<img src="${ctx }/skins/index/images/chart_icon_02.png">
                房屋库
                <span id="fwk"></span>
            </a>
            <b><div id='jzrk' style="height:160px;"></div></b>
        </dd>
    </dl>
	<dl class="chart_ibox w1000">
    	<dt>
        	<a href="#">
            	<img src="${ctx }/skins/index/images/chart_icon_03.png">
                法人库
                <span id="frk"></span>
            </a>
            <b><div id='frdjtj' style="height:160px"></div></b>
        </dt>
        <dd>
        	<a href="#">
            	<img src="${ctx }/skins/index/images/chart_icon_04.png">
                专题库
                <span>0</span>
            </a>
            <b><img src="${ctx }/skins/index/images/chart_i04.jpg"></b>
        </dd>
    </dl>
</div>
<div class="content bg_white">
	<dl class="norm w1000">
    	<dt>
        	<h1>重点指标</h1>
            <span><div id='lnrk' style="height:240px"></div></span>
        </dt>
    	<dt>
        	<h1>标准规范</h1>
            <ul>
            	<c:forEach items="${obj.doc }" var="doc">
            		<li>
                		<em><span><fmt:formatDate value="${doc.postedtime }" type="both" pattern="MM-dd"/></span><a href="${ctx}/db/file/fileDownById?fid=${doc.fid}">${doc.filenamelocal }</a></em>
                		<em><span><fmt:formatDate value="${doc.postedtime }" type="both" pattern="yyyy"/></span><i>科学技术委员会</i></em>
                	</li>
            	</c:forEach>
            </ul>
        </dt>
    </dl>
</div>
<div class="content bg_dgreen">
	<div class="share w1000">
    	<h1>数据共享交换平台</h1>
        <dl>
        	<dt class="bg_blue">
            	<span><img src="${ctx }/skins/index/images/share_icon_01.png">资源目录管理</span>
                <ul>
                	<li><a href="${ctx}/home/toJh?id=zxbm" target="_blank" onClick="changeposition('在线编目');">在线编目</a></li>
                	<li><a href="${ctx}/home/toJh?id=zysh" target="_blank" onClick="changeposition('资源审核发布');">资源审核发布</a></li>
                	<li><a href="${ctx}/zymlgl/toContentIndex" target="_blank" onClick="changeposition('资源目录检索');">资源x目录检索</a></li>
                	<!-- 
                	<li><a href="${ctx}/home/toJh?id=zyml" target="_blank" onClick="changeposition('资源目录检索');">资源目录检索</a></li>
                	 -->
                </ul>
                <b><a href="#">&nbsp;</a></b>
            </dt>
        	<dt class="bg_dgreen">
            	<span><img src="${ctx }/skins/index/images/share_icon_02.png">资源目录共享</span>
                <ul>
                	<li><a href="${ctx}/home/toJh?id=zysq" target="_blank" onClick="changeposition('资源申请');">资源申请</a></li>
                	<li><a href="${ctx}/home/toJh?id=sqsh" target="_blank" onClick="changeposition('申请审核');">申请审核</a></li>
                	<li><a href="${ctx}/home/toJh?id=zyhq" target="_blank" onClick="changeposition('资源获取');">资源获取</a></li>
                </ul>
                <b><a href="#">&nbsp;</a></b>
            </dt>
        	<dt class="bg_green">
            	<span><img src="${ctx }/skins/index/images/share_icon_03.png">数据交换管理</span>
                <ul>
                	<li><a href="${ctx}/home/toJh?id=jdjk" target="_blank" onClick="changeposition('交换节点监控')">节点监控</a></li>
                	<li><a href="${ctx}/home/toJh?id=rksj" target="_blank" onClick="changeposition('人口数据库')">人口数据库</a></li>
                	<li><a href="${ctx}/home/toJh?id=jktj" target="_blank" onClick="changeposition('监控统计')">监控统计</a></li>
                </ul>
                <b><a href="#">&nbsp;</a></b>
            </dt>
        	<dt class="bg_red">
            	<span><img src="${ctx }/skins/index/images/share_icon_04.png">交换运行监督</span>
                <ul>
                	<li><a href="${ctx}/home/toJk?id=gnyj" target="_blank" onClick="changeposition('功能预警')">统一预警</a></li>
                	<li><a href="${ctx}/home/toJk?id=aqsj" target="_blank" onClick="changeposition('安全审计')">安全审计</a></li>
                	<li><a href="${ctx}/home/toJk?id=yjsb" target="_blank" onClick="changeposition('硬件设备')">统一监控</a></li>
                </ul>
                <b><a href="#">&nbsp;</a></b>
            </dt>
        </dl>
    </div>
</div>
<div class="content bg_white ptb20">
	<dl class="platform w1000">
    	<dt>
        	<span><img src="${ctx }/skins/index/images/chart_03.jpg"></span>
            <a class="bg_green" href="${ctx}/home/toZx" target="_blank">信息综合利用平台</a>
        </dt>
        <dd>
        	<span><img src="${ctx }/skins/index/images/chart_04.jpg"></span>
            <a class="bg_violet" href="${ctx}/home/toJk" target="_blank">中心统一监控平台</a>
        </dd>
        <dt class="map">
        	<span><img src="${ctx }/skins/index/images/chart_05.jpg"></span>
            <a class="bg_blue" href="${ctx}/home/toGis" target="_blank">地理信息服务</a>
        </dt>
    </dl>
</div>
<div class="footer"><span><b>Copyright © 2015 znsj.com All Rights Reserved   松江区科委  版权所有</b></span></div>
</body>
<script>
/*导航菜单切换*/
$(".nav_menu li").mouseover(function(){
	$(this).addClass("nm_cur").siblings().removeClass("nm_cur");
});
</script>
<script type="text/javascript">
	require.config({
		paths: {
			echarts: '${ctx}/tiles/echarts'
		}
	});
	require([
				'echarts',
				'echarts/chart/line',
				'echarts/chart/bar',
			],function (ec){
		drawrkzl(ec);
		drawJzrk(ec);
		drawFrdjtj(ec);
		drawlnrk(ec);
	});
	//人口总量走势
	function drawrkzl(ec)
	{
		//2016519150003
		var temp;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519153522867",
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp = data;
         }
     	});
		
		var myChart = ec.init(document.getElementById("rkzl"));
		var option ={
				 title : {
				        
				        x:'left'
				    }, tooltip : {
				        trigger: 'axis'
				    },
				    smooth:true,
				    calculable : true,
				    xAxis : [
				             {
				                 type : 'category',
				                 boundaryGap : false,
				                 data : [temp[0].name,temp[1].name,temp[2].name,temp[3].name,temp[4].name,temp[5].name,temp[6].name,temp[7].name]
				             }
				         ],
				         grid: { // 控制图的大小，调整下面这些值就可以，
				             x:  15,
				             x2: 10,
				             y:	 10,
				             y2: 20,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
				         },
				         yAxis : [
				                  {
				                      type : 'value',
				                      axisLabel : {
				                          formatter: ' '
				                      },
				                  }
				              ],
				              series : [
				                        {
				                        	type:'line',
				                        	data:[temp[0].value,temp[1].value,temp[2].value,temp[3].value,temp[4].value,temp[5].value,temp[6].value,temp[7].value],
				                        	smooth:true,
				                        	
				                            
				                        }
				                        ]
				    
		};
		myChart.setOption(option);
	}
	//分街道 -居住人口 柱状图  
	function drawJzrk(ec)
	{
		var myChart = ec.init(document.getElementById("jzrk"));

		var temp;
		$.ajax({
			type : "GET",
			url : "${ctx}/dp/piequery?info.sqlcode=20160512145927238",
			data : {},
			async : false,
			success : function(data) {
				temp = eval('(' + data + ')');

			}
		});

		option = {
			
			tooltip : {
				trigger : 'axis'
			},
			
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : [ temp[0].name, temp[1].name, temp[2].name,
						temp[3].name, temp[4].name, temp[5].name,
						temp[6].name, temp[7].name, temp[8].name,
						temp[9].name, temp[10].name, temp[11].name,
						temp[12].name, temp[13].name, temp[14].name,
						temp[15].name, temp[16].name, temp[17].name ],
				axisLabel : {
					formatter : function(val) {
						return val.split("").join("\n");
					},
					textStyle : {
						color : '#B6A2DE'

					},
					interval : 0,
					//rotate:45,
					margin : 2,
				}

			} ],
			grid : { // 控制图的大小，调整下面这些值就可以，
				x  : 10,
				x2 : 10,
				y  : 10,
				y2 : 5,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
			},
			yAxis : [ {
				type : 'value',
				axisLabel : {
					formatter : ' '
				}
			} ],
			series : [ {
				name : '街(镇)房屋总数',
				type : 'bar',
				data : [ temp[0].value, temp[1].value, temp[2].value,
						temp[3].value, temp[4].value, temp[5].value,
						temp[6].value, temp[7].value, temp[8].value,
						temp[9].value, temp[10].value, temp[11].value,
						temp[12].value, temp[13].value, temp[14].value,
						temp[15].value, temp[16].value, temp[17].value ],
				
				itemStyle : {
					normal : {
						color : "#68A44A"
					}
				}
			} ]
		};
		myChart.setOption(option);
	}
	//法人登记统计图 (成立+变更+注销)
	function drawFrdjtj(ec)
	{
		//使用SQLCode获取数据
		var temp1;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519172806993",//成立 20160519172806993  old:20160420112618683
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp1 = data;
         }
     	});
		
		$.ajax({
	         type: "GET",
	         url: "${ctx}/dp/piequery?info.sqlcode=20160519172831038",//变更
	         data: {},
	         async : false,
	         success: function(data){
	        		data = eval('('+data+')');
					temp2 = data;
	         }
	     	});
		
		$.ajax({
	         type: "GET",
	         url: "${ctx}/dp/piequery?info.sqlcode=20160519172846889",//注销
	         data: {},
	         async : false,
	         success: function(data){
	        		data = eval('('+data+')');
					temp3 = data;
	         }
	     	});
		
		var myChart = ec.init(document.getElementById('frdjtj'));
		var option = {
				title : {
			        
			        x:'left'
			    },tooltip : {
			        trigger: 'axis'
			    },
			    
			    
			    calculable : true,
			    xAxis : [
			             {
			                 type : 'category',
			                 boundaryGap : false,
			                 data : [temp1[0].name,temp1[1].name,temp1[2].name,temp1[3].name,temp1[4].name,temp1[5].name,
			                         temp1[6].name,temp1[7].name,temp1[8].name,temp1[9].name,temp1[10].name,temp1[11].name,
			                         temp1[12].name,temp1[13].name,temp1[14].name,temp1[15].name,temp1[16].name],
			             }
			         ],
			         grid: { // 控制图的大小，调整下面这些值就可以，
			             x:  15,
			             x2: 10,
			             y:	 0,
			             y2: 20,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
			         },
			         yAxis : [
			                  {
			                      type : 'value',
			                      axisLabel : {
			                          formatter: ' '
			                      },
			                  }
			     ],
			     series : [
							{
								name:"成立",
								type:'line',
								smooth:true,
								data:[temp1[0].value,temp1[1].value,temp1[2].value,temp1[3].value,temp1[4].value,temp1[5].value,
								      temp1[6].value,temp1[7].value,temp1[8].value,temp1[9].value,temp1[10].value,temp1[11].value,
								      temp1[12].value,temp1[13].value,temp1[14].value,temp1[15].value,temp1[16].value],
								itemStyle: {
									normal: {
										areaStyle: {type: 'default',
											//填充颜色
											color : 'rgba(95, 165, 85,0.8)'
											},
										}
								},
								
							},
							{
								name:"变更",
								type:'line',
								smooth:true,
								data:[temp2[0].value,temp2[1].value,temp2[2].value,temp2[3].value,temp2[4].value,temp2[5].value,
								      temp2[6].value,temp2[7].value,temp2[8].value,temp2[9].value,temp2[10].value,temp2[11].value,
								      temp2[12].value,temp2[13].value,temp2[14].value,temp2[15].value,temp2[16].value],
								itemStyle: {
									normal: {
										areaStyle: {type: 'default',
											//填充颜色
											color : 'rgba(255, 127, 80,0.8)'
											},
										}
								},
								
							},
							{
								name:"注销",
								type:'line',
								smooth:true,
								data:[temp3[0].value,temp3[1].value,temp3[2].value,876,temp3[4].value,temp3[5].value,temp3[6].value,
								      temp3[7].value,temp3[8].value,temp3[9].value,temp3[10].value,temp3[11].value,temp3[12].value,temp3[13].value,
								      temp3[14].value,temp3[15].value,temp3[16].value],
								itemStyle: {
									normal: {
										areaStyle: {type: 'default',
											//填充颜色
											color : 'rgba(135, 206, 250,0.8)'
											},
										}
								},
								
							}
	            ]
		};
		myChart.setOption(option);
	}
	//老龄人口走势图
	function drawlnrk(ec)
	{
		var temp;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519153904059",//大于60岁的
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp = data;
         }
     	});
		
		var temp2;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519154740017",//大于60岁的
         data: {},
         async : false,
         success: function(data){
        		data = eval('('+data+')');
				temp2 = data;
         }
     	});
		$('#llYear_1').html(temp[0].name);
		$('#llYear_2').html(temp[1].name);
		$('#llYear_3').html(temp[2].name);
		$('#llYear_4').html(temp[3].name);
		$('#llYear_5').html(temp[4].name);
		$('#llYear_6').html(temp[5].name);
		$('#llYear_7').html(temp[6].name);
		$('#60_1').html(temp[0].value);
		$('#60_2').html(temp[1].value);
		$('#60_3').html(temp[2].value);
		$('#60_4').html(temp[3].value);
		$('#60_5').html(temp[4].value);
		$('#60_6').html(temp[5].value);
		$('#60_7').html(temp[6].value);
		$('#80_1').html(temp2[0].value);
		$('#80_2').html(temp2[1].value);
		$('#80_3').html(temp2[2].value);
		$('#80_4').html(temp2[3].value);
		$('#80_5').html(temp2[4].value);
		$('#80_6').html(temp2[5].value);
		$('#80_7').html(temp2[6].value);
		
		
		var myChart = ec.init(document.getElementById("lnrk"));
		var option = {
				title : {
			        text: '老龄人口走势图 ',
			        x:'left'
			    },tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['60岁以上','80岁以上']
			    },
			    calculable : true,
			    xAxis : [
			             {
			                 type : 'category',
			                 boundaryGap : false,
			                 data : [temp[0].name,temp[1].name,temp[2].name,temp[3].name,temp[4].name,temp[5].name,temp[6].name]
			             }
			         ],
			         yAxis : [
			                  {
			                      type : 'value',
			                      axisLabel : {
			                          formatter: '{value} 人'
			                      },
			                  }
			     ],
			     series : [
							{
								name:"80岁以上",
								type:'line',
								smooth:true,
								data:[temp2[0].value,temp2[1].value,temp2[2].value,temp2[3].value,temp2[4].value,temp2[5].value,temp2[6].value],
								itemStyle: {normal: {areaStyle: {type: 'default'}}},
								markPoint : {
							        data : [
							            {type : 'max', name: '最大值'},
							            {type : 'min', name: '最小值'}
							        ]
							    }
							},
	                        {
	                        	name:"60岁以上",
	                        	type:'line',
	                        	smooth:true,
	                        	data:[temp[0].value,temp[1].value,temp[2].value,temp[3].value,temp[4].value,temp[5].value,temp[6].value],
	                        	itemStyle: {normal: {areaStyle: {type: 'default'}}},
	                        	markPoint : {
	                                data : [
	                                    {type : 'max', name: '最大值'},
	                                    {type : 'min', name: '最小值'}
	                                ]
	                            }
	                        }
	                        
	            ]
		};
		myChart.setOption(option);
	}
	
	</script>
	

</html>