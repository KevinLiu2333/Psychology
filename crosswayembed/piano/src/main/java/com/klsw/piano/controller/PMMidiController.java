package com.klsw.piano.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.piano.service.TbMidClassService;
import com.klsw.piano.service.TbMidService;
import com.klsw.piano.util.Constants;
import com.klsw.pianoCommon.api.model.TbMid;
import com.klsw.pianoCommon.api.model.TbMidclass;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liukun on 2016/11/22.
 * 在线曲谱
 */
@Controller
@RequestMapping(value = "/PMMidi")
public class PMMidiController {

    private static final Logger logger = LoggerFactory.getLogger(PMMidiController.class);

    @Resource
    private TbMidService tbMidService;

    @Resource
    private TbMidClassService tbMidClassService;

    /**
     * 接口：下载MIDI
     *
     * @param request
     * @return
     * @author HKJ
     */
    @RequestMapping(value = "/DownMidi")
    @ResponseBody
    public String DownMidi(HttpServletRequest request) {
        String VersionNo = request.getParameter("VersionNo");               // 版本号
        String VersionPlatform = request.getParameter("VersionPlatform");   // 平台
        String Uid = request.getParameter("Uid");                           // userid 机器唯一标识
        String IDStr = request.getParameter("MidiID");                      // midi编号
        if (StringUtils.isEmpty(VersionNo) || StringUtils.isEmpty(VersionPlatform)
                || StringUtils.isEmpty(Uid) || StringUtils.isEmpty(IDStr)) {
            return "fail";
        }
        try {
            Integer id = new Integer(IDStr);
            TbMid tbMid = new TbMid();
            tbMid.setMidid(id);
            tbMid = tbMidService.selectOne(tbMid);
            if (tbMid.getDownloads() == null) {
                tbMid.setDownloads(1);
            } else {
                tbMid.setDownloads(tbMid.getDownloads() + 1);
            }
            tbMidService.updateByPrimaryKey(tbMid);
            return "okey," + tbMid.getDownloads();
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            return "error";
        }
    }

