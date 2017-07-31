package com.esri.viewer.utils
{
	import com.adobe.utils.StringUtil;

	public class XMLUtil
	{
		public function XMLUtil()
		{
		}
		
		public static function getByXPath(xml:XML, xpath:String, pathSep:String = "/"):XMLList{
			if(xml && xpath){
				xpath =  StringUtil.trim(xpath);
				var paths:Array = xpath.split(pathSep);
				var xmlList:XMLList = XMLList(xml);
				for(var i:int = 0; i < paths.length && xmlList; i++){
					xmlList = xmlList.child(paths[i]);
				}
				return xmlList;
			}
			return null;
		}
	}
}