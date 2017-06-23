package com.klsw.pianoCommon.api.mapper;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbPmuser;

public interface TbPmuserMapper extends MyMapper<TbPmuser>{

    TbPmuser findByName(String username);
}