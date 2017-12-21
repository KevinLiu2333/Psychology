package com.wonders.admin.service.unifiedmonitoring;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.admin.dao.unifiedmonitoring.ThresholdSetDao;
import com.wonders.admin.entity.unifiedmonitoring.ThresholdSetBean;
import com.wonders.admin.util.ResultRules;
import com.wonders.tiles.authority.service.SystemConstants;

/**
 * 阀值设置业务层接口的实现.
 * 
 * @author Gray
 *
 */
@IocBean
public class ThresholdSetServiceImpl implements ThresholdSetService {

	// 注入阀值设置Dao
	@Inject("refer:thresholdSetDaoImpl")
	private ThresholdSetDao tsDao;

	/**
	 * 获取所有的阀值设置并返回.
	 * 
	 * @return 规定的规则.
	 */
	@Override
	public ResultRules getAllThreshold() {
		// 创建传输类
		ResultRules result = new ResultRules();
		// 调取页面需要的数据
		@SuppressWarnings("rawtypes")
		List<List> lists = tsDao.getNeedAll();
		// 设置返回类
		result.setState(0);// 成功
		result.setMsg("取出成功");// 存入消息
		result.setData(lists);// 存入数据
		// 返回值
		return result;
	}

	/**
	 * 将传入的数据打包,并进行阀值设置表的数据更新.
	 * 
	 * @param id
	 *            唯一ID值.
	 * @param threshold
	 *            设置值.
	 * @param alarmLevel
	 *            设置等级.
	 * @return 规定的规则.
	 */
	@Override
	public ResultRules updateThreshold(Integer id, String threshold, Integer alarmLevel,HttpSession session) {
		// 创建传输类
		ResultRules result = new ResultRules();
		// 判定传入的值
		if (id == null || threshold == null || alarmLevel == null || id <= 0 || alarmLevel <= 0) {// 如果符合条件则错误
			result.setState(1);// 失败
			result.setMsg("传入的数值不正确.");// 存入消息
			// 返回值
			return result;
		} // 如果正确
			// 对数据进行打包
		ThresholdSetBean tsb = new ThresholdSetBean();
		// 设置ID
		tsb.setId(id);
		// 设置报警等级
		tsb.setAlarmLevel(alarmLevel);
		// 设置阀值值
		tsb.setThreshold(threshold);
		// 设置更新时间
		tsb.setUpdateTime(new Date(System.currentTimeMillis()));
		// 设置用户名
		String userName = (String)session.getAttribute(SystemConstants.LOGON_NAME);//获取用户名
		tsb.setUserName(userName);
		// 执行更新
		Integer i = tsDao.update(tsb);
		// 判断更新的数量
		// XXX这里需要加入事物
		if (i == null || i > 1) {// 如果符合条件则错误
			result.setState(2);// 失败
			result.setMsg("更新异常.");// 存入消息
			// 返回值
			return result;
		} // 如果正确
			// 设置返回类
		result.setState(0);// 成功
		result.setMsg("更新成功");// 存入消息
		// 返回值
		return result;
	}

}
