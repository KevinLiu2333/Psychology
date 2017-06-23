package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.mapper.TbGamenotebattleMapper;
import com.klsw.apiCommon.api.mapper.TbNbsongsMapper;
import com.klsw.apiCommon.api.mapper.TbNbtoolsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by liukun on 2017/5/6.
 * 音符大作战相关
 */
@Service
public class NoteBattleService {
    @Resource
    private TbGamenotebattleMapper tbGamenotebattleMapper;

    @Resource
    private TbNbsongsMapper tbNbsongsMapper;

    @Resource
    private TbNbtoolsMapper tbNbtoolsMapper;

    public TbGamenotebattleMapper getTbGamenotebattleMapper() {
        return tbGamenotebattleMapper;
    }

    public void setTbGamenotebattleMapper(TbGamenotebattleMapper tbGamenotebattleMapper) {
        this.tbGamenotebattleMapper = tbGamenotebattleMapper;
    }

    public TbNbsongsMapper getTbNbsongsMapper() {
        return tbNbsongsMapper;
    }

    public void setTbNbsongsMapper(TbNbsongsMapper tbNbsongsMapper) {
        this.tbNbsongsMapper = tbNbsongsMapper;
    }

    public TbNbtoolsMapper getTbNbtoolsMapper() {
        return tbNbtoolsMapper;
    }

    public void setTbNbtoolsMapper(TbNbtoolsMapper tbNbtoolsMapper) {
        this.tbNbtoolsMapper = tbNbtoolsMapper;
    }
}
