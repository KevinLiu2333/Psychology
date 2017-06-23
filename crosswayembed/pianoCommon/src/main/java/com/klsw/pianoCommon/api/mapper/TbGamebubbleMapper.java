package com.klsw.pianoCommon.api.mapper;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbGamebubble;

import java.util.List;

public interface TbGamebubbleMapper extends MyMapper<TbGamebubble> {

    List<TbGamebubble> selectInfoList();

    Integer selectRank(Integer score);
}