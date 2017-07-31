package com.esri.viewer.managers
{

import com.esri.viewer.AppEvent;
import com.esri.viewer.ConfigData;
import com.esri.viewer.ViewerContainer;

import flash.events.EventDispatcher;

import mx.core.FlexGlobals;
import mx.styles.CSSStyleDeclaration;
import mx.styles.IStyleManager2;

public class UIManager extends EventDispatcher
{
    private var configData:ConfigData;

    public function UIManager()
    {
        super();
        ViewerContainer.addEventListener(AppEvent.CONFIG_LOADED, config);
    }

    private function config(event:AppEvent):void
    {
        configData = event.data as ConfigData;
        setViewerStyle();
    }

    private function setViewerStyle():void
    {
        var topLevelStyleManager:IStyleManager2 = FlexGlobals.topLevelApplication.styleManager;

        if (configData.styleColors.length > 4)
        {
            var textColor:uint = configData.styleColors[0];
            var backgroundColor:uint = configData.styleColors[1];
            var rolloverColor:uint = configData.styleColors[2];
            var selectionColor:uint = configData.styleColors[3];
            var titleColor:uint = configData.styleColors[4];
            var applicationBackgroundColor:uint = (configData.styleColors[5] != null) ? configData.styleColors[5] : 0xFFFFFF;
            var styleAlpha:Number = configData.styleAlpha;

            var cssStyleDeclarationGlobal:CSSStyleDeclaration = topLevelStyleManager.getStyleDeclaration("global")
            cssStyleDeclarationGlobal.setStyle("chromeColor", backgroundColor);
            cssStyleDeclarationGlobal.setStyle("color", textColor);
			//cssStyleDeclarationGlobal.setStyle("backgroundColor", backgroundColor);
			//cssStyleDeclarationGlobal.setStyle("backgroundAlpha", styleAlpha);
            cssStyleDeclarationGlobal.setStyle("contentBackgroundColor", backgroundColor);
            cssStyleDeclarationGlobal.setStyle("contentBackgroundAlpha", styleAlpha);
            //cssStyleDeclarationGlobal.setStyle("symbolColor", textColor);
            cssStyleDeclarationGlobal.setStyle("rollOverColor", rolloverColor);
            cssStyleDeclarationGlobal.setStyle("selectionColor", selectionColor);
            cssStyleDeclarationGlobal.setStyle("focusColor", titleColor);
            //cssStyleDeclarationGlobal.setStyle("accentColor", textColor);
            cssStyleDeclarationGlobal.setStyle("textSelectedColor", textColor);
            cssStyleDeclarationGlobal.setStyle("textRollOverColor", textColor);
            topLevelStyleManager.setStyleDeclaration("global", cssStyleDeclarationGlobal, false);

            var cssStyleDeclarationModule:CSSStyleDeclaration = new CSSStyleDeclaration();
            cssStyleDeclarationModule.setStyle("chromeColor", backgroundColor);
            cssStyleDeclarationModule.setStyle("color", textColor);
            cssStyleDeclarationModule.setStyle("contentBackgroundColor", backgroundColor);
            cssStyleDeclarationModule.setStyle("contentBackgroundAlpha", styleAlpha);
            //cssStyleDeclarationModule.setStyle("symbolColor", textColor);
            cssStyleDeclarationModule.setStyle("rollOverColor", rolloverColor);
            cssStyleDeclarationModule.setStyle("selectionColor", selectionColor);
            cssStyleDeclarationModule.setStyle("focusColor", titleColor);
            //cssStyleDeclarationModule.setStyle("accentColor", textColor);
            cssStyleDeclarationModule.setStyle("textSelectedColor", textColor);
            cssStyleDeclarationModule.setStyle("textRollOverColor", textColor);
            topLevelStyleManager.setStyleDeclaration("mx.modules.Module", cssStyleDeclarationModule, false);

            //Style Application
            var cssStyleDeclarationApplication:CSSStyleDeclaration = topLevelStyleManager.getStyleDeclaration("spark.components.Application");
            if (cssStyleDeclarationApplication)
            {
                cssStyleDeclarationApplication.setStyle("backgroundColor", applicationBackgroundColor);
                cssStyleDeclarationApplication.setStyle("backgroundAlpha", styleAlpha);
                topLevelStyleManager.setStyleDeclaration("spark.components.Application", cssStyleDeclarationApplication, false);
            }

            var cssStyleDeclarationApplicationWindowed:CSSStyleDeclaration = topLevelStyleManager.getStyleDeclaration("spark.components.WindowedApplication");
            if (cssStyleDeclarationApplicationWindowed)
            {
                cssStyleDeclarationApplicationWindowed.setStyle("backgroundColor", applicationBackgroundColor);
                cssStyleDeclarationApplicationWindowed.setStyle("backgroundAlpha", styleAlpha);
                topLevelStyleManager.setStyleDeclaration("spark.components.WindowedApplication", cssStyleDeclarationApplicationWindowed, false);
            }

            //Style WidgetTemplate
            var cssStyleDeclarationWT:CSSStyleDeclaration = new CSSStyleDeclaration("com.esri.viewer.WidgetTemplate");
            // When pointing to graphical theme replace WidgetTemplateSkin with GraphicalWidgetTemplateSkin 
            cssStyleDeclarationWT.setStyle("backgroundColor", backgroundColor);
			cssStyleDeclarationWT.setStyle("backgroundAlpha", styleAlpha);
            //cssStyleDeclarationWT.setStyle("borderColor", textColor);
			cssStyleDeclarationWT.setStyle("borderColor", 0x1FA5FB);
            topLevelStyleManager.setStyleDeclaration("com.esri.viewer.WidgetTemplate", cssStyleDeclarationWT, false);

			
			/*Style For InfoWindow
               When pointing to graphical theme, you will need to explicitly set properties for infoWindow.
               e.g. cssStyleDeclarationInfoContainer.setStyle("backgroundColor",0xFFFFFF);
             */
            var cssStyleDeclarationInfoContainer:CSSStyleDeclaration = topLevelStyleManager.getStyleDeclaration("com.esri.ags.components.supportClasses.InfoContainer");

            /*
               For custom skin for infoWindow, borderSkin needs to be set to null as shouwn below
               infoOffsetX and infoOffsetY can be used to change the location where infowindow will be shown.
               infoPlacement would need to be set to none in this case for preventing infowindow placement from auto adjusting.
             */
            /*
               cssStyleDeclarationInfoContainer.setStyle("borderSkin",null);
               cssStyleDeclarationInfoContainer.setStyle("infoOffsetX",-45);
             */

            cssStyleDeclarationInfoContainer.setStyle("backgroundColor", backgroundColor);
            cssStyleDeclarationInfoContainer.setStyle("backgroundAlpha", styleAlpha);
            cssStyleDeclarationInfoContainer.setStyle("color", 0x000000);
			cssStyleDeclarationInfoContainer.setStyle("borderColor", 0x1FA5FB);
            cssStyleDeclarationInfoContainer.setStyle("borderThickness", 1);
            topLevelStyleManager.setStyleDeclaration("com.esri.ags.components.supportClasses.InfoContainer", cssStyleDeclarationInfoContainer, false);

            //Style Banner title and WidgetTitle
            var cssStyleDeclarationWidgetTitle:CSSStyleDeclaration = new CSSStyleDeclaration(".WidgetTitle");
            cssStyleDeclarationWidgetTitle.setStyle("color", titleColor);
            topLevelStyleManager.setStyleDeclaration(".WidgetTitle", cssStyleDeclarationWidgetTitle, false);

            var cssStyleDeclarationBannerTitle:CSSStyleDeclaration = new CSSStyleDeclaration(".BannerTitle");
            cssStyleDeclarationBannerTitle.setStyle("color", titleColor);
            topLevelStyleManager.setStyleDeclaration(".BannerTitle", cssStyleDeclarationBannerTitle, false);

            var cssStyleDeclarationBannerSubtitle:CSSStyleDeclaration = new CSSStyleDeclaration(".BannerSubtitle");
            cssStyleDeclarationBannerSubtitle.setStyle("color", titleColor);
            topLevelStyleManager.setStyleDeclaration(".BannerSubtitle", cssStyleDeclarationBannerSubtitle, false);

            // Style s|Panel
            var cssStyleDeclarationSPanel:CSSStyleDeclaration = topLevelStyleManager.getStyleDeclaration("spark.components.Panel");
            cssStyleDeclarationSPanel.setStyle("backgroundColor", backgroundColor);
            cssStyleDeclarationSPanel.setStyle("backgroundAlpha", styleAlpha);
            topLevelStyleManager.setStyleDeclaration("spark.components.Panel", cssStyleDeclarationSPanel, false);

            //Style mx|Panel
            var cssStyleDeclarationMxPanel:CSSStyleDeclaration = topLevelStyleManager.getStyleDeclaration("mx.containers.Panel");
            cssStyleDeclarationMxPanel.setStyle("backgroundColor", backgroundColor);
            cssStyleDeclarationMxPanel.setStyle("backgroundAlpha", styleAlpha);
            topLevelStyleManager.setStyleDeclaration("mx.containers.Panel", cssStyleDeclarationMxPanel, false);

            //Style TabNavigator
            var cssStyleDeclarationNavigator:CSSStyleDeclaration = topLevelStyleManager.getStyleDeclaration("mx.containers.TabNavigator");
            cssStyleDeclarationNavigator.setStyle("backgroundColor", backgroundColor);
            cssStyleDeclarationNavigator.setStyle("backgroundAlpha", styleAlpha);
            topLevelStyleManager.setStyleDeclaration("mx.containers.TabNavigator", cssStyleDeclarationNavigator, false);

            // Style mx|Alert
            var cssStyleDeclaration:CSSStyleDeclaration = new CSSStyleDeclaration();
            cssStyleDeclaration.setStyle("color", textColor);
            cssStyleDeclaration.setStyle("backgroundColor", backgroundColor);
            topLevelStyleManager.setStyleDeclaration("mx.controls.Alert", cssStyleDeclaration, false);

            //Style Tooltip
            var cssStyleDeclarationTooltip:CSSStyleDeclaration = topLevelStyleManager.getStyleDeclaration("mx.controls.ToolTip");
            cssStyleDeclarationTooltip.setStyle("color", textColor);
            cssStyleDeclarationTooltip.setStyle("backgroundColor", backgroundColor);
            topLevelStyleManager.setStyleDeclaration("mx.controls.ToolTip", cssStyleDeclarationTooltip, false);

            //Style TitleWindow
            var cssStyleDeclarationTitleWindow:CSSStyleDeclaration = new CSSStyleDeclaration();
            cssStyleDeclarationTitleWindow.setStyle("color", textColor);
            cssStyleDeclarationTitleWindow.setStyle("backgroundColor", backgroundColor);
            topLevelStyleManager.setStyleDeclaration("mx.containers.TitleWindow", cssStyleDeclarationTitleWindow, false);

            //Style DataGrid
            var cssStyleDeclarationDataGrid:CSSStyleDeclaration = new CSSStyleDeclaration();
            cssStyleDeclarationDataGrid.setStyle("alternatingItemColors", [ backgroundColor, backgroundColor ]);
            cssStyleDeclarationDataGrid.setStyle("contentBackgroundColor", backgroundColor);
            cssStyleDeclarationDataGrid.setStyle("backgroundAlpha", styleAlpha);
			
			cssStyleDeclarationDataGrid.setStyle("color", 0x3F3F3F);
			cssStyleDeclarationDataGrid.setStyle("textSelectedColor", 0xFFFFFF);
			cssStyleDeclarationDataGrid.setStyle("textRollOverColor", 0xFF0000);
			
            topLevelStyleManager.setStyleDeclaration("mx.controls.DataGrid", cssStyleDeclarationDataGrid, false);

            //Style RichEditableText
            var cssStyleDeclarationRET:CSSStyleDeclaration = new CSSStyleDeclaration();
            cssStyleDeclarationRET.setStyle("focusedTextSelectionColor", rolloverColor);
            cssStyleDeclarationRET.setStyle("unfocusedTextSelectionColor", rolloverColor);
            topLevelStyleManager.setStyleDeclaration("spark.components.RichEditableText", cssStyleDeclarationRET, false);

            var cssStyleDeclarationTI:CSSStyleDeclaration = new CSSStyleDeclaration();
            cssStyleDeclarationTI.setStyle("color", textColor);
			//cssStyleDeclarationTI.setStyle("chromeColor", textColor);
            topLevelStyleManager.setStyleDeclaration("spark.components.TextInput", cssStyleDeclarationTI, false);
            topLevelStyleManager.setStyleDeclaration("spark.components.TextArea", cssStyleDeclarationTI, false);
            topLevelStyleManager.setStyleDeclaration("mx.controls.TextInput", cssStyleDeclarationTI, false);
            topLevelStyleManager.setStyleDeclaration("mx.controls.TextArea", cssStyleDeclarationTI, false);
            topLevelStyleManager.setStyleDeclaration("mx.controls.VSlider", cssStyleDeclarationTI, false);
            topLevelStyleManager.setStyleDeclaration("spark.components.VSlider", cssStyleDeclarationTI, false);
            topLevelStyleManager.setStyleDeclaration("mx.controls.HSlider", cssStyleDeclarationTI, false);
            topLevelStyleManager.setStyleDeclaration("spark.components.HSlider", cssStyleDeclarationTI, false);
        }

        var cssStyleDeclarationModal:CSSStyleDeclaration = topLevelStyleManager.getStyleDeclaration("global")
        cssStyleDeclarationModal.setStyle("modalTransparencyColor", 0x777777);
        cssStyleDeclarationModal.setStyle("modalTransparencyBlur", 1);
        cssStyleDeclarationModal.setStyle("modalTransparency", 0.5);
        cssStyleDeclarationModal.setStyle("modalTransparencyDuration", 1000); //messes up tween!
        topLevelStyleManager.setStyleDeclaration("global", cssStyleDeclarationModal, true);
		
		var cssStyleDeclarationMapInfoWindow:CSSStyleDeclaration = new CSSStyleDeclaration("com.esri.ags.Map.infoWindow.label");
//		cssStyleDeclarationMapInfoWindow.setStyle("backgroundColor", 0x1FA5FB);
		cssStyleDeclarationMapInfoWindow.setStyle("color", 0x000000);
		topLevelStyleManager.setStyleDeclaration("com.esri.ags.Map.infoWindow.label", cssStyleDeclarationMapInfoWindow, false);

    }
}

}
