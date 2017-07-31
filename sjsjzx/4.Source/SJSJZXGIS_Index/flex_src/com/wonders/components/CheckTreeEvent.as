package com.wonders.components
{
	import flash.events.Event;

	public class CheckTreeEvent extends Event
	{
		public static const CHECK_TREE_CHECKED:String="checkTreeChecked";
		public function CheckTreeEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}