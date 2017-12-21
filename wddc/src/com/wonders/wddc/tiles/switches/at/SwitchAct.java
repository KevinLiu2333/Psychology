package com.wonders.wddc.tiles.switches.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.wddc.suite.logger.service.LogCoreService;
import com.wonders.wddc.tiles.sn.SnCreator;
import com.wonders.wddc.tiles.switches.Switches;
import com.wonders.wddc.tiles.switches.entity.SwitchInfoBo;

/**
 * 开关组件
 * @author wonders
 *
 */
@At("/kernel/switch")
@IocBean
public class SwitchAct {
	
	@Inject
	private Dao dao;
	@Inject
	private LogCoreService logService;
	
	/**
	 * 开关列表首页
	 * @return
	 */
	@At
	@Ok("jsp:wddc.tiles.switches.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result = new HashMap<>();
		List<SwitchInfoBo> list = dao.query(SwitchInfoBo.class, null);
		result.put("list", list);
		return result;
		
	}
	
	/**
	 * 新增、编辑开关
	 * @return
	 */
	@At
	@Ok("jsp:wddc.tiles.switches.edit")
	public Object addOrEdit(String id){
		if(!Strings.isEmpty(id)){
			SwitchInfoBo infoBo =dao.fetch(SwitchInfoBo.class,id);
			return infoBo;
		}
		return null;
		
	}
	
	/**
	 * 编辑保存
	 * @param info
	 * @return
	 */
	@At
	public View saveSwitch(@Param("::info.")SwitchInfoBo info,HttpServletRequest request){
		if(Strings.isEmpty(info.getId())){
			info.setCode(SnCreator.getInstance().getSN("Switch", 3, "SW"));
			SwitchInfoBo infoBo = dao.insert(info);
			Switches.getInstance().addSwitch(infoBo);
			logService.insertOpLogAndDetail("xt", "xt201", "开关操作", "新增开关:"+infoBo.getName(), "", request);
		}else{
			Switches.getInstance().ChangeState(info);;
		    dao.update(info);
		    logService.insertOpLogAndDetail("xt", "xt201", "开关操作", "编辑开关:"+info.getName(), "", request);
		}
		return new ServerRedirectView("/kernel/switch/toIndex");
		
	}
	
	/**
	 * 删除开关
	 * @param id
	 * @return
	 */
	@At
	@Ok("json")
	public int delSwitch(String id,HttpServletRequest request){
		int flag = 0;
		if(!Strings.isEmpty(id)){
			SwitchInfoBo infoBo = dao.fetch(SwitchInfoBo.class,id);
			Switches.getInstance().removeSwitch(infoBo);
			flag = dao.delete(infoBo);
			logService.insertOpLogAndDetail("xt", "xt201", "开关操作", "删除开关："+infoBo.getName(), "", request);
		}
		return flag;
		
	}
	
	
	/**
	 * 改变开关状态
	 * @param id
	 * @param state
	 * @return
	 */
	@At
	@Ok("json")
	public String switchTask(String id,String state,HttpServletRequest request){
		SwitchInfoBo infoBo = dao.fetch(SwitchInfoBo.class,id);
		
		if(!Strings.isEmpty(state)){
			infoBo.setValue(state);
			Switches.getInstance().ChangeState(infoBo);
			dao.update(infoBo);
			String mString = "关闭";
			if("1".equals(infoBo.getValue())){
				mString = "开启";
			}
			logService.insertOpLogAndDetail("xt", "xt201", "开关操作", "将开关"+infoBo.getName()+mString, "", request);
		}
		
		return "1";
		
	}
	
	
	
	
	

}
