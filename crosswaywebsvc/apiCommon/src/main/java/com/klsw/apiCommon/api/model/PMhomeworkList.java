package com.klsw.apiCommon.api.model;

import java.util.List;
import java.util.Map;

public class PMhomeworkList {
    //当前页数
    private Integer currentpageindex;
    //总页数
    private Integer totalpagecount;
    //用户名
    private String cwkname;
    //用户ID
    private Integer cwkid;
    //昵称
    private String cwknickname;
    //作业列表
    private List<Map<String, String>> pagecontent;

    public Integer getCurrentpageindex() {
        return currentpageindex;
    }

    public void setCurrentpageindex(Integer currentpageindex) {
        this.currentpageindex = currentpageindex;
    }

    public Integer getTotalpagecount() {
        return totalpagecount;
    }

    public void setTotalpagecount(Integer totalpagecount) {
        this.totalpagecount = totalpagecount;
    }

    public String getCwkname() {
        return cwkname;
    }

    public void setCwkname(String cwkname) {
        this.cwkname = cwkname;
    }

    public Integer getCwkid() {
        return cwkid;
    }

    public void setCwkid(Integer cwkid) {
        this.cwkid = cwkid;
    }

    public String getCwknickname() {
        return cwknickname;
    }

    public void setCwknickname(String cwknickname) {
        this.cwknickname = cwknickname;
    }

    public List<Map<String, String>> getPagecontent() {
        return pagecontent;
    }

    public void setPagecontent(List<Map<String, String>> pagecontent) {
        this.pagecontent = pagecontent;
    }
}
