package com.klsw.apiCommon.api.mapper;


import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.DbOpern;

import java.util.HashMap;
import java.util.List;

public interface DbOpernMapper extends MyMapper<DbOpern> {
	List<DbOpern> searchOperns(String opernName);
	List<DbOpern> getCollection(Integer userId);
	List<HashMap<String, Object>> getOpernList(Integer userId, String opernLabel);
}