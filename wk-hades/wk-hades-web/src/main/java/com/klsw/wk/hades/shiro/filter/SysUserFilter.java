package com.klsw.wk.hades.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.klsw.wk.hades.dao.UserMapper;

public class SysUserFilter extends PathMatchingFilter {

	@Autowired
	private UserMapper userDao;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String userName = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute("user", userDao.findByName(userName));
        return true;
    }
}