package com.wonders.circle
{
	
	import com.esri.ags.Graphic;
	import com.esri.ags.Map;
	import com.esri.ags.events.DrawEvent;
	import com.esri.ags.geometry.MapPoint;
	import com.esri.ags.geometry.Polygon;
//	import com.esri.ags.symbol.*;
//	import com.esri.ags.toolbars.Draw;
	import com.esri.ags.symbols.*;
	import com.esri.ags.tools.DrawTool;
	import flash.events.MouseEvent;
	import mx.controls.Alert;
	import mx.formatters.NumberBaseRoundType;
	import mx.formatters.NumberFormatter;   
	import com.esri.viewer.ConfigData;
	public class CustomDraw extends DrawTool
	{
		 public static const CIRCLE:String = "circle";   
		 
		 public static const POINTCIRCLE:String = "pointCircle"; //点查操作标志
		 
		 public static const CUSTOM_CIRCLE:String = "customcircle";    	 
        
	    //use to set the text symbol to display the radius (as a number) is desired
	    [Bindable]   
	    public var textSymbol : TextSymbol;        
	    //use to set the symbol for the center of the circle as a point is desired
	    [Bindable]
	    public var centerPointSymbol: Symbol;        
	    //set this in the code using this component to specify 
	    //how manu points will be used to create the circle
	    //If not specified, the value is 100.      
	    public var numberOfCirclePoints : int = 100;   
	    //set this to false if displaying the center of the cicle is not desired
	    public var displayCenter:Boolean = true;
	    //set this to false if displaying the radius as a number is not desired
	    public var displayRadius:Boolean = true;    
	    
	    private var outlineSym:SimpleLineSymbol=new SimpleLineSymbol("solid", 0x0008F13, 0.5, 2);

		private var polySym:SimpleFillSymbol=new SimpleFillSymbol("solid", 0x0008F13, 0.1, outlineSym);

	    
	    private var m_center:MapPoint;        
	    private var m_radius:Number;   
	    private var m_graphic : Graphic; 
	    private var m_textGraphic : Graphic;       
	    private var m_circleGeometry : Polygon;  
		private var pointWidth : String;  
		public function CustomDraw(map:Map=null)
		{
			super(map);
		}
		
		//activate cirlce drawing
        override public function activate(drawType:String, enableGraphicsLayerMouseEvents:Boolean = false) : void
        {
        	//if map is not specified
        	if (map == null) {
        		throw new Error("Map is not specified for CustomDraw component!");
        		//return;
        	}
        	if (graphicsLayer == null) {
        		throw new Error("Graphics Layer is not specified for CustomDraw component!");
        		//return;
        	}
        	if (drawType == CIRCLE) {        		
        		map.mapNavigationEnabled = false;
            	map.addEventListener(MouseEvent.MOUSE_DOWN, map_mouseDownHandler);
            	graphicsLayer.mouseEnabled = enableGraphicsLayerMouseEvents;            	
         	}
			else if (drawType.indexOf(POINTCIRCLE)!=-1) { 
				var pairs:Array=drawType.split("&");
				pointWidth = pairs[1].toString();
				map.mapNavigationEnabled = false;
				map.addEventListener(MouseEvent.MOUSE_DOWN, map_pointMouseDownHandler);
				graphicsLayer.mouseEnabled = enableGraphicsLayerMouseEvents;            	
			}else {
        		super.activate(drawType, enableGraphicsLayerMouseEvents);
        	}          	
        } 
        
        override public function deactivate():void {
        	//if map is not specified
        	if (map == null) {
        		throw new Error("Map is not specified for CustomDraw component!");
        		//return;
        	}
        	map.mapNavigationEnabled = true;        	
        	super.deactivate();
        }  
        
        //handle the beginning of the drawing
        private function map_mouseDownHandler(event:MouseEvent) : void
        {     
            map.removeEventListener(MouseEvent.MOUSE_DOWN, map_mouseDownHandler);			
            const mapPoint : MapPoint = map.toMapFromStage( event.stageX, event.stageY );
            //create the polygon for the circle 
            m_circleGeometry = new Polygon();             
            m_center = mapPoint;            
            m_radius = 1; 
            //dispatch the drawStart event to allow users to clear graphics, 
            //or just do whatever needs to be done
            //but do it after the m_center has been defined, because 
            //if done before, and the user clears graphics during the drawStart
            //a bogus center point is defined. 
            this.dispatchEvent(new DrawEvent("drawStart", m_graphic));                              
            m_graphic = new Graphic( m_circleGeometry, polySym );
            graphicsLayer.add( m_graphic );
            
            if (displayRadius) {
	            //create the point for the text showing the radius
	            var textPoint:MapPoint = new MapPoint(m_center.x, m_center.y);
	            if (textSymbol == null) {
	            	textSymbol = new TextSymbol(null,null,0x337014,true,0x337014,true,0xFFFFFF,"above");
	           
        			
        			
	            }
	            m_textGraphic = new Graphic( textPoint, textSymbol);
	            graphicsLayer.add( m_textGraphic);
            }
            
            //create the center graphic if user has provided centerPointSymbol
            if (displayCenter) {
            	var centerGraphic:Graphic = new Graphic(m_center, centerPointSymbol);
            	graphicsLayer.add(centerGraphic);
            }
            
            map.addEventListener(MouseEvent.MOUSE_MOVE, map_mouseMoveHandler);
            map.addEventListener(MouseEvent.MOUSE_UP, map_mouseUpHandler);
        }
		
		//点查操作鼠标按下事件实现
		private function map_pointMouseDownHandler(event:MouseEvent) : void
		{   
			//点击查询后释放鼠标监听
			map.removeEventListener(MouseEvent.MOUSE_DOWN, map_pointMouseDownHandler);
			const mapPoint : MapPoint = map.toMapFromStage( event.stageX, event.stageY );
			//create the polygon for the circle 
			m_circleGeometry = new Polygon();             
			m_center = mapPoint;            
			m_radius = 1; 
			//dispatch the drawStart event to allow users to clear graphics, 
			//or just do whatever needs to be done
			//but do it after the m_center has been defined, because 
			//if done before, and the user clears graphics during the drawStart
			//a bogus center point is defined. 
			this.dispatchEvent(new DrawEvent("drawStart", m_graphic));                              
			m_graphic = new Graphic( m_circleGeometry, polySym );
			graphicsLayer.add( m_graphic );
			
			if (displayRadius) {
				//create the point for the text showing the radius
				var textPoint:MapPoint = new MapPoint(m_center.x, m_center.y);
				if (textSymbol == null) {
					textSymbol = new TextSymbol(null,null,0x337014,true,0x337014,true,0xFFFFFF,"above");
					
					
					
				}
				m_textGraphic = new Graphic( textPoint, textSymbol);
				graphicsLayer.add( m_textGraphic);
			}
			
			//create the center graphic if user has provided centerPointSymbol
			if (displayCenter) {
				var centerGraphic:Graphic = new Graphic(m_center, centerPointSymbol);
				graphicsLayer.add(centerGraphic);
			}
			
			//map.addEventListener(MouseEvent.MOUSE_MOVE, map_mouseMoveHandler);
			//设置半径为500米
			m_radius = Number(pointWidth)?Number(pointWidth):500;
			updateCirclePolygon();
			m_graphic.refresh();  
			//add the text showing the radius 	
			if (displayRadius) {           		
				setRadiusText();   
			}
			
			map.addEventListener(MouseEvent.MOUSE_UP, map_mouseUpHandler);
		}
        
        //draw the cirle while the user is dragging the mouse
        private function map_mouseMoveHandler( event : MouseEvent ) : void
        {           	     
            m_radius = calculateRadius(event.stageX, event.stageY); 
            updateCirclePolygon();
           	m_graphic.refresh();  
           	//add the text showing the radius 	
           	if (displayRadius) {           		
           		setRadiusText();   
           	}           	                     
        }
        
        private function map_mouseUpHandler(event:MouseEvent) : void
        {
        	//clean up the text to avoid showing the last radius 
            //at the beginning of a new cirle drawing
            if (displayRadius) {
            	textSymbol.text = "";
            }
            if (Polygon(m_graphic.geometry).rings == null) {
            	m_graphic = null;
            }      
            
            map.removeEventListener(MouseEvent.MOUSE_MOVE, map_mouseMoveHandler);
            map.removeEventListener(MouseEvent.MOUSE_UP, map_mouseUpHandler);
            //TODO:格式规范 
            var m_radius_obj:Object={radius: m_radius,cpoint:m_center,type:CUSTOM_CIRCLE};
            //FIXME:如果没有画出圆，则抛出一个点。
            if(m_graphic!=null)
            {
            	 m_graphic.attributes= m_radius_obj;       
            }
            else
            {
            	m_graphic=new Graphic(m_center);
            }
            //dispatch the drawEnd to allow handling of the event                        
            this.dispatchEvent(new DrawEvent("drawEnd", m_graphic));                          
        }
        
        private function setRadiusText():void {         	
			//set the text to show the circle radius + the appropriate units
        	textSymbol.text = formatRaduisNumber() + " 米";
        	//change the geometry of the graphic to move it above the circle
        	MapPoint(m_textGraphic.geometry).y = m_center.y + m_radius;        	
        	m_textGraphic.refresh();
        }
        
        private function calculateRadius(stageX:Number, stageY:Number):Number {
        	//convert the current cursor point to MapPoint     	
        	const mapPoint : MapPoint = map.toMapFromStage( stageX, stageY );
        	//find the horizontal offset from the center in map units
          	const dx : Number = mapPoint.x - m_center.x;
          	//find the vertical offset from the center in map units
            const dy : Number = mapPoint.y - m_center.y;
            //find the radius of the circle using the Pythagorean Theoreme 
            return Math.sqrt( dx * dx + dy * dy );       
        }
        
        //format the radius to not display digits after the decimal point
        private function formatRaduisNumber():String {        	
        	var numberFormater:NumberFormatter = new NumberFormatter();
        	numberFormater.precision = 0;
        	numberFormater.rounding = NumberBaseRoundType.UP;
        	return numberFormater.format(m_radius);
        }
        
        private function updateCirclePolygon():void {
        	//if the cirlce geometry already exists, remove the first and only ring         
            if ((m_circleGeometry.rings != null) && (m_circleGeometry.rings.length > 0)) {
            	m_circleGeometry.removeRing(0);
            }     
            //create the circle points  		
			//add the array of poits as a ring to the circle polygon   
            m_circleGeometry.addRing(createCirclePoints()); 
        }
        
        //create the circle polygon
        private function createCirclePoints():Array {                       
            var cosinus:Number;
            var sinus:Number;
            var x:Number;
            var y:Number;
            var arrayOfPoints:Array = new Array();
            
            //create the array of points which will compose the circle
            for (var i:int = 0; i < numberOfCirclePoints; i++) {
            	sinus = Math.sin((Math.PI*2.0)*(i/numberOfCirclePoints));
            	cosinus = Math.cos((Math.PI*2.0)*(i/numberOfCirclePoints));
            	x = m_center.x + m_radius*cosinus;
            	y = m_center.y + m_radius*sinus; 
            	arrayOfPoints[i] = new MapPoint(x, y);            	        
            }
            
            //add the first point at the end of the array to close the polygon
            arrayOfPoints.push(arrayOfPoints[0]);         
            return arrayOfPoints;
        } 	
		public function cancelClick():void {
			//if map is not specified
			map.removeEventListener(MouseEvent.MOUSE_DOWN, map_pointMouseDownHandler);
		}  
	}
	 
}