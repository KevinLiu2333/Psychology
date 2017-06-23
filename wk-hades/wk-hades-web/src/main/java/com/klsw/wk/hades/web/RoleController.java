package com.klsw.wk.hades.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.klsw.unbiz.common.StringUtil;
import com.klsw.wk.hades.domain.Resource;
import com.klsw.wk.hades.domain.Role;
import com.klsw.wk.hades.domain.User;
import com.klsw.wk.hades.exception.AjaxException;
import com.klsw.wk.hades.exception.SystemException;
import com.klsw.wk.hades.service.IResourceService;
import com.klsw.wk.hades.service.IRoleService;
import com.klsw.wk.hades.util.Constants;
import com.klsw.wk.hades.util.Pager;
import com.klsw.wk.hades.util.PagerPropertyUtils;
import com.klsw.wk.hades.util.TreeUtil;

@Controller
@Scope("prototype")
@RequestMapping("/role/")
public class RoleController extends BaseController {
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("listUI")
    public String listUI() {
        try {
            return "/authorize/role/index";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping("list")
    public Object list(String dtGridPager) throws Exception {
        Pager<Role> pager = new Pager<Role>();
		pager.setIsSuccess(false);
		try {
			pager = PagerPropertyUtils.copy(JSONObject.fromObject(dtGridPager), Role.class);
			Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
			List<Role> list = roleService.queryListByPage(pager.getParameters());
			pager.setExhibitDatas(list);
			pager.setRecordCount(page.getTotal());
			pager.setIsSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pager;
    }


    @RequestMapping("addUI")
    public String addUI() {
        return "/authorize/role/addform";
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(Role roleEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            roleEntity.setCreateTime(new Date());
            roleEntity.setStatus(0);
            int result = roleService.insert(roleEntity);
            if (result > 0) {
                setResponseSuccessMessage(map,null,"添加成功");
            } else {
                setResponseFailedMessage(map,null,"添加失败");
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


    @RequestMapping("editUI")
    public String editUI(Model model, HttpServletRequest request, Long id) {
        try {
            Role roleEntity = roleService.findById(id);
            model.addAttribute("roleEntity", roleEntity);
            return "/authorize/role/editform";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("edit")
    @ResponseBody
    public Object update(Role roleEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            int result = roleService.update(roleEntity);
            if (result > 0) {
                setResponseSuccessMessage(map,null,"编辑成功");
            } else {
                setResponseFailedMessage(map,null,"编辑失败");
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


    @RequestMapping("deleteBatch")
    @ResponseBody
    public Object deleteBatch(String ids) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String[] roleIds = ids.split(",");
            List<Long> list = new ArrayList<Long>();
            for (String string : roleIds) {
                list.add(Long.valueOf(string));
            }
            int cnt = roleService.deleteBatchById(list);
            if (cnt == list.size()) {
                setResponseSuccessMessage(result,null,"删除成功");
            } else {
                setResponseFailedMessage(result,null,"删除失败");
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return result;
    }


    @RequestMapping("permissionUI")
    public String permissionUI(Model model, HttpServletRequest request, Long id) {
        try {
            Role roleEntity = roleService.findById(id);
            model.addAttribute("roleEntity", roleEntity);
            return "/authorize/role/permission";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }


    @RequestMapping("permission")
    @ResponseBody
    public Object permission(int roleId, String resourceIds,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Integer> list = new ArrayList<Integer>();
            if (StringUtil.isNotBlank(resourceIds)) {
                for (String id : resourceIds.split(",")) {
                    list.add(Integer.valueOf(id));
                }
            }
            boolean result = roleService.addRolePerm(roleId, list);
            if (result) {
                User userEntity = (User) request.getSession().getAttribute("userSession");
                List<Resource> rsList = resourceService.findResourcesByUserId(userEntity.getId().intValue());
                List<Resource> treeList = new TreeUtil().getChildResourceEntitys(rsList, null);
                request.getSession().setAttribute("list", treeList);
                request.getSession().setAttribute("userEntity", userEntity);
                setResponseSuccessMessage(map,null,"授权成功");
            } else {
                setResponseFailedMessage(map,null,"授权失败");
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return map;
    }


}
