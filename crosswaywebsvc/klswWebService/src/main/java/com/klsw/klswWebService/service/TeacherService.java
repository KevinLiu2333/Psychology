package com.klsw.klswWebService.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klsw.apiCommon.api.mapper.TbCwkteacherMapper;
import com.klsw.apiCommon.api.model.TbCwkteacher;
import com.klsw.apiCommon.api.model.WKTeacher;


@Service
public class TeacherService extends _BaseService<TbCwkteacher> {
    @Autowired
    private TbCwkteacherMapper cwkteacherMapper;

    public int selectId(Integer cwkId) {
        return cwkteacherMapper.selectId(cwkId);
    }

    public HashMap<String, Object> selectTeacherInfo(Integer teacherId) {
        return cwkteacherMapper.selectTeacherInfo(teacherId);
    }

    public TbCwkteacher selectByUserId(Integer userId) {
        TbCwkteacher teacher = new TbCwkteacher();
        teacher.setCwkid(userId);
        return cwkteacherMapper.selectOne(teacher);
    }

    public List<WKTeacher> selectTeacherList() {
        return cwkteacherMapper.selectTeacherList();
    }
    
    public int insertByUserId(Integer userId) {
    	TbCwkteacher tbCwkteacher = new TbCwkteacher();
    	tbCwkteacher.setCwkid(userId);
    	return cwkteacherMapper.insert(tbCwkteacher);
    }
}
