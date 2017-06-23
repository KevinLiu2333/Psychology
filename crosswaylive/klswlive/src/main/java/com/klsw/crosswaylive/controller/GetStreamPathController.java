package com.klsw.crosswaylive.controller;

import com.klsw.common.live.model.Ret;
import com.klsw.common.live.model.TbLiveuser;
import com.klsw.crosswaylive.service.TbLiveUserService;
import com.klsw.crosswaylive.util.AliyunUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class GetStreamPathController {

    @Resource
    private TbLiveUserService userService;

    /**
     * 获取推流地址
     *
     * @param request 请求
     * @return 推流地址
     */
    @RequestMapping(value = "/getPushStreamPath")
    @ResponseBody
    public Ret getPushStreamPath(HttpServletRequest request) {
        Integer userId;
        userId = Integer.valueOf(request.getParameter("userId"));
        try {
            TbLiveuser liveuser = userService.selectByPrimaryKey(userId);
            String pushStreamPath = AliyunUtils.getPushStreamPath(liveuser.getUsername());
            return Ret.success("pushStreamPath", pushStreamPath);
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("获取推流地址失败");
        }
    }

    /**
     * 获取拉流地址
     *
     * @param request 请求
     * @return 拉流地址
     */
    @RequestMapping(value = "/getPullStreamPath")
    @ResponseBody
    public Ret createPullStreamPath(HttpServletRequest request) {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        TbLiveuser liveuser;
        try {
            liveuser = userService.selectByPrimaryKey(userId);
            String pullStreamPath = AliyunUtils.getPullStreamPath(liveuser.getUsername());
            return Ret.success("pullStreamPath", pullStreamPath);
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("获取拉流地址失败");
        }

    }
}



