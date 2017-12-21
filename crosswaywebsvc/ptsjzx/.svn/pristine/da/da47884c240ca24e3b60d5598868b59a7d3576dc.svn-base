package com.wonders.sjtb.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.upload.TempFile;
import org.nutz.service.Service;

import com.wonders.sjtb.entity.TbColumnsSgs;
import com.wonders.sjtb.entity.TbContentsSgs;
import com.wonders.sjtb.entity.TbFileSgs;
import com.wonders.sjtb.entity.TbRecordSgs;
import com.wonders.sjtb.utils.CreditCodeChecker;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.util.PropertyUtils;
import com.wonders.utils.DateUtils;

@IocBean(fields = "dao")
public class SgsServiece extends Service{

	private static Log log = Logs.get();
	
	public List<TbContentsSgs> getContentsSgsbyType(String type){
		Criteria cri = Cnd.cri();
		cri.where().and("TYPE","=",type);
		cri.getOrderBy().asc("ORDER_NO");
		return dao().query(TbContentsSgs.class,cri);
	}
	
	
	public Map<String, Object> getTbymRecord1(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager= new Pager();
		int pageSize = Integer.valueOf(request.getParameter("rows")==null?10+"":request.getParameter("rows"));
        int pageNumber = Integer.valueOf(request.getParameter("page")==null?"1":request.getParameter("page"));
        pager.setPageNumber(pageNumber);
        pager.setPageSize(pageSize);
        Criteria cri =Cnd.cri();
		cri.where().and("VALID","=","1");
		cri.getOrderBy().desc("TB_DATE");
		List<TbFileSgs> list=dao().query(TbFileSgs.class, cri,pager);
		pager.setRecordCount(dao().count(TbFileSgs.class, cri));
		result.put("tbfilelist", list);
		result.put("pager", pager);
		return result;
	}
	
