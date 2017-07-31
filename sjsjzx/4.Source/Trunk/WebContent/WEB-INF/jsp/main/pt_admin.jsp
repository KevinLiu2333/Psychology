<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/tiles/cj/title_setting.jsp" %>
<%@ include file="/common/taglibs.jsp" %>

    <script type="text/javascript" src="${ctx}/tiles/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/tiles/ace/js/ace.min.js"></script>
    <script type="text/javascript" src="${ctx}/tiles/ace/js/ace-elements.min.js"></script>
    <script type="text/javascript"  src="${ctx}/tiles/adapter/pie.js" ></script>
	<script type="text/javascript"  src="${ctx}/tiles/adapter/bar.js" ></script>
    <!-- echarts -->
    <script type="text/javascript" src="${ctx}/tiles/echarts/echarts.min.js"></script>

<div class="row">
    <h1 id="disable-responsive" class="page-header">数据汇聚</h1>
    <br/>
    <div class="row">
     
       <div class="col-md-8 col-sm-8 col-xs-8 infobox-container" >
										<div class="infobox infobox-green">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-street-view"></i>
											</div>

											<div class="infobox-data link-pannel" >
												<span class="infobox-data-number" id="rk">1,923,399</span>
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

											<div class="infobox-data link-pannel" >
												<span class="infobox-data-number" id="fr">236,987</span>
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

											<div class="infobox-data link-pannel" >
												<span class="infobox-data-number" id="fw">2,236,987</span>
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

											<div class="infobox-data link-pannel" >
												<span class="infobox-data-number" id="zyAllCount1">0</span>
												<div class="infobox-content">数据目录总量</div>
											</div>
										</div>

										<div class="infobox infobox-orange2">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-flask"></i>
											</div>

											<div class="infobox-data link-pannel" >
												<span class="infobox-data-number" id="fwCount1">0</span>
												<div class="infobox-content">共享服务总量</div>
											</div>

										</div>

										<div class="infobox infobox-blue2">
											<div class="infobox-icon">
												<i class="ace-icon fa fa-flask"></i>
											</div>

											<div class="infobox-data">
												<span class="infobox-data-number">3</span>

												<div class="infobox-content">
													接入单位总量
												</div>
											</div>
										</div>


        </div>
        <div class="col-md-4">
 			<div class="panel" style="padding-bottom:1px;">
                <div >
                    <div style="width:100%;border:1px dotted;border-color:#D8D8D8 !important;">
                        <div style="margin-left:3px;color:#555;">户籍人员：<span id="hjry"></span></div>
                        <div style="margin-left:3px;color:#555;">来沪人员：<span id="lhry"></span></div>
                        <div style="margin-left:3px;color:#555;">境外人员：<span id="jwry"></span></div>
                    </div>
                     <div style="width:48%;margin-top:3px;border:1px dotted;border-color:#D8D8D8 !important;float:left;">
                        <div style="margin-left:3px;color:#555;">法人登记:<span id="frdj">222</span></div>
                        <div style="margin-left:3px;color:#555;">本月新增:<span id="byxz"></span></div>
                        <div style="margin-left:3px;color:#555;">本月变更:<span id="bybg"></span></div>
                        <div style="margin-left:3px;color:#555;">本月注销:<span id="byzx"></span></div>
                    </div>
					<div style="width:50%;margin-top:3px;border:1px dotted;border-color:#D8D8D8 !important;float:left;margin-left:2%;">
                        <div style="margin-left:3px;color:#555;">法人处罚:<span id="frzz">11111</span></div>
                        <div style="margin-left:3px;color:#555;">法人处罚:<span  id="frcf">1212</span></div>
                        <div style="margin-left:3px;color:#555;">后置审批:<span id="hzsp">1111</span></div>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>


