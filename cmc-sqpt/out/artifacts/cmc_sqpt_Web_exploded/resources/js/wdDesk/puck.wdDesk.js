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
			'click a#uaLogout': 'initUaLogout',
			'click a#searchbar': 'initSearch',
			'click a#totaskbar': 'initTaskCenter',
			'click a#eastbar': 'initEast',
			'click ul#first_menu':'touchDevicePanel'
		},
		initialize: function(){
			if(WdDesk._instance){
				return WdDesk._instance;
			}
			_.bindAll(this, 'initPersonInfo', 'initLogout', 'initUaLogout', 'initSearch','initEast', 'initTaskCenter'); 
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
		initUaLogout: function(){
			window.location = static_server+"/uaLogout.action";
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
/* "Layout.js" */
define('wdDesk/wdDesk.Layout',function(require, exports, module){
	var $ = window.jQuery;
	var Layout = Backbone.View.extend({
		el: 'body',
		events: {
			'click a.west-handle': 'leftHandle',
			'click a.east-handle': 'rightHandle',
			'click #funcmenu_switcher': 'toggleMenu'
		},
		initialize: function(c){
			_.bindAll(this, 'leftHandle', 'rightHandle', 'toggleMenu');
			this.initEastPanel();
			this.initRegPanel();
			this.wdDesk = c.wdDesk;
			//get menu status from localStorage
			if(localStorage.open_menu == 'open-menu') {
				$(this.el).addClass('open-menu');
				$('.funcmenu_switcher').addClass('active');
			} else {
				$(this.el).removeClass('open-menu');
				$('.funcmenu_switcher').removeClass('active');
			}
			if(localStorage.left_mini == 'left-mini') {
				$(this.el).addClass('left-mini');
			} else {
				$(this.el).removeClass('left-mini');
			}
		},
		initRegPanel:function(){
			if( $('#hero_bar').length > 0){
				$('#east,#west,#center').css('bottom','61px');
			}else{
				$('#east,#west,#center').css('bottom','0');
			}
		},
		leftHandle: function(){
			$(this.el).toggleClass('left-mini');
			if(window.localStorage || typeof window.localStorage != 'undefined'){
				if(localStorage.left_mini == '' || localStorage.left_mini == undefined) {
					localStorage.left_mini = 'left-mini';
				} else {
					localStorage.left_mini = '';
				}
			}
		},
		rightHandle: function(){
			$(this.el).toggleClass('right-mini');
			$('#eastbar').toggleClass('on');
		},
		toggleMenu: function(){
			$(this.el).toggleClass('open-menu');
			$('.funcmenu_switcher').toggleClass('active');
			if(window.localStorage || typeof window.localStorage != 'undefined'){
				if(localStorage.open_menu == '' || localStorage.open_menu == undefined) {
					localStorage.open_menu = 'open-menu';
				} else {
					localStorage.open_menu = '';
				}
			}
		},
		initEastPanel: function(){
			var self = this;
			$('.js-inNocbox').click(function(){
				$('#eastbar').addClass('on');
				$('body').addClass('right-mini');
				$('.nav-pill').removeClass('active');
				$('.tab-pane').removeClass('active');
				$('[panelType="msg"]').addClass('active');
				$('.pane-msg').addClass('active');
				$(".msg-tool .btn").removeClass("btn-primary");
				$('[msg-panel="nocbox"]').addClass("btn-primary");
				$(".msg-panel").removeClass("active");
				$("#nocbox").addClass("active");
				self.wdDesk.EventManager.trigger('online:stopcomet');
			});
			$('.nav-pill').click(function(){
				$('.nav-pill').removeClass('active');
				$('.tab-pane').removeClass('active');
				$(this).addClass('active');
				var target = $(this).attr('panelType');
				$('.pane-'+target).addClass('active');
				if(target == "org"){
					//comet online
					self.wdDesk.EventManager.trigger('online:comet');
				}
				else{
					//stop comet
					self.wdDesk.EventManager.trigger('online:stopcomet');
				}
			});	
			//toggle noc and sms
			$("#msg-tool .btn").click(function(){
				$(".msg-tool .btn").removeClass("btn-primary");
				$(this).addClass("btn-primary");
				var target = $(this).attr("msg-panel");
				$(".msg-panel").removeClass("active");
				$("#"+target).addClass("active");
				if(target=="nocbox" && wdDesk.isTouchDevice()){
					wdDesk.EventManager.trigger('iscroller:refresh','new_noc_list_wrapper');
				}
			}); 
		}
	});
	exports.Layout = Layout;
});
/* "Menu.js" */
define('wdDesk/wdDesk.Menu',function(require, exports, module){
	var $ = window.jQuery;
	require('menu-aim');
	require('backbone');
	
	//first Model
	var Menu = Backbone.Model.extend({
		defaults: {
			menuId: null,
			menuText: "",
			iconfont: ""
		}
	});
	//first Collection
	var MenuList = Backbone.Collection.extend({
		model: Menu,
		url: '',
		sync: function(){}
	});
	//second third Model
	var secondMenu = Backbone.Model.extend({
		defaults: {
			parentId: null,
			menuId: null,
			menuText: "",
			expand: null,
			actionType: ""
		}
	});
	//second third Collection
	var secondMenuList = Backbone.Collection.extend({
		model: secondMenu,
		url: '',
		sync: function(){}
	});
	var thirdMenuList = Backbone.Collection.extend({
		model: secondMenu,
		url: '',
		sync: function(){}
	});
	
	var menulist = new MenuList;
	var secondmenulist = new secondMenuList;
	var thirdmenulist = new thirdMenuList;
	
	//first View
	var menuItemView = Backbone.View.extend({
		el: 'ul.first-menu',
		template: $("#menuTmpl").template(),
		initialize: function() {
			_.bindAll(this, 'render');
			this.model.bind('change',this.render);
		},
		render: function() {
			var element = jQuery.tmpl(this.template, this.model.toJSON());
			$(this.el).append(element);
			return this;
		}
	});
	//second View
	var secondMenuView = Backbone.View.extend({
		el: 'ul.second-menu',
		template: $("#secondMenuTmpl").template(),
		initialize: function() {
			_.bindAll(this, 'render');
			this.model.bind('change',this.render);
		},
		render: function() {
			var element = jQuery.tmpl(this.template, this.model.toJSON());
			var menu_id = this.model.toJSON().parentId;
			$('#second-menulist-'+menu_id).append(element);
			return this;
		}
		
	});
	//third View
	var thirdMenuView = Backbone.View.extend({
		el: 'ul.third-menu',
		template: $("#thirdMenuTmpl").template(),
		initialize: function() {
			_.bindAll(this, 'render');
			this.model.bind('change',this.render);
		},
		render: function() {
			var element = jQuery.tmpl(this.template, this.model.toJSON());
			var menu_id = this.model.toJSON().parentId;
			$('#third-menulist-'+menu_id).append(element);
			return this;
		}
	});
	//view apply
	var MenuView = Backbone.View.extend({
		el: '.west-body',
		initialize: function(){
			var self = this;
			_.bindAll(this, 'createMenu', 'menuHover', 'createSecondMenu', 'createThirdMenu', 'showSubmenu', 'hideSubmenu', 'hideAllSubmenu');
			
			menulist.bind('add', self.createMenu);
			
			menulist.fetch();
		   
			secondmenulist.bind('add', self.createSecondMenu);
			secondmenulist.fetch();
			
			thirdmenulist.bind('add', self.createThirdMenu);
			thirdmenulist.fetch();

			//create first
			for(var i = 0; i < first_array.length; i++){
				var json = {};
				var menu_id = first_array[i];
				json["menuId"] = first_array[i].menuId;
				json["menuText"] = first_array[i].menuText;
				json["iconfont"] = module2iconfont[first_array[i].iconfont] || module2iconfont['default'];
				menulist.add(json);
			}
			//create second
			for(var i=0; i < second_array.length; i++){
				var json = {}
				json["parentId"] = second_array[i].parentId;
				json["menuId"] = second_array[i].menuId;
				json["menuText"] = second_array[i].menuText;
				json["expand"] = true;
				secondmenulist.add(json);
			}
			//create third
			for(var i=0; i < third_array.length; i++) {
				var json = {};
				var onclick = "createTab(" + third_array[i].menuId + ",'" + third_array[i].menuText.replace("'", "\'") + "','" + third_array[i].url.replace("'", "\'") + "','0');";
				json["parentId"] = third_array[i].parentId;
				json["menuId"] = third_array[i].menuId;
				json["menuText"] = third_array[i].menuText;
				json["expand"] = false; 
				json["actionType"] = onclick;
				thirdmenulist.add(json);
			}
		},
		//second panel toggle show or hide
		menuHover: function(){
			var self = this;
			var $menu = jQuery(".first-menu");
			$menu.menuAim({
				activate: self.showSubmenu,
				deactivate: self.hideSubmenu,
				exitMenu:self.hideAllSubmenu
			});
			$(".first-menu li a").click(function(e) {
				e.stopPropagation();
			});
			
		},
		//create menu
		createMenu: function(menuitem){
			var view = new menuItemView({model: menuitem});
			$('.first-menu').prepend(view.render().el);
		},
		createSecondMenu: function(secondmenuitem){
			var secondview = new secondMenuView({model: secondmenuitem});
			$('.second-menu').prepend(secondview.render().el);
		},
		createThirdMenu: function(thirdmenuitem){
			var thirdview = new thirdMenuView({model: thirdmenuitem});
			$('.third-menu').prepend(thirdview.render().el);
		},
		//menu show hide
		showSubmenu: function(row){
			var $menu = $(".first-menu"),
				$row = $(row),
				submenuId = $row.data("submenuId"),
				$submenu = $("#" + submenuId),
				width = $menu.outerWidth();
			this.$active = $row;
			$submenu.css({
				display: "block",
				top: -22,
				left: width
			});
			$submenu.animate('width','400');
			$row.find("div.first-menu-item").addClass("first-menu-item-hover");
		},
		hideAllSubmenu: function(){
			$(".second-panel").css("display", "none");
			$("div.first-menu-item").removeClass("first-menu-item-hover");
			return true;
		},
		hideSubmenu: function(row){
			var $row = $(row),
				submenuId = $row.data("submenuId"),
				$submenu = $("#" + submenuId);
			$submenu.css("display", "none");
			$row.find("div.first-menu-item").removeClass("first-menu-item-hover");
		},
		hideActiveMenu: function(){
			if(this.$active){
				this.hideSubmenu(this.$active);	
			}	
		}
		
	});
	var MenuScrollView = Backbone.View.extend({
		el: $('.west'),
		events: {
			'click div.scroll-up': 'scrollUp',
			'click div.scroll-down': 'scrollDown'
		},
		initialize: function() {
			var self = this;
			_.bindAll(this, 'scrollUp', 'scrollDown');
			$(window).resize(function(){self.initMenuHeight();});
			this.initMenuHeight();
			$('#first_menu').mousewheel(function(event, delta) {
				var ul = $('#first_menu');
				if(delta > 0){
					ul.scrollTop(ul.scrollTop()-46);
				}
				else{
					ul.scrollTop(ul.scrollTop()+46);
				}
				return false;
			});
			$(".second-panel").mousewheel(function(e){
				e.stopPropagation();
			});
		},
		initMenuHeight: function(){
			var availheight = $('.west-footer').offset().top-$('#north').height()-$("#funcbar").height();
			var scrollheight = $("#first_menu")[0].scrollHeight;
			if(availheight < scrollheight){
				availheight = availheight-40;//avail height - top bottom scroll height
				$("#first_menu").height(availheight);
				$('.scroll-up').show();
				$('.scroll-down').show();
			}
			else{
				$("#first_menu").height(availheight);
				$('.scroll-up').hide();
				$('.scroll-down').hide();
			}
		},
		scrollUp: function() {
			//4 times height of firstmenuitem'height
			var ul = $('#first_menu');
			ul.animate({'scrollTop':(ul.scrollTop()-144)}, 600);
		},
		scrollDown: function(){
			var ul = $('#first_menu');
			ul.animate({'scrollTop':(ul.scrollTop()+144)}, 600);
		}
	});
	
	exports.Menu = {
		MenuInit: MenuView,
		MenuScroll: MenuScrollView
	};
});
/* "Nocbox.js" */
define('wdDesk/wdDesk.Nocbox',function(require, exports, module){
	var $ = window.jQuery;
	var Nocbox = {
		init: function(c){
			this.wdDesk = c.wdDesk;
			this.loadNoc();
			this.bindEvent();
		},
		bindEvent: function(){
			var self = this;
			self.wdDesk.EventManager.on('loadNoc:load',function() {
				self.loadNoc();
			});

			self.wdDesk.EventManager.on('notify:read',function(item) {
				var $target = $('#new_noc_list li[sms_id="'+item.sms_id+'"]');
				var url = item.url;
				var sms_id = item.sms_id;
				var type_id = item.type_id;
				//remove this
				self.removeNoc($target, sms_id, 0);
				if(url!=""){//open this url
					self.openURL('', item.type_name, url);
				}
			});

			self.wdDesk.EventManager.on('nocbox:markAll',function(ids) {
				self.removeNoc($('.noc_item_cancel'),ids,0);
			})
			
			//click to read detail
			$('#new_noc_list').delegate('li', 'click', function(){
				var url = $(this).attr('url');
				var sms_id = $(this).attr('sms_id');
				var type_id = $(this).attr('type_id');
				//remove this
				self.removeNoc($(this),sms_id, 0);
				if(url!=""){//open this url
					self.openURL('', '', url, '1');
				}
				
			});
			//click and read History
			//--TODO
			$('#check_remind_histroy,#tohistory').click(function(){
				
			});
			
			//click read all
			//--TODO
			$('#ViewAllNoc').click(function(){
				var idstr = self.get_noc_idstr();
				$.ajax({
					type: 'POST',
					url: '',
					data: {'SMS_ID': idstr},
					cache: false,
					success: function(){
						$('#new_noc_list').empty();
						var datanum = self.get_noc_num();
						$("#noc_item_num").html(datanum); 
						$('#nodata_tips').show();
						
						$('.noc-nav-bar').hide();
					}
				});
			});
			//click all detail
			//--TODO
			$('#ViewDetail').click(function(){
				var idstr = firsturl = separator = "";
				var idobj = $('#new_noc_list > .noc_item > .noc_item_data > li');
				var readobj = $('#new_noc_list > div.noc_item > .noc_item_title > .noc_item_read');
				var idstr_all = self.get_noc_idstr();
				idobj.each(function(){
					url = $(this).attr("url");
					sms_id = $(this).attr("sms_id");
					if(url!="" && firsturl==""){
						firsturl = url;
					}
					if(url!=""){
						idstr += separator + $(this).attr("sms_id");
						separator = ",";
					}
				});
				window.open('/module/nav/?SMS_ID_STR='+idstr+'&NAV_MAIN_URL='+encodeURIComponent(firsturl));
				self.removeNoc(readobj,idstr_all, 0); 
			});
			//--TODO
			$('#new_noc_list').delegate('.noc_item_read', 'click', function(){
				var idstr = idstr_all = firsturl = "";
				var separator = ",";
				var type_id = $(this).attr('type_id');
				var idstr_all = self.get_noc_idstr(type_id);
				self.removeNoc($(this),idstr_all, 0);
				var idobj = $("#new_noc_list > .noc_item_" + type_id + " > .noc_item_data > li");
				idobj.each(function(){
					url = $(this).attr("url");
					if(url == "")
					{
						return true;
					}
					sms_id = $(this).attr("sms_id");
					if(url!="" && firsturl==""){
						firsturl = url;
					}
					if(url!=""){
						idstr += separator + $(this).attr("sms_id");
						separator = ",";
					}
				});
				url = '/module/nav/?SMS_ID_STR='+idstr+'&NAV_MAIN_URL='+encodeURIComponent(firsturl);
				if(idstr != "")
				{
					self.openURL('', '', url, '1');
				}
			});
			$('#new_noc_list').delegate('.noc_item_cancel', 'click', function(){
				var type_id = $(this).attr('type_id');
				var idstr_all = self.get_noc_idstr(type_id);
				self.removeNoc($(this),idstr_all, 0);
			});
		},
		//get noc data
		loadNoc: function(flag){
			
			var self = this;
			var isTOS = true;
			flag = typeof(flag) == "undefined" ? "1" : "0";
			$.ajax({
				type: 'GET',
				url: '',
				data: {'FLAG': flag,'isTos': isTOS},
				dataType: "json",
				cache: false,
				success: function(data){
					
					$('#nocbox_tips').hide();
					if(data == null){
						$('#nodata_tips').show();
						$('.noc-nav-bar').hide();
						$('#new_noc_list_wrapper').empty();
					}
					else{
						$("#nodata_tips").hide();
						$('.noc-nav-bar').show();
						self.formatNoc(data);
					}
				}
			});
		},
		//dat to dom
		formatNoc: function(data){
			var totalnum = "",
				  self = this;
			$('#new_noc_list').empty();
			$.each(data,function(key, item){ 
				if(item.type_id == ""){
					return false;
				}
				item.content = decodeURIComponent(item.content);
				item.type_name = decodeURIComponent(item.type_name);
				item.send_time = decodeURIComponent(item.send_time);
				item.from_name = decodeURIComponent(item.from_name);
				
				//noc trigger notification,and this remind and sms'remind from_id means not same
				var tmpl = item.from_id;
				item.from_id = item.uid;
				item.uid = item.from_id;
				
				self.wdDesk.EventManager.trigger('message:create', item);  
				
				if($('#new_noc_list').find('.noc_item_'+item.type_id).size()!=0){
					$('.noc_item_'+item.type_id+' > ul').append($("#nocitem-template").tmpl(item));	
				}else{
					$('#new_noc_list').append($("#noc-template").tmpl(item));
					$('.noc_item_'+item.type_id+' > ul').append($("#nocitem-template").tmpl(item));	
				}
			});
			$('.noc').addClass("on"); 
			var num = self.get_noc_num();
			$("#noc_item_num").html(num);
			
			
		},	
		//get noc count
		get_noc_num: function(){
			var totalnum = '';
			totalnum = $("#new_noc_list > .noc_item > .noc_item_data > li").length;
			return totalnum;	
		},
		//cal unnoc noc
		get_noc_idstr: function(type_id){
			var idstr = '';
			var separator = '';
			if(type_id!="" && typeof(type_id)!=="undefined")
			{
				var idsobj = $("#new_noc_list > .noc_item_" + type_id + " > .noc_item_data > li");
			}else
			{
				var idsobj = $("#new_noc_list > .noc_item > .noc_item_data > li");
			}	
			$.each(idsobj,function(){
				idstr += separator + $(this).attr("sms_id");
				separator = ",";
			});
			return idstr;
		},
		//remove
		removeNoc: function(obj, recvIdStr, del){
			var self = this;
			if(!recvIdStr){ 
				return
			}
			$.ajax({
				type: 'POST',
				url: '',
				data: {'SMS_ID':recvIdStr},
				success: function(data){
					var lis = obj.parents(".noc_item").find("li").size();
					if(recvIdStr.indexOf(",")!='-1'){
						obj.parents(".noc_item").remove()
					}else{
						lis == 1 ? obj.parents(".noc_item").remove() :	obj.remove();
					}
					var num = self.get_noc_num();
					$("#noc_item_num").html(num);
					if(num < 1){
						$('#nodata_tips').show();
						$('.noc-nav-bar').hide();
					}
				}
			});
		},
		openURL: function(id, name, url, open_window, width, height, left, top){
			id = !id ? ('w' + (nextTabId++)) : id;
			if(open_window != "1") {
				window.setTimeout(function(){$().addTab(id, name, url, true)}, 1);
			} else {
				width = typeof(width) == "undefined" ? 780 : width;
				height = typeof(height) == "undefined" ? 550 : height;
				left = typeof(left) == "undefined" ? (screen.availWidth-width)/2 : left;
				top = typeof(top) == "undefined" ? (screen.availHeight-height)/2-30 : top;
				window.open(url, id, "height="+height+",width="+width+",status=0,toolbar=no,menubar=yes,location=no,scrollbars=yes,top="+top+",left="+left+",resizable=yes");
			}
		}
	};
	exports.Nocbox = Nocbox;
});
/* "Notification.js" */
define('wdDesk/wdDesk.Notification',function(require, exports, module){
	var $ = window.jQuery;
	require('miniNotification');
	require('backbone');
	var wdDesk = window.wdDesk;
	//--------------------------------------------define Message'model-----------------------------------------------
	var Msg = Backbone.Model.extend({
		defaults: {
			id: "",//the id of the model
			mid: "",
			name: "",//the name of the message sender
			module: "",//the type of the model
			title: "",
			msg: "",//the content of the model
			num: 1,//the count of the model
			closable: true,//is the model closable
			autoClose: true
		},
		//items: [],//store the items of the msg
		initialize: function(item) {//initialize the model if necessary
			//init code goes here
			this.items = [];
			this.addItem(item);
		},
		addItem: function(item){
			var allKeys = [];
			$.each(this.items,function(index,_item){
				allKeys.push(_item.key);
			})
			if($.inArray(item.key,allKeys) === -1){
				this.items.push(item); 
				this.set({
					title: item.title,
					mid: item.mid,
					msg: item.msg,
					num: this.size(),
					url: item.url
				}); 
			}  
		},
		removeLastItem: function(){
			this.items.pop();
			var last = this.getLastItem(); 
			if(last){
				this.set({
					title: last.title,
					mid: last.mid,
					msg: last.msg,
					num: this.size(),
					url: last.url
				}); 
			}else{
				this.destroy();
			}  
		},
		getLastItem: function(){
			return _.last(this.items);
		},
		size: function(){
			return this.items.length;
		},
		getData: function(){
			return $({}, this.getLastItem(), { num: this.size() });
		},
		sync: function(){
			
		}
	});


	//-------------------------------------------define Message's collection-------------------------------------------
	var MsgList = Backbone.Collection.extend({
		model: Msg,
		url: '',
		addItem: function(item){
			var self = this,
				model = this.findWhere({id: item.id});	
			if(model){
				model.addItem(item);
			}else{
				this.add(item);
				item.autoClose && setTimeout((function(id){
					return function(){
						var item = self.findWhere({id: id});
						item && item.destroy();
						calculateItemPosition();
					}
				})(item.id), 6000);
			}

		},
		removeGroup: function(id){
			var models = this.where({ id: id });
			this.remove(models);return;
			models.each(function(model){
				model.unset();
			});
		},
		clear: function(noagain){
			var self = this;
			this.each(function(model){
				model.destroy();
			});
			//strange bug,when clear, surplus one msg temporary deal like this by jx
			// !noagain && setTimeout(function(){ self.clear(1) }, 100);
		}
	});

	//init one msg list
	var msgs = new MsgList;
	window.msgs = msgs;
	//--------------------------------------------����Message�ĵ���View----------------------------------------------------
	var MsgView = Backbone.View.extend({
		tagName: "li",

		//temp function
		template: $("#item-template").template(),

		//bind delegate click
		events: {
			"click .msg-close": "destroy",
			"click": "clickHandle",
			"click .ignore-this": "destroy",
			"click .ignore-all": "ignoreAllMessage",
			"click .mark-read": "markRead",
			"click .mark-all": "markAll"
		},

		initialize: function() {
			var self = this;
			
			//make sure functions are called in the right scope
			_.bindAll(this, 'render','remove');

			//listen to model changes
			this.model.bind('change',this.render);
			this.model.bind('destroy',this.remove);
//			wdDesk.EventManager.on('msg:read', function(sms){
//				//var targetmodel = msgs.where({module: "sms",id: sms.from_id});
//				//console.log(targetmodel);
//			});

		},

		render: function() {
			//update el with stored template
			var data = this.model.toJSON();
				
			var element = jQuery.tmpl(this.template, data);
			$(this.el).html(element);
			return this;
		},

		//remove element when model is destroyed
		remove: function() {
			$(this.el).remove();
			msgs.ignoreThis && msgs.ignoreThis(this.model);
		},

		//destroy model when "li.msg-wrapper" is clicked
		destroy: function() {
			this.model.destroy();
			calculateItemPosition();
			return false;
		},
		
		clickHandle: function(){
			if(this.model.items.length <= 1) {
				this.destroy();
			}
			msgs.clickCallback && msgs.clickCallback(this.model);
			this.model.removeLastItem();
		},

		ignoreAllMessage: function() {
			msgs.ignoreAll && msgs.ignoreAll(this);
			return false;
		},

		markRead: function(){
			this.model.destroy();
			calculateItemPosition();
			msgs.readThis && msgs.readThis(this.model);
			return false;
		},

		markAll: function(){
			//msgs.ignoreAll && msgs.ignoreAll(this);
			msgs.readAll && msgs.readAll(this);
			return false;
		}
		
	});


	//---------------------------------------define the whole UI of the Plugin-------------------------------------------------------------------
	var ListView = Backbone.View.extend({
		//our app already present in the HTML
		el: $('.msg-list'),


		//at initialization, we bind to the relevant events on the "msgs" collection, when items are added or changed.
		initialize: function() {
			_.bindAll(this, 'addOne', 'addAll');
			msgs.bind('add', this.addOne);
			msgs.bind('refresh', this.addAll);
			msgs.bind('reset',this.removeAll);
		},



		//add a single msg item to the list by creating a view for it and appending its element to the <ul class"msg-list">
		addOne: function(msg) {
			var view = new MsgView({model: msg});
			$('.msg-list').prepend(view.render().el);
			//calculate the item position
			calculateItemPosition();
		},

		//add all items in the msgs collection at once
		addAll: function() {
			msgs.each(this.addOne);
		},

		removeAll: function() {
			$('.msg-list').animate({
				right: -300,
				opacity: 0
			},{
				duration: 500,
				complete: function() {
					$('.msg-list li').remove();
					$(this).css({
						right: 0,
						opacity: 1
					})
				}
			});

		}


	});

	var calculateItemPosition = (function() {
		var timer;
		return function() {
			timer && clearTimeout(timer);
			timer = setTimeout(function () {
				_calculateItemPosition();
			}, 300);
		}
	})();

	function _calculateItemPosition() {
		//count the number of the models in the collection
		var totalHeight = $('.center').height() + parseInt(jQuery('.center').css('bottom')) - 75,
			singleHeight = $('.msg-list .msg-wrapper').outerHeight(true) || 70,
			itemCount = Math.floor(totalHeight/singleHeight),
			elBottom = totalHeight - itemCount*singleHeight,
			el = $('.msg-list');
		if(itemCount >= msgs.length) {
			elBottom = totalHeight - msgs.length * singleHeight;
			el.animate({bottom: elBottom});
			if(msgs.length <= 0) {
				el.animate({bottom: 1200});
			}
		} else {
			el.animate({bottom: (elBottom+5)});
		}
	}

	$(window).resize(calculateItemPosition);
	

	//Alert msg tip
	var Alert = function(msg,type) {
		$('#mini-notification').miniNotification({
			closeButton: true,
			closeButtonText: 'x',
			closeButtonClass:'closeMsg',
			onLoad: function() {
				$('#mini-notification .inner p').text(msg);
				$('#mini-notification').addClass(type);
			}
		});
	};

	//wdDesk EventManager bind event
	function bindEvents(wD) {
		//--------------Alert--------------
		wD.EventManager.on('alert',function(arg) {//bind show this from bottom function
			Alert(arg.content,arg.type);
		});

		//---------System Message----------
		wD.EventManager.on('message:create',function(cfg) {//bind create system msg function
			//console.log('cfg',cfg)
			var arg;
			if(cfg.type_id == 'message'){
				arg = {
					id: cfg.type_id + '-' + cfg.from_id,
					mid: cfg.type_id,
					closable: true,
					title: cfg.from_name,
					msg: $('<div>').html(cfg.content).text(),	
					module: 'message',
					autoClose: false,
					key: cfg.sms_id,
					url: cfg.url,
					data: cfg
				};
			}else{
				arg = {
					id: cfg.type_id + '-' + cfg.from_id,
					mid: cfg.type_id,
					closable: true,
					title: cfg.from_name,
					autoClose: !cfg.url,
					msg: cfg.type_name,
					key: cfg.sms_id,
					url: cfg.url,
					data: cfg
				};
			}
			//received msg don't show again
			if(cfg.receive !== "1"){
				return;
			}

			if(cfg.type_id !== "message" && cfg.remind_flag !== "1"){
				return;
			}
			//arg.autoClose =1;
			msgs.addItem(arg);

		});

		wD.EventManager.on('message:remove',function(arg) {//ignore the single model
			var sms_ids = [];
			var smsIdStr = '';
			$.each(arg.items,function(index,item) {
				sms_ids.push(item.key);
			});
			smsIdStr = sms_ids.join(",");

			$.ajax({
				type: "GET",
				url: "",
				data: {smsIdStr: smsIdStr},
				success: function(status) {
					if(status == "ok") {
						//do something here
					}
				}
			})
		});

		wD.EventManager.on('message:clear',function(arg) {
			//ignore all the models
			var all_msgs_items = [];
			var smsIdStr = '';
			$.each(msgs.models,function(index,_msg) {
				$.each(_msg.items,function(n,item) {
					all_msgs_items.push(item.key);
				})
			});
			smsIdStr = all_msgs_items.join(",");

			$.ajax({
				type: "GET",
				url: "",
				data: {smsIdStr: smsIdStr},
				success: function(status) {
					if(status == "ok") {
						msgs.trigger('reset');
						msgs.reset();
					}
				}
			})
			msgs.trigger('reset');
			msgs.reset();
		});

		wD.EventManager.on('message:read',function(arg) {//mark have read this message	
			var sms_ids = [];
			var smsIdStr = '';
			$.each(arg.items,function(index,item) {
				sms_ids.push(item.key);
			});
			smsIdStr = sms_ids.join(",");

			$.ajax({
				type: "POST",
				url: "",
				data: {'SMS_ID': smsIdStr},
				success: function(status) {
					wD.initNocbox();
				}
			})
		});

		wD.EventManager.on('message:readall',function(arg) {
			//mark all these messages have been read
			var all_msgs_items = [];
			var smsIdStr = '';
			$.each(msgs.models,function(index,_msg) {
				$.each(_msg.items,function(n,item) {
					all_msgs_items.push(item.key);
				})
			});
			smsIdStr = all_msgs_items.join(",");

			wD.EventManager.trigger('nocbox:markAll',smsIdStr);
			msgs.trigger('reset');
			msgs.reset();
		});

		msgs.clickCallback = function(model){
			var eventType = model.get('module') == 'message' ? 'msg:read' : 'notify:read';
			if(eventType == 'msg:read') {
				model.destroy();
				calculateItemPosition();
			}
			wD.EventManager.trigger( eventType, model.getLastItem().data );
		};

		msgs.ignoreThis = function(model) {
			wD.EventManager.trigger('message:remove',model);
		};

		msgs.ignoreAll = function(view) {
			wD.EventManager.trigger('message:clear',view);
		};

		msgs.readThis = function(model) {
			wD.EventManager.trigger('message:read',model);
		};

		msgs.readAll = function(view) {
			wD.EventManager.trigger('message:readall',view);
		};
	}

	module.exports = {
		init: function(wdDesk) {
			var app = new ListView;
			bindEvents(wdDesk);
		}
	};
});
/* "Pulse.js" */
define('wdDesk/wdDesk.Pulse', function(require,exports,module) {
	var $ = window.jQuery;
	exports.pulseFormer = function() {
		$('#progressBar').removeClass('done');
		$({property: 7}).animate({property: 60},{
			duration: 5000,
			step: function() {
				var percentage = Math.round(this.property);
				$('#progressBar').css('width',percentage+'%');
			}
		});
		setTimeout(function() {
			exports.pulseLater();
		},2000);
	};

	exports.pulseLater = function() {
		$({property: 60}).animate({property:100},{
			duration: 500,
			step: function() {
				var percentage = Math.round(this.property);
				$('#progressBar').css('width',percentage+'%');
				if(percentage == 100) {
					$('#progressBar').addClass('done');
				}
			}
		});
	}
});
/* "Search.js" */
define('wdDesk/wdDesk.Search',function(require,exports,module) {
	var $ = window.jQuery;
	require('backbone');
	require('/resources/js/bootstrap/paginator/bootstrap.paginator.min.js');
	
	//定义搜索模块
	var searchModule = {
		$el: $('.search-container'), //取得搜索的外部容器
		paginators: {}, //分页器容器
		templates: []//搜索结果模板容器
	};

	//-------------- 定义搜索模块的初始化函数 -----------------
	searchModule.init = function() {
		//console.log(this,"this is searchModule object.");
		this.initBind(); //初始化基础板块UI及其事件绑定
		this.initTemplates(); //初始化页面模板

		return 'search module has been initialized.';
	};

	//获取并储存页面模板
	searchModule.initTemplates = function() {
		this.templates["menu"] = $('#search-template-menu').template();
		this.templates["workflow"] = $('#search-template-workflow').template();
		this.templates["contacts"] = $('#search-template-contacts').template();
	};

	//设置并显示搜索返回结果条数
	searchModule.setItemsNum = function(type,num) {
		var _counter = $('.search-counter-'+type);
		_counter.text("("+num+")");
		_counter.show();
	};

	//初始化页脚(init Pagination)
	searchModule.initPagination = function(keyword,type,curPage,totalPages) {
		$('#pagination-'+type).bootstrapPaginator({
			currentPage: curPage,
			totalPages: totalPages,
			onPageChanged: function(e,oldPage,newPage){
				
				var self = this;//取得页脚的引用
				$(this).hide();//隐藏页脚，1s后再显示
				setTimeout(function() {
					$(self).show();
				},1000);
				
				$('.search-container').animate({scrollTop: 0},300,function() {
					$('.search-results-'+type+'>li').remove(); //清空上一页内容
					search(keyword,type,newPage); //发送分页搜索(send pagination search request)
				});
				
			}
		});
	};


	//定义基础UI部分的事件绑定函数
	searchModule.initBind = function() {
		var self = this;
			
		//搜人关注
		$(".search-container").delegate(".user-body-follow", "click", function(){
			var $this = $(this),
				uid = $this.parents("li").attr("u_id"),
				userid = $this.parents("li").attr("userid");
			$.get("", {load:"concern",concern_content:"COMMUNITY,",group_id:0,user_id:userid}, function(d){
				if(d == "ok"){
					$this.parents("li").addClass("unconcern");
				}
			});
		});
		//---------搜索框重定位至左上角，并放大搜索输入框--------
		$('#search-input').one('keydown', function(e) {
			$('.search-box').css({
				'margin-top': '15px',
				'margin-left': '0px'
			});
			//$(this).css('width','600px');
		});
		
		if(wdDesk.isTouchDevice()){
			$("#search-results-container").css({"heigth":"100%","overflow":"hidden"});
			wdDesk.EventManager.trigger('iscroller:create','search-results-container');
		}

		//---------提交搜索请求到后台，发送“关键字”和“搜索类型”，搜索类型默认是“user”型-------
		$('#search-btn').click(function(e) {
			//取得关键词和搜索类型，如果未输入关键词则直接返回
			var $value = $('#search-input').val();
			if(!$value || $value.lastIndexOf(' ') == ($value.length-1)) return false;
			var $searchType = $('#search-btn').attr('search-type');

			//重置所有搜索类型下的searched属性为空，当前类型值为searched
			$('.search-results-tabs li').attr('searched','');
			$('.' + $searchType + '-tab').attr('searched','searched');

			$('.search-results-iscroll>ul>li').remove(); //重新提交搜索后，清空上次搜索结果
			// $('.search-results-container>ul>li').remove(); //重新提交搜索后，清空上次搜索结果
			$('.search-counter').hide(); //重新提交搜索后，取消显示tab的条目数

			//重新提交搜索后，清除上次搜索页脚
			if(!$.isEmptyObject(self.paginators)) {
				_.each(self.paginators,function(item) {
					item.destroy();
				});
				self.paginators = {};
			}

			search($value,$searchType); //提交搜索(invoke search function)
			$('.search-results-wrapper').show(); //显示结果列表
			
			
		});

		//--------------为tab绑定点击事件-------------
		$('.search-results-tabs li').click(function(){
			
			//取得关键词和搜索类型
			var $value = $('#search-input').val();
			var $searchType = $(this).attr('search-type');
			//如果当前tab为激活状态，即刻返回
			if($(this).hasClass('active')) {
				return false;
			}
			//显示相应的搜索结果返回列表（ul)
			var $tabName = $(this)[0].className.split('-')[0];
			//获取当前对应的tab类型，如果非点击“在线帮助”，则进行tab切换；如果是点击“在线帮助”则调用跳页
			if($tabName != 'helper') {
				//重新拼接ul类名，并控制tab显隐
				var $resultsTabclassName = '.search-results-' + $tabName;
				$('.search-results-container ul').hide();
				$($resultsTabclassName).show();
			} else {
				return;
			}
			
			//切换tab菜单
			$('.search-results-tabs li').removeClass('active');
			$(this).addClass('active');
			
			//为返回结果容器ul添加动画
			$('.search-results-container ul').removeClass('animated fadeInLeft');
			$('.search-results-'+$tabName).addClass('animated fadeInLeft');

			//重置“搜索”下的请求类型
			$('#search-btn').attr('search-type',$(this).attr('search-type'));

			//如果当前tab的已搜索(searched)属性为空，则提交搜送
			if($(this).attr('searched') == '' && $value != '') {
				search($value,$searchType);
				$(this).attr('searched','searched');//更改当前tab的searched属性为已搜索(searched)
			}
			// 分页组件的显隐
			if($('#pagination-'+$tabName).html()==" "){
				$('#pagination-'+$tabName+' ul').hide();
			}else{
				$('#pagination-'+$tabName+' ul').show();
			}
			wdDesk.EventManager.trigger('iscroller:refresh','search-results-container');
		});


		//---------------- 自动提交搜索请求(设置setTimeOut来减少请求数） ----------------
		var searchTimer;//搜索请求计时器
		$('#search-input').keyup(function(e) {

			searchTimer && clearTimeout(searchTimer);
			searchTimer = setTimeout(function() {
				$('#search-btn').trigger('click');
			},500);

		});

		//纠正workflow板块返回的url地址，并赋值给<a>的href属性
		$('.search-results-workflow').bind('ajaxDataReady',function() {
			$.each($('.search-results-workflow>li'),function(index,item) {
				var url = $(item).attr('url');
				var newUrl = url.slice(8);//去掉general/
				$(item).find('a').attr({
					'href': newUrl,
					'target': '_blank'
				});
			})
		});

		//关闭搜索模态框
		this.$el.find('.search-close-btn').click(function() {
			$('body').removeClass('showSearch');
			$('#searchbar').removeClass('on');
		})

	};

	//定义弹出搜索页函数
	searchModule.open = function() {

	};

	//定义关闭搜索页函数
	searchModule.close = function() {

	};

	//--------------------------------------------------模块内部搜索函数------------------------------------------------
	function search(keyword,searchType,curPage,pageLimit) {
		var self = searchModule; //储存搜索对象的引用
		//如果搜索类型是"菜单(menu)"，则无需向服务器提交请求
		if(searchType == 'menu') {
			var count = 0, //定义搜索返回数
				menus = [];//创建一个存放返回菜单的数组

			for (var menuId in func_array) {
				var func = func_array[menuId],
					func_id = menuId.substr(1);
				//如果一级菜单、有三级菜单的二级菜单、没有权限的菜单则跳过
				if (menuId.substr(0, 1) != 'f' || func[1].substr(0, 1) == '@'/* || (my_func_id_str.indexOf(func_id + ',') < 0 && my_func_id_str.indexOf(',' + func_id + ',') < 0)*/) {
					continue;
				}
				//匹配 菜单名称、名称拼音首字母中的任何一个
				var title = func[0] || "",
					abbr = func[3] || "";
				if (title.toLowerCase().indexOf(keyword) >= 0 || abbr.toLowerCase().indexOf(keyword) >= 0) {
					count++;
					var singleMenu = {};
					singleMenu.menuId = func_id;
					singleMenu.menuTitle = title;
					singleMenu.menuUrl = func[1];
					menus.push(singleMenu);
				}
			}

			//在页面上渲染返回的菜单
			searchRender(searchType,count,menus);

			return;
		}

		//其它类型搜索(user,workflow,contacts,calendar)，则向服务器提交ajax请求
		//定义当前选中页和每页显示条数，默认为1和10(define the chosen page and the number of items per page, default is 1 and 10.)
		var _curPage = curPage || 1,
			_pageLimit = pageLimit || 10;
		$.ajax({
			type: "GET",
			url: "",
			data: {
				curPage: _curPage,
				pageLimit: _pageLimit,
				type: searchType,
				keyword: keyword
			},
			dataType: "json",
			success: function(data) {
				//根据返回数据(data.curPage,data.totalpage)初始化分页组件(init pagination based on the return data.)
				if(!self.paginators[searchType]) {
					if(data.curPage>0 && data.totalpage>1) {
						searchModule.initPagination(keyword,searchType,data.curPage,data.totalpage);
						self.paginators[searchType] = $('#pagination-'+searchType).data('bootstrapPaginator'); //储存当前分页器
						// console.log(self.paginators,"this is the array of paginators.");
					}
				}
				self.$el.animate({height:'85%'},300);//展开搜索框
				
				searchRender(searchType,data.numCount,data.datalist); //根据返回数据(tata.datalist)来渲染模板
				
				if(searchType == 'workflow')
					$('.search-results-workflow').trigger('ajaxDataReady');//广播ajax返回数据可供操作
			},
			error: function(error) {
				console.error('some error happened:' + error.statusText);
			}
		})
	}

	//渲染返回数据
	function searchRender(type,num,data) { 
		var self = searchModule; //储存搜索对象的引用
		self.setItemsNum(type,num);//显示搜索的返回结果数
		
		$.each(data,function(index,item) {
			var element = $.tmpl(self.templates[type],item);
			element.insertBefore('#pagination-'+type);
		});
		// ipad滚动时添加分页的高度
		var iscrollh = $('.search-results-'+type).height();
		if($('#pagination-'+type).html().length==0){
			$('.search-results-'+type).css({"height":iscrollh});
			wdDesk.EventManager.trigger('iscroller:refresh','search-results-container');
			$('.search-results-'+type).css({"height":"auto"});
		}else{
			var	newiscrollh = iscrollh + 75;
			$('.search-results-'+type).css({"height":newiscrollh});
			wdDesk.EventManager.trigger('iscroller:refresh','search-results-container');
			$('.search-results-'+type).css({"height":"auto"});
		}
	}
	//提供模块的外部接口
	module.exports = searchModule;
});
/* "Tabs.js" */
define('wdDesk/wdDesk.Tabs', function(require, exports, module){
	var $ = window.jQuery;
	var pulser = require('wdDesk/wdDesk.Pulse');
	var Tabs = {
		init: function(){
			this.initTabs();
		},
		initTabs: function(){
			var self = this;
			$(window).resize(function(){
				self.resizeLayout();
			});
			self.resizeLayout();
			$('#tabs_container').tabs({
				tabsLeftScroll:'tabs_left_scroll',
				tabsRightScroll:'tabs_right_scroll',
				panelsContainer:'center',
				secondTabsContainer: 'funcbar_left'
			});
			$('#funcbar_left').delegate('.second-tab-item','click',function(){
				var self = $(this);
				var url = self.attr('action');
				var id = self.attr('secondTabId');
				$("#tabs_"+id+"_iframe").attr('src',url);
			});
		},
		resizeLayout: function(){
			var wWidth = (window.document.documentElement.clientWidth || window.document.body.clientWidth || window.innerHeight);
			var width = wWidth - $('#logo').outerWidth() - $('#infobar').outerWidth();
			$('#tabs_container').width(width - $('#tabs_left_scroll').outerWidth() - $('#tabs_right_scroll').outerWidth() - 2);
			$('#taskbar').width(width-2);
			$('#tabs_container').triggerHandler('_resize');
		},
		createTab: function(id, name, code, open_window){
			var self = this;
			jQuery('#funcbar_left > div.second-tabs-container').hide();
			if(id.toString().indexOf('portal_') == 0 || code.indexOf('http://') == 0 || code.indexOf('https://') == 0 || code.indexOf('ftp://') == 0){
				self.openURL(id, name, code, open_window);
				return;
			} else if(code.indexOf('file://') == 0) {
				winexe(name, code.substr(7));
				return;
			}
			var url = code;
			if(url.indexOf(".") < 0 && url.indexOf("?") < 0  && url.indexOf("#") < 0 && url.substring(url.length-1) != "/") {
				url += "/";
			}
			if(open_window == "1"){
				self.openURL(id, name, url, open_window);
				return;
			}
			self.openURL(id, name, url, open_window);
		},
		addTab: function(id, title, url, closable, selected, callback){
			var self = this;
			if(!id) return;
			closable = (typeof(closable) == 'undefined') ? true : closable;
			selected = (typeof(selected) == 'undefined') ? true : selected;

			var height = '100%';
			jQuery('#tabs_container').tabs('add', {
				id: id,
				title: title,
				closable: closable,
				selected: selected,
				style: 'height:' + height + ';',
				content: '<iframe id="tabs_' + id + '_iframe" name="tabs_' + id + '_iframe" allowTransparency= "true"' + (!selected ? (' _src="' + url + '"') : '') + ' src="' + (selected ? url : '') + '"' + (selected ? (' onload="IframeLoaded(\'' + id + '\');"') : '') + ' border="0" frameborder="0" framespacing="0" marginheight="0" marginwidth="0" style="width:100%;height:' + height + ';"></iframe>',
				callback: function() {
					pulser.pulseFormer();
					callback && callback();
				}
			});
		},
		selectTab: function(id){
			$('#tabs_container').tabs('select', id);
		},
		closeTab: function(id){
			$('#tabs_container').tabs('close', id);
		},
		getSelected: function(){
			return $('#tabs_container').tabs('selected');

		},
		isTouchDevice: function(){
			try{
				document.createEvent("TouchEvent");
				return userAgent.indexOf("mobile") >= 0 || userAgent.indexOf("maxthon") < 0;
			}catch(e){
				return false;
			}
		},
		openURL: function(id, name, url, open_window, width, height, left, top){
			var self = this;
			id = !id ? ('w' + (nextTabId++)) : id;
			if(open_window != "1") {
				window.setTimeout(function(){self.addTab(id, name, url, true)}, 1);
			} else {
				width = typeof(width) == "undefined" ? 780 : width;
				height = typeof(height) == "undefined" ? 550 : height;
				left = typeof(left) == "undefined" ? (screen.availWidth-width)/2 : left;
				top = typeof(top) == "undefined" ? (screen.availHeight-height)/2-30 : top;
				window.open(url, id, "height="+height+",width="+width+",status=0,toolbar=no,menubar=yes,location=no,scrollbars=yes,top="+top+",left="+left+",resizable=yes");
			}
		},
		Text2Object: function(data){
			var self = this;
			try{
				var func = new Function("return " + data);
				return func();
			} catch(ex){
				return '<b>' + ex.description + '</b><br /><br />' + self.HTML2Text(data) + '';
			}
		},
		HTML2Text: function(html){
			var div = document.createElement('div');
			div.innerHTML = html;
			return div.innerText;
		},
		IframeLoaded: function(id){
			var iframe = window.frames['tabs_' + id + '_iframe'];
			if(iframe && $('#tabs_link_' + id) && $('#tabs_link_' + id).innerText == ''){
				$('#tabs_link_' + id).innerText = iframe.document.title;
			}
			pulser.pulseLater();
		}
	};
	exports.Tabs = Tabs;
});
/* "Today.js" */
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