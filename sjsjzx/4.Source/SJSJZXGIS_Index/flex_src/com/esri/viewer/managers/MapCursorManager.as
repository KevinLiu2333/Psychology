package com.esri.viewer.managers
{
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.events.CalendarLayoutChangeEvent;
	import mx.managers.CursorManager;
	import mx.managers.CursorManagerPriority;
	
	/**
	 * 
	 */
	public class MapCursorManager extends EventDispatcher
	{	
		[Embed(source="assets/images/cursor/panCursor.png")]
		public static var panCursor:Class;
		[Embed(source="assets/images/cursor/paningCursor.png")]
		public static var paningCursor:Class;
		[Embed(source="assets/images/cursor/updownCursor.png")]
		public static var updownCursor:Class;
		[Embed(source="assets/images/cursor/lefttopCursor.png")]
		public static var lefttopCursor:Class;
		[Embed(source="assets/images/cursor/moveCursor.png")]
		public static var moveCursor:Class;
		[Embed(source="assets/images/cursor/crossCursor.png")]
		public static var crossCursor:Class;
		[Embed(source="assets/images/cursor/leftrightCursor.png")]
		public static var leftrightCursor:Class;
		[Embed(source="assets/images/cursor/righttopCursor.png")]
		public static var righttopCursor:Class;
		
		public static function setCursor(cousr:Class):void{
			CursorManager.setCursor(cousr, CursorManagerPriority.HIGH, -16, -16);
		}
	}

}
