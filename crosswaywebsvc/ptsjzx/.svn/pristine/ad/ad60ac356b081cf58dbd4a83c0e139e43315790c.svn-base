package com.wonders.admin.service.unifiedmonitoring;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.admin.dao.unifiedmonitoring.AlarmListDao;
import com.wonders.admin.entity.unifiedmonitoring.AlarmListBean;
import com.wonders.admin.util.Page;
import com.wonders.admin.util.ResultRules;

/**
 * 实现了报警列表业务层.
 * 
 * @author Gray
 *
 */
@IocBean
public class AlarmListServiceImpl implements AlarmListService {
	// 设置每页数据量
	private final int numOfData = 20;

	// 注入报警列表Dao
	@Inject("refer:alarmListDaoImpl")
	private AlarmListDao alDao;

	/**
	 * 通过给定的页面数值,从数据库中提取报警列表分页.
	 * 
	 * @param pageNum
	 *            页面数值.
	 * @return Result 规定的规则.
	 */
	@Override
	public ResultRules getAlarmListByPage(Integer pageNum) {
		// 创建传输类
		ResultRules result = new ResultRules();
		// 判定传入的页面数值
		if (pageNum == null || pageNum <= 0) {// 如果符合条件则错误
			result.setState(1);// 失败
			result.setMsg("页面数值不正确.");// 存入消息
			// 返回值
			return result;
		}
		// 从数据库提取数据总量的值
		Integer count = alDao.getPageNumber();
		// 判定值
		if (count == null) {// 如果符合条件则错误
			result.setState(1);// 失败
			result.setMsg("数据库异常.");// 存入消息
			// 返回值
			return result;
		}
		// 页面总数小于等于提取总数
		if ((count + numOfData) <= pageNum * numOfData) {// 如果符合条件则错误
			result.setState(1);// 失败
			result.setMsg("页面数值不正确.");// 存入消息
			// 返回值
			return result;
		}
		// 从数据库中提取数据
		@SuppressWarnings("rawtypes")
		List<List> list = alDao.getPage(pageNum, numOfData);
		// 判定list是否为空
		if (list == null) {// 如果为空
			result.setState(0);// 失败
			result.setMsg("数据库繁忙.");// 存入消息
			// 返回值
			return result;
		} // 如果不为空
			// 从数据库提取页面信息
		List<Integer> pageInfo = new ArrayList<Integer>();
		// 加入页面总数
		pageInfo.add(count);
		// 加入当前页数
		pageInfo.add(pageNum);
		// 加入总页数
		pageInfo.add(count/numOfData);
		// 包装返回值
		Page page = new Page();
		page.setList(list);
		page.setPage(pageInfo);
		// 设置返回类
		result.setState(0);// 成功
		result.setMsg("取出成功");// 存入消息
		result.setData(page);// 存入数据
		// 返回值
		return result;
	}

}
