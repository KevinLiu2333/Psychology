package com.klsw.piano.service;

import com.klsw.pianoCommon.api.mapper.TbVersionMapper;
import com.klsw.pianoCommon.api.model.TbVersion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liukun on 2016/11/3.
 * 版本相关
 */
@Service
public class TbVersionService extends _BaseService<TbVersion> {

    @Resource
    TbVersionMapper tbVersionMapper;

    public TbVersion selectByVersionNo(String versionNo) {
        TbVersion tbVersion = new TbVersion();
        tbVersion.setVersionno(versionNo);
        try {
            tbVersion = super.selectOne(tbVersion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbVersion;
    }

    public List<TbVersion> selectLaterVersion(TbVersion tbVersion) {
        return tbVersionMapper.selectLaterVersion(tbVersion);
    }

    /**
     * 
     * @param tbVersion
     * @return
     */
    public List<TbVersion> selectLaterVersion2(TbVersion tbVersion) {
        return tbVersionMapper.selectLaterVersion2(tbVersion);
    }
    
    public List<TbVersion> selectHistoryVersion() {
    	return tbVersionMapper.selectHistoryVersion();
    }
    public List<TbVersion> selectHistoryVersion2() {
    	return tbVersionMapper.selectHistoryVersion2();
    }
}














