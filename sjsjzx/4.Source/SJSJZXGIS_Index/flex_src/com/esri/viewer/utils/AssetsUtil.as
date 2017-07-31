package com.esri.viewer.utils
{
	[Bindbale]public final class AssetsUtil
	{
		[Embed(source='assets/images/title.png')]
		internal var title:Class;
		[Bindbale]
		[Inspectable(type="Boolean", defaultValue=true, enumeration="true,false")]
		public var logoVisible:Boolean = true;
		
		private static var assetsInstance:AssetsUtil;
		internal function AssetsUtil()
		{
		}
		
		public static function get instance():AssetsUtil{
			if(assetsInstance == null){
				assetsInstance = new AssetsUtil();
			}
			return assetsInstance;
		}
		
		public static function getAssets(str:String):Class{
			return instance[str];
		}
	}
}