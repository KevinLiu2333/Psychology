package com.klsw.common.live.mapper;

import com.klsw.common.live.MyMapper;
import com.klsw.common.live.model.TbLiveroom;

import java.util.List;

public interface TbLiveroomMapper extends MyMapper<TbLiveroom> {

    List<String> selectLivingDate();

    List<TbLiveroom> selectLiveroomByDate(String datestr);

    List<TbLiveroom> selectAppointLiveroom(Integer userId, Integer status);

    List<TbLiveroom> selectAppointLiveroom2(Integer userId);

    Integer selectAppointLiveroomCount(Integer userId, Integer status);
}