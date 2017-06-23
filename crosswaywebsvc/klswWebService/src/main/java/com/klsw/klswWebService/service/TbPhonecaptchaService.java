package com.klsw.klswWebService.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.apiCommon.api.mapper.TbCwkphonecaptchaMapper;
import com.klsw.apiCommon.api.model.TbCwkphonecaptcha;
import com.klsw.klswWebService.util.PasswdEncryption;

@Service
public class TbPhonecaptchaService extends _BaseService<TbCwkphonecaptcha>{

	@Autowired
	private TbCwkphonecaptchaMapper tbPhonecaptchaMapper;
	
	/**
	 * 向数据库存入密文验证码
	 * @param username
	 * 			提交注册的用户名
	 * @param captcha
	 * 			手机验证码
	 * @return
	 */
	public boolean saveCaptcha(String username,String captcha){
		TbCwkphonecaptcha storecaptcha = new TbCwkphonecaptcha();
		storecaptcha.setCaptcha(PasswdEncryption.storeCaptcha(username, captcha));
		storecaptcha.setCreatetime(new Date());
		storecaptcha.setPhonenum(username);
		try {
			super.insertUseGeneratedKeys(storecaptcha);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 验证用户注册验证码是否匹配
	 * 
	 * @param storeCaptcha
	 * @return
	 */
	public boolean selectBystoreCaptcha(String storeCaptcha,String phonenum){
		TbCwkphonecaptcha captcha = new TbCwkphonecaptcha();
		captcha.setCaptcha(storeCaptcha);
		captcha.setPhonenum(phonenum);
		try {
			captcha = super.selectOne(captcha);
			if(captcha!=null){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
}
