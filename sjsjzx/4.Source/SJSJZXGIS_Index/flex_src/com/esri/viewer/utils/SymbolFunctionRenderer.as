package com.esri.viewer.utils
{
	import com.esri.ags.Graphic;
	import com.esri.ags.renderers.Renderer;
	import com.esri.ags.symbols.Symbol;
	
	public class SymbolFunctionRenderer extends Renderer
	{
		public var symbolFunction:Function;
		
		public function SymbolFunctionRenderer()
		{
			super();
		}
		
		public override function getSymbol(graphic:Graphic):Symbol{
			return symbolFunction(graphic);
		}
	}
}