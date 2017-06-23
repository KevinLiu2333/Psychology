package com.klsw.crosswaylive.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.common.live.model.Ret;
import com.klsw.common.live.model.TbLiveappoint;
import com.klsw.common.live.model.TbLiveroom;
import com.klsw.common.live.model.TbLiveuser;
import com.klsw.crosswaylive.service.TbLiveAppointService;
import com.klsw.crosswaylive.service.TbLiveRoomService;
import com.klsw.crosswaylive.util.AliyunUtils;
import com.klsw.crosswaylive.util.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * Created by liukun on 2017/3/11.
 * 直播间相关
 */
@RestController
@RequestMapping(value = "live")
public class LiveRoomController extends _BaseContoller {

    private static final Logger LOGGER = LoggerFactory.getLogger(LiveRoomController.class);

    @Resource
    private TbLiveRoomService liveRoomService;

    @Resource
    private TbLiveAppointService appointService;

    /**
     * @param title       直播标题
     * @param startTime   开始时间
     * @param endTime     结束时间
     * @param userId      用户id
     * @param interactNum 互动人数
     * @param level       直播间难度等级
     * @param password    密码
     * @param remark      备注
     * @return 创建结果
     */
    @RequestMapping("createLiveRoom")
    public Ret appoint(@RequestParam("title") String title,
                       @RequestParam("startTime") String startTime,
                       @RequestParam("endTime") String endTime,
                       @RequestParam("userId") Integer userId,
                       @RequestParam("interactNum") Integer interactNum,
                       @RequestParam("level") Integer level,
                       @RequestParam("password") String password,
                       @RequestParam("remark") String remark) {
        try {
            if (StringUtils.isBlank(title) || StringUtils.isBlank(startTime)) {
                return Ret.warn("直播参数有误");
            }
            Date start = DateUtils.parseDate(startTime, Constants.parsePattern);
            if (start.before(new Date())) {
                return Ret.warn("直播参数有误");
            }
            Date end = DateUtils.parseDate(endTime, Constants.parsePattern);
            int duration = (int) ((end.getTime() - start.getTime()) / (1000 * 60));
            TbLiveuser liveuser = userService.selectByPrimaryKey(userId);
            //如果用户没有设备,不允许直播
            if (StringUtils.isBlank(liveuser.getSerialnum())) {
                return Ret.warn("您未绑定设备");
            } else if (!"tea".equals(liveuser.getType())) {
                return Ret.warn("学生不允许开直播");
            }
            TbLiveroom liveroom = new TbLiveroom();
            level = level == null ? 1 : level;
            if (!StringUtils.isBlank(password)) {
                liveroom.setPassword(password);
            }
            liveroom.setInteractnum(interactNum);
            liveroom.setRemark(remark);
            liveroom.setTitle(title);
            liveroom.setCreatetime(new Date());
            liveroom.setAnchorid(userId);
            liveroom.setEndtime(end);
            liveroom.setStarttime(start);
            liveroom.setDuration(duration);
            liveroom.setStatus(0);
            liveroom.setAnchorname(liveuser.getNickname());
            liveroom.setLevel(level);
            liveRoomService.insertSelective(liveroom);
            return Ret.success("S", "创建直播成功", null);
        } catch (Exception e) {
            LOGGER.error("msg", e);
            return Ret.error("创建直播失败");
        }
    }

    /**
     * 查询自己的直播间
     * 如果是教师,则是查询自己开播的直播间
     * 如果是学生,则是查询预约的直播间
     *
     * @param pageNum  页码
     * @param status   状态
     * @param pageSize 每页页数
     * @param userId   用户id
     * @return 查询结果
     */
    @RequestMapping(value = "myLiveRoom")
    public Ret getMyLiveRoom(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam(value = "status", required = false) Integer status,
                             @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("userId") Integer userId,
                             @RequestParam(value = "level", required = false) Integer level) {
        List<TbLiveroom> liveroomList;
        try {
            TbLiveuser liveuser = userService.selectByPrimaryKey(userId);
            PageHelper.startPage(pageNum, pageSize);
            PageHelper.orderBy("startTime,createTime desc");
            if ("stu".equals(liveuser.getType())) {
                liveroomList = liveRoomService.selectAppointLiveroom(userId, status);

            } else {
                TbLiveroom liveroom = new TbLiveroom();
                liveroom.setAnchorid(userId);
                if (status != null && status != 10) {
                    liveroom.setStatus(status);
                }
                if (level != null && level != 0) {
                    liveroom.setLevel(level);
                }
                liveroomList = liveRoomService.select(liveroom);
            }
            PageInfo<TbLiveroom> pageInfo = new PageInfo<>(liveroomList);
            return Ret.success("pageInfo", pageInfo);
        } catch (Exception e) {
            LOGGER.error("msg", e);
            return Ret.error("查询失败");
        }
    }

