define('wdDesk',function(require, exports, module){
	
	//require
	var $ = window.jQuery;
	var JSON = window.JSON;
	
	//require Plugin
	var TabObj = require('wdDesk/wdDesk.Tabs');
	var tab = TabObj.Tabs;
	var LayoutObj = require('wdDesk/wdDesk.Layout');
	var MenuObj = require('wdDesk/wdDesk.Menu');
	var TodayObj = require('wdDesk/wdDesk.Today');
	var NocboxObj = require('wdDesk/wdDesk.Nocbox');
	var nocbox = NocboxObj.Nocbox;
	var Search = require('wdDesk/wdDesk.Search');
	var Notification = require('wdDesk/wdDesk.Notification');

	//define search init variaty 
	var hasSearchModuleBeenInit;
	
	window.createTab = function(id, name, code, open_window){
		tab.createTab(id, name, code, open_window);
		$('body').removeClass('showSearch').removeClass('right-mini');
		$('#eastbar,#searchbar').removeClass('on');
		if(wdDesk.isTouchDevice()){
			$('#east').addClass('mobile-east').hide();
			$('#first_menu li div').removeClass('first-menu-item-hover');
			$('#menu-panel,#mask').hide();
		}
	};
	window.selectTab = function(id){
		tab.selectTab(id);
	};
	window.closeTab = function(id){
		id = (typeof(id) != 'string') ? tab.getSelected() : id;
			tab.closeTab(id);
	};
	window.IframeLoaded = function(id){
		tab.IframeLoaded(id);
	};
	
	var nextTabId = 10000;
	window.openURL = function(id, name, url, open_window, width, height, left, top){
	   id = !id ? ('w' + (nextTabId++)) : id;
	   if(open_window != "1") {
		  window.setTimeout(function(){jQuery().addTab(id, name, url, true)}, 1);
	   } else {
		  width = typeof(width) == "undefined" ? 780 : width;
		  height = typeof(height) == "undefined" ? 550 : height;
		  left = typeof(left) == "undefined" ? (screen.availWidth-width)/2 : left;
		  top = typeof(top) == "undefined" ? (screen.availHeight-height)/2-30 : top;
		  window.open(url, id, "height="+height+",width="+width+",status=0,toolbar=no,menubar=yes,location=no,scrollbars=yes,top="+top+",left="+left+",resizable=yes");
	   }
	   jQuery(document).trigger('click');
	}
	$.fn.addTab = function(id, title, url, closable, selected){
		tab.addTab(id, title, url, closable, selected);
		$('body').removeClass('showSearch').removeClass('right-mini');
		$('#eastbar,#searchbar').removeClass('on');
		if(wdDesk.isTouchDevice()){
			$('#east').addClass('mobile-east').hide();
		}
	};
	$.fn.selectTab = function(id){
		tab.selectTab(id);
	};
	$.fn.closeTab = function(id){
		id = (typeof(id) != 'string') ? tab.getSelected() : id;
		tab.closeTab(id);
	};
	$.fn.getSelected = function(){
		return $('#tabs_container').tabs('selected');
	};
	var Iscroll;
	var WdDesk = Backbone.View.extend({
		el: $('body'),
		iscroller: {},
		events: {
			'click a#person_info': 'initPersonInfo',
			'click a#logout': 'initLogout',
			'click a#searchbar': 'initSearch',
			'click a#totaskbar': 'initTaskCenter',
			'click a#eastbar': 'initEast',
			'click ul#first_menu':'touchDevicePanel'
		},
		initialize: function(){
			if(WdDesk._instance){
				return WdDesk._instance;
			}
			_.bindAll(this, 'initPersonInfo', 'initLogout', 'initSearch','initEast', 'initTaskCenter'); 
			var self = this;

			self.EventManager = {};
			_.extend(self.EventManager, Backbone.Events);
			
			self.initIscrollEvent();
			self.initIscrollRefresh();
			self.initLayout();
			self.initMenu();
			self.initTabs();
			self.initPortal();
			self.initTip();
			self.initToday();
			self.initNotify();
			self.initNocbox();
			self.initNotification();
   
			WdDesk._instance = this;

			//for iPad touch
			if(self.isTouchDevice()) {	
				Iscroll = require('../iscroll.js');
				$('#east').hide();
				$('body').addClass('mobile-body');
				$('#center').addClass('mobile-center');
				$('#west').prepend('<div id="menu-panel"><div id="menu-panel-scroller"></div></div>');
				self.EventManager.trigger('iscroller:create','menu-panel');
				
				
				$('#new_noc_list_wrapper').css({"overflow":"hidden"});
				self.EventManager.trigger('iscroller:create',"new_noc_list_wrapper");
				$(window).resize(function(){
					var iscrollh = $('#west').height();
					$('#west-body-wrapper').css({"height":iscrollh,"overflow":"hidden"});
					self.EventManager.trigger('iscroller:refresh','west-body-wrapper');
				});

				$("body").delegate('.first-menu li','click',function(){
					$('#first_menu li div').removeClass('first-menu-item-hover');
					$(this).children('div').addClass('first-menu-item-hover');
					var id = $(this).attr('data-submenu-id');
					if($('#first_menu li div').hasClass('first-menu-item-hover')){
						$('#menu-panel').show();
						var menu_content = $('#'+ id).html();
						$('#menu-panel-scroller').html( "<div>" + menu_content + "<div>");
						self.EventManager.trigger('iscroller:refresh','menu-panel');
						$('#mask').show();
					}else{
						$('#menu-panel,#mask').hide();
					}
				});

				$('body').delegate('#mask','touchend',function(){
					$('#first_menu li div').removeClass('first-menu-item-hover');
					$('#menu-panel,#mask').hide();
				});

				document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
			}
		},
		initIscrollEvent: function(){
			var self = this;
			self.EventManager.on('iscroller:create',function(id){
				var myScroll = new IScroll('#' + id, {
					scrollbars: false,
					mouseWheel: true,
					interactiveScrollbars: true,
					shrinkScrollbars: 'scale',
					preventDefault: false,
					fadeScrollbars: true
				});
				self.iscroller[id] = myScroll;
			});
		},
		initIscrollRefresh: function(){
			var self = this;
			self.EventManager.on('iscroller:refresh',function(id){
				var myScroll = self.iscroller[id];
				myScroll && myScroll.refresh();
			
			});
		},
		initTip: function(){
			var self = this;
			if(self.isTouchDevice()){
				return;
			}else{
				if(jQuery && jQuery.fn.tooltip){
					jQuery('[data-toggle="tooltip"]').tooltip({
						container: 'body'
					});
				}
			}  
		},
		isTouchDevice: function(){
			try{
				document.createEvent("TouchEvent");
				return userAgent.indexOf("mobile") >= 0 || userAgent.indexOf("maxthon") < 0;
			}catch(e){
				return false;
			}
		},
		initLayout: function(){
			var layout = new LayoutObj.Layout({ wdDesk: this });

		},
		initMenu: function(){
			var self = this;
			if (self.isTouchDevice()){
				$('.menu-scroll').remove();
				var menu = new MenuObj.Menu.MenuInit({ wdDesk: this });
				
				var iscrollh = $('#west').height() -40;
				$('#west-body-wrapper').css({"height":iscrollh,"overflow":"hidden"});
				self.EventManager.trigger('iscroller:create','west-body-wrapper');
				this.menu = menu;
				this.EventManager.on('createTab', function(){
					menu.hideActiveMenu();
				});
				$("#first_menu").resize();
			}else{
				var menu = new MenuObj.Menu.MenuInit({ wdDesk: this });
				var menuscroll = new MenuObj.Menu.MenuScroll({ wdDesk: this });
				menu.menuHover();
				this.menu = menu;
				this.EventManager.on('createTab', function(){
					menu.hideActiveMenu();
				});
				$("#first_menu").resize();
			}
		},
		initTabs: function(){
			var self = this;
			tab.init();
			tab._createTab = tab.createTab;
			tab.createTab = function(){
				tab._createTab.apply(tab, arguments);
				self.EventManager.trigger('createTab');
			}
		},
		initPortal: function(){
			for(var i=0; i < portalLoadArray.length; i++){
				tab.addTab(portalLoadArray[i].id==""?"portal_":portalLoadArray[i].id, portalLoadArray[i].title, portalLoadArray[i].url, portalLoadArray[i].closable, (i==0));
			}
		},
		initSearch: function(){
			if(!hasSearchModuleBeenInit) {
				hasSearchModuleBeenInit = Search.init();
			}
			$("#eastbar").removeClass('on');
			var self = this
			if(self.isTouchDevice()){
				$('#east').addClass('mobile-east').hide();
			}
			$("#searchbar").toggleClass('on');
			$('body').removeClass('right-mini').toggleClass('showSearch');
		
		},
		initToday: function(){
			var deskdate = new TodayObj.Today.Deskdate({ wdDesk: this });
			var calendar = new TodayObj.Today.Calendar({ wdDesk: this });
			var reminder = new TodayObj.Today.Reminder({ wdDesk: this });
		},
		//--TODO 修改为项目所需的控制面板
		initPersonInfo: function(){
			tab.addTab('console_','控制面板',static_server+'/workspace/console.jsp','0');
		},
		initLogout: function(){
			window.location = static_server+"/logout.action";
		},
		//--TODO 修改为项目所需的任务中心
		initTaskCenter: function(){
		},
		initEast: function(){
			var self = this;
			$("#searchbar").removeClass('on');
			$("#eastbar").toggleClass('on');
			$('body').removeClass('showSearch');
			if (self.isTouchDevice()){
				$('#east').css('right','0');
				if($("#eastbar").hasClass('on')){
					$('#east').addClass('mobile-east').show();
				}else{
					$('#east').addClass('mobile-east').hide();
				}
			}else{
				$('body').toggleClass('right-mini');
			}
		
		},
		initNocbox: function(){
				nocbox.init({ wdDesk: this });
		},
		initNotify: function(){
			if($('#notify_panel').length > 0)
			{
				$('#overlay').show();
				$('#notify_panel').show();
				$('#notify_panel .btn-ok').click(function(){
					var cookie_name = $(this).attr("cookie_name");
					var cookie_value = $(this).attr("cookie_value");
					document.cookie = cookie_name + "=" + cookie_value; path="/"; expires=1000;
					$('#notify_panel .btn-close').click();
				});
				$('#notify_panel .btn-close').click(function(){
					$('#overlay').hide();
					$('#notify_panel').hide();
				});
				$('#notify_panel .head-close').click(function(){
					$('#notify_panel .btn-close').click();
				});
			}
			
		},
		initNotification: function() {
			Notification.init(this);
		}
	});
	
	WdDesk.getInstance = function(){
		return wdDesk._instance;  
	};
	exports.WdDesk = WdDesk;
	window.WdDesk = WdDesk;
});