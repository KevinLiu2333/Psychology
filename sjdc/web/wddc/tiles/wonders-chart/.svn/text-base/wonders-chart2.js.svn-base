
//生成数据图表入口
	$.fn.newEcharts = function(options){
		var divId = this.attr("id");
		var echartId = options.echartId;
		var date = options.date;
		//createMyDateEcharts(divId,echartId,date);
		createMyEcharts(divId,echartId,date);
	};
	function createMyEcharts(divId,echartId,date){
		$.ajax({
	 		type  : "post",
	 		async : "false",
	 		//请求参数
	 		data : {id : echartId,currDate : date},
	 		url : $('#js_ctx').val()+"/suite/chart/getEchartsObj",
	 		success : function(sourceData){
	 			echart = sourceData.echart;
	 			getMyEchartsObj(echart,divId,date);
	 		}
	 	});
	}
	function getMyEchartsObj(echart,divId,data){
		$.ajax({
			type:"get",
			url:"/wddc/fw/ptservices?unitKey=SJ20161447233004873&fwCode=fw00013&id="+echart.fwDataParameter,
			dataType: 'text',
			success : function(result){
			result = eval('('+result+')');
			var echartsData = result;
			initMyChart(echart,echartsData,divId);
			}
		});
	};
	 function initMyChart(echart,echartsData,divId){
		 var myChart = echarts.init(document.getElementById(divId));
		 var option = echart.optionConfig;
		 option = option.trim();
	     option = option.substring(9,(option.length - 1)) ;
	     option = eval("(" + option + ")");
	     var legend = echart.legendToKey;
	     legend = legend.trim();
		 legend = eval("(" + legend + ")");
		 var keys=[];
		 for(var key in legend){
			keys.push(key);
		 }
		 //判断是否有legend
		 var axisNameList = [];
		 for(var key in legend){
			 axisNameList.push(legend[key]);
		 }
	     toChartAxis(option,echartsData,axisNameList,keys);
	     for(i=0;i<keys.length;i++){
	    	toChartData(option,echartsData,i,axisNameList,keys);
	     }
	     console.log(option);
	     myChart.setOption(option);
	 }
	 function toChartAxis(option,echartsData,axisNameList,keys){
		 if(option.series[0].type == "bar" || option.series[0].type =="line"){
			 //新增坐标轴
			 if(option.legend != null && option.legend != ''&& typeof(option.legend) != 'undefined'){
				 option.legend.data = keys;
			 }
			 var axis = [];
			 if(echartsData.dic != null && echartsData.dic != ''&& typeof(echartsData.dic) != 'undefined'){
				 axis = echartsData.dic.dic;
			 }else if(echartsData.custom_json != null && echartsData.custom_json != '' && typeof(echartsData.custom_json.dic) != 'undefined'){
				 var axisDic = echartsData.custom_json.dic;
				 axisDic = eval("(" + axisDic + ")");
				 axis = axisDic;
			 }else{
				 var axisJson = echartsData.DATA.DATA[axisNameList[0]];
				 axisJson.forEach(function(result){
					 axis.push(result.name);
				 });
			 }
			 if(option.xAxis.type == "value" || option.xAxis[0].type == "value" ){
		    		option.yAxis[0].data = axis;
		    		option.yAxis.data = axis;
		    }else if(option.yAxis.type == "value"||option.yAxis[0].type == "value"){
		    		option.xAxis[0].data = axis;
		    		option.xAxis.data = axis;
		    }
		 }else{
			 var axis = [];
			 for(i=0;i<axisNameList.length;i++){
				 var axisJson = echartsData.DATA.DATA[axisNameList[0]];
				 axisJson.forEach(function(result){
					 axis.push(result.name);
				 });
			 }
			 if(option.legend != null && option.legend != ''&& typeof(option.legend) != 'undefined'){
				 option.legend.data = axis;
			 }
		 }
		 return option;
	 }
	 //新增数据
	 //明天新增图表数据
	 function toChartData(option,echartsData,i,axisNameList,keys){
		 if(option.series[0].type == "bar" || option.series[0].type =="line"){
			 var axisJson = echartsData[axisNameList[i]];
			 var axisList = [];
			 var dataList = [];
			 var nameList = [];
			 if(option.xAxis.type == "value" || option.xAxis[0].type == "value" ){
				 axisList = option.yAxis[0].data;
				 axisList = option.yAxis.data;
		    }else if(option.yAxis.type == "value"||option.yAxis[0].type == "value"){
		    	axisList = option.xAxis[0].data;
		    	axisList = option.xAxis.data;
		    }
			for(k=0;k<axisList.length;k++){
				var axisFlag = 0;
				axisJson.forEach(function(result){
					if(result.name == axisList[k]){
						dataList.push(result.value);
						axisFlag = 1;
					}
				});
				if(axisFlag == 0){
					dataList.push('0');
				}
				
			}
			 option.series[i].name = keys[i];
			 option.series[i].data = dataList;
		 }else{
			 var axisJson = echartsData.DATA.DATA[axisNameList[i]];
			 option.series[i].name = keys[i];
	    	 option.series[i].data = axisJson;
		 }
		 return option;
	 }
