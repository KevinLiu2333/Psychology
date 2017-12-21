package com.wonders.sjtb.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.upload.TempFile;
import org.nutz.service.Service;

import com.wonders.sjtb.entity.TbColumns;
import com.wonders.sjtb.entity.TbFile;
import com.wonders.sjtb.entity.TbRecord;
import com.wonders.sjtb.entity.TbSheet;

/**
 * 解析xls的类
 * 
 * @author Administrator
 * 
 */
@IocBean(fields = "dao")
public class ParseService extends Service {

	/**
	 * 解析xls文件
	 * 
	 * @param tbFile
	 * @param tempFile
	 */
	public void parse(TbFile tbFile, TempFile tempFile, String datamon,HttpSession session) {
		try {
			long current = 0;
			long datasum = 0;
			InputStream stream = new FileInputStream(tempFile.getFile());
			Workbook rwb = Workbook.getWorkbook(stream);
			List<TbSheet> tbSheets = dao().query(TbSheet.class,
					Cnd.where("TABEL_BM", "=", tbFile.getTbtype()));
			for(TbSheet tbSheet : tbSheets){
				Sheet sheet = rwb.getSheet(tbSheet.getSheetName());
				datasum+=sheet.getRows();
			}
			for (TbSheet tbSheet : tbSheets) {
				Sheet sheet = rwb.getSheet(tbSheet.getSheetName());
				List<TbColumns> columns = dao().query(
						TbColumns.class,
						Cnd.where("TABLE_BM", "=", tbFile.getTbtype()).and(
								"SHEET_ID", "=", tbSheet.getOrderNo()));
				//判断是否为空表格
				boolean flag = false; //是否需要清除数据标示
				int nowybs=0;
				for(int i=0;i<columns.size();i++){
					if(Strings.isEmpty(columns.get(i).getIsWybs())||!columns.get(i).getIsWybs().equals("1")){
						nowybs=i;
						break;
					}
				}
				for(int i = 1; i < sheet.getRows(); i++){
					if(columns.size()>0){
						if(!Strings.isEmpty(sheet.getCell(columns.get(nowybs).getOrderNo(),i).getContents())){
							flag=true;
							break;
						}
					}
				}
				if(flag){ //表格不为空 清除数据库
					StringBuffer deletesqlstr = new StringBuffer("delete from "+columns.get(0).getTableName()+" where DATA_YEAR='"+datamon+"'");
					for (TbColumns col:columns) {
						if(Strings.isEmpty(col.getIsWybs())){
							deletesqlstr.append(" and "+col.getColumnName() +" is not null");
							break;
						}
					}
					Sql deletesql = Sqls.create(deletesqlstr.toString());
					dao().execute(deletesql);
				}else {	//表格为空，直接跳出本次循环
					continue;
				}
				StringBuffer insertsqlstr = new StringBuffer("insert into "
						+ columns.get(0).getTableName() + " (ID,");
				StringBuffer inserthistorysqlstr = new StringBuffer(
						"insert into " + columns.get(0).getHistoryTableName()
								+ " (ID,");
				
				StringBuffer columnsname = new StringBuffer("");
				StringBuffer columnsvalue = new StringBuffer("");
				for (int i = 0; i < columns.size(); i++) {
					columnsname.append(columns.get(i).getColumnName());
					columnsvalue.append("@" + columns.get(i).getColumnName());
					if (i < columns.size() - 1) {
						columnsname.append(",");
						columnsvalue.append(",");
					}
				}
				insertsqlstr.append(columnsname.toString())
						.append(",DATA_YEAR,SBR) values (@ID,")
						.append(columnsvalue.toString()).append(",@DATA_MON,@SBR)");
				inserthistorysqlstr.append(columnsname.toString())
						.append(",FILE_ID,SBR,SHEET_ID,DATA_YEAR) values (@ID,")
						.append(columnsvalue.toString())
						.append(",@FILE_ID,@SBR,@SHEET_ID,@DATA_MON)");
				for (int i = 1; i < sheet.getRows(); i++) {
					// 当行为空
					int cols = 0;
					for (int j = 0; j < columns.size(); j++) {
						if (sheet.getCell(columns.get(j).getOrderNo(), i)
								.getContents() != null
								&& !sheet
										.getCell(columns.get(j).getOrderNo(), i)
										.getContents().equals("")) {
							cols++;
							break;
						}
					}
					if (cols == 0) {
						continue;
					}
						Sql insertsql = Sqls.create(insertsqlstr.toString());
						Sql inserthistrorySql = Sqls.create(inserthistorysqlstr
								.toString());
						for (int j = 0; j < columns.size(); j++) {
							if (columns.get(j).getColumnType()
									.equals("VARCHAR")) {
								insertsql.params().set(
										columns.get(j).getColumnName(),
										sheet.getCell(
												columns.get(j).getOrderNo(), i)
												.getContents());
								inserthistrorySql.params().set(
										columns.get(j).getColumnName(),
										sheet.getCell(
												columns.get(j).getOrderNo(), i)
												.getContents());
							}
							if (columns.get(j).getColumnType().equals("NUMBER")) {
								if(sheet.getCell(
										columns.get(j).getOrderNo(), i)
										.getContents().equals("")||sheet.getCell(
												columns.get(j).getOrderNo(), i)
												.getContents()==null){
									insertsql.params().set(
											columns.get(j).getColumnName(),null);
									inserthistrorySql.params().set(
											columns.get(j).getColumnName(),null);
								}else {
									insertsql.params().set(
											columns.get(j).getColumnName(),
											Double.parseDouble(sheet.getCell(
													columns.get(j).getOrderNo(), i)
													.getContents()));
									inserthistrorySql.params().set(
											columns.get(j).getColumnName(),
											Double.parseDouble(sheet.getCell(
													columns.get(j).getOrderNo(), i)
													.getContents()));
								}
								
							}
							if (columns.get(j).getColumnType().equals("DATE")) {
								if (sheet.getCell(columns.get(j).getOrderNo(),
										i).getContents() == null
										|| sheet.getCell(
												columns.get(j).getOrderNo(), i)
												.getContents().equals("")) {
									inserthistrorySql.params().set(
											columns.get(j).getColumnName(),
											null);
								} else {
									Date date = parseDate(sheet.getCell(
															columns.get(j)
																	.getOrderNo(),
															i).getContents());
//									Date date = Times
//											.parse(new SimpleDateFormat(columns
//													.get(j).getParseDateType()),
//													sheet.getCell(
//															columns.get(j)
//																	.getOrderNo(),
//															i).getContents());
									insertsql.params().set(
											columns.get(j).getColumnName(),
											date);
									inserthistrorySql.params().set(
											columns.get(j).getColumnName(),
											date);
								}
							}
						}
						insertsql.params().set(
								"ID",
								UUID.randomUUID().toString()
										.replaceAll("-", ""));
						
						insertsql.params().set("DATA_MON", datamon);
						insertsql.params().set("SBR",  tbFile.getTbr());
						inserthistrorySql.params().set(
								"ID",
								UUID.randomUUID().toString()
										.replaceAll("-", ""));
						inserthistrorySql.params().set("FILE_ID",
								tbFile.getId());
						inserthistrorySql.params().set("SBR", tbFile.getTbr());
						inserthistrorySql.params().set("SHEET_ID",
								tbSheet.getOrderNo());
						inserthistrorySql.params().set("DATA_MON", datamon);
						dao().execute(insertsql);
						dao().execute(inserthistrorySql);
						current++;
						session.setAttribute("stjbupload", "文件上传中（"+(current*100/datasum)+"%）");
				}
			}
			Criteria cri=Cnd.cri();
			cri.where().and("TB_MONTH","=",datamon);
			cri.where().and("ISTB","=","1");
			cri.where().and("TB_TYPE","=",tbFile.getTbtype());
			TbRecord record = dao().fetch(TbRecord.class,cri);
			if(record==null){
				record = new TbRecord();
				record.setIstb("1");
				record.setTbMonth(datamon);
				record.setTbType(tbFile.getTbtype());
				record.setLastUpdateTime(new Date());
				dao().insert(record);
			}else {
				record.setLastUpdateTime(new Date());
				dao().update(record);
			}
			session.setAttribute("stjbupload","");
			rwb.close();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * 文件检查
	 * @param type
	 * @param tempFile
	 * @return
	 */
	public Map<String, Object> fileCheck(String type, TempFile tempFile,HttpSession session){
		session.setAttribute("stjbupload", "文件检查中....");
		Map<String, Object> result=new HashMap<String, Object>();
		boolean checkresult=true;
		String errortitle="";
		String errorcontent="";
		Criteria cri2 =Cnd.cri();
		cri2.where().and("TABEL_BM","=",type);
		cri2.getOrderBy().asc("ORDER_NO");
		List<TbSheet> tbSheets = dao().query(TbSheet.class, cri2);
		try {
			InputStream stream = new FileInputStream(tempFile.getFile());
			Workbook rwb = Workbook.getWorkbook(stream);
			Sheet[] sheets = rwb.getSheets();
			//检查sheet数是否匹配
			if(sheets.length!=tbSheets.size()){
				checkresult=false;
				errortitle="文件格式错误";
				errorcontent="上传的文件与模板文件的sheet数不匹配";
				result.put("checkresult", checkresult);
				result.put("errortitle", errortitle);
				result.put("errorcontent", errorcontent);
				rwb.close();
				stream.close();
				return result;
			}
			//检查sheet名
			boolean flag=false;//sheet名是否有误的标示
			for(int i=0;i<tbSheets.size();i++){
				Sheet tempsheet =rwb.getSheet(tbSheets.get(i).getSheetName());
				if(tempsheet==null){
					errorcontent=errorcontent+"上传文件缺失sheet："+tbSheets.get(i).getSheetName().trim()+"<br>";
					flag=true;
				}
				
				/*if(!tbSheets.get(i).getSheetName().trim().equals(sheets[i].getName().trim()))
				{
					errorcontent=errorcontent+"模板文件的第"+(i+1)+"个sheet名称为："+tbSheets.get(i).getSheetName().trim()
							+"&nbsp;&nbsp;&nbsp;上传文件为:"+sheets[i].getName().trim()+"<br>";
					flag=true;
				}*/
			}
			if(flag){
				checkresult=false;
				errortitle="文件格式错误";
				result.put("checkresult", checkresult);
				result.put("errortitle", errortitle);
				result.put("errorcontent", errorcontent);
				rwb.close();
				stream.close();
				return result;
			}
			boolean zdppflag=false;
			boolean sjwzflag=false;
			String errortitle1="文件格式错误";
			String errortitle2="数据不完整";
			String errortitle3="数据格式错误";
			String errorcontent1="";
			String errorcontent2="";
			String errorcontent3="";
			//检查每个sheet中各个字段是否匹配
			for(int i=0;i<tbSheets.size();i++){
				Criteria cri=Cnd.cri();
				cri.where().and("SHEET_ID","=",tbSheets.get(i).getOrderNo());
				cri.where().and("TABLE_BM","=",tbSheets.get(i).getTabelBm());
				cri.getOrderBy().asc("ORDER_NO");
				List<TbColumns> columns=dao().query(TbColumns.class,cri);
				String errortemp="";
				boolean flag1=false;
				Sheet tempsheet =rwb.getSheet(tbSheets.get(i).getSheetName());
				try{
				for(int j=0;j<columns.size();j++){
					if(tempsheet.getCell(j+1, 0)==null||tempsheet.getCell(j+1, 0).getContents().equals("")){
						errortemp=errortemp+"第"+(j+1)+"列为：&nbsp;"+columns.get(j).getColumnComment().trim()
								+"&nbsp;&nbsp;&nbsp;上传文件为:&nbsp;空<br>";
						flag=true;
						flag1=true;
						zdppflag=true;
					}
					else if(!tempsheet.getCell(j+1, 0).getContents().trim()
							.equals(columns.get(j).getColumnComment().trim())){
						errortemp=errortemp+"第"+(j+1)+"列为：&nbsp;"+columns.get(j).getColumnComment().trim()
								+"&nbsp;&nbsp;&nbsp;上传文件为:&nbsp;"+tempsheet.getCell(j+1, 0).getContents().trim()+"<br>";
						flag=true;
						flag1=true;
						zdppflag=true;
					}
				}
				}catch(ArrayIndexOutOfBoundsException e){
					errorcontent1=errorcontent1+"&nbsp;&nbsp;"+tbSheets.get(i).getSheetName().trim()+"&nbsp;sheet中：<br>"+"与模板中的列数不一样";
					flag=true;
					zdppflag=true;
				}catch(NullPointerException e){}
				if(flag1){
					errorcontent1=errorcontent1+"&nbsp;&nbsp;"+tbSheets.get(i).getSheetName().trim()+"&nbsp;sheet中：<br>"+errortemp;
				}
				if(zdppflag){
					checkresult=false;
					continue;
				}
				//校验数据完整性
				errortemp="";
				boolean flag2=false;
				for(int j=1;j<tempsheet.getRows();j++){
					int sum=0;
					int wybssum=0;
					boolean wybs =false;
					for(int k=0;k<columns.size();k++){
						if(tempsheet.getCell(columns.get(k).getOrderNo(),j)==null||tempsheet.getCell(columns.get(k).getOrderNo(),j).getContents().equals("")){
							sum++;
							if(!Strings.isEmpty(columns.get(k).getIsWybs())&&columns.get(k).getIsWybs().equals("1")){
								wybssum++;
							}
						}
						if(!Strings.isEmpty(tempsheet.getCell(columns.get(k).getOrderNo(),j).getContents())
								&&(!Strings.isEmpty(columns.get(k).getIsWybs())
								&&columns.get(k).getIsWybs().equals("1"))){
							wybs=true;
						}
					}
					if(wybs){
						sum=sum-wybssum;
					}
					if(sum>0&&sum<columns.size()){
						sjwzflag=true;
						flag=true;
						flag2=true;
						errortemp=errortemp+"<br>第"+(j)+"行数据不完整！<br>";
					}
				}
				if(flag2){
					errorcontent2= errorcontent2+"<br>&nbsp;&nbsp;"+tbSheets.get(i).getSheetName().trim()+"&nbsp;sheet中："+errortemp;
				}
				if(sjwzflag){
					continue;
				}
				//校验数据格式
				boolean flag3=false;
				errortemp="";
				for(int j=0;j<columns.size();j++){
					for(int k=1;k<sheets[i].getRows();k++){
						if(columns.get(j).getColumnType().equalsIgnoreCase("VARCHAR")){
							if(columns.get(j).getCheckType()!=null&&columns.get(j).getCheckType().equals("1")){//校验身份证 
								if(tempsheet.getCell(columns.get(j).getOrderNo(),k)!=null&&!tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents().equals("")&&sheets[i].getCell(columns.get(j).getOrderNo(),k).getContents().length()==18){
									IdCard idCard=new IdCard();
									if(!idCard.verify(tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents())){
										flag=true;
										flag3=true;
										errortemp=errortemp+"第 "+(k+1)+"行&nbsp;第"+(columns.get(j).getOrderNo()+1)+"列："+tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents()+"不是合法的身份证号"+"<br>";
									}
								}
							}
						}
						if(columns.get(j).getColumnType().equalsIgnoreCase("NUMBER")){
							try {
								if(tempsheet.getCell(columns.get(j).getOrderNo(),k)!=null&&!tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents().equals("")){
									Double.parseDouble(tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents());
								}
							} catch (Exception e) {
								flag=true;
								flag3=true;
								errortemp=errortemp+"第 "+(k+1)+"行&nbsp;第"+(columns.get(j).getOrderNo()+1)+"列为:"+tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents()+",&nbsp;&nbsp;&nbsp;本列应填写数字<br>";
							}
						}
						if(columns.get(j).getColumnType().equalsIgnoreCase("DATE")){
							if(tempsheet.getCell(columns.get(j).getOrderNo(),k)!=null&&!tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents().equals("")){
								String date=tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents();
								if(!datecheck1(date)){
									flag=true;
									flag3=true;
									SimpleDateFormat format=new SimpleDateFormat(columns.get(j).getParseDateType());
									errortemp=errortemp+"第 "+(k+1)+"行&nbsp;第"+(columns.get(j).getOrderNo()+1)+"列为:"+tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents()+",&nbsp;&nbsp;本列应填写日期格式("+format.format(new Date())+")<br>";
									continue;
								}
								String datecheck=datecheck2(date);
								if(datecheck!=null){
									flag=true;
									flag3=true;
									errortemp=errortemp+"第 "+(k+1)+"行&nbsp;第"+(columns.get(j).getOrderNo()+1)+"列为:"+tempsheet.getCell(columns.get(j).getOrderNo(),k).getContents()+",&nbsp;&nbsp;"+datecheck+"<br>";
								}
							}
						}
					}
				}
				if(flag3){
					errorcontent3=errorcontent3+"<br>&nbsp;&nbsp;"+tbSheets.get(i).getSheetName().trim()+"&nbsp;sheet中：<br>"+errortemp;
					
				}
			}
			if(flag){
				if(zdppflag){
					errortitle=errortitle1;
					result.put("errorcontent", errorcontent1);
				}else if(sjwzflag){
					errortitle=errortitle2;
					result.put("errorcontent", errorcontent2);
				}else {
					errortitle=errortitle3;
					result.put("errorcontent", errorcontent3);
				}
				result.put("checkresult", false);
				result.put("errortitle", errortitle);
				rwb.close();
				stream.close();
				return result;
			}
			rwb.close();
			stream.close();
		} catch (Exception e) {
			checkresult=false;
			errortitle="文件格式错误";
			errorcontent="上传的文件格式错误，请下载最新模板！";
		}
		result.put("checkresult", checkresult);
		result.put("errortitle", errortitle);
		result.put("errorcontent", errorcontent);
		return result;
	}
	
	
	
	

	//日期格式校验
	public boolean datecheck1(String date){
		String regex="[0-9]{4}-[0-9]{2}-[0-9]{2}|" 	//2012-02-20
				+ "[0-9]{4}-[0-9]{1}-[0-9]{2}|"     //2012-2-20
				+ "[0-9]{4}-[0-9]{2}-[0-9]{1}|"		//2012-12-2
				+ "[0-9]{4}/[0-9]{2}/[0-9]{2}|" 	//2012/02/20
				+ "[0-9]{4}/[0-9]{1}/[0-9]{2}|"     //2012/2/20
				+ "[0-9]{4}/[0-9]{2}/[0-9]{1}|"		//2012/12/2
				+ "[0-9]{4}年[0-9]{2}月[0-9]{2}日|" 	//2012年02月20日
				+ "[0-9]{4}年[0-9]{1}月[0-9]{2}日|"   //2012年2月20日
				+ "[0-9]{4}年[0-9]{2}月[0-9]{1}日|"	//2012年12月2日
				+ "[0-9]{4}-[0-9]{1}-[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}年[0-9]{1}月[0-9]{1}日";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
	
	/**
	 * 日期数据校验
	 * @param date
	 * @return 返回 null表示正确
	 */
	public String datecheck2(String date){
		int year=0;
		int month=0;
		int day=0;
		//月2位  日2位
		if(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{4}/[0-9]{2}/[0-9]{2}|[0-9]{4}年[0-9]{2}月[0-9]{2}日").matcher(date).matches()){
			year=Integer.parseInt(date.substring(0, 4));
			month=Integer.parseInt(date.substring(5, 7));
			day=Integer.parseInt(date.substring(8, 10));
		}
		//月2位  日1位
		if(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{1}|[0-9]{4}/[0-9]{2}/[0-9]{1}|[0-9]{4}年[0-9]{2}月[0-9]{1}日").matcher(date).matches()){
			year=Integer.parseInt(date.substring(0, 4));
			month=Integer.parseInt(date.substring(5, 7));
			day=Integer.parseInt(date.substring(8, 9));
		}
		//月1位  日2位
		if(Pattern.compile("[0-9]{4}-[0-9]{1}-[0-9]{2}|[0-9]{4}/[0-9]{1}/[0-9]{2}|[0-9]{4}年[0-9]{1}月[0-9]{2}日").matcher(date).matches()){
			year=Integer.parseInt(date.substring(0, 4));
			month=Integer.parseInt(date.substring(5, 6));
			day=Integer.parseInt(date.substring(7, 9));
		}
		//月1位  日1位
		if(Pattern.compile("[0-9]{4}-[0-9]{1}-[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}年[0-9]{1}月[0-9]{1}日").matcher(date).matches()){
			year=Integer.parseInt(date.substring(0, 4));
			month=Integer.parseInt(date.substring(5, 6));
			day=Integer.parseInt(date.substring(7, 8));
		}
		//判断月份是否问题
		if(month>12){
			return "月份超过12";
		}
		//日是否有问题
		if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
			if(day>31){
				return month+"月只有31天";
			}
		}
		if(month==4||month==6||month==9||month==11){
			if(day>30){
				return month+"月只有30天";
			}
		}
		if(month==2){
			if((year%100!=0&&year%4==0)||year%400==0){//闰年
				if(day>29){
					return year+"年2月只有29天";
				}
			}else {//非闰年
				if(day>28){
					return year+"年2月只有28天";
				}
			}
		}
		return null;
	}
	//解析日期
	public Date parseDate(String date){
		int year=0;
		int month=0;
		int day=0;
		//月2位  日2位
		if(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}|[0-9]{4}/[0-9]{2}/[0-9]{2}|[0-9]{4}年[0-9]{2}月[0-9]{2}日").matcher(date).matches()){
			year=Integer.parseInt(date.substring(0, 4));
			month=Integer.parseInt(date.substring(5, 7));
			day=Integer.parseInt(date.substring(8, 10));
		}
		//月2位  日1位
		if(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{1}|[0-9]{4}/[0-9]{2}/[0-9]{1}|[0-9]{4}年[0-9]{2}月[0-9]{1}日").matcher(date).matches()){
			year=Integer.parseInt(date.substring(0, 4));
			month=Integer.parseInt(date.substring(5, 7));
			day=Integer.parseInt(date.substring(8, 9));
		}
		//月1位  日2位
		if(Pattern.compile("[0-9]{4}-[0-9]{1}-[0-9]{2}|[0-9]{4}/[0-9]{1}/[0-9]{2}|[0-9]{4}年[0-9]{1}月[0-9]{2}日").matcher(date).matches()){
			year=Integer.parseInt(date.substring(0, 4));
			month=Integer.parseInt(date.substring(5, 6));
			day=Integer.parseInt(date.substring(7, 9));
		}
		//月1位  日1位
		if(Pattern.compile("[0-9]{4}-[0-9]{1}-[0-9]{1}|[0-9]{4}/[0-9]{1}/[0-9]{1}|[0-9]{4}年[0-9]{1}月[0-9]{1}日").matcher(date).matches()){
			year=Integer.parseInt(date.substring(0, 4));
			month=Integer.parseInt(date.substring(5, 6));
			day=Integer.parseInt(date.substring(7, 8));
		}
		StringBuffer datestr=new StringBuffer();
		datestr.append(year+"-"+(month<9?"0"+month:month)+"-"+(day<9?"0"+day:day));
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(datestr.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
