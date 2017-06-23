package com.klsw.apiCommon.api.mapper;



import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbLivefollow;
import org.apache.ibatis.annotations.Param;

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
    List<Map<String, Object>> selectFollows(@Param("userId") Integer userId, @Param("userType") String userType);

    /**
     * 查询关注过为自己的人的列表
     *
     * @param userId   用户id
     * @param userType 用户类型
     * @return 列表
     */
    List<Map<String, Object>> selectFans(@Param("userId") Integer userId, @Param("userType") String userType);
}