/*
 * @Author: wulong
 * @Date:   2017-03-09 10:51:42
 * @Last Modified by:   wulong
 * @Last Modified time: 2017-03-22 11:07:55
 */

function jdYearCharts(data){
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('jd-charts'));
    // 数据源
    var dataFSL =data.dataFSL.data;
    var dataJSL =data.dataJSL.data;

    function month(data) {
        for (var i = 0;i<data.length;i++){
            data[i] = data[i]+'年';
        }
        return data
    }
    var month = month(data.time);

    option = {
        baseOption: {
            timeline: {
                // y: 0,
                axisType: 'category',
                // realtime: false,
                // loop: false,
                autoPlay: true,
                // currentIndex: 2,
                playInterval: 2000,
                // controlStyle: {
                //     position: 'left'
                // },
                data: month,
                label: {
                    // value 对应上面data的value
                    formatter: '{value}'
                }
            },
            // 显示柱形图的各项的数值
            label:{
                normal:{
                    show: false,
                    position: 'outside'}
            },
            // title: {
            //     subtext: '数据来自后台数据库'
            // },
            // 显示柱形提示条
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            // 显示发送量和接收量的选项位置
            legend: {
                x: '70%',
                data: [ '发送量','接收量'],
                selected: { '发送量': true,'接收量':true,'节点数':false }
            },
            calculable : true,
            grid: {
                top: 110,
                bottom: 100
            },

            // X轴
            xAxis: [
                {
                    'type':'category',
                    'axisLabel':{'interval':0,rotate:45},
                    'name':'部门名称',
                    'data':data.dpList,
                    splitLine: {show: false}
                }
            ],

            // Y轴
            yAxis: [
                {
                    type: 'value',
                    name: '交换量/条',
                    // 不定死y值，这样会自动识别最大值，从而选择出适合的y轴范围
                    // max: 100000
                }
            ],
            series: [
                {name: '发送量', type: 'bar' },
                {name: '接收量', type: 'bar' },

                {
                    name: '节点数',
                    type: 'pie',
                    center: ['88%', '25%'],
                    radius: '28%'
                }
            ]
        },
        options: [
            {
                title: {text: month[0]+'各部门交换状态'+'\n\n总发送量：'+eval(dataFSL[0].join('+'))+'条'+'  总接收量：'+eval(dataJSL[0].join('+'))+'条'},
                series: [
                    {
                        data: dataFSL[0],
                        // 显示最大值标记点
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                            ],
                            symbolSize:65
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    },
                    {
                        data: dataJSL[0],
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                            ],
                            symbolSize:65
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            },
            {
                title: {text: month[1]+'各部门交换状态'+'\n\n总发送量：'+eval(dataFSL[1].join('+'))+'条'+'  总接收量：'+eval(dataJSL[1].join('+'))+'条'},
                series : [
                    {data: dataFSL[1]},
                    {data: dataJSL[1]},
                ]
            },
            {
                title: {text: month[2]+'各部门交换状态'+'\n\n总发送量：'+eval(dataFSL[2].join('+'))+'条'+'  总接收量：'+eval(dataJSL[2].join('+'))+'条'},
                series : [
                    {data: dataFSL[2]},
                    {data: dataJSL[2]},
                ]
            },
            {
                title: {text: month[3]+'各部门交换状态'+'\n\n总发送量：'+eval(dataFSL[3].join('+'))+'条'+'  总接收量：'+eval(dataJSL[3].join('+'))+'条'},
                series : [
                    {data: dataFSL[3]},
                    {data: dataJSL[3]},
                ]
            },
            {
                title: {text: month[4]+'各部门交换状态'+'\n\n总发送量：'+eval(dataFSL[4].join('+'))+'条'+'  总接收量：'+eval(dataJSL[4].join('+'))+'条'},

                series : [
                    {data: dataFSL[4]},
                    {data: dataJSL[4]},
                ]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
};