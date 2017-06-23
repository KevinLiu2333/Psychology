package com.klsw.common.live.mapper;

import com.klsw.common.live.MyMapper;
import com.klsw.common.live.model.TbLivefollow;

import java.util.List;
import java.util.Map;

public interface TbLivefollowMapper extends MyMapper<TbLivefollow> {
    /**
     * 根据用户id查询关注列表
     *
     * @param userId   用户id
     * @param userType 用户类型
     * @return 列表
     */
    List<Map<String, Object>> selectFollows(Object userId, Object userType);

    /**
     * 查询关注过为自己的人的列表
     *
     * @param userId   用户id
     * @param userType 用户类型
     * @return 列表
     */
    List<Map<String, Object>> selectFans(Object userId, Object userType);
}