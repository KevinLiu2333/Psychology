package com.klsw.website.controller.bg;
  
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo; 
import com.klsw.common.mall.model.TMallProduct;
import com.klsw.website.controller._BaseController;
import com.klsw.website.service.ProductService;

 
@Controller
@RequestMapping("/manage")
public class Bg_productController extends _BaseController {
	static final int PAGESIZE = 10;
	@Autowired  
	private ProductService productService;
	
	@RequestMapping(value="order/productList")
    public ModelAndView productList(HttpServletRequest request,Model model){
		ModelAndView mav = new ModelAndView("background/bg_product/bg_product_list");
		List<TMallProduct> productList= null;
		String pageNum = request.getParameter("pageNum"); 
		String name=request.getParameter("name");
		 if(pageNum==null){
			   PageHelper.startPage(1, PAGESIZE); 
		}
		else{
			  PageHelper.startPage(Integer.parseInt(pageNum), PAGESIZE);
			
		} 

		PageHelper.orderBy("ctime desc");
		
		try {
			if(name==null){ 		
				productList=productService.selectAll(); 
			}else{ 
				
				 productList=productService.selectByProduct(name); 
 			  
			}
			 model.addAttribute("name",name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageInfo<TMallProduct> pageInfo = new PageInfo<>(productList); 
		mav.addObject("productList", pageInfo);
    	return mav;
    }
	 
	
}

