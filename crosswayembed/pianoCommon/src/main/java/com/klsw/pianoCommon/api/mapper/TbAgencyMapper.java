package com.klsw.pianoCommon.api.mapper;

import java.util.List;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbAgency;

public interface TbAgencyMapper extends MyMapper<TbAgency> {
	
	public List<TbAgency> selectByLikeName(String searchStr);
}