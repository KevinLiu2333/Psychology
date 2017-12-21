package com.wonders.query.at;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.pzgl.entity.DwLog;
import com.wonders.query.entity.CorpInfo;
import com.wonders.query.entity.CorpLicense;
import com.wonders.query.entity.PersonInfo;
import com.wonders.query.entity.PunishNoteEnty;
import com.wonders.query.entity.PunishNoteInfoEnty;
import com.wonders.query.entity.TbHouseBo;
import com.wonders.query.entity.XyxxCloumn;
import com.wonders.query.entity.XyxxInfo;
import com.wonders.sjtb.entity.CorpCF;
import com.wonders.sjtb.entity.CorpXK;
import com.wonders.sjtb.entity.PeopleCF;
import com.wonders.sjtb.entity.PeopleXK;
import com.wonders.sms.common.SmsMould;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.util.PropertyUtils;
import com.wonders.utils.VerifyCodeUtils;
import com.wonders.utils.sendMailUtils;
import com.wonders.ws.receive.bean.BuildingBean;
import com.wonders.ws.receive.bean.BuildingCorp;
import com.wonders.ws.receive.bean.CommunityAcceptanceAffairsApplyBean;
import com.wonders.ws.receive.bean.PtQfbWangtingBean;
import com.wonders.yjyb.entity.Bjlb;
import com.wonders.yjyb.entity.Fzlb;

import net.sf.json.JSONArray;

@At("/query")
@IocBean
public class QueryAt {
	@Inject
	private Dao dao;
	@Inject
	private Dao dao1;
	
	private static String filepath;
	
	private static String TITLE = "法人导出文件密码";
	
	private static String HOST = "ptqmail.ptq.sh.gov.cn";
	
	
	static{
		filepath = PropertyUtils.getProperty("app.path")+PropertyUtils.getProperty("encryptPath")+"/";
	}
	
