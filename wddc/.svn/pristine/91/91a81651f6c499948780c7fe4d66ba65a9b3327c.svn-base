package com.wonders.wddc.suite.user.at;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.wddc.suite.user.entity.UserUnitBo;
import com.wonders.wddc.tiles.tools.DateUtils;

@At("/suite/unit")
@IocBean(fields = "dao")
public class UnitAct {

	private Dao dao;

    /**
     * 单位管理页面.
     */
    @At
    @Ok("jsp:wddc.suite.user.unit_list")
    public  Object toUnitList(){
        //获取所有标签
    	Map<String,Object>  result= new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        List<UserUnitBo> unitList = dao.query(UserUnitBo.class, cri);
        result.put("unitList",unitList);
        return result;
    }
    /**
	 * 进入单位新增修改页面.
	 * @param unitId
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.user.unit_edit")
		public Map<String, Object> toUnitEdit(String unitId){
		Map<String, Object> result = new HashMap<String, Object>();
		if(!Strings.isEmpty(unitId)){
			UserUnitBo userUnit = dao.fetch(UserUnitBo.class, unitId);
			result.put("userUnit", userUnit);
		}
		return result;
	}
	

	/**
	 * 新增，修改功能的保存操作.
	 */
	
	@At
    @Ok("redirect:/suite/unit/toUnitList")
	public void saveUnit(@Param("::userUnit.") UserUnitBo userUnit, HttpServletRequest request){
		if(Strings.isEmpty(userUnit.getUnitId())){
			String key = "SJ"+DateUtils.date2String(new Date(), DateUtils.FORMAT_YEAR)+System.currentTimeMillis();
			userUnit.setUnitKey(key);
			dao.insert(userUnit);
		}else{
			UserUnitBo oldUserUnit = dao.fetch(UserUnitBo.class, userUnit.getUnitId());
			userUnit.setUnitKey(oldUserUnit.getUnitKey());
			dao.update(userUnit);
		}
	}
	

    /**
	 * 查询所有单位的信息.
	 * @param unitId
	 * @return
	 */
	@At
	@Ok("json")
	public Map<String, Object> allUnitData(){
		Map<String, Object> result = new HashMap<String, Object>();
        Criteria cri = Cnd.cri();
		List<UserUnitBo> userUnitList = dao.query(UserUnitBo.class, cri);
		result.put("result", userUnitList);
		return result;
	}
	
	/**
	 * 单位的删除功能,ajax调用.
	 */
	@At
	@Ok("json")
	public void delWork(String unitId){
		if(Strings.isNotBlank(unitId)){
			UserUnitBo userUnit = dao.fetch(UserUnitBo.class, unitId);
			if(userUnit != null){
				dao.delete(userUnit);
			}
		}
	}
	
	
	
}
