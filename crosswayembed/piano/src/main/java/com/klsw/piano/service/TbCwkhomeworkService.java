package com.klsw.piano.service;

import com.klsw.pianoCommon.api.mapper.TbCwkhomeworkMapper;
import com.klsw.pianoCommon.api.model.TbCwkhomework;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liukun on 2016/11/16.
 */
@Service
public class TbCwkhomeworkService extends _BaseService<TbCwkhomework> {

    @Resource
    private TbCwkhomeworkMapper tbCwkhomeworkMapper;

    public Integer selectGradeCount(int homeworkId) {
        return tbCwkhomeworkMapper.selectGradeCount(homeworkId);
    }


    /**
     * 通过作业Id查询所有已批改记录
     *
     * @param homeworkId
     * @return
     */
    public List<TbCwkhomework> homeworkInfolist(Integer homeworkId) {
        return tbCwkhomeworkMapper.homeworkInfolist(homeworkId);
    }

}