	@SuppressWarnings("rawtypes")
	@At
	@Ok("jsp:jsp.query.person_list")
	public Map<String, Object> toPersonList(HttpServletRequest request,String countflag,
			HttpSession session, String nl,String nl2, String xb, String zjhm,String xm,
			String csny1,String csny2,String mz, String syrklb, String hjjd,String newquery) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		DwLog log = new DwLog();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String today = df.format(new Date());
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		if(Strings.isEmpty(countflag)){
			log.setOperateType("查询人口列表");
			log.setCountType("1");
		}else {
			log.setCountType("7");
			log.setOperateType("统计人口总数");
		}
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		String condition = "where 1=1 ";
		Criteria cri = Cnd.cri();
		boolean queryflag = false; // 无条件时不查询
		if (!Strings.isEmpty(xm)){
			cri.where().and("XM","like",xm+"%");
			condition += " and xm like '" + xm+"%'";
			queryflag = true;
		}
		if(!Strings.isEmpty(csny1)){
			cri.where().and("CSRQ",">=",csny1.replaceAll("-", "")+"00");
			condition += " and CSRQ >='" + csny1.replaceAll("-", "")+"00'";
			queryflag = true;
			try {
				result.put("csny1", new SimpleDateFormat("yyyyMMdd").parse(csny1.replaceAll("-", "")+"01"));
			} catch (ParseException e) {
				result.put("csny1",null);
			}
		}
		if(!Strings.isEmpty(csny2)){
			cri.where().and("CSRQ","<=",csny2.replaceAll("-", "")+"99");
			condition += " and CSRQ <='" + csny2.replaceAll("-", "")+"99'";
			queryflag = true;
			try {
				result.put("csny2", new SimpleDateFormat("yyyyMMdd").parse(csny2.replaceAll("-", "")+"01"));
			} catch (ParseException e) {
				result.put("csny2",null);
			}
		}
		if (!Strings.isEmpty(mz)) {
			String mztemp="";
			String[] mzs = mz.split(",");
			for(int i = 0 ;i< mzs.length ; i++){
				mztemp+="'"+mzs[i]+"'";
				if(i!=mzs.length-1){
					mztemp+=",";
				}
			}
			cri.where().andIn("MZDM", mzs);
			condition += " and MZDM in (" + mztemp+")";
			queryflag = true;
		}
		if (!Strings.isEmpty(xb)) {
			cri.where().and("XBDM", "=", xb);
			condition += " and XBDM='" + xb+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(zjhm)) {
			cri.where().and("ZJHM", "=", zjhm.trim());
			condition += " and ZJHM='" + zjhm.trim()+"'";
			queryflag = true;
		}
		if(!Strings.isEmpty(nl)&&!Strings.isEmpty(nl2)){
			if(Integer.parseInt(nl)>Integer.parseInt(nl2)){
				String csnl=nl;
				nl=nl2;
				nl2=csnl;
			}
		}
		if (!Strings.isEmpty(nl)) {
			int nowday = Integer.parseInt((new SimpleDateFormat("yyyyMMdd"))
					.format(new Date()));
			cri.where().and("CSRQ", "<=",
					"" + (nowday - Integer.parseInt(nl) * 10000));
			condition += " and CSRQ<='"
					+ (nowday - Integer.parseInt(nl) * 10000)+"'";
			queryflag = true;
		}
		if(!Strings.isEmpty(nl2)){
			int nowday = Integer.parseInt((new SimpleDateFormat("yyyyMMdd"))
					.format(new Date()));
			cri.where().and("CSRQ", ">",
					"" + (nowday - (Integer.parseInt(nl2)+1) * 10000));
			condition += " and CSRQ>'"
					+ (nowday - (Integer.parseInt(nl2) +1)* 10000)+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(syrklb)) {
			cri.where().and("SYRKLBDM", "=", syrklb);
			condition += " and SYRKLBDM='" + syrklb+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(hjjd)) {
			cri.where().and("HJDJDDM","=",hjjd);
			condition += " and HJDJDDM = '" + hjjd+"'";
			queryflag = true;
		}
		log.setQueryCondition(condition);
		log.setQueryTable("T_GA_RJBXX");
		List list =null;
		//List<PersonInfo> list = null;
		String user_jd=DicDataUtils.getInstance().getDicData(1100, ((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		if(!Strings.isEmpty(user_jd)){
			condition += " and ( JZDJDDM = '"+user_jd+"' or HJDJDDM='"+user_jd+"')";
		}
		
		Criteria yjcri=Cnd.cri();
		yjcri.where().andEquals("COUNT_TYPE", "1");
		yjcri.where().andEquals("OPERATE_USER", ((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		yjcri.where().andEquals("to_char(OPERATE_DATE,'yyyymmdd')", today);
		//int rkfz=dao.count(DwLog.class,yjcri);
		//Fzlb fz =dao.fetch(Fzlb.class,Cnd.where("type", "=", "rkcx"));
			
		// 查询超出限制禁止查询
//				if(rkfz>=fz.getFzsx()&&"1".equals(fz.getStatus())){
//					queryflag = false;
//					Bjlb  bj=new Bjlb();
//					bj.setBjtime(new Date());
//					bj.setBjxq("rkcx");
//					bj.setType("rkcx");
//					bj.setUsername(((User) session
//						.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
//					bj.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//					dao.insert(bj);
//					result.put("warn", "您已超过查询次数上限");
//				}
		if (queryflag) {
			if(Strings.isEmpty(countflag)){
				Sql sql =Sqls.create("select rid,ZJHM,XM,CSRQ,xbdm,hjdjdhz from T_GA_RJBXX "
							+condition+" ");
				sql.setPager(pager);
				sql.setCallback(new SqlCallback() {
					
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
							throws SQLException {
						List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
						while (rs.next()) {
							Map<String, Object> person = new HashMap<String, Object>();
							person.put("xbdm", rs.getString("xbdm"));
							person.put("hjdjdhz", rs.getString("hjdjdhz"));
							person.put("rid", rs.getString("rid"));
							String name = rs.getString("XM");
							if(!Strings.isEmpty(name)){
								if (name.length() == 2) {
									person.put("xm",name.charAt(0) + "*");
								}else if (name.length() == 1) {
									person.put("xm","*");
								}else if (name.length() == 3) {
									person.put("xm",name.charAt(0)+"**");
								}else {
									person.put("xm",name.charAt(0)
											+ Strings.dup('*', name.length() - 2)
											+ name.charAt(name.length() - 1));
								}
								String csrq = rs.getString("CSRQ");
								if(!Strings.isEmpty(csrq))
									person.put("csrq",csrq.substring(0, 4) + "-"
										+ csrq.substring(4, 6));
							}
							String zjhm1 = rs.getString("ZJHM");
							if(!Strings.isEmpty(zjhm1)){
								if (zjhm1.length() == 18) {
									person.put("zjhm",zjhm1.substring(0, 6)
										+ Strings.dup("*", zjhm1.length() - 7)
										+ zjhm1.substring(zjhm1.length() - 1,
												zjhm1.length()));
								} else if (zjhm1.length() > 6) {
									person.put("zjhm",zjhm1.substring(0, 3)
										+ Strings.dup("*", zjhm1.length() - 6)
										+ zjhm1.substring(zjhm1.length() - 3,
												zjhm1.length()));
								} else {
									person.put("zjhm",Strings.dup('*', zjhm1.length() / 2)
										+ zjhm1.substring(zjhm1.length() / 2,
												zjhm1.length()));
								}
							}
							list.add(person);
						}
						return list;
					}
				});
				dao1.execute(sql);
				list =(List) sql.getResult();
				pager.setRecordCount(dao1.count(PersonInfo.class, Cnd.wrap(condition)));
				log.setQueryCount(list.size() + "");
			}	else {
				int countresult=dao1.count(PersonInfo.class, Cnd.wrap(condition));
				dao.insert(log);
				result.put("countresult", countresult);
			}

			if(!Strings.isEmpty(newquery)&&newquery.equals("1")){
				dao.insert(log);
			}
		} else {
			pager.setRecordCount(0);
			log.setQueryCount("0");
		}
		// 查询超出限制设置验证码才能继续查询
		int rkfz=dao.count(DwLog.class,yjcri);
		Fzlb fz =dao.fetch(Fzlb.class,Cnd.where("type", "=", "rkcx"));
		result.put("queryCount", rkfz);
		result.put("xzCount", fz.getFzsx());
		
		result.put("nl2", nl2);
		result.put("xm", xm);
		result.put("nl", nl);
		result.put("xb", xb);
		result.put("zjhm", zjhm);
		result.put("mz", mz);
		result.put("syrklb", syrklb);
		result.put("hjjd", hjjd);
		result.put("list", list);
		result.put("pager", pager);
		return result;
	}

	@At
	@Ok("json")
	public Map<String, Object> getRandomNumber() {
		Map<String, Object> result = new HashMap<String, Object>();
		String str="abcdefghigklmnopqrstuvwxyz0123456789";
        String code = "";
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
			code = code + str.charAt(rand.nextInt(36));
		}
        result.put("code",code );	
        return result;
	}
	
	@At
	@Ok("raw")
	public String getVerifyImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
        //生成随机字串  
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
        //存入会话session  
        HttpSession session = request.getSession(true);
        session.removeAttribute("verifyCode");
        session.setAttribute("rand", verifyCode.toLowerCase());
        //生成图片  
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        String result = verifyCode.toLowerCase();
        return result; 
    }
	
	@At
	@Ok("raw")
	public String checkVerifyCode(HttpServletRequest request, HttpServletResponse response,String validatorCode) {
		String rand = (String) request.getSession().getAttribute("rand");
		if (rand.equals(validatorCode.toLowerCase())) {
			return "TRUE";
		}
		return "FALSE";
	}

	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.query.person_view")
	public Map<String, Object> personView( String rid,
			HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		PersonInfo personInfo = dao1.fetch(PersonInfo.class,
				Cnd.where("RID", "=", rid));
		String csrq = personInfo.getCsrq();
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询人口详情");
		log.setCountType("2");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryCondition("RID=" + rid);
		log.setQueryTable("T_GA_RJBXX");
		log.setQueryCount(personInfo == null ? "0" : "1");
		dao.insert(log);
		if (!Strings.isEmpty(csrq)&&csrq.length() == 8) {
			personInfo.setCsrq(csrq.substring(0, 4) + "-"
					+ csrq.substring(4, 6) + "-" + csrq.substring(6, 8) );
		}
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		Sql sql =Sqls.create("select DEPTID from TB_PERSON where ZJHM ='"+personInfo.getZjhm()+"' group by DEPTID");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while(rs.next()){
					list.add(rs.getString("DEPTID"));
				}
				return list;
			}
		});
		dao.execute(sql);
		result.put("deptlist", (List<String>)sql.getResult());
		int count =0;
		int sqsw =0;
		int sgsrkcf = 0;
		int sgsrkxk = 0;
		if(!Strings.isEmpty(personInfo.getZjhm())){
			count =dao.count(XyxxInfo.class,Cnd.where("ZJHM", "=", personInfo.getZjhm()));
			sqsw =dao.count(CommunityAcceptanceAffairsApplyBean.class,Cnd.where("IDNO", "=", personInfo.getZjhm()));
			sgsrkcf = dao.count(PeopleCF.class, Cnd.where("CF_XDR_SFZ", "=", personInfo.getZjhm()));
			sgsrkxk = dao.count(PeopleXK.class, Cnd.where("XK_XDR_SFZ", "=", personInfo.getZjhm()));
		}
		result.put("count", count);
		result.put("userid", user.getUserId());
		result.put("person", personInfo);
		result.put("sqsw", sqsw);
		result.put("sgsrkcf", sgsrkcf);
		result.put("sgsrkxk", sgsrkxk);
		return result;
	}
	/**
	 * 导出法人列表
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@At
	public void exportCorpList(HttpServletRequest request,HttpServletResponse response,
			HttpSession session, String dwmc, String frzt,String frlx2,
			String frlx, String zczj1, String zczj2, Date zcrq1, Date zcrq2,
			String cylb1,String cylb2, String dmlx, String dmh,String jyfw,String jycs) throws IOException{
		Pager pager = dao.createPager(1, 200);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("导出法人登记信息列表");
		log.setCountType("5");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryTable("CORP_INFO");
		String condition = "where 1=1 ";
		Criteria cri = Cnd.cri();
		boolean queryflag = false;
		if(!Strings.isEmpty(cylb1)){
			queryflag = true;
			if(!Strings.isEmpty(cylb2)){
				cri.where().and("INDUSTRY_CODE","like",cylb2+"%");
				condition +=" and INDUSTRY_CODE like '%"+cylb2+"%'"; 
			}
			else {
				Sql sql =Sqls.create("select ID from DIC_FR_INDUSTRY_TYPE where INDUSTRY_TYPE ='"+cylb1+"'");
				sql.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
							throws SQLException {
						List<String> result = new ArrayList<String>();
						while(rs.next()){
							result.add(rs.getString(1));
						}
						return result;
					}
				});
				dao.execute(sql);
				List<String> list=(List<String>) sql.getResult();
				SqlExpressionGroup exps = null;
				if(list!=null&&list.size()>0){
					exps = Cnd.exps("INDUSTRY_CODE","like",list.get(0)+"%");
					condition=condition+" and (INDUSTRY_CODE like '%"+list.get(0)+"%'";
					for(int i=1;i<list.size();i++){
						exps.or("INDUSTRY_CODE","like",list.get(i)+"%");
						condition=condition+" or INDUSTRY_CODE like '%"+list.get(i)+"%'";
					}
					condition=condition+")";
				}else {
					exps = Cnd.exps("1","=","1");
				}
				cri.where().and(exps);
			}
		}
		if(!Strings.isEmpty(dmh)&&!Strings.isEmpty(dmlx)){
			cri.where().and(dmlx,"=",dmh);
			condition += " and "+dmlx+" = '"+dmh+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(dwmc)) {
			cri.where().and("CORP_NAME", "like", "%" + dwmc + "%");
			condition += " and CORP_NAME like '%" + dwmc + "%'";
			queryflag = true;
		}
		if (!Strings.isEmpty(frzt)) {
			cri.where().and("CORP_STATUS", "=", frzt);
			condition += " and CORP_STATUS = '" + frzt + "'";
			queryflag = true;
		}
		if (!Strings.isEmpty(frlx)) {
			if(!Strings.isEmpty(frlx2)){
				cri.where().and("CORP_TYPE", "=", frlx2);
				condition += " and CORP_TYPE = '" + frlx2 + "'";
			}else {
				cri.where().and("CORP_TYPE", "like", frlx + "%");
				condition += " and CORP_TYPE like '" + frlx + "%'";
			}
			queryflag = true;
		}
		if (!Strings.isEmpty(zczj1)) {
			cri.where().and("REG_CAPITAL", ">=", zczj1);
			condition += " and REG_CAPITAL >= '" + zczj1 + "'";
			queryflag = true;
		}
		if (!Strings.isEmpty(zczj2)) {
			cri.where().and("REG_CAPITAL", "<", zczj2);
			condition += " and REG_CAPITAL <= '" + zczj2 + "'";
			queryflag = true;
		}
		if (!Strings.isEmpty(jyfw)) {
			cri.where().and("BUSINESS_SCOPE", "like", "%" + jyfw + "%");
			condition += " and BUSINESS_SCOPE like '%" + jyfw + "%'";
			queryflag = true;
		}
		if (!Strings.isEmpty(jycs)) {
			cri.where().and("ADDRESS", "like", "%" + jycs + "%");
			condition += " and ADDRESS like '%" + jycs + "%'";
			queryflag = true;
		}
		if (zcrq1 != null) {
			cri.where().and("ESTABLISH_DATE", ">", zcrq1);
			condition += " and to_char(ESTABLISH_DATE,'yyyymmdd')>= '" +new SimpleDateFormat("yyyyMMdd").format(zcrq1)  + "'";
			queryflag = true;
		}
		if (zcrq2 != null) {
			cri.where().and("ESTABLISH_DATE", "<=", zcrq2);
			condition += " and to_char(ESTABLISH_DATE,'yyyymmdd') <= '" + new SimpleDateFormat("yyyyMMdd").format(zcrq2)  + "'";
			queryflag = true;
		}
		log.setQueryCondition(condition);
		cri.getOrderBy().desc("ESTABLISH_DATE");
		if (queryflag) {
			Sql sql =Sqls.create("select CORP_NAME,ORGAN_CODE,PERSON_NAME,CORP_STATUS,ESTABLISH_DATE from corp_info   "+condition+" order by ESTABLISH_DATE desc nulls last");
			sql.setCondition(cri);
			sql.setPager(pager);
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
					while (rs.next()) {
						ArrayList<String> row = new ArrayList<String>(5);
						for(int i=1;i<6;i++){
							row.add(rs.getString(i));
						}
						list.add(row);
					}
					return list;
				}
			});
			dao.execute(sql);
			List<ArrayList<String>> list = (List<ArrayList<String>>) sql.getResult();
			log.setQueryCount(list.size() + "");
			dao.insert(log);
			
			XSSFWorkbook workbook = new XSSFWorkbook();
			Sheet sheet1 = workbook.createSheet("法人列表");
			Row row = sheet1.createRow(0);
			String[] header = {"法人名称","组织机构代码","法定代表人","法人状态","成立日期"};
			for (int i= 0;i<header.length;i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(header[i]);
			}
			DicDataUtils dicutil = DicDataUtils.getInstance();
			for(int j = 0 ;j < list.size() ; j++){
				 Row row1 = sheet1.createRow(j+1);
				 if(list.get(j) != null){
					for(int k = 0; k < list.get(j).size() ; k ++){
						Cell cell1 = row1.createCell(k);
						if(k==3){	
							cell1.setCellValue(dicutil.getDicData(1041,list.get(j).get(k)));
						}else{
							cell1.setCellValue(list.get(j).get(k));
						}	
					}
				}
			}
			
			/** **********将以上缓存中的内容写到EXCEL文件中******** */
			String filename = UUID.randomUUID().toString();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
	        try
	        {
	        	workbook.write(os);
	        } catch (IOException e){
	            e.printStackTrace();
	        }
	        byte[] content = os.toByteArray();
	        File file = new File(filepath+filename+".xlsx");//Excel文件生成后存储的位置。
	        OutputStream fos  = null;
	        try
	        {
	            fos = new FileOutputStream(file);
	            fos.write(content);
	            os.close();
	            fos.close();
	        }catch (Exception e){
	            e.printStackTrace();
	        }     
	        POIFSFileSystem fs = new POIFSFileSystem();  
	        EncryptionInfo info = new EncryptionInfo(fs, EncryptionMode.agile);  
	        org.apache.poi.poifs.crypt.Encryptor enc = info.getEncryptor();  

