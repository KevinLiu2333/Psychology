package com.wonders.wddc.suite.user.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.wddc.suite.logger.entity.LogLoginBo;

@At("/suite/userStat")
@IocBean(fields = "dao")
public class UserStatAct {

	private Dao dao;
	 /**
     * 登入登出日志信息
     * @return
     */
    @At
    @Ok("jsp:wddc.suite.user.login_log_list")
    public Map<String,Object> toLoginLogList(){
    	Map<String,Object> result = new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        cri.getOrderBy().desc("opTime");
        List<LogLoginBo> logLoginList = dao.query(LogLoginBo.class, cri);
    	result.put("logLoginList", logLoginList);
		return result;
    }
    /**
     * 在线用户
     * @return
     */
    @At
    @Ok("jsp:wddc.suite.user.user_online")
    public Map<String,Object> userOnline(){
    	Map<String,Object> result = new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        cri.getOrderBy().desc("opTime");
        List<LogLoginBo> logLoginList = dao.query(LogLoginBo.class, cri);
    	result.put("logLoginList", logLoginList);
		return result;
    }
}
