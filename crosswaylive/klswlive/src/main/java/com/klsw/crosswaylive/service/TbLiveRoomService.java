package com.klsw.crosswaylive.service;

import com.klsw.common.live.mapper.TbLiveroomMapper;
import com.klsw.common.live.model.TbLiveroom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liukun on 2017/3/11.
 * 直播间相关
 */
@Service
public class TbLiveRoomService extends _BaseService<TbLiveroom> {

    @Resource
    private TbLiveroomMapper liveroomMapper;

    public List<String> selectLivingDate() {
        return liveroomMapper.selectLivingDate();
    }

    public List<TbLiveroom> selectLiveroomByDate(String datestr) {
        return liveroomMapper.selectLiveroomByDate(datestr);
    }

    public List<TbLiveroom> selectAppointLiveroom(Integer userId, Integer status) {
        if (status == null) {
            return liveroomMapper.selectAppointLiveroom2(userId);
        }
        return liveroomMapper.selectAppointLiveroom(userId, status);
    }

    public Integer selectAppointLiveroomCount(Integer userId, Integer status) {
        return liveroomMapper.selectAppointLiveroomCount(userId, status);
    }
}
