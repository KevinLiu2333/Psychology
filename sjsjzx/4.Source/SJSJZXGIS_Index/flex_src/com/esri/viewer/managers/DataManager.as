
package com.esri.viewer.managers
{
import com.esri.viewer.utils.Hashtable;

import flash.events.EventDispatcher;

import mx.collections.ArrayCollection;
import com.esri.viewer.AppEvent;
import com.esri.viewer.ViewerContainer;

/**
 * data manager will be storing the session data to support data reuse such as
 * widget chain.
 * A data manager UI (a special widget) will be developed to allow user edit the data.
 */
public class DataManager extends EventDispatcher
{
    private var dataTable:Hashtable  = new Hashtable();

    public function DataManager()
    {
        //TODO: implement function
        super();
        ViewerContainer.addEventListener(AppEvent.CONFIG_LOADED, config);

        //this is a example to setup the listner to get the type of data the Data
        //Manager is interested in.
        ViewerContainer.addEventListener(AppEvent.DATA_FETCH_ALL, fetchAllData);
        ViewerContainer.addEventListener(AppEvent.DATA_PUBLISH, addData);
        ViewerContainer.addEventListener(AppEvent.DATA_FETCH, fetchData);
    }

    private function config(event:AppEvent):void
    {

    }

    private function fetchAllData(event:AppEvent):void
    {
        ViewerContainer.dispatchEvent(new AppEvent(AppEvent.DATA_ALL_SENT, dataTable));
    }

    private function fetchData(event:AppEvent):void
    {
        var key:String = event.data.key as String;
        var data:Object =
            {
                key: key,
                data: dataTable.find(key)
            };
        ViewerContainer.dispatchEvent(new AppEvent(AppEvent.DATA_SENT, data));
    }

    private function addData(event:AppEvent):void
    {
        var key:String = event.data.key;
        if (key != "")
        {
            var dataCollection:Object = event.data.data;
            if (dataTable.containsKey(key))
            {
                dataTable.remove(key);
            }
            dataTable.add(key, dataCollection);

            var data:Object =
                {
                    key: key,
                    data: dataTable
                };
            ViewerContainer.dispatchEvent(new AppEvent(AppEvent.DATA_NEW_PUBLISHED, data));
        }
    }
}

}