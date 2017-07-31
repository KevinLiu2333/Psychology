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