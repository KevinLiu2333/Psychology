package com.klsw.piano.service.SIDPIDManage;

import com.klsw.piano.service._BaseService;
import com.klsw.pianoCommon.api.mapper.TbAgencyMapper;
import com.klsw.pianoCommon.api.model.TbAgency;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbAgencyService extends _BaseService<TbAgency> {
	@Resource
	private TbAgencyMapper agencyMapper;

	public List<TbAgency> selectByLikeName(String searchStr) {
		return agencyMapper.selectByLikeName(searchStr);
	}
}
