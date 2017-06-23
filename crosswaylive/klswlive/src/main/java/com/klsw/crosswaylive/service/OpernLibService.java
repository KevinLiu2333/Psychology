package com.klsw.crosswaylive.service;

import com.klsw.pianoCommon.api.mapper.DbOpernMapper;
import com.klsw.pianoCommon.api.model.DbOpern;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/1
 * Time: 10:39
 */
@Service
public class OpernLibService extends _BaseService2<DbOpern> {

    @Resource
    private DbOpernMapper mapper;


    public List<DbOpern> getCollection(Integer userId) {
        return mapper.getCollection(userId);
    }
}
