package com.klsw.pianoCommon.api.model;

import java.util.List;
import java.util.Map;

public class PMhomeworkList {
    //当前页数
    private Integer currentPageIndex;
    //总页数
    private Integer totalPageCount;
    //用户名
    private String cwkName;
    //用户ID
    private Integer cwkID;
    //昵称
    private String cwkNickName;
    //作业列表
    private List<Map<String, Object>> pageContent;

    public Integer getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(Integer currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public String getCwkName() {
        return cwkName;
    }

    public void setCwkName(String cwkName) {
        this.cwkName = cwkName;
    }

    public Integer getCwkID() {
        return cwkID;
    }

    public void setCwkID(Integer cwkID) {
        this.cwkID = cwkID;
    }

    public String getCwkNickName() {
        return cwkNickName;
    }

    public void setCwkNickName(String cwkNickName) {
        this.cwkNickName = cwkNickName;
    }

    public List<Map<String, Object>> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<Map<String, Object>> pageContent) {
        this.pageContent = pageContent;
    }
}
