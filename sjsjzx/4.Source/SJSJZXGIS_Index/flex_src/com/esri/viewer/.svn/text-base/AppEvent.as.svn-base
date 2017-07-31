package com.esri.viewer
{

import flash.events.Event;

/**
 * AppEvent is used within the application to send message among components
 * through the EventBus. All event driven messaging in the Flex Viewer is
 * using the AppEvent.
 *
 * <p>The typical way of sending a message via the AppEvent is, for example:</p>
 *
 * <listing>
 *   ViewerContainer.dispatchEvent(new AppEvent(AppEvent.WIDGET_MENU_CLICKED, false, false, data));
 * </listing>
 *
 * <p>The typical way of receiving a message via the AppEvent is, for example:</p>
 * <listing>
 *   ViewerContainer.addEventListener(AppEvent.WIDGET_MENU_CLICKED, widgetMenuClicked);
 * </listing>
 *
 * @see EventBus
 */
public class AppEvent extends Event
{
    //--------------------------------------------------------------------------
    //
    //  Class constants
    //
    //--------------------------------------------------------------------------

    /**
     * The error event type. This event type is used to send a user friendly
     * error message via the event bus. A error window will display the error
     * message.
     *
     * <p>When sending the error message, the data sent with the AppEvent is the
     * error string. For example: </p>
     *
     * <listing>
     * SiteContainer.dispatchEvent(new AppEvent(AppEvent.APP_ERROR, false, false, "An Error Message"));
     * </listing>
     *
     * @see components.ErrorWindow
     */
    public static const APP_ERROR:String = "appError";

    /**
     * This event type indicates that the Flex Viewer application has completed loading the
     * configuration file. The ConfigManager sends this event so that other components that
     * are interested in obtaining configuration data can listen to this event.
     *
     * @see ConfigManager
     */
    public static const CONFIG_LOADED:String = "configLoaded";

    /**
     * This event type indicates that the map is loaded. The MapManager sends this event so
     * that other components such as the Controller can start working with the map.
     *
     * @see MapManager
     * @see Controller
     */
    public static const MAP_LOADED:String = "mapLoaded";

    public static const MAP_RESIZE:String = "mapResize";

    public static const MAP_LAYER_VISIBLE:String = "mapLayerVisibleChange";

    /**
     * This event type indicates a dynamic layer is loaded.
     */
    public static const LAYER_LOADED:String = "layerLoaded";

    //public static const LAYER_VISIBLE_CHANGED:String   = "layerVisibilityChanged";
    /**
     * This event type is used by the Controller to indicate a base map menu item
     * is clicked.
     *
     * @see Controller
     */
    public static const BASEMAP_SWITCH:String = "basemapSwitch";

	public static const EVENT_LIST_UPDATED:String   = "eventListUpdated";

    /**
     * This event type is used by either Flex Viewer components or a widget to
     * request set the map naviation method. The map navigation method could be
     * pan, zoomin, zoomout, etc.
     *
     * <p>The navigation methods supported are:</p>
     * <listing>
     * pan          (Navigation.PAN)
     * zoomin       (Navigation.ZOOM_IN)
     * zoomout      (Navigation.ZOOM_OUT)
     * zoomfull     (SiteContainer.NAVIGATION_ZOOM_FULL)
     * zoomprevious (SiteContainer.NAVIGATION_ZOOM_PREVIOUS)
     * zoomnext     (SiteContainer.NAVIGATION_ZOOM_NEXT)
     * </listing>
     *
     * <p>The navigation request can be sent as such:</P>
     * <listing>
     *  var data:Object =
     *     {
     *       tool: Navigation.PAN,
     *       status: status
     *      }
     *   SiteContainer.dispatchEvent(new AppEvent(AppEvent.SET_MAP_NAVIGATION, false, false, data));
     * </listing>
     *
     */
    public static const SET_MAP_NAVIGATION:String = "setMapNavigation";

	public static const wdgState:String				  ="wdgState";
	public static const wdg_loaded:String             ="wdg_loaded";
	public static const QuerywdgState:String		  ="QuerywdgState";
	public static const querywdg_loaded:String        ="querywdg_loaded";
    /**
     * This event type is used to set the status text shown at the controller bar. to AppEvent
     * to set the status string, for example:
     *
     * <listing>
     *  dispatchEvent(new AppEvent(AppEvent.SET_STATUS, false, false, status));
     * </listing>
     */
    public static const SET_STATUS:String = "setStatus";

    /**
     * Used to show the info windows on the map through the AppEvent via EventBus.
     *
     * <listing>
     *  SiteContainer.dispatchEvent(new AppEvent(AppEvent.SHOW_INFOWINDOW, false, false, infoData));
     * </listing>
     *
     * The infoData is a dynamic object structure as, for example:
     * <listing>
     *   var infoData:Object =
     *       {
     *          icon: icon,              //a Image object
     *          title: "a title string",
     *          content: "a string",
     *          link: "http://a.url.com",
     *          point: point,            //a Point object
     *          geometry: geom           //a Geometry object
     *       };
     * </listing>
     */
    public static const SHOW_INFOWINDOW:String = "widgetShowInfo";

    /**
     * Used to set map's interactive mode, such as Draw point, line, etc. To
     * use AppEvent via EventBus:
     *
     * <listing>
     * SiteContainer.dispatchEvent(new AppEvent(AppEvent.SET_MAP_ACTION, false, false, data));
     * </listing>
     *
     * Where data is a dynamic data structure:
     *
     * <listing>
     * var data:Object =
     *   {
     *       tool: action,       //an action string token
     *       status: "status string",
     *       handler: callback   //a callback Function
     *   }
     * </listing>
     * Please refer to the Developer's Guide for details.
     */
    public static const SET_MAP_ACTION:String = "setMapAction";

    /**
     * For widget chain and data manager to manage the session generated data.
     */
    public static const DATA_PUBLISH:String = "dataPublishing";

    /**
     * For widget chain. TBD
     */
    public static const DATA_NEW_PUBLISHED:String = "dataPublished";

    /**
     * for widget chain. TBD
     */
    public static const DATA_FETCH_ALL:String = "dataFetchAll";

    public static const DATA_FETCH:String = "dataFetch";

    public static const DATA_SENT:String = "dataFetched";
	
	public static const DATA_ALL_SENT:String = "dataallFetched";
    
	public static const DATA_OPT_LAYERS:String = "dataOperationalLayers";

    public static const DATA_CREATE_INFOWIDGET:String = "createInfoWidget";

    /**
     * for widget layout
     */
    public static const CHANGE_LAYOUT:String = "changeLayout";

	
	/**
	 * for EditWidget Event
	 */
	public static const GRAPHIC_SELECTED:String="graphicSelectById";
	
	public static const GRAPHIC_EDITED:String="graphicEditById";
	public static const GRAPHIC_MAPLOCATEPOINT:String="graphicMapLocatePointById";
	public static const DRAWPOINTS_CLUSTER:String="drawPointsCluster";
	
	public static const DRAWPOINTS_HEAT:String="drawPointsHeat";

	public static const GRAPHIC_GETMOUSECOORD:String="graphicGetMouseCoordById";
	public static const GRAPHIC_PROJECTPOINTS:String="graphicShowProjectPointsById";
	
	public static const GRAPHIC_DELETED:String="graphicDeleted";
	
	public static const GRAPHIC_SAVED:String="graphicSaved";
	
	public static const GRAPHIC_CLEAR:String="graphicClear";
	
	public static const GHDataGridClick:String="fqghDataGridClick";

	public static const LAYER_CLEAR:String="layerClear";

	public static const EXTENT_SELECTED:String="extentSelected";

	public static const SHOW_COLORTHEMETIC:String="showColorThemetic";
	
	public static const TOWN_LOCATED:String="townLocated";
    /**
     * This event type is used by the Controller to indicate a widget run request
     */
    public static const WIDGET_RUN:String = "widgetRunRequested";

	
	public static const REALINFORMATIONWIDGET_RUN:String="realInformationWidgetRun";

    /**
     * used to send message to widget to change its state such as close, min and max
     * var data:Object {
     *    id: widgetId, //as Number
     *    state: stateString //as String
     * }
     * ViewerContainer.publish(AppEvent.WIDGET_CHANGE_STATE, data);
     */
    public static const WIDGET_CHANGE_STATE:String = "widgetChangeState";

    public static const WIDGET_STATE_CHANGED:String = "widgetStateChanged";

	
	public static const LEGENDWIDGETOPEN:String="legendWidgetOpen";
    /**
     * for widget layout
     */
    public static const WIDGET_FOCUS:String = "focusWidget";


    public static const WIDGET_CHAIN_NEXT:String = "widgetChainNextRequested";

    public static const WIDGET_CHAIN_START:String = "widgetChainStartRequested"

    public static const WIDGET_MGR_RESIZE:String = "widgetManagerResize";

    public static const WIDGET_ADD:String = "addWidget";

    public static const WIDGET_ADDED:String = "widgetAdded";

    public static const INFOWIDGET_REQUEST:String = "requestInfoWidget";

    public static const INFOWIDGET_READY:String = "infoWidgetReady";

	public static const LAYERS_LIST_UPDATED:String   = "layerListUpdated";
    //--------------------------------------------------------------------------
    //
    //  Constructor
    //
    //--------------------------------------------------------------------------
    //TODO: simplify this by moving data to 2nd position
    public function AppEvent(type:String, data:Object = null, callback:Function = null)
    {
        super(type, false, false);
        _data = data;
        _callback = callback;
    }

    //--------------------------------------------------------------------------
    //
    //  Properties
    //
    //--------------------------------------------------------------------------

    private var _data:Object;

    private var _callback:Function;

    /**
     * The data will be passed via the event. It allows the event dispatcher to publish
     * data to event listener(s).
     */
    public function get data():Object
    {
        return _data;
    }

    /**
     * @private
     */
    public function set data(value:Object):void
    {
        _data = value;
    }

    /**
     * The callback function associated with this event.
     */
    public function get callback():Function
    {
        return _callback;
    }

    /**
     * @private
     */
    public function set callback(value:Function):void
    {
        _callback = value;
    }

    public override function clone():Event
    {
        return new AppEvent(this.type, this.data);

    }
}

}
