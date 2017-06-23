package com.klsw.piano.service.database;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klsw.piano.service._BaseService;
import com.klsw.pianoCommon.api.mapper.DbCountMapper;
import com.klsw.pianoCommon.api.mapper.DbOpernMapper;
import com.klsw.pianoCommon.api.model.DbCount;
import com.klsw.pianoCommon.api.model.DbOpern;

@Service
public class OpernService extends _BaseService<DbOpern> {
	
	@Autowired
	private DbOpernMapper opernMapper;
	
	@Autowired
	private DbCountMapper countMapper;
	
	
	public List<DbOpern> getCollection(Integer userId) {
		return opernMapper.getCollection(userId);
	}
	

	/**
	 * 通过曲谱名搜索曲谱
	 * @param opernName
	 * @return
	 */
	public List<DbOpern> searchOperns(String opernName) {
		return opernMapper.searchOperns(opernName);
	}
	
	/**
	 * 根据曲谱列表改变统计表中统计次数
	 * @param opernList
	 */
	public void addSearchNum(List<DbOpern> opernList) {
		DbCount dbCount = null;
		
		for(DbOpern opern : opernList) {
			dbCount = new DbCount();
			dbCount.setName(opern.getMainTitle());
			dbCount.setAssistant(opern.getAssistantTitle());
			dbCount.setLabel(opern.getOpernLabel());
			
			dbCount = countMapper.selectOne(dbCount);
			if(dbCount == null) {
				dbCount = new DbCount();
				dbCount.setType(0);
				dbCount.setName(opern.getMainTitle());
				dbCount.setCount(1);
				dbCount.setAddTime(new Date());
				dbCount.setLabel(opern.getOpernLabel());
				dbCount.setPath(opern.getOpernPath());
				countMapper.insert(dbCount);
				continue;
			}
			
			dbCount.setCount(dbCount.getCount() + 1);
			dbCount.setModifyTime(new Date());
			countMapper.updateByPrimaryKeySelective(dbCount);
		}
	}
	
	public List<HashMap<String, Object>> getOpernList(Integer userId, String opernLabel) {
		return opernMapper.getOpernList(userId, opernLabel);
	}
}
