package com.klsw.website.service.background;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.klsw.common.mall.mapper.BgAuthorityMapper;
import com.klsw.common.mall.model.BgAuthority; 
import com.klsw.website.service._BaseService;
import com.klsw.website.util.Constants;
@Service
public class Bg_AuthorityService  extends _BaseService<BgAuthority> {

	@Autowired
	private BgAuthorityMapper bgAuthorityMapper;
	
	public List<BgAuthority> selectByLikeName(String name) {
		List<BgAuthority> bgAuthorityList = Lists.newArrayList();
		String[] roles = Constants.ROLE_AUTHORITY;
		BgAuthority entity = new BgAuthority();
		BgAuthority bgAuthority = null;
		for(int i=1;i<15;i++) {
			if(i<12) {
				String str = name + roles[i];
				entity.setAuthorityid(str);
				bgAuthority = bgAuthorityMapper.selectOne(entity);
				if(bgAuthority != null) {
					bgAuthorityList.add(bgAuthority);
				}
			} else {
				String str = name + i;
				entity.setAuthorityid(str);
				bgAuthority = bgAuthorityMapper.selectOne(entity);
				if(bgAuthority != null) {
					bgAuthorityList.add(bgAuthority);
				}
			}
		}
		return bgAuthorityList;
	}
}
