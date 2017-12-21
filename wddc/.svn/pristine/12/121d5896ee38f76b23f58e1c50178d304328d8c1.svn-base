<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
    <!-- Loading Bootstrap js -->
	<script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <!-- echarts -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>
    <!--chosen-->
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.min.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/wddc/tiles/chosen/chosen.wddc.css"/>
    <script type="text/javascript" src="${ctx}/wddc/tiles/chosen/chosen.jquery.min.js"></script>

</head>
<body>
<jsp:include page="/cj/header.jsp"/>
<br/>
<div class="container">

    <div class="row">
        <div class="col-md-12">
            <h2 class="page-header" style="margin-top: 5px">数据交换量监控

                <span style="float: right;padding-right: 10px">
                <select class="chosen-select form-control" id="fwInfoId"  name="fwInfoId" onchange="initData()" data-placeholder="指定监控服务...">
                    <option value="">所有委办局</option>
                    <option value="">工商局</option>
                    <option value="">公安局</option>
                    <option value="">民政局</option>
                    <option value="">房地局</option>
                </select>
                <div class="btn-group">
                    <button class="btn btn-info" type="button" onclick="window.location.href='#'" >本日</button>
                    <button class="btn btn-default" type="button" onclick="window.location.href='#'">本月</button>
                    <button class="btn btn-default" type="button" onclick="window.location.href='#'">本年</button>
                </div>
               </span>
            </h2>

            <div class="wrapper">
                <div class="row jk-info">
                    <div class="col-md-3">
                        <div class="panel red-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <i class="fa fa-money"></i>
                                    </div>
                                    <div class="col-xs-8">
                                        <span class="state-title">节点数量</span>
                                        <h4 id = "alertCount">232</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel blue-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <i class="fa fa-remove"></i>
                                    </div>
                                    <div class="col-xs-8">
                                        <span class="state-title">委办局数量</span>
                                        <h4 id = "errorCount">17</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel green-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <i class="fa fa-user"></i>
                                    </div>
                                    <div class="col-xs-8">
                                        <span class="state-title">总接收量</span>
                                        <h4 id="usedCount">62,336,230</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="panel yellow-bg">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <i class="fa fa-eye"></i>
                                    </div>
                                    <div class="col-xs-8">
                                        <span class="state-title">总发送量</span>
                                        <h4 id="appliedCount">73,131,311</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="content">
                <div class="panel-body">
                    <div id="mainTable" style="width: 100%;height:350px;"></div>
                </div>

            </div>

        </div>

    </div>
</div>
<jsp:include page="/cj/foot.jsp"/>

</body>

<script type="text/javascript">

    $(document).ready(function() {
        //初始化服务选择列表
        initChart();
        $('.chosen-select').chosen({allow_single_deselect:true});
        $(window).off('resize.chosen').on('resize.chosen', function() {
            $('.chosen-select').each(function() {
                var $this = $(this);
                $this.next().css({'width': '300px'});
            });
        }).trigger('resize.chosen');
       
    });

    
    //初始化图表
    function initChart() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('mainTable'));
        // 指定图表的配置项和数据
       option = {
    title: {
        text: '数据接收发送量'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['接收量','发送量']
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: ['0:00','1:00','2:00','3:00','4:00','5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00',
               '13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00']
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value}条'
        }
    },
    series: [
        {
            name:'接收量',
            type:'line',
            data:[611,651,415,613,712,323,810,462,765,865,234,456,851,421,412,745,257,231,845,236,923,163,452,634],
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
        {
            name:'发送量',
            type:'line',
            data:[724,523,513,623,135,513,734,254,843,461,724,745,423,562,732,824,132,635,257,242,167,236,724,132],
            markPoint: {
        	data: [
                   {type: 'max', name: '最大值'},
                   {type: 'min', name: '最小值'}
               ]
            },
            markLine: {
                data: [
                    {type: 'average', name: '平均值'},
                    [{
                        symbol: 'none',
                        x: '90%',
                        yAxis: 'max'
                    }, {
                        symbol: 'circle',
                        label: {
                            normal: {
                                position: 'start',
                                formatter: '最大值'
                            }
                        },
                        type: 'max',
                        name: '最高点'
                    }]
                ]
            }
        }
    ]
};
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
</script>
</html>
