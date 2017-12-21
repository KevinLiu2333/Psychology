/*
* @Author: wulong
* @Date:   2017-02-20 14:28:55
* @Last Modified by:   wulong
* @Last Modified time: 2017-03-14 10:25:15
*/

function fsCharts(data){
    // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('fs-charts'));

        option = {

    title: {
        text: '数据发送去向',
        left: 'right',
        top: 0,

    },

    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },

    series : [
        {
            name:'接收量',
            type:'pie',
            radius : '80%',
            center: ['50%', '50%'],
            data:data.sort(function (a, b) { return a.value - b.value}),
            roseType: 'angle',

            labelLine: {
                normal: {

                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            animationType: 'scale',
            animationEasing: 'elasticOut',
            animationDelay: function (idx) {
                return Math.random() * 200;
            }
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
         myChart.setOption(option);

}