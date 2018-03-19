package com.kevin.nutzbook.shiro.realm;

import com.kevin.nutzbook.bean.Permission;
import com.kevin.nutzbook.bean.Role;
import com.kevin.nutzbook.bean.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.nutz.dao.Dao;
import org.nutz.integration.shiro.SimpleShiroToken;
import org.nutz.mvc.Mvcs;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/19
 * Time: 10:42
 */
public class SimpleAuthorizingRealm extends AuthorizingRealm {

    protected Dao dao;  //ShiroFilter先于NutFilter初始化，所以无法使用注入

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // null usernames are valid
        if (principals == null) {
            throw new ArithmeticException("PrincipalCollection method argument cannot be null.");
        }
        int userId = (Integer) principals.getPrimaryPrincipal();
        User user = dao().fetch(User.class, userId);

        if (user == null) {
            return null;
        }
        if (user.isLocked()) {
            throw new LockedAccountException("Account [" + user.getName() + "] is locked.");
        }
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        user = dao().fetchLinks(user, null);//查询到一对一的关联信息
        if (user.getRoles() != null) {
            dao().fetchLinks(user.getRoles(), null);
            for (Role role : user.getRoles()) {
                auth.addRole(role.getName());
                if (role.getPermissions() != null) {
                    for (Permission p : role.getPermissions()) {
                        auth.addStringPermission(p.getName());
                    }
                }
            }
        }
        if (user.getPermissions() != null) { // 特许/临时分配的权限
            for (Permission p : user.getPermissions()) {
                auth.addStringPermission(p.getName());
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        SimpleShiroToken upToken = (SimpleShiroToken) token;
        // upToken.getPrincipal() 的返回值就是SimpleShiroToken构造方法传入的值
        // 可以是int也可以是User类实例,或任何你希望的值,自行处理一下就好了
        User user = dao().fetch(User.class, ((Integer) upToken.getPrincipal()).longValue());
        if (user == null)
            return null;
        if (user.isLocked())
            throw new LockedAccountException("Account [" + user.getName() + "] is locked.");
        return new SimpleAccount(user.getId(), user.getPassword(), getName());
    }

    /**
     * 覆盖父类的验证，pass掉
     *
     * @param token token
     * @param info  info
     * @throws AuthenticationException 异常
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
    }

    public Dao dao() {
        if (dao == null) {
            dao = Mvcs.ctx().getDefaultIoc().get(Dao.class, "dao");
            return dao;
        }
        return dao;
    }

}
