package com.klsw.piano.service;


import com.klsw.pianoCommon.api.mapper.TbSerialnumbersMapper;
import com.klsw.pianoCommon.api.model.TbSerialnumbers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbSerialnumbersService extends _BaseService<TbSerialnumbers> {

	@Resource
	private TbSerialnumbersMapper tbSerialnumbersMapper;
	
	
	public List<TbSerialnumbers> selectLikeName(String searchStr,String serialType) throws Exception {
		
		return tbSerialnumbersMapper.selectLikeName(searchStr,serialType);
	}
}
