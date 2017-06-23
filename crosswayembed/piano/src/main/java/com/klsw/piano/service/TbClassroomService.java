package com.klsw.piano.service;

import com.klsw.pianoCommon.api.mapper.TbClassroomMapper;
import com.klsw.pianoCommon.api.model.TbClassroom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbClassroomService extends _BaseService<TbClassroom> {
    @Resource
    TbClassroomMapper tbClassroomMapper;

    public List<TbClassroom> selectClassroomList(Integer typeId) {
        return tbClassroomMapper.selectClassroomList(typeId);
    }
}
