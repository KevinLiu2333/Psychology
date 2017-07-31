package com.esri.viewer.utils
{
	import com.esri.ags.Map;
	import com.esri.ags.geometry.Extent;
	import com.esri.ags.layers.GraphicsLayer;
	import com.esri.ags.utils.GraphicUtil;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.effects.Glow;

	public class UtilsClass
	{
		public static function zoomToGraphics(map:Map, graphicsLayer_num:GraphicsLayer):void{
			var graphicProvider:ArrayCollection = graphicsLayer_num.graphicProvider as ArrayCollection;
			var graphicsExtent:Extent = GraphicUtil.getGraphicsExtent(graphicProvider.toArray());
			if(graphicsExtent){
				map.extent = graphicsExtent.expand(1.5);
				if (!map.extent.contains(graphicsExtent)){
					map.level--;
				}
			}
		}
		public static function zoomToGraphicsArrCols(map:Map, graphicProvider:ArrayCollection):void{
			var graphicsExtent:Extent = GraphicUtil.getGraphicsExtent(graphicProvider.toArray());
			if(graphicsExtent){
				map.extent = graphicsExtent.expand(1.5);
				if (!map.extent.contains(graphicsExtent)){
					map.level--;
				}
			}
		}
		public static function sortArrayCollection(column:String,recAC:ArrayCollection):ArrayCollection{
			var m_sort:Sort = new Sort();
			m_sort.fields = [new SortField(column)];
			recAC.sort = m_sort;
			recAC.sort.reverse();
			recAC.refresh();
			return recAC;
		}
		
		public static function sortArrayList(column:String,recAL:ArrayList):ArrayList{
			var recAC:ArrayCollection=new ArrayCollection(recAL.toArray());
			var m_sort:Sort = new Sort();
			m_sort.fields = [new SortField(column)];
			recAC.sort = m_sort;
			recAC.sort.reverse();
			recAC.refresh();
			return new ArrayList(recAC.toArray());
		}
		public static function sortReverseArrayCollection(column:String,recAC:ArrayCollection):ArrayCollection{
			var m_sort:Sort = new Sort();
			m_sort.fields = [new SortField(column)];
			recAC.sort = m_sort;
//			recAC.sort.reverse();
			recAC.refresh();
			return recAC;
		}
		public static function sortReverseArrayList(column:String,recAL:ArrayList):ArrayList{
			var recAC:ArrayCollection=new ArrayCollection(recAL.toArray());
			var m_sort:Sort = new Sort();
			m_sort.fields = [new SortField(column)];
			recAC.sort = m_sort;
			//			recAC.sort.reverse();
			recAC.refresh();
			return new ArrayList(recAC.toArray());
		}
		
		
//		public static var glowEffect:Glow=new Glow();
//		glowEffect.duration=200;
//		glowEffect.alphaTo=1;
//		glowEffect.alphaFrom=0.5;
//		glowEffect.blurXFrom=-10;
//		glowEffect.blurXTo=10;
//		glowEffect.blurYFrom=-10;
//		glowEffect.blurYTo=10;
//		glowEffect.color=0xFD0318;
	}
	
	
}