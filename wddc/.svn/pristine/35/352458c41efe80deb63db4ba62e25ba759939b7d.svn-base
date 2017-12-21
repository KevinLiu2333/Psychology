package com.wonders.wddc.tiles.jk;

import com.wonders.wddc.tiles.jk.entity.User;

/**
 * 用户服务接口.
 */
public interface UserService {
	/**
	 * 平台seesion用户的key.
	 */
    public final static String SESSION_USER = "session_user";
    /**
     * 判断用户验证是否通过.
     * @param logonName 登录名
     * @param password 密码
     * @return
     */
    public boolean login(String logonName, String password);

    /**
     * 根据登录名称获取用户信息.
     * @param logonName 登录名
     * @return
     */
    public User getUser(String logonName);

}
