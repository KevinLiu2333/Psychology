package com.klsw.pianoCommon.api.mapper;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbCwkhomework;

import java.util.List;

public interface TbCwkhomeworkMapper extends MyMapper<TbCwkhomework> {
    Integer selectGradeCount(Integer homeworkId);

    List<TbCwkhomework> homeworkInfolist(Integer homeworkId);
}