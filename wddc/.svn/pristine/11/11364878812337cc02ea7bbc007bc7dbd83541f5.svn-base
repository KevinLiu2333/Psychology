package com.wonders.sjfw.at;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

import com.wonders.sjfw.entity.FwAccess;
import com.wonders.sjfw.entity.FwApply;
import com.wonders.sjfw.entity.FwInfo;
import com.wonders.sjfw.entity.LogFw;
import com.wonders.wddc.config.SessionFilter;
import com.wonders.wddc.suite.user.entity.UserUnitBo;
import com.wonders.wddc.tiles.jk.entity.User;
/**
 * 服务平台提供查看使用，不需要用户登录，全部函数的都为ajax数据返回或者页面返回.
 *
 */
@At("/open/fw")
@IocBean(fields = "dao")
@Filters
public class FwOpenAct {

    private Dao dao;
    
    /**
     * 根据服务代码查询服务对象信息 .
     */
    @At
    @Ok("json")
    public Map<String, Object> fwInfoData(HttpServletRequest request,String fwCode) {
        //回传数据
        Map<String,Object>  result= new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        cri.where().and("fwCode", "=", fwCode);
        FwInfo fwInfo = dao.fetch(FwInfo.class,cri);
        //服务对象信息
        result.put("fwInfo",fwInfo);
        
        User sessionUser = (User)request.getSession().getAttribute(SessionFilter.SESSION_USER);
        if(sessionUser != null){
        	UserUnitBo userUnit = dao.fetch(UserUnitBo.class, sessionUser.getUnitId());
        	FwAccess fwAccess = dao.fetch(FwAccess.class,Cnd.where("fwInfoId", "=", fwInfo.getFwInfoId()).and("unitKey", "=", userUnit.getUnitKey()));
        	FwApply fwApply = dao.fetch(FwApply.class,Cnd.where("fwInfoId", "=", fwInfo.getFwInfoId()).and("unitKey", "=", userUnit.getUnitKey()));
            result.put("fwAccess",fwAccess);
            result.put("fwApply",fwApply);
        }
        return result;

    }
    


}
