
//生成数据图表入口
	$.fn.echarts = function(options){
		var divId = this.attr("id");
		var echartId = options.echartId;
		var date = options.date;
		createMyDateEcharts(divId,echartId,date);
	};
//生成表格的入口
	$.fn.table = function(options){
		var divId = this.attr("id");
		var echartId = options.echartId;
		var date = options.date;
		createMyDateTable(divId,echartId,date);
		
	};	

	function createMyDateEcharts(divId,echartId,date){
		getEchartsObj(echartId,divId,date);
		
	}
	
	function createMyDateTable(divId,echartId,date){
		$.ajax({
	 		type  : "post",
	 		async : "false",
	 		data : {id : echartId,currDate : date},
	 		url : $('#js_ctx').val()+"/suite/chart/getEchartsObj",
	 		success : function(sourceData){
	 			sourceData = sourceData.echart;
	 			getTableEchartsData(sourceData,divId);
	 		}
	 	});
	}

	function getEchartsObj(echartId,divId,date){
		$.ajax({
	 		type  : "post",
	 		async : "false",
	 		//请求参数
	 		data : {id : echartId,currDate : date},
	 		url : $('#js_ctx').val()+"/suite/chart/getEchartsObj",
	 		success : function(sourceData){
	 			sourceData = sourceData.echart;
	 			getEchartsData(sourceData,divId);
	 		}
	 	});
	};
	
	function getEchartsData(sourceData,divId,date){
		 $.ajax({
	 		type  : "post",
	 		async : "false",
	 		data : {id : sourceData.fwDataParameter,type : "2",currDate : date},
	 		url : $('#js_ctx').val()+"/suite/data/term/getTermResultById",
	 		success : function(data){
 				echartsData = eval("(" + data + ")");
 				var echartObj = getEchartsObject(echartsData);
 				initChart(sourceData,echartObj,divId);
	 		}
	 	});
	}

	function getEchartsObject(echartsData){
		 var echartsObject = {legendData:getLegendData(echartsData),axisData:getAxisData(echartsData),seriesData:getSeriesData(echartsData),complexData:getComplexData(echartsData)};
		 return echartsObject;
	 }
	 
	 function initChart(sourceData,echartObj,divId){
	     var myChart = echarts.init(document.getElementById(divId));
	     var legendDataLength = echartObj.legendData.length;
	     var option = sourceData.optionConfig;
	     option = option.trim();
	     option = option.substring(9,(option.length - 1)) ;
	     option = eval("(" + option + ")");
	     //柱状或者折线图例
	     if( option.series[0].type =="bar" || option.series[0].type =="line" ){
	    	 if(option.legend != null&& option.legend != ''){
	    		 option.legend.data = echartObj.legendData;
	    	 }
	    	//还需要改进
	    	if(option.xAxis.type == "value" || option.xAxis[0].type == "value" ){
	    		option.yAxis[0].data = echartObj.axisData;
	    		option.yAxis.data = echartObj.axisData;
	    	}else if(option.yAxis.type == "value"||option.yAxis[0].type == "value"){
	    		option.xAxis[0].data = echartObj.axisData;
	    		option.xAxis.data = echartObj.axisData;
	    		
	    	}
	    	for(var i = 0;i<legendDataLength;i++){
		    	option. series[i].name = echartObj.legendData[i];
		    	option. series[i].data = echartObj.seriesData[i];
		     }
	     }else{
	    	 //饼图等
	    	 if(option.legend != null&& option.legend != ''){
	    		 option.legend.data = echartObj.axisData;
	    		 //option.legend.data = echartObj.legendData;
	    	 }
	    	 
	    	 for(var i = 0;i<legendDataLength;i++){
	    		 option.series[i].name = echartObj.legendData[i];
		    	 option.series[i].data = echartObj.complexData[i];
			     }
	    	// option.series[0].name = echartObj.legendData;
	    	 //option.series[0].data = echartObj.complexData[0];
	     }
	     myChart.setOption(option);
	     onclickEvent(myChart);
	 }
	
	function getLegendData(echartsData){
		var legendData = echartsData.key;
		return legendData;
	}
	function getAxisData(echartsData){
		var axisData = echartsData.axis;
		return axisData;
	}
	 function getSeriesData(echartsData){
		 var seriesData = [];
		 for(var i in echartsData.value){
			if(typeof(echartsData.value[i]) == "undefined"){
				seriesData[i] = 0;
	      	   }else{
	      		 seriesData.push(echartsData.value[i]);
	      	   }
		 }
		 return seriesData;
	 }
	 function getComplexData(echartsData){
		var axisData = getAxisData(echartsData);
		var complexData = new Array();
		for(var i in echartsData.value){
			var groupData = [];
			var innerData = echartsData.value[i];
			for(var j in innerData){
				var targetData = {value:innerData[j],name:axisData[j]};
				groupData.push(targetData);
			}
			
			complexData[i] = groupData ;
		}
		return complexData;
	 }

	 function getTableEchartsData(sourceData,divId,date){
		 $.ajax({
	 		type  : "post",
	 		async : "false",
	 		data : {id : sourceData.fwDataParameter,type : "2",currDate : date},
	 		url : $('#js_ctx').val()+"/suite/data/term/getTermResultById",
	 		success : function(data){
 				echartsData = eval("(" + data + ")");
 				var echartObj = getEchartsObject(echartsData);
 				createTable(echartObj,divId);
	 		}
	 	});
	}
	 function createTable(echartObj,divId){
		 createShowingTable(echartObj,divId);
	 }
	 
	 function createShowingTable(echartObj,divId){
		 var legendData = echartObj.legendData;
		 var axisData = echartObj.axisData; 
		 var seriesData = echartObj.seriesData;
		 var tableStr = "<table>";
		 tableStr = tableStr + "<thead><td>数据项描述</td>";
		 theadStr = "";
		 var theadLen = axisData.length;
		 for(var i=0;i<theadLen;i++){
			 theadStr = theadStr + "<td>"+axisData[i]+"</td>";
		 };
		 tableStr = tableStr+theadStr+"</thead>";
		 var legendLen = legendData.length;
		 var trStr = "";
		 var tdStr = "";
		 var bodyStr = "";
		 var i = 0;
		 for(i=0;i<legendLen;i++){
			 trStr = trStr +"<tr>"+"<td>"+legendData[i]+"</td>";
			 for(var j = 0;j<theadLen;j++){
				 tdStr = tdStr +"<td>"+seriesData[i][j]+"</td>";
			 }
			 bodyStr = bodyStr+trStr + tdStr +"</tr>";
			 trStr = "";
			 tdStr = "";
		 }
		 tableStr = tableStr+bodyStr;
		 $("#"+divId).html(tableStr);  
	 }
	 
	 function onclickEvent(myChart){
		 myChart.on('click', function (params) {
			 	alert(params.componentType+":"+params.seriesName+":"+params.name+" :"+params.value );
			 	//+":"+params.seriesIndex
			    if (params.componentType === 'markPoint') {
			        // 点击到了 markPoint 上
			        if (params.seriesIndex == 0) {
			            // 点击到了 index 为 5 的 series 的 markPoint 上。
			        	alert('点击到了 index 为 1 的 series 的 markPoint 上,改线条描述的是闵行信息！');
			        }
			    }
			    else if (params.componentType === 'series') {
			        if (params.seriesType === 'graph') {
			            if (params.dataType === 'edge') {
			                // 点击到了 graph 的 edge（边）上。
			            }
			            else {
			                // 点击到了 graph 的 node（节点）上。
			            }
			        }
			    }

			});
	 }
