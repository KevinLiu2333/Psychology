package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.mapper.TbCwkhomeworksignMapper;
import com.klsw.apiCommon.api.model.TbCwkhomeworksign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class HomeworkSignService extends _BaseService<TbCwkhomeworksign> {

    @Autowired
    TbCwkhomeworksignMapper tbCwkhomeworksignMapper;


    public List<HashMap<String, Object>> signedList(Integer cwkId) {
        return tbCwkhomeworksignMapper.signedList(cwkId);
    }


}
