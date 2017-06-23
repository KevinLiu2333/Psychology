package com.klsw.piano.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.klsw.piano.service.TbClassTypeService;
import com.klsw.piano.service.TbClassroomService;
import com.klsw.piano.util.Constants;
import com.klsw.pianoCommon.api.model.TbClassroom;
import com.klsw.pianoCommon.api.model.TbClasstype;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 在线课堂相关
 */
@Controller
@RequestMapping(value = "/PMClassroom")
public class PMClassRoomController {

    private final Logger logger = LoggerFactory.getLogger(PMClassRoomController.class);
    @Resource
    private TbClassroomService tbClassroomService;
    @Resource
    private TbClassTypeService tbClassTypeService;

    /**
     * 接口：在线课堂列表查询
     *
     * @param params 参数
     * @return 返回
     */
    @RequestMapping(value = "/classroom")
    @ResponseBody
    public Object classroom(@RequestParam Map<String, String> params) {
        try {
//            String versionNo = params.get("VersionNo");                // 版本号
//            String versionPlatform = params.get("VersionPlatform");    // 平台
//            String uid = params.get("Uid");
            // userid 机器唯一标识
            Integer page = StringUtils.isBlank(params.get("PageId")) || params.get("PageId").equals("undefined") ? 1 : Integer.parseInt(params.get("PageId"));     // 当前页码
            //列表类型id
            Integer id = !StringUtils.isNumeric(params.get("ListId")) ? 9 : Integer.parseInt(params.get("ListId"));        //列表Id
            TbClasstype classtype = tbClassTypeService.selectByPrimaryKey(id);
            if (classtype == null) {
                id = 9;
            }
            //最终返回值
            Map<String, Object> resultMap = Maps.newLinkedHashMap();
            //获取ListContent...tb_ClassType列表
            PageHelper.orderBy("sort");
            List<TbClasstype> tbClasstypeList = tbClassTypeService.selectAll();
            //获取所有课堂(分页)
            PageHelper.startPage(page, 8);
            PageHelper.orderBy("");
            List<TbClassroom> classroomList = tbClassroomService.selectClassroomList(id);
            PageInfo<TbClassroom> classroomPageInfo = new PageInfo<>(classroomList);
            List<Map<String, Object>> pageContent = Lists.newArrayList();
            List<Map<String, Object>> listContent = Lists.newArrayList();
            for (TbClasstype entity : tbClasstypeList) {
                Map<String, Object> map = Maps.newLinkedHashMap();
                map.put("ID", entity.getId());
                map.put("TypeName", entity.getTypename());
                map.put("sort", entity.getSort());
                map.put("isfree", entity.getIsfree());
                map.put("price", entity.getPrice());
                map.put("auditing", entity.getAuditing());
                map.put("cwkuserid", entity.getCwkuserid());
                //加入列表
                listContent.add(map);
            }
            //将实体类中的元素加入pageContent中
            for (TbClassroom entity : classroomList) {
                Map<String, Object> map = Maps.newLinkedHashMap();
//                map.put("ID", entity.getId());
                map.put("ClassID", entity.getId());
                map.put("Title", entity.getTitle());
                //实体类中的classtype元素对应ClassTypeID参数
                map.put("ClassTypeID", entity.getClasstype());
                //实体类中连表查询的typename参数对应ClassType参数
                map.put("ClassType", entity.getTypename());
                map.put("ImgPath", Constants.VIDEOHOST + entity.getImgpath() + Constants.SITE_VERSION);
                map.put("Serices", entity.getSerices());
                map.put("Keynote", entity.getKeynote());
                map.put("Characteristic", entity.getCharacteristic());
                //完整的资源路径
                map.put("VideoPath", Constants.VIDEOHOST + entity.getVideopath() + Constants.SITE_VERSION);
                //加入列表
                pageContent.add(map);
            }
            //当前页
            resultMap.put("currentPageIndex", classroomPageInfo.getPageNum());
            //总页数
            resultMap.put("totalPageCount", classroomPageInfo.getPages());
            //listID
            resultMap.put("currentListID", id);
            //typeList参数
            resultMap.put("ListContent", listContent);
            resultMap.put("pageContent", pageContent);
            return resultMap;
        } catch (Exception e) {
            logger.error("msg",e);
            return "fail";
        }

    }

    /**
     * 接口：在线课堂作业详情
     *
     * @param params 参数
     */
    @RequestMapping(value = "/classroom_info")
    @ResponseBody
    public Object classroom_info(@RequestParam Map<String, String> params) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Map<String, Object> resultMap = Maps.newLinkedHashMap();
        try {
            //ClassID参数
            Integer id = Integer.parseInt(params.get("ClassID"));
            //版本号参数
//            String versionNo = params.get("VersionNo");
//            //平台参数
//            String versionPlatform = params.get("VersionPlatform");
            //机器序列号
//            String uid = params.get("uid");
            TbClassroom tbClassroom = new TbClassroom();
            tbClassroom.setId(id);
            List<TbClassroom> tbClassroomList = tbClassroomService.select(tbClassroom);
            List<Map<String, Object>> content = Lists.newArrayList();
            for (TbClassroom entity : tbClassroomList) {
                Map<String, Object> map = Maps.newLinkedHashMap();
                map.put("ID", entity.getId());
                map.put("auditing", entity.getAuditing());
                map.put("Characteristic", entity.getCharacteristic());
                map.put("ClassType", entity.getClasstype());
                map.put("cwkuserid", entity.getCwkuserid());
                map.put("Keynote", entity.getKeynote());
                map.put("Serices", entity.getSerices());
                map.put("sort", entity.getSort());
                map.put("Title", entity.getTitle());
                map.put("AddTime", sdf.format(entity.getAdddatetime()));
                map.put("ImgPath", Constants.VIDEOHOST + entity.getImgpath() + Constants.SITE_VERSION);
                map.put("VideoPath", Constants.VIDEOHOST + entity.getVideopath() + Constants.SITE_VERSION);
                content.add(map);
            }
            resultMap.put("ClassID", id);
            resultMap.put("Content", content);

        } catch (Exception e) {
            logger.error("msg",e);
            return "fail";
        }
        return resultMap;
    }
}






