package com.wonders.wddc.tiles.sn.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.wddc.tiles.sn.entity.SerialNumberBo;

/**
 * 序号生成器AT
 * @author vcixp
 *
 */
@At("/kernel/sn")
@IocBean
public class SnAct {
	@Inject
	private Dao dao;
	@At
	@Ok("jsp:wddc.tiles.sn.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<SerialNumberBo> list = dao.query(SerialNumberBo.class, null);
		result.put("list", list);
		return result;
	}
}
