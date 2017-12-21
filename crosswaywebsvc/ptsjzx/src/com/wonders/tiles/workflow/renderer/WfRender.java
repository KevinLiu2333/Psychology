package com.wonders.tiles.workflow.renderer;

import java.util.List;

import com.wonders.tiles.workflow.entity.WfUser;
/**
 * 流程适配器
 *
 */

public interface WfRender {
	/**
	 * 生成用户信息.
	 * @param userId
	 * @return
	 */
	public WfUser getUserInfoById(String userId);

	/**
	 * 根据角色获取用户列表信息.
	 * @param userId
	 * @return
	 */
	public List<WfUser> genUserListByRoleId(String roleId);

	/**
	 * 根据角色获取用户idd字符串，用逗号号隔开.
	 * @param userId
	 * @return
	 */
	public String genUserIdsByRoleId(String roleId);

	/**
	 * 根据角色获取用户idd字符串，用逗号号隔开.
	 * @param userId
	 * @return
	 */
	public String genUserIdsByDeptCode(String deptCode);
	
	/**
	 * 根据部门过滤节点中的 人.
	 * @param roleId
	 * @param deptCode
	 * @return
	 */
	public String genUserIdsByFilter(String roleId ,String deptCode);
}
