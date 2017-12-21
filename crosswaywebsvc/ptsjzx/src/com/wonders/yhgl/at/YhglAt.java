package com.wonders.yhgl.at;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.pzgl.entity.DwLog;
import com.wonders.tiles.authority.entity.Org;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.util.PropertyUtils;

/**
 * 用户管理页面.
 *
 */
@At("/yhgl")
@IocBean(fields = "dao")
public class YhglAt {

	private Dao dao;

	/**
	 * 权限说明页面.
	 */
	@At("/toQxsm")
	@Ok("jsp:jsp.yhgl.qxsm")
	public void toQxsm(){
		
	}
	
	/**
	 * 调转用户管理页面.
	 */
	@At("/toIndex")
	@Ok("jsp:jsp.yhgl.user_list")
	public Map<String,Object> toIndex(HttpServletRequest request,String logonName,HttpSession session,String dept,
			String displayName,String role,String startTime, String endTime,String state,String spqx){
		Map<String,Object> map = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("LOGON_NAME","<>","administrator");
//		if(!Strings.isEmpty(((User)session.getAttribute(SystemConstants.SYSTEM_USER)).getUserId())){
//			cri.where().and("USER_ID","<>",((User)session.getAttribute(SystemConstants.SYSTEM_USER)).getUserId());
//		}
		if(!Strings.isEmpty(logonName)){
			cri.where().and("LOGON_NAME","like","%"+logonName+"%");
		}
		if(!Strings.isEmpty(state)){
			cri.where().and("STATE","=",state);
		}
		if(!Strings.isEmpty(displayName)){
			cri.where().and("DISPLAY_NAME","like","%"+displayName+"%");
		}
		if(!Strings.isEmpty(role)){
			cri.where().and("ROLE_ID","like","%"+role+"%");
		}
		if(!Strings.isEmpty(dept)){
			cri.where().and("DEPT","=",dept);
		}
		if(!Strings.isEmpty(spqx)){
			cri.where().and("TYPE","=",spqx);
		}
		if(!Strings.isEmpty(startTime)){
			cri.where().and("to_char(UPDATE_TIME,'yyyymmdd')", ">=", startTime.replaceAll("-", ""));
			try {
				map.put("startTime", new SimpleDateFormat("yyyy-MM-dd").parse(startTime+"-01"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!Strings.isEmpty(endTime)){
			cri.where().and("to_char(UPDATE_TIME,'yyyymmdd')", "<=", endTime.replaceAll("-", ""));
			try {
				map.put("endTime", new SimpleDateFormat("yyyy-MM-dd").parse(endTime+"-01"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Pager pager = ConUtils.makePaginationPager(request);
		cri.getOrderBy().asc("dept");
		List<User> user = dao.query(User.class,cri,pager);
		Map<String,String> rolemap=DicDataUtils.getInstance().getDic(1066);
		for(User u:user){
			if(!Strings.isEmpty(u.getRoleId())){
				String roles[]=u.getRoleId().split(",");
				String temp= "";
				for(String r:roles){
					if(!r.equals("0")){
						temp+=rolemap.get(r)+" ";
					}
				}
				u.setRoleId(temp);
			}else {
				u.setRoleId("");
			}
		}
		pager.setRecordCount(dao.count(User.class,cri));
		map.put("state", state);
		map.put("dept", dept);
		map.put("listUser", user);
		map.put("displayName", displayName);
		map.put("role", role);
		map.put("pager", pager);
		map.put("logonName", logonName);
		map.put("spqx", spqx);
		return map;
	}
	
	/**
	 * 跳转修改用户页面
	 */
	@At("/toEditUser")
	@Ok("jsp:jsp.yhgl.user_edit")
	public Map<String,Object> toEditUser(String userId,HttpSession session){
		User user = dao.fetch(User.class,Cnd.where("USER_ID","=",userId));
		User thisuser = (User)session.getAttribute(SystemConstants.SYSTEM_USER);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		map.put("thisuser", thisuser);
		return map;
	}
	
	/**
	 * 通过指定条件查询用户
	 */
	@At("/updateUser")
	@Ok("jsp:jsp.yhgl.user_list")
	public Map<String,Object> cxyh(String logonName, String userFullName, String roleId, String startTime, String endTime) {
		Criteria cri = Cnd.cri();
		if (logonName != "" && logonName != null) {
			cri.where().and("LOGON_NAME", "LIKE", "%"+logonName+"%");
		}
		if (userFullName != "" && userFullName != null) {
			cri.where().and("DISPLAY_NAME", "LIKE", "%"+userFullName+"%");
		}
		if (roleId != "" && roleId != null) {
			cri.where().and("ROLE_ID","=",roleId);
		}
		if (startTime.length() > 5 && startTime != null) {
			cri.where().and("to_char(UPDATE_TIME,'yyyymmdd')", ">=", startTime.replaceAll("-", ""));
		}
		if (endTime.length() > 5 && endTime != null) {
			cri.where().and("to_char(UPDATE_TIME,'yyyymmdd')", "<=", endTime.replaceAll("-", ""));
		}
		Pager pager = dao.createPager(1, 10);
		List<User> list = dao.query(User.class, cri,pager);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("listUser", list);
		return map;
	}

	/**
	 * 更新用户
	 */
	@At("/gxyh")
	@Ok("jsp:jsp.yhgl.success")
	public void gxyh(@Param("::user.") User user,HttpSession session) {
		User user1 =dao.fetch(User.class,Cnd.where("USER_ID","=",user.getUserId()));
		if(user1!=null){

			//更新部门,插入日志
			DwLog log =new DwLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setOperateDate(new Date());
			if(!user1.getState().equalsIgnoreCase(user.getState())){
				if(user1.getState().equalsIgnoreCase("1")){
					log.setOperateType("关闭用户");
					log.setCountType("gb");
				}else{
					log.setOperateType("开启用户");
					log.setCountType("kq");
				}
			}else{
				log.setOperateType("修改权限");
				log.setCountType("xg");
			}
			log.setQueryCondition(user1.getLogonName());
			log.setQueryTable("TEST_USER_INFO");
			log.setOperateUser(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
			log.setOperateDept(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
			dao.insert(log);
			user1.setRoleId(user.getRoleId());
			user1.setState(user.getState());
			user1.setType(user.getType());
			user1.setUpdateTime(new Date());
			if(Strings.isEmpty(user1.getDept())||!Strings.isEmpty(user1.getOrgCode())){
			
			String orgid=user1.getOrgId();
			//用code更新org_id
			if(!Strings.isEmpty(user1.getOrgCode())){
				Org org =new Org();
                org =dao.fetch(Org.class,Cnd.where("ORG_CODE", "=", user1.getOrgCode()));
                user1.setOrgId(org.getOrgId());
                orgid=org.getOrgId();
			}
			if(!Strings.isEmpty(orgid)){
				
				Sql sql =Sqls.create("select ST_DEPT_CODE from DIC_MUNICIPAL_DEPT where ORGAN_ID ='"+orgid+"'");
				sql.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
							throws SQLException {
						if(rs.next()){
							return rs.getString("ST_DEPT_CODE");
						}
						return null;
					}
				});
				dao.execute(sql);
				String deptid = (String) sql.getResult();
				if(!Strings.isEmpty(deptid)){
					user1.setDept(deptid);
				}else {
					int counttime=20;//防止死循环
					boolean flag = false;//是否查到主部门organid
					String orgcode = orgid;
					while(counttime>0){
						sql = Sqls.create("select ORG_ID,ORG_PARENT_ID "
								+ "from UA_ORG where ORG_ID=(select ORG_PARENT_ID from UA_ORG where ORG_ID='"+orgcode+"')");
						sql.setCallback(new SqlCallback() {
							@Override
							public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
									throws SQLException {
								Map<String, String> result = new HashMap<String, String>();
								if(rs.next()){
									result.put("ORG_ID", rs.getString("ORG_ID"));
									result.put("ORG_PARENT_ID", rs.getString("ORG_PARENT_ID"));
									return result;
								}
								return null;
							}
						});
						dao.execute(sql);
						@SuppressWarnings("unchecked")
						Map<String, String> result = (Map<String, String>) sql.getResult();
						if(result==null){
							break;
						}else if(result.get("ORG_PARENT_ID").equals("1")){
							orgid=result.get("ORG_ID");
							flag=true;
							break;
						}else {
							orgcode=result.get("ORG_ID");
						}
						counttime--;
					}
					if(flag){
						sql =Sqls.create("select ST_DEPT_CODE from DIC_MUNICIPAL_DEPT where ORGAN_ID ='"+orgid+"'");
						sql.setCallback(new SqlCallback() {
							@Override
							public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
									throws SQLException {
								if(rs.next()){
									return rs.getString("ST_DEPT_CODE");
								}
								return null;
							}
						});
						dao.execute(sql);
						deptid = (String) sql.getResult();
						user1.setDept(deptid);
					}
				}
			
			}
			
			}
			dao.update(user1);
			creatwatermark(user1.getDept()==null?"":user1.getDept(),user1.getDisplayName(),user1.getUserId());
		} 
		/*else {
			user.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
			user.setUpdateTime(new Date());
			dao.insert(user);
			creatwatermark(user.getDept()==null?"":user.getDept(),user.getDisplayName(),user.getUserId());
		}*/
		
	}
	
	private void creatwatermark(String dept,String displayname,String userid){
		try {
			dept=DicDataUtils.getInstance().getDicData(1003, dept);
			int width = 150;   
	        int height = 150;   
	        File path =new File(getFilePath()+"/watermark");   
	        if(!path.exists()&&!path.isDirectory()){
	        	path.mkdir();
	        }
	        File file = new File(getFilePath()+"/watermark/"+userid+".png");   
	           
	        Font font = new Font("微软雅黑", Font.BOLD, 19);   
	        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);   
	        Graphics2D g2 = (Graphics2D)bi.getGraphics();   
	        g2.setBackground(new Color(255, 255, 255, 0));   
	        g2.clearRect(0, 0, width, height);   
	        g2.setPaint(new Color(100, 100, 100, 25));   
	        g2.setFont(font);
	        g2.translate(width / 2, height/ 2);
	        g2.rotate(-45 * Math.PI / 180);
	        g2.drawString(dept==null?"":dept,0,0);
	        g2.drawString(displayname==null?"":displayname,0,50);
	           
	        ImageIO.write(bi, "png", file);   
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String getFilePath() {
		String path = PropertyUtils.getProperty("app.path");
		return path;
	}
}
