package com.klsw.apiCommon.api.mapper;


import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbLiveroom;

import java.util.List;

public interface TbLiveroomMapper extends MyMapper<TbLiveroom> {

    List<String> selectLivingDate();

    List<TbLiveroom> selectLiveroomByDate(String datestr);

    List<TbLiveroom> selectAppointLiveroom(Integer userId, Integer status);

    List<TbLiveroom> selectAppointLiveroom2(Integer userId);

    Integer selectAppointLiveroomCount(Integer userId, Integer status);

    List<TbLiveroom> selectLiveroomList(TbLiveroom liveroom);

    List<TbLiveroom> selectByTeacherId(Integer teacherId);
}