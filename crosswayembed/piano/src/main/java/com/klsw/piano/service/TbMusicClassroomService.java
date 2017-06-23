package com.klsw.piano.service;

import com.klsw.pianoCommon.api.mapper.TbMusicclassroomVersionMapper;
import com.klsw.pianoCommon.api.model.TbMusicclassroomVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liukun on 2017/3/4.
 * 音乐教室
 */
@Service
public class TbMusicClassroomService extends _BaseService<TbMusicclassroomVersion> {

    @Autowired
    private TbMusicclassroomVersionMapper mapper;

    public TbMusicclassroomVersion getVersion() throws Exception {
        return mapper.getVersion();
    }

}
