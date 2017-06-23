package com.klsw.pianoCommon.api.mapper;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbHomework;
import java.util.List;

public interface TbHomeworkMapper extends MyMapper<TbHomework> {

    List<TbHomework> PMhomeworkList(Integer cwkId, String uid);

	List<TbHomework> loadMID(String MIDName, String studentTime,String endTime,String studentName);

}