    /**
     * 接口：获取mid列表
     *
     * @param request 请求
     * @return 返回
     * @author HKJ
     */
    @RequestMapping(value = "/GetMidiList")
    @ResponseBody
    public String GetMidiList(HttpServletRequest request) {
        Integer id = 1;
        String VersionNo = request.getParameter("VersionNo");               // 版本号
        String VersionPlatform = request.getParameter("VersionPlatform");   // 平台
        String Uid = request.getParameter("Uid");                           // userid 机器唯一标识
        String Type = request.getParameter("Type");                         // 分类名称
        String IsList = request.getParameter("IsList");                     // 是否是列表
        String idStr = request.getParameter("PageId");                      // 传过来的当前页码
        if (StringUtils.isEmpty(VersionNo) || StringUtils.isEmpty(VersionPlatform)
                || StringUtils.isEmpty(Uid) || StringUtils.isEmpty(idStr)) {
            return "fail";
        }
        id = new Integer(idStr);
        try {
            Map<String, Object> result = new LinkedHashMap<>();
            int pageCount = 10;
            if (!StringUtils.isEmpty(IsList) && IsList.toLowerCase().equals("true")) {
                pageCount = 6;
            } else {
                id = 1;
            }
            String TypeName = "最新推荐";
            Integer typeInt = 0;
            if (!StringUtils.isEmpty(Type)) {
                typeInt = new Integer(Type);
            }
            TbMidclass tbMidclass = new TbMidclass();
            tbMidclass.setId(typeInt);
            tbMidclass = tbMidClassService.selectOne(tbMidclass);
            if (tbMidclass != null) {
                TypeName = tbMidclass.getClassname();
            }

            List<TbMid> midList;
            if (!StringUtils.isEmpty(Type) && !Type.equals("0")) {
                TbMid tbMid = new TbMid();
                tbMid.setMidclassify(Type);
                PageHelper.startPage(id, pageCount);
                PageHelper.orderBy("DownLoads desc");
                midList = tbMidService.select(tbMid);
                PageInfo<TbMid> midlist = new PageInfo<>(midList);
                result.put("Type", Type);
                result.put("IsList", IsList);
                result.put("TypeName", TypeName);
                result.put("currentPageIndex", midlist.getPageNum());
                result.put("totalPageCount", midlist.getPages() == 0 ? 1 : midlist.getPages());
                List<Map<String, Object>> mapList = Lists.newArrayList();
                if (!midlist.getList().isEmpty()) {
                    for (TbMid tMid1 : midlist.getList()) {
                        Map<String, Object> rMap = new LinkedHashMap<>();
                        String time = "";
                        if (tMid1.getAddtime() != null) {
                            time = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(tMid1.getAddtime()));
                        }
                        rMap.put("addtime", String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tMid1.getAddtime())));
                        rMap.put("Time", time);
                        rMap.put("Downloads", tMid1.getDownloads());
                        rMap.put("MIDAuthor", tMid1.getMidauthor());
                        rMap.put("MIDClassify", tMid1.getMidclassify());
                        rMap.put("MIDID", tMid1.getMidid());
                        rMap.put("MIDName", tMid1.getMidname());
                        rMap.put("MIDPath", tMid1.getMidpath());
                        rMap.put("FullPath", Constants.VIDEOHOST + tMid1.getMidpath() + Constants.SITE_VERSION);
                        rMap.put("MIDSize", tMid1.getMidsize());
                        rMap.put("Portfolio", tMid1.getPortfolio());
                        mapList.add(rMap);
                    }
                    result.put("Pagecontent", mapList);
                } else {
                    result.put("Pagecontent", "");
                }
                return JSONUtils.toJSONString(result);
            } else {
                PageHelper.startPage(id, pageCount);
                PageHelper.orderBy("AddTime desc");
                midList = tbMidService.selectByNotNull();
                PageInfo<TbMid> midlist = new PageInfo<>(midList);
                result.put("Type", Type);
                result.put("IsList", IsList);
                result.put("TypeName", TypeName);
                result.put("currentPageIndex", midlist.getPageNum());
                result.put("totalPageCount", midlist.getPages() == 0 ? 1 : midlist.getPages());
                List<Map<String, Object>> mapList = Lists.newArrayList();
                if (!midlist.getList().isEmpty()) {
                    for (TbMid tMid1 : midlist.getList()) {
                        Map<String, Object> rMap = new LinkedHashMap<>();
                        String time = "";
                        if (tMid1.getAddtime() != null) {
                            time = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(tMid1.getAddtime()));
                        }
                        rMap.put("AddTime", String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tMid1.getAddtime())));
                        rMap.put("Time", time);
                        rMap.put("Downloads", tMid1.getDownloads());
                        rMap.put("MIDAuthor", tMid1.getMidauthor());
                        rMap.put("MIDClassify", tMid1.getMidclassify());
                        rMap.put("MIDID", tMid1.getMidid());
                        rMap.put("MIDName", tMid1.getMidname());
                        rMap.put("MIDPath", tMid1.getMidpath());
                        rMap.put("FullPath", Constants.VIDEOHOST + tMid1.getMidpath() + Constants.SITE_VERSION);
                        rMap.put("MIDSize", tMid1.getMidsize());
                        rMap.put("Portfolio", tMid1.getPortfolio());
                        mapList.add(rMap);
                    }

                }
                result.put("pageContent", mapList);
                return JSONUtils.toJSONString(result);
            }
        } catch (Exception exception) {
            logger.info(exception.getMessage());
            return "error";
        }
    }


    @ResponseBody
    @RequestMapping(value = "getTotalMidi")
    public String getTotalMidi() {
        try {
            List<TbMid> midList = tbMidService.selectAll();
            List<Map<String, String>> list = new ArrayList<>();
            for (TbMid mid : midList) {
                Map<String, String> map = new HashMap<>();
                map.put("midid", mid.getMidid().toString());
                map.put("midpath", mid.getMidpath());
                map.put("midname", mid.getMidname());
                list.add(map);
            }
            return JSONUtils.toJSONString(list);
        } catch (Exception e) {
            return "fail";
        }

    }


}








