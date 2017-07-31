package com.esri.viewer.managers
{
	import com.esri.viewer.AppEvent;
	import com.esri.viewer.ConfigData;
	import com.esri.viewer.ViewerContainer;
	import com.esri.viewer.utils.UrlUtil;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IOErrorEvent;
	import flash.events.SecurityErrorEvent;
	import flash.external.*;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	import mx.utils.URLUtil;
	
	import org.osmf.utils.URL;
	
	[Event(name="configLoaded", type="com.esri.viewer.AppEvent")]
	
	/**
	 * ConfigManager is used to parse the configuration file and store the information in ConfigData.
	 * The default configuration file is config.xml, but you can specify an alternative configuration file,
	 * e.g. http://myserver/flexwebmaps/index.html?config=config2.xml
	 *
	 * The name of the default configuration file is specified in ViewerContainer.mxml.
	 */
	public class ConfigManager extends EventDispatcher
	{
		private var permissions:String = "";
		private var params:Object={};
		private var userId:String = "";
		private var preload:String ="";
		private var preSearchStr="";
		private var ip:String = "";
		
		public function ConfigManager()
		{
			super();
			
			//make sure the container is properly initialized and then
			//proceed with configuration initialization.
			ViewerContainer.addEventListener(ViewerContainer.CONTAINER_INITIALIZED, init);
		}
		
		//init - start loading the configuration file and parse.
		public function init(event:Event):void
		{
			initializeHandler(event);
			//getPermissions();
		}
		private function getPermissions():void
		{
			//跳过登录验证，直接赋予权限。
			permissions="Ab,Db,Eb,Ei,Fc,Af,Bb,Ca,Dc,Ef,Fa,Fb,Ga,Ad,Ag," +
				"Ai,Aa,Ba,Bc,Cc,Da,Eh,Gb,Fd,Ah,D,Ed,Eg,Ek,A,B,Be,C,Ej,El," +
				"GIS,Bd,Cb,Cd,Ce,Ea,Ec,ROLE_ADMIN,Fe,Ac,Ae,Aj,CODE_ADMIN,E,F,G,";
					
			if(permissions != null ||permissions != ""){
				
				configLoad();
			}else{
				Alert.show("获取权限失败，请联系管理员！","登录失败");
			}
		}
		
		private var _myService:URLLoader;
		
		private function initializeHandler(event:Event):void {
			_myService = new URLLoader();
			_myService.addEventListener(Event.COMPLETE,myCompleteHandler);
			//_myService.load(new URLRequest("../../../../config.xml"));
			_myService.load(new URLRequest(ViewerContainer.configFile));
			XML.ignoreWhitespace = true;
		}
		
		private function myCompleteHandler(event:Event):void {
			var xml:XML = new XML(_myService.data);
			//myComboBox.dataProvider = xml.children(  );
			ip = xml.child("appconfig").gisconfig.servletRoot;
			getPermissions();
		}	
		//config load
		private function configLoad():void
		{
			var configService:HTTPService = new HTTPService();
			configService.url = UrlUtil.UrlComplete(ViewerContainer.configFile);
			configService.resultFormat = "e4x";
			
			configService.addEventListener(ResultEvent.RESULT, configResult);
			configService.addEventListener(FaultEvent.FAULT, configFault);
			configService.send();
			
			var typeStr:String=params["fName"];
			if(typeStr!=""&&typeStr!=null){
				var data:Object={typeStr:typeStr};
				var sharedata:Object={key:"queryExtent", data:data};
				ViewerContainer.dispatchEvent(new AppEvent(AppEvent.DATA_PUBLISH, sharedata));
			}
		}
		
		//config fault
		private function configFault(event:mx.rpc.events.FaultEvent):void
		{
			// happens if for example the main config file is missing or have crossdomain problem
			
			var sInfo:String = "";
			
			// Missing config file
			if (event.fault.rootCause is IOErrorEvent)
			{
				var ioe:IOErrorEvent = event.fault.rootCause as IOErrorEvent;
				if (ioe.text.indexOf("2032: Stream Error. URL:") > -1)
				{
					sInfo += "Could not find " + ioe.text.substring(32) + "\n\n";
				}
				else
				{
					// some other IOError
					sInfo += event.fault.rootCause + "\n\n";
				}
			}
			
			// config file with crossdomain issue
			if (event.fault.rootCause is SecurityErrorEvent)
			{
				var sec:SecurityErrorEvent = event.fault.rootCause as SecurityErrorEvent;
				if (sec.text.indexOf("Error #2048: ") > -1)
				{
					sInfo += "Possible crossdomain issue:\n" + sec.text + "\n\n";
				}
				else
				{
					// some other Security error
					sInfo += event.fault.rootCause + "\n\n";
				}
			}
			
			if (event.statusCode) // e.g. 404 - Not Found - http://en.wikipedia.org/wiki/List_of_HTTP_status_codes
			{
				sInfo += "http response status: " + event.statusCode + "\n\n";
			}
			
			sInfo += "Fault Code: " + event.fault.faultCode + "\n\n";
			sInfo += "Fault Info: " + event.fault.faultString + "\n\n";
			sInfo += "Fault Details: " + event.fault.faultDetail;
			
			ViewerContainer.showError(sInfo);
		}
		
		private var basemapGroup:String;
		
		//config result
		private function configResult(event:ResultEvent):void
		{
			try
			{
				//parse main configuration file to create config data object
				var configData:ConfigData = new ConfigData();
				var configXML:XML = event.result as XML;
				var i:int;
				var j:int;
				
				//================================================
				//Proxy configuration
				//================================================
				var proxyUrl:String = configXML..httpproxy;
				if (proxyUrl)
				{
					configData.proxyUrl = UrlUtil.UrlComplete(proxyUrl);
				}
				
				//================================================
				//BingKey configuration
				//================================================
				var bingKey:String = configXML.bing.@key;
				if (bingKey)
				{
					configData.bingKey = bingKey;
				}
				
				//================================================
				//App configuration
				//================================================
				var appList:XMLList = configXML.appconfig;
				var extentList:XMLList = configXML.mapextent//增加地图显示范围配置参数
				if (appList && appList.length() > 0)
				{
					configData.appConfig = appList[0];
					configData.extentConfig = extentList[0];
					
				}
				
				//================================================
				//Widget Config configuration
				//================================================
				var widgetconfigList:XMLList = configXML.widgetconfig;
				if (widgetconfigList && widgetconfigList.length() > 0)
				{
					configData.widgetConfig = widgetconfigList[0];
				}
				
				
				//================================================
				//Style configuration
				//================================================
				var styleAlpha:String = configXML.stylealpha;
				if (styleAlpha)
				{
					configData.styleAlpha = Number(styleAlpha);
				}
				
				var styleColors:Array = String(configXML.stylecolors).split(",");
				
				var colorStr:String = "";
				for each (colorStr in styleColors)
				{
					configData.styleColors.push(uint(colorStr));
				}
				
				//================================================
				//user interface
				//================================================
				var configUI:Array = [];
				var value:String = configXML..title;
				var title:Object =
					{
						id: "title",
						value: value
					};
				configUI.push(title);
				
				value = configXML..subtitle;
				var subtitle:Object =
					{
						id: "subtitle",
						value: value
					};
				configUI.push(subtitle);
				
				value = configXML..logo;
				var logo:Object =
					{
						id: "logo",
						value: value
					}
				configUI.push(logo);
				
				value = configXML..widgetlayout;
				var widgetlayout:Object =
					{
						id: "widgetlayout",
						value: value
					};
				configUI.push(widgetlayout);
				
				value = configXML..splashpage.@url;
				if (value)
				{
					var splashconfig:String = configXML..splashpage.@config;
					var splashtitle:String = configXML..splashpage.@label;
					var splashpage:Object =
						{
							id: "splashpage",
							value: value,
							config: splashconfig,
							title: splashtitle
						};
					configUI.push(splashpage);
				}
				
				var wleft:String = configXML.widgetcontainer.@left;
				var wright:String = configXML.widgetcontainer.@right;
				var wtop:String = configXML.widgetcontainer.@top;
				var wbottom:String = configXML.widgetcontainer.@bottom;
				var wlayout:String = configXML.widgetcontainer.@layout;
				if (!wlayout)
				{
					wlayout = "horizontal";
				}
				
				if (wleft || wright || wtop || wbottom || wlayout)
				{
					var widgetContainer:Object =
						{
							id: "widgetcontainer",
							left: wleft,
							right: wright,
							top: wtop,
							bottom: wbottom,
							layout: wlayout
						};
					configUI.push(widgetContainer);
				}
				
				configData.viewerUI = configUI;
				
				
				//================================================
				//controls
				//================================================
				var configControls:Array = [];
				var controlList:XMLList = configXML.widget;
				var controlIdWeight:Number = 1000;
				for (i = 0; i < controlList.length(); i++)
				{
					var controlIcon:String = controlList[i].@icon;
					var controlLabel:String = controlList[i].@label;
					var controlLeft:String = controlList[i].@left;
					var controlRight:String = controlList[i].@right;
					var controlTop:String = controlList[i].@top;
					var controlBottom:String = controlList[i].@bottom;
					var controlVisible:String = controlList[i].@visible;
					var controlConfig:String = controlList[i].@config;
					if(controlConfig){
						controlConfig = UrlUtil.UrlComplete(controlConfig);
					}
					
					var controlUrl:String = controlList[i].@url;
					if(controlUrl){
						controlUrl = UrlUtil.UrlComplete(controlUrl);
					}
					
					var control:Object =
						{
							id: controlIdWeight + i,
								icon: controlIcon,
								label: controlLabel,
								left: controlLeft,
								right: controlRight,
								top: controlTop,
								bottom: controlBottom,
								visible: controlVisible,
								config: controlConfig,
								url: controlUrl
						};
					configControls.push(control);
				}
				configData.controls = configControls;
				
				
				//=================================================
				//map
				//================================================
				var mapAttrs:Array = [];
				var initialExtent:String = configXML.map.@initialextent;
				var fullExtent:String = configXML.map.@fullextent;
				if (initialExtent)
				{
					var iExt:Object =
						{
							id: "initial",
							extent: initialExtent
						}
					mapAttrs.push(iExt);
				}
				if (fullExtent)
				{
					var fExt:Object =
						{
							id: "full",
							extent: fullExtent
						}
					mapAttrs.push(fExt);
				}
				
				var mapLeft:Number;
				var mapRight:Number;
				var mapTop:Number;
				var mapBottom:Number;
				if (configXML.map.@left)
				{
					mapLeft = Number(configXML.map.@left);
				}
				if (configXML.map.@right)
				{
					mapRight = Number(configXML.map.@right);
				}
				if (configXML.map.@top)
				{
					mapTop = Number(configXML.map.@top);
				}
				if (configXML.map.@bottom)
				{
					mapBottom = Number(configXML.map.@bottom);
				}
				
				var size:Object =
					{
						id: "map",
						left: mapLeft,
						right: mapRight,
						top: mapTop,
						bottom: mapBottom
					};
				mapAttrs.push(size);
				
				var wkid:Number;
				var wkt:String
				if (configXML.map.@wkid)
				{
					wkid = Number(configXML.map.@wkid);
				}
				if (configXML.map.@wkt)
				{
					wkt = configXML.map.@wkt;
				}
				var ref:Object =
					{
						id: "spatialref",
						wkid: wkid,
						wkt: wkt
					};
				mapAttrs.push(ref);
				var zoomSliderVisible:Boolean = configXML.map.@zoomslidervisible == "true";
				
				var zoomSliderVisibility:Object =
					{
						id: "zoomSlider",
						zoomSliderVisible: zoomSliderVisible
					};
				mapAttrs.push(zoomSliderVisibility);
				
				configData.mapAttrs = mapAttrs;
				
				var scaleBarVisible:Boolean = configXML.map.@scaleBarVisible == "true";
				
				var scaleBarVisibility:Object =
					{
						id: "scaleBar",
						scaleBarVisible: scaleBarVisible
					};
				mapAttrs.push(scaleBarVisibility);
				
				configData.mapAttrs = mapAttrs;
				
				
				
				//================================================
				//map:basemaps
				//================================================
				var configMap:Array = [];
				var maplayerList:XMLList = configXML.map.basemaps.mapservice;
				
				if (maplayerList.length() < 1)
				{
					maplayerList = configXML.map.basemaps.layer;
				}
				
				basemapGroup = configXML.map.basemaps.@group;
				for (i = 0; i < maplayerList.length(); i++)
				{
					configMap.push(getLayerObject(maplayerList[i], i, false, bingKey));
				}
				configData.basemaps = configMap;
				
				
				//================================================
				//map:operationalLayers
				//================================================
				var configOpLayers:Array = [];
				var opLayerList:XMLList = configXML.map.operationallayers.layer;
				for (i = 0; i < opLayerList.length(); i++)
				{
					configOpLayers.push(getLayerObject(opLayerList[i], i, true, bingKey));
				}
				configData.opLayers = configOpLayers;
				
				
				//=================================================
				//widgets
				//================================================
				var configWidgets:Array = [];
				var widgetContainerList:XMLList = configXML.widgetcontainer;
				var widgetId:Number = 0;
				for (i = 0; i < widgetContainerList.children().length(); i++)
				{
					var xmlObject:XML = widgetContainerList.children()[i];
					if (xmlObject.name() == "widgetgroup")
					{
						var widgetGroupList:XMLList = XMLList(xmlObject);
						createWidgets(widgetGroupList.widget, true, widgetGroupList.widget.length(), widgetGroupList.@label, widgetGroupList.@icon,widgetGroupList.@labelIcon,widgetGroupList.@overlabelIcon);
					}
					else
					{
						var widgetList:XMLList = XMLList(xmlObject);
						createWidgets(widgetList, false);
					}
				}
				
				function createWidgets(widgetList:XMLList, grouped:Boolean, groupLength:Number = 0, groupLabel:String = null, groupIcon:String = null,groupLabelIcon:String= null,overlabelIcon:String=null):void
				{
					if(groupIcon){
						groupIcon = UrlUtil.UrlComplete(groupIcon);
					}
					if(groupLabelIcon){
						groupLabelIcon=UrlUtil.UrlComplete(groupLabelIcon);
					}
					if(overlabelIcon){
						overlabelIcon=UrlUtil.UrlComplete(overlabelIcon);
					}
					for (var p:int = 0; p < widgetList.length(); p++)
					{
						// if grouped
						var wGrouped:Boolean = grouped;
						var wGroupLength:Number = groupLength;
						var wGroupIcon:String = groupIcon;
						var wGroupLabelIcon:String=groupLabelIcon;
						var wGroupLabel:String = groupLabel;
						var wLabel:String = widgetList[p].@label;
						var wIcon:String = widgetList[p].@icon;
						if(wIcon){
							wIcon = UrlUtil.UrlComplete(wIcon);
						}
						
						var wConfig:String = widgetList[p].@config;
						if(wConfig){
							wConfig = UrlUtil.UrlComplete(wConfig);
						}
						var wPreload:String = widgetList[p].@preload;
						var wUrl:String = widgetList[p].@url;
						if(wUrl){
							wUrl = UrlUtil.UrlComplete(wUrl);
						}
						var wx:String = widgetList[p].@x;
						var wy:String = widgetList[p].@y;
						var wLeft:String = widgetList[p].@left;
						var wTop:String = widgetList[p].@top;
						var wRight:String = widgetList[p].@right;
						var wBottom:String = widgetList[p].@bottom;
						var wPermission:String = widgetList[p].@permission + ",";
						
						if (!wGroupIcon){
							wGroupIcon = ViewerContainer.DEFAULT_WIDGET_GROUP_ICON;
						}
						
						if (!wIcon){
							wIcon = ViewerContainer.DEFAULT_WIDGET_ICON;}
						
						var widget:Object =
							{
								id: widgetId,
								grouped: wGrouped,
								groupLength: wGroupLength,
								groupIcon: wGroupIcon,
								groupLabelIcon:wGroupLabelIcon,
								groupOverLabelIcon:overlabelIcon,
								groupLabel: wGroupLabel,
								label: wLabel,
								icon: wIcon,
								config: wConfig,
								preload: wPreload,
								x: wx,
								y: wy,
								left: wLeft,
								top: wTop,
								right: wRight,
								bottom: wBottom,
								url: wUrl
							};
						if(permissions.indexOf(wPermission) != -1){
							if(preload.indexOf(wPermission)!= -1){
								widget.preload="open";
							}
							configWidgets.push(widget);
							widgetId++;
						}
					}
				}
				configData.widgets = configWidgets;
				
				
				//=================================================
				//widgetContainers
				//   [] ={container, widgets}
				//================================================
				
				var wContainers:XMLList = configXML.widgetcontainer;
				var configWContainers:Array = [];
				for (i = 0; i < wContainers.length(); i++)
				{
					//get container parameters
					var wcLeft:String = wContainers[i].@left;
					var wcRight:String = wContainers[i].@right;
					var wcTop:String = wContainers[i].@top;
					var wcBottom:String = wContainers[i].@bottom;
					var wcLayout:String = wContainers[i].@layout;
					var wcUrl:String = wContainers[i].@url;
					
					if(wcUrl){
						wcUrl = UrlUtil.UrlComplete(wcUrl);
					}
					
					if (!wcLayout)
					{
						wcLayout = ViewerContainer.DEFAULT_WIDGET_LAYOUT;
					}
					
					if (!wcUrl)
					{
						wcUrl = ViewerContainer.DEFAULT_WIDGET_CONTAINER_WIDGET;
					}
					
					var wgContainer:Object =
						{
							id: i,
							left: wcLeft,
							right: wcRight,
							top: wcTop,
							bottom: wcBottom,
							layout: wcLayout,
							url: wcUrl,
							obj: null
						};
					
					
					//get widgets for this container
					var contWidgets:Array = [];
					var wid:uint = 0;
					for (var n:int = 0; n < wContainers[i].children().length(); n++)
					{
						var xmlObj:XML = wContainers[i].children()[n];
						if (xmlObj.name() == "widgetgroup")
						{
							var widgetGrpList:XMLList = XMLList(xmlObj);
							getWidgetList(widgetGrpList.widget, true, widgetGrpList.widget.length(), widgetGrpList.@label, widgetGrpList.@icon);
						}
						else
						{
							var wdgtList:XMLList = XMLList(xmlObj);
							getWidgetList(wdgtList, false);
						}
					}
					
					function getWidgetList(wgList:XMLList, grouped:Boolean, groupLength:Number = 0, groupLabel:String = null, groupIcon:String = null):void
					{
						if(groupIcon){
							groupIcon = UrlUtil.UrlComplete(groupIcon);
						}
						for (j = 0; j < wgList.length(); j++)
						{
							// if grouped
							var wgGrouped:Boolean = grouped;
							var wgGroupLength:Number = groupLength;
							var wgGroupIcon:String = groupIcon;
							var wgGroupLabel:String = groupLabel;
							
							var wgLabel:String = wgList[j].@label;
							var wgIcon:String = wgList[j].@icon;
							if(wgIcon){
								wgIcon = UrlUtil.UrlComplete(wgIcon);
							}
							var wgConfig:String = wgList[j].@config;
							if(wgConfig){
								wgConfig = UrlUtil.UrlComplete(wgConfig);
							}
							var wgPreload:String = wgList[j].@preload;
							var wgUrl:String = wgList[j].@url;
							if(wgUrl){
								wgUrl = UrlUtil.UrlComplete(wgUrl);
							}
							var wgx:String = wgList[j].@x;
							var wgy:String = wgList[j].@y;
							var wgLeft:String = wgList[j].@left;
							var wgTop:String = wgList[j].@top;
							var wgRight:String = wgList[j].@right;
							var wgBottom:String = wgList[j].@bottom;
							var wgPermission:String = wgList[j].@permission + ",";
							
							if (!wgGroupIcon){
								wgGroupIcon = ViewerContainer.DEFAULT_WIDGET_GROUP_ICON;}
							
							if (!wgIcon){
								wgIcon = ViewerContainer.DEFAULT_WIDGET_ICON;}
							
							var wg:Object =
								{
									id: wid,
									grouped: wgGrouped,
									groupLength: wgGroupLength,
									groupIcon: wgGroupIcon,
									groupLabel: wgGroupLabel,
									label: wgLabel,
									icon: wgIcon,
									config: wgConfig,
									preload: wgPreload,
									x: wgx,
									y: wgy,
									left: wgLeft,
									right: wgRight,
									top: wgTop,
									bottom: wgBottom,
									url: wgUrl
								};
							if(permissions.indexOf(wgPermission) != -1){
								if(preload.indexOf(wgPermission)!= -1){
									wg.preload="open";
								}
								contWidgets.push(wg);
								
								//indexing
								var windex:Object = { container: i, widget: wid };
								configData.widgetIndex.push(windex);
								wid++;
							}
						}
					}
					
					var container:Object = { container: wgContainer, widgets: contWidgets };
					configWContainers.push(container);
				}
				configData.widgetContainers = configWContainers;
				
				//设置系统登录用户ID
				configData.userId = userId;
				configData.preloadWidget=preload;
				configData.preSearchStr=preSearchStr;
				//================================================
				//announce configuration is complete
				//================================================
				ViewerContainer.dispatchEvent(new AppEvent(AppEvent.CONFIG_LOADED, configData));
			}
			catch (error:Error)
			{
				ViewerContainer.showError(error.message);
			}
		}
		
		private function getLayerObject(obj:Object, num:Number, isOpLayer:Boolean, bingkey:String):Object
		{
			var label:String = isOpLayer ? 'OpLayer ' + num : 'Map ' + num; // default basemap label
			if (obj.@label[0]) // check that label attribute exist
			{
				label = obj.@label; // set basemap label if specified in configuration file
			}
			
			var type:String;
			if (!isOpLayer)
			{
				type = "tiled"; // default basemap type
			}
			if (obj.@type[0]) // check that type attribute exist
			{
				type = obj.@type; // set basemap type if specified in configuration file
			}
			
			var visible:Boolean = obj.@visible == "true";
			
			var alpha:Number = 1.0;
			if (obj.@alpha[0])
			{
				if (!isNaN(parseFloat(obj.@alpha)))
				{
					alpha = parseFloat(obj.@alpha);
				}
			}
			
			var noData:Number;
			if (obj.@nodata[0])
			{
				if (!isNaN(parseFloat(obj.@nodata)))
				{
					noData = parseFloat(obj.@nodata);
				}
			}
			
			var autoRefresh:Number = 0;
			if (obj.@autorefresh[0])
			{
				if (!isNaN(parseInt(obj.@autorefresh)))
				{
					autoRefresh = parseInt(obj.@autorefresh);
				}
			}
			
			var useProxy:Boolean = obj.@useproxy[0] && obj.@useproxy == "true";
			var useAMF:String = obj.@useamf[0] ? obj.@useamf : "";
			var token:String = obj.@token[0] ? obj.@token : "";
			var mode:String = obj.@mode[0] ? obj.@mode : "";
			var icon:String = obj.@icon[0] ? obj.@icon : "";
			var visibleLayers:String = obj.@visiblelayers;
			var url:String = obj.@url;
			
			// ve tiled layer
			var style:String = obj.@style[0] ? obj.@style : "";
			var key:String;
			if (bingkey && bingkey != "")
			{
				key = bingkey;
			}
			else
			{
				key = obj.@key[0] ? obj.@key : "";
			}
			var culture:String = obj.@culture[0] ? obj.@culture : "";
			
			// arcims layer
			var serviceHost:String = obj.@servicehost[0] ? obj.@servicehost : "";
			var serviceName:String = obj.@servicename[0] ? obj.@servicename : "";
			
			// definitionExpression for featurelayer
			var definitionExpression:String = obj.@definitionexpression[0] ? obj.@definitionexpression : "";
			
			var resultObject:Object
			if (isOpLayer)
			{
				var opLayerInfo:String = obj.@info;
				var opLayerInfoConfig:String = obj.@infoconfig;
				
				resultObject =
					{
						id: String(num),
						alpha: alpha,
						autoRefresh: autoRefresh,
						culture: culture,
						definitionExpression: definitionExpression,
						icon: icon,
						infoConfig: opLayerInfoConfig,
						infoUrl: opLayerInfo,
						key: key,
						label: label,
						layer: null,
						mode: mode,
						noData: noData,
						serviceHost: serviceHost,
						serviceName: serviceName,
						style: style,
						token: token,
						type: type,
						url: url,
						useAMF: useAMF,
						useProxy: useProxy,
						visible: visible,
						visibleLayers: visibleLayers
					};
			}
			else
			{
				resultObject =
					{
						id: String(num),
						alpha: alpha,
						autoRefresh: autoRefresh,
						culture: culture,
						definitionExpression: definitionExpression,
						icon: icon,
						key: key,
						label: label,
						mode: mode,
						noData: noData,
						serviceHost: serviceHost,
						serviceName: serviceName,
						style: style,
						token: token,
						type: type,
						url: url,
						useAMF: useAMF,
						useProxy: useProxy,
						visible: visible,
						visibleLayers: visibleLayers,
						group: basemapGroup
					};
			}
			return resultObject;
		}
	}
	
}
