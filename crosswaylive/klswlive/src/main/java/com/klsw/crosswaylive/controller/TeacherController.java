package com.klsw.crosswaylive.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.common.live.model.Ret;
import com.klsw.common.live.model.TbLiveroom;
import com.klsw.common.live.model.TbLiveuser;
import com.klsw.crosswaylive.service.TbLiveRoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师相关
 * Created by liukun on 2017/5/18.
 */
@RestController
@RequestMapping("teacher")
public class TeacherController extends _BaseContoller {

//    @Resource
//    private TeacherService teacherService;

    @Resource
    private TbLiveRoomService liveRoomService;

    /**
     * 根据课程状态查询指定老师的直播间
     *
     * @param teacherId 教师id
     * @param status    直播间状态
     * @param level     直播间等级
     * @param pageNum   页数
     * @param pageSize  每页条数
     * @return 查询结果
     */
    @RequestMapping("livingRooms")
    public Ret livingRooms(@RequestParam("teacherId") Integer teacherId,
                           @RequestParam(value = "status", required = false) Integer status,
                           @RequestParam(value = "level", required = false) Integer level,
                           @RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TbLiveroom liveroom = new TbLiveroom();
        liveroom.setAnchorid(teacherId);
        if (status != null) {
            liveroom.setStatus(status);
        }
        if (level != null) {
            liveroom.setLevel(level);
        }
        try {
            List<TbLiveroom> liverooms = liveRoomService.select(liveroom);
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
     * @param status   状态
     * @param level    等级
     * @param pageNum  页数
     * @param pageSize 每页显示条数
     * @return 教师列表
     */
    @RequestMapping("/teacherList")
    public Ret teacherList(@RequestParam(value = "status") Integer status,
                           @RequestParam(value = "level") Integer level,
                           @RequestParam(value = "pageNum") Integer pageNum,
                           @RequestParam(value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TbLiveuser> liveusers = userService.selectTeacherListByliveroomInfo(status, level);
        PageInfo<TbLiveuser> pageInfo = new PageInfo<>(liveusers);
        return Ret.success("pageInfo", pageInfo);

    }

}


