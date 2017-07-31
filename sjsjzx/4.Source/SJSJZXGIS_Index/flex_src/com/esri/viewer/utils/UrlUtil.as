package com.esri.viewer.utils
{
	import com.adobe.net.URI;
	
	import mx.core.FlexGlobals;

	public final class UrlUtil
	{
		private static var domain:String;
		
		public function UrlUtil()
		{
			
		}
		public static function get URL_DOMAIN():String{
			if(!domain){
				var url:String = FlexGlobals.topLevelApplication.url;
				var _URL:URI = new URI(url);
				var path:String = _URL.path;
				domain = url.substr(0, url.indexOf(path))+ path.substr(0, path.lastIndexOf("/")+ 1);
			}
			return domain;
		}
		
		public static function UrlComplete(url:String):String{
			var str:String = url;
			if(str && str.length > 0){
				if(str.substr(0, 7).toLowerCase() != "http://" && str.substr(0, 8).toLowerCase() != "https://" && str.substr(0, 8).toLowerCase() != "file:///"){
					str = URL_DOMAIN + str;
				}
			}
			return str;
		}
		
	}
}