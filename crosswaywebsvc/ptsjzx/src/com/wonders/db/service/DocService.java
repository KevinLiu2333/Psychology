package com.wonders.db.service;


import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.Service;

import com.wonders.db.entity.Xdb_files;

@IocBean(fields = "dao")
public class DocService extends Service{
	//获取所有的规范文档文件
	public List<Xdb_files> getxdb_file()
	{
		Criteria cri=Cnd.cri();
		cri.where().and("isdeleted", "=", "0");
		cri.where().and("postcomplete", "=", "1");
		cri.getOrderBy().desc("POSTEDTIME");
		List<Xdb_files> result = dao().query(Xdb_files.class, cri);
		for(Xdb_files files:result)
		{
			String filename=files.getFilenamelocal();
			if(filename.lastIndexOf('.')>-1)
			{
				String suffix=filename.substring(filename.lastIndexOf('.')+1);
				files.setFileSuffix(suffix);
			}
		}
		return result;
	}
	
}