<div class="row">
    <h1 id="disable-responsive" class="page-header">数据交换</h1>
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
            <span class="editable" id="username">3</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 数据调用单位 </div>

        <div class="profile-info-value">
            <span class="editable" id="city">0</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 数据交换总量 </div>

        <div class="profile-info-value">
            <span class="editable" id="age">0</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 数据调用总量 </div>

        <div class="profile-info-value">
            <span class="editable" id="signup">0</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 本月交换量 </div>

        <div class="profile-info-value">
            <span class="editable" id="login">0</span>
        </div>
    </div>

    <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 本月调用量 </div>

        <div class="profile-info-value">
            <span class="editable" id="about">0</span>
        </div>
    </div>
      <div class="profile-info-row">
        <div class="profile-info-name" style="height:40px"> 最后更新日期 </div>

        <div class="profile-info-value">
            <span class="editable" id="about">0</span>
        </div>
    </div>
</div>
													
        </div>
    </div>
</div>


<div class="row">
    <h1 id="disable-responsive" class="page-header">数据综合利用</h1>
    <br/>
    <div class="row">
        <div class="col-md-4">
            <div class="panel">
                <div id="oldage" style="height:270px;"></div>
            </div>
        </div>
         <div class="col-md-4">
            <div class="panel">
                <div id='frcy' style="height:270px"></div>
            </div>
        </div>
        <div class="col-md-4" >
           <div class="panel">
                 <div id='frdjtj' style="height:270px"></div>
            </div>
        </div>
       
    </div>
</div>



<div class="row">
    <h1 id="disable-responsive" class="page-header">运行环境监控</h1>
    <div class="row">
        <div class="col-md-12"  id="ipzt" >
        	
        </div>
        
    </div>
    <div class="row">
        <div class="col-md-4" style="width:32%">
            <div class="panel">
                <div id="yibiaopan31" style="height:300px;"></div>
            </div>
        </div>
         <div class="col-md-4" style="width:32%">
            <div class="panel">
                <div id="yibiaopan3" style="height:300px;"></div>
            </div>
        </div>
        
        <div class="col-md-4" style="width:35%">
         <div class="panel">
         <span><h3>磁盘使用率：</h3></span>
         <div style="margin-top: 15px;"></div>
         <div id="yibiaopan2"  style="border:1px solid #DCEBF7;" >

       
   </div>
                    </div>
                </div>
            </div>
        </div>
   

<script type="text/javascript">
sjtj();
fr();
function sjtj(){
	$.post("${ctx}/echarts/queryjson?theme_id=39",
			{ Action: "post"},
			function(data, textStatus){
				data = eval('('+data+')');
				$('#hjry').html(data.hjry+"(其中本区户籍:"+data.bqhj+")");
				$('#lhry').html(data.lhry);
				$('#jwry').html(data.jwry);
			});
	}
$.post("${ctx}/home/to12",
		{ Action: "post"},
		function(data, textStatus){
			data = eval('('+data+')');
			$("#zyAllCount1").text(data['countML']);
			$("#fwCount1").text(data['countSQ']);
			
		});
$.post("${ctx}/echarts/queryjson?theme_id=31",
		{ Action: "post"},
		function(data, textStatus){
			data = eval('('+data+')');
		
			$("#frdj").text(data['法人登记']);
			$("#frzz").text(data['法人资质']);
			$("#byxz").text(data['法人本月新增']);
			$("#frcf").text(data['法人处罚']);
			
			$("#bybg").text(data['法人本月变更']);
			$("#hzsp").text(data['后置审批']);
			$("#byzx").text(data['法人本月注销']);
		});

//获取首页人口和法人数据
function fr(){
	$.post("${ctx}/echarts/queryjson?theme_id=77",
			{ Action: "post"},
			function(data, textStatus){
				data = eval('('+data+')');
				var rk;
				var fr;
				var bzf;
				if(data.syrk.length>4){
					rk=(Number(data.syrk/10000)).toFixed(2)+"万";
				}
				else{
					rk=data.syrk;
					}
				if(data.syfr.length>4 )
				{
					fr=(Number(data.syfr/10000)).toFixed(2)+"万";
					
					}
				else{
					fr=data.syfr;
					}
				if(data.ryfw.length>4){
					bzf=(Number(data.ryfw/10000)).toFixed(2)+"万";
					}else
					{
						bzf=data.ryfw;
					}
				$('#fw').html(bzf);
				$('#fr').html(fr);
				$('#rk').html(rk);
			});
}

