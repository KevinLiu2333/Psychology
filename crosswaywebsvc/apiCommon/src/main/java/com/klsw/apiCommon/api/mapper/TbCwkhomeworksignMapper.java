package com.klsw.apiCommon.api.mapper;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbCwkhomeworksign;

import java.util.HashMap;
import java.util.List;

public interface TbCwkhomeworksignMapper extends MyMapper<TbCwkhomeworksign> {

    List<HashMap<String, Object>> signedList(Integer studentId);
}