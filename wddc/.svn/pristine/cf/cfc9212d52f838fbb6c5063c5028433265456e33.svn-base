package com.wonders.wdac.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.wdac.entity.RCheckResult;
import com.wonders.wdac.entity.RJob;
import com.wonders.wdac.entity.RJobResult;

@At("/jh")
@IocBean(fields = "dao")
public class JhAct {
	private Dao dao;
	
	@At("/sjjh")
	@Ok("jsp:wdac.jh.date_change")
	public void sjjh(){
		
	}
	
	@At("/jhpz")
	@Ok("jsp:wdac.jh.change_deploy")
	public Map<String,Object> jhpz(){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.getOrderBy().asc("idJob");
		List<RJob> jobList = dao.query(RJob.class,cri);
		result.put("jobList", jobList);
		return result;
	}
	
	@At("/jhjg")
	@Ok("jsp:wdac.jh.change_result")
	public Map<String,Object> jhjg(){
		Map<String,Object> result = new HashMap<String,Object>();
		String sqlStr ="select RESULT_NAME,ID_JOB,SUM(LINES_READ),SUM(LINES_WRITTEN),SUM(LINES_UPDATED),SUM(LINES_INPUT),SUM(LINES_OUTPUT),SUM(LINES_REJECTED),SUM(ERRORS)"
			+" FROM ac_job_result"
			+" group by RESULT_NAME,ID_JOB ";
		Sql sql = Sqls.create(sqlStr);
		sql.setCallback(new SqlCallback(){

			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
					throws SQLException {
				List<RJobResult> jobList = new ArrayList<RJobResult>();
				while(rs.next()){
					RJobResult rJobResult =new RJobResult();
					rJobResult.setresultName(rs.getString("RESULT_NAME"));
					rJobResult.setIdJob(rs.getString("ID_JOB"));
					rJobResult.setLinesRead(rs.getInt("SUM(LINES_READ)"));
					rJobResult.setLinesWritten(rs.getInt("SUM(LINES_WRITTEN)"));
					rJobResult.setLinesUpdated(rs.getInt("SUM(LINES_UPDATED)"));
					rJobResult.setLinesInput(rs.getInt("SUM(LINES_INPUT)"));
					rJobResult.setLinesRejected(rs.getInt("SUM(LINES_REJECTED)"));
					rJobResult.setErrors(rs.getInt("SUM(ERRORS)"));
					jobList.add(rJobResult);
				}
				return jobList;
			}
			
		});
		dao.execute(sql);
		result.put("resultList", sql.getResult());
		return result;
	}
	
	@At("/jgxx")
	@Ok("jsp:wdac.jh.result_info")
	public Map<String,Object> jgxx(String idJob){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.getOrderBy().desc("endDate");
		cri.where().and("idJob","=",idJob);
		List<RJobResult> list = dao.query(RJobResult.class, cri);
		result.put("infoList", list);
		return result;
	}
	
	@At("/sjjy")
	@Ok("jsp:wdac.jh.check_result")
	public Map<String,Object> sjjy(){
		Map<String,Object> result = new HashMap<String,Object>();
		String sqlStr ="select CHECK_NAME,ID_JOB,SUM(LINES_WRITTEN),SUM(LINES_REJECTED),SUM(ERRORS)"
			+" FROM ac_check_result"
			+" group by CHECK_NAME,ID_JOB ";
		Sql sql = Sqls.create(sqlStr);
		sql.setCallback(new SqlCallback(){

			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
					throws SQLException {
				List<RCheckResult> jobList = new ArrayList<RCheckResult>();
				while(rs.next()){
					RCheckResult rCheckResult =new RCheckResult();
					rCheckResult.setcheckName(rs.getString("CHECK_NAME"));
					rCheckResult.setIdJob(rs.getString("ID_JOB"));
					rCheckResult.setLinesWritten(rs.getInt("SUM(LINES_WRITTEN)"));
					rCheckResult.setLinesRejected(rs.getInt("SUM(LINES_REJECTED)"));
					rCheckResult.setErrors(rs.getInt("SUM(ERRORS)"));
					jobList.add(rCheckResult);
				}
				return jobList;
			}
			
		});
		dao.execute(sql);
		result.put("checkList", sql.getResult());
		return result;
	}
	
	@At("/jyjg")
	@Ok("jsp:wdac.jh.check_info")
	public Map<String,Object> jyjg(String idJob){
		Map<String,Object> result = new HashMap<String,Object>();
		Criteria cri = Cnd.cri();
		cri.getOrderBy().desc("endDate");
		cri.where().and("idJob","=",idJob);
		List<RCheckResult> list = dao.query(RCheckResult.class, cri);
		result.put("infoList", list);
		return result;
	}
}
