package com.klsw.piano.service;

import com.klsw.pianoCommon.api.mapper.TbPmuserMapper;
import com.klsw.pianoCommon.api.model.TbPmuser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liukun on 2016/11/3.
 */
@Service
public class TbPmUserService extends _BaseService<TbPmuser>{
    @Resource
    TbPmuserMapper tbPmuserMapper;
    public TbPmuser findByName(String username){
        return tbPmuserMapper.findByName(username);
    }

}
