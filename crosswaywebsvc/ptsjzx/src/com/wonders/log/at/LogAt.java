package com.wonders.log.at;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.api.entity.ApiLogWebService;
import com.wonders.log.entity.LoginLog;
import com.wonders.log.entity.OperateLog;
import com.wonders.log.entity.SystemLog;
import com.wonders.pzgl.entity.DwLog;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.extend.adaptor.util.ConUtils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@At("/log")
@IocBean
public class LogAt {

    @Inject
    private Dao dao;

    @At
    @Ok("jsp:jsp.log.index")
    public Map<String, Object> toIndex() {
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    @At
    @Ok("jsp:jsp.log.log_login")
    public Map<String, Object> toLoginLog(HttpServletRequest request, String loginName, Date czsj1, Date czsj2, String count) {
        Map<String, Object> result = new HashMap<String, Object>();
        Pager pager = ConUtils.makePaginationPager(request);
        Criteria cri = Cnd.cri();
        if (!Strings.isEmpty(loginName)) {
            cri.where().and("OPERATE_USER", "=", loginName);
        }
        if (czsj1 != null) {
            cri.where().and("to_char(OPERATE_DATE,'yyyymmdd')", ">=", new SimpleDateFormat("yyyyMMdd").format(czsj1));
        }
        if (czsj2 != null) {
            cri.where().and("to_char(OPERATE_DATE,'yyyymmdd')", "<=", new SimpleDateFormat("yyyyMMdd").format(czsj2));
        }
        cri.getOrderBy().desc("OPERATE_DATE");
        if ("1".equals(count)) {
            int c = dao.count(LoginLog.class, cri);
            result.put("count", c);
            result.put("czsj1", czsj1);
            result.put("czsj2", czsj2);
            result.put("loginName", loginName);
        } else {
            List<LoginLog> list = dao.query(LoginLog.class, cri, pager);
            pager.setRecordCount(dao.count(LoginLog.class, cri));
            result.put("list", list);
            result.put("czsj1", czsj1);
            result.put("czsj2", czsj2);
            result.put("loginName", loginName);
            result.put("count", -1);
            result.put("pager", pager);
        }
        return result;
    }

    @At
    @Ok("jsp:jsp.log.log_dw")
    public Map<String, Object> toDwLog(HttpServletRequest request, String user, Date czsj1, Date czsj2, String dept) {
        Map<String, Object> result = new HashMap<String, Object>();
        Pager pager = ConUtils.makePaginationPager(request);
        Criteria cri = Cnd.cri();
        if (!Strings.isEmpty(user)) {
            cri.where().and("OPERATE_USER", "=", user);
        }
        if (!Strings.isEmpty(dept)) {
            cri.where().and("OPERATE_DEPT", "=", dept);
        }
        if (czsj1 != null) {
            cri.where().and("to_char(OPERATE_DATE,'yyyymmdd')", ">=", new SimpleDateFormat("yyyyMMdd").format(czsj1));
        }
        if (czsj2 != null) {
            cri.where().and("to_char(OPERATE_DATE,'yyyymmdd')", "<=", new SimpleDateFormat("yyyyMMdd").format(czsj2));
        }
        cri.getOrderBy().desc("OPERATE_DATE");
        List<DwLog> list = dao.query(DwLog.class, cri, pager);
        pager.setRecordCount(dao.count(DwLog.class, cri));
        result.put("czsj1", czsj1);
        result.put("czsj2", czsj2);
        result.put("list", list);
        result.put("user", user);
        result.put("dept", dept);
        result.put("pager", pager);
        return result;
    }

    @At
    @Ok("jsp:jsp.log.dw_log_view")
    public Map<String, Object> viewDwlog(String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        DwLog log = dao.fetch(DwLog.class, Cnd.where("ID", "=", id));
        if (!Strings.isEmpty(log.getOperateUser())) {
            User user = dao.fetch(User.class, Cnd.where("LOGON_NAME", "=", log.getOperateUser()));
            result.put("user", user);
        }
        result.put("log", log);
        return result;
    }

    @At
    @Ok("jsp:jsp.log.api_log")
    public Map<String, Object> toAPILog(HttpServletRequest request, String dept) {
        Map<String, Object> result = new HashMap<String, Object>();
        Pager pager = ConUtils.makePaginationPager(request);
        Criteria cri = Cnd.cri();
        if (!Strings.isEmpty(dept)) {
            cri.where().and("DEPT_ID", "=", dept);
        }
        cri.getOrderBy().desc("CALL_DATE");
        List<ApiLogWebService> list = dao.query(ApiLogWebService.class, cri, pager);
        pager.setRecordCount(dao.count(ApiLogWebService.class, cri));
        result.put("list", list);
        result.put("dept", dept);
        result.put("pager", pager);
        return result;
    }

    @At
    @Ok("jsp:jsp.log.operate_log")
    public Map<String, Object> toOperateLog(HttpServletRequest request, String loginName) {
        Map<String, Object> result = new HashMap<String, Object>();
        Pager pager = ConUtils.makePaginationPager(request);
        Criteria cri = Cnd.cri();
        if (!Strings.isEmpty(loginName)) {
            cri.where().and("OPERATE_USER", "=", loginName);
        }
        cri.getOrderBy().desc("OPERATE_DATE");
        List<OperateLog> list = dao.query(OperateLog.class, cri, pager);
        pager.setRecordCount(dao.count(OperateLog.class, cri));
        result.put("list", list);
        result.put("loginName", loginName);
        result.put("pager", pager);
        return result;
    }

    /**
     * 系统日志
     *
     * @param repuest
     * @param loginName
     * @return
     */
    @At
    @Ok("jsp:jsp.log.system_log")
    public Map<String, Object> toSystemLog(HttpServletRequest request, String loginName) {
        Map<String, Object> result = new HashMap<String, Object>();
        Pager pager = ConUtils.makePaginationPager(request);
        Criteria cri = Cnd.cri();
        if (!Strings.isEmpty(loginName)) {
            cri.where().and("OPERATE_USER", "=", loginName);
        }
        cri.getOrderBy().desc("OPERATE_DATE");
        List<SystemLog> list = dao.query(SystemLog.class, cri, pager);
        pager.setRecordCount(dao.count(SystemLog.class, cri));
        result.put("list", list);
        result.put("loginName", loginName);
        result.put("pager", pager);
        return result;

    }

    /**
     * 详情显示用户中文名
     *
     * @param id
     * @return
     */
    @At
    @Ok("jsp:jsp.log.log_name_view")
    public Map<String, Object> viewName(String id) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = dao.fetch(User.class, Cnd.where("LOGON_NAME", "=", id));
        result.put("user", user);
        return result;

    }

