package com.wonders.qxkz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.Constants;
import com.wonders.qxkz.QXConstants;
import com.wonders.tiles.authority.service.SystemConstants;

public class AdminQx implements ActionFilter{

	@Override
	public View match(ActionContext act) {
		HttpServletRequest req = act.getRequest();
		HttpSession session = req.getSession();
		String roleId = (String)session.getAttribute(Constants.ROLE_LIST);
		if(roleId.indexOf(QXConstants.ADMIN) >= 0){
			return null;
		}
		return new ServerRedirectView("/qx.jsp");
	}

}
