package com.klsw.klswWebService.service;

import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.apiCommon.api.mapper.TbCwkhistorysignMapper;
import com.klsw.apiCommon.api.model.TbCwkhistorysign;
import com.klsw.apiCommon.api.model.TbCwkhomeworksign;
import com.klsw.klswWebService.util.Constants;

@Service
public class HistorySignService extends _BaseService<TbCwkhistorysign> {
	@Autowired
	private TbCwkhistorysignMapper cwkhistorysignMapper;
	
	public List<HashMap<String, Object>> selectSignedList(Integer id) {
		return cwkhistorysignMapper.selectSignedList(id);
	}
	
	public int insertByHomeworkSign(TbCwkhomeworksign tbCwkhomeworksign) {
		TbCwkhistorysign hCwkhistorysign = new TbCwkhistorysign();
		hCwkhistorysign.setAddtime(tbCwkhomeworksign.getAddtime());
		hCwkhistorysign.setCwkstudentid(tbCwkhomeworksign.getCwkstudentid());
		hCwkhistorysign.setCwkteacherid(tbCwkhomeworksign.getCwkteacherid());
		hCwkhistorysign.setEndtime(tbCwkhomeworksign.getEndtime());
		hCwkhistorysign.setIsfirstsign(tbCwkhomeworksign.getIsfirstsign());
		hCwkhistorysign.setIsoverdue(tbCwkhomeworksign.getIsoverdue());
		hCwkhistorysign.setMonths(tbCwkhomeworksign.getMonths());
		hCwkhistorysign.setNickname(tbCwkhomeworksign.getNickname());
		hCwkhistorysign.setOneprice(tbCwkhomeworksign.getOneprice());
		hCwkhistorysign.setScore(tbCwkhomeworksign.getScore());
		hCwkhistorysign.setTimesperday(tbCwkhomeworksign.getTimesperday());
		hCwkhistorysign.setTimestoday(tbCwkhomeworksign.getTimestoday());
		hCwkhistorysign.setTotalprice(tbCwkhomeworksign.getTotalprice());
		hCwkhistorysign.setType(tbCwkhomeworksign.getType());
		hCwkhistorysign.setUsedcount(tbCwkhomeworksign.getUsedcount());
		
		return cwkhistorysignMapper.insertSelective(hCwkhistorysign);
	}
	
	public int updateByHomeworkSign(TbCwkhomeworksign tbCwkhomeworksign) {
		TbCwkhistorysign cwkhistorysign = new TbCwkhistorysign();
		cwkhistorysign.setCwkstudentid(tbCwkhomeworksign.getCwkstudentid());
		cwkhistorysign.setCwkteacherid(tbCwkhomeworksign.getCwkteacherid());
		cwkhistorysign.setType(tbCwkhomeworksign.getType());
		cwkhistorysign.setIsoverdue(tbCwkhomeworksign.getIsoverdue());
		cwkhistorysign = cwkhistorysignMapper.selectOne(cwkhistorysign);
		
		if(cwkhistorysign != null) {
			if(cwkhistorysign.getType() == 1) {
				cwkhistorysign.setUsedcount(1);
				cwkhistorysign.setIsoverdue(true);
			} else {
				cwkhistorysign.setUsedcount(cwkhistorysign.getUsedcount() + 1);
				cwkhistorysign.setTimestoday(cwkhistorysign.getTimestoday() - 1);
				if(cwkhistorysign.getUsedcount() >= Constants.MONTH_COUNT) {
					cwkhistorysign.setIsoverdue(true);
				}
			}
		}
		return cwkhistorysignMapper.updateByPrimaryKeySelective(cwkhistorysign);
	}
}
