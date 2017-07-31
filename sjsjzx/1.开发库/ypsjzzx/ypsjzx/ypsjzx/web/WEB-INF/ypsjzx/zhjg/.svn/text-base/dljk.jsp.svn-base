<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${sys_title}</title>
    <%@ include file="/cj/meta.jsp" %>
    <!-- Loading Bootstrap -->
    <link href="${ctx}/wddc/tiles/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--font-awesome-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/awesome/css/font-awesome.min.css"/>
    <!-- Loading jquery -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/jquery-2.2.3.min.js"></script>
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/js/cookie.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/wddc.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/skins/css/btn.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/ace/css/ace.min.css">
	<style type="text/css">
        .dataTables_paginate.paging_bootstrap.pagination li {
            float: left;
            margin: 0 1px;
            border: 1px solid #ddd;
            list-style: none;
        }

        .dataTables_paginate.paging_bootstrap.pagination li.disabled a {
            color: #c7c7c7;
        }

        .dataTables_paginate.paging_bootstrap.pagination li a {
            color: #797979;
            padding: 5px 10px;
            display: inline-block;
        }

        .dataTables_paginate.paging_bootstrap.pagination li:hover a,
        .dataTables_paginate.paging_bootstrap.pagination li.active a {
            color: #fff;
            background: #65cea7;
            text-decoration: none;
        }

        .dataTables_paginate.paging_bootstrap.pagination li:hover,
        .dataTables_paginate.paging_bootstrap.pagination li.active {
            border-color: #65cea7;
        }

        .dataTables_paginate.paging_bootstrap.pagination li.disabled:hover,
        .dataTables_paginate.paging_bootstrap.pagination li.disabled:hover a {
            color: #C7C7C7;
            background: #fff;
            border-color: #DDDDDD;
            cursor: no-drop;
        }

        .dataTables_paginate.paging_bootstrap.pagination {
            float: right;
            margin-bottom: 15px;
        }
        body{
        	background-color: #ffffff;
        	font-size: 16px;
        }
        .footer{
        	position: absolute;
    		bottom: 0;
    		width: 100%;
    		height: 50px;
    		border-top: 1px solid #ccc;
    		padding-top: 0px;
        }
        .navbar{
        	min-height: 50px;
        }
    </style>
</head>
<body>
<jsp:include page="/cj/header.jsp"/>
	<div class="container">
		<div class="row">
			<h2 class="page-header" style="margin-top: 25px">登录监控</h2>
			<div class="col-md-12" style="border-bottom:1px dashed #ccc;">
                <div class="wrapper">
	                <div class="row jk-info">
	                    <div class="col-md-3">
	                        <div class="panel green-bg btn-success">
	                            <div class="panel-body">
	                                <div class="row">
	                                    <div align="center">
	                                        <span class="state-title"><span style="font-size:24px" id="xtdlzs">0</span>
	                                        <h4 id="usedCount">系统登录总数</h4>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-md-3">
	                        <div class="panel yellow-bg btn-info">
	                            <div class="panel-body">
	                                <div class="row">
	                                    <div align="center">
	                                        <span class="state-title" style="font-size:24px" id="jrdlzs">0</span>
	                                        <h4 id="appliedCount">今日系统登录总数</h4>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="col-md-3">
	                        <div class="panel blue-bg btn-danger">
	                            <div class="panel-body">
	                                <div class="row">
	                                    <div align="center">
	                                        <span class="state-title" style="font-size:24px" id="dlyj">0</span>
	                                        <h4 id="errorCount">非工作时间登录预警</h4>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                     </div>
                            <div class="col-md-3">
                                <div class="panel red-bg btn-warning">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div align="center">
                                                <span class="state-title" style="font-size:24px" id="zxrs">0</span>
                                                <h4 id="alertCount">当前在线人数</h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
	                	</div>
            		</div>
        		</div>
        		<div class="col-md-12" style="margin-top:15px">
        			<div class="col-md-9" id="loginChart" style="height:350px"></div>
        			<div class="col-md-3">
        				<div class="wrapper" style="margin-top:30px">
                                <div class="row">
                                    <div class="row">
                                        <div  class="width-100 label label-info label-xlg arrowed-in arrowed-right">
                                            <span align="center" style="font-family:'微软雅黑';font-size:20px">登录部门排名</span>
                                        </div>
                                    </div>
                                    <p id="startTime"></p>
                                    <p id="endTime"></p>
                                    <div align="left">
                                            <ul id="dpRank" class="list-unstyled spaced">
                                                <li style="border-bottom:1px dashed #BBB;">
                                                    <span class="badge badge-danger" style="font-size:18px">1</span>&nbsp;&nbsp;
                                                    <span  style="font-size:18px" id="pmm0">质检局</span>
                                                    <span  style="font-size:15px;float:right;margin-right: 25px" id="pms0">0</span>
                                                </li>
                                                <li style="border-bottom:1px dashed #BBB;">
                                                    <span class="badge badge-success" style="font-size:18px">2</span>&nbsp;&nbsp;
                                                    <span  style="font-size:18px" id="pmm1">公安局</span>
                                                    <span  style="font-size:15px;float:right;margin-right: 25px" id="pms1">0</span>
                                                </li>
                                                <li style="border-bottom:1px dashed #BBB;">
                                                    <span class="badge badge-purple" style="font-size:18px">3</span>&nbsp;&nbsp;
                                                    <span  style="font-size:18px" id="pmm2">税务局</span>
                                                    <span  style="font-size:15px;float:right;margin-right: 25px" id="pms2">0</span>
                                                </li>
                                                <li style="border-bottom:1px dashed #BBB;">
                                                    <span class="badge badge-grey" style="font-size:18px">4</span>&nbsp;&nbsp;
                                                    <span  style="font-size:18px" id="pmm3">科委</span>
                                                    <span  style="font-size:15px;float:right;margin-right: 25px" id="pms3">0</span>
                                                </li>
                                                <li style="border-bottom:1px dashed #BBB;">
                                                    <span class="badge badge-grey" style="font-size:18px">5</span>&nbsp;&nbsp;
                                                    <span  style="font-size:18px" id="pmm4">教育局</span>
                                                    <span  style="font-size:15px;float:right;margin-right: 25px" id="pms4">0</span>
                                                </li>
                                                <li style="border-bottom:1px dashed #BBB;">
                                                    <span class="badge badge-grey" style="font-size:18px">6</span>&nbsp;&nbsp;
                                                    <span  style="font-size:18px" id="pmm5">文化局</span>
                                                    <span  style="font-size:15px;float:right;margin-right: 25px" id="pms5">0</span>
                                                </li>
                                                <li style="border-bottom:1px dashed #BBB;">
                                                    <span class="badge badge-grey" style="font-size:18px">7</span>&nbsp;&nbsp;
                                                    <span  style="font-size:18px" id="pmm6">旅游局</span>
                                                    <span  style="font-size:15px;float:right;margin-right: 25px" id="pms6">0</span>
                                                </li>
                                            </ul>
                                    </div>
                                </div>
                            </div>
        			</div>
        		</div>
			</div>
		</div>
