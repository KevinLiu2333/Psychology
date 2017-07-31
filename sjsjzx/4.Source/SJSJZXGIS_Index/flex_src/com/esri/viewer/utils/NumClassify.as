package com.esri.viewer.utils
{
	public  class NumClassify
	{
		public  function NumClassify()
		{
		}
		public static function ThreeClass(min:int,max:int):Array
		{
			var array:Array=new Array();
			var dis:int=max-min;
			var grade:int=(int)(dis/3);
			array[0]=min;
			array[1]=min+grade;
			array[2]=min+grade*2;
			array[3]=max;
			return array;
		}
	}
}