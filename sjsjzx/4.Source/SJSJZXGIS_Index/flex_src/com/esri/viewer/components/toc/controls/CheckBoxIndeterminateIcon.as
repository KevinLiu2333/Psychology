package com.esri.viewer.components.toc.controls
{

import mx.skins.halo.CheckBoxIcon;

/**
 * CheckBox skin that supports a tri-state check. In addition to selected and
 * unselected, the CheckBox can be in an indeterminate state.
 */
public class CheckBoxIndeterminateIcon extends CheckBoxIcon
{
    /**
     * @private
     */
    override protected function updateDisplayList(w:Number, h:Number):void
    {
        super.updateDisplayList(w, h);

        var indet:Boolean = getStyle("indeterminate");

        if (indet)
        {
            var cornerRadius:Number = 2;

            var boxFillColors:Array = [ 0xAAAACC, 0x666666 ];
            var boxFillAlphas:Array = [ 1.0, 1.0 ];

            drawRoundRect(
                3, 3, w - 6, h - 6, cornerRadius,
                boxFillColors, boxFillAlphas,
                verticalGradientMatrix(1, 1, w - 2, h - 2));
        }
    }
}

}
