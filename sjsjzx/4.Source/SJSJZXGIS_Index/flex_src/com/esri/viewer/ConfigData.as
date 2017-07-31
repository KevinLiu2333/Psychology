package com.esri.viewer
	
{

	/**
	 * ConfigData class is used to store configuration information from the config.xml file.
	 */
	import mx.controls.Alert;
	public class ConfigData
	{
	    public var viewerUI:Array;
	    public var controls:Array;
	    public var mapAttrs:Array;
	    public var basemaps:Array;
	    public var opLayers:Array;
	    public var widgetContainers:Array;
	    public var widgets:Array;
	    public var widgetIndex:Array;
	    public var styleAlpha:Number;
	    public var styleColors:Array;
	
	    public var bingKey:String;
	    public var proxyUrl:String;
		
		public var appConfig:XML;
		public var widgetConfig:XML;
		public var extentConfig:XML;
		
		public var userId:String;
		public var preloadWidget:String;
		public var preSearchStr:String;
	    public function ConfigData()
	    {
	        viewerUI = [];
	        controls = [];
	        mapAttrs = [];
	        basemaps = [];
	        opLayers = [];
	        widgets = [];
	        widgetContainers = []; //[i]={container, widgets]
	        widgetIndex = []; //[i]={container, inx}
	        styleAlpha = 0.8;
	        styleColors = [];
			
			userId = "";
			preloadWidget="";
			preSearchStr="";
	    }
		
		public function get widget_Config():XML{
			return widgetConfig;
		}
		
		public function get project_Config():XML{
			return appConfig ? appConfig.projectconfing[0] : null;
		}
		
		public function get gis_Config():XML{
			return appConfig ? appConfig.gisconfig[0] : null;
		}
		
		public function get project_UrlROOT():String{
			return getProjectConfigNodeValue("urlroot");
		}
		public function get project_UrlROOT_SSNYD():String{
			return getProjectConfigNodeValue("ssnydroot");
		}
		public function get servlet_UrlROOT():String{
			return getProjectConfigNodeValue("servletRoot");
		}
		public function get gisdata_UrlROOT():String{
			return getGISConfigNodeValue("mapserverroot");
		}
		public function get extentdata_LEFTMIN():String{
			return getExtentConfigNodeValue("leftmin");
		}
		public function get extentdata_LEFTMAX():String{
			return getExtentConfigNodeValue("leftmax");
		}
		public function get extentdata_RIGHTMIN():String{
			return getExtentConfigNodeValue("rightmin");
		}
		public function get extentdata_RIGHTMAX():String{
			return getExtentConfigNodeValue("rightmax");
		}
		public function getExtentConfigNodeValue(xmlNode:String):String{
			if(extentConfig){
				return extentConfig.child(xmlNode).toString();
			}
			return "";
		}
		public function getProjectConfigNodeValue(xmlNode:String):String{
			if(appConfig && appConfig.projectconfing){
				return appConfig.projectconfing.child(xmlNode).toString();
			}
			return "";
		}
		
		public function getGISConfigNodeValue(xmlNode:String):String{
			return appConfig.gisconfig.child(xmlNode).toString();
			if(appConfig && appConfig.gisconfig){
				return appConfig.gisconfig.child(xmlNode).toString();
			}
			return "";
		}
		public function getWidgetConfigNode(xmlNode:String):XML{
			if(widgetConfig){
				return widgetConfig[xmlNode];
			}
			return null;
			
		}
	}
}
