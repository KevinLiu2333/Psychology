<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="/cj/title_setting.jsp" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Insert title here</title>
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
            <style type="text/css">
                td,
                th {
                    text-align: center;
                }
            </style>
        </head>

        <body class="no-skin">
            <jsp:include page="/wdac/cj/sjclyy_header.jsp"></jsp:include>
            <div class="main-container" id="main-container">
                <script type="text/javascript">
                    try {
                        ace.settings.check('main-container', 'fixed');
                    } catch (e) {}
                </script>
                <jsp:include page="/wdac/cj/sjclyy_slider.jsp"></jsp:include>
                <div class="main-content">
                    <div class="main-content-inner">
                        <div class="page-content">
                            <div class="page-header">
                                <h1>数据增量比对
                                    <small>
                                            <i class="ace-icon fa fa-angle-double-right"></i>比对结果查询</small></h1>
                            </div>
                            <!-- /.page-header -->
                            <div class="widget-box">
                                <div class="widget-header widget-header-small">
                                    <h5 class="widget-title lighter">数据查询</h5>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <table id="simple-table" class="table table-striped table-bordered table-hover">
                                        <!-- 按标签分类统计 -->
                                        <div class="row">
                                            <div class="col-xs-10">
                                                <div id="fltjbg" style="height:320px;"></div>
                                            </div>
                                            <div class="col-xs-2">
                                                <div id="" style="height:360px;"></div>
                                            </div>
                                        </div>
                                    </table>
                                </div>
                                <!-- /.span -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.page-content -->
                    </div>
                    <jsp:include page="/wdac/cj/sjclyy_foot.jsp"></jsp:include>
                </div>
            </div>
            <!--[if !IE]> -->
            <script src="${ctx }/wdac/data-deal/js/jquery.2.1.1.min.js"></script>
            <!-- <![endif]-->
            <!--[if !IE]> -->
            <script type="text/javascript">
                window.jQuery || document.write("<script src='assets/js/jquery.min.js'>" + "<" + "/script>");
            </script>
            <!--[if !IE]> -->
            <script type="text/javascript">
                window.jQuery || document.write("<script src='${ctx }/wdac/data-deal/js/jquery.min.js'>" + "<" + "/script>");
            </script>
            <!-- <![endif]-->
            <!--[if IE]>
                <script type="text/javascript">window.jQuery || document.write("<script src='${ctx }/wdac/data-deal/js/jquery1x.min.js'>" + "<" + "/script>");</script>
            <![endif]-->
            <script src="${ctx }/wdac/data-deal/js/bootstrap.min.js"></script>
            <!--[if lte IE 8]>
               <script src="${ctx }/wdac/data-deal/js/excanvas.min.js"></script>
            <![endif]-->
            <script src="${ctx }/wdac/data-deal/js/ace-elements.min.js"></script>
            <script src="${ctx }/wdac/data-deal/js/ace.min.js"></script>
            <script>
                $("#dataDuibi").attr("class", "active open");
                $("#countIndex").attr("class", "active");
            </script>
            <!-- echarts js -->
            <script src="${ctx}/wddc/tiles/echarts/echarts.js"></script>
            <script type="text/javascript">
            var data = ${obj.data};
                //分类统计柱子
                var myChart = echarts.init(document.getElementById('fltjbg'));

                var option = {
                    title: {
                        x: 'center',
                        text: '2017年普陀人口迁入迁出后的数据变化情况',

                    },
                    tooltip: {
                        trigger: 'item'//触发类型
                    },
                    calculable: true,//拖拽时，是否实时更新。
                    grid: {
                        borderWidth: 0,
                        x: 100,
                        y: 60,
                        y2: 40
                    },
                    xAxis: [{
                        type: 'category',
                        show: true,
                        data: ['新增', '减少']
                    }],
                    yAxis: [{
                        type: 'value',
                        show: true
                    }],
                    series: [{
                        name: data.data,
                        barWidth: 40,
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: function(params) {
                                    // build a color map as your need.
                                    var colorList = ['#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B', '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD', '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'];
                                    return colorList[params.dataIndex];
                                },
                                label: {
                                    show: true,
                                    position: 'top',
                                    formatter: '{b}\n{c}'
                                }
                            }
                        },
                        data: data.xz, 
						
                    }]
                };
                myChart.setOption(option);
            </script>
        </body>

        </html>