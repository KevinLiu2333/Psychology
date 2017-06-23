package com.klsw.website.controller.bg;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.common.mall.model.TMallProduct;
import com.klsw.common.mall.model.TMallService;
import com.klsw.website.controller._BaseController;
import com.klsw.website.service.ProductService;
import com.klsw.website.service.TMallServiceService;

@Controller
@RequestMapping("/manage/order")
public class Bg_SaledService extends _BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(Bg_SaledService.class);
	
	@Autowired
	private TMallServiceService tMallService;
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 售后单查询功能
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saledList")
	public String saledList(Model model) {
		List<TMallService> serviceList = Lists.newArrayList();
		try {
			List<TMallService> list = tMallService.selectAll();
			PageHelper.startPage(1,3);
			if(list != null) {
				for(TMallService service : list) {
					TMallProduct product = new TMallProduct();
					product.setSerial(service.getProductserial());
					product = productService.selectOne(product);
					service.setProductname(product.getName());
					service.setProductcolor(product.getColor());
					serviceList.add(service);
				}
				
			}
			PageInfo< TMallService> pageInfo = new PageInfo<TMallService>(serviceList);
			model.addAttribute("serviceList", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			model.addAttribute("msg", "服务器异常，请稍后重试！");
			return "redirect:/manage";
		}
		return "background/bg_order/bg_service_list";
	}
}
