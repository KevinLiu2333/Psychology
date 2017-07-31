package widgets.Query
{
	import com.esri.ags.symbols.LineSymbol;
	import com.esri.ags.symbols.SimpleFillSymbol;
	import com.esri.ags.symbols.SimpleLineSymbol;
	import com.esri.viewer.utils.Hashtable;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;

	public class ZTGHDKSymbol
	{
		private  static var  ZTGHDKSymbolsHT:Hashtable=new Hashtable;
		private static var ztghdkSymbol:ZTGHDKSymbol=null;
		public  function ZTGHDKSymbol()
		{
			var httpservice:HTTPService=new HTTPService();
			httpservice.url="widgets/Query/ZTGHDKSymbol.xml";
			httpservice.showBusyCursor=true;
			httpservice.addEventListener(FaultEvent.FAULT, onFault);
			httpservice.addEventListener(ResultEvent.RESULT, onResult);
			httpservice.resultFormat="e4x";
			httpservice.send();
			function onResult(event:ResultEvent):void
			{
				
				var outlineSym:SimpleLineSymbol;
				var polySym:SimpleFillSymbol;
				var layerRts:XML=(XML)(event.result);
				var layerlist:XMLList=layerRts.symbol;
				if (layerlist.length() > 0)
				{
					for each(var xmlObj:XML in layerlist)
					{
						var code:String=xmlObj.code;
						var name:String=xmlObj.name;
						var outlineColor:uint=xmlObj.outlineColor.toString();
						var fillColor:uint=xmlObj.fillColor.toString();
//						none（无边框线）、 dotted（由点组成的虚线）、 dashed（由短线组成的虚线）、 solid（实线）、 double（双线，双线宽度加上它们之间的空白部分的宽度就等于border-width定义的宽度）、 groove（3D沟槽状的边框）、 ridge（3D脊状的边框）、 inset（3D内嵌边框，颜色较深）、 outset（3D外嵌边框，颜色较浅），
//						注意：如果系统不支持这些边框的属性值，那么“dotted”、“dashed”、“double”、“groove”、“ridge”、“inset”和“outset”都会被“solid”代替。

						outlineSym=new SimpleLineSymbol("solid", outlineColor, 0.5, 1);
						polySym=new SimpleFillSymbol("solid", fillColor, 0.8 , outlineSym);
						var value:Object={name:name,symbol:polySym};
						ZTGHDKSymbolsHT.add(code,value);
					}
				}
			}
			function onFault(event:FaultEvent):void
			{
				Alert.show(event.message.toString());
			}
		}
		
		public static function getInstance():void
		{
			if(ZTGHDKSymbolsHT.size==0)
			{
				ztghdkSymbol=new ZTGHDKSymbol();
			}
		}	
		public static function gethashTable():Hashtable{
			var  SymbolsHT:Hashtable=new Hashtable;
			SymbolsHT=ZTGHDKSymbolsHT;
			return SymbolsHT;
		}
		public static function findSymbolByCode(code:String):Object
		{
				getInstance();
				var obj:Object;
				if(!code)
				{
					code="其他";
					obj=ZTGHDKSymbolsHT.find(code);
				}
				else
				{
					obj=ZTGHDKSymbolsHT.find(code);
					if(!obj)
					{
						code="其他";
						obj=ZTGHDKSymbolsHT.find(code);
					}
				}
				
				return obj;
		}
	}
}