package com.klsw.website.service.background;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klsw.common.mall.model.BgManager;
import com.klsw.website.service._BaseService;
import com.klsw.website.util.PasswdEncryption;

import tk.mybatis.mapper.entity.Example;
/**
 * 后台人员Service
 * @author liukun
 *
 */
@Service
public class Bg_ManagerService extends _BaseService<BgManager>{
	/**
	 * 登录验证校对方法
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public BgManager managerVrtify(String userName,String password ) throws Exception {
		Example example=new Example(BgManager.class);
		example.createCriteria().andEqualTo("username",userName);
		List<BgManager> managerlist=selectByExample(example);
		if(managerlist!=null){
			for(BgManager manager:managerlist){
				if(PasswdEncryption.checkPasswd(password, manager.getUserpassword())) {
					return manager;
				}
			}
		}
		return null;
	}
}
