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