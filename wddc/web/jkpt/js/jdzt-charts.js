/*
* @Author: wulong
* @Date:   2017-02-13 16:56:39
* @Last Modified by:   wulong
* @Last Modified time: 2017-04-27 15:57:04
*/
function jdztCharts(data){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('jdzt-charts'));
    var a = 111;

    var dpList = data.dpList;
    var jdCount = data.jdCount;

    // 字典转换工具
    // 1为警告，2为错误，0为正常
    function TransferColor(data){
        if (1 == data) {
            return  '#FFB752'
        }
        else if(2 == data){
            return '#D15B47'
        }
        else return '#87B87F'
    }

    function　TransferIcon(data){
        if (1 == data) {
            return 'glyphicon-exclamation-sign'
        }else if(2 == data){
            return 'glyphicon-remove'
        }else return 'glyphicon-ok'
    }

    function TransferCss(data){
        if (1 == data) {
            return 'alert-warning'
        }else if(2 == data){
            return 'alert-danger'
        }else return 'alert-success'
    }

    option = {
        //气泡显示各控件的详情
        tooltip: {
        },

        animationDurationUpdate: 500,
        animationEasingUpdate: 'quinticInOut',
        series: [
        // 正常画圈和画线
        {
        type: 'graph',
        layout: 'none',
        //圈圈大小
        symbolSize: 60,
        // symbol:'roundRect',
        roam: true, //鼠标滚动放大缩小效果
        center:['20%','20%'],
        // 连接线两端的大小和类型
        edgeSymbol : [ 'arrow', 'arrow' ],
        edgeSymbolSize : [ 5, 5 ],
        edgeLabel: {
            normal: {
                show: true,
                textStyle: {
                    fontSize: 20
                }
            }
        },
        itemStyle:{
            normal:{
                color:'#87B87F'
                // color:'green'
            }
        },
        lineStyle: {
            normal: {
                opacity: 1,
                width: 2,
                // 线条弯曲度
                // curveness: 0.1
            }
        },
        // 圆圈内的文字
        label: {
            normal: {
                show: true,
                textStyle:{
                    fontSize:12
                }
            }
        },
        // 设置动画延迟
        animationDelay: function (idx) {
            return idx * 100;
        },
        // 设置动画的缓动效果
        animationEasing:'elasticOut',
        data: [
                {
                name: dpList[0],
                x: 550,
                y: 250,
                symbolSize: 100,
                value:jdCount[0],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[0]),
                        // borderColor:TransferColor(2),
                        // borderWidth:99
                        }
                    },
                },

                {
                name: dpList[1],
                x: 590,
                y: 140,
                value:jdCount[1],
                //自定义各个item的颜色
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[1]),
                        // borderColor:TransferColor(1),
                        // borderWidth:59
                        }
                    },
                },

                {
                name: dpList[2],
                x: 650,
                y: 185,
                value:jdCount[2],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[2]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },

                {
                name: '公安局',
                x: 670,
                y: 250,
                value:jdCount[3],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[3]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },

                {
                name: dpList[4],
                x: 650,
                y: 315,
                value:jdCount[4],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[4]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },

                },

                {
                name: dpList[5],
                x: 590,
                y: 360,
                value:jdCount[5],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[5]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },

                {
                name: dpList[6],
                x: 510,
                y: 360,
                value:jdCount[6],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[6]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },

                },

                {
                name: dpList[7],
                x: 450,
                y: 315,
                value:jdCount[7],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[7]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },

                {
                name: dpList[8],
                x: 430,
                y: 250,
                value:jdCount[8],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[8]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                // --
                {
                name: dpList[9],
                x: 450,
                y: 185,
                value:jdCount[9],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[9]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[10],
                x: 510,
                y: 140,
                value:jdCount[10],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[10]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },

                {
                name: dpList[11],
                x: 680,
                y: 105,
                value:jdCount[11],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[11]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[12],
                x: 740,
                y: 200,
                value:jdCount[12],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[12]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[13],
                x: 740,
                y: 300,
                value:jdCount[13],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[13]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[14],
                x: 680,
                y: 395,
                value:jdCount[14],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[14]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[15],
                x: 550,
                y: 440,
                value:jdCount[15],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[15]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[16],
                x: 420,
                y: 395,
                value:jdCount[16],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[16]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[17],
                x: 360,
                y: 300,
                value:jdCount[17],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[17]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[18],
                x: 360,
                y: 200,
                value:jdCount[18],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[18]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[19],
                x: 420,
                y: 105,
                value:jdCount[19],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[19]),
                        // borderColor:TransferColor(2),
                        // borderWidth:59
                        }
                    },
                },
                {
                name: dpList[20],
                x: 550,
                y: 60,
                value:jdCount[20],
                itemStyle:{
                    normal:{
                        color:TransferColor(data.type[20]),
                        // borderColor:TransferColor(0),
                        // borderWidth:59
                        }
                    },
                }
            ],   // ---data end---

        // links: [],
        links: [
            {
            source: dpList[0],
            target: dpList[1],
            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[2],
            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: '公安局',
            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[4],
            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[5],
            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[6],
            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[7],
            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[8],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            // --
            {
            source: dpList[0],
            target: dpList[9],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[10],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[11],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[12],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[13],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[14],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[15],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[16],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[17],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[18],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[19],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
            {
            source: dpList[0],
            target: dpList[20],

            label : {
                    normal : {
                        show : false
                    }
                }
            },
        ] // --- links end---
    },

    ] // series end
};

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    // 设置初始列表
    data.jdInfo[0].data.forEach(function (data) {
        $('#ngRepeat').append(
            '<div class="alert '+TransferCss(data.type)+'" style="border-left:6px solid '+TransferColor(data.type)+';margin-bottom: 10px;height: 55px">'+
            '<i class="col-md-1 glyphicon '+TransferIcon(data.type)+'" style="padding-left: 10px;padding-right: 15px;margin-top:4px;width:14px">&nbsp;&nbsp;&nbsp;</i>'+
            '<p class="col-md-6 ng-binding">'+data.jdName+'节点交换</p>'+
            '<div class="col-md-5 ng-binding">'+
            '<i class="ace-icon fa fa-clock-o bigger-110" style="padding-right: 10px"></i>'+
            data.jdTime+
            '</div>'+
            '</div>'+
            '</div>')
    })
    // 处理点击事件并且跳转到相应的百度搜索页面
    myChart.on('click', function (params) {
        // window.open('../数据中心监控平台/jdzt.html');
        // console.log(params.name);
        // alert(params.name);
         // window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.seriesName));
          if (params.componentType === 'markPoint') {
                if (params.seriesIndex === 5) {
                }
         }
         else if (params.componentType === 'series') {
                if (params.seriesType === 'graph') {
                    if (params.dataType === 'edge') {
                    }
                    else {
                        // 获取点击的index通过infoJson来解析相应的名字
                        $("#jdName").html(dpList[params.dataIndex]+"交换节点");
                        // 循环出每个值赋值给标签
                        $("#ngRepeat").html("");
                        // 判断当前节点是否有数据
                        if (data.jdInfo[params.dataIndex] != null){
                            data.jdInfo[params.dataIndex].data.forEach(function (data) {
                                $('#ngRepeat').append(
                                    '<div class="alert ' + TransferCss(data.type) + '" style="border-left:6px solid ' + TransferColor(data.type) + ';margin-bottom: 10px;height: 55px">' +
                                    '<i class="col-md-1 glyphicon ' + TransferIcon(data.type) + '" style="padding-left: 10px;padding-right: 15px;margin-top:4px;width:14px"></i>&nbsp;&nbsp;&nbsp;' +
                                    '<p class="col-md-6 ng-binding">' + data.jdName + '节点交换</p>' +
                                    '<div class="col-md-5 ng-binding">' +
                                    '<i class="ace-icon fa fa-clock-o bigger-110" style="margin-right: 5px"></i>' +
                                    data.jdTime +
                                    '</div>' +
                                    '</div>' +
                                    '</div>')

                            })
                        }else{
                            $("#ngRepeat").html("<h2 style='text-align:center'>当前部门没有交换数据</h2>");
                        }
                    }
                 }
            }

});
}