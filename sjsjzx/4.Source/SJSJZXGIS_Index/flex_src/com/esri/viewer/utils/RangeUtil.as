package com.esri.viewer.utils
{
	public class RangeUtil
	{
		public function RangeUtil()
		{
		}
		
		/**
		 * 根据最大值，最小值得到等分值
		 * @param minValue 最小值
		 * @param maxValue 最大值
		 * @param partNum 等份数
		 * @return  分割值数组
		 */
		public static function getSplit(minValue:Number, maxValue:Number, partNum:int = 5):Array{
			var split:Array = new Array();
			minValue = Math.min(minValue, maxValue);
			maxValue = Math.max(minValue, maxValue);
			var lower:Number = Math.floor(minValue);
			var upper:Number = Math.ceil(maxValue);
			
			var differValue:Number = upper - lower;//计算差值
			var i:int = 0;
			var gap:Number = differValue / partNum;
			var num:Number = lower;
			if(gap <= 1){
				gap = 1;
				num = lower;
				for(i = 0; i < partNum; i++){
					split.push([num, num + gap]);
					num += gap;
				}
				return split;
			}
			lower = 0;
			upper = getMaxValue(upper);
			gap = getGapValue(upper/partNum );
			
			num = lower;
			var tmp:Number = num + gap;
			for(i = 0; i < partNum; i++){
				split.push([num, tmp]);
				num = tmp;
				tmp = tmp + gap;
			}
			return split;
		}
		
		/*private static function getMinValue(value:Number):Number{
			var count:int = 0;
			var intValue:Number = Math.floor(value); 
			while(intValue > 10){//取前两位
				intValue = Math.floor(intValue / 10);
				count++;
			}
			for(var i:int = 0; i < count; i++){
				intValue *= 10;
			}
			return intValue;
		}
		*/
		private static function getGapValue(value:Number):Number{
			var count:int = 0;
			var intValue:Number = Math.ceil(value); 
			while(intValue > 100){//取前两位
				intValue = Math.ceil(intValue / 10);
				count++;
			}
			while(intValue > 5 && intValue % 5 != 0){//如果不能被5整除则加1，直到能被5整除
				intValue += 1;
			}
			for(var i:int = 0; i < count; i++){
				intValue *= 10;
			}
			return intValue;
		}
		
		private static function getMaxValue(value:Number):Number{
			var count:int = 0;
			var intValue:Number = Math.ceil(value); 
			while(intValue > 1000){//取前三位
				intValue = Math.ceil(intValue / 10);
				count++;
			}
			for(var i:int = 0; i < count; i++){
				intValue *= 10;
			}
			return intValue;
		}
		
	}
}