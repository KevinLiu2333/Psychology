package com.wonders.query.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.query.entity.CorpInfo;
import com.wonders.sjtb.entity.CorpCF;
import com.wonders.sjtb.entity.CorpXK;
import com.wonders.sjtb.entity.PeopleCF;
import com.wonders.sjtb.entity.PeopleXK;
import com.wonders.tiles.extend.adaptor.util.ConUtils;

@At("/querySGS")
@IocBean
public class QuerySGSAt {
	@Inject
	private Dao	dao;

	@At
	@Ok("jsp:jsp.query.sgs.sgs_index")
	public Map<String, Object> toSgsIndex(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", id);
		return result;
	}

	// CORP_CF列表
	@At
	@Ok("jsp:jsp.query.sgs.corp_cf_list")
	public Map<String, Object> toCorpCfList(String corpinfoid, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		CorpInfo corpinfo = dao.fetch(CorpInfo.class, Cnd.where("CORP_INFO_ID", "=", corpinfoid));
		Pager pager = ConUtils.makePaginationPager(request);
		List<CorpCF> list = null;
		if (!Strings.isEmpty(corpinfo.getCorpname())) {
			list = dao.query(CorpCF.class, Cnd.where("CF_XDR_MC", "=", corpinfo.getCorpname()).desc("CF_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpCF.class, Cnd.where("CF_XDR_MC", "=", corpinfo.getCorpname())));
		}
		if (list == null && !Strings.isEmpty(corpinfo.getOrgancode())) {
			list = dao.query(CorpCF.class, Cnd.where("CF_XDR_ZDM", "=", corpinfo.getOrgancode()).desc("CF_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpCF.class, Cnd.where("CF_XDR_ZDM", "=", corpinfo.getOrgancode())));
		} else if (list == null && !Strings.isEmpty(corpinfo.getUniscid())) {
			list = dao.query(CorpCF.class, Cnd.where("CF_XDR_SHXYM", "=", corpinfo.getUniscid()).desc("CF_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpCF.class, Cnd.where("CF_XDR_SHXYM", "=", corpinfo.getUniscid())));
		} else if (list == null && !Strings.isEmpty(corpinfo.getRegno())) {
			list = dao.query(CorpCF.class, Cnd.where("CF_XDR_GSDJ", "=", corpinfo.getRegno()).desc("CF_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpCF.class, Cnd.where("CF_XDR_GSDJ", "=", corpinfo.getRegno())));
		} else if (list == null && !Strings.isEmpty(corpinfo.getTaxpayerscode())) {
			list = dao.query(CorpCF.class, Cnd.where("CF_XDR_SWDJ", "=", corpinfo.getTaxpayerscode()).desc("CF_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpCF.class, Cnd.where("CF_XDR_SWDJ", "=", corpinfo.getTaxpayerscode())));
		}
		result.put("list", list);
		result.put("pager", pager);
		result.put("corpinfoid", corpinfoid);
		return result;

	}

	// CORP_CF详情
	@At
	@Ok("jsp:jsp.query.sgs.corp_cf_view")
	public Object toCorpCfView(String id, HttpServletRequest request) {
		CorpCF cf = dao.fetch(CorpCF.class, Cnd.where("ID", "=", id));
		return cf;
	}

	// CORP_XK列表
	@At
	@Ok("jsp:jsp.query.sgs.corp_xk_list")
	public Map<String, Object> toCorpXkList(String corpinfoid, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		CorpInfo corpinfo = dao.fetch(CorpInfo.class, Cnd.where("CORP_INFO_ID", "=", corpinfoid));
		Pager pager = ConUtils.makePaginationPager(request);
		List<CorpXK> list = null;
		if (!Strings.isEmpty(corpinfo.getCorpname())) {
			list = dao.query(CorpXK.class, Cnd.where("XK_XDR", "=", corpinfo.getCorpname()).desc("XK_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpXK.class, Cnd.where("XK_XDR", "=", corpinfo.getCorpname())));
		}
		if (list == null && !Strings.isEmpty(corpinfo.getOrgancode())) {
			list = dao.query(CorpXK.class, Cnd.where("XK_XDR_ZDM", "=", corpinfo.getOrgancode()).desc("XK_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpXK.class, Cnd.where("XK_XDR_ZDM", "=", corpinfo.getOrgancode())));
		} else if (list == null && !Strings.isEmpty(corpinfo.getUniscid())) {
			list = dao.query(CorpXK.class, Cnd.where("XK_XDR_SHXYM", "=", corpinfo.getUniscid()).desc("XK_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpXK.class, Cnd.where("XK_XDR_SHXYM", "=", corpinfo.getUniscid())));
		} else if (list == null && !Strings.isEmpty(corpinfo.getRegno())) {
			list = dao.query(CorpXK.class, Cnd.where("XK_XDR_GSDJ", "=", corpinfo.getRegno()).desc("XK_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpXK.class, Cnd.where("XK_XDR_GSDJ", "=", corpinfo.getRegno())));
		} else if (list == null && !Strings.isEmpty(corpinfo.getTaxpayerscode())) {
			list = dao.query(CorpXK.class, Cnd.where("XK_XDR_SWDJ", "=", corpinfo.getTaxpayerscode()).desc("XK_JDRQ"), pager);
			pager.setRecordCount(dao.count(CorpXK.class, Cnd.where("XK_XDR_SWDJ", "=", corpinfo.getTaxpayerscode())));
		}
		result.put("list", list);
		result.put("pager", pager);
		result.put("corpinfoid", corpinfoid);
		return result;

	}

	// CORP_XK详情
	@At
	@Ok("jsp:jsp.query.sgs.corp_xk_view")
	public Object toCorpXkView(String id, HttpServletRequest request) {
		CorpXK xk = dao.fetch(CorpXK.class, Cnd.where("ID", "=", id));
		return xk;
	}

	// PEOPLE_CF列表
	@At
	@Ok("jsp:jsp.query.sgs.people_cf_list")
	public Map<String, Object> toPeopleCfList(String zjhm, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		List<PeopleCF> list = dao.query(PeopleCF.class, Cnd.where("CF_XDR_SFZ", "=", zjhm).desc("CF_JDRQ"), pager);
		pager.setRecordCount(dao.count(PeopleCF.class, Cnd.where("CF_XDR_SFZ", "=", zjhm)));
		result.put("list", list);
		result.put("pager", pager);
		result.put("zjhm", zjhm);
		return result;
	}

	// PEOPLE_CF详情
	@At
	@Ok("jsp:jsp.query.sgs.people_cf_view")
	public Object toPeopleCfView(String id) {
		PeopleCF cf = dao.fetch(PeopleCF.class, Cnd.where("ID", "=", id));
		return cf;
	}

	// PEOPLE_XK列表
	@At
	@Ok("jsp:jsp.query.sgs.people_xk_list")
	public Map<String, Object> toPeopleXkList(String zjhm, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		List<PeopleXK> list = dao.query(PeopleXK.class, Cnd.where("XK_XDR_SFZ", "=", zjhm).desc("XK_JDRQ"), pager);
		pager.setRecordCount(dao.count(PeopleXK.class, Cnd.where("XK_XDR_SFZ", "=", zjhm)));
		result.put("list", list);
		result.put("pager", pager);
		result.put("zjhm", zjhm);
		return result;
	}

	// PEOPLE_XK详情
	@At
	@Ok("jsp:jsp.query.sgs.people_xk_view")
	public Object toPeopleXkView(String id) {
		PeopleXK xk = dao.fetch(PeopleXK.class, Cnd.where("ID", "=", id));
		return xk;
	}

}
