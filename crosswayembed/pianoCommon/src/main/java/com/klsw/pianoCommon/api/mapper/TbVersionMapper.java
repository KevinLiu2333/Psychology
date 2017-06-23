package com.klsw.pianoCommon.api.mapper;

import com.klsw.pianoCommon.api.MyMapper;
import com.klsw.pianoCommon.api.model.TbVersion;

import java.util.List;

public interface TbVersionMapper extends MyMapper<TbVersion> {

    List<TbVersion> selectLaterVersion(TbVersion tbVersion);
    List<TbVersion> selectLaterVersion2(TbVersion tbVersion);
    List<TbVersion> selectHistoryVersion();
    List<TbVersion> selectHistoryVersion2();

}