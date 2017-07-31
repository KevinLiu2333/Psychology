package com.wonders.tiles.workflow.renderer;

import java.util.ArrayList;
import java.util.List;
import org.nutz.ioc.loader.annotation.IocBean;
import com.wonders.tiles.workflow.entity.WfUser;
@IocBean
public class DefaultRender implements WfRender{

	@Override
	public WfUser getUserInfoById(String userId) {
		//RestClient client = ClientUtil.getClient();
		
		WfUser wfUser = new WfUser();
		return wfUser;
	}

	@Override
	public List<WfUser> genUserListByRoleId(String roleId) {
		List<WfUser> wfUserList = new ArrayList<WfUser>();
		
		return wfUserList;
	}

	@Override
	public String genUserIdsByRoleId(String roleId) {
		String result = "";
		
		return result;
	}
	
	@Override
	public String genUserIdsByFilter(String roleId ,String deptCode) {
		//总的结果
		String result = "";
		
		return result;
	}

	@Override
	public String genUserIdsByDeptCode(String deptCode) {
		String result = "";
		
		return result;
	}
	
}