	        // set the password  
	        enc.confirmPassword(sendMail((User)session.getAttribute(SystemConstants.SYSTEM_USER)));  

	        // encrypt the file  
	        try{
	        	 OPCPackage opc = OPCPackage.open(new File(filepath+filename+".xlsx"),PackageAccess.READ_WRITE);  
	             OutputStream os1 = enc.getDataStream(fs);  
	             opc.save(os1);  
	             opc.close();  
	        }catch (Exception e){
	        	 e.printStackTrace();
	        }
	         
	        FileOutputStream fos1 = new FileOutputStream(filepath+filename+".xlsx");
	        fs.writeFilesystem(fos1);
	        fos1.close();
	       
	        try {
				
				File downfile = new File(filepath+filename+".xlsx");
				InputStream inStream = new FileInputStream(downfile);
				OutputStream outStream = response.getOutputStream();
				response.reset();
				response.addHeader("Content-Disposition", "attachment; filename="
						+ URLEncoder.encode("法人列表.xlsx", "utf-8"));
				int tempbyte;
				while ((tempbyte = inStream.read()) != -1) {
					outStream.write(tempbyte);
					outStream.flush();
				}
				outStream.close();
				inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		
		}
	}
	@SuppressWarnings({ "rawtypes"})
	@At
	@Ok("jsp:jsp.query.corpinfo_list")
	public Map<String, Object> toCorpInfoList(HttpServletRequest request,
			HttpSession session, String dwmc, String frzt,String frlx2,String jyfw,String jycs,
			String frlx, String zczj1, String zczj2, Date zcrq1, Date zcrq2,
			String cylb1,String cylb2,String dmlx, String dmh,String newquery){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String today = df.format(new Date());
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询法人登记信息列表");
		log.setCountType("3");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryTable("CORP_INFO");
		String condition = "where 1=1 ";
		Criteria cri = Cnd.cri();
		boolean queryflag = false;
		if(!Strings.isEmpty(cylb1)){
			queryflag = true;
			if(!Strings.isEmpty(cylb2)){
				cri.where().and("INDUSTRY_CODE","like",cylb2+"%");
				condition +=" and INDUSTRY_CODE like '%"+cylb2+"%'"; 
			}
			else {
				Sql sql =Sqls.create("select ID from DIC_FR_INDUSTRY_TYPE where INDUSTRY_TYPE ='"+cylb1+"'");
				sql.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
							throws SQLException {
						List<String> result = new ArrayList<String>();
						while(rs.next()){
							result.add(rs.getString(1));
						}
						return result;
					}
				});
				dao.execute(sql);
				@SuppressWarnings("unchecked")
				List<String> list=(List<String>) sql.getResult();
				SqlExpressionGroup exps = null;
				if(list!=null&&list.size()>0){
					exps = Cnd.exps("INDUSTRY_CODE","like",list.get(0)+"%");
					condition=condition+" and (INDUSTRY_CODE like '%"+list.get(0)+"%'";
					for(int i=1;i<list.size();i++){
						exps.or("INDUSTRY_CODE","like",list.get(i)+"%");
						condition=condition+" or INDUSTRY_CODE like '%"+list.get(i)+"%'";
					}
					condition=condition+")";
				}else {
					exps = Cnd.exps("1","=","1");
				}
				cri.where().and(exps);
			}
		}
		if (!Strings.isEmpty(dwmc)) {
			cri.where().and("CORP_NAME", "like", "%" + dwmc + "%");
			condition += " and CORP_NAME like '%" + dwmc + "%'";
			queryflag = true;
		}
		if(!Strings.isEmpty(dmh)&&!Strings.isEmpty(dmlx)){
			cri.where().and(dmlx,"=",dmh);
			condition += " and "+dmlx+" = '"+dmh+"'";
			queryflag = true;
		}
		if (!Strings.isEmpty(frzt)) {
			cri.where().and("CORP_STATUS", "=", frzt);
			condition += " and CORP_STATUS = '" + frzt + "'";
			queryflag = true;
		}
		if (!Strings.isEmpty(frlx)) {
			if(!Strings.isEmpty(frlx2)){
				cri.where().and("CORP_TYPE", "=", frlx2);
				condition += " and CORP_TYPE = '" + frlx2 + "'";
			}else {
				cri.where().and("CORP_TYPE", "like", frlx + "%");
				condition += " and CORP_TYPE like '" + frlx + "%'";
			}
			queryflag = true;
		}
		if(!Strings.isEmpty(zczj1)&&!Strings.isEmpty(zczj2)){
			if(Integer.parseInt(zczj1)>Integer.parseInt(zczj2)){
				String zczj=zczj1;
				zczj1=zczj2;
				zczj2=zczj;
			}
		}
		if (!Strings.isEmpty(zczj1)) {
			cri.where().and("REG_CAPITAL", ">=", zczj1);
			condition += " and REG_CAPITAL >= '" + zczj1 + "'";
			queryflag = true;
		}
		if (!Strings.isEmpty(zczj2)) {
			cri.where().and("REG_CAPITAL", "<", zczj2);
			condition += " and REG_CAPITAL <= '" + zczj2 + "'";
			queryflag = true;
		}
		if (zcrq1 != null) {
			cri.where().and("ESTABLISH_DATE", ">", zcrq1);
			condition += " and to_char(ESTABLISH_DATE,'yyyymmdd')>= '" +new SimpleDateFormat("yyyyMMdd").format(zcrq1)  + "'";
			queryflag = true;
		}
		if (zcrq2 != null) {
			cri.where().and("ESTABLISH_DATE", "<=", zcrq2);
			condition += " and to_char(ESTABLISH_DATE,'yyyymmdd') <= '" + new SimpleDateFormat("yyyyMMdd").format(zcrq2)  + "'";
			queryflag = true;
		}
		if (!Strings.isEmpty(jyfw)) {
			cri.where().and("BUSINESS_SCOPE", "like", "%" + jyfw + "%");
			condition += " and BUSINESS_SCOPE like '%" + jyfw + "%'";
			queryflag = true;
		}
		if (!Strings.isEmpty(jycs)) {
			cri.where().and("ADDRESS", "like", "%" + jycs + "%");
			condition += " and ADDRESS like '%" + jycs + "%'";
			queryflag = true;
		}
		List list = null;
		log.setQueryCondition(condition);
		cri.getOrderBy().desc("ESTABLISH_DATE");
		Criteria crilb=Cnd.cri();
		crilb.where().andEquals("COUNT_TYPE", "3");
		crilb.where().andEquals("OPERATE_USER", ((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		crilb.where().andEquals("to_char(OPERATE_DATE,'yyyymmdd')", today);
		int lbfz=dao.count(DwLog.class,crilb);
		Fzlb fz =dao.fetch(Fzlb.class,Cnd.where("type", "=", "frcx"));
		if(lbfz>=fz.getFzsx()&&"1".equals(fz.getStatus())){
			queryflag = false;
			Bjlb  bj=new Bjlb();
			bj.setBjtime(new Date());
			bj.setBjxq("frcx");
			bj.setType("frcx");
			bj.setUsername(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
			bj.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			dao.insert(bj);
			result.put("warn", "您已超过查询次数上限");
		}
		Fzlb frlb =dao.fetch(Fzlb.class,Cnd.where("type", "=", "frlb"));
		Criteria crixz=Cnd.cri();
		crixz.where().andEquals("COUNT_TYPE", "5");
		crixz.where().andEquals("OPERATE_USER", ((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		crixz.where().andEquals("to_char(OPERATE_DATE,'yyyymmdd')", today);
		int xzfz=dao.count(DwLog.class,crixz);
		if(xzfz<frlb.getFzsx()||"0".equals(frlb.getStatus())){
			result.put("flag", 1);
		}else{
			result.put("flag", 0);
		}
		if (queryflag) {
			Sql sql =Sqls.create("select CORP_INFO_ID,CORP_NAME,ORGAN_CODE,PERSON_NAME,CORP_STATUS,ESTABLISH_DATE from corp_info "+condition+" order by ESTABLISH_DATE desc nulls last");
			sql.setPager(pager);
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					while (rs.next()) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("corpinfoid", rs.getObject("CORP_INFO_ID"));
						map.put("corpname", rs.getString("CORP_NAME"));
						map.put("organcode", rs.getString("ORGAN_CODE"));
						map.put("personname", rs.getString("PERSON_NAME"));
						map.put("corpstatus", rs.getString("CORP_STATUS"));
						map.put("establishdate", rs.getDate("ESTABLISH_DATE"));
						list.add(map);
					}
					return list;
				}
			});
			dao.execute(sql);
			//list = dao.query(CorpInfo.class, cri, pager);
			list = (List) sql.getResult();
			pager.setRecordCount(dao.count(CorpInfo.class, cri));
			log.setQueryCount(list.size() + "");
			if(!Strings.isEmpty(newquery)&&newquery.equals("1")){
				dao.insert(log);
			}
		}
		result.put("dmh", dmh);
		result.put("list", list);
		result.put("cylb1", cylb1);
		result.put("cylb2", cylb2);
		result.put("dwmc", dwmc);
		result.put("dmlx", dmlx);
		result.put("frzt", frzt);
		result.put("frlx", frlx);
		result.put("frlx2", frlx2);
		result.put("zczj1", zczj1);
		result.put("zczj2", zczj2);
		result.put("zcrq1", zcrq1);
		result.put("zcrq2", zcrq2);
		result.put("jyfw", jyfw);
		result.put("jycs", jycs);
		result.put("pager", pager);
		result.put("lbfz", frlb.getFzsx());
		result.put("", pager);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.corp_view")
	public Map<String, Object> viewCorpInfo(String corpinfoid,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("CORP_INFO_ID","=",corpinfoid);
		CorpInfo corp = dao.fetch(CorpInfo.class,cri);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询法人登记信息详情");
		log.setCountType("4");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryCondition("CORP_INFO_ID=" + corpinfoid);
		log.setQueryTable("CORP_INFO");
		log.setQueryCount(corp == null ? "0" : "1");
		dao.insert(log);
		Criteria crixq=Cnd.cri();
		crixq.where().andEquals("COUNT_TYPE", "6");
		crixq.where().andEquals("OPERATE_USER", ((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		crixq.where().andEquals("to_char(OPERATE_DATE,'yyyymmdd')", new SimpleDateFormat("yyyyMMdd").format(new Date()));
		int xqfz=dao.count(DwLog.class,crixq);
		Fzlb fz =dao.fetch(Fzlb.class,Cnd.where("type", "=", "frxq"));
		if(xqfz<fz.getFzsx()||"0".equals(fz.getStatus())){
			result.put("flag", 1);
		}else{
			result.put("flag", 0);
		}
		Sql sql = Sqls.create("select DEPTID from TB_CORP where ( (SHTYXYDM is not null and SHTYXYDM ='"+corp.getUniscid()+"')"
				+ " or ( ZZJGDM is not null and ZZJGDM ='"+corp.getOrgancode()+"' ) "
				+ " or ( YYZZZCH is not null and YYZZZCH = '"+corp.getRegno()+"') "
				+ " or ( NSRSBH is not null and NSRSBH ='"+corp.getTaxpayerscode()+"')"
				+ " or ( CORP_NAME is not null and CORP_NAME ='"+corp.getCorpname()+"')) group by DEPTID"); 
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet resultset, Sql sql)
					throws SQLException {
				List<String> result = new ArrayList<String>();
				while(resultset.next()){
					result.add(resultset.getString("DEPTID"));
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		List<String> deptlist =(List<String>) sql.getResult();
		int xyxx =0;
		if(!Strings.isEmpty(corp.getOrgancode())){
			 xyxx=dao.count(XyxxInfo.class,Cnd.where("ZJHM", "=", corp.getOrgancode()));
		}
		if(!Strings.isEmpty(corp.getCorpname())&&xyxx==0){
			xyxx=dao.count(XyxxInfo.class,Cnd.where("NAME", "=", corp.getCorpname()));
		}
		int build=0;
		if(!Strings.isEmpty(corp.getUniscid())){
			build=dao.count(BuildingCorp.class,Cnd.where("LICENCECODE", "=", corp.getUniscid()));
		}
		if(!Strings.isEmpty(corp.getCorpname())&&build==0){
			build=dao.count(BuildingCorp.class,Cnd.where("CORPNAME", "=", corp.getCorpname()));
		}
		int wssp=dao.count(PtQfbWangtingBean.class,Cnd.where("APPLICANT","=",corp.getCorpname()));
		int sgsfrcf = dao.count(CorpCF.class, Cnd.where("CF_XDR_MC", "=", corp.getCorpname()));
		int sgsfrxk = dao.count(CorpXK.class, Cnd.where("XK_XDR", "=", corp.getCorpname()));
		
		result.put("build", build);
		result.put("xyxx", xyxx);
		result.put("deptlist", deptlist);
		result.put("corp", corp);
		result.put("wssp", wssp);
		result.put("sgsfrcf", sgsfrcf);
		result.put("sgsfrxk", sgsfrxk);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.corplicense_list")
	public Map<String, Object> toCorpLicenseList(String corpinfoid,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		cri.where().and("CORP_INFO_ID","=",corpinfoid);
		cri.getOrderBy().desc("START_DATE");
		List<CorpLicense> corpLicenses=dao.query(CorpLicense.class, cri ,pager);
		for(CorpLicense corpLicense:corpLicenses){
			String licensetype=corpLicense.getLicensetype();
			licensetype=DicDataUtils.getInstance().getDic(1060).get(licensetype);
			if(licensetype.length()>15){
				corpLicense.setLicensetype(licensetype.substring(0,14)+"...");
			}else {
				corpLicense.setLicensetype(licensetype);
			}
		}
		pager.setRecordCount(dao.count(CorpLicense.class, cri));
		result.put("corpLicenses", corpLicenses);
		result.put("corpinfoid", corpinfoid);
		result.put("pager", pager);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.corplicense_view")
	public Map<String, Object> viewCorpLicense(String licenseid,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("LICENSE_ID","=",licenseid);
		CorpLicense license=dao.fetch(CorpLicense.class , cri);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询法人资质信息详细信息");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryCondition("LICENSE_ID=" + licenseid);
		log.setQueryTable("CORP_LICENSE");
		log.setQueryCount(license == null ? "0" : "1");
		dao.insert(log);
		result.put("license", license);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.punishnoteenty_list")
	public Map<String, Object> toPunishNoteEntyList(String corpinfoid,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Sql sql = Sqls.create("select PUNISH_ENTY_ID from PUNISH_NOTE_CORP_ENTY where CORP_INFO_ID = '"+corpinfoid+"'");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<String> result = new ArrayList<String>();
				while (rs.next()) {
					result.add(rs.getString("PUNISH_ENTY_ID"));
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		List<String> punishids = (List<String>) sql.getResult();
		
		List<PunishNoteEnty> punishNoteEnties = null;
		if(punishids!=null&&punishids.size()>0){
			Criteria cri = Cnd.cri();
			cri.where().and("PUNISH_ENTY_ID","in", punishids);
			punishNoteEnties = dao.query(PunishNoteEnty.class, cri,pager);
			pager.setRecordCount(dao.count(PunishNoteEnty.class, cri));
		}
		result.put("punishNoteEnties", punishNoteEnties);
		result.put("pager", pager);
		return result;
	}
	@At
	@Ok("jsp:jsp.query.punishnoteenties_view")
	public Map<String, Object> viewPunishNoteEnties(String punishentyid,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("PUNISH_ENTY_ID","=",punishentyid);
		PunishNoteEnty punishNoteEnty = dao.fetch(PunishNoteEnty.class,cri);
		PunishNoteInfoEnty punishNoteInfoEnty =dao.fetch(PunishNoteInfoEnty.class, Cnd.where("PUNISH_ENTY_ID", "=", punishentyid));
		if(!Strings.isEmpty(punishNoteInfoEnty.getPunishtype())){
			String pt = DicDataUtils.getInstance().getDicData(1060, punishNoteInfoEnty.getPunishtype());
			result.put("punishtype", pt);
		}
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询法人处罚信息详细信息");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryCondition("PUNISH_ENTY_ID=" + punishentyid);
		log.setQueryTable("PUNISH_NOTE_ENTY");
		log.setQueryCount(punishNoteEnty == null ? "0" : "1");
		dao.insert(log);
		result.put("punishNoteEnty", punishNoteEnty);
		return result;
	}
	//首页处罚信息
	@At
	@Ok("jsp:jsp.query.corp_punish.corp_punish")
	public Map<String, Object> tocorp_punish(String year, String quarter){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("year",year);
		result.put("quarter",quarter);
		return result;
	}
	//首页处罚信息详情列表
		@SuppressWarnings("unchecked")
		@At
		@Ok("jsp:jsp.query.corp_punish.corp_punish_list")
		public Map<String, Object> tocorp_punish_list(String year, String quarter){
			Map<String, Object> result = new HashMap<String, Object>();
			Sql sql = Sqls.create("select CORP_NAME,ORGAN_CODE,PUNISH_UNIT,PUNISH_ENTY_ID,to_char(PUNISH_DATE,'YYYY-MM-DD') as SETDATE from PUNISH_NOTE_ENTY where to_char(PUNISH_DATE,'YYYY-Q') = '"+year+"-"+quarter+"' order by setdate asc");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("corpname", rs.getString("CORP_NAME"));
						map.put("organcode", rs.getString("ORGAN_CODE"));
						map.put("punishdate", rs.getString("SETDATE"));
						map.put("punishiunit", rs.getString("PUNISH_UNIT"));
						map.put("punishEntyId", rs.getString("PUNISH_ENTY_ID"));
						result.add(map);
					}
					return result;
				}
			});
			dao.execute(sql);
			List<Object> list =(List<Object>) sql.getResult();
			result.put("year",year);
			result.put("quarter",quarter);
			result.put("list",list);
			return result;
		}
		//首页园区信息
		@At
		@Ok("jsp:jsp.query.industrial_park.industrial_park")
		public Map<String, Object> toindustrial_park(String series, String data){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("series",series);
			result.put("data",data);
			return result;
		}
		//首页园区详细信息
			@SuppressWarnings("unchecked")
			@At
			@Ok("jsp:jsp.query.industrial_park.industrial_park_list")
			public Map<String, Object> toindustrial_park_list(String series, String data){
				Map<String, Object> result = new HashMap<String, Object>();
				if( series.equals("0") && data.equals("0") )
					{
						Sql sql = Sqls.create("select S2_DATA5 from TB_KW_JJL where S2_DATA5 is not null");
						sql.setCallback(new SqlCallback() {
							@Override
							public Object invoke(Connection connection, ResultSet rs, Sql sql)
									throws SQLException {
									List<Object> result = new ArrayList<Object>();
									while (rs.next()){
										Map<String, Object> map = new HashMap<String, Object>();
										map.put("yqmc", rs.getString("S2_DATA5") );
										result.add(map);
									}
								return result;
							}
						});
						dao.execute(sql);
						List<Object> list = (List<Object>) sql.getResult();
						result.put("list",list);
					}
				if( series.equals("0") && data.equals("1") )
				{
					Sql sql = Sqls.create("select S3_DATA5 from TB_KW_JJL where S3_DATA5 is not null");
					sql.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection connection, ResultSet rs, Sql sql)
								throws SQLException {
								List<Object> result = new ArrayList<Object>();
								while (rs.next()){
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("yqmc", rs.getString("S3_DATA5") );
									result.add(map);
								}
							return result;
						}
					});
					dao.execute(sql);
					List<Object> list = (List<Object>) sql.getResult();
					result.put("list",list);
				}
				if( series.equals("0") && data.equals("2") )
				{
					Sql sql = Sqls.create("select S1_DATA7 from TB_WH_JJL where S1_DATA7 is not null");
					sql.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection connection, ResultSet rs, Sql sql)
								throws SQLException {
								List<Object> result = new ArrayList<Object>();
								while (rs.next()){
									Map<String, Object> map = new HashMap<String, Object>();
									map.put("yqmc", rs.getString("S1_DATA7") );
									result.add(map);
								}
							return result;
						}
					});
					dao.execute(sql);
					List<Object> list = (List<Object>) sql.getResult();
					result.put("list",list);
				}
				
				
				return result;
			}
		//房屋查询列表
		@SuppressWarnings("unchecked")
		@At
		@Ok("jsp:jsp.query.people_house.houselist")
		public Map<String, Object> toHouselist(HttpServletRequest request,HttpSession session,String addr){
			Map<String, Object> result = new HashMap<String, Object>();
			Pager pager = ConUtils.makePaginationPager(request);
			DwLog log = new DwLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setOperateDate(new Date());
			log.setOperateType("查询房屋信息");
			log.setOperateUser(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
			log.setOperateDept(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
			log.setCountType("8");
			String condition = "where 1=1 ";
			boolean queryflag = false; // 无条件时不查询
			String house = null;
			if(!Strings.isEmpty(addr)){
				 house = addr.trim();
			}
			if (!Strings.isEmpty(house)){
				result.put("addr", addr);
				addr = addr.replaceAll(" ", "%");
				condition += " and (hjdz like '%" + addr + "%' or JZDZ like '%" + addr + "%') ";
				queryflag = true;
			}
			log.setQueryCondition(condition);
			log.setQueryTable("TB_HOUSE");
			if(queryflag){
			Sql sql = Sqls.create("select * from tb_house " 
					+ condition + " ");
			sql.setPager(pager);
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("fwbm",		rs.getString("FWID"));
						map.put("hjdz",		rs.getString("HJDZ"));
						map.put("jzdz",		rs.getString("JZDZ"));
						result.add(map);
					}
					return result;
				}
				
			});
			dao1.execute(sql);
			List<Object> list =(List<Object>) sql.getResult();
			sql = Sqls.create("select count(1) from ( select * from tb_house " 
					+ condition + " ) ");
			sql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
					if(rs.next())
						return rs.getInt(1);
					return 0;
				}
			});
			dao1.execute(sql);
			pager.setRecordCount((Integer) sql.getResult());
			log.setQueryCount(list.size() + "");
			dao.insert(log);
			result.put("list",list);
			}
			result.put("pager", pager);
			return result;
		}
		
		/**
		 * 房屋室号展现
		 * @param request
		 * @param session
		 * @param road
		 * @param nong
		 * @param number
		 * @return
		 */
		@SuppressWarnings("unchecked")
		@At
		@Ok("jsp:jsp.query.people_house.house_show")
		public Map<String, Object> toShowHouse(HttpServletRequest request, HttpSession session,String road,String nong,String number){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("road", road);
			result.put("nong", nong);
			result.put("number", number);
			Pager pager = ConUtils.makePaginationPager(request);
			DwLog log = new DwLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setOperateDate(new Date());
			log.setOperateUser(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
			log.setOperateDept(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
			log.setCountType("8");
			String condition = "1=1 ";
			String house = null;
			boolean queryflag = false; // 无条件时不查询
			if (!Strings.isEmpty(road)) {
				if (!Strings.isEmpty(nong)) {
					String road1 = road.replace(" ", "%");
					String nong1 = nong.replace(" ", "%");
					String number1 = number.replace(" ", "%");
					if (nong.contains("支")) {
						house = road1+ "%" + nong1.replace("支弄", "支") + "弄" + number1.replace("号", "") + "号%室";
						String logHouse = house.replaceAll("%", "");
						log.setOperateType("查询房屋信息-"+ logHouse.replaceAll("室", ""));
					}else{
						house = road1+ "%" + nong1.replaceAll("弄", "") + "弄" + number1.replace("号", "") + "号%室";
						String logHouse = house.replaceAll("%", "");
						log.setOperateType("查询房屋信息-"+ logHouse.replaceAll("室", ""));
					}
					condition += " and (hjdz like '%" + house +"%') order by hjdz asc";
				}else {
					house = road + "%弄" + number.replace("号", "") + "号%室";
					String logHouse = house.replaceAll("%", "");
					log.setOperateType("查询房屋信息-"+ logHouse.replaceAll("室", ""));
					condition += " and (hjdz like '%" + house +"%') order by hjdz asc";
				}
				queryflag = true;
			}
			
			log.setQueryCondition(condition);
			log.setQueryTable("TB_HOUSE");
			if(queryflag){
				List<TbHouseBo> list = dao.query(TbHouseBo.class, Cnd.wrap(condition),pager);
				List<Object> houseList = new ArrayList<Object>();
				List<Object> nongList = new ArrayList<Object>();
				Map<String, Object> houseInfo = new HashMap<String, Object>();
				if (!Strings.isEmpty(nong) && list.size() > 0) {
					if (!nong.contains("支弄")) {
						houseInfo.put("name", road+"路"+nong+"弄"+number+"号");
						for (TbHouseBo info : list) {
							Map<String, String> nongInfo = new HashMap<String, String>();
							nongInfo.put("name", info.getHjdz().split("号")[1]);
							nongList.add(nongInfo);
						}
						houseInfo.put("children", nongList);
						houseList.add(houseInfo);
						result.put("houseList", JSONArray.fromObject(houseList));
					}else {
						result.put("houseList", null);
					}
				}else {
					result.put("houseList", null);
				}
				
				Sql sql = Sqls.create("select count(1) from ( select * from tb_house where " 
						+ condition + " ) ");
				sql.setCallback(new SqlCallback() {
					
					@Override
					public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
						if(rs.next())
							return rs.getInt(1);
						return 0;
					}
				});
				dao1.execute(sql);
				pager.setRecordCount((Integer) sql.getResult());
				log.setQueryCount(list.size() + "");
				dao.insert(log);
				result.put("list",list);
			}
			result.put("pager", pager);
			return result;
		}
		
		//房屋查询详细首页
		@SuppressWarnings("unchecked")
		@At
		@Ok("jsp:jsp.query.people_house.address_index")
		public Map<String, Object> toHouseindex(HttpServletRequest request, HttpSession session,String hjfwid){
			Map<String, Object> result = new HashMap<String, Object>();
			User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
			/*Sql sql = Sqls.create("select hjdxzqhhz||hjdz as hjdz,HJDJDHZ,HJDJWHHZ from t_ga_rjbxx  where HJFWID = '"
					+hjfwid+"' ");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					while (rs.next()){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("address",	rs.getString("hjdz"));
						map.put("jcwmc",	rs.getString("HJDJWHHZ"));
						map.put("jdmc",		rs.getString("HJDJDHZ"));
						return map;
					}
					return null;
				}
				
			});
			dao1.execute(sql);
			Map<String,Object> map1 = (Map<String, Object>) sql.getResult();
			result.put("map1",map1);
			sql = Sqls.create("select JZDXZQHHZ||JZDZ as jzdz,JZDJDHZ,JZDJWHHZ from t_ga_rjbxx  where JZFWID = '"
					+hjfwid+"' ");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					while (rs.next()){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("address",	rs.getString("jzdz"));
						map.put("jcwmc",	rs.getString("JZDJWHHZ"));
						map.put("jdmc",		rs.getString("JZDJDHZ"));
						return map;
					}
					return null;
				}
				
			});
			dao1.execute(sql);
			Map<String,Object> map2 = (Map<String, Object>) sql.getResult();
			result.put("map2",map2);*/
			Sql sql = Sqls.create("select * from"
					+ "(select xm,zjhm,xbhz,csrq,hjfwid,SYRKLBDM,hjdxzqhhz||hjdz as address "
	//				+ ",hyzkhz,fwcs,hh,yhzgxhz,whcdhz,is_kgh,"
	//				+ "decode(yhzgxhz,'户主',1,'配偶',2,'夫',2,'妻',2,3) as Relation_order "
					+ "from t_ga_rjbxx t)"
					+ "where hjfwid = '"+hjfwid+"' and SYRKLBDM = '01' "
	//						+ "order by Relation_order,csrq,is_kgh"
							+ "");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("xm",		rs.getString("xm"));
						map.put("zjhm",		rs.getString("zjhm"));
						map.put("xbhz",		rs.getString("xbhz"));
						map.put("csrq",		rs.getString("csrq"));
						map.put("address",	rs.getString("address"));
						/*map.put("hyzkhz",	rs.getString("hyzkhz"));
						map.put("fwcs",		rs.getString("fwcs"));
						map.put("hh",		rs.getString("hh"));
						map.put("yhzgxhz",	rs.getString("yhzgxhz"));
						
						map.put("whcdhz",	rs.getString("whcdhz"));
						String status = rs.getString("is_kgh");
						if( status.equals("1") ){
							map.put("is_kgh",	"是" );
						}else if( status.equals("0") ){
							map.put("is_kgh",	"否" );
						}else{
							map.put("is_kgh",	status );
						}*/
						result.add(map);
					}
					return result;
				}
				
			});
			dao1.execute(sql);
			List<Object> list1 =(List<Object>) sql.getResult();
			result.put("list1",list1);
			int res1 = list1.size();
			sql = Sqls.create("select * from"
					+ "(select JZFWID,xm,xbhz,zjzlhz,zjhm,jzdxzqhhz||jzdz as address"
	//				+ ",syrklbhz,syrkzlbhz,whcdhz,YHZGXHZ,RJSJ,SYRKLBDM,hjdz,hyzkhz,jghz"
					+ " from t_ga_rjbxx t) where JZFWID = '"+hjfwid+"' ");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					List<Object> result = new ArrayList<Object>();
					while (rs.next()){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("xm",		rs.getString("xm"));
						map.put("xbhz",		rs.getString("xbhz"));
						map.put("zjzlhz",	rs.getString("zjzlhz"));
						map.put("zjhm",		rs.getString("zjhm"));
						map.put("address",	rs.getString("address"));
						/*String lb1 = rs.getString("syrklbhz");
						String lb2 = rs.getString("syrkzlbhz");
						if( lb2 == "" || lb2 == null ){
							map.put("rklb",		lb1);
						}else{
							map.put("rklb",		lb1+"（"+lb2+"）");
						}
						map.put("yhzgxhz",	rs.getString("yhzgxhz"));
						map.put("rjsj",		rs.getString("rjsj"));
						map.put("whcdhz",	rs.getString("whcdhz"));
						
						map.put("hjdz",		rs.getString("hjdz"));
						map.put("hyzkhz",	rs.getString("hyzkhz"));
						map.put("jghz",		rs.getString("jghz"));*/
						result.add(map);
					}
					return result;
				}
			});
			dao1.execute(sql);
			List<Object> list2 =(List<Object>) sql.getResult();
			result.put("list2",list2);
			int res2 = list2.size();
			if( res1 == 0 && res2 == 0 ){
				result.put("flag", "0");
			}
			if( res1 > 0 && res2 == 0 ){
				result.put("flag", "1");
			}
			if( res1 == 0 && res2 > 0 ){
				result.put("flag", "2");
			}
			if( res1 > 0 && res2 > 0 ){
				result.put("flag", "3");
			}
			result.put("hjfwid", hjfwid);
			result.put("userid", user.getUserId());
			return result;
		}
		
		//自然人数据比对
		@SuppressWarnings("unchecked")
		@At
		@Ok("jsp:jsp.query.person_contrast")
		public Map<String, Object> toPersoncontrast(HttpServletRequest request,HttpSession session,String hjdz,
				String jzdz ,String zjhm ,String xm){
			Map<String, Object> result = new HashMap<String, Object>();
			DwLog log = new DwLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setOperateDate(new Date());
			log.setOperateType("自然人数据比对");
			log.setOperateUser(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
			log.setOperateDept(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
			log.setCountType("9");
			String condition = "where 1=1 ";
			boolean queryflag = false; // 无条件时不查询
			if (!Strings.isEmpty(zjhm)){
				String str1 = zjhm.replaceAll(" ", "");
				condition += " and zjhm = '" + str1 + "' ";
				queryflag = true;
			}
			log.setQueryCondition(condition);
			log.setQueryTable("T_GA_RJBXX");
			if(queryflag){
			Sql sql = Sqls.create("select HJDXZQHHZ||hjdz as huji,JZDXZQHHZ||jzdz as juzhu,xm,zjhm from t_ga_rjbxx "
					+ condition + "  ");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					if (rs.next()){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("zjhm",		rs.getString("zjhm"));
						map.put("huji",		rs.getString("huji"));
						map.put("juzhu",		rs.getString("juzhu"));
						map.put("xm",		rs.getString("xm"));
						return map;
					}
					return null;
				}
			});
			dao1.execute(sql);
			Map<String, Object> map = (Map<String, Object>) sql.getResult();
			result.put("map", map);
			dao.insert(log);
			}
			if (!Strings.isEmpty(xm)){
				String str2 = xm.replaceAll(" ", "");
				result.put("xm", str2);
			}else{
				result.put("xm", xm);
			}
			if (!Strings.isEmpty(hjdz)){
				String str3 = hjdz.replaceAll(" ", "");
				result.put("hjdz", str3);
			}else{
				result.put("hjdz", hjdz);
			}
			if (!Strings.isEmpty(jzdz)){
				String str4 = jzdz.replaceAll(" ", "");
				result.put("jzdz", str4);
			}else{
				result.put("jzdz", jzdz);
			}
			if (!Strings.isEmpty(zjhm)){
				String str5 = zjhm.replaceAll(" ", "");
				result.put("zjhm", str5);
			}else{
				result.put("zjhm", zjhm);
			}
			return result;
		}
		//法人数据比对
		@SuppressWarnings("unchecked")
		@At
		@Ok("jsp:jsp.query.corp_contrast")
		public Map<String, Object> toCorpcontrast(HttpServletRequest request,HttpSession session,String tyshxydm,
				String zzjgdm,String yyzzzch,String nsrsbh,String frmc,
				String fddbrmc ,String zczj,String zcdz ,String dmlx, String dmh){
			Map<String, Object> result = new HashMap<String, Object>();
			DwLog log = new DwLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setOperateDate(new Date());
			log.setOperateType("法人数据比对");
			log.setOperateUser(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
			log.setOperateDept(((User) session
					.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
			log.setCountType("10");
			String condition = "where 1=1 ";
			boolean queryflag = false; // 无条件时不查询
			if(!Strings.isEmpty(dmh)&&!Strings.isEmpty(dmlx)){
				String str1 = dmh.replaceAll(" ", "");
				String str2 = dmlx.replaceAll(" ", "");
				condition += " and "+str2+" = '"+str1+"'";
				queryflag = true;
			}
			log.setQueryCondition(condition);
			log.setQueryTable("CORP_INFO");
			if(queryflag){
			Sql sql = Sqls.create("select UNI_SC_ID,ORGAN_CODE,REG_NO,TAXPAYERS_CODE,CORP_NAME,PERSON_NAME,REG_CAPITAL,ADDRESS from CORP_INFO "
					+ condition + "  ");
			sql.setCallback(new SqlCallback() {
				@Override
				public Object invoke(Connection connection, ResultSet rs, Sql sql)
						throws SQLException {
					if (rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("uniscid", 			rs.getString("UNI_SC_ID"));
					map.put("organcode", 		rs.getString("ORGAN_CODE"));
					map.put("regno", 			rs.getString("REG_NO"));
					map.put("taxpayerscode", 	rs.getString("TAXPAYERS_CODE"));
					map.put("corpname", 		rs.getString("CORP_NAME"));
					map.put("personname", 		rs.getString("PERSON_NAME"));
					map.put("regcapital", 		rs.getString("REG_CAPITAL"));
					map.put("address", 			rs.getString("ADDRESS"));
					return map;
					}
					return null;
				}
			});
			dao1.execute(sql);
			Map<String,Object> map = (Map<String, Object>) sql.getResult();
			result.put("map",map);
			dao.insert(log);
			}
			if(!Strings.isEmpty(tyshxydm)){
				String str3 = tyshxydm.replaceAll(" ", "");
				result.put("tyshxydm", str3);
			}else{
				result.put("tyshxydm", tyshxydm);
			}
			if(!Strings.isEmpty(zzjgdm)){
				String str4 = zzjgdm.replaceAll(" ", "");
				result.put("zzjgdm", str4);
			}else{
				result.put("zzjgdm", zzjgdm);
			}
			if(!Strings.isEmpty(yyzzzch)){
				String str5 = yyzzzch.replaceAll(" ", "");
				result.put("yyzzzch", str5);
			}else{
				result.put("yyzzzch", yyzzzch);
			}
			if(!Strings.isEmpty(nsrsbh)){
				String str6 = nsrsbh.replaceAll(" ", "");
				result.put("nsrsbh", str6);
			}else{
				result.put("nsrsbh", nsrsbh);
			}
			if(!Strings.isEmpty(frmc)){
				String str7 = frmc.replaceAll(" ", "");
				result.put("frmc", str7);
			}else{
				result.put("frmc", frmc);
			}
			if(!Strings.isEmpty(fddbrmc)){
				String str8 = fddbrmc.replaceAll(" ", "");
				result.put("fddbrmc", str8);
			}else{
				result.put("fddbrmc", fddbrmc);
			}
			if(!Strings.isEmpty(zczj)){
				String str9 = zczj.replaceAll(" ", "");
				result.put("zczj", str9);
			}else{
				result.put("zczj", zczj);
			}
			if(!Strings.isEmpty(zcdz)){
				String str10 = zcdz.replaceAll(" ", "");
				result.put("zcdz", str10);
			}else{
				result.put("zcdz", zcdz);
			}
			if(!Strings.isEmpty(dmlx)){
				String str11 = dmlx.replaceAll(" ", "");
				result.put("dmlx", str11);
			}else{
				result.put("dmlx", dmlx);
			}
			if(!Strings.isEmpty(dmh)){
				String str12 = dmh.replaceAll(" ", "");
				result.put("dmh", str12);
			}else{
				result.put("dmh", dmh);
			}
			return result;
		}
		
	@At
	@Ok("json")
	public Object getcylb(String cylb){
		Sql sql=Sqls.create("select ID,VALUE from DIC_FR_INDUSTRY_TYPE where INDUSTRY_TYPE='"+cylb+"'");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql)
					throws SQLException {
				List<Object> result = new ArrayList<Object>();
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id",rs.getString("ID"));
					map.put("value", rs.getString("VALUE"));
					result.add(map);
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>) sql.getResult();
		return result;
	}
	@At
	@Ok("json")
	public Object getfrlx(String frlx){
		Sql sql = Sqls.create("select ID,VALUE from DIC_FR_TYPE"
				+ " where id like '"+frlx+"%' and id <> '"+frlx+"0000'" );
		sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
					throws SQLException {
				List<Object> result = new ArrayList<Object>();
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id",rs.getString("ID"));
					map.put("value", rs.getString("VALUE"));
					result.add(map);
				}
				return result;
			}
		});
		dao.execute(sql);
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>) sql.getResult();
		return result;
	}
	@At
	@Ok("jsp:jsp.query.corpinfo_export")
	public Map<String, Object> exportcorpinfo(String corpinfoid,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		cri.where().and("CORP_INFO_ID","=",corpinfoid);
		CorpInfo corp = dao.fetch(CorpInfo.class,cri);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("导出法人登记信息详情");
		log.setCountType("6");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		log.setQueryCondition("CORP_INFO_ID=" + corpinfoid);
		log.setQueryTable("CORP_INFO");
		log.setQueryCount(corp == null ? "0" : "1");
		dao.insert(log);
		result.put("corp", corp);
		return result;
	}
	
	@At
	@Ok("jsp:jsp.query.xyxx_list2")
	public Map<String, Object> toXyxxList(String id,HttpServletRequest request){
		Map<String, Object> result =new HashMap<String, Object>();
		CorpInfo corpinfo =dao.fetch(CorpInfo.class,Cnd.where("CORP_INFO_ID", "=", id));
		List<XyxxInfo> xyxx =new ArrayList<XyxxInfo>();
		Pager pager = ConUtils.makePaginationPager(request);
		if(!Strings.isEmpty(corpinfo.getOrgancode())){
		    xyxx=dao.query(XyxxInfo.class, Cnd.where("ZJHM", "=", corpinfo.getOrgancode()),pager);
		    pager.setRecordCount(dao.count(XyxxInfo.class,Cnd.where("ZJHM", "=", corpinfo.getOrgancode())));
		}
		if(!Strings.isEmpty(corpinfo.getCorpname())&&xyxx.isEmpty()){
			xyxx=dao.query(XyxxInfo.class, Cnd.where("NAME", "=", corpinfo.getCorpname()),pager);
			pager.setRecordCount(dao.count(XyxxInfo.class,Cnd.where("NAME", "=", corpinfo.getCorpname())));
		}
		
		result.put("pager", pager);
		result.put("id", id);
		result.put("xyxx", xyxx);
		return result;
		
	}
	
	@At
	@Ok("jsp:jsp.query.xyxx_list")
	public Map<String, Object> toPeopleXyxx(String zjhm,HttpServletRequest request){
		Map<String, Object> result =new HashMap<String, Object>();
		List<XyxxInfo> xyxx =new ArrayList<XyxxInfo>();
		Pager pager = ConUtils.makePaginationPager(request);
		if(!Strings.isEmpty(zjhm)){
		    xyxx=dao.query(XyxxInfo.class, Cnd.where("ZJHM", "=", zjhm),pager);
		    pager.setRecordCount(dao.count(XyxxInfo.class,Cnd.where("ZJHM", "=", zjhm)));
		}
		result.put("pager", pager);
		result.put("zjhm", zjhm);
		result.put("xyxx", xyxx);
		return result;
		
	}
	
	@At
	@Ok("jsp:jsp.query.view")
	public Map<String, Object> viewXyxx(String id){
		Map<String, Object> result =new HashMap<String, Object>();
		StringBuffer html = new StringBuffer();
		XyxxInfo xyxx=dao.fetch(XyxxInfo.class, Cnd.where("id","=", id));
		final List<XyxxCloumn> cloumn =dao.query(XyxxCloumn.class, Cnd.where("ZYDM", "=", xyxx.getZydm()));
	    StringBuffer sqlstr =new StringBuffer();
		sqlstr.append("select ");
		for(int i=0;i<cloumn.size();i++){
			sqlstr.append(cloumn.get(i).getTablecolumn());
			if(i<cloumn.size()-1){
				sqlstr.append(",");
			}
		}
		sqlstr.append(" from v_xypt_data where id ='"+id+"'");
		Sql sql =Sqls.create(sqlstr.toString());
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
				while(rs.next()){
					StringBuffer html = new StringBuffer();
					html.append("<table class='table_multilist' width='96%' style='margin:auto'>").append("<tbody>");
					int sum=0;
					for(int i=0;i<cloumn.size();i++){
						if(sum%2==0){
							html.append("<tr>");
						}
						html.append("<td class='label_1' align='center' width='20%'>"+cloumn.get(i).getModdelcolumn()+":<td>");
						if(rs.getObject(cloumn.get(i).getTablecolumn())==null){
							html.append("<td class='label_2' align='center' width='30%'><td>");
						}else {
							html.append("<td class='label_2' align='center' width='30%'>"+rs.getString(cloumn.get(i).getTablecolumn())+"<td>");
						}
						if(sum%2!=0){
							html.append("</tr>");
						}
						sum++;
					}
					html.append("</tbody>").append("</table>");
					return html.toString();
				}
				return null;
			}
		});
		dao.execute(sql);
		html.append("<div><p style='text-align:center;font-size:20px;padding-bottom: 15px;padding-top: 10px'><b>"+cloumn.get(0).getXxsx()+"</b></p></div>");
		html.append(sql.getResult());
		result.put("html", html.toString());
		return result;
	}
	
	@At
	@Ok("jsp:jsp.query.buildingview")
	public Map<String, Object> toBuliding(String id){
		Map<String, Object> result =new HashMap<String, Object>();
		CorpInfo corpinfo =dao.fetch(CorpInfo.class,Cnd.where("CORP_INFO_ID", "=", id));
		BuildingCorp corp=null;
		if(!Strings.isEmpty(corpinfo.getUniscid())){
			corp=dao.fetch(BuildingCorp.class,Cnd.where("LICENCECODE", "=", corpinfo.getUniscid()));
		}else if(!Strings.isEmpty(corpinfo.getOrgancode())){
			corp=dao.fetch(BuildingCorp.class,Cnd.where("CORPTAXPAYERCODE", "=", corpinfo.getOrgancode()));
		}
		if(corp==null){
			corp=dao.fetch(BuildingCorp.class,Cnd.where("CORPNAME", "=", corpinfo.getCorpname()));
		}
		BuildingBean building=null;
		if(corp!=null){
		    building=dao.fetch(BuildingBean.class,Cnd.where("BUILDINGNAME", "=", corp.getBuildname()));
		}
		result.put("build", building);
		return result;
		
	}
	
	//查询产业科技园
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.query.industrial_park.industrial_park_querylist")
	public Map<String, Object> toIndustrial_Park_Querylist(HttpServletRequest request,HttpSession session,String name,String addr){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		DwLog log = new DwLog();
		log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		log.setOperateDate(new Date());
		log.setOperateType("查询产业园区");
		log.setOperateUser(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		log.setOperateDept(((User) session
				.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		String condition = "where 1=1 ";
		boolean queryflag = false; // 无条件时不查询
		if (!Strings.isEmpty(addr)){
			String str1 = addr.replaceAll(" ", "");
			result.put("addr", str1);
			condition += " and ADDRESS like '%" + str1 + "%' ";
			queryflag = true;
		}
		if (!Strings.isEmpty(name)){
			String str2 = name.replaceAll(" ", "");
			result.put("name", str2);
			condition += " and NAME like '%" + str2 + "%' ";
			queryflag = true;
		}
		log.setQueryCondition(condition);
		log.setQueryTable("t_corp_zone");
		if(queryflag){
		Sql sql = Sqls.create("select KEYID,NAME,ADDRESS,ZONE_TYPE from t_corp_zone " 
				+ condition + " ");
		sql.setPager(pager);
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql)
					throws SQLException {
				List<Object> result = new ArrayList<Object>();
				while (rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name",		rs.getString("NAME"));
					map.put("address",	rs.getString("ADDRESS"));
					map.put("type",		rs.getString("ZONE_TYPE"));
					map.put("keyid",	rs.getString("KEYID"));
					result.add(map);
				}
				return result;
			}
		});
		dao.execute(sql);
		List<Object> list =(List<Object>) sql.getResult();
		sql = Sqls.create("select count(1) from ( select KEYID,NAME,ADDRESS,ZONE_TYPE from t_corp_zone "
				+ condition + " ) ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
				if(rs.next())
					return rs.getInt(1);
				return 0;
			}
		});
		dao.execute(sql);
		pager.setRecordCount((Integer) sql.getResult());
		log.setQueryCount(list.size() + "");
		dao.insert(log);
		result.put("list",list);
		}
		result.put("pager", pager);
		return result;
	}
	
	//查询产业科技园区内企业
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.query.industrial_park.industrial_park_corpinfo_querydetail")
	public Map<String, Object> toIndustrial_Park_Corpinfo_Querydetail(HttpServletRequest request,HttpSession session,String keyid){
		Map<String, Object> result = new HashMap<String, Object>();
		Sql sql = Sqls.create("select NAME,ADDRESS from t_corp_zone "
				+ "where KEYID = '" + keyid + "' ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql)
					throws SQLException {
				List<Object> result = new ArrayList<Object>();
				while (rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name",		rs.getString("NAME"));
					map.put("address",	rs.getString("ADDRESS"));
					result.add(map);
				}
				return result;
			}
		});
		dao.execute(sql);
		List<Object> list1 =(List<Object>) sql.getResult();
		
		
		sql = Sqls.create("select CORPNAME,CORPADDRESS,INDUSTRY_STATNAME from t_corp_zone_record "
				+ "where ZONEID = '" + keyid + "' ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql)
					throws SQLException {
				List<Object> result = new ArrayList<Object>();
				while (rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("corpname",		rs.getString("CORPNAME"));
					map.put("corpaddress",	rs.getString("CORPADDRESS"));
					map.put("statname",		rs.getString("INDUSTRY_STATNAME"));
					result.add(map);
				}
				return result;
			}
		});
		dao.execute(sql);
		List<Object> list2 =(List<Object>) sql.getResult();
		
		dao.execute(sql);
		int corpCount = list2.size();
		result.put("list1",list1);
		result.put("list2",list2);
		result.put("corpCount", corpCount);
		return result;
	}
	@At
	@Ok("json")
	public Object checkcode(String code,HttpSession session){
		String code2=(String) session.getAttribute("rpbdtpyzm");
		if(!Strings.isEmpty(code2)&&!Strings.isEmpty(code)&&code2.toLowerCase().equals(code.toLowerCase())){
			return 1;
		}
		return 0;
	}
	
	/**
	 * @return
	 * 异步发送短信
	 */
	public String sendMail(final User user){
		
		
		final String password = String.valueOf((int)((Math.random()*9+1)*100000));
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				sendMailUtils.sendMail(HOST, SmsMould.E_Mail, user.geteMail(), TITLE, password);
				
			}
		}).start();
		return password;
		
	}
}
