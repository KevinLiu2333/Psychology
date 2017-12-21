package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "DIC_RESOURCE_THEME")
public class DicResourceTheme {
    @Column(name = "THEME_ID")
    private String themeId;

    @Column(name = "THEME_NAME")
    private String themeName;

    /**
     * @return THEME_ID
     */
    public String getThemeId() {
        return themeId;
    }

    /**
     * @param themeId
     */
    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    /**
     * @return THEME_NAME
     */
    public String getThemeName() {
        return themeName;
    }

    /**
     * @param themeName
     */
    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}