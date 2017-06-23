package com.klsw.piano.service;

import com.klsw.pianoCommon.api.mapper.TbGamebubbleMapper;
import com.klsw.pianoCommon.api.model.TbGamebubble;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liukun on 2016/12/12.
 */
@Service
public class TbGameBubbleService extends _BaseService<TbGamebubble> {
    @Resource
    TbGamebubbleMapper tbGamebubbleMapper;

    public List<TbGamebubble> selectInfoList() {
        return tbGamebubbleMapper.selectInfoList();
    }

    public Integer selectRank(Integer score) {
        return tbGamebubbleMapper.selectRank(score);
    }
}