    /**
     * 查询直播间列表
     *
     * @param pageNum  页码
     * @param status   状态
     * @param pageSize 每页页数
     * @return 查询结果
     */
    @RequestMapping(value = "liveRoomList")
    public Ret getLiveRoomList(@RequestParam("pageNum") Integer pageNum,
                               @RequestParam("status") Integer status,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("level") Integer level,
                               @RequestParam(value = "date", required = false) String datestr) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            PageHelper.orderBy("startTime,createTime desc");
            List<TbLiveroom> liveroomList;
            if (!StringUtils.isBlank(datestr)) {
                liveroomList = liveRoomService.selectLiveroomByDate(datestr);
            } else {
                TbLiveroom liveroom = new TbLiveroom();
                liveroom.setStatus(status);
                if (level != null && level != 0) {
                    liveroom.setLevel(level);
                }
                liveroomList = liveRoomService.select(liveroom);
            }
            PageInfo<TbLiveroom> pageInfo = new PageInfo<>(liveroomList);
            return Ret.success("pageInfo", pageInfo);
        } catch (Exception e) {
            LOGGER.error("msg", e);
            return Ret.error("查询失败");
        }
    }

    /**
     * 预约互动直播
     *
     * @param userId     用户名
     * @param liveroomId 直播间Id
     * @return 预约结果
     */
    @RequestMapping(value = "appoint")
    @Transactional(rollbackFor = Exception.class)
    public Ret appoint(@RequestParam("userId") Integer userId,
                       @RequestParam("liveroomId") Integer liveroomId) throws Exception {
        TbLiveuser liveuser = userService.selectByPrimaryKey(userId);
        if (liveuser == null || StringUtils.isBlank(liveuser.getSerialnum()))
            return Ret.warn("您未绑定设备");
//        if (liveuser.getType().equals("tea")) {
//            return Ret.warn("教师不允许预约");
//        }
        TbLiveroom tbLiveroom = liveRoomService.selectByPrimaryKey(liveroomId);
        if (tbLiveroom == null || tbLiveroom.getStatus() > 0 || tbLiveroom.getAnchorid().equals(userId
        ))
            return Ret.warn("该课程不允许预约");
        //预约人数已满
        if (tbLiveroom.getAppointnum() >= 4)
            return Ret.warn("该课程已预约满");
        TbLiveappoint liveappoint = new TbLiveappoint();
        liveappoint.setLiveroomid(liveroomId);
        liveappoint.setUserid(userId);
        if (appointService.select(liveappoint).size() > 0)
            return Ret.warn("您已经预约过该课程");
        liveappoint.setCreatetime(new Date());
        liveappoint.setUsername(liveuser.getUsername());
        appointService.insertSelective(liveappoint);
        StringBuilder interactUser = new StringBuilder(tbLiveroom.getInteractuser());
        if (interactUser.length() == 0)
            interactUser.append(userId);
        else
            interactUser.append(",").append(userId);
        tbLiveroom.setInteractuser(interactUser.toString());
        tbLiveroom.setAppointnum(tbLiveroom.getAppointnum() + 1);
        liveRoomService.updateByPrimaryKey(tbLiveroom);
        return Ret.success("S", "预约成功", null);
    }

    /**
     * //     * @param userId     用户名
     * //     * @param liveroomId 直播间Id
     *
     * @return 结果
     */
    @RequestMapping(value = "/getPushStreamPath")
    public String getPushStreamPath(@RequestParam(value = "username",required = false)String username
//            @RequestParam("userId") Integer userId,
//                                 @RequestParam("liveroomId") Integer liveroomId
    ) {
        try {
//            TbLiveroom liveroom = liveRoomService.selectByPrimaryKey(liveroomId);
//            TbLiveuser liveuser = userService.selectByPrimaryKey(userId);
//            if (liveroom == null || liveroom.getStatus() == 2 || !liveroom.getAnchorid().equals(userId)) {
//                return Ret.warn("参数有误");
//            }

            String streamname = username==null?"yindian":username;
//            liveroom.setStatus(1);
//            liveRoomService.updateByPrimaryKey(liveroom);
            return AliyunUtils.getPushStreamPath(streamname);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * @param liveroomId 直播间id
     * @param password   密码
     * @return 检查结果
     */
    @RequestMapping("checkPassword")
    public Ret checkPassword(@RequestParam("liveroomId") Integer liveroomId,
                             @RequestParam("password") String password) {
        try {
            TbLiveroom liveroom = liveRoomService.selectByPrimaryKey(liveroomId);
            if (liveroom == null)
                return Ret.warn("直播间不存在");
            else if (!password.equals(liveroom.getPassword())) {
                return Ret.warn("密码错误");
            }
            return Ret.success();
        } catch (Exception e) {
            LOGGER.error("msg", e);
            return Ret.error("未知错误");
        }
    }

    /**
     * @param userId     用户名
     * @param liveroomId 直播间Id
     * @return 结果
     */
    @RequestMapping(value = "/getAppointList")
    public Ret getAppointList(@RequestParam("userId") Integer userId,
                              @RequestParam("liveroomId") Integer liveroomId) {
        try {
            TbLiveroom liveroom = liveRoomService.selectByPrimaryKey(liveroomId);
            if (null == liveroom || !Objects.equals(liveroom.getAnchorid(), userId))
                return Ret.error("参数有误");
            TbLiveappoint liveappoint = new TbLiveappoint();
            liveappoint.setLiveroomid(liveroomId);
            List<TbLiveappoint> list = appointService.select(liveappoint);
            return Ret.success(list);
        } catch (Exception e) {
            LOGGER.error("msg", e);
            return Ret.error("fail");
        }
    }

    @RequestMapping(value = "/getLivingDate")
    public Ret getLivingDate() {
        List<String> list = liveRoomService.selectLivingDate();
        return Ret.success(list);
    }


    /**
     * @return 结果
     */
    @RequestMapping(value = "/getPullStreamPath")
    public String getPullStreamPath(@RequestParam(value = "username",required = false)String username) {
//        try {
//            TbLiveroom liveroom = new TbLiveroom();
//            liveroom.setStatus(1);
//            List<TbLiveroom> liveroomList = liveRoomService.select(liveroom);
//            JSONArray jsonArray = new JSONArray();
//            for (TbLiveroom entity : liveroomList) {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("id", entity.getId());
//                jsonObject.put("pullStreamPath", AliyunUtils.getPullStreamPath(entity.getAnchorname()));
//                jsonArray.add(jsonObject);
//            }
//            return Ret.success("liveRoom", jsonArray);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String streamname = username==null?"yindian":username;

        return AliyunUtils.getPullStreamPath(streamname);
    }


}

