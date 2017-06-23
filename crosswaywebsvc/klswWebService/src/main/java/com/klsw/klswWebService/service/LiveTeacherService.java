package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.model.TbLiveteacher;
import org.springframework.stereotype.Service;

/**
 * Created by liukun on 2017/5/15.
 * 直播教师
 */
@Service
public class LiveTeacherService extends _BaseService<TbLiveteacher> {

    public TbLiveteacher selectByUserId(Integer userId) {
        TbLiveteacher liveteacher = new TbLiveteacher();
        liveteacher.setUserid(userId);
        try {
            liveteacher = selectOne(liveteacher);
            return liveteacher;
        } catch (Exception e) {
            return null;
        }
    }
}
