package com.wonders.sjtb.timer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.sjtb.entity.TbColumns;
import com.wonders.sjtb.entity.TbContents;
import com.wonders.sjtb.entity.TbCorp;
import com.wonders.sjtb.entity.TbPerson;
import com.wonders.sjtb.entity.TbSheet;

@IocBean
public class SjtbTask {
	
	@Inject
	private Dao dao;
	
	public void extract(){
		extractpersondata();
		extractcorpdata();
		extractcyjjdata();
	}
	public void extractcorpdata(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1); 
		String sjssyf = new SimpleDateFormat("yyyyMM").format(calendar.getTime());
		List<TbContents> contents = dao.query(TbContents.class, Cnd.where("ORDER_NO","=","2"));
		for(final TbContents content:contents){
			List<TbSheet> sheets = dao.query(TbSheet.class, Cnd.where("TABEL_BM","=",content.getName()));
			for(final TbSheet sheet : sheets){
				Criteria cri = Cnd.cri();
				cri.where().and("TABLE_BM","=",content.getName());
				cri.where().and("SHEET_ID","=",sheet.getOrderNo());
				cri.where().andNotIsNull("EXTRACT_FLAG");
				cri.getOrderBy().asc("EXTRACT_FLAG");
				final List<TbColumns> columns =dao.query(TbColumns.class, cri);
				if(columns!=null&&columns.size()>0){
					StringBuffer sqlstr=new StringBuffer("select ID,DATA_YEAR");
					for(TbColumns c:columns){
						sqlstr.append(","+c.getColumnName());
					}
					sqlstr.append(" from "+columns.get(0).getTableName()+" t where DATA_YEAR='"+sjssyf+"' and  (");
					for(int i=0;i<columns.size();i++){
						sqlstr.append(columns.get(i).getColumnName()+" is not null ");
						if(i<columns.size()-1){
							sqlstr.append(" or ");
						}
					}
					sqlstr.append(") and id not in (select TB_DATA_ID from TB_CORP c where c.TB_DATA_ID = t.ID "
							+ " and t.DATA_YEAR = c.DATAMONTH and c.TABLENAME ='"+columns.get(0).getTableBm()+"')");
					Sql sql = Sqls.create(sqlstr.toString());
					sql.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection connection, ResultSet rs, Sql sql)
								throws SQLException {
							List<TbCorp> result = new ArrayList<TbCorp>();
							while(rs.next()){
								TbCorp corp = new TbCorp();
								corp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
								corp.setTbdataid(rs.getString("ID"));
								corp.setDeptid(content.getDeptId());
								corp.setSheetid(sheet.getOrderNo()+"");
								corp.setDatamonth(rs.getString("DATA_YEAR"));
								corp.setTablename(columns.get(0).getTableBm());
								corp.setSheetname(sheet.getSheetName());
								for(TbColumns c:columns){
									if(c.getExtractflag().equals("1")){ //统一信用代码
										corp.setShtyxydm(rs.getString(c.getColumnName()));
									}
									if(c.getExtractflag().equals("2")){ //组织机构代码
										corp.setZzjgdm(rs.getString(c.getColumnName()));
									}
									if(c.getExtractflag().equals("3")){ //营业执照注册号
										corp.setYyzzzch(rs.getString(c.getColumnName()));
									}
									if(c.getExtractflag().equals("4")){ //纳税人识别号
										corp.setNsrsbh(rs.getString(c.getColumnName()));
									}
									if(c.getExtractflag().equals("5")){ //法人名称
										corp.setCorpname(rs.getString(c.getColumnName()));
									}
								}
								result.add(corp);
							}
							return result;
						}
					});
					dao.execute(sql);
					@SuppressWarnings("unchecked")
					List<TbCorp> list=(List<TbCorp>) sql.getResult();
					for(TbCorp corp:list){
						dao.insert(corp);
					}
				}
			}
		}
	}
	public void extractpersondata(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1); 
		String sjssyf = new SimpleDateFormat("yyyyMM").format(calendar.getTime());
		List<TbContents> contents = dao.query(TbContents.class, Cnd.where("ORDER_NO","=","1"));
		for(final TbContents content:contents){
			List<TbSheet> sheets = dao.query(TbSheet.class, Cnd.where("TABEL_BM","=",content.getName()));
			for(final TbSheet sheet : sheets){
				final TbColumns columns=dao.fetch(TbColumns.class, Cnd.where("TABLE_BM","=",content.getName()).and("SHEET_ID","=",sheet.getOrderNo()).and("EXTRACT_FLAG","=","1"));
				if(columns!=null){
					Sql sql=Sqls.create("select ID,DATA_YEAR,"+columns.getColumnName()+
							" zjhm from "+columns.getTableName()+" t where DATA_YEAR='"+sjssyf+"' and "+columns.getColumnName()+" is not null and id not in ( select "
							+ "TB_DATA_ID from TB_PERSON p where p.TB_DATA_ID = t.ID and "
							+ "t.DATA_YEAR = p.DATAMONTH and p.TABLENAME ='"+columns.getTableBm()
							+"' )");
					sql.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection conn, ResultSet rs, Sql sql)
								throws SQLException {
							List<TbPerson> list = new ArrayList<TbPerson>();
							while (rs.next()) {
								TbPerson person = new TbPerson();
								person.setId(UUID.randomUUID().toString().replaceAll("-", ""));
								person.setZjhm(rs.getString("zjhm"));
								person.setTbdataid(rs.getString("ID"));
								person.setDeptid(content.getDeptId());
								person.setSheetid(sheet.getOrderNo()+"");
								person.setDatamonth(rs.getString("DATA_YEAR"));
								person.setTablename(columns.getTableBm());
								person.setSheetname(sheet.getSheetName());
								list.add(person);
							}
							return list;
						}
					});
					dao.execute(sql);
					@SuppressWarnings("unchecked")
					List<TbPerson> list =(List<TbPerson>) sql.getResult();
					for(TbPerson person:list){
						dao.insert(person);
					}
				}
			}
		}
	}
	
	public void extractcyjjdata(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1); 
		String sjssyf = new SimpleDateFormat("yyyyMM").format(calendar.getTime());
		List<TbContents> contents = dao.query(TbContents.class, Cnd.where("ORDER_NO","=","3"));
		for(final TbContents content:contents){
			List<TbSheet> sheets = dao.query(TbSheet.class, Cnd.where("TABEL_BM","=",content.getName()));
			for(final TbSheet sheet : sheets){
				Criteria cri = Cnd.cri();
				cri.where().and("TABLE_BM","=",content.getName());
				cri.where().and("SHEET_ID","=",sheet.getOrderNo());
				cri.where().andNotIsNull("EXTRACT_FLAG");
				cri.getOrderBy().asc("EXTRACT_FLAG");
				final List<TbColumns> columns =dao.query(TbColumns.class, cri);
				if(columns!=null&&columns.size()>0){
					StringBuffer sqlstr=new StringBuffer("select ID,DATA_YEAR");
					for(TbColumns c:columns){
						sqlstr.append(","+c.getColumnName());
					}
					sqlstr.append(" from "+columns.get(0).getTableName()+" t where DATA_YEAR='"+sjssyf+"' and  (");
					for(int i=0;i<columns.size();i++){
						sqlstr.append(columns.get(i).getColumnName()+" is not null ");
						if(i<columns.size()-1){
							sqlstr.append(" or ");
						}
					}
					sqlstr.append(") and id not in (select TB_DATA_ID from TB_CORP c where c.TB_DATA_ID = t.ID "
							+ " and t.DATA_YEAR = c.DATAMONTH and c.TABLENAME ='"+columns.get(0).getTableBm()+"')");
					Sql sql = Sqls.create(sqlstr.toString());
					sql.setCallback(new SqlCallback() {
						@Override
						public Object invoke(Connection connection, ResultSet rs, Sql sql)
								throws SQLException {
							List<TbCorp> result = new ArrayList<TbCorp>();
							while(rs.next()){
								TbCorp corp = new TbCorp();
								corp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
								corp.setTbdataid(rs.getString("ID"));
								corp.setDeptid(content.getDeptId());
								corp.setSheetid(sheet.getOrderNo()+"");
								corp.setDatamonth(rs.getString("DATA_YEAR"));
								corp.setTablename(columns.get(0).getTableBm());
								corp.setSheetname(sheet.getSheetName());
								for(TbColumns c:columns){
									if(c.getExtractflag().equals("1")){ //统一信用代码
										corp.setShtyxydm(rs.getString(c.getColumnName()));
									}
									if(c.getExtractflag().equals("2")){ //组织机构代码
										corp.setZzjgdm(rs.getString(c.getColumnName()));
									}
									if(c.getExtractflag().equals("3")){ //营业执照注册号
										corp.setYyzzzch(rs.getString(c.getColumnName()));
									}
									if(c.getExtractflag().equals("4")){ //纳税人识别号
										corp.setNsrsbh(rs.getString(c.getColumnName()));
									}
									if(c.getExtractflag().equals("5")){ //法人名称
										corp.setCorpname(rs.getString(c.getColumnName()));
									}
								}
								result.add(corp);
							}
							return result;
						}
					});
					dao.execute(sql);
					@SuppressWarnings("unchecked")
					List<TbCorp> list=(List<TbCorp>) sql.getResult();
					for(TbCorp corp:list){
						dao.insert(corp);
					}
				}
			}
		}
	}
}
