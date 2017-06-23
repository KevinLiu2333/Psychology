package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.mapper.TbCwkmessageMapper;
import com.klsw.apiCommon.api.model.TbCwkmessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbCwkmessageService extends _BaseService<TbCwkmessage> {

    @Autowired
    TbCwkmessageMapper tbCwkmessageMapper;

    public List<TbCwkmessage> messageList(Integer messageType, Integer cwkId) {
        return tbCwkmessageMapper.messageList(messageType, cwkId);
    }
    
    public TbCwkmessage selectByMessageId(Integer messageId) {
    	return tbCwkmessageMapper.selectByMessageId(messageId);
    }

}
