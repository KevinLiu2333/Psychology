package com.esri.viewer.components
{
	import spark.components.Button;
	
	//icons
	[Style(name="iconUp",type="*")]
	[Style(name="iconOver",type="*")]
	[Style(name="iconDown",type="*")]
	[Style(name="iconDisabled",type="*")]
	
	[Style(name="iconWidth",type="Number")]
	[Style(name="iconHeight",type="Number")]
	
	//paddings
	[Style(name="paddingLeft",type="Number")]
	[Style(name="paddingRight",type="Number")]
	[Style(name="paddingTop",type="Number")]
	[Style(name="paddingBottom",type="Number")]
	
	public class IconButton extends Button
	{
		public function IconButton()
		{
			super();
			this.setStyle("paddingLeft", 4);
			this.setStyle("paddingRight", 4);
			this.setStyle("paddingTop", 4);
			this.setStyle("paddingBottom", 4);
		}
	}
}