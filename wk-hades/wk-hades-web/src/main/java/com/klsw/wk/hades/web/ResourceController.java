package com.klsw.wk.hades.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.klsw.wk.hades.domain.Resource;
import com.klsw.wk.hades.exception.AjaxException;
import com.klsw.wk.hades.exception.SystemException;
import com.klsw.wk.hades.service.IResourceService;
import com.klsw.wk.hades.util.Constants;
import com.klsw.wk.hades.util.JSTreeEntity;
import com.klsw.wk.hades.util.Pager;
import com.klsw.wk.hades.util.PagerPropertyUtils;
import com.klsw.wk.hades.util.Select2Entity;
import com.klsw.wk.hades.util.TreeUtil;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping("/resource/")
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping("listUI")
    public String listUI(Model model, HttpServletRequest request) {
        try {
            return "/authorize/resource/index";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }


    @SuppressWarnings("unchecked")
	@RequestMapping("list")
    @ResponseBody
    public Object list(String dtGridPager) throws Exception {
        Pager<Resource> pager = new Pager<Resource>();
		pager.setIsSuccess(false);
		try {
			pager = PagerPropertyUtils.copy(JSONObject.fromObject(dtGridPager), Resource.class);
			Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
			List<Resource> list = resourceService.queryListByPage(pager.getParameters());
			pager.setExhibitDatas(list);
			pager.setRecordCount(page.getTotal());
			pager.setIsSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pager;
    }


    @RequestMapping("resourceTree")
    @ResponseBody
    public Object resourceTree(int roleId) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        List<JSTreeEntity> jstreeList = null;
        try {
            parameter.put("isHide", 0);
            //parameter.put("type", 0);
            parameter.put("roleId", roleId);
            List<Resource> list = resourceService.queryResourceList(parameter);
            jstreeList = new TreeUtil().generateJSTree(list);
        } catch (Exception e) {
            jstreeList = new ArrayList<JSTreeEntity>();
            LOG.error(e.getMessage(), e);
        }
        return jstreeList;
    }


    @RequestMapping("resourceSelectTree")
    @ResponseBody
    public Object resourceSelectTree() {
        List<Select2Entity> select2Entity = null;
        try {
            Map<String, Object> parameter = new HashMap<String, Object>();
            List<Resource> list = resourceService.queryListByPage(parameter);
            select2Entity = new TreeUtil().getSelectTree(list, null);
            parameter.clear();
            parameter.put("items", select2Entity);
            return parameter;
        } catch (Exception e) {
            select2Entity = new ArrayList<Select2Entity>();
            throw new AjaxException(e);
        }
    }


    @RequestMapping("addUI")
    public String addUI(Model model, HttpServletRequest request) {
        try {
            List<Resource> list = resourceService.queryListByPage(new HashMap<String, Object>());
            List<Select2Entity> select2List = new TreeUtil().getSelectTree(list, null);

            model.addAttribute("select2List", select2List);
            return "/authorize/resource/addform";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(Resource resourceEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            resourceEntity.setIsHide(0);
            resourceEntity.setCreateTime(new Date());
            if (resourceEntity.getParentId() != null) {
                resourceEntity.setIcon(null);
            }
            int result = resourceService.insert(resourceEntity);
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
            Resource resourceEntity = resourceService.findById(id);
            List<Resource> list = resourceService.queryListByPage(new HashMap<String, Object>());
            List<Select2Entity> select2List = new TreeUtil().getSelectTree(list, null);

            model.addAttribute("resourceEntity", resourceEntity);
            model.addAttribute("select2List", select2List);
            return "/authorize/resource/editform";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("edit")
    @ResponseBody
    public Object update(Resource resourceEntity) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (resourceEntity.getType() == 1) {
                resourceEntity.setIcon(null);
            }
            int result = resourceService.update(resourceEntity);
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
            int cnt = resourceService.deleteBatchById(list);
            if (cnt == list.size()) {
                setResponseSuccessMessage(result,null,"删除成功");
            } else {
                setResponseFailedMessage(result,null,"编辑失败");
            }
        } catch (Exception e) {
            throw new AjaxException(e);
        }
        return result;
    }


    @RequestMapping("icon")
    public String icon() {
        return Constants.BACKGROUND_PATH + "/authorize/resource/icon";
    }

}
