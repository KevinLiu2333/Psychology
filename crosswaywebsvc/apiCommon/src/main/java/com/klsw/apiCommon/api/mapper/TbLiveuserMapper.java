package com.klsw.apiCommon.api.mapper;


import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbLiveuser;

import java.util.List;

public interface TbLiveuserMapper extends MyMapper<TbLiveuser> {

    List<TbLiveuser> selectTeacherListByliveroomInfo(Integer status, Integer level);

}