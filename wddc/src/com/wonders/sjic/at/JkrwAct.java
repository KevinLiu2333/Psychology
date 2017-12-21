package com.wonders.sjic.at;

import com.wonders.sjic.StringUtils;
import com.wonders.sjic.entity.InterfaceClassBo;
import com.wonders.wddc.tiles.sn.SnCreator;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 接口服务后台
 */
@At("/sjic/jkrw")
@IocBean
public class JkrwAct {

	@Inject
	private Dao dao;

	@At
	@Ok("jsp:sjic.jkrw.edit_view")
	public Map<String, Object> edit(String id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		if (id != null) {
			InterfaceClassBo classBo = dao.fetch(InterfaceClassBo.class, id);
			map.put("info", classBo);
		}
		return map;
	}

	@At
	@Ok("jsp:sjic.jkrw.index")
	public Map<String, Object> classList() {
		List<InterfaceClassBo> list = dao.query(InterfaceClassBo.class, Cnd.where("status","=","1"));
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("info", list);
		return map;
	}

	@At
	@Ok("raw")
	public String del(String id) {
		try {
			InterfaceClassBo classBo = dao.fetch(InterfaceClassBo.class, id);
			classBo.setStatus("0");
			dao.update(classBo);
			return "删除成功";
		} catch (Exception e) {
			return "删除失败";
		}
	}

	@At
	@Ok("raw")
	public String save(@Param("..") InterfaceClassBo classBo) {
		String resultString;
		try {
			classBo.setUpdateTime(new Date());
			// 更新操作
			if (!StringUtils.isEmpty(classBo.getId())) {

				resultString = dao.updateIgnoreNull(classBo) == 1 ? "保存成功" : "保存失败";
				// 插入操作
			} else if (!StringUtils.isEmpty(classBo.getJkid())) {
				classBo.setStatus("1");
				classBo.setId(SnCreator.getInstance().getSN("sjicc", 5, "JKC"));
				resultString = dao.insert(classBo) != null ? "插入信息成功" : "插入信息失败";
			} else {
				resultString = "未知错误";
			}
		} catch (Exception e) {
			resultString = "未知错误";
		}
		return resultString;
	}

}
