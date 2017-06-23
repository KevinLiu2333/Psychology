package com.klsw.piano.service;

import com.klsw.pianoCommon.api.mapper.WxTokenMapper;
import com.klsw.pianoCommon.api.model.WxToken;
import org.springframework.stereotype.Service;

/**
 * Created by liukun on 2016/11/15.
 */
@Service
public class WxTokenService extends _BaseService<WxToken>{
    WxTokenMapper wxTokenMapper;
}
