package com.esri.viewer
{

import com.esri.ags.Map;

public interface IInfowindowTemplate
{
    function set data(value:Object):void;
    function get data():Object;
    function set infoClass(value:Class):void;
    function get infoClass():Class;
    function set infoConfig(value:XML):void;
}

}