<jsp:include page="/cj/foot.jsp"/>
<script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
<script type="text/javascript">
	// 从服务获取数据 ${fwurl}?unitKey=***&fwCode=***
	$.ajax({
		type:"get",
		url:"${fwurl}?unitKey=${unitKey}&fwCode=fw00013&id=F00021",
		dataType:"text",
		success:function(result){
			var data = eval('('+result+')');
			$("#xtdlzs").html(data.DATA.logCount.result);
			$("#jrdlzs").html(data.DATA.todayLogCount.result);
			$("#dlyj").html(data.DATA.notWorkTime.result);
			$("#zxrs").html(data.DATA.onlineCount.result);
			for(var i=0;i<data.DATA.dpRank.dpname.length;i++){
                $('#pmm'+i).html(data.DATA.dpRank.dpname[i]);
                $('#pms'+i).html(data.DATA.dpRank.count[i]);
            }
			
			//获取一个月的时间周期
			//String----date：传入的日期
			//Int-------days: 减去的时间周期
			function addDate(date,days){ 
				var nowDay= new Date(date);
				var oldDay = nowDay.setDate(nowDay.getDate() - days);
				var month=nowDay.getMonth()+1;
				var day = nowDay.getDate(); 
				if(month<13){ 
					month = month; 
				} 
				if(day<10){ 
					day = day;
				} 
				//var val = nowDay.getFullYear()+""+month+""+day;
				var val = month+"/"+day;
				return val; 
			}
			//时间格式化
			function FormatTime (strTime) {
			    var date = new Date(strTime);
			    //return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
				return (date.getMonth()+1)+"/"+date.getDate();
			}
			// timeArray:一个月时间周期
			var timeArray = [];
			for(var i = 29;i >= 0;i--){
				timeArray[29-i] = addDate(data.DATA.currentDate.result,i);
			}

			console.log(timeArray)
			// 创建字典对应value为0
			var timeData = {};
			for(var i = 0;i<timeArray.length;i++){
				timeData[timeArray[i]] = 0;
			}

			// 从服务中获取的数据与字典相替换
			data.DATA.monthLog.forEach(function(result){
				var datetime =  FormatTime(result.datetime);
				var count = parseInt(result.count);
				console.log(datetime);
				for(var key in timeData){
					if(JSON.stringify(key) == JSON.stringify(datetime)){
						timeData[key] = count;
					}
				}
			});
			
			var dataArray = [];
			for(var key in timeData){
				dataArray.push(timeData[key]);
			}
			
			// charts
			var myChart = echarts.init(document.getElementById('loginChart'));
			option = {
				    title: {
				        text: '近30日登陆情况',
				        x:'center'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    toolbox: {
				        show: true
				        
				    },
				    xAxis:  {
				        type: 'category',
				        boundaryGap: false,
				        data: timeArray,
				        axisLabel: {
	                        'interval': 0,
	                        rotate: 40,
	                        textStyle:{
				    			fontSize:10
                    		}
                    	}
				    },
				    yAxis: {
				        type: 'value',
				        axisLabel: {
				            formatter: '{value}'
				        }
				    },
				    series: [
				        {
				            name:'登陆次数',
				            type:'line',
				            data:dataArray,
				            markPoint: {
				                data: [
				                    {type: 'max', name: '最大值'},
				                    {type: 'min', name: '最小值'}
				                ]
				            },
				            markLine: {
				                data: [
				                    {type: 'average', name: '平均值'}
				                ]
				            }
				        },
				    ]
				};
			myChart.setOption(option);
		}
	});
	
</script>
</body>
</html>
