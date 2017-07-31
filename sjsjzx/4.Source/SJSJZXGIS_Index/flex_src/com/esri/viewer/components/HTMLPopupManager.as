package com.esri.viewer.components
{

import flash.display.DisplayObject;
import flash.display.DisplayObjectContainer;

import mx.core.IFlexDisplayObject;
import mx.managers.PopUpManager;

public class HTMLPopupManager
{
    public static function addPopUp(window:IFlexDisplayObject,
                                    parent:DisplayObject,
                                    modal:Boolean = false,
                                    childList:String = null):void
    {
        PopUpManager.addPopUp(window, parent, modal, childList);
        showSplash(window, parent as DisplayObjectContainer);
    }

    private static function showSplash(window:IFlexDisplayObject, parent:DisplayObjectContainer):void
    {
        var x:Number;
        var y:Number;
        x = (parent.width - window.width) / 2;
        y = (parent.height - window.height) / 2;

        window.move(Math.ceil(x), Math.ceil(y));
        //tweenPosition(window, window.height);
    }

    public static function removePopUp(window:IFlexDisplayObject):void
    {
        PopUpManager.removePopUp(window);
    }
}

}
