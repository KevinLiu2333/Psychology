package com.klsw.website.service.background;
 
import org.springframework.stereotype.Service;

import com.klsw.common.mall.model.BgRole; 
import com.klsw.website.service._BaseService;
@Service
public class Bg_RoleService  extends _BaseService<BgRole>{

	
	public BgRole selectBySerial(Integer roleId) throws Exception {
		BgRole role = new BgRole();
		role.setRoleid(roleId);
		return super.selectOne(role);
		
	}
}
