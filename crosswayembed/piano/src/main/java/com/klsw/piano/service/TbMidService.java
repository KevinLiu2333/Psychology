package com.klsw.piano.service;

import com.klsw.pianoCommon.api.mapper.TbMidMapper;
import com.klsw.pianoCommon.api.model.TbMid;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbMidService extends _BaseService<TbMid> {

	@Resource
	private TbMidMapper tbMidMapper;
	
	public List<TbMid> selectByNotNull() {
		return tbMidMapper.selectByNotNull();
	}
}
