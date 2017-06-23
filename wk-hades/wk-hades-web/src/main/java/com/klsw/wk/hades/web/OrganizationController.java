package com.klsw.wk.hades.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.klsw.wk.hades.domain.Organization;
import com.klsw.wk.hades.exception.AjaxException;
import com.klsw.wk.hades.service.IOrganizationService;
import com.klsw.wk.hades.util.Pager2;

/**
 * 机构管理
 * @author liulixi
 * @version 2017年6月21日10:59:18
 */
@Controller
@Scope("prototype")
@RequestMapping("/org")
public class OrganizationController extends BaseController {
	@Autowired
    private IOrganizationService organizationService;
	
	@RequestMapping("listUI")
    public String listUI(Model model, HttpServletRequest request) {
        try {
            return "/org/index";
        } catch (Exception e) {
            throw new AjaxException(e);
        }
    }
	
	/**
     * ajax分页动态加载模式
     *
     * @param gridPager Pager对象
     * @throws Exception
     */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody Object list(String dtGridPager) throws Exception {
    	List<Organization> organizationList = organizationService.getAll(new Organization(dtGridPager));
        return new Pager2<Organization>((Page<Organization>) organizationList);
    }

    @RequestMapping(value = "/add")
    public Organization add() {
        return new Organization();
    }

    @RequestMapping(value = "/view/{id}")
    public Organization view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView();
        Organization organization = organizationService.getById(id);
        return organization;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        organizationService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelMap save(Organization organization) {
        ModelMap result = new ModelMap();
        String msg = organization.getId() == null ? "新增成功!" : "更新成功!";
        organizationService.save(organization);
        result.put("city", organization);
        result.put("msg", msg);
        return result;
    }
}
