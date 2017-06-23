
package com.klsw.pianoCommon.api.mapper;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbCwk;

public interface TbCwkMapper extends MyMapper<TbCwk> {
	
	public Integer selectId(String username,String type);

}