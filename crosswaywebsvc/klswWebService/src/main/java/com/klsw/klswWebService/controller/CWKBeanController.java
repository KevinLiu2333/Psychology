package com.klsw.klswWebService.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkbean;
import com.klsw.apiCommon.api.model.TbReceivebean;
import com.klsw.klswWebService.service.TbCwkBeanService;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.service.TbReceiveBeanService;
import com.klsw.klswWebService.util.BeanUtil;
import com.klsw.klswWebService.util.Constants;


/**
 * 
 * ClassName: CWKBeanController
 * 
 * @Description: 威豆相关接口
 * @author LiuKun
 * @date 2016年8月25日
 */
@Controller
@RequestMapping(value = "/doBean")
public class CWKBeanController {
	private static final Logger logger = LoggerFactory.getLogger(CWKBeanController.class);

	@Autowired
	private TbCwkService tbCwkService;

	@Autowired
	private TbCwkBeanService tbCwkBeanService;

	@Autowired
	private TbReceiveBeanService receiveService;

	/**
	 * @Description 接口：签到领取威豆功能
	 * @param request
	 * @param name
	 *            用户名：手机号
	 * @param type
	 *            用户类型：学生或老师
	 * @return Ret
	 * @throws @author
	 *             HKJ
	 * @date 2016-8-25
	 */
	@RequestMapping(value = "/getBeans")
	@ResponseBody
	public Ret receiveBeans(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("type") String type) {
		try {
			TbCwk tbCwk = tbCwkService.findByName(username, type);// 获取威客用户
			Integer id = tbCwk.getId();// 获取用户id
			Date date = new Date();// 获取系统当前时间

			/*** 插入或更新威豆领取登记表开始 ***/
			TbReceivebean entity_receiveBean = new TbReceivebean();
			entity_receiveBean.setCwkid(id);
			TbReceivebean tbReceiveBean = receiveService.selectOne(entity_receiveBean);

			/*** 根据查询结果判断是新插入一条记录还是更新一条记录 ***/
			if (tbReceiveBean == null) { //从未领取过威豆
				//在威豆签到领取表里加入一条领取记录
				entity_receiveBean.setBeans(5);
				entity_receiveBean.setContinuous(1);
				entity_receiveBean.setTime(date);
				receiveService.insert(entity_receiveBean);

				// 插入一条威豆交易记录
				TbCwkbean tbCwkbean = new TbCwkbean();
				tbCwkbean.setCwkid(id);
				tbCwkbean.setAddtime(date);
				tbCwkbean.setChangenumber(5);
				tbCwkbean.setChangetype(1);
				tbCwkbean.setDescribtion("签到领取威豆");
				tbCwkbean.setRemainbean(tbCwk.getCwkbeancount() + 5);
				tbCwkBeanService.insert(tbCwkbean);
				
				//改变威客用户威豆余额
				tbCwk.setCwkbeancount(tbCwk.getCwkbeancount() + 5);
				tbCwkService.updateByPrimaryKey(tbCwk);
				
			} else { //领取过威豆，更新威豆领取表里的记录
				Date oldDate = tbReceiveBean.getTime();// 获取上次领取时间
				Integer days = BeanUtil.days(oldDate, date);// 获取两次领取相差的天数
				if (days == 0) {// 当天
					return Ret.warn("你今天已经领取过了");
				} else if (days == 1) {// 隔1天，连续
					// 更新威豆签到领取表
					Integer continuous = tbReceiveBean.getContinuous();// 获取连续领取天数
					continuous += 1;
					tbReceiveBean.setContinuous(continuous);
					if (continuous == 2) {
						tbReceiveBean.setBeans(10);
					} else {
						tbReceiveBean.setBeans(20);
					}
					tbReceiveBean.setTime(date);
					receiveService.updateByPrimaryKey(tbReceiveBean);

					// 插入一条威豆交易记录
					TbCwkbean tbCwkbean = new TbCwkbean();
					tbCwkbean.setCwkid(id);
					tbCwkbean.setChangenumber(tbReceiveBean.getBeans());
					tbCwkbean.setChangetype(1);
					tbCwkbean.setAddtime(date);
					tbCwkbean.setDescribtion("签到领取威豆");
					tbCwkbean.setRemainbean(tbCwk.getCwkbeancount() + tbReceiveBean.getBeans());
					tbCwkBeanService.insert(tbCwkbean);
					
					//改变威客用户威豆余额
					tbCwk.setCwkbeancount(tbCwk.getCwkbeancount() + tbReceiveBean.getBeans());
					tbCwkService.updateByPrimaryKey(tbCwk);
					
				} else {// 隔2天及以上，不连续
					// 更新威豆签到领取表
					tbReceiveBean.setContinuous(1);
					tbReceiveBean.setBeans(5);
					tbReceiveBean.setTime(date);
					receiveService.updateByPrimaryKey(tbReceiveBean);

					// 插入一条威豆交易记录
					TbCwkbean tbCwkbean = new TbCwkbean();
					tbCwkbean.setCwkid(id);
					tbCwkbean.setChangenumber(tbReceiveBean.getBeans());
					tbCwkbean.setAddtime(date);
					tbCwkbean.setChangetype(1);
					tbCwkbean.setDescribtion("签到领取威豆");
					tbCwkbean.setRemainbean(tbCwk.getCwkbeancount() + 5);
					tbCwkBeanService.insert(tbCwkbean);
					
					//改变威客用户威豆余额
					tbCwk.setCwkbeancount(tbCwk.getCwkbeancount() + 5);
					tbCwkService.updateByPrimaryKey(tbCwk);
				}
			}
			/*** 插入或更新威豆领取登记表结束 ***/

			TbReceivebean receiveBean = new TbReceivebean();
			receiveBean.setCwkid(tbCwk.getId());
			receiveBean = receiveService.selectOne(receiveBean);
			tbCwk = tbCwkService.selectOne(tbCwk);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("continuous", receiveBean.getContinuous());
			map.put("remainBean", tbCwk.getCwkbeancount());
			Integer tomorrowBean = null;
			if(receiveBean.getContinuous() == 0) {
				tomorrowBean = 5;
			} else if(receiveBean.getContinuous() == 1){
				tomorrowBean = 10;
			} else {
				tomorrowBean = 20;
			}
			map.put("tomorrowBean", tomorrowBean);
			return Ret.success("S","签到成功",map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return Ret.error("发生异常");
		}
	}

	/**
	 * 接口：获取威豆账户功能
	 * 
	 * @param request
	 * @param username
	 * @param type
	 * @return
	 * @author HKJ
	 */
	@RequestMapping(value = "getAccount")
	@ResponseBody
	public Ret account(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("type") String type) {
		// 查询威客用户
		TbCwk tbCwk = tbCwkService.findByName(username);
		String message = "";


		try {
			// 查询威客用户有无签到记录
			TbReceivebean tbReceivebean = new TbReceivebean();
			tbReceivebean.setCwkid(tbCwk.getId());
			tbReceivebean = receiveService.selectOne(tbReceivebean);
			Map<String,Object> map = new HashMap<String,Object>();
			if (tbReceivebean != null) {// 有签到记录
				Date newDate = new Date();
				int days = BeanUtil.days(tbReceivebean.getTime(), newDate);
				if(days == 1) {
					message = "未签到";
				} else if(days > 1) {
					tbReceivebean.setContinuous(0);
					message = "未签到";
				} else {
					message = "已签到";
				}

			} else {//没有签到记录
				tbReceivebean = new TbReceivebean();
				tbReceivebean.setContinuous(0);
				message = "未签到";
			}
			
			Integer tomorrowBean = null;
			if(tbReceivebean.getContinuous() == 0) {
				tomorrowBean = 5;
			} else if(tbReceivebean.getContinuous() == 1){
				tomorrowBean = 10;
			} else {
				tomorrowBean = 20;
			}
			map.put("remainBean", tbCwk.getCwkbeancount());
			map.put("tomorrowBean", tomorrowBean);
			map.put("continuous", tbReceivebean.getContinuous());
			return Ret.success("S",message,map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return Ret.error("发生异常!");
		}
	}

	/**
	 * 接口：获取威豆账户收支明细功能
	 * 
	 * @param request
	 * @param username
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "getBalance")
	@ResponseBody
	public Ret getBalance(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("type") String type, @RequestParam("pageNum") String pageNum,
			@RequestParam("tradeType")Integer tradeType) {
		TbCwk tbCwk = tbCwkService.findByName(username);

		try {
			if (StringUtils.isEmpty(pageNum)) {
				pageNum = "1";
			}
			PageHelper.startPage(Integer.parseInt(pageNum), Constants.PAGE_SIZE);
			List<TbCwkbean> beansList = tbCwkBeanService.selectByType(tradeType,tbCwk.getId());
			if (beansList == null || beansList.isEmpty()) {
				return Ret.warn("您无此交易记录");
			}
			PageInfo<TbCwkbean> pageInfo = new PageInfo<TbCwkbean>(beansList);
			return Ret.success(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return Ret.error("发生异常");
		}
	}
}
