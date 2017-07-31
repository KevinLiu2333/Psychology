package com.esri.viewer.utils
{
	public final class Util
	{
		public function Util()
		{
		}
		public static const startYear:int = 2008;
		
		public static function get slide_MaxValue():int{
			var date:Date = new Date();
			return (date.getFullYear() - startYear) * 12 +  date.getMonth();
		}
		
		
		public static function getSlideMaxValue(startyear:int):int{
			var date:Date = new Date();
			return (date.getFullYear() - startyear) * 12 +  date.getMonth();
		}
		
		public static function getSlideValue(date:Date):int{
			return (date.getFullYear() - startYear) * 12 +  date.getMonth();
		}
		
		public static function getYearMonth(value:int):String{
			var year:int = value / 12 + startYear;
			var month:int = value % 12 + 1;
			if(month  <  10){
				return year + "-0" + month;
			}
			return year + "-" + month;
		}
	}
}