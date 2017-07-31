package widgets.Query
{
	import com.esri.ags.symbols.SimpleFillSymbol;
	import com.esri.ags.symbols.SimpleLineSymbol;
	import com.esri.ags.symbols.Symbol;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.mxml.HTTPService;

	public   class FWSymbol
	{
		private static var fwSymbol:FWSymbol=null;
		
		public static var  xgDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  xgGraphicSymbol:SimpleFillSymbol;
		
		public static var  xgSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  xgDKExtentLineSymbos:SimpleFillSymbol;
		
		
		public static var  bgxgGraphicSymbol:SimpleFillSymbol;
		
		public static var  bgxgSelectedGraphicSymbol:SimpleFillSymbol;
	
		
		public static var  cdxgGraphicSymbol:SimpleFillSymbol;
		
		public static var  cdxgSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  fqDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  fqGraphicSymbol:SimpleFillSymbol;
		
		public static var  fqSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  fqDKExtentLineSymbos:SimpleFillSymbol;
		
		
		
		
		public static var  zxDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  zxGraphicSymbol:SimpleFillSymbol;
		
		public static var  zxSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  zxDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  dyDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  dyGraphicSymbol:SimpleFillSymbol;
		
		public static var  dySelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  dyDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  ztDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  ztGraphicSymbol:SimpleFillSymbol;
		
		public static var  ztSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  ztDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  gcDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  gcBelowGraphicSymbol:SimpleFillSymbol;
		
		public static var  gcUpGraphicSymbol:SimpleFillSymbol;
		
		public static var  gcSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  gcDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  xzDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  xzGraphicSymbol:SimpleFillSymbol;
		
		public static var  xzSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  xzDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  ydDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  ydGraphicSymbol:SimpleFillSymbol;
		
		public static var  ydSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  ydDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  wfDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  wfGraphicSymbol:SimpleFillSymbol;
		
		public static var  wfSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  wfDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  qszdDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  qszdGraphicSymbol:SimpleFillSymbol;
		
		public static var  qszdSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  chcgDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  chcgGraphicSymbol:SimpleFillSymbol;
		
		public static var  chcgSelectedGraphicSymbol:SimpleFillSymbol;
		
		
		public static var  cbjhDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  cbjhGraphicSymbol:SimpleFillSymbol;
		
		public static var  cbjhSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  cbjhDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  cbxmDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  cbxmGraphicSymbol:SimpleFillSymbol;
		
		public static var  cbxmSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  cbxmDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  crdkDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  crdkGraphicSymbol:SimpleFillSymbol;
		
		public static var  crdkSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  crdkDKExtentLineSymbos:SimpleFillSymbol;
		
		
		public static var  htjgDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  htjgGraphicSymbol:SimpleFillSymbol;
		
		public static var  htjgSelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  htjgDKExtentLineSymbos:SimpleFillSymbol;
		
		public static var  tyDrawGraphicSymbol:SimpleFillSymbol;
		
		public static var  tyGraphicSymbol:SimpleFillSymbol;
		
		public static var  tySelectedGraphicSymbol:SimpleFillSymbol;
		
		public static var  tyDKExtentLineSymbos:SimpleFillSymbol;
		
		//地基修补测配图
		public static var  djxbcExtentLineSymbos:SimpleFillSymbol;

		public static var  djxbcCDExtentLineSymbos:SimpleFillSymbol;
		
		public static var  djxbcFXExtentLineSymbos:SimpleFillSymbol;
		
		//线图层配色方案
		public static var zjdjzwLineGraphicSimple:SimpleLineSymbol;
		
		public  function FWSymbol()
		{
			var httpservice:HTTPService=new HTTPService();
			httpservice.url="widgets/Query/FWSymbol.xml";
			httpservice.showBusyCursor=true;
			httpservice.addEventListener(FaultEvent.FAULT, onFault);
			httpservice.addEventListener(ResultEvent.RESULT, onResult);
			httpservice.resultFormat="e4x";
			httpservice.send();
			var outlineSym:SimpleLineSymbol;
			function onResult(event:ResultEvent):void
			{
				var data:XML=(XML)(event.result);
				//详细规划
				outlineSym=new SimpleLineSymbol("solid",data.xgSymbols.drawGraphicSymbol.outLine.color,data.xgSymbols.drawGraphicSymbol.outLine.alpha,data.xgSymbols.drawGraphicSymbol.outLine.width);
				xgDrawGraphicSymbol=new SimpleFillSymbol("solid",data.xgSymbols.drawGraphicSymbol.fillSymbol.color,data.xgSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.xgSymbols.graphicSymbols.outLine.color,data.xgSymbols.graphicSymbols.outLine.alpha,data.xgSymbols.graphicSymbols.outLine.width);
				xgGraphicSymbol=new SimpleFillSymbol("solid",data.xgSymbols.graphicSymbols.fillSymbol.color,data.xgSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.xgSymbols.selectedGraphicSymbols.outLine.color,data.xgSymbols.selectedGraphicSymbols.outLine.alpha,data.xgSymbols.selectedGraphicSymbols.outLine.width);
				xgSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.xgSymbols.selectedGraphicSymbols.fillSymbol.color,data.xgSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);

				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.xgSymbols.dkExtentLineSymbos.outLine.color,data.xgSymbols.dkExtentLineSymbos.outLine.alpha,data.xgSymbols.dkExtentLineSymbos.outLine.width);
				xgDKExtentLineSymbos=new SimpleFillSymbol("solid",data.xgSymbols.dkExtentLineSymbos.fillSymbol.color,data.xgSymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				//重叠详规
				outlineSym=new SimpleLineSymbol("solid",data.cdxgSymbols.graphicSymbols.outLine.color,data.cdxgSymbols.graphicSymbols.outLine.alpha,data.cdxgSymbols.graphicSymbols.outLine.width);
				cdxgGraphicSymbol=new SimpleFillSymbol("solid",data.cdxgSymbols.graphicSymbols.fillSymbol.color,data.cdxgSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.cdxgSymbols.selectedGraphicSymbols.outLine.color,data.cdxgSymbols.selectedGraphicSymbols.outLine.alpha,data.cdxgSymbols.selectedGraphicSymbols.outLine.width);
				cdxgSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.cdxgSymbols.selectedGraphicSymbols.fillSymbol.color,data.cdxgSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				//变更详规
				outlineSym=new SimpleLineSymbol("solid",data.bgxgSymbols.graphicSymbols.outLine.color,data.bgxgSymbols.graphicSymbols.outLine.alpha,data.bgxgSymbols.graphicSymbols.outLine.width);
				bgxgGraphicSymbol=new SimpleFillSymbol("solid",data.bgxgSymbols.graphicSymbols.fillSymbol.color,data.bgxgSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.bgxgSymbols.selectedGraphicSymbols.outLine.color,data.bgxgSymbols.selectedGraphicSymbols.outLine.alpha,data.bgxgSymbols.selectedGraphicSymbols.outLine.width);
				bgxgSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.bgxgSymbols.selectedGraphicSymbols.fillSymbol.color,data.bgxgSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				//分区规划
				outlineSym=new SimpleLineSymbol("solid",data.fqSymbols.drawGraphicSymbol.outLine.color,data.fqSymbols.drawGraphicSymbol.outLine.alpha,data.fqSymbols.drawGraphicSymbol.outLine.width);
				fqDrawGraphicSymbol=new SimpleFillSymbol("solid",data.fqSymbols.drawGraphicSymbol.fillSymbol.color,data.fqSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.fqSymbols.graphicSymbols.outLine.color,data.fqSymbols.graphicSymbols.outLine.alpha,data.fqSymbols.graphicSymbols.outLine.width);
				fqGraphicSymbol=new SimpleFillSymbol("solid",data.fqSymbols.graphicSymbols.fillSymbol.color,data.fqSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.fqSymbols.selectedGraphicSymbols.outLine.color,data.fqSymbols.selectedGraphicSymbols.outLine.alpha,data.fqSymbols.selectedGraphicSymbols.outLine.width);
				fqSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.fqSymbols.selectedGraphicSymbols.fillSymbol.color,data.fqSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.fqSymbols.dkExtentLineSymbos.outLine.color,data.fqSymbols.dkExtentLineSymbos.outLine.alpha,data.fqSymbols.dkExtentLineSymbos.outLine.width);
				fqDKExtentLineSymbos=new SimpleFillSymbol("solid",data.fqSymbols.dkExtentLineSymbos.fillSymbol.color,data.fqSymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				
				//专项规划
				outlineSym=new SimpleLineSymbol("solid",data.fqSymbols.drawGraphicSymbol.outLine.color,data.fqSymbols.drawGraphicSymbol.outLine.alpha,data.fqSymbols.drawGraphicSymbol.outLine.width);
				zxDrawGraphicSymbol=new SimpleFillSymbol("solid",data.zxSymbols.drawGraphicSymbol.fillSymbol.color,data.zxSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.zxSymbols.graphicSymbols.outLine.color,data.zxSymbols.graphicSymbols.outLine.alpha,data.zxSymbols.graphicSymbols.outLine.width);
				zxGraphicSymbol=new SimpleFillSymbol("solid",data.zxSymbols.graphicSymbols.fillSymbol.color,data.zxSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.zxSymbols.selectedGraphicSymbols.outLine.color,data.zxSymbols.selectedGraphicSymbols.outLine.alpha,data.zxSymbols.selectedGraphicSymbols.outLine.width);
				zxSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.zxSymbols.selectedGraphicSymbols.fillSymbol.color,data.zxSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.zxSymbols.dkExtentLineSymbos.outLine.color,data.zxSymbols.dkExtentLineSymbos.outLine.alpha,data.zxSymbols.dkExtentLineSymbos.outLine.width);
				zxDKExtentLineSymbos=new SimpleFillSymbol("solid",data.zxSymbols.dkExtentLineSymbos.fillSymbol.color,data.zxSymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				
				
				//单元规划
				outlineSym=new SimpleLineSymbol("solid",data.dySymbols.drawGraphicSymbol.outLine.color,data.dySymbols.drawGraphicSymbol.outLine.alpha,data.dySymbols.drawGraphicSymbol.outLine.width);
				dyDrawGraphicSymbol=new SimpleFillSymbol("solid",data.dySymbols.drawGraphicSymbol.fillSymbol.color,data.dySymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.dySymbols.graphicSymbols.outLine.color,data.dySymbols.graphicSymbols.outLine.alpha,data.dySymbols.graphicSymbols.outLine.width);
				dyGraphicSymbol=new SimpleFillSymbol("solid",data.dySymbols.graphicSymbols.fillSymbol.color,data.dySymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.dySymbols.selectedGraphicSymbols.outLine.color,data.dySymbols.selectedGraphicSymbols.outLine.alpha,data.dySymbols.selectedGraphicSymbols.outLine.width);
				dySelectedGraphicSymbol=new SimpleFillSymbol("solid",data.dySymbols.selectedGraphicSymbols.fillSymbol.color,data.dySymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.dySymbols.dkExtentLineSymbos.outLine.color,data.dySymbols.dkExtentLineSymbos.outLine.alpha,data.dySymbols.dkExtentLineSymbos.outLine.width);
				dyDKExtentLineSymbos=new SimpleFillSymbol("solid",data.dySymbols.dkExtentLineSymbos.fillSymbol.color,data.dySymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				
				//总体规划
				outlineSym=new SimpleLineSymbol("solid",data.ztSymbols.drawGraphicSymbol.outLine.color,data.ztSymbols.drawGraphicSymbol.outLine.alpha,data.ztSymbols.drawGraphicSymbol.outLine.width);
				ztDrawGraphicSymbol=new SimpleFillSymbol("solid",data.ztSymbols.drawGraphicSymbol.fillSymbol.color,data.ztSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.ztSymbols.graphicSymbols.outLine.color,data.ztSymbols.graphicSymbols.outLine.alpha,data.ztSymbols.graphicSymbols.outLine.width);
				ztGraphicSymbol=new SimpleFillSymbol("solid",data.ztSymbols.graphicSymbols.fillSymbol.color,data.ztSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.ztSymbols.selectedGraphicSymbols.outLine.color,data.ztSymbols.selectedGraphicSymbols.outLine.alpha,data.ztSymbols.selectedGraphicSymbols.outLine.width);
				ztSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.ztSymbols.selectedGraphicSymbols.fillSymbol.color,data.ztSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.ztSymbols.dkExtentLineSymbos.outLine.color,data.ztSymbols.dkExtentLineSymbos.outLine.alpha,data.ztSymbols.dkExtentLineSymbos.outLine.width);
				ztDKExtentLineSymbos=new SimpleFillSymbol("solid",data.ztSymbols.dkExtentLineSymbos.fillSymbol.color,data.ztSymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				
				//工程范围
				outlineSym=new SimpleLineSymbol("solid",data.gcSymbols.drawGraphicSymbol.outLine.color,data.gcSymbols.drawGraphicSymbol.outLine.alpha,data.gcSymbols.drawGraphicSymbol.outLine.width);
				gcDrawGraphicSymbol=new SimpleFillSymbol("solid",data.gcSymbols.drawGraphicSymbol.fillSymbol.color,data.gcSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.gcSymbols.BelowGraphicSymbols.outLine.color,data.gcSymbols.BelowGraphicSymbols.outLine.alpha,data.gcSymbols.BelowGraphicSymbols.outLine.width);
				gcBelowGraphicSymbol=new SimpleFillSymbol("solid",data.gcSymbols.BelowGraphicSymbols.fillSymbol.color,data.gcSymbols.BelowGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.gcSymbols.UpGraphicSymbols.outLine.color,data.gcSymbols.UpGraphicSymbols.outLine.alpha,data.gcSymbols.UpGraphicSymbols.outLine.width);
				gcUpGraphicSymbol=new SimpleFillSymbol("solid",data.gcSymbols.UpGraphicSymbols.fillSymbol.color,data.gcSymbols.UpGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				
				outlineSym=new SimpleLineSymbol("solid",data.gcSymbols.selectedGraphicSymbols.outLine.color,data.gcSymbols.selectedGraphicSymbols.outLine.alpha,data.gcSymbols.selectedGraphicSymbols.outLine.width);
				gcSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.gcSymbols.selectedGraphicSymbols.fillSymbol.color,data.gcSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.gcSymbols.dkExtentLineSymbos.outLine.color,data.gcSymbols.dkExtentLineSymbos.outLine.alpha,data.gcSymbols.dkExtentLineSymbos.outLine.width);
				gcDKExtentLineSymbos=new SimpleFillSymbol("solid",data.gcSymbols.dkExtentLineSymbos.fillSymbol.color,data.gcSymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				
				
				//用地范围
				outlineSym=new SimpleLineSymbol("solid",data.ydSymbols.drawGraphicSymbol.outLine.color,data.ydSymbols.drawGraphicSymbol.outLine.alpha,data.ydSymbols.drawGraphicSymbol.outLine.width);
				ydDrawGraphicSymbol=new SimpleFillSymbol("solid",data.ydSymbols.drawGraphicSymbol.fillSymbol.color,data.ydSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.ydSymbols.graphicSymbols.outLine.color,data.ydSymbols.graphicSymbols.outLine.alpha,data.ydSymbols.graphicSymbols.outLine.width);
				ydGraphicSymbol=new SimpleFillSymbol("solid",data.ydSymbols.graphicSymbols.fillSymbol.color,data.ydSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.ydSymbols.selectedGraphicSymbols.outLine.color,data.ydSymbols.selectedGraphicSymbols.outLine.alpha,data.ydSymbols.selectedGraphicSymbols.outLine.width);
				ydSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.ydSymbols.selectedGraphicSymbols.fillSymbol.color,data.ydSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.ydSymbols.dkExtentLineSymbos.outLine.color,data.ydSymbols.dkExtentLineSymbos.outLine.alpha,data.ydSymbols.dkExtentLineSymbos.outLine.width);
				ydDKExtentLineSymbos=new SimpleFillSymbol("solid",data.ydSymbols.dkExtentLineSymbos.fillSymbol.color,data.ydSymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				
				//选址意见
				outlineSym=new SimpleLineSymbol("solid",data.xzSymbols.drawGraphicSymbol.outLine.color,data.xzSymbols.drawGraphicSymbol.outLine.alpha,data.xzSymbols.drawGraphicSymbol.outLine.width);
				xzDrawGraphicSymbol=new SimpleFillSymbol("solid",data.xzSymbols.drawGraphicSymbol.fillSymbol.color,data.xzSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.xzSymbols.graphicSymbols.outLine.color,data.xzSymbols.graphicSymbols.outLine.alpha,data.xzSymbols.graphicSymbols.outLine.width);
				xzGraphicSymbol=new SimpleFillSymbol("solid",data.xzSymbols.graphicSymbols.fillSymbol.color,data.xzSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.xzSymbols.selectedGraphicSymbols.outLine.color,data.xzSymbols.selectedGraphicSymbols.outLine.alpha,data.xzSymbols.selectedGraphicSymbols.outLine.width);
				xzSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.xzSymbols.selectedGraphicSymbols.fillSymbol.color,data.xzSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.xzSymbols.dkExtentLineSymbos.outLine.color,data.xzSymbols.dkExtentLineSymbos.outLine.alpha,data.xzSymbols.dkExtentLineSymbos.outLine.width);
				xzDKExtentLineSymbos=new SimpleFillSymbol("solid",data.xzSymbols.dkExtentLineSymbos.fillSymbol.color,data.xzSymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				
				//违法用地
				outlineSym=new SimpleLineSymbol("solid",data.wfSymbols.drawGraphicSymbol.outLine.color,data.wfSymbols.drawGraphicSymbol.outLine.alpha,data.wfSymbols.drawGraphicSymbol.outLine.width);
				wfDrawGraphicSymbol=new SimpleFillSymbol("solid",data.wfSymbols.drawGraphicSymbol.fillSymbol.color,data.wfSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.wfSymbols.graphicSymbols.outLine.color,data.wfSymbols.graphicSymbols.outLine.alpha,data.wfSymbols.graphicSymbols.outLine.width);
				wfGraphicSymbol=new SimpleFillSymbol("solid",data.wfSymbols.graphicSymbols.fillSymbol.color,data.wfSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.wfSymbols.selectedGraphicSymbols.outLine.color,data.wfSymbols.selectedGraphicSymbols.outLine.alpha,data.wfSymbols.selectedGraphicSymbols.outLine.width);
				wfSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.wfSymbols.selectedGraphicSymbols.fillSymbol.color,data.wfSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.wfSymbols.dkExtentLineSymbos.outLine.color,data.wfSymbols.dkExtentLineSymbos.outLine.alpha,data.wfSymbols.dkExtentLineSymbos.outLine.width);
				wfDKExtentLineSymbos=new SimpleFillSymbol("solid",data.wfSymbols.dkExtentLineSymbos.fillSymbol.color,data.wfSymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				
				//权属宗地
				outlineSym=new SimpleLineSymbol("solid",data.qszdSymbols.drawGraphicSymbol.outLine.color,data.qszdSymbols.drawGraphicSymbol.outLine.alpha,data.qszdSymbols.drawGraphicSymbol.outLine.width);
				qszdDrawGraphicSymbol=new SimpleFillSymbol("solid",data.qszdSymbols.drawGraphicSymbol.fillSymbol.color,data.qszdSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.qszdSymbols.graphicSymbols.outLine.color,data.qszdSymbols.graphicSymbols.outLine.alpha,data.qszdSymbols.graphicSymbols.outLine.width);
				qszdGraphicSymbol=new SimpleFillSymbol("solid",data.qszdSymbols.graphicSymbols.fillSymbol.color,data.qszdSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.qszdSymbols.selectedGraphicSymbols.outLine.color,data.qszdSymbols.selectedGraphicSymbols.outLine.alpha,data.qszdSymbols.selectedGraphicSymbols.outLine.width);
				qszdSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.qszdSymbols.selectedGraphicSymbols.fillSymbol.color,data.qszdSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				//测绘成果
				outlineSym=new SimpleLineSymbol("solid",data.qszdSymbols.drawGraphicSymbol.outLine.color,data.qszdSymbols.drawGraphicSymbol.outLine.alpha,data.qszdSymbols.drawGraphicSymbol.outLine.width);
				chcgDrawGraphicSymbol=new SimpleFillSymbol("solid",data.chcgSymbols.drawGraphicSymbol.fillSymbol.color,data.chcgSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.chcgSymbols.graphicSymbols.outLine.color,data.chcgSymbols.graphicSymbols.outLine.alpha,data.chcgSymbols.graphicSymbols.outLine.width);
				chcgGraphicSymbol=new SimpleFillSymbol("solid",data.chcgSymbols.graphicSymbols.fillSymbol.color,data.chcgSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.chcgSymbols.selectedGraphicSymbols.outLine.color,data.chcgSymbols.selectedGraphicSymbols.outLine.alpha,data.chcgSymbols.selectedGraphicSymbols.outLine.width);
				chcgSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.chcgSymbols.selectedGraphicSymbols.fillSymbol.color,data.chcgSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				//储备计划
				outlineSym=new SimpleLineSymbol("solid",data.qszdSymbols.drawGraphicSymbol.outLine.color,data.qszdSymbols.drawGraphicSymbol.outLine.alpha,data.qszdSymbols.drawGraphicSymbol.outLine.width);
				cbjhDrawGraphicSymbol=new SimpleFillSymbol("solid",data.cbjhSymbols.drawGraphicSymbol.fillSymbol.color,data.cbjhSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.cbjhSymbols.graphicSymbols.outLine.color,data.cbjhSymbols.graphicSymbols.outLine.alpha,data.cbjhSymbols.graphicSymbols.outLine.width);
				cbjhGraphicSymbol=new SimpleFillSymbol("solid",data.cbjhSymbols.graphicSymbols.fillSymbol.color,data.cbjhSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.cbjhSymbols.selectedGraphicSymbols.outLine.color,data.cbjhSymbols.selectedGraphicSymbols.outLine.alpha,data.cbjhSymbols.selectedGraphicSymbols.outLine.width);
				cbjhSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.cbjhSymbols.selectedGraphicSymbols.fillSymbol.color,data.cbjhSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				//储备项目
				outlineSym=new SimpleLineSymbol("solid",data.qszdSymbols.drawGraphicSymbol.outLine.color,data.qszdSymbols.drawGraphicSymbol.outLine.alpha,data.qszdSymbols.drawGraphicSymbol.outLine.width);
				cbxmDrawGraphicSymbol=new SimpleFillSymbol("solid",data.cbxmSymbols.drawGraphicSymbol.fillSymbol.color,data.cbxmSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.cbxmSymbols.graphicSymbols.outLine.color,data.cbxmSymbols.graphicSymbols.outLine.alpha,data.cbxmSymbols.graphicSymbols.outLine.width);
				cbxmGraphicSymbol=new SimpleFillSymbol("solid",data.cbxmSymbols.graphicSymbols.fillSymbol.color,data.cbxmSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.cbxmSymbols.selectedGraphicSymbols.outLine.color,data.cbxmSymbols.selectedGraphicSymbols.outLine.alpha,data.cbxmSymbols.selectedGraphicSymbols.outLine.width);
				cbxmSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.cbxmSymbols.selectedGraphicSymbols.fillSymbol.color,data.cbxmSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				//出让地块
				outlineSym=new SimpleLineSymbol("solid",data.qszdSymbols.drawGraphicSymbol.outLine.color,data.qszdSymbols.drawGraphicSymbol.outLine.alpha,data.qszdSymbols.drawGraphicSymbol.outLine.width);
				crdkDrawGraphicSymbol=new SimpleFillSymbol("solid",data.crdkSymbols.drawGraphicSymbol.fillSymbol.color,data.crdkSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.crdkSymbols.graphicSymbols.outLine.color,data.crdkSymbols.graphicSymbols.outLine.alpha,data.crdkSymbols.graphicSymbols.outLine.width);
				crdkGraphicSymbol=new SimpleFillSymbol("solid",data.crdkSymbols.graphicSymbols.fillSymbol.color,data.crdkSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.crdkSymbols.selectedGraphicSymbols.outLine.color,data.crdkSymbols.selectedGraphicSymbols.outLine.alpha,data.crdkSymbols.selectedGraphicSymbols.outLine.width);
				crdkSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.crdkSymbols.selectedGraphicSymbols.fillSymbol.color,data.crdkSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				//出让合同
				outlineSym=new SimpleLineSymbol("solid",data.qszdSymbols.drawGraphicSymbol.outLine.color,data.qszdSymbols.drawGraphicSymbol.outLine.alpha,data.qszdSymbols.drawGraphicSymbol.outLine.width);
				htjgDrawGraphicSymbol=new SimpleFillSymbol("solid",data.htjgSymbols.drawGraphicSymbol.fillSymbol.color,data.htjgSymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.htjgSymbols.graphicSymbols.outLine.color,data.htjgSymbols.graphicSymbols.outLine.alpha,data.htjgSymbols.graphicSymbols.outLine.width);
				htjgGraphicSymbol=new SimpleFillSymbol("solid",data.htjgSymbols.graphicSymbols.fillSymbol.color,data.htjgSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.htjgSymbols.selectedGraphicSymbols.outLine.color,data.htjgSymbols.selectedGraphicSymbols.outLine.alpha,data.htjgSymbols.selectedGraphicSymbols.outLine.width);
				htjgSelectedGraphicSymbol=new SimpleFillSymbol("solid",data.htjgSymbols.selectedGraphicSymbols.fillSymbol.color,data.htjgSymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				
				//通用查询
				outlineSym=new SimpleLineSymbol("solid",data.tySymbols.drawGraphicSymbol.outLine.color,data.tySymbols.drawGraphicSymbol.outLine.alpha,data.tySymbols.drawGraphicSymbol.outLine.width);
				tyDrawGraphicSymbol=new SimpleFillSymbol("solid",data.tySymbols.drawGraphicSymbol.fillSymbol.color,data.tySymbols.drawGraphicSymbol.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.tySymbols.graphicSymbols.outLine.color,data.tySymbols.graphicSymbols.outLine.alpha,data.tySymbols.graphicSymbols.outLine.width);
				tyGraphicSymbol=new SimpleFillSymbol("solid",data.tySymbols.graphicSymbols.fillSymbol.color,data.tySymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol("solid",data.tySymbols.selectedGraphicSymbols.outLine.color,data.tySymbols.selectedGraphicSymbols.outLine.alpha,data.tySymbols.selectedGraphicSymbols.outLine.width);
				tySelectedGraphicSymbol=new SimpleFillSymbol("solid",data.tySymbols.selectedGraphicSymbols.fillSymbol.color,data.tySymbols.selectedGraphicSymbols.fillSymbol.alpha,outlineSym);
				
				outlineSym=new SimpleLineSymbol(SimpleLineSymbol.STYLE_DASHDOT,data.tySymbols.dkExtentLineSymbos.outLine.color,data.tySymbols.dkExtentLineSymbos.outLine.alpha,data.tySymbols.dkExtentLineSymbos.outLine.width);
				tyDKExtentLineSymbos=new SimpleFillSymbol("solid",data.tySymbols.dkExtentLineSymbos.fillSymbol.color,data.tySymbols.dkExtentLineSymbos.fillSymbol.alpha,outlineSym);
				
				//地籍修补测
				outlineSym=new SimpleLineSymbol("solid",data.djxbcSymbols.drawGraphicSymbol.outLine.color,data.djxbcSymbols.drawGraphicSymbol.outLine.alpha,data.djxbcSymbols.drawGraphicSymbol.outLine.width);
				djxbcExtentLineSymbos=new SimpleFillSymbol("solid",data.djxbcSymbols.graphicSymbols.fillSymbol.color,data.djxbcSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				//地籍修补测-重叠
				outlineSym=new SimpleLineSymbol("solid",data.djxbcCDSymbols.drawGraphicSymbol.outLine.color,data.djxbcCDSymbols.drawGraphicSymbol.outLine.alpha,data.djxbcCDSymbols.drawGraphicSymbol.outLine.width);
				djxbcCDExtentLineSymbos=new SimpleFillSymbol("solid",data.djxbcCDSymbols.graphicSymbols.fillSymbol.color,data.djxbcCDSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				//地籍修补测-缝隙
				outlineSym=new SimpleLineSymbol("solid",data.djxbcFXSymbols.drawGraphicSymbol.outLine.color,data.djxbcFXSymbols.drawGraphicSymbol.outLine.alpha,data.djxbcFXSymbols.drawGraphicSymbol.outLine.width);
				djxbcFXExtentLineSymbos=new SimpleFillSymbol("solid",data.djxbcFXSymbols.graphicSymbols.fillSymbol.color,data.djxbcFXSymbols.graphicSymbols.fillSymbol.alpha,outlineSym);
				
				//宅基地-线图层配色方案
				zjdjzwLineGraphicSimple = new SimpleLineSymbol("solid",data.zjdjzwSymbol.color,data.zjdjzwSymbol.alpha,data.zjdjzwSymbol.width);
			}
			function onFault(event:FaultEvent):void
			{
				Alert.show(event.message.toString());
			}
		}
		public static function getInstance():void
		{
			if(fwSymbol==null)
			{
				fwSymbol=new FWSymbol();
			}
		}	
		
	
	}
}