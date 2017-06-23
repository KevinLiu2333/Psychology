package com.klsw.apiCommon.api.mapper;

import com.klsw.apiCommon.api.MyMapper;
import com.klsw.apiCommon.api.model.TbHomework;

import java.util.List;

public interface TbHomeworkMapper extends MyMapper<TbHomework> {

    List<TbHomework> homeworkList(Integer CWKID);

    List<TbHomework> corected(Integer CWKID);

    List<TbHomework> PMhomeworkList(Integer cwkId, String uid);
	List<TbHomework> loadMID(String MIDName, String studentTime,String studentName);
    
}