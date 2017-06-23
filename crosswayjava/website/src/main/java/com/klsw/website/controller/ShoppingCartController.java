package com.klsw.website.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.klsw.common.mall.model.TMallProduct;
import com.klsw.website.service.ProductService;
import com.klsw.website.vo.ShoppingCart;
import com.klsw.website.vo.ShoppingCartList;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController extends _BaseController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("")
	public String shoppingCart() throws Exception {
		return "mall/shopcart";
	}
	
	@RequestMapping("/info")
	public ModelAndView cartInfo(HttpServletRequest request) throws Exception {
		List<ShoppingCart> cartList = Lists.newArrayList();
		
		Double totalM = 0d;
		
		Map<String, Integer> cartMap = getShoppingCart(request);
		
		if(cartMap.size() > 0) {
			
			List<String> serialList = Lists.newArrayList();
			
			for (String key : cartMap.keySet()) {  
				serialList.add(key);
			}
			
			List<TMallProduct> productList = productService.selectBySerialList(serialList);
			
			if(productList != null && productList.size() > 0) {
				ShoppingCart shoppingCart = null;
				for(TMallProduct product : productList) {
					shoppingCart = new ShoppingCart();
					shoppingCart.setProductColor(product.getColor());
					shoppingCart.setProductImgurl(product.getImgurl());
					shoppingCart.setProductName(product.getName());
					shoppingCart.setProductPrice(product.getPrice());
					shoppingCart.setProductSerial(product.getSerial());
					shoppingCart.setProductCount(cartMap.get(shoppingCart.getProductSerial()));
					Double total = shoppingCart.getProductCount() * shoppingCart.getProductPrice();
					shoppingCart.setTotal(total);
					totalM += total;
					cartList.add(shoppingCart);
				}
			}
		}
		
		ShoppingCartList shoppingCart = new ShoppingCartList();
		shoppingCart.setCart(cartList);
		shoppingCart.setTotalM(totalM);
		
		return new ModelAndView("mall/cartDetail", "shoppingCart", shoppingCart);
	}
	
	@RequestMapping("/product/{operate}/{serial}/{count}")
	public void addProduct(HttpServletRequest request, HttpServletResponse response, @PathVariable String serial, @PathVariable int count, @PathVariable String operate) {
		
		String result = "";
		
		try {
			Map<String, Integer> cartMap = getShoppingCart(request);
			
			if(cartMap.get(serial) == null) cartMap.put(serial, 0);
			
			Integer productCount = 0;
			
			if("incr".equals(operate)) {
				productCount = cartMap.get(serial) + count;
			} else if("decr".equals(operate)) {
				productCount = cartMap.get(serial) - count;
			} else if("set".equals(operate)) {
				productCount = count;
			}
			
			if(productCount < 0) productCount = 0;
			
			cartMap.put(serial, productCount);
			
			writeShoppingCartToCookie(response, cartMap);
			
			result = "ok";
			
		}catch(Exception e) {
			result = "fail";
		}
		
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.print(result);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
