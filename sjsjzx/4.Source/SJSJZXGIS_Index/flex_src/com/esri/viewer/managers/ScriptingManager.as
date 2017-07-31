package com.esri.viewer.managers
{
	import com.esri.ags.utils.JSON;
	import com.esri.viewer.AppEvent;
	import com.esri.viewer.ViewerContainer;
	
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.external.ExternalInterface;
	import flash.utils.setTimeout;
	
	import mx.controls.Alert;
	import mx.core.FlexGlobals;
	
	
	public class ScriptingManager extends EventDispatcher
	{
		public function ScriptingManager(target:IEventDispatcher = null)
	    {
	        super(target);
			registerInterface();
	    }
		
		private function registerInterface():void{
			if (ExternalInterface.available){
				
				ExternalInterface.addCallback("clearLayer", _layerClearedHandler);
				ExternalInterface.addCallback("selectExtent", _extentSelectedHandler);
			}
		}
		private function _layerClearedHandler(paramJsonString:String):void
		{
			try
			{	
				var parameter:Object=JSON.decode(paramJsonString);
				var callBackFunctionName:String=parameter.callback;
				
				var data:Object={callBackFunctionName:callBackFunctionName};
				ViewerContainer.dispatchEvent(new AppEvent(AppEvent.LAYER_CLEAR, data));
			}
			catch (error:Error)
			{
				
				
			}
			
		}
		private function _extentSelectedHandler(paramJsonString:String):void
		{
			try
			{
				var parameter:Object=JSON.decode(paramJsonString);
				var graphicType:String=parameter.type;
				var graphicId:String=parameter.misid;
				var tableName:String=parameter.tablename;
				var queryField:String=parameter.queryfield;
				var isLabled:Boolean=parameter.islabled||true;
				var callBackFunctionName:String=parameter.callback;
				var ifRefreshValue:Boolean=parameter.ifRefresh;
				if(parameter.ifRefresh == null){
					ifRefreshValue = true;
				}
				var colorValue:String=parameter.color;
				var data:Object={type:graphicType,id:graphicId,tablename:tableName,callBackFunctionName:callBackFunctionName,queryField:queryField,isLabled:isLabled,ifRefresh:ifRefreshValue,color:colorValue};
				ViewerContainer.dispatchEvent(new AppEvent(AppEvent.EXTENT_SELECTED, data));
			}
			catch (error:Error)
			{
				
			}
		}

	}
}
