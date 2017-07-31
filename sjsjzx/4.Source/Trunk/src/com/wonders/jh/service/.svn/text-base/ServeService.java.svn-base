package com.wonders.jh.service;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.Service;

/**
 * 产品服务  service
 * @author Xixi Luo
 * @date 2015-11-4
 */
@IocBean(fields = "dao")
public class ServeService  extends Service {

	/**
	 * 获取 序列
	 * 
	 * @author Xixi Luo
	 * @date 2015-11-3
	 * @param seqName
	 *            序列名称
	 * @return
	 */
	public Integer getSeq(String seqName) {
		if (StringUtils.isBlank(seqName)) {
			return null;
		}
		Sql sql = Sqls.create("select " + seqName + ".nextval from dual");
		sql.setCallback(Sqls.callback.integer());
		dao().execute(sql);

		return sql.getInt();
	}
}
