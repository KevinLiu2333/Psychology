package com.klsw.apiCommon.api.mapper;

import java.util.HashMap;
import java.util.List;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbCwkhistorysign;

public interface TbCwkhistorysignMapper extends MyMapper<TbCwkhistorysign> {
	
	List<HashMap<String, Object>> selectSignedList(Integer id);
}