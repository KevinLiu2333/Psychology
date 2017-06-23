package com.klsw.klswWebService.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkmessage;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.service.TbCwkmessageService;
import com.klsw.klswWebService.util.Constants;
import com.klsw.klswWebService.util.MessageDomainConfig;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//import com.klsw.klswWebService.service.SettingService;

@Controller
@RequestMapping(value = "/set")
public class SettingController {
	
	@Autowired
	private MessageDomainConfig messageDomainConfig;

	@Autowired
	private TbCwkService cwkService;

	@Autowired
	private TbCwkmessageService tbCwkmessageService;

	// @Autowired
	// private SettingService settingService;
	private static Logger log = Logger.getLogger(SettingController.class);

	/****
	 * 接口：设置消息开关
	 *
	 * @param id
	 * @param systemButton
	 * @param messageButton
	 * @return
	 */
	@RequestMapping(value = "setSwitch")
	@ResponseBody
	public Ret saveMessageButton(

			@RequestParam("systemButton") Boolean systemButton, @RequestParam("messageButton") Boolean messageButton,
			@RequestParam("username") String username) {
		try {

			TbCwk cwk = cwkService.findByName(username);
			if (cwk == null) {
				return Ret.warn("用戶不存在");
			}

			TbCwk tbCwk = cwkService.selectByPrimaryKey(cwk.getId());
			tbCwk.setMessageButton(messageButton);
			tbCwk.setSystemButton(systemButton);
			cwkService.updateByPrimaryKey(tbCwk);
			return Ret.success();
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			return Ret.error("发生异常");
		}
	}

	/****
	 * 接口：查询消息
	 *
	 * @return
	 */
	@RequestMapping(value = "messageList")
	@ResponseBody
	public Ret notify(@RequestParam("username") String username, 
					  @RequestParam("messageType") Integer messageType,
					  @RequestParam("pageNum")Integer pageNum) {
		TbCwk tbCwk = cwkService.findByName(username);
		if (tbCwk == null) {
			return Ret.warn("用戶不存在");
		}
		List<TbCwkmessage> cwkmessageList = null;
		List<TbCwkmessage> newList = Lists.newArrayList();
		PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
		PageHelper.orderBy("addtime DESC");
		try {
			cwkmessageList = tbCwkmessageService.messageList(messageType, tbCwk.getId());
			if(cwkmessageList != null && cwkmessageList.size() > 0 && !cwkmessageList.isEmpty()) {
				for(TbCwkmessage mCwkmessage : cwkmessageList) {
					mCwkmessage.setAddress(messageDomainConfig.getMessageDomain() + mCwkmessage.getAddress() + mCwkmessage.getId());
					newList.add(mCwkmessage);
				}
			}
			PageInfo<TbCwkmessage> pageInfo = new PageInfo<TbCwkmessage>(newList);
			return Ret.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("msg",e);
			return Ret.error("查询错误");
		}

	}

	/****
	 * 接口：查询消息详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "messageInfo")
	@ResponseBody
	public Ret systemNotifyDetails(@RequestParam("messageId") Integer messageId) {
		try {
			TbCwkmessage tbCwkmessage = tbCwkmessageService.selectByMessageId(messageId);
			if (tbCwkmessage == null) {
				return Ret.warn("该消息不存在");
			}
			return Ret.success(tbCwkmessage);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("msg",e);
			return Ret.error("获取详情错误");
		}
	}

}