    @At
    @Ok("jsp:jsp.log.log_count")
    public Map<String, Object> toLogCount(HttpServletRequest request, String user, Date czsj1, Date czsj2) {
        Map<String, Object> result = new HashMap<String, Object>();
        String condition_login = " where 1=1 "; // 登录
        String condition_query = " where 1=1 "; // 查询
        String condition = "";
        if (!Strings.isEmpty(user)) {
            condition_query += " and OPERATE_USER ='" + user + "'";
            condition_login += " and OPERATE_USER ='" + user + "'";
        }
        if (czsj1 != null) {
            condition_login = condition_login + " and to_char(OPERATE_DATE,'yyyymmdd')>='" + new SimpleDateFormat("yyyyMMdd").format(czsj1) + "'";
            condition_query = condition_login + " and to_char(OPERATE_DATE,'yyyymmdd')>='" + new SimpleDateFormat("yyyyMMdd").format(czsj1) + "'";
        }
        if (czsj2 != null) {
            condition_login = condition_login + " and to_char(OPERATE_DATE,'yyyymmdd')<='" + new SimpleDateFormat("yyyyMMdd").format(czsj2) + "'";
            condition_query = condition_login + " and to_char(OPERATE_DATE,'yyyymmdd')<='" + new SimpleDateFormat("yyyyMMdd").format(czsj2) + "'";
        }
        String conditionstr = null;
        if (czsj1 != null && czsj2 != null) {
            conditionstr = new SimpleDateFormat("yyyy年MM月dd日").format(czsj1) + "至" + new SimpleDateFormat("yyyy年MM月dd日").format(czsj2);
        } else if (czsj1 != null && czsj2 == null) {
            conditionstr = new SimpleDateFormat("yyyy年MM月dd日").format(czsj1) + "至今";
        } else if (czsj1 == null && czsj2 != null) {
            conditionstr = new SimpleDateFormat("yyyy年MM月dd日").format(czsj2) + "之前";
        }
        condition += (Strings.isEmpty(conditionstr)) ? "" : conditionstr;
        condition = condition + ((Strings.isEmpty(user) ? "" : (" 登录名：" + user)));
        // 登录统计
        Sql sql = Sqls.create("select count(1) from (select count(1) from log_login " + condition_login + " group by OPERATE_USER)");
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("login_countuser", sql.getResult());
        sql = Sqls.create("select count(1) from log_login " + condition_login + " and OPERATE_TYPE='登录'");
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("login_countsucess", sql.getResult());
        result.put("login_condition", Strings.isEmpty(condition) ? "无" : condition);
        // 查询统计
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='1'");
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query1", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='2'");
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query2", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='3'");
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query3", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='4'");
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query4", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='5'");
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query5", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='6'");
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query6", sql.getResult());
        if (Strings.isEmpty(condition)) {
            sql = Sqls.create("select count(1) from TEST_USER_INFO WHERE STATE = '1' ");// 没有日期范围，显示所有开启用户
        } else {
            sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='kq'");// 开启用户数
        }
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("kq", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='gb'");// 关闭用户数
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("gb", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='xg'");// 权限修改数
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("xg", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='7'");// 自然人统计次数
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query7", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='8'");// 房屋查询次数
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query8", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='9'");// 自然人对比次数
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query9", sql.getResult());
        sql = Sqls.create("select count(1) from DW_LOG " + condition_query + " and COUNT_TYPE='10'");// 法人对比次数
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query10", sql.getResult());
        sql = Sqls.create("select count(1) from log_operate " + condition_query + " and operate_result = '上报成功'");// 一数据源上报成功次数
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query11", sql.getResult());
        sql = Sqls.create("select count(1) from log_operate " + condition_query + " and operate_result = '双公示上报成功'");// 双公示上报成功次数
        sql.setCallback(new getOneStringCallBack());
        dao.execute(sql);
        result.put("query12", sql.getResult());

        result.put("query_condition", (Strings.isEmpty(condition) ? "无" : condition));
        result.put("user", user);
        result.put("czsj1", czsj1);
        result.put("czsj2", czsj2);
        return result;
    }

    @SuppressWarnings("unchecked")
    @At
    @Ok("jsp:jsp.log.log_population")
    public Map<String, Object> toPopulation(HttpServletRequest request, Date czsj1, Date czsj2) {
        Map<String, Object> result = new HashMap<String, Object>();
        Pager pager = ConUtils.makePaginationPager(request);
        String condition = "1=1 ";
        if (czsj1 != null) {
            String time1 = new SimpleDateFormat("yyyyMMdd").format(czsj1);
            condition += "and start_time >= " + time1 + " ";
        }
        if (czsj2 != null) {
            String time2 = new SimpleDateFormat("yyyyMMdd").format(czsj2);
            condition += "and start_time <= " + time2 + " ";
        }
        Sql sql = Sqls.create("select * from " + "(select substr(start_TIME,0,8) start_time,substr(end_TIME,0,8) end_time,status,outputnum,elapsedtime from t_etllog t "
                + "where jobid = 'T_GA_RJBXX_PT' group by start_TIME,end_TIME,status,outputnum,elapsedtime order by start_time desc)" + "where " + condition + " ");
        sql.setPager(pager);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                List<Object> result = new ArrayList<Object>();
                while (rs.next()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("start_time", rs.getString("start_time"));
                    map.put("end_time", rs.getString("end_time"));
                    map.put("status", rs.getString("status"));
                    String sta = rs.getString("status");
                    if (sta.equals("S")) {
                        map.put("status", "成功");
                    }
                    if (sta.equals("F")) {
                        map.put("status", "失败");
                    }
                    map.put("outputnum", rs.getString("outputnum"));
                    map.put("elapsedtime", rs.getString("elapsedtime"));
                    result.add(map);
                }
                return result;
            }
        });
        dao.execute(sql);
        List<Object> list = (List<Object>) sql.getResult();
        sql = Sqls.create("select count(1) from ( select * from "
                + "(select substr(start_TIME,0,8) start_time,substr(end_TIME,0,8) end_time,status,outputnum,elapsedtime from t_etllog t "
                + "where jobid = 'T_GA_RJBXX_PT' group by start_TIME,end_TIME,status,outputnum,elapsedtime order by start_time desc)" + "where " + condition + " ) ");
        sql.setCallback(new SqlCallback() {

            @Override
            public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                if (rs.next())
                    return rs.getInt(1);
                return 0;
            }
        });
        dao.execute(sql);
        pager.setRecordCount((Integer) sql.getResult());
        result.put("czsj1", czsj1);
        result.put("czsj2", czsj2);
        result.put("pager", pager);
        result.put("list", list);
        return result;
    }

    @SuppressWarnings({"unchecked"})
    @At
    @Ok("jsp:jsp.log.log_interface_receive")
    public Map<String, Object> toInterfaceReceiveLog(HttpServletRequest request, String rksj1, String rksj2) {
        Map<String, Object> result = new HashMap<String, Object>();
        Pager pager = ConUtils.makePaginationPager(request);
        String condition = "where 1=1 ";
        if (!Strings.isEmpty(rksj1)) {
            condition += " and substr( INPUTDATE, 0, 6 ) >= '" + rksj1.replaceAll("-", "") + "'";
            try {
                result.put("rksj1", new SimpleDateFormat("yyyyMMdd").parse(rksj1.replaceAll("-", "") + "01"));
            } catch (ParseException e) {
                result.put("rksj1", null);
            }
        }
        if (!Strings.isEmpty(rksj2)) {
            condition += " and substr( INPUTDATE, 0, 6 ) <= '" + rksj2.replaceAll("-", "") + "'";
            try {
                result.put("rksj2", new SimpleDateFormat("yyyyMMdd").parse(rksj2.replaceAll("-", "") + "01"));
            } catch (ParseException e) {
                result.put("rksj2", null);
            }
        }
        Sql sql = Sqls.create("select SERVICE_NAME,TABLE_NAME,DEPT_NAME,sum(inputnum) as suminputnum,sum(CALL_NUM) as sumcallnum " + "from interface_receive_log t " + condition
                + " group by SERVICE_NAME,TABLE_NAME,DEPT_NAME ");
        // sql.setPager(pager);
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                List<Object> result = new ArrayList<Object>();
                while (rs.next()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name", rs.getString("SERVICE_NAME"));
                    map.put("dept", rs.getString("DEPT_NAME"));
                    map.put("suminputnum", rs.getString("suminputnum"));
                    map.put("sumcallnum", rs.getString("sumcallnum"));
                    String ca = rs.getString("TABLE_NAME");
                    sql = Sqls.create("select count(1) from " + ca + " ");
                    sql.setCallback(new SqlCallback() {
                        @Override
                        public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                            if (rs.next())
                                return rs.getInt(1);
                            return 0;
                        }
                    });
                    dao.execute(sql);
                    map.put("inputnum", sql.getResult());
                    sql = Sqls.create("select inputdate from INTERFACE_RECEIVE_LOG "
                            + "where table_name = '" + ca + "' order by inputdate desc");
                    sql.setCallback(new SqlCallback() {
                        @Override
                        public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                            if (rs.next())
                                return rs.getString(1);
                            return 0;
                        }
                    });
                    dao.execute(sql);
                    map.put("inputdate", sql.getResult());
                    result.add(map);
                }
                return result;
            }
        });
        dao.execute(sql);
        List<Object> list = (List<Object>) sql.getResult();
        sql = Sqls.create("select count(1) from (" + "select SERVICE_NAME,sum(inputnum) as num,sum(CALL_NUM) as cou " + "from interface_receive_log t " + condition
                + " group by SERVICE_NAME )");
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
                if (rs.next())
                    return rs.getInt(1);
                return 0;
            }
        });
        dao.execute(sql);
        pager.setRecordCount((Integer) sql.getResult());
        result.put("list", list);
        result.put("pager", pager);
        return result;
    }

    /**
     * 导出日志统计
     */
    @At
    public void exportLogCount(HttpServletResponse response, String login_condition, String login_countuser, String login_countsucess, String kq, String gb, String xg,
                               String query_condition, String query1, String query2, String query3, String query4, String query5, String query6, String query7, String query8, String query9,
                               String query10, String query11, String query12) {
        try {
            OutputStream os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("日志统计列表.xls", "utf-8"));
            response.setContentType("application/msexcel");
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            jxl.SheetSettings sheetset = sheet.getSettings();
            sheetset.setProtected(false);
            /** ************设置单元格字体************** */
            WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);
            WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            /** ************以下设置三种单元格样式，灵活备用************ */
            // 用于标题居中
            WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
            wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐
            wcf_center.setWrap(false); // 文字是否换行
            // 用于正文居左
            WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);
            wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条
            wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐
            wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐
            wcf_left.setWrap(false); // 文字是否换行
            /** ***************以下是EXCEL第一列 ********************* */
            sheet.addCell(new Label(0, 0, "统计类型", wcf_center));
            sheet.addCell(new Label(0, 1, "统计条件", wcf_center));
            sheet.addCell(new Label(0, 2, "登录用户数", wcf_center));
            sheet.addCell(new Label(0, 3, "登录次数", wcf_center));
            sheet.addCell(new Label(0, 4, "开启用户数", wcf_center));
            sheet.addCell(new Label(0, 5, "关闭用户数", wcf_center));
            sheet.addCell(new Label(0, 6, "修改权限数", wcf_center));
            sheet.addCell(new Label(0, 8, "统计类型", wcf_center));
            sheet.addCell(new Label(0, 9, "统计条件", wcf_center));
            sheet.addCell(new Label(0, 10, "查询人口列表", wcf_center));
            sheet.addCell(new Label(0, 11, "查询人口详情", wcf_center));
            sheet.addCell(new Label(0, 12, "比对人口信息", wcf_center));
            sheet.addCell(new Label(0, 13, "统计人口信息", wcf_center));
            sheet.addCell(new Label(0, 14, "人口房屋查询", wcf_center));
            sheet.addCell(new Label(0, 15, "查询法人列表", wcf_center));
            sheet.addCell(new Label(0, 16, "查询法人详情", wcf_center));
            sheet.addCell(new Label(0, 17, "导出法人列表", wcf_center));
            sheet.addCell(new Label(0, 18, "导出法人详情", wcf_center));
            sheet.addCell(new Label(0, 19, "比对法人信息", wcf_center));
            sheet.addCell(new Label(0, 20, "一数据源上报", wcf_center));
            sheet.addCell(new Label(0, 21, "双公示上报", wcf_center));
            /** ***************以下是EXCEL第二列 ********************* */
            sheet.addCell(new Label(1, 0, "管理日志", wcf_center));
            sheet.addCell(new Label(1, 1, login_condition, wcf_center));
            sheet.addCell(new Label(1, 2, login_countuser + "人", wcf_center));
            sheet.addCell(new Label(1, 3, login_countsucess + "次", wcf_center));
            sheet.addCell(new Label(1, 4, kq + "人", wcf_center));
            sheet.addCell(new Label(1, 5, gb + "人", wcf_center));
            sheet.addCell(new Label(1, 6, xg + "人", wcf_center));
            sheet.addCell(new Label(1, 8, "查询日志", wcf_center));
            sheet.addCell(new Label(1, 9, query_condition, wcf_center));
            sheet.addCell(new Label(1, 10, query1 + "次", wcf_center));
            sheet.addCell(new Label(1, 11, query2 + "次", wcf_center));
            sheet.addCell(new Label(1, 12, query9 + "次", wcf_center));
            sheet.addCell(new Label(1, 13, query7 + "次", wcf_center));
            sheet.addCell(new Label(1, 14, query8 + "次", wcf_center));
            sheet.addCell(new Label(1, 15, query3 + "次", wcf_center));
            sheet.addCell(new Label(1, 16, query4 + "次", wcf_center));
            sheet.addCell(new Label(1, 17, query5 + "次", wcf_center));
            sheet.addCell(new Label(1, 18, query6 + "次", wcf_center));
            sheet.addCell(new Label(1, 19, query10 + "次", wcf_center));
            sheet.addCell(new Label(1, 20, query11 + "次", wcf_center));
            sheet.addCell(new Label(1, 21, query12 + "次", wcf_center));
            workbook.write();
            /** *********关闭文件************* */
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class getOneStringCallBack implements SqlCallback {
        @Override
        public Object invoke(Connection conn, ResultSet rs, Sql sql) throws SQLException {
            if (rs.next()) {
                return rs.getString(1);
            }
            return null;
        }

    }
}
