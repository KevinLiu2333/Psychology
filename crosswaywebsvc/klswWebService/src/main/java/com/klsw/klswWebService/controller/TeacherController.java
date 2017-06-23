package com.klsw.klswWebService.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbLiveroom;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.service.TbLiveRoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 教师相关
 * Created by liukun on 2017/5/18.
 */
@RestController
@RequestMapping("live/teacher")
public class TeacherController {

//    @Resource
//    private TeacherService teacherService;

    @Resource
    private TbLiveRoomService liveRoomService;

    @Resource
    private TbCwkService userService;

    /**
     * 根据课程状态查询指定老师的直播间
     *
     * @param teacherId 教师id
     * @param pageNum   页数
     * @param pageSize  每页条数
     * @return 查询结果
     */
    @RequestMapping("livingRooms")
    public Ret livingRooms(@RequestParam("teacherId") Integer teacherId,
                           @RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<TbLiveroom> liverooms = liveRoomService.selectByTeacherId(teacherId);
            PageInfo<TbLiveroom> pageInfo = new PageInfo<>(liverooms);
            return Ret.success("pageInfo", pageInfo);
        } catch (Exception e) {
            return Ret.error("查询失败");
        }

    }

    /**
     * 根据开课情况查询符合条件的教师
     * 教师不重复出现
     *
     * @param teachGrade 教师授课级别
     * @param level      等级
     * @param pageNum    页数
     * @param pageSize   每页显示条数
     * @return 教师列表
     */
    @RequestMapping("/list")
    public Ret teacherList(@RequestParam(value = "teachGrade", required = false) String teachGrade,
                           @RequestParam(value = "level", required = false) Integer level,
                           @RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        teachGrade = StringUtils.isBlank(teachGrade) ? null : teachGrade;
        level = level == 10 ? null : level;
        List<Map<String, Object>> liveusers = userService.selectTeacherListByliveroomInfo(teachGrade, level);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(liveusers);
        return Ret.success("pageInfo", pageInfo);

    }

}


