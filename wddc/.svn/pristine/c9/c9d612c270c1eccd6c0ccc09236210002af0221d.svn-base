
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
			url:$('#js_ctx').val()+"/fw/ptservices?unitKey=SJ20161447233004873&fwCode=fw00013&id="+echart.fwDataParameter,
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
	     myChart.setOption(option);
	 }
	 function toChartAxis(option,echartsData,axisNameList,keys){
		 if(option.series[0].type == "bar" || option.series[0].type =="line"){
			 //新增坐标轴
			 if(option.legend != null && option.legend != ''&& typeof(option.legend) != 'undefined'){
				 option.legend.data = keys;
			 }
			 console.log(echartsData);
			 var axis = [];
			 if(echartsData.DATA.dic != null && echartsData.DATA.dic != ''&& typeof(echartsData.DATA.dic) != 'undefined'){
				 axis = echartsData.DATA.dic.dic;
			 }else if(echartsData.DATA.custom_json != null && echartsData.DATA.custom_json != '' && typeof(echartsData.DATA.custom_json.dic) != 'undefined'){
				 var axisDic = echartsData.DATA.custom_json.dic;
				 axis = axisDic;
			 }else{
				 var axisJson = echartsData.DATA[axisNameList[0]];
				 axisJson.forEach(function(result){
					 axis.push(result.name);
				 });
			 }
			 if(option.xAxis[0].type == "value" ){
		    		option.yAxis[0].data = axis;
		    }else if(option.yAxis[0].type == "value"){
		    		option.xAxis[0].data = axis;
		    }
			 console.log(option.xAxis[0]);
		 }else{
			 var axis = [];
			 console.log(echartsData);
			 for(i=0;i<axisNameList.length;i++){
				 var axisJson = echartsData.DATA[axisNameList[0]];
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
			 var axisJson = echartsData.DATA[axisNameList[i]];
			 var axisList = [];
			 var dataList = [];
			 var nameList = [];
			 if(option.xAxis[0].type == "value" ){
				 axisList = option.yAxis[0].data;
		    }else if(option.yAxis[0].type == "value"){
		    	axisList = option.xAxis[0].data;
		    }
			for(k=0;k<axisList.length;k++){
				var axisFlag = 0;
				if(axisJson != '当前数据项含有动态字典无法缓存')
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
			 var axisJson = echartsData.DATA[axisNameList[i]];
			 option.series[i].name = keys[i];
	    	 option.series[i].data = axisJson;
		 }
		 return option;
	 }
