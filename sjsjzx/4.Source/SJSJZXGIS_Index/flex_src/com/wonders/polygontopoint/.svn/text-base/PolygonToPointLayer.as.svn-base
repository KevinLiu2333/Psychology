package com.wonders.polygontopoint
{
	import com.esri.ags.Graphic;
	import com.esri.ags.Map;
	import com.esri.ags.events.ZoomEvent;
	import com.esri.ags.geometry.MapPoint;
	import com.esri.ags.geometry.Polygon;
	import com.esri.ags.layers.GraphicsLayer;
	import com.esri.ags.symbols.PictureMarkerSymbol;
	import com.esri.viewer.ViewerContainer;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.events.FlexEvent;
	import mx.states.OverrideBase;
	import com.esri.viewer.utils.UrlUtil;

	public class PolygonToPointLayer extends GraphicsLayer
	{
		private const ICON_URL:String=UrlUtil.URL_DOMAIN+"assets/images/icons/";
		private var  pictureMarkerSymbol:PictureMarkerSymbol=new PictureMarkerSymbol(ICON_URL+"map_pin.png",48,48);
		private var graphic:Graphic;
		private var polygon:Polygon=new Polygon();
		private var center:MapPoint=new MapPoint();
		public  var layerMap:Map;
		private var mapScale:int;
		public function PolygonToPointLayer(layerMap:Map,mapScale:int=1000000)
		{
			super();
			this.mapScale=mapScale;
			this.layerMap=layerMap;
		}

		//重写Update方法
		override protected function updateLayer():void
		{
			// TODO Auto Generated method stub
			super.updateLayer();
			if(this.layerMap.scale>mapScale){
				for(var i:int=0;i<graphicProvider.length;i++){
					
					graphic=graphicProvider[i];
					if(graphic.geometry is MapPoint){
						
						m=graphicProvider.length;
					}else{
						
						polygon=graphic.geometry as Polygon;
						center=polygon.extent.center;
						graphic.attributes.polygon=polygon;
						graphic.attributes.symbol=graphic.symbol;
						graphic.geometry=center;
						graphic.symbol=pictureMarkerSymbol;
					}
				}
				
			}else{
				for(var m:int=0;m<graphicProvider.length;m++){
					
					graphic=graphicProvider[m];
					if(graphic.geometry is Polygon){
						
						m=graphicProvider.length;
						
					}else{
						
						polygon=graphic.attributes.polygon;
						graphic.geometry=polygon;
						graphic.symbol=graphic.attributes.symbol; 
					}
				}
			}
			this.refresh();
		}
		
	}
}