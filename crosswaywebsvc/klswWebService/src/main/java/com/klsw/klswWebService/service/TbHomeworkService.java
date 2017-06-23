package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.mapper.TbHomeworkMapper;
import com.klsw.apiCommon.api.model.TbHomework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: TbHomeworkService
 *
 * @author LiuKun
 * @Description: 琴上作业
 * @date 2016年9月5日
 */
@Service
public class TbHomeworkService extends _BaseService<TbHomework> {

    @Autowired
    TbHomeworkMapper tbHomeworkMapper;

    public List<TbHomework> homeworkList(Integer CWKID) {
        return tbHomeworkMapper.homeworkList(CWKID);
    }

    public List<TbHomework> corected(Integer CWKID) {
        return tbHomeworkMapper.corected(CWKID);
    }

    public List<TbHomework> PMhomeworkList(Integer cwkId, String uid) {
        return tbHomeworkMapper.PMhomeworkList(cwkId, uid);
    }
}





