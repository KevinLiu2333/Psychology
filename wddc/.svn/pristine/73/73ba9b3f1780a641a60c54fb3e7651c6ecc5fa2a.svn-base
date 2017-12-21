package com.wonders.wddc.suite.data.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.wddc.suite.data.adapter.DBAdapter;
import com.wonders.wddc.suite.data.entity.DBinfoBo;
import com.wonders.wddc.suite.data.entity.MultStatInfoBo;
import com.wonders.wddc.suite.data.entity.StatInfoBo;
import com.wonders.wddc.tiles.sn.SnCreator;

/**
 * 数据库配置的at
 * 
 * @author vcixp
 * 
 */
@At("/suite/data/db")
@IocBean
public class DbAct {
	@Inject
	private Dao dao;
	private Log log = Logs.get();

	/**
	 * 数据库配置首页
	 * 
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.db.index")
	public Map<String, Object> toIndex() {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("state", "=", "1");
		List<DBinfoBo> list = dao.query(DBinfoBo.class, cri);
		result.put("dbinfos", list);
		return result;
	}

	/**
	 * 编辑数据库
	 * 
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.db.edit")
	public Map<String, Object> toEdit(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(id)) {
			DBinfoBo dBinfo = dao.fetch(DBinfoBo.class, Cnd.where("id", "=", id));
			result.put("dbinfo", dBinfo);
		}
		return result;
	}

	/**
	 * 数据库测试
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param type
	 * @return
	 */
	@At
	@Ok("json")
	public Object DBTest(String url, String username, String password, int type) {
		try {
			DBAdapter.testDbTest(url, username, password, type);
			return "连接成功！";
		} catch (Exception e) {
			log.error(e);
			return "连接失败！" + e.getMessage();
		}
	}

	/**
	 * 保存数据库信息
	 * 
	 * @param dbinfo
	 * @return
	 */
	@At
	public View save(@Param("::dbinfo.") DBinfoBo dbinfo) {
		if (dbinfo != null) {
			if (Strings.isEmpty(dbinfo.getId())) {
				dbinfo.setId(SnCreator.getInstance().getSN("database", 5, "DB"));
				dbinfo.setState("1");
				dbinfo.setConfigType("1");
				dao.insert(dbinfo);
			} else {
				dao.update(dbinfo);
			}
		}
		return new ServerRedirectView("/suite/data/db/toIndex");
	}
	/**
	 * 数据库配置项是否正在被使用
	 * @param id
	 * @return
	 */
	@At
	@Ok("json")
	public Object isUsing(String id) {
		int count = dao.count(StatInfoBo.class, Cnd.where("DATABASE_ID", "=", id));
		count += dao.count(MultStatInfoBo.class, Cnd.where("DATABASE_ID", "=", id));
		if (count > 0) {
			return '1';
		} else {
			return '0';
		}
	}
	/**
	 * 删除数据库配置项
	 * @param id
	 */
	@At
	public void delete(String id){
		DBinfoBo info = dao.fetch(DBinfoBo.class, Cnd.where("ID", "=", id));
		info.setState("0");
		dao.update(info);
	}
	/**
	 * 根据id测试数据库链接是否正确
	 * @param id
	 * @return
	 */
	@At
	@Ok("json")
	public Object DBTestById(String id){
		DBinfoBo dBinfo = dao.fetch(DBinfoBo.class, id);
		if(null!=dBinfo){
			try {
				DBAdapter.testDbTest(dBinfo.getUrl(), dBinfo.getUsername(), dBinfo.getPassword(), dBinfo.getType());
				return "连接成功！";
			} catch (Exception e) {
				log.error(e);
				return "连接失败！" + e.getMessage();
			}
		}else {
			return "数据库已经被删除！";
		}
	}
	@At
	@Ok("json")
	public Object getAllDbJson(){
		Map<String, String> result = new HashMap<String, String>();
		Criteria cri = Cnd.cri();
		cri.where().and("state", "=", "1");
		List<DBinfoBo> list = dao.query(DBinfoBo.class, cri);
		for(DBinfoBo bo:list){
			result.put(bo.getId(), bo.getDbname());
		}
		return result;
	}
	
}
