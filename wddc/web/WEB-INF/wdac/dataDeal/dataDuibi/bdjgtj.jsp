<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/cj/title_setting.jsp"%>
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
		td,th{
            text-align: center;
           } 
	</style>
</head>
<body class="no-skin">
<jsp:include page="/wdac/cj/sjclyy_header.jsp"></jsp:include>
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
								数据增量比对
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>
									比对结果查询
								</small>
							</h1>
						</div><!-- /.page-header -->

					    <div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 class="widget-title lighter">数据查询</h5>
											</div>

											<div class="widget-body">
												<div class="widget-main">
													<form class="form-search">
														<div class="row">
															
															<table>
                                                            <tr>
                                                            <td width="5%"></td>
															<td width="10%">统计类型</td>
															<td width="20%">
															<select class="chosen-select form-control" id="form-field-select-3" data-placeholder="Choose a State..." onchange="setdata2()">
																<option value="">  </option>
																<option value="AL">法人数据</option>
																<option value="AK">人口数据</option>
																<option value="AZ">房屋数据</option>
															</select>
															</td>
															<td width="10px"></td>
															<td width="10%">统计名称</td>
															<td width="20%"><select class="chosen-select form-control" id="biao" data-placeholder="Choose a State..." disabled="disabled" onchange="change2()">
																<option value="">  </option>
																<option value="AL">近半年内迁出人员趋势情况</option>
																<option value="AK">特定街镇区域户籍迁出趋势情况</option>
																<option value="AZ">“小巨人”新增企业情况</option>
																<option value="AZ">房屋户籍情况</option>
															</select></td>
                                                            <td width="5px"></td>
                                                            
															<td width="5%"></td>
                                                                <td><div class="input-group">
																	
                                                                <span class="input-group-btn">
																		<button type="button" class="btn btn-purple btn-sm" onclick="tijiao()">
																			<span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
																			统计分析
																		</button>
																	</span>
																</div></td>
                                                            
                                                          <tr><td height="10px"></td></tr>
														   </tr>
														   </table>
															
														</div>
													</form>
												</div>
											</div>
										</div>

											<div class="row">
									<div class="col-xs-12">
										<table id="simple-table" class="table table-striped table-bordered table-hover">
											
												<!-- 按标签分类统计 -->
						<div class="row">
							<div class="col-xs-10" >
								<div id="fltjbg" style="height:320px;"></div>
							</div>
							<div class="col-xs-2">
								<div id="" style="height:360px;"></div>
							</div>
							
						</div>
											
										</table>
									</div><!-- /.span -->
                                
									
								</div><!-- /.row -->
									
					</div><!-- /.page-content -->
					</div>
				<jsp:include page="/wdac/cj/sjclyy_foot.jsp"></jsp:include>
			</div>
			</div>
			<!--[if !IE]> -->
		<script src="${ctx }/wdac/data-deal/js/jquery.2.1.1.min.js"></script>
	<!-- <![endif]-->
	<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery.min.js'>"+"<"+"/script>");
			function setdata(){
				$('#tim').removeAttr('disabled');
			}
			$('#simple-table').hide();
			function change(){
				$('#ziduan1').removeAttr('disabled');
			/*$('#simple-table').hide();
			  $('#simple-table').fadeIn("slow");*/
			}
			function jiance(){
				alert("检测完成！");
			}
			function tijiao(){
			  $('#simple-table').hide();
			  $('#simple-table').fadeIn("slow");
			}
            function setdata2(){
				$('#biao').removeAttr('disabled');
			}
			$('#test').hide();
			function change2(){
			/*$('#simple-table').hide();
			  $('#simple-table').fadeIn("slow");*/
			  $('#ziduan').removeAttr('disabled');
			}
		</script>
	
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
	<script>
		$("#dataDuibi").attr("class","active open");
		$("#countIndex").attr("class","active");
	</script>
 	<!-- echarts js -->
	<script src="${ctx }/wdac/data-deal/js/echarts.js"></script>
	
