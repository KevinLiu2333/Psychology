package com.klsw.website.controller;

import com.klsw.common.mall.model.TMallProduct;
import com.klsw.common.mall.model.TMallUser;
import com.klsw.website.service.ProductService;
import com.klsw.website.service.SmsService;
import com.klsw.website.service.UserService;
import com.klsw.website.util.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 商城入口
 * @author ZhouPingping
 *
 */
@Controller
@RequestMapping("/")
public class MallController extends _BaseController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("mall")
	public String  productList(HttpServletRequest request ,Model model) {
		
		List<TMallProduct> list= null; 
		
		List<TMallProduct>  listTwo=null;
			
		try {	
			
		/*	list = productService.selectCanSaleProductList();	*/
			list=productService.selectBySerialId(Constants.HORIZONTAL_SERIALID);
			
			listTwo=productService.selectBySerialId(Constants.TRIGON_SERIALID);
			model.addAttribute("list", list);
			model.addAttribute("listTwo", listTwo);
			
			
		} catch(Exception e) {
			
		}
		return  "mall/mall";
	}
/*	
 	@RequestMapping("malls")
	public ModelAndView productID(HttpServletRequest request){
		List<TMallProduct> list = null;
		list=productService
		
		list=productService.selectBySerialId();
				
		return null;
		
	}*/
	
	
	@RequestMapping("mobile/captcha")
	@ResponseBody
	public String mobileCaptcha(HttpServletRequest request, @RequestParam String imageCaptcha, @RequestParam String mobile) {
		
		//获取存储的图片验证码
		Object captcha = request.getSession().getAttribute(Constants.IMAGE_CAPTCHA);
		
		//转为字符串...做存在判断
		String storeCaptcha = captcha == null ? null : (String) captcha;
		
		try {
			//图片验证码通过
			if (StringUtils.isNotBlank(imageCaptcha) && StringUtils.isNotBlank(storeCaptcha)
					&& imageCaptcha.equalsIgnoreCase(storeCaptcha)) {
				//验证手机号
				TMallUser user = new TMallUser();
				user.setMobile(mobile);
				
				int count = userService.selectCount(user);
				
				if(count > 0) {
					return "手机号码已被注册！";
				}
				String randomString = RandomStringUtils.randomNumeric(6);
				String content = "验证码是" + randomString + "，请在注册页面输入以完成注册。";
				
				request.getSession().setAttribute(Constants.MOBILE_CAPTCHA, randomString);
				request.getSession().setAttribute(Constants.MOBILE_CAPTCHA_PHONE, mobile);
				
				//短信接口
				smsService.sendSms(mobile, content);
				
				return "ok";
				
			} else {
				return "图片验证码错误";
			}
			
		} catch(Exception e) {
			return "验证码发送失败";
		}
	}
	
	
	@RequestMapping("mobile/phoneCaptcha")
	@ResponseBody
	public String phoneCaptcha(HttpServletRequest request, @RequestParam("mobile") String mobile) {
		try {
				//验证手机号
				TMallUser user = new TMallUser();
				user.setMobile(mobile);
				user = userService.selectOne(user);
				if(user != null) {
					String randomString = RandomStringUtils.randomNumeric(6);
					String content = "验证码是" + randomString + "，请在账号验证页面输入以完成验证。";
					request.getSession().setAttribute(Constants.MOBILE_CAPTCHA, randomString);
					request.getSession().setAttribute(Constants.MOBILE_CAPTCHA_PHONE, mobile);
					
					//短信接口
					smsService.sendSms(mobile, content);
					return "ok";
				}
				return "fail";
			} catch(Exception e) {
				return "验证码发送失败";
			}
	}
	
	@RequestMapping("product/{productSerial}")
	public String productList(HttpServletRequest request, @PathVariable("productSerial") String productSerial,Model model) {
		
//		Map<String, Object> resultMap = Maps.newHashMap();
		
		TMallProduct product = null;
		TMallProduct SProduct = null;
		
		try {
			product = productService.selectProductDetailBySerial(productSerial);
			
			List<TMallProduct> products = productService.selectAll();
			for(TMallProduct entity : products) {
				if(entity.getSeriesid()!= product.getSeriesid()){
					SProduct = entity;
				}
			}

			List<TMallProduct> seriesProductList = productService.selectSameSeriesProduct(product.getSeriesid());
//			
//			resultMap.put("product", product);
//			resultMap.put("seriesProductList", seriesProductList);
			
			product.setSeriesProductList(seriesProductList);
			
		} catch(Exception e) {
			
		}
		model.addAttribute("SProduct", SProduct);
		model.addAttribute("product", product);
		return "mall/product";
		
	}
	
	/*@RequestMapping("product/{productSerial}")
	@ResponseBody
	public TMallProduct productList(HttpServletRequest request, @PathVariable("productSerial") String productSerial) {
		
		TMallProduct product = null;
		
		try {
			product = productService.selectProductDetailBySerial(productSerial);
		} catch(Exception e) {
			
		}
		
		return product;
		
	}*/
	
	
	
}
