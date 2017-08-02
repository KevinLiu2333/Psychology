<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/cj/title_setting.jsp" %>

    <script type="text/javascript" src="${ctx}/wddc/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/ace/js/ace.min.js"></script>
    <script type="text/javascript" src="${ctx}/wddc/tiles/ace/js/ace-elements.min.js"></script>
    <!-- echarts -->
    <script type="text/javascript" src="${ctx}/wddc/tiles/echarts/echarts.min.js"></script>


<div class="row">
    <h3 id="disable-responsive" class="page-header">平台运行状态</h3>
    <div class="row">
        <div class="col-md-12" >
           <img src="${ctx}/sjdc/home/yx.png" alt=""/>
        </div>
        
    </div>
    
   
</div>


<div class="row">
    <h3 id="disable-responsive" class="page-header">数据汇聚</h3>
    <br/>
    <div class="row">
       <div class="col-md-8 col-sm-8 col-xs-8 infobox-container" >
										<div class="infobox infobox-green">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-street-view"></i>
											</div>

											<div class="infobox-data link-pannel" onclick="window.open('${ctx}/zx/toZtPeople')">
												<span class="infobox-data-number">1,923,399</span>
												<div class="infobox-content">实有人口总数</div>
											</div>

											<div class="badge badge-success">
												+2697
												<i class="ace-icon fa fa-arrow-up"></i>
											</div>
										</div>

										<div class="infobox infobox-blue">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-sitemap"></i>
											</div>

											<div class="infobox-data link-pannel" onclick="window.open('${ctx}/zx/toFrjbqk')">
												<span class="infobox-data-number">236,987</span>
												<div class="infobox-content">实有法人总数</div>
											</div>

											<div class="badge badge-info">
												-357
												<i class="ace-icon fa fa-arrow-down"></i>
											</div>
										</div>

										<div class="infobox infobox-pink ">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-university"></i>
											</div>

											<div class="infobox-data link-pannel" onclick="window.open('${ctx}/zx/toZtBuilding')">
												<span class="infobox-data-number">2,236,987</span>
												<div class="infobox-content">实有房屋总数</div>
											</div>
											<div class="badge badge-success">
												+178
												<i class="ace-icon fa fa-arrow-up"></i>
											</div>
										</div>

										<div class="infobox infobox-red">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-flask"></i>
											</div>

											<div class="infobox-data link-pannel" onclick="toZy('')">
												<span class="infobox-data-number" id="zyAllCount1">0</span>
												<div class="infobox-content">数据目录总量</div>
											</div>
										</div>

										<div class="infobox infobox-orange2">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-flask"></i>
											</div>

											<div class="infobox-data link-pannel" onclick="toFw()">
												<span class="infobox-data-number" id="fwCount1">0</span>
												<div class="infobox-content">共享服务总量</div>
											</div>

										</div>

										<div class="infobox infobox-blue2">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-flask"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">20</span>

												<div class="infobox-content">
													接入单位总量
												</div>
											</div>
										</div>


        </div>
        <div class="col-md-4">
 			<div class="panel">
                <div class="panel-body extra-pad15">
                    <div class="col-sm-6 col-xs-6">
                        <div class="v-title">数据总量</div>
                        <div class="v-value">2,690,207</div>
                        <div id="visit-1" ></div>
                    </div>
                    <div class="col-sm-6 col-xs-6">
                        <div class="v-title">月增长量</div>
                        <div class="v-value">+28,173</div>
                        <div id="visit-2"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<div class="row">
    <h3 id="disable-responsive" class="page-header">数据交换</h3>
    <br/>
    <div class="row">
        <div class="col-md-8">
    <div class="panel">
            <div id="jhjk" style="height:270px;"></div>
            </div>
        </div>
        
       
        <div class="col-md-4">
<div class="profile-user-info profile-user-info-striped">
    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 交换接入单位 </div>

        <div class="profile-info-value">
            <span class="editable" id="username">6</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 数据调用单位 </div>

        <div class="profile-info-value">
            <span class="editable" id="city">20</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 数据交换总量 </div>

        <div class="profile-info-value">
            <span class="editable" id="age">2,690,207</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 数据调用总量 </div>

        <div class="profile-info-value">
            <span class="editable" id="signup">380,233</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 本月交换量 </div>

        <div class="profile-info-value">
            <span class="editable" id="login">20,236</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 本月调用量 </div>

        <div class="profile-info-value">
            <span class="editable" id="about">4,557</span>
        </div>
    </div>
      <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 最后更新日期 </div>

        <div class="profile-info-value">
            <span class="editable" id="about">2016-7-22</span>
        </div>
    </div>
</div>
													
        </div>
    </div>
</div>


