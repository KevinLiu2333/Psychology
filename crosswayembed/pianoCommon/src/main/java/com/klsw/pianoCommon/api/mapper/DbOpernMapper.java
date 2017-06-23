package com.klsw.pianoCommon.api.mapper;


import java.util.HashMap;
import java.util.List;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.DbOpern;

public interface DbOpernMapper extends MyMapper<DbOpern> {
	List<DbOpern> searchOperns(String opernName);
	List<DbOpern> getCollection(Integer userId);
	List<HashMap<String, Object>> getOpernList(Integer userId, String opernLabel);
}