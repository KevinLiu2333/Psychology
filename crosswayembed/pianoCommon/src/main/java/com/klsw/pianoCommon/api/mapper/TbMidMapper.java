package com.klsw.pianoCommon.api.mapper;

import java.util.List;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbMid;

public interface TbMidMapper extends MyMapper<TbMid> {
	public List<TbMid> selectByNotNull();
}