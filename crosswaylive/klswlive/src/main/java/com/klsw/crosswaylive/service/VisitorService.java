package com.klsw.crosswaylive.service;

import com.klsw.common.live.model.TbLivevisitor;
import org.springframework.stereotype.Service;

/**
 * Created by liukun on 2017/3/22.
 * 游客相关
 */
@Service
public class VisitorService extends _BaseService<TbLivevisitor> {

    public TbLivevisitor selectByName(String username) {
        TbLivevisitor livevisitor = new TbLivevisitor();
        livevisitor.setUsername(username);
        try {
            livevisitor = selectOne(livevisitor);
            return livevisitor;
        } catch (Exception e) {
            return null;
        }
    }
}
