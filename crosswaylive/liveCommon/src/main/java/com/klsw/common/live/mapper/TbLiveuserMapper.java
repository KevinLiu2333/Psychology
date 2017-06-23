package com.klsw.common.live.mapper;

import com.klsw.common.live.MyMapper;
import com.klsw.common.live.model.TbLiveuser;

import java.util.List;

public interface TbLiveuserMapper extends MyMapper<TbLiveuser> {

    List<TbLiveuser> selectTeacherListByliveroomInfo(Integer status, Integer level);

}