<div class="row">
    <h3 id="disable-responsive" class="page-header">数据综合利用</h3>
    <br/>
    <div class="row">
        <div class="col-md-4">
            <div class="panel">
                <div id="lnrk" style="height:230px;"></div>
            </div>
        </div>
         <div class="col-md-4">
            <div class="panel">
               <div id='frdjtj' style="height:230px"></div>
            </div>
        </div>
        <div class="col-md-4">
           <div class="panel">
                <div class="panel-body extra-pad">
                    <h4 class="pros-title" ><b>更多专题分析</b></h4>
                    <div class="row">
                        <div class="col-sm-6 col-xs-6">
                            <div id="p-lead-1"></div>
                            <p class="p-chart-title"><a href="${ctx}/zx/toZtPeople">人口专题</a></p>
                        </div>
                        <div class="col-sm-6 col-xs-6">
                            <div id="p-lead-2"></div>
                            <p class="p-chart-title"><a href="${ctx}/zx/toFrjbqk">法人专题</a></p>
                        </div>
                     </div>
                     <div class="row">
                        <div class="col-sm-6 col-xs-6">
                            <div id="p-lead-3"></div>
                            <p class="p-chart-title"><a href="${ctx}/zx/toZtBuilding">房屋专题</a></p>
                        </div>
                        <div class="col-sm-6 col-xs-6">
                            <div id="p-lead-4"></div>
                            <p class="p-chart-title"><a href="${ctx}/zx/toHyztqk">行业专题</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
       
    </div>
</div>




<script type="text/javascript">
function drawlnrk()
	{

		var temp;
		$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519153904059",//大于60岁的
         data: {},
         async : false,
         success: function(data){

     	 	//alert("11111111111111111"+data);
        	//	data = eval('('+data+')');
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
        		//data = eval('('+data+')');
				temp2 = data;
         }
     	});
	    var myChart = echarts.init(document.getElementById('lnrk'));
		var option = {
				title : {
			        text: '老龄人口走势图 ',
			        x:'left'
			    },tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        x:'right',
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


function drawFrdjtj()
{
	
	//使用SQLCode获取数据
	var temp1;
	$.ajax({
     type: "GET",
     url: "${ctx}/dp/piequery?info.sqlcode=20160519172806993",//成立 20160519172806993  old:20160420112618683
     data: {},
     async : false,
     success: function(data){
    		//data = eval('('+data+')');
			temp1 = data;
     }
 	});
	
	$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519172831038",//变更
         data: {},
         async : false,
         success: function(data){
        		//data = eval('('+data+')');
				temp2 = data;
         }
     	});
	
	$.ajax({
         type: "GET",
         url: "${ctx}/dp/piequery?info.sqlcode=20160519172846889",//注销
         data: {},
         async : false,
         success: function(data){
        		//data = eval('('+data+')');
				temp3 = data;
         }
     	});
	

    var myChart = echarts.init(document.getElementById('frdjtj'));
	var option = {
		    title : {
		        text: '法人登记信息 ',
		        x:'left'
		    },tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        x:'right',
		        data:['成立','变更','注销']
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
function jhjk(){
	option = {
		    title : {
		        text: '半年内交换信息分析',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        x : 'center',
		        y : 'bottom',
		        data:['民政局','税务局','计生委','公安局','教育局','房管局']
		    },
		    calculable : true,
		    series : [
		        {
		            name:'各单位数据接入量',
		            type:'pie',
		            radius : [10, 100],
		            center : ['25%', '50%'],
		            roseType : 'radius',
		            label: {
		                normal: {
		                    show: false
		                },
		                emphasis: {
		                    show: true
		                }
		            },
		            lableLine: {
		                normal: {
		                    show: false
		                },
		                emphasis: {
		                    show: true
		                }
		            },
		            data:[
		                {value:10, name:'民政局'},
		                {value:5, name:'税务局'},
		                {value:15, name:'计生委'},
		                {value:25, name:'公安局'},
		                {value:20, name:'教育局'},
		                {value:35, name:'房管局'}
		            ]
		        },
		        {
		            name:'各单位数据使用量',
		            type:'pie',
		            radius : [10, 100],
		            center : ['75%', '50%'],
		            roseType : 'area',
		            
		            data:[
		                {value:10, name:'民政局'},
		                {value:5, name:'税务局'},
		                {value:15, name:'计生委'},
		                {value:25, name:'公安局'},
		                {value:20, name:'教育局'},
		                {value:35, name:'房管局'}
		            ]
		        }
		    ]
		};


    var myChart = echarts.init(document.getElementById('jhjk'));
	myChart.setOption(option);
			
}
function yibiaopan()
{
	option = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    series: [
		        {
		            name: 'cpu',
		            type: 'gauge',
		            detail: {formatter:'{value}%'},
		            data: [{value: 50, name: 'CPU'}]
		        }
		    ]
		};
	option2 = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    series: [
		        {
		            name: '硬盘',
		            type: 'gauge',
		            detail: {formatter:'{value}%'},
		            data: [{value: 20, name: '硬盘'}]
		        }
		    ]
		};
	option3 = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    series: [
		        {
		            name: '内存',
		            type: 'gauge',
		            detail: {formatter:'{value}%'},
		            data: [{value: 35, name: '内存'}]
		        }
		    ]
		};
	 var myChart1 = echarts.init(document.getElementById('yibiaopan1'));
		myChart1.setOption(option);

		 var myChart2 = echarts.init(document.getElementById('yibiaopan2'));
			myChart2.setOption(option2);

			 var myChart3 = echarts.init(document.getElementById('yibiaopan3'));
				myChart3.setOption(option3);
	setInterval(function () {
	    option.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
	    myChart1.setOption(option, true);
	},2000);
	setInterval(function () {
	    option2.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
	    myChart2.setOption(option2, true);
	},2000);
	setInterval(function () {
	    option3.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
	    myChart3.setOption(option3, true);
	},2000);
	

}

drawlnrk();
drawFrdjtj();
jhjk();
yibiaopan();
</script>