	/**
	 * 填报页面 填报记录获取
	 * @param id
	 * @param request
	 * @param user
	 * @return
	 */
	public Map<String, Object> getTbymRecord(String id,HttpServletRequest request,User user){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager= new Pager();
		int pageSize = Integer.valueOf(request.getParameter("rows")==null?10+"":request.getParameter("rows"));
        int pageNumber = Integer.valueOf(request.getParameter("page")==null?"1":request.getParameter("page"));
        pager.setPageNumber(pageNumber);
        pager.setPageSize(pageSize);
        Criteria cri =Cnd.cri();
		cri.where().and("TBR","=",user.getUserId());
		cri.where().and("CONTENTID","=",id);
		cri.where().and("VALID","=","1");
		cri.getOrderBy().desc("TB_DATE");
		List<TbFileSgs> list=dao().query(TbFileSgs.class, cri,pager);
		pager.setRecordCount(dao().count(TbFileSgs.class, cri));
		TbContentsSgs sgs = dao().fetch(TbContentsSgs.class,id);
		result.put("tbfilelist", list);
		result.put("pager", pager);
		result.put("type", sgs.getType());
		return result;
	}
	/**
	 * 模板下载
	 * @param id
	 * @param response
	 */
	public void download(String id,HttpServletResponse response){
		try{
			TbContentsSgs contents = dao().fetch(TbContentsSgs.class,Cnd.where("ID","=",id));
			File file = new File(getFilePath() + "/"
					+ PropertyUtils.getProperty("downloadmoban") + "/"
					+ contents.getFilelocalname());
			InputStream inStream = new FileInputStream(file);
			OutputStream outStream = response.getOutputStream();
			response.reset();
			String filename = contents.getFilename();
			response.addHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(filename, "utf-8"));
			int tempbyte;
			while ((tempbyte = inStream.read()) != -1) {
				outStream.write(tempbyte);
				outStream.flush();
			}
			outStream.close();
			inStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private String getFilePath() {
		String path = PropertyUtils.getProperty("app.path");
		return path;
	}
	
	
	public TbFileSgs upload1(TempFile tempFile, HttpSession session, String id,String dept ){
		User user = new User();
		user.setDept(dept);
		TbFileSgs tbFile = new TbFileSgs();
		tbFile.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tbFile.setContentid(id);
		tbFile.setTbdate(new Date());
		tbFile.setTbr(dept);
		Map<String, Object> checkResult =fileCheck(id, tempFile);
		if(checkResult.get("checkresult")!=null&&!(Boolean) checkResult.get("checkresult")){
			tbFile.setValid("0");
			tbFile.setErrorcontent( (String) checkResult.get("errorcontent"));
			tbFile.setErrortitle((String) checkResult.get("errortitle"));
			dao().insert(tbFile);
		}else {
			tbFile.setValid("1");
			dao().insert(tbFile);
			parse(id, tempFile,user,"");
		}
		return tbFile;
	}
	/**
	 * 模板上传
	 * @param tempFile
	 * @param session
	 * @param id
	 * @return
	 */
	public TbFileSgs upload(TempFile tempFile, HttpSession session, String id){
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		TbFileSgs tbFile = new TbFileSgs();
		String uploadid=UUID.randomUUID().toString().replaceAll("-", "");
		tbFile.setId(uploadid);
		tbFile.setContentid(id);
		tbFile.setTbdate(new Date());
		tbFile.setTbr(((User)session.getAttribute(SystemConstants.SYSTEM_USER)).getUserId());
		Map<String, Object> checkResult =fileCheck(id, tempFile);
		if(checkResult.get("checkresult")!=null&&!(Boolean) checkResult.get("checkresult")){
			tbFile.setValid("0");
			tbFile.setErrorcontent( (String) checkResult.get("errorcontent"));
			tbFile.setErrortitle((String) checkResult.get("errortitle"));
			dao().insert(tbFile);
		}else {
			tbFile.setValid("1");
			dao().insert(tbFile);
			parse(id, tempFile,user,uploadid);
		}
		return tbFile;
	}
	/**
	 * 获取上传文件信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> getFileMsg(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		TbFileSgs file=dao().fetch(TbFileSgs.class,Cnd.where("ID","=",id));
		result.put("file", file);
		dao().delete(file);
		return result;
	}
	/**
	 * 文件检查
	 * @return
	 */
	private Map<String, Object> fileCheck(String id,TempFile tempFile) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean checkresult=true;
		String errortitle="";
		String errorcontent="";
		Criteria cri=Cnd.cri();
		cri.where().and("CONTENT_ID","=",id);
		cri.getOrderBy().asc("COLUMN_LOCATION");
		TbContentsSgs content = dao().fetch(TbContentsSgs.class,Cnd.where("ID","=",id));
		List<TbColumnsSgs> columns =dao().query(TbColumnsSgs.class,cri);
		for(TbColumnsSgs c:columns){
			c.initdicmap();
		}
		try {
			InputStream stream = new FileInputStream(tempFile.getFile());
			Workbook rwb = Workbook.getWorkbook(stream);
			Sheet sheet=rwb.getSheet(content.getSheetname());
			if(sheet==null){
				checkresult=false;
				errortitle="文件错误";
				errorcontent="无法找到对应的sheet";
				result.put("checkresult", checkresult);
				result.put("errortitle", errortitle);
				result.put("errorcontent", errorcontent);
				rwb.close();
				stream.close();
				return result;
			}
			boolean flag=false;
			String errortemp="";
			//查询字段是否正确
			for(int j=0;j<columns.size();j++){
				if(sheet.getCell(columns.get(j).getColumnlocation(), 0)==null||sheet.getCell(columns.get(j).getColumnlocation(), 0).getContents().equals("")){
					errortemp=errortemp+"第"+(j+1)+"列为：&nbsp;"+columns.get(j).getColumncomment().trim()
							+"&nbsp;&nbsp;&nbsp;上传文件为:&nbsp;空<br>";
					flag=true;
				}
				else if(!sheet.getCell(columns.get(j).getColumnlocation(), 0).getContents().trim()
						.equals(columns.get(j).getColumncomment().trim())){
					errortemp=errortemp+"第"+(j+1)+"列为：&nbsp;"+columns.get(j).getColumncomment().trim()
							+"&nbsp;&nbsp;&nbsp;上传文件为:&nbsp;"+sheet.getCell(columns.get(j).getColumnlocation(), 0).getContents().trim()+"<br>";
					flag=true;
				}
			}
			if(flag){
				errortitle="文件错误";
				errorcontent=errortemp;
				checkresult=false;
				result.put("checkresult", checkresult);
				result.put("errortitle", errortitle);
				result.put("errorcontent", errorcontent);
				rwb.close();
				stream.close();
				return result;
			}
			//判断数据完整性以及数据格式
			boolean sjwzflag=false;
			errortemp="";
			for(int i=1;i<sheet.getRows();i++){
				int sum=0;
				int selectsum=0;
				boolean selectflag = false;
				int nulldicsum = 0;
				int notmust=0;
				boolean havedata=false;
				List<Integer> list =new ArrayList<Integer>();
				for(int j=0;j<columns.size();j++){
					if(sheet.getCell(columns.get(j).getColumnlocation(),i)!=null&&!sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().equals("")){
						havedata=true;
					}
					if(sheet.getCell(columns.get(j).getColumnlocation(),i)==null||sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().equals("")){
						if(!Strings.isEmpty(columns.get(j).getNotmust())&&columns.get(j).getNotmust().equals("1")){
							notmust++;
						}
						sum++;
						list.add(j);
						if(!Strings.isEmpty(columns.get(j).getColumnchoise())&&columns.get(j).getColumnchoise().equals("1")){
							selectsum++;
						}
						if(!Strings.isEmpty(columns.get(j).getNullDic())){
							nulldicsum++;
						}
					}
					if(!Strings.isEmpty(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents())
							&&(!Strings.isEmpty(columns.get(j).getColumnchoise())
							&&columns.get(j).getColumnchoise().equals("1"))){
						selectflag=true;
					}
				}
				if(havedata){
					sum=sum-nulldicsum;
					sum=sum-notmust;
				}
				if(selectflag){
					sum=sum-selectsum;
				}
				if(sum>0&&sum<columns.size()){
					sjwzflag=true;
					String nulllocation="";
					for(int j:list){
						if(selectflag){
							if(Strings.isEmpty(columns.get(j).getColumnchoise())||!columns.get(j).getColumnchoise().equals("1")){
								if(Strings.isEmpty(columns.get(j).getNullDic())&&Strings.isEmpty(columns.get(j).getNotmust()))
									nulllocation+=" "+(columns.get(j).getColumncomment())+"、 ";	
							}
						}else {
							if(Strings.isEmpty(columns.get(j).getNullDic())&&Strings.isEmpty(columns.get(j).getNotmust()))
								nulllocation+=" "+(columns.get(j).getColumncomment())+"、 ";
						}
					}
					errortemp=errortemp+"<br>第"+(i)+"行 "+nulllocation+" 有必填项未填报，请核对！<br>";
				}
			}
			if(sjwzflag){
				errortitle="文件不完整";
				errorcontent=errortemp;
				checkresult=false;
				result.put("checkresult", checkresult);
				result.put("errortitle", errortitle);
				result.put("errorcontent", errorcontent);
				rwb.close();
				stream.close();
				return result;
			}
			//判断格式错误
			boolean gscwflag=false;
			errortemp="";
			for(int i=1;i<sheet.getRows();i++){
				for(int j=0;j<columns.size();j++){
					if(!Strings.isEmpty(columns.get(j).getColumndatatype())&&columns.get(j).getColumndatatype().equals("3")){
						if(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents()!=null&&!sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().equals("")){
							boolean datecheck=isDateFormat(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents());
							if(!datecheck){
								gscwflag=true;
								errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 数据无效<br>";
							}
						}
					}else if(!Strings.isEmpty(columns.get(j).getColumndatatype())&&(columns.get(j).getColumndatatype().equals("1")
							||columns.get(j).getColumndatatype().equals("2"))){
						if(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents()!=null&&!sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().equals("")){
							if(columns.get(j).getDicid()!=-1){ //校验字典
								if(!(Strings.isEmpty(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents())&&!Strings.isEmpty(columns.get(j).getNullDic()))){
									if(!Strings.isEmpty(columns.get(j).getIsSaveDic())&&columns.get(j).getIsSaveDic().equals("1")){
										Map<String, String> dic = DicDataUtils.getInstance().getDic(columns.get(j).getDicid());
										String value=dic.get(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents());
										if(Strings.isEmpty(value)){
											gscwflag=true;
											errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 数据无效<br>";
										}
									}else {
										String key =columns.get(j).getDicmap().get(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents());
										if(Strings.isEmpty(key)){
											gscwflag=true;
											errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 数据无效<br>";
										}
									}
								}
							}
							//代码校验
							if(!Strings.isEmpty(columns.get(j).getCodechecktype())&&columns.get(j).getCodechecktype().equals("1")){
								String resultstr=CreditCodeChecker.check_creditCode(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents());
								if(!resultstr.equals(CreditCodeChecker.isCreditCode)){
									gscwflag=true;
									errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 数据无效<br>";
								}
							}
							if(!Strings.isEmpty(columns.get(j).getCodechecktype())&&columns.get(j).getCodechecktype().equals("2")){
							if(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().length() < 9 || 
									sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().length() > 10 ){
								gscwflag=true;
								errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 数据无效<br>";
								}
							}
							if(!Strings.isEmpty(columns.get(j).getCodechecktype())&&columns.get(j).getCodechecktype().equals("3")){
								if( !(	sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().length() == 12  ||
										sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().length() == 15)   ){
									gscwflag=true;
									errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 数据无效<br>";
									}
								}
							if(!Strings.isEmpty(columns.get(j).getCodechecktype())&&columns.get(j).getCodechecktype().equals("4")){
								if(	!(	sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().length() == 15 || 
										sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().length() == 18 || 
										sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().length() == 20 )
										){
									gscwflag=true;
									errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 数据无效<br>";
									}
								}
							if(!Strings.isEmpty(columns.get(j).getCodechecktype())&&columns.get(j).getCodechecktype().equals("5")){
								new IdCard().verify(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents());
								if(!new IdCard().verify(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents())){
									gscwflag=true;
									errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 数据无效<br>";
								}
							}
							if(!Strings.isEmpty(columns.get(j).getCodechecktype())&&columns.get(j).getCodechecktype().equals("6")){
								if(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().length() >50){
									gscwflag=true;
									errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 字符长度过长（限制50个字符内）<br>";
								}
							}
							if(!Strings.isEmpty(columns.get(j).getCodechecktype())&&columns.get(j).getCodechecktype().equals("7")){
								if(!("一年".equals(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents())||
										"三年".equals(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents()))){
									gscwflag=true;
									errortemp+="<br>第"+(i+1)+"行 "+columns.get(j).getColumncomment()+" 数据无效<br>";
								}
							}
							
						}
					}
				}
			}
			if(gscwflag){
				errortitle="数据内容格式不正确";
				errorcontent=errortemp;
				checkresult=false;
				result.put("checkresult", checkresult);
				result.put("errortitle", errortitle);
				result.put("errorcontent", errorcontent);
				rwb.close();
				stream.close();
				return result;
			}
		}catch(Exception e){
			checkresult=false;
			errortitle="文件格式错误或带有密码";
			errorcontent="文件不可读取！请下载最新模板！";
			result.put("checkresult", checkresult);
			result.put("errorcontent", errorcontent);
			result.put("errortitle", errortitle);
		}
		return result;
	}
	/**
	 * 解析文件并入库
	 * @param id
	 * @param tempFile
	 */
	private void parse(String id,TempFile tempFile,User user,String uploadid){
		try{
			String datamon = new SimpleDateFormat("yyyyMM").format(new Date());
			String tbdept =user.getDept();
			TbContentsSgs content=dao().fetch(TbContentsSgs.class,Cnd.where("ID","=",id));
			Criteria cri=Cnd.cri();
			cri.where().and("CONTENT_ID","=",id);
			cri.getOrderBy().asc("COLUMN_LOCATION");
			List<TbColumnsSgs> columns =dao().query(TbColumnsSgs.class,cri);
			for(TbColumnsSgs c:columns){
				c.initdicmap();
			}
			InputStream stream = new FileInputStream(tempFile.getFile());
			Workbook rwb = Workbook.getWorkbook(stream);
			Sheet sheet = rwb.getSheet(content.getSheetname());
			String sspc = new SimpleDateFormat("yyyy-ww").format(getlastdayofweek(new Date()));
			//去掉删除重复上传问题
//			StringBuffer deletesqlstr = new StringBuffer("delete from "+content.getTablename()+" where SSPC = '"+sspc+"' and TBDEPT='"+tbdept+"'");
			StringBuffer insertsqlstr = new StringBuffer("insert into "+content.getTablename()+" (ID,");
			StringBuffer item1= new StringBuffer();
			StringBuffer item2= new StringBuffer();
			for(TbColumnsSgs c:columns){
				item1.append(c.getColumnname()+",");
				item2.append("@"+c.getColumnname()+",");
			}
			insertsqlstr.append(item1+"SSPC,DFBM,SJC,TBDEPT,DATA_MON,UPLOADID) values (@ID,"+item2+"@SSPC,@DFBM,@SJC,@TBDEPT,@DATA_MON,@UPlOADID)");
			//判断是否为空表、空表不处理
			boolean nullflag=true;
			for(int i=1;i<sheet.getRows();i++){
				for(int j=0;j<columns.size();j++){
					if(sheet.getCell(columns.get(j).getColumnlocation(),i)!=null&&!sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().equals("")){
						nullflag=false;
						break;
					}
				}
			}
			if(!nullflag){
				//去掉删除重复上传问题
//				Sql deletesql = Sqls.create(deletesqlstr.toString());
//				dao().execute(deletesql);
				for(int i=1;i<sheet.getRows();i++){
					Sql insertsql = Sqls.create(insertsqlstr.toString());
					insertsql.params().set("ID", UUID.randomUUID().toString().replaceAll("-", ""));
					insertsql.params().set("SSPC", sspc);
					insertsql.params().set("DFBM", "310107");
					insertsql.params().set("SJC", new Date());
					insertsql.params().set("TBDEPT", tbdept);
					insertsql.params().set("DATA_MON", datamon);
					insertsql.params().set("UPlOADID", uploadid);
					boolean havedata= false;
					for(int j=0;j<columns.size();j++){
						if(sheet.getCell(columns.get(j).getColumnlocation(),i)==null||sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().equals("")){
							if(!Strings.isEmpty(columns.get(j).getNullDic()))
							{
								insertsql.params().set(columns.get(j).getColumnname(), columns.get(j).getNullDic());
							}else {
								insertsql.params().set(columns.get(j).getColumnname(), null);
							}
						}else {
							if(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents()!=null&&!sheet.getCell(columns.get(j).getColumnlocation(),i).getContents().equals("")){
								havedata=true;
								if(columns.get(j).getColumndatatype().equals("1")||columns.get(j).getColumndatatype().equals("2")){
									/*if(columns.get(j).getDicid()!=-1){
										insertsql.params().set(columns.get(j).getColumnname(),
												columns.get(j).getDicmap().get(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents()));
									}else{*/
										String data=sheet.getCell(columns.get(j).getColumnlocation(),i).getContents();
										if(!Strings.isEmpty(data)&&data.length()<30){
											data=data.replaceAll(" ", "");
										}
										insertsql.params().set(columns.get(j).getColumnname(), data);
									/*}*/
								}
								if(columns.get(j).getColumndatatype().equals("3")){
									Date date = parseDate(sheet.getCell(columns.get(j).getColumnlocation(),i).getContents());
									insertsql.params().set(columns.get(j).getColumnname(),date);
								}
							}
						}
					}
					if(havedata){
						dao().execute(insertsql);
					}
				}
			}
			rwb.close();
			stream.close();
			TbRecordSgs record = dao().fetch(TbRecordSgs.class,Cnd.where("TB_DEPT","=",tbdept).and("TB_WEEK","=",sspc).and("CONTENT_ID","=",id));
			if(record==null){
				record = new TbRecordSgs();
				record.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				record.setIstb("1");
				record.setTbdept(tbdept);
				record.setTbmonth(datamon);
				record.setLastupdatetime(new Date());
				record.setTbWeek(sspc);
				record.setContentid(id);
				dao().insert(record);
			}else {
				record.setLastupdatetime(new Date());
				dao().update(record);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("null")
	public Map<String , Object> count(String datamonth,String dataweek,String type,String operate,Date jdrq1,Date jdrq2){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			creatZcDic();
		} catch (ParseException e) {
			log.error(e);
		}
		if(Strings.isEmpty(type)){
			type="1";
		}
		if(Strings.isEmpty(dataweek)){
			dataweek = new SimpleDateFormat("yyyy-ww").format(new Date());
		}else {
			if(!Strings.isEmpty(operate)&&(!Strings.isEmpty(type)&&type.equals("2"))){
				if(operate.equals("1")){
					dataweek = prevweek(dataweek);
				}
				if(operate.equals("2")){
					dataweek = nextweek(dataweek);
				}
			}
		}
		if(!Strings.isEmpty(datamonth)){
			try {
				Date date =new SimpleDateFormat("yyyy-MM-dd").parse(datamonth+"-01");
				result.put("datamonth", date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			datamonth=datamonth.replaceAll("-", "");
		}else {
			Date date = new Date();
			result.put("datamonth",date);
			datamonth=new SimpleDateFormat("yyyyMM").format(date);
		}
		Sql sql = Sqls.create("select ST_DEPT_CODE from DIC_MUNICIPAL_DEPT where IS_SGSTB_DEPT = '1'");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
					throws SQLException {
				List<String> result = new ArrayList<String>();
				while(rs.next()){
					result.add(rs.getString("ST_DEPT_CODE"));
				}
				return result;
			}
		});
		dao().execute(sql);
		@SuppressWarnings("unchecked")
		List<String> dept = (List<String>) sql.getResult();
		Criteria cri = Cnd.cri();
		cri.getOrderBy().asc("TYPE");
		String condition = "1=1" ;
		String jdrq = "";
		if(type.equals("1")){
			condition = "and DATA_MON='"+datamonth+"'";
		}
		if(type.equals("2")){
			condition = "and SSPC='"+dataweek+"'";
		}
		if(type.equals("3")){
			condition = "";
		}
		List<TbContentsSgs> contents = dao().query(TbContentsSgs.class,cri);
		for(TbContentsSgs c :contents){
			String temp="";
			jdrq = "";
			int fcount = 0;
			if(c.getType().equals("1")){
				temp = "许可";
				if(jdrq1!=null){
					jdrq = " and to_char(xk_jdrq,'yyyymmdd') >'"+new SimpleDateFormat("yyyyMMdd").format(jdrq1)+"'";
					result.put("jdrq1", jdrq1);
				}
				if(jdrq2!=null){
					jdrq += " and to_char(xk_jdrq,'yyyymmdd') <='"+new SimpleDateFormat("yyyyMMdd").format(jdrq2)+"'";
					result.put("jdrq2", jdrq2);
				}
				
			}
			if(c.getType().equals("2")){
				temp = "处罚";
				if(jdrq1!=null){
					jdrq = " and to_char(cf_jdrq,'yyyymmdd') >'"+new SimpleDateFormat("yyyyMMdd").format(jdrq1)+"'";
					result.put("jdrq1", jdrq1);
				}
				if(jdrq2!=null){
					jdrq += " and to_char(cf_jdrq,'yyyymmdd') <='"+new SimpleDateFormat("yyyyMMdd").format(jdrq2)+"'";
					result.put("jdrq2", jdrq2);
				}
			}
			c.setDatatype(c.getDatatype()+temp);
		    for(String d: dept){
			sql = Sqls.create("select count(1) count from "+c.getTablename()+" where "+ "TBDEPT='"+d+"' "+condition+jdrq );
			sql.setCallback(new SqlCallback() {	
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2) throws SQLException {
					// TODO Auto-generated method stub
					if(rs.next()){
					return rs.getInt("count");
					}
					return 0;
				}
			});
			dao().execute(sql);
			int a =(Integer) sql.getResult();
			fcount = fcount + a;
		    }
			if("corpxk".equalsIgnoreCase(c.getId())){
				result.put("frxk",fcount );
			}else if("corpcf".equalsIgnoreCase(c.getId())){
				result.put("frcf", fcount);
			}else if("peoplecf".equalsIgnoreCase(c.getId())){
				result.put("rkcf", fcount);
			}else{
				result.put("rkxk", fcount);
			}
		}
	
		List<Map<String, Object>> countResult = new ArrayList<Map<String,Object>>(); 
		DicDataUtils dicutil = DicDataUtils.getInstance();
		
		int zcount = 0;
		int bmcount = 0;
		for(String d:dept){
			bmcount = 0;
			Map<String, Object> countMap = new HashMap<String, Object>();
			String dept1 =dicutil.getDicData(1003, d);
			String dept2 =dept1.replaceAll("普陀区", "");
			countMap.put("dept", dept2);
			List<String> countlist = new ArrayList<String>();
			for(TbContentsSgs c :contents){
				jdrq = "";
				if("1".equals(c.getType())){
					if(jdrq1!=null){
						jdrq = " and to_char(xk_jdrq,'yyyymmdd') >'"+new SimpleDateFormat("yyyyMMdd").format(jdrq1)+"'";
					}
					if(jdrq2!=null){
						jdrq += " and to_char(xk_jdrq,'yyyymmdd') <='"+new SimpleDateFormat("yyyyMMdd").format(jdrq2)+"'";
					}
				}
				if("2".equals(c.getType())){
					if(jdrq1!=null){
						jdrq = " and to_char(cf_jdrq,'yyyymmdd') >'"+new SimpleDateFormat("yyyyMMdd").format(jdrq1)+"'";
					}
					if(jdrq2!=null){
						jdrq += " and to_char(cf_jdrq,'yyyymmdd') <='"+new SimpleDateFormat("yyyyMMdd").format(jdrq2)+"'";
					}
				}
				sql = Sqls.create("select count(1) c from "+c.getTablename()+" where "
					+ "TBDEPT='"+d+"' "+condition+jdrq);
				sql.setCallback(new SqlCallback() {
					@Override
					public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
							throws SQLException {
						if(rs.next()){
							return rs.getString("c");
						}
						return "0";
					}
				});
				dao().execute(sql);
				int a =Integer.parseInt((String) sql.getResult());
				bmcount =bmcount+a;
				countlist.add((String) sql.getResult());
			}
			zcount =zcount + bmcount;
			countMap.put("bmcount", bmcount);
			countMap.put("countlist", countlist);
			countResult.add(countMap);
		}
		result.put("zcount", zcount);
		//是否有上一周、下一周
		if(!dataweek.equals(new SimpleDateFormat("yyyy-ww").format(new Date()))){
			result.put("nextweek", "1");
		}
		try {
			Date date = getlastdayofweek(new SimpleDateFormat("yyyy-ww").parse(dataweek));
			date=DateUtils.addDays(date, -7);
			Map<String, String> zcdic=DicDataUtils.getInstance().getDic(1101);
			if(zcdic.get(new SimpleDateFormat("yyyy-ww").format(date))!=null){
				result.put("prevweek", "1");
			}
		} catch (ParseException e) {
			log.error(e);
		}
		
		
		result.put("type", type);
		result.put("dataweek", dataweek);
		result.put("countResult", countResult);
		result.put("contents", contents);
		result.put("dept", dept);
		return result;
	}
	//生成最新周次字典
	private void creatZcDic() throws ParseException{
		Map<String, String> zcdic=DicDataUtils.getInstance().getDic(1101);
		String zc = new SimpleDateFormat("yyyy-ww").format(getlastdayofweek(new Date()));
		if(Strings.isEmpty(zcdic.get(zc))){
			//寻找字典表中最大一周的字典
			long temp=0;
			for(String key:zcdic.keySet()){
				long temp1=Integer.parseInt(key.substring(0,4)+key.substring(5,7));
				if(temp1>temp){
					temp=temp1;
				}
			}
			SimpleDateFormat format=new SimpleDateFormat("yyyy-ww");
			Calendar c2=Calendar.getInstance();
			c2.setTime(new Date());
			c2.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			String latestweek=format.format(c2.getTime());
			Date date = format.parse(temp/100+"-"+temp%100);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			boolean flag = true;
			while(flag){
				calendar.add(Calendar.DATE, 7);
				if(format.format(calendar.getTime()).equals(latestweek)){
					flag=false;
				}
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				String t1 =  new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime());
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				String t2 =  new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime());
				String t = t1 + "-" + t2;
				String czc = format.format(calendar.getTime());
				zcdic.put(czc, t);
				dao().execute(Sqls.create("insert into DIC_ZC (id,value) values ('"+czc+"','"+t+"')"));
			}
			DicDataUtils.getInstance().addDic(1101, zcdic);
			
		}
	}
	/**
	 * 获取一周的最后一天
	 * @param date
	 * @return
	 */
	private Date getlastdayofweek(Date date){
		if(date!=null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			return calendar.getTime();
		}
		return null;
	}
	private String nextweek(String dataweek){
		if(dataweek.equals(new SimpleDateFormat("yyyy-ww").format(new Date()))){
			return dataweek;
		}else {
			try {
				Date date = new SimpleDateFormat("yyyy-ww").parse(dataweek);
				date = DateUtils.addDays(date, 7);
				date = getlastdayofweek(date);
				return new SimpleDateFormat("yyyy-ww").format(date);
			} catch (ParseException e) {
				log.error(e);
				return dataweek;
			}
		}
	}
	private String prevweek(String dataweek){
		try {
			Date date = new SimpleDateFormat("yyyy-ww").parse(dataweek);
			date = DateUtils.addDays(date, -7);
			date = getlastdayofweek(date);
			String week2=new SimpleDateFormat("yyyy-ww").format(date);
			Map<String, String> zcdic=DicDataUtils.getInstance().getDic(1101);
			if(zcdic.get(week2)==null){
				return dataweek;
			}else {
				return week2;
			}
		} catch (Exception e) {
			log.error(e);
			return dataweek;
		}
	}
	/**
	 * 判断是否为可通过的时间格式
	 */
	private boolean isDateFormat(String datestr){
		if(Strings.isEmpty(datestr)||datestr.length()!=10){
			return false;
		}
		if(Pattern.compile("[0-9]{4}-[0-1]{1}[0-9]{1}-[0-3]{1}[0-9]{1}"
				+ "|[0-9]{4}.[0-1]{1}[0-9]{1}.[0-3]{1}[0-9]{1}"
				+ "|[0-9]{4}/[0-1]{1}[0-9]{1}/[0-3]{1}[0-9]{1}"
				+ "|[0-9]{4}_[0-1]{1}[0-9]{1}_[0-3]{1}[0-9]{1}").matcher(datestr).matches()){
			return true;
		}
		return false;
	}
	/**
	 * 日期字符串
	 */
	private Date parseDate(String date){
		date=date.replaceAll("\\.", "/").replaceAll("_", "/").replaceAll("-", "/");
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		try {
			return format.parse(date);
		} catch (ParseException e) {
			log.error(e);
			return null;
		}
	}
}
