package com.klsw.pianoCommon.api.mapper;


import java.util.List;


import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbSerialnumbers;

public interface TbSerialnumbersMapper extends MyMapper<TbSerialnumbers> {
	
	public List<TbSerialnumbers> selectLikeName(String searchStr,String serialType) throws Exception;
	
}