function jhjk(){
	$.post("${ctx}/echarts/queryjson?theme_id=100",
			{ Action: "post"},
			function(data, textStatus){
				var data1 = eval('('+data+')');
	 var myChart = echarts.init(document.getElementById('jhjk'));
		
		option = {
			    title : {
					text: '松江区数据交换',
		        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			         x : 'center',
			        y : 'bottom',
			        data:['市公安','市经信委','科委']
			    },
			    series : [
			        {
			            name: '',
			            type: 'pie',
			            radius : '50%',
			            center: ['50%', '60%'],
			            data:[
							{value:data1['市公安'], name:'市公安'},
							{value:data1['市经信委'], name:'市经信委'},
							{value:data1['科委'], name:'科委'}
			            ],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
	myChart.setOption(option);
			});		
}

function drawoldage()
{
	$.post("${ctx}/echarts/queryjson?theme_id=39",
			{ Action: "post"},
			function(data, textStatus){
				data = eval('('+data+')');
				
				var myChart = echarts.init(document.getElementById("oldage"));
				option = {
					    title : {
					        text: '人口数据占比',
					        x:'center'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					         x : 'center',
					        y : 'bottom',
					        data: ['户籍人员','本区户籍人员','来沪人员','境外人员']
					    },
					    series : [
					        {
					            name: '',
					            type: 'pie',
					            radius : '55%',
					            center: ['50%', '60%'],
					            data:[
					                {value:data.hjry, name:'户籍人员'},
					                {value:data.bqhj, name:'本区户籍人员'},
					                {value:data.lhry, name:'来沪人员'},
					                {value:data.jwry, name:'境外人员'}
					            ],
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
					};
				myChart.setOption(option);
												});
}

//法人登记统计图 (成立+变更+注销)
function drawFrdjtj()
{
	$.post("${ctx}/echarts/queryjson?theme_id=70",
			{ Action: "post"},
			function(data, textStatus){
				data = eval('('+data+')');
				var myChart = echarts.init(document.getElementById("frdjtj"));
				option = {
						 title : {
				        text: '廉租房数据占比',
				        x:'center'
			   			 },
					    tooltip: {
					        trigger: 'item',
					        formatter: "{a} <br/>{b}: {c} ({d}%)"
					    },
					    legend: {
					        
					        x: 'center',
					       y : 'bottom',
					       data: ['动迁房','保障房','公租房','旧城改造','廉租房']
					    },
					    series: [
					        {
					            type:'pie',
					            radius: ['50%', '65%'],
					            avoidLabelOverlap: false,
					            label: {
					                normal: {
					                    show: false,
					                    position: 'center'
					                },
					                emphasis: {
					                    show: true,
					                    textStyle: {
					                        fontSize: '30',
					                        fontWeight: 'bold'
					                    }
					                }
					            },
					            labelLine: {
					                normal: {
					                    show: false
					                }
					            },
					            data:[
					                {value:data.dqf, name:'动迁房'},
					                {value:data.bzf, name:'保障房'},
					                {value:data.gzf, name:'公租房'},
					                {value:data.jcgz, name:'旧城改造'},
					                {value:data.lzf, name:'廉租房'}
					            ]
					        }
					    ]
					};
				myChart.setOption(option);
			
												});
			
}

function drawfrcy(){
	$.post("${ctx}/echarts/queryjson?theme_id=14",
			{ Action: "post"},
			function(data, textStatus){
				data = eval('('+data+')');
				var myChart = echarts.init(document.getElementById("frcy"));
				var color=['#CD5C5C','#32CD32','#2EC6C9','#8AED35','#FE9616','#44B7D3'];
				var option = {
						title: {
							text: '法人产业类型占比',
							subtext:'(单位:家)',
							x: 'center'
						},
						tooltip: {
							trigger: 'item',
							formatter: "{b}<br/>{c}"
						},
						legend: {
							orient: 'horizontal',
							x: 'center',
							y:'295',
							data: toPie_legend(data)
						},
						calculable: true,
						series: [{
							name: '法人产业',
							type: 'pie',
							radius : ['40',"55"],
							center: ['50%', '50%'],
							roseType : 'area',
							itemStyle: {
								normal: {
									label: {
									//	show:false
										formatter: '{b}:\n{c}' 
									},
									labelLine:{
										show:true
									}
								},emphasis : {
									label : {
										show : false,
										position : 'center',
										textStyle : {
											fontSize : '30',
		                        			fontWeight : 'bold'
										}
									}
								}
							},
							data: toPie_Seriesdate(data,color)
						}]
				};
				myChart.setOption(option);
			});
}

function yiyiy(ip,drive)

{
	 $(this).click(function(){
		 var liidx = $(this).index();
		 $("#span11").addClass("label label-default").siblings().removeClass("label-info");
	    });
	
	$.post("${ctx}/home/to11?ip="+$.trim(ip)+"&drive="+drive,
			{ Action: "post"},
			function(data, textStatus){
				data = eval('('+data+')');
				$("#yibiaopan2").empty();
				$("#yibiaopan31").empty();
				$("#yibiaopan3").empty();
				for(var j=0;j<data.dsk.length;j++)
				{
					$("#yibiaopan2").append(' <div class="profile-info-row" ><div  style="line-height:58px;color:#336199;background-color:#EDF3F4;width:70px;text-align:center; id="pf">'+data.dsk[j].diskName+'</div>'
							+' <div class="profile-info-value">'
				           +' <span class="editable" id="zl">总量：'+data.dsk[j].totalCount+' 已使用：'+data.dsk[j].usedPercent+'%</span></div></div>');

				}
				option31 = {
					    tooltip : {
					        formatter: "{a} <br/>{b} : {c}%"
					    },
					    series: [
					        {
					            name: 'cpu',
					            type: 'gauge',
					            detail: {formatter:'{value}%'},
					            data: [{value: data.cpu, name: 'CPU'}]
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
					            data: [{value: data.nc, name: '内存'}]
					        }
					    ]
					};
				 var myChart31 = echarts.init(document.getElementById('yibiaopan31'));
					myChart31.setOption(option31);

						 var myChart3 = echarts.init(document.getElementById('yibiaopan3'));
							myChart3.setOption(option3);
				
						});
							
	}




function yibiaopan()
{
	$.post("${ctx}/home/to11",
			{ Action: "post"},
			function(data, textStatus){
				data = eval('('+data+')');
				 for(var i=0;i<data.bt.length;i++)
				 {
					 var htl="<h3 class='io'><a href='javascript:void(0);'  onclick='yiyiy(\" "+data.bt[i].ip+"\",\""+data.bt[i].drive+"\")' style='float: left;margin: 15px;cursor:pointer;'><span id='span11' class='label label-info'>"+data.bt[i].ip+"</span></a></h3>";
					 $("#ipzt").append(htl);	
					 }
				for(var j=0;j<data.dsk.length;j++)
				{
					$("#yibiaopan2").append(' <div class="profile-info-row" ><div  style="line-height:58px;color:#336199;background-color:#EDF3F4;width:70px;text-align:center;" id="pf">'+data.dsk[j].diskName+'</div>'
					+' <div class="profile-info-value">'
		           +' <span class="editable" id="zl">总量：'+data.dsk[j].totalCount+' 已使用：'+data.dsk[j].usedPercent+'%</span></div></div>');

				}
			
	option31 = {
		    tooltip : {
		        formatter: "{a} <br/>{b} : {c}%"
		    },
		    series: [
		        {
		            name: 'cpu',
		            type: 'gauge',
		            detail: {formatter:'{value}%'},
		            data: [{value: data.cpu, name: 'CPU'}]
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
		            data: [{value: data.nc, name: '内存'}]
		        }
		    ]
		};
	 var myChart31 = echarts.init(document.getElementById('yibiaopan31'));
		myChart31.setOption(option31);

			 var myChart3 = echarts.init(document.getElementById('yibiaopan3'));
				myChart3.setOption(option3);
	
			});

}

jhjk();
drawoldage();
drawfrcy();
drawFrdjtj();
yibiaopan();

</script>
