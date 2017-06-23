package com.klsw.pianoCommon.api.mapper;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbClassroom;

import java.util.List;

public interface TbClassroomMapper extends MyMapper<TbClassroom> {
    List<TbClassroom> selectClassroomList(Integer typeId);
}