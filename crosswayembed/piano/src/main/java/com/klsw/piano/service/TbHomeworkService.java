package com.klsw.piano.service;

import com.klsw.pianoCommon.api.mapper.TbHomeworkMapper;
import com.klsw.pianoCommon.api.model.TbHomework;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 
 * ClassName: TbHomeworkService 
 * @Description: 琴上作业
 * @author LiuKun
 * @date 2016年9月5日
 */
@Service
public class TbHomeworkService extends _BaseService<TbHomework>{
	
	@Resource
	TbHomeworkMapper tbHomeworkMapper;

	public List<TbHomework> PMhomeworkList(Integer cwkId, String uid) {
		return tbHomeworkMapper.PMhomeworkList(cwkId, uid);
	}
	

	public List<TbHomework> loadMID(String MIDName, String studentTime,String endTime,String studentName) {
		return tbHomeworkMapper.loadMID(MIDName, studentTime,endTime,studentName);
	}

}