<script type="text/javascript">
			jQuery(function($) {
				//initiate dataTables plugin
				var oTable1 =
				$('#dynamic-table')
				//.wrap("<div class='dataTables_borderWrap' />")   //if you are applying horizontal scrolling (sScrollX)
				.dataTable( {
					bAutoWidth: false,
					"aoColumns": [
					  { "bSortable": false },
					  null, null,null, null, null,
					  { "bSortable": false }
					],
					"aaSorting": [],

					//,
					//"sScrollY": "200px",
					//"bPaginate": false,

					//"sScrollX": "100%",
					//"sScrollXInner": "120%",
					//"bScrollCollapse": true,
					//Note: if you are applying horizontal scrolling (sScrollX) on a ".table-bordered"
					//you may want to wrap the table inside a "div.dataTables_borderWrap" element

					//"iDisplayLength": 50
			    } );
				//oTable1.fnAdjustColumnSizing();


				//TableTools settings
				TableTools.classes.container = "btn-group btn-overlap";
				TableTools.classes.print = {
					"body": "DTTT_Print",
					"info": "tableTools-alert gritter-item-wrapper gritter-info gritter-center white",
					"message": "tableTools-print-navbar"
				}

				//initiate TableTools extension
				var tableTools_obj = new $.fn.dataTable.TableTools( oTable1, {
					"sSwfPath": "assets/swf/copy_csv_xls_pdf.swf",

					"sRowSelector": "td:not(:last-child)",
					"sRowSelect": "multi",
					"fnRowSelected": function(row) {
						//check checkbox when row is selected
						try { $(row).find('input[type=checkbox]').get(0).checked = true }
						catch(e) {}
					},
					"fnRowDeselected": function(row) {
						//uncheck checkbox
						try { $(row).find('input[type=checkbox]').get(0).checked = false }
						catch(e) {}
					},

					"sSelectedClass": "success",
			        "aButtons": [
						{
							"sExtends": "copy",
							"sToolTip": "Copy to clipboard",
							"sButtonClass": "btn btn-white btn-primary btn-bold",
							"sButtonText": "<i class='fa fa-copy bigger-110 pink'></i>",
							"fnComplete": function() {
								this.fnInfo( '<h3 class="no-margin-top smaller">Table copied</h3>\
									<p>Copied '+(oTable1.fnSettings().fnRecordsTotal())+' row(s) to the clipboard.</p>',
									1500
								);
							}
						},

						{
							"sExtends": "csv",
							"sToolTip": "Export to CSV",
							"sButtonClass": "btn btn-white btn-primary  btn-bold",
							"sButtonText": "<i class='fa fa-file-excel-o bigger-110 green'></i>"
						},

						{
							"sExtends": "pdf",
							"sToolTip": "Export to PDF",
							"sButtonClass": "btn btn-white btn-primary  btn-bold",
							"sButtonText": "<i class='fa fa-file-pdf-o bigger-110 red'></i>"
						},

						{
							"sExtends": "print",
							"sToolTip": "Print view",
							"sButtonClass": "btn btn-white btn-primary  btn-bold",
							"sButtonText": "<i class='fa fa-print bigger-110 grey'></i>",

							"sMessage": "<div class='navbar navbar-default'><div class='navbar-header pull-left'><a class='navbar-brand' href='#'><small>Optional Navbar &amp; Text</small></a></div></div>",

							"sInfo": "<h3 class='no-margin-top'>Print view</h3>\
									  <p>Please use your browser's print function to\
									  print this table.\
									  <br />Press <b>escape</b> when finished.</p>",
						}
			        ]
			    } );
				//we put a container before our table and append TableTools element to it
			    $(tableTools_obj.fnContainer()).appendTo($('.tableTools-container'));

				//also add tooltips to table tools buttons
				//addding tooltips directly to "A" buttons results in buttons disappearing (weired! don't know why!)
				//so we add tooltips to the "DIV" child after it becomes inserted
				//flash objects inside table tools buttons are inserted with some delay (100ms) (for some reason)
				setTimeout(function() {
					$(tableTools_obj.fnContainer()).find('a.DTTT_button').each(function() {
						var div = $(this).find('> div');
						if(div.length > 0) div.tooltip({container: 'body'});
						else $(this).tooltip({container: 'body'});
					});
				}, 200);



				//ColVis extension
				var colvis = new $.fn.dataTable.ColVis( oTable1, {
					"buttonText": "<i class='fa fa-search'></i>",
					"aiExclude": [0, 6],
					"bShowAll": true,
					//"bRestore": true,
					"sAlign": "right",
					"fnLabel": function(i, title, th) {
						return $(th).text();//remove icons, etc
					}

				});

				//style it
				$(colvis.button()).addClass('btn-group').find('button').addClass('btn btn-white btn-info btn-bold');

				//and append it to our table tools btn-group, also add tooltip
				$(colvis.button())
				.prependTo('.tableTools-container .btn-group')
				.attr('title', 'Show/hide columns').tooltip({container: 'body'});

				//and make the list, buttons and checkboxed Ace-like
				$(colvis.dom.collection)
				.addClass('dropdown-menu dropdown-light dropdown-caret dropdown-caret-right')
				.find('li').wrapInner('<a href="javascript:void(0)" />') //'A' tag is required for better styling
				.find('input[type=checkbox]').addClass('ace').next().addClass('lbl padding-8');



				/////////////////////////////////
				//table checkboxes
				$('th input[type=checkbox], td input[type=checkbox]').prop('checked', false);

				//select/deselect all rows according to table header checkbox
				$('#dynamic-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header

					$(this).closest('table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) tableTools_obj.fnSelect(row);
						else tableTools_obj.fnDeselect(row);
					});
				});

				//select/deselect a row when the checkbox is checked/unchecked
				$('#dynamic-table').on('click', 'td input[type=checkbox]' , function(){
					var row = $(this).closest('tr').get(0);
					if(!this.checked) tableTools_obj.fnSelect(row);
					else tableTools_obj.fnDeselect($(this).closest('tr').get(0));
				});




					$(document).on('click', '#dynamic-table .dropdown-toggle', function(e) {
					e.stopImmediatePropagation();
					e.stopPropagation();
					e.preventDefault();
				});


				//And for the first simple table, which doesn't have TableTools or dataTables
				//select/deselect all rows according to table header checkbox
				var active_class = 'active';
				$('#simple-table > thead > tr > th input[type=checkbox]').eq(0).on('click', function(){
					var th_checked = this.checked;//checkbox inside "TH" table header

					$(this).closest('table').find('tbody > tr').each(function(){
						var row = this;
						if(th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
						else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
					});
				});

				//select/deselect a row when the checkbox is checked/unchecked
				$('#simple-table').on('click', 'td input[type=checkbox]' , function(){
					var $row = $(this).closest('tr');
					if(this.checked) $row.addClass(active_class);
					else $row.removeClass(active_class);
				});



				/********************************/
				//add tooltip for small view action buttons in dropdown menu
				$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});

				//tooltip placement on right or left
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('table');
					var off1 = $parent.offset();
					var w1 = $parent.width();

					var off2 = $source.offset();
					//var w2 = $source.width();

					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			
		</script>
		<script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['一级标签']
            },
            xAxis : [
                {
                    type : 'category',
                    data : ["测试一","测试二","测试三","测试四","测试五","测试六"]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"一级标签",
                    "type":"bar",
                    "data":[5, 20, 40, 10, 10, 20]
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
	
	<script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main2')); 
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['二级标签']
            },
            xAxis : [
                {
                    type : 'category',
                    data : ["测试一","测试二","测试三","测试四","测试五","测试六"]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"二级标签",
                    "type":"bar",
                    "data":[5, 20, 40, 10, 10, 20]
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
	
	<script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main3')); 
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['三级标签']
            },
            xAxis : [
                {
                    type : 'category',
                    data : ["测试一","测试二","测试三","测试四","测试五","测试六"]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"三级标签",
                    "type":"bar",
                    "data":[5, 20, 40, 10, 10, 20]
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
	
	<script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main4')); 
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['四级标签']
            },
            xAxis : [
                {
                    type : 'category',
                    data : ["测试一","测试二","测试三","测试四","测试五","测试六"]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"四级标签",
                    "type":"bar",
                    "data":[5, 20, 40, 10, 10, 20]
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
	
	<script type="text/javascript">
        // 法人主题图表
        var myChart = echarts.init(document.getElementById('zhuti')); 
        
        var option = {
			title : {
				text: '主题标签统计',
				subtext: '纯属虚构',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient : 'vertical',
				x : 'left',
				data:['人口','法人','房屋','其他']
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {
						show: true, 
						type: ['pie', 'funnel'],
						option: {
							funnel: {
								x: '25%',
								width: '50%',
								funnelAlign: 'left',
								max: 1548
							}
						}
					},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			series : [
				{
					name:'主题标签统计',
					type:'pie',
					radius : '55%',
					center: ['50%', '60%'],
					data:[
						{value:335, name:'人口'},
						{value:310, name:'法人'},
						{value:234, name:'房屋'},
						{value:150,name:'其他'}
					   
					]
				}
			]
		};

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
	
	<script type="text/javascript">
        // 法人主题图表
        var myChart = echarts.init(document.getElementById('faren')); 
        
        var option = {
			title : {
				text: '法人主题标签统计',
				subtext: '纯属虚构',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient : 'horizontal',
				y : 'bottom',
				padding:1,
				data:['证件类','职业相关','行业相关']
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {
						show: true, 
						type: ['pie', 'funnel'],
						option: {
							funnel: {
								x: '25%',
								width: '50%',
								funnelAlign: 'left',
								max: 1548
							}
						}
					},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			series : [
				{
					name:'法人主题标签统计',
					type:'pie',
					radius : '55%',
					center: ['50%', '60%'],
					data:[
						
						{value:310, name:'职业相关'},
						{value:234, name:'行业相关'},
					   
					]
				}
			]
		};

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
	
	<script type="text/javascript">
        //人口图表
        var myChart = echarts.init(document.getElementById('renkou')); 
        
        var option = {
			title : {
				text: '人口主题标签统计',
				subtext: '纯属虚构',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient : 'horizontal',
				y : 'bottom',
				padding:1,
				data:['证件类','居住信息类','工作信息类']
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {
						show: true, 
						type: ['pie', 'funnel'],
						option: {
							funnel: {
								x: '25%',
								width: '50%',
								funnelAlign: 'left',
								max: 1548
							}
						}
					},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			series : [
				{
					name:'人口主题标签统计',
					type:'pie',
					radius : '55%',
					center: ['50%', '60%'],
					data:[
						{value:335, name:'证件类'},
						{value:310, name:'居住信息类'},
						{value:234, name:'工作信息类'}
						
					   
					]
				}
			]
		};

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
	
	<script type="text/javascript">
        //房屋图表
        var myChart = echarts.init(document.getElementById('fangwu')); 
        
        var option = {
			title : {
				text: '房屋主题标签统计',
				subtext: '纯属虚构',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient : 'horizontal',
				y : 'bottom',
				padding:[1,1,0,1],
				data:['证件类','房源类','危房类']
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {
						show: true, 
						type: ['pie', 'funnel'],
						option: {
							funnel: {
								x: '25%',
								width: '50%',
								funnelAlign: 'left',
								max: 1548
							}
						}
					},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			series : [
				{
					name:'房屋主题标签统计',
					type:'pie',
					radius : '55%',
					center: ['50%', '60%'],
					data:[
						{value:15, name:'证件类'},
						{value:10, name:'房源类'},
						{value:4, name:'危房类'}
						
					]
				}
			]
		};

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
	
	<script type="text/javascript">
        //分类统计柱子
        var myChart = echarts.init(document.getElementById('fltjbg')); 
        
		var option = {
			title: {
				x: 'center',
				text: '2017年4月-5月普陀人口迁入迁出后的数据变化情况',
				
			},
			tooltip: {
				trigger: 'item'
			},
			calculable: true,
			grid: {
				borderWidth: 0,
				x:100,
				y: 60,
				y2: 40
			},
			xAxis: [
				{
					type: 'category',
					show: true,
					data: ['新增', '减少', '更新', '不变']
				}
			],
			yAxis: [
				{
					type: 'value',
					show: true
				}
			],
			series: [
				{
					name: '标签分类统计',
					barWidth:40,
					type: 'bar',
					itemStyle: {
						normal: {
							color: function(params) {
								// build a color map as your need.
								var colorList = [
								  '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
								   '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
								   '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
								];
								return colorList[params.dataIndex];
							},
							label: {
								show: true,
								position: 'top',
								formatter: '{b}\n{c}'
							}
						}
					},
					data: [12,21,10,4,],
				   
				}
			]
		};
		myChart.setOption(option);
		
    </script>
	<script type="text/javascript">
        //分类统计饼图
        var myChart = echarts.init(document.getElementById('fltjbt')); 
        
		var option = {
			title : {
				text: '结果种类',
				subtext: '纯属虚构',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				 orient : 'vertical',
        x : 'left',
				data:['法人数据','人口数据','产业园数据']
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {
						show: true, 
						type: ['pie', 'funnel'],
						option: {
							funnel: {
								x: '25%',
								width: '50%',
								funnelAlign: 'left',
								max: 1548
							}
						}
					},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			series : [
				{
					name:'标签分类统计',
					type:'pie',
					radius : '55%',
					center: ['50%', '60%'],
					data:[
						{value:3315, name:'法人数据'},
						{value:3110, name:'人口数据'},
						{value:534, name:'产业园数据'}
					]
					
					
				}
			]
		};
                    
		myChart.setOption(option);
    </script>
    
	</body>
</html>