package com.klsw.klswWebService.service;

import com.klsw.apiCommon.api.mapper.TbLiveroomMapper;
import com.klsw.apiCommon.api.model.TbLiveroom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    public List<TbLiveroom> selectLiveroomList(TbLiveroom liveroom) {
        return liveroomMapper.selectLiveroomList(liveroom);
    }

    public List<TbLiveroom> selectByTeacherId(Integer teacherId) {
        return liveroomMapper.selectByTeacherId(teacherId);
    }
}
