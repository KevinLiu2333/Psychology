package com.klsw.website.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.common.mall.model.TMallAddress;
import com.klsw.common.mall.model.TMallOrder;
import com.klsw.common.mall.model.TMallOrderAddress;
import com.klsw.common.mall.model.TMallOrderProduct;
import com.klsw.common.mall.model.TMallProduct;
import com.klsw.common.mall.model.TMallService;
import com.klsw.common.mall.model.TMallUser;
import com.klsw.website.service.OrderService;
import com.klsw.website.service.ProductService;
import com.klsw.website.service.TMallAddressService;
import com.klsw.website.service.TMallOrderAddressService;
import com.klsw.website.service.TMallOrderProductService;
import com.klsw.website.service.TMallServiceService;
import com.klsw.website.service.UserService;
import com.klsw.website.util.Constants;

import com.klsw.website.util.PasswdEncryption;
import com.klsw.website.util.Ret;

import com.klsw.website.util.UploadUtils;

@Controller
@RequestMapping("/information")
public class InformationController extends _BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private TMallOrderProductService orderProductService;

	@Autowired
	private TMallServiceService tMallServiceService;

	@Autowired
	private TMallAddressService addressService;

	@Autowired
	private TMallOrderAddressService orderAddressService;


	@Autowired
	private UploadUtils uploadUtils;


	static final int PAGESIZE = 2;

	/****

	 * 进入修改密码界面
	 * 
	 * @return
	 */
	@RequestMapping("/toPwd")

	public String toPwd(Model model) {
		model.addAttribute("index", 7);
		return "information/mypassword";
	}

	/**
	 * 修改密码功能
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "password", method = RequestMethod.POST)
	public String password(HttpServletRequest request, RedirectAttributes model) {

		Object objUser = request.getSession(true).getAttribute(Constants.USER);
		TMallUser tmalUser = (TMallUser) objUser;

		// String storeMobile = tmalUser.getMobile();
		Date date = new Date();

		String odlPwd = request.getParameter("odlPwd");
		String newPwd = request.getParameter("newPwd");
		String newPwd2 = request.getParameter("newPwd2");
		if (StringUtils.isBlank(odlPwd)) {

			model.addFlashAttribute("odlPwdMsg", "原密码不能为空");
			return "information/password";
		}
		if (StringUtils.isBlank(odlPwd)) {
			model.addFlashAttribute("newPwdMsg", "新密码不能为空");
			return "information/password";
		}
		if (StringUtils.isBlank(odlPwd)) {
			model.addFlashAttribute("newPwd2Msg", "确认密码不能为空");
			return "information/password";
		}
		try {

			if (PasswdEncryption.checkPasswd(odlPwd, tmalUser.getPassword())) {
				if (!StringUtils.isBlank(newPwd) && newPwd.equals(newPwd2)) {

					tmalUser.setPassword(PasswdEncryption.toPasswd(newPwd));

					tmalUser.setLtime(date);
					userService.updateByPrimaryKey(tmalUser);

				} /*
					 * else{ model.addFlashAttribute("pwdMsg","两次输入密码不一致"); }
					 */
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/login";
	}

	/****
	 * 显示地址
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddress")
	public String toAddress(HttpServletRequest request, Model model) {
		model.addAttribute("index", 6);

		Object objUser = request.getSession(true).getAttribute(Constants.USER);
		TMallUser tmalUser = (TMallUser) objUser;
		Integer storeUser = tmalUser.getId();

		TMallAddress tMallAddress = new TMallAddress();
		tMallAddress.setUserid(storeUser);
		try {
			List<TMallAddress> addressList = addressService.select(tMallAddress);
			model.addAttribute("addressList", addressList);
			Integer addressCount = addressService.selectCount(tMallAddress);
			model.addAttribute("addressCount", addressCount);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "information/myaddress";
	}

	/***
	 * 新增地址
	 * 
	 * @return
	 */
	@RequestMapping(value = "addAddress", method = RequestMethod.POST)
	public String addAddress(HttpServletRequest request) {
		Object objUser = request.getSession(true).getAttribute(Constants.USER);
		TMallUser tmalUser = (TMallUser) objUser;
		Integer storeUser = tmalUser.getId();
		Date date = new Date();

		String name = request.getParameter("name");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String address = request.getParameter("address");
		String mobile = request.getParameter("mobile");
		String hasElevator = request.getParameter("hasElevator");
		/* String floor = request.getParameter("floor"); */

		TMallAddress taMallAddress = new TMallAddress();

		taMallAddress.setUserid(storeUser);
		taMallAddress.setUsername(name);
		taMallAddress.setUseraddress(address);
		taMallAddress.setUserphone(mobile);
		/* taMallAddress.setFloor(floor); */
		taMallAddress.setElevator(hasElevator);
		taMallAddress.setProvince(province);
		taMallAddress.setCity(city);
		taMallAddress.setDistrict(district);
		taMallAddress.setDefaultaddress(false);
		taMallAddress.setCtime(date);
		taMallAddress.setLtime(date);
		try {
			addressService.insert(taMallAddress);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:toAddress";
	}

	/****
	 * 进入修改地址界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "toModifyAddress", method = RequestMethod.POST)
	public TMallAddress toModifyAddress(HttpServletRequest request, Model model, String id) {

		Integer ids = Integer.parseInt(id);
		TMallAddress tMallAddress = new TMallAddress();
		tMallAddress.setId(ids);

		try {

			tMallAddress = addressService.selectOne(tMallAddress);
			model.addAttribute("tMallAddress", tMallAddress);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tMallAddress;
	}

	/**
	 * 修改地址功能
	 * 
	 * @param request
	 * @param tMallAddress
	 * @return
	 */
	@RequestMapping(value = "modifyAddress", method = RequestMethod.POST)
	public ModelAndView modifyAddress(HttpServletRequest request, TMallAddress tMallAddress) {

		try {
			Date date = new Date();
			tMallAddress.setCtime(date);
			tMallAddress.setLtime(date);
			tMallAddress.setDefaultaddress(false);
			addressService.updateByPrimaryKey(tMallAddress);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return new ModelAndView("information/myaddress");
	}


	/****
	 * 设置默认地址
	 * 
	 * @return
	 */

	@RequestMapping("defaultAddress")
	public String defaultAddress(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("mrdzid"));
		Integer userId = getUserId(request);

		TMallAddress tMallAddress = new TMallAddress();
		tMallAddress.setUserid(userId);
		tMallAddress.setDefaultaddress(true);
		try {
			TMallAddress tMallAddress1 = addressService.selectOne(tMallAddress);
			if(tMallAddress1!=null){
				tMallAddress1.setDefaultaddress(false);
				addressService.updateByPrimaryKeySelective(tMallAddress1);
			}	 
			tMallAddress.setId(id); 
			Date date = new Date();
			tMallAddress.setCtime(date);
			tMallAddress.setLtime(date);
			addressService.updateByPrimaryKeySelective(tMallAddress);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:toAddress";
	}

	/****
	 * 删除地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteAddress")
	public String deleteAddress(HttpServletRequest request) {

		Integer id = Integer.parseInt(request.getParameter("id"));
		TMallAddress tMallAddress = new TMallAddress();
		tMallAddress.setId(id);
		try {
			addressService.delete(tMallAddress);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "redirect:toAddress";
	}

	/**
	 * 进入售后服务界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/saleService")
	public String saleService(HttpServletRequest request, Model model) {
		model.addAttribute("index", 1);
		Integer userId = getUserId(request);
		try {
			TMallOrder order = new TMallOrder();
			order.setUserid(userId);
			List<TMallOrder> tMallOrders = orderService.select(order);
			for (TMallOrder orders : tMallOrders) {
				TMallOrderProduct product = new TMallOrderProduct();
				product.setOrderserial(orders.getSerial());

				List<TMallOrderProduct> productList = orderProductService.select(product);
				List<TMallOrderProduct> orderProductsList = Lists.newArrayList();
				for (TMallOrderProduct orderProduct : productList) {
					TMallProduct tMallProduct = new TMallProduct();
					tMallProduct.setSerial(orderProduct.getProductserial());
					tMallProduct = productService.selectOne(tMallProduct);
					orderProduct.setProductImg(tMallProduct.getImgurl());
					orderProduct.setProductColor(tMallProduct.getColor());
					orderProductsList.add(orderProduct);

					TMallService tMallService = new TMallService();
					tMallService.setOrderserial(orderProduct.getOrderserial());
					tMallService.setProductserial(orderProduct.getProductserial());
					tMallService = tMallServiceService.selectOne(tMallService);
					if (tMallService == null) {
						orderProduct.setSqstatus(0);
					} else {
						orderProduct.setSqstatus(1);
					}
				}
				orders.setProductList(orderProductsList);
				model.addAttribute("tMallOrders", tMallOrders);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "information/sale_service";
	}

	/**
	 * 售后服务申请功能
	 * @param model
	 * @param request
	 * @param hResponse
	 * @param taMallService
	 * @param fileList
	 * @return
	 */
	@RequestMapping("/applyService")

	public String applyService(Model model, HttpServletRequest request, HttpServletResponse hResponse,
			TMallService taMallService, @RequestParam("file") List<MultipartFile> fileList) {

		Date date = new Date();
		Integer userId = getUserId(request);
		try {
			Ret ret = uploadUtils.uploadFile(fileList, "image", "saled");
			if ("S".equals(ret.getStatus())) {
				taMallService.setUserid(userId);
				taMallService.setApplyTime(date);
				taMallService.setHandleTime(date);
				taMallService.setServiceimg((String)ret.getdata());
				tMallServiceService.insert(taMallService);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:saleService";
	}

	/***
	 * 我的賬戶
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/myAccount")
	public ModelAndView myAccount(HttpServletRequest request, Model model) {
		model.addAttribute("index", 3);
		String status = request.getParameter("status");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			PageHelper.startPage(1, PAGESIZE);
		} else {
			PageHelper.startPage(Integer.parseInt(pageNum), PAGESIZE);

		}
		List<TMallOrder> orderList = Lists.newArrayList();		try {
			List<TMallOrder> tMallOrders = Lists.newArrayList();
			TMallOrder order = new TMallOrder();
			Integer userId = getUserId(request);
			if (status == null || status == "") {
				order.setUserid(userId);
				tMallOrders = orderService.select(order);
			} else {
				order.setUserid(userId);
				order.setStatus(new Integer(status));
				tMallOrders = orderService.select(order);
			}
			for (TMallOrder orders : tMallOrders) {

				TMallOrderAddress orderAddress = new TMallOrderAddress();
				orderAddress.setOrderserial(orders.getSerial());

				orderAddress = orderAddressService.selectOne(orderAddress);


				orders.setReceiver(orderAddress.getUsername());				
				TMallOrderProduct product = new TMallOrderProduct();
				product.setOrderserial(orders.getSerial());

				List<TMallOrderProduct> productList = orderProductService.select(product);
				List<TMallOrderProduct> orderProductsList = Lists.newArrayList();
				for (TMallOrderProduct orderProduct : productList) {
					TMallProduct tMallProduct = new TMallProduct();
					tMallProduct.setSerial(orderProduct.getProductserial());
					tMallProduct = productService.selectOne(tMallProduct);
					orderProduct.setProductImg(tMallProduct.getImgurl());
					orderProduct.setProductColor(tMallProduct.getColor());
					orderProductsList.add(orderProduct);
				}
				orders.setProductList(orderProductsList);
				orderList.add(orders);
			}
			PageInfo<TMallOrder> pageInfo = new PageInfo<>(tMallOrders);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("status", status);
			TMallOrder tMallOrder = new TMallOrder();

			tMallOrder.setUserid(userId);			
			Integer totalCount = orderService.selectCount(tMallOrder);
			tMallOrder.setStatus(1);
			Integer noPayCount = orderService.selectCount(tMallOrder);
			tMallOrder.setStatus(5);
			Integer signed = orderService.selectCount(tMallOrder);
			Integer noReceiveCount = totalCount - noPayCount - signed;
			model.addAttribute("nopayCount", noPayCount);
			model.addAttribute("noreceiveCount", noReceiveCount);
			return new ModelAndView("information/myaccount");
		} catch (Exception e) {
			logger.error("order list error >>> ", e);

		}

		return new ModelAndView("information/myaccount");
	}
	


	/**
	 * 进入个人信息修改界面
	 * 
	 * @param model
	 * @return

	 */
	@RequestMapping("/toModifyMessage")
	public String modifyMessage(Model model) {
		model.addAttribute("index", 5);
		return "information/modifyMessage";
	}
	
	/**
	 * 修改个人信息
	 * @param request
	 * @param tMallUser
	 * @param fileList
	 * @return
	 */
	@RequestMapping(value="modifyMessage")
	public String setPortrait(HttpServletRequest request,@RequestParam("filehide")List<MultipartFile> fileList) {
		String nickname = request.getParameter("nickname");
		String sex = request.getParameter("sex");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String birth = request.getParameter("birth");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		TMallUser user = (TMallUser)request.getSession().getAttribute(Constants.USER);
		try {
			String dirName = "image";
			String packageName = "upload/information";
			Ret ret = uploadUtils.uploadFile(fileList, dirName, packageName);
			if("S".equals(ret.getStatus())) {
				String imgPath = (String) ret.getdata();

				user.setImgpath(imgPath);
			}
			user.setBirth(simpleDateFormat.parse(birth));
			user.setMobile(mobile);
			user.setSex(sex);
			user.setNickname(nickname);
			userService.updateByPrimaryKey(user);
			user.setEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "redirect:/information/myAccount";
	}

	/***
	 * 我的优惠券
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/myCoupons")
	public String myCoupons(Model model) {
		model.addAttribute("index", 4);
		return "information/mycoupons";
	}

	/***
	 * 我的服务单
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/serviceList")
	public String serviceList(HttpServletRequest request, Model model) {
		model.addAttribute("index", 2);
		Integer userId = getUserId(request);
		TMallService tMallserivce = new TMallService();
		tMallserivce.setUserid(userId);
		try {
			List<TMallService> serviceList = Lists.newArrayList();
			List<TMallService> tMallServices = tMallServiceService.select(tMallserivce);
			for (TMallService service : tMallServices) {
				TMallProduct tMallProduct = new TMallProduct();
				tMallProduct.setSerial(service.getProductserial());
				tMallProduct = productService.selectOne(tMallProduct);
				service.setProductname(tMallProduct.getName());
				service.setProductcolor(tMallProduct.getColor());
				serviceList.add(service);
			}
			model.addAttribute("serviceList", serviceList);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "information/service_list";
	}
}
