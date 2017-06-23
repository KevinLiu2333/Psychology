package com.klsw.klswWebService.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbLivefollow;
import com.klsw.klswWebService.service.FollowService;
import com.klsw.klswWebService.service.TbCwkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 关注相关
 * Created by liukun on 2017/5/17.
 */
@RestController
@RequestMapping("follow")
public class FollowController {
    private static Logger log = LoggerFactory.getLogger(FollowController.class);

    @Resource
    private FollowService followService;

    @Resource
    private TbCwkService cwkService;

    /**
     * 关注用户
     *
     * @param userId   用户id
     * @param followId 被关注人id
     * @return 返回信息
     */
    @RequestMapping
    public Ret follow(@RequestParam("userId") Integer userId,
                      @RequestParam("followId") Integer followId) {
        if (followId.equals(userId)) {
            return Ret.error("关注信息有误");
        }
        TbCwk user, follow;
        TbLivefollow livefollow = new TbLivefollow();
        try {
            livefollow.setFollowid(followId);
            livefollow.setUserid(userId);
            if (followService.selectCount(livefollow) > 0) {
                return Ret.error("您已经关注过了");
            }
            user = cwkService.selectByPrimaryKey(userId);
            follow = cwkService.selectByPrimaryKey(followId);
            if (user == null || follow == null) {
                return Ret.error("用户信息有误");
            } else if ("tea".equals(user.getType()) && "stu".equals(follow.getType())) {
                return Ret.warn("教师不允许关注学生");
            }
            livefollow = new TbLivefollow();
            livefollow.setCtime(new Date());
            livefollow.setUserid(userId);
            livefollow.setFollowid(followId);
            followService.insert(livefollow);
            return Ret.success("S", "关注成功", null);
        } catch (Exception e) {
            log.info("msg", e);
            return Ret.error("出现异常");
        }
    }

    /**
     * 取消关注
     *
     * @param userId   用户id
     * @param followId 被关注者id
     * @return 结果
     */
    @RequestMapping("cancel")
    public Ret cancelFollow(@RequestParam("userId") Integer userId,
                            @RequestParam("followId") Integer followId) {
        TbLivefollow follow = new TbLivefollow();
        follow.setUserid(userId);
        follow.setFollowid(followId);
        try {
            follow = followService.selectOne(follow);
            if (follow != null) {
                followService.delete(follow);
                return Ret.success("取消关注成功");
            }
        } catch (Exception e) {
            log.error("msg", e);
        }
        return Ret.error("取消关注失败");
    }

    /**
     * @param userId   用户id
     * @param pageSize 页数
     * @param pageNum  每页条数
     * @param type     用户类型
     * @param status   1为查询自己的粉丝 0为查询自己关注的人
     * @return 获取关注列表
     */
    @RequestMapping("getFollowList")
    public Ret getFollowList(@RequestParam("userId") Integer userId,
                             @RequestParam("pageSize") Integer pageSize,
                             @RequestParam("pageNum") Integer pageNum,
                             @RequestParam("status") Integer status,
                             @RequestParam("type") String type) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 8 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        //1为查询自己的粉丝
        try {
            List<Map<String, Object>> list = followService.selectFollows(status, userId, type);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
            return Ret.success("pageInfo", pageInfo);
        } catch (Exception e) {
            log.info("msg", e);
            return Ret.error("查询失败");
        }
    }

    /**
     * @param userId   用户id
     * @param followId 被关注人id
     * @return 检查结果
     */
    @RequestMapping("check")
    public Ret checkFollow(@RequestParam("userId") Integer userId,
                           @RequestParam("followId") Integer followId) {
        if (userId.equals(followId)) {
            return Ret.warn("参数有误");
        }
        TbLivefollow livefollow = new TbLivefollow();
        livefollow.setUserid(userId);
        livefollow.setFollowid(followId);
        try {
            if (followService.selectOne(livefollow) == null) {
                return Ret.success("check", false);
            } else {
                return Ret.success("check", true);
            }

        } catch (Exception e) {
            return Ret.success("check", false);
        }
    }
}





