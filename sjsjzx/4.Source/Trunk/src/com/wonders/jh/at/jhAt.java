package com.wonders.jh.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.Constants;
import com.wonders.jh.entity.FaSong;
import com.wonders.jh.entity.JiaoHuan;
import com.wonders.jh.entity.JiaoHuanZong;
import com.wonders.jh.entity.JieShou;
import com.wonders.platform.entity.FwApply;
import com.wonders.platform.entity.ZyItem;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.util.DateUtils;
import com.wonders.zymlgx.entity.ApplyCount;
import com.wonders.zymlgl.entity.Resource;

@IocBean
@At("/jh")
public class jhAt {

	@Inject
	private Dao dao ;
	
	/**
	 *
	 * @param session
	 */
	@At
	@Ok("jsp:jsp.jh.home")
	public void toHome(HttpSession session){
	}
	/**
	 *  交换节点监控
	 * @return
	 */

	@At
	@Ok("jsp:jsp.jh.jhjd.main")
	public Map<String, Object> toMain(String duixiangjiaohuanzongliang){
		Map<String , Object> result = new HashMap<String, Object>();
		//交换信息量
		Criteria cri = Cnd.cri();
		cri.getOrderBy().desc("DUIXIANGJIAOHUANZONGLIANG");
		List<JiaoHuanZong> jiaohuanzong = dao.query(JiaoHuanZong.class, cri ,dao.createPager(1, 5));
		result.put("jiaohuanzong", jiaohuanzong);
		//交换总量
		String jhzl = "select sum(DUIXIANGJIAOHUANZONGLIANG) jhzl from JK_JHJD_JHXXL";
		Sql is = Sqls.create(jhzl);
		is.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				while (rs.next())
				{
					return rs.getString("jhzl");
				}
				return null;
			}
		});
		dao.execute(is);
		result.put("jhzl", is.getResult());
		//日交换总量
		String rjhzl = "select sum(RIJIAOHUANZONGLIANG) rjhzl from JK_JHJD_JHXXL";
		Sql is1 = Sqls.create(rjhzl);
		is1.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				while (rs.next())
				{
					return rs.getString("rjhzl");
				}
				return null;
			}
		});
		dao.execute(is1);
		result.put("rjhzl", is1.getResult());
		return result;
	}
	/**
	 * 交换节点监控 详情
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.detail")
	public Map<String, Object> toDetail(String duixiangbianma){
		Map<String , Object> result = new HashMap<String, Object>();
		//交换详情
			//监控状态
		JiaoHuan jiaohuan = dao.fetch(JiaoHuan.class, Cnd.where("DUIXIANGBIANMA","=",duixiangbianma) );
		result.put("duixiangbianma", duixiangbianma);
		result.put("jiaohuan", jiaohuan);
			//总接收量
		String zjsl = "select sum(JIESHOUZONGLIANG) zjsl from JK_JHJD_JSJK";
		Sql is2 = Sqls.create(zjsl);
		is2.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				while (rs.next())
				{
					return rs.getString("zjsl");
				}
				return null;
			}
		});
		dao.execute(is2);
		result.put("zjsl", is2.getResult());
			//总发送量
		String zfsl = "select sum(FASONGZONGLIANG) zfsl from JK_JHJD_FSJK";
		Sql is3 = Sqls.create(zfsl);
		is3.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				while (rs.next())
				{
					return rs.getString("zfsl");
				}
				return null;
			}
		});
		dao.execute(is3);
		result.put("zfsl", is3.getResult());

		//发送监控
		List<FaSong> fasong = dao.query(FaSong.class, Cnd.where("DUIXIANGBIANMA","=",duixiangbianma) );
		result.put("fasong", fasong);
		//接收监控
		List<JieShou> jieshou = dao.query(JieShou.class, Cnd.where("DUIXIANGBIANMA","=",duixiangbianma) );
		result.put("jieshou", jieshou);
		return result;	
	}

	
	/**
	 * 交换节点监控 房屋房产数据库
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.jh.jhjd.resource-catalog")
	public Map<String, Object> toHouse(){
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Resource> resourceList = dao.query(Resource.class, null);
		Set<String> set = new HashSet<String>();
		for(Resource  resource : resourceList){
			set.add(resource.getProvideDepId());
		}
		//>>>>>已接入单位数量
		int resourceCount = set.size();
		//>>>>>已发布资源占比
		//人口类资源数量
		int peopleTypeCount = 0;
		//法人类资源数量
		int corpTypeCount = 0;
		//房屋类资源数量
		int houseTypeCount = 0;
		
		for(Resource  resource : resourceList){
			if(Constants.R_TYPE_PEOPLE.equals(resource.getResourceType())){
				peopleTypeCount++;
			}else if(Constants.R_TYPE_HOUSE.equals(resource.getResourceType())){
				houseTypeCount++;
			}else if(Constants.R_TYPE_CORP.equals(resource.getResourceType())){
				corpTypeCount++;
			}
		}
		result.put("resourceCount", resourceCount);
		result.put("peopleTypeCount", peopleTypeCount);
		result.put("corpTypeCount", corpTypeCount);
		result.put("houseTypeCount", houseTypeCount);
		//近30天新增资源目录
		//当月第一天
		Date firstDateOfMonth = DateUtils.getFirstDateOfMonth(new Date());
		//当月1-5天
		Date in5Date = DateUtils.addDays(firstDateOfMonth, 4);
		//当月5-10天
		Date in10Date = DateUtils.addDays(firstDateOfMonth, 9);
		//当月1-15天
		Date in15Date = DateUtils.addDays(firstDateOfMonth, 14);
		//当月15-20天
		Date in20Date = DateUtils.addDays(firstDateOfMonth, 19);
		//当月20-25天
		Date in25Date = DateUtils.addDays(firstDateOfMonth, 24);
		//当月25-30天 
		Date in30Date = DateUtils.addDays(firstDateOfMonth, 29);
		
		Map map5 = getCountByCriteria(new Date[]{firstDateOfMonth, in5Date});
		
		Map map10 = getCountByCriteria(new Date[]{in5Date, in10Date});
		
		Map map15 = getCountByCriteria(new Date[]{in10Date, in15Date});
		
		Map map20 = getCountByCriteria(new Date[]{in15Date, in20Date});
		
		Map map25 = getCountByCriteria(new Date[]{in20Date, in25Date});
		
		Map map30 = getCountByCriteria(new Date[]{in25Date, in30Date});
		
		result.put("map5", map5);
		result.put("map10", map10);
		result.put("map15", map15);
		result.put("map20", map20);
		result.put("map25", map25);
		result.put("map30", map30);
		
		
		//人口
		Criteria cri1 = Cnd.cri();
		cri1.where().andEquals("zyInfoId","e2f92fd91fc94a82b2703ee022caa5d3");
		List<ZyItem> countrk=dao.query(ZyItem.class,cri1 );
		//法人
		Criteria cri2 = Cnd.cri();
		cri2.where().andEquals("zyInfoId", "4d125caafae84ff6b24b632187f5f83d");
		List<ZyItem> countfr=dao.query(ZyItem.class,cri2 );
		//房屋
		Criteria cri3 = Cnd.cri();
		cri3.where().andIn("zyInfoId", "87108c683d00487b8ef0d1d8fd692c8c","9192301b26544b09b14bff88bff4ffd5","1ae93fc16c544d1096c9171583173843","8248fc2d5e004218a5e13f9588caea03","dc02cae2745a49fd85d18f70af692b0a");
		List<ZyItem>countfw=dao.query(ZyItem.class, cri3);
		//当月申请
		Criteria cri4 = Cnd.cri();
		cri4.where().and("applyTime", ">", DateUtils.getFirstDateOfMonth(new Date()));
		cri4.where().and("applyTime", "<", DateUtils.getLastDateOfMonth(new Date()));
		cri4.where().andEquals("auditStatus",10736 );
		int dysq=dao.count(FwApply.class,cri4);
		
		//当月已审核
		Criteria cri5 = Cnd.cri();
		cri5.where().and("applyTime", ">", DateUtils.getFirstDateOfMonth(new Date()));
		cri5.where().and("applyTime", "<", DateUtils.getLastDateOfMonth(new Date()));
		cri5.where().andEquals("auditStatus",10733 );
		int ysh=dao.count(FwApply.class,cri5);
		//当月已调用总数
		DateFormat format=new SimpleDateFormat("yyyy-MM");
		Sql sql = Sqls.create("select sum(APPLY_COUNT) from R_APPLY_COUNT where to_char(APPLY_DATE,'yyyy-MM') like '%"+format.format(new Date())+"%'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		
		//各委办单位调用此时
		Criteria cri = Cnd.cri();
		List<ApplyCount> ac=dao.query(ApplyCount.class, cri);
		StringBuffer s1=new StringBuffer();
		StringBuffer s2=new StringBuffer();
		for(int i=0;i<ac.size();i++)
		{
			User user= dao.fetch(User.class, Cnd.where("USER_ID","=",ac.get(i).getUserId()));
			String deptName = DicDataUtils.getInstance().getDicData(1003, user.getDept());
			s1.append(deptName+",");
			s2.append(ac.get(i).getCount()+",");
		}
		result.put("cc", s1);
		result.put("cc1", s2);
		result.put("dyzs", sql.getResult());
		result.put("dysq", dysq);
		result.put("ysh", ysh);
		result.put("rkcount",countrk.size() );
		result.put("frcount", countfr.size());
		result.put("fwcount", countfw.size());
		
		
		return result;
	}
	
	/**
	 * 根据条件获取某时间段：人口、房屋、法人类型的资源数量。
	 * @param dates
	 * @return
	 */
	public Map<String, Integer> getCountByCriteria(Date[] dates){
		Map<String, Integer> map = new HashMap<String, Integer>();
		Criteria peopleCri = Cnd.cri();
		peopleCri.where().and("createTime", ">", dates[0]);
		peopleCri.where().and("createTime", "<", dates[1]);
		peopleCri.where().andEquals("resourceType", Constants.R_TYPE_PEOPLE);
		List<Resource> peopleTypelist = dao.query(Resource.class, peopleCri); 
		if(peopleTypelist != null && peopleTypelist.size() > 0){
			map.put("rkl", peopleTypelist.size());
		}else{
			map.put("rkl", 0);
		}
		
		Criteria corpCri = Cnd.cri();
		corpCri.where().and("createTime", ">", dates[0]);
		corpCri.where().and("createTime", "<", dates[1]);
		corpCri.where().andEquals("resourceType", Constants.R_TYPE_CORP);
		List<Resource> corpTypelist = dao.query(Resource.class, corpCri);
		if(corpTypelist != null && corpTypelist.size() > 0){
			map.put("frl", corpTypelist.size() > 0 ? corpTypelist.size() : 0);
		}else{
			map.put("frl", 0);
		}
		
		Criteria houseCri = Cnd.cri();
		houseCri.where().and("createTime", ">", dates[0]);
		houseCri.where().and("createTime", "<", dates[1]);
		houseCri.where().andEquals("resourceType", Constants.R_TYPE_HOUSE);
		List<Resource> houseTypelist = dao.query(Resource.class, houseCri);
		if(houseTypelist != null && houseTypelist.size() > 0){
			map.put("fwl", houseTypelist.size() > 0 ? houseTypelist.size() : 0);
		}else{
			map.put("fwl", 0);
		}
		
		return map;
	}
	
	/**
	 * 交换节点监控 人口数据库
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.people")
	public Map<String, Object> toPeople(){
		
		Map<String, Object> result = new HashMap<String, Object>();
		//人口
		Criteria cri1 = Cnd.cri();
		cri1.where().andEquals("zyInfoId","e2f92fd91fc94a82b2703ee022caa5d3");
		List<ZyItem> countrk=dao.query(ZyItem.class,cri1 );
		//法人
		Criteria cri2 = Cnd.cri();
		cri2.where().andEquals("zyInfoId", "4d125caafae84ff6b24b632187f5f83d");
		List<ZyItem> countfr=dao.query(ZyItem.class,cri2 );
		//房屋
		Criteria cri3 = Cnd.cri();
		cri3.where().andIn("zyInfoId", "87108c683d00487b8ef0d1d8fd692c8c","9192301b26544b09b14bff88bff4ffd5","1ae93fc16c544d1096c9171583173843","8248fc2d5e004218a5e13f9588caea03","dc02cae2745a49fd85d18f70af692b0a");
		List<ZyItem>countfw=dao.query(ZyItem.class, cri3);
		//当月申请
		Criteria cri4 = Cnd.cri();
		cri4.where().and("applyTime", ">", DateUtils.getFirstDateOfMonth(new Date()));
		cri4.where().and("applyTime", "<", DateUtils.getLastDateOfMonth(new Date()));
		cri4.where().andEquals("auditStatus",10736 );
		int dysq=dao.count(FwApply.class,cri4);
		
		//当月已审核
		Criteria cri5 = Cnd.cri();
		cri5.where().and("applyTime", ">", DateUtils.getFirstDateOfMonth(new Date()));
		cri5.where().and("applyTime", "<", DateUtils.getLastDateOfMonth(new Date()));
		cri5.where().andEquals("auditStatus",10733 );
		int ysh=dao.count(FwApply.class,cri5);
		//当月已调用总数
		DateFormat format=new SimpleDateFormat("yyyy-MM");
		Sql sql = Sqls.create("select sum(APPLY_COUNT) from R_APPLY_COUNT where to_char(APPLY_DATE,'yyyy-MM') like '%"+format.format(new Date())+"%'");
		sql.setCallback(new getOneStringCallBack());
		dao.execute(sql);
		
		//各委办单位调用此时
		Criteria cri = Cnd.cri();
		List<ApplyCount> ac=dao.query(ApplyCount.class, cri);
		StringBuffer s1=new StringBuffer();
		StringBuffer s2=new StringBuffer();
		for(int i=0;i<ac.size();i++)
		{
			User user= dao.fetch(User.class, Cnd.where("USER_ID","=",ac.get(i).getUserId()));
			String deptName = DicDataUtils.getInstance().getDicData(1003, user.getDept());
			s1.append(deptName+",");
			s2.append(ac.get(i).getCount()+",");
		}
		result.put("cc", s1);
		result.put("cc1", s2);
		result.put("dyzs", sql.getResult());
		result.put("dysq", dysq);
		result.put("ysh", ysh);
		result.put("rkcount",countrk.size() );
		result.put("frcount", countfr.size());
		result.put("fwcount", countfw.size());
		return result;
	}
	
	
	private class getOneStringCallBack implements SqlCallback {
		@Override
		public Object invoke(Connection conn, ResultSet rs, Sql sql)
				throws SQLException {
			if (rs.next()) {
				return rs.getString(1);
			}
			return null;
		}

	}
	
	/**
	 * 交换节点监控 法人数据库
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.corporation")
	public Map<String, Object> toCorporation(){
		return null;
	}
	/**
	 * 交换节点监控 监控统计
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.monitoring")
	public Map<String, Object> toMonitoring(){
		return null;
	}
}
