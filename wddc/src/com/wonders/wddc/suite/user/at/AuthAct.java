package com.wonders.wddc.suite.user.at;

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

import com.wonders.wddc.suite.user.entity.AuthorityBo;
import com.wonders.wddc.tiles.sn.SnCreator;

@At("/suite/auth")
@IocBean(fields = "dao")
public class AuthAct {
	private Dao dao;

    /**
     * 单位管理页面.
     */
    @At
    @Ok("jsp:wddc.suite.user.auth_list")
    public  Object toAuthList(){
        //获取所有标签
    	Map<String,Object>  result= new HashMap<String,Object>();
        Criteria cri = Cnd.cri();
        List<AuthorityBo> authorityList = dao.query(AuthorityBo.class, cri);
        result.put("authorityList",authorityList);
        return result;
    }
    
    /**
	 * 进入资源新增修改页面.
	 * @param unitId
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.user.auth_edit")
		public Map<String, Object> toAuthEdit(String nodeId){
		Map<String, Object> result = new HashMap<String, Object>();
		if(!Strings.isEmpty(nodeId)){
			AuthorityBo auth = dao.fetch(AuthorityBo.class, nodeId);
			result.put("auth", auth);
		}
		return result;
	}
	

	/**
	 * 新增，修改功能的保存操作.
	 */
	
	@At
    @Ok("redirect:/suite/auth/toAuthList")
	public void saveAuth(@Param("::auth.") AuthorityBo auth, HttpServletRequest request){
		if(Strings.isEmpty(auth.getNodeId())){
			String code = "";
			if("菜单".equals(auth.getNodeType())){
				code = SnCreator.getInstance().getSN("auth_menu", 4, "menu");
			}else{
				code = SnCreator.getInstance().getSN("auth_bun", 4, "btn");
			}
			auth.setNodeCode(code);
			auth.setNodeId(System.currentTimeMillis()+"");
			dao.insert(auth);
		}else{
			AuthorityBo oldAuthority = dao.fetch(AuthorityBo.class, auth.getNodeId());
			auth.setNodeCode(oldAuthority.getNodeCode());
			dao.update(auth);
		}
	}
	
	
	
	/**
	 * 资源的删除功能,ajax调用.
	 */
	@At
	@Ok("json")
	public void delAuth(String nodeId){
		if(Strings.isNotBlank(nodeId)){
			AuthorityBo auth = dao.fetch(AuthorityBo.class, nodeId);
			if(auth != null){
				dao.delete(auth);
			}
		}
	}
}
