package com.klsw.crosswaylive.service;

import com.klsw.common.live.mapper.TbLivefollowMapper;
import com.klsw.common.live.model.TbLivefollow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户关注相关
 * Created by liukun on 2017/5/15.
 */
@Service
public class FollowService extends _BaseService<TbLivefollow> {
    @Resource
    private TbLivefollowMapper livefollowsMapper;

    public List<Map<String, Object>> selectFollows(Integer status, Integer userId, String type) {
        if (status == 0) {
            return livefollowsMapper.selectFollows(userId, type);
        } else if (status == 1) {
            return livefollowsMapper.selectFans(userId, type);
        }
        return null;
    }
}
