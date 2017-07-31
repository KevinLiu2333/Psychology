define('wdDesk/wdDesk.Today',function(require, exports, module){
	var $ = window.jQuery;
	require('backbone');
	var Deskdate = Backbone.View.extend({
		el: $('.dateArea'), 
		events: {
			'click div#date': 'openCal',
			'click div#mdate': 'openCal',
			'click div#time_area': 'openTime'
		},
		initialize: function(){
			_.bindAll(this, 'openCal', 'openTime'); 
		},
		//--TODO
		openCal: function(){
			
		},
		//--TODO
		openTime: function(){
			
		}
	});
	var Calendar = Backbone.View.extend({
		initialize: function(){
			this.render();
		},
		render: function(){
			var now = new Date(); 
			$.ajax({
				url:'//--TODO URL',
				data:{
					starttime: now.getTime()/1000,
					endtime: now.getTime()/1000,
					view: "agendaDay"
				},
				async: true,
				type: 'get',
				success:function(d){
					if(d.length > 0){
						$.each(d,function(k, v){
							$("#calendar-template").tmpl(v).appendTo('#cal_list');					  
						});  
					}
					else{
						$("#caltip").show();
					}
					
				}
			});	 
		}
	});
	var Reminder = Backbone.View.extend({
		initialize: function(){
			this.render();
		},
		render: function(){
			$.ajax({
				url:'//--TODO URL',
				data: "",
				async: true,
				type: 'get',
				success:function(d){
					if(d.length > 0){
						$.each(d,function(k, v){
							$("#reminder-template").tmpl(v).appendTo('#remind_list');
						});  
					}
					else{
						$("#remindtip").show();
					}
					
				}
			});	 
		}
	}); 
	exports.Today = {
		Deskdate: Deskdate,
		Calendar: Calendar,
		Reminder: Reminder
	};
});