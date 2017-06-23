package com.klsw.website.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klsw.common.mall.model.TMallUser;
import com.klsw.website.util.PasswdEncryption;
 
import tk.mybatis.mapper.entity.Example;

@Service
public class UserService extends _BaseService<TMallUser> {
	public TMallUser userVrtify(String mobile,String password ) throws Exception {
		Example example=new Example(TMallUser.class);
		example.createCriteria().andEqualTo("mobile",mobile);
		List<TMallUser> userlist=selectByExample(example);
		if(userlist!=null){
			for(TMallUser user:userlist){
				if(PasswdEncryption.checkPasswd(password, user.getPassword())) {
					return user;
				}
			}
		}
		return null;
	}
}
