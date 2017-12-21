package com.wonders.zymlgl.at;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.Constants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.zymlgl.entity.Resource;
import com.wonders.zymlgl.entity.ResourceDetails;
import com.wonders.zymlgl.entity.ShiResource;
import com.wonders.zymlgl.entity.ShiResourceDetails;

import jxl.Sheet;
import jxl.Workbook;

@IocBean
@At("/zypz")
public class ZypzAt {
	
	@Inject
	private Dao dao;
	
	
	@At
	@Ok("jsp:jsp.zymlgl.zypz.pzlist")
	public Map<String, Object> toPzym(HttpServletRequest request,String rename){
		Map<String, Object> result=new HashMap<String, Object>();
		Criteria cri=Cnd.cri();
		cri.where().andEquals("VALIDITY","0");
		if(!Strings.isEmpty(rename)){
			cri.where().andLike("RESOURCE_NAME", rename);
		}
		Pager pager = ConUtils.makePaginationPager(request);
		List<ShiResource> resource=dao.query(ShiResource.class, cri,pager);
		pager.setRecordCount(dao.count(Resource.class,cri));
		result.put("list", resource);
		result.put("pager", pager);
		return result;
		
	}
	
	@At
	@Ok("jsp:jsp.zymlgl.zypz.index")
	public Map<String, Object> toIndex(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		ShiResource resource=dao.fetch(ShiResource.class,id);
		result.put("re", resource);
		return result;

	}
	
	
	@At
	@AdaptBy(type = UploadAdaptor.class, args = { "${app.root}/upload/" })
	@Ok("jsp:jsp.zymlgl.zypz.add")
	public Map<String, Object> savePz(TempFile file,String resourceid,int orderno,int rowno) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<ShiResourceDetails> list =new ArrayList<ShiResourceDetails>();
		try {
			InputStream stream = new FileInputStream(file.getFile());
			Workbook rwb = Workbook.getWorkbook(stream);
			Sheet sheet = rwb.getSheet(0);
			int j=0;
			for(int i=rowno;i<sheet.getColumns();i++){
				String name=sheet.getCell(i, orderno).getContents();
				if(!Strings.isEmpty(name)){
					ShiResourceDetails re=new ShiResourceDetails();
					re.setDataitemname(name);
					re.setFieldcode("data"+j);
					re.setOrdernum(j);
					list.add(re);
					j++;
				}	
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		result.put("list", list);
		result.put("resourceid", resourceid);
		return result;
	}
	
	@At
	public View toSave(@Param("::column")List<ShiResourceDetails> column,String resourceid){
		ShiResource resource=dao.fetch(ShiResource.class,resourceid);
		for(ShiResourceDetails re:column){
			re.setResourceid(resourceid);
			re.setDetailsid(UUID.randomUUID().toString().replaceAll("-", ""));
			re.setOpenproperty(resource.getOpenproperty());
			dao.insert(re);
		}
		resource.setUpdatetime(new Date());
		resource.setValidity("1");
		dao.update(resource);
		return new ServerRedirectView("/zypz/toPzym");
		
	}
	
	@At
	@Ok("jsp:jsp.zymlgl.zypz.re_list")
	public Map<String, Object> toContentIndex(HttpServletRequest request,String resourcename,String dept) {
		Map<String, Object> result = new HashMap<String, Object>();

		Criteria cri =Cnd.cri();
		cri.where().andEquals("validity", "1");
		
		if(!Strings.isEmpty(resourcename)){
			cri.where().andLike("RESOURCE_NAME", resourcename);
		}
		Pager pager = ConUtils.makePaginationPager(request);
		List<ShiResource> resourcelist =dao.query(ShiResource.class, cri,pager);
		pager.setRecordCount(dao.count(ShiResource.class, cri));
		result.put("list", resourcelist);
        result.put("pager", pager);
		return result;

	}
	
	@At
	@Ok("jsp:jsp.zymlgl.zypz.resource-details")
	@Filters
	public Object toDetail(String resourceId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.getOrderBy().asc("ordernum");
		cri.where().andEquals("resourceid", resourceId);
		ShiResource resource = dao.fetch(ShiResource.class, resourceId);
		resource.setBrowsecount((resource.getBrowsecount()) + 1);
		dao.update(resource);
		List<ShiResourceDetails> fieldDetailsList = dao.query(ShiResourceDetails.class, cri);

		result.put("resource", resource);
		result.put("fieldDetailsList", fieldDetailsList);
		return result;
	}
	
}
