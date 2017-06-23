package com.klsw.website.controller.bg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.common.mall.model.BackgroundOrder;
import com.klsw.common.mall.model.BgAuthority;
import com.klsw.common.mall.model.BgManager;
import com.klsw.common.mall.model.BgRole;
import com.klsw.common.mall.model.TMallLogistics;
import com.klsw.common.mall.model.TMallOrder;
import com.klsw.common.mall.model.TMallOrderAddress;
import com.klsw.common.mall.model.TMallOrderHistory;
import com.klsw.common.mall.model.TMallOrderProduct;
import com.klsw.common.mall.model.TMallProduct;
import com.klsw.common.mall.model.TMallReceipt;
import com.klsw.common.mall.model.TMallUser;
import com.klsw.website.controller._BaseController;
import com.klsw.website.service.OrderHistoryService;
import com.klsw.website.service.OrderService;
import com.klsw.website.service.ProductService;
import com.klsw.website.service.ReceiptService;
import com.klsw.website.service.TMallOrderAddressService;
import com.klsw.website.service.TMallOrderProductService;
import com.klsw.website.service.UserService;
import com.klsw.website.service.background.Bg_AuthorityService;
import com.klsw.website.service.background.Bg_RoleService;
import com.klsw.website.service.background.LogisticsService;
import com.klsw.website.util.Bg_Constants;

/**
 * 该类中主要包含以下功能：
 * 					1、登录后进入后台主页     	2、添加新闻请求
 * 					3、进行订单管理			4、进行确认到账
 * 					5、进行发货			6、查看订单详情
 * @author liukun、hekejun、houzhangyuan
 *
 */
@Controller
@RequestMapping("/manage")
public class Bg_ManagerController extends _BaseController{
	
	static final int PAGESIZE = 4;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderHistoryService orderHistoryService;
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TMallOrderProductService orderProductService;
	
 	@Autowired
 	private TMallOrderAddressService orderAddressService;  
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LogisticsService logisticsService;
	
	@Autowired
	private Bg_RoleService bgRoleService;
	
	@Autowired
	private Bg_AuthorityService bgAuthorityService;
	
	@Autowired
	private ReceiptService receiptService; 
	
	/**
	 * 该请求的功能是：登录成功后进入后台主页
	 * @param request
	 * @return
	 */
	@RequestMapping(value="order")
	public String toIndex(HttpServletRequest request,Model model){
		
		List<BgAuthority> authorityList=Lists.newArrayList();		
		BgManager bgManager=(BgManager) request.getSession().getAttribute(Bg_Constants.MANAGER);
		Integer roleId = bgManager.getRollid();
		BgRole bgRole = new BgRole();
		
		try {
			bgRole.setRoleid(roleId);
			bgRole = bgRoleService.selectOne(bgRole);
			String[] manageAuthority = bgRole.getRoleauthority().split(",");
			for(int i=0;i<manageAuthority.length;i++){
				BgAuthority bgAuthority=new BgAuthority();
				bgAuthority.setAuthorityid(manageAuthority[i]);
				bgAuthority=bgAuthorityService.selectOne(bgAuthority);
				authorityList.add(bgAuthority);
 				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("authorityList",authorityList);
		model.addAttribute("bgRole",bgRole);
		 
		return "background/bg_index";
	}
 
	/**
	 * 该请求的功能是：进入订单操作界面，可以对订单进行确认到账和发货操作，还可以查看订单详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value="order/orderManage")
	public ModelAndView orderManage(HttpServletRequest request,Model model){
		String send = request.getParameter("send");
		String pagenum = request.getParameter("pageNum");
		String index = request.getParameter("index");
		
		String msg = "";
		
		List<BgAuthority> authorityList=Lists.newArrayList();	
		/* 以下部分是获取管理员的权限  */
		BgManager bgManager = (BgManager) request.getSession().getAttribute(Bg_Constants.MANAGER);
		Integer roleId = bgManager.getRollid();
		BgRole bgRole = new BgRole();
		bgRole.setRoleid(roleId);
		try {
		
			bgRole = bgRoleService.selectOne(bgRole);
			model.addAttribute("authority", bgRole.getRoleauthority());
			String[] manageAuthority = bgRole.getRoleauthority().split(",");
			for(int i=0;i<manageAuthority.length;i++) {
				BgAuthority bgAuthoritys=new BgAuthority();
				bgAuthoritys.setAuthorityid(manageAuthority[i]);
				bgAuthoritys=bgAuthorityService.selectOne(bgAuthoritys);
				authorityList.add(bgAuthoritys);
				
				String str = manageAuthority[i];
				if(str.equals("05") || str.equals("0504") || str.equals("0507")|| str.equals("06") || str.equals("0601") || str.equals("0602")) {
					ModelAndView mav = new ModelAndView("background/bg_order/bg_order_list");
					List<TMallOrder> orderList = new ArrayList<TMallOrder>();//创建订单集合
					List<BackgroundOrder> bgOrderList = new ArrayList<BackgroundOrder>();//创建后台需要的实体对象集合，每个订单会对应后台需要的一个实体对象
					Page<BackgroundOrder> page = null;
					String pageNum = request.getParameter("pageNum");
					if(pageNum==null){
						 page = PageHelper.startPage(1, PAGESIZE);
						
					}
					else{
						page = PageHelper.startPage(Integer.parseInt(pageNum), PAGESIZE);
					}
					 PageHelper.orderBy("ctime desc");
					try {
						//获取订单集合
						orderList = orderService.selectAll();
						//遍历订单集合
						for(TMallOrder order : orderList) {
							//创建订单产品集合
							List<TMallOrderProduct> orderproductList = new ArrayList<TMallOrderProduct>();//创建产品集合
							//创建后台封装的一个订单实体对象，下文简称“实体对象”
							BackgroundOrder bgOrder = new BackgroundOrder();
							//设置实体对象的“订单状态”属性
							bgOrder.setStatus(order.getStatus());
							//设置实体对象的“订单序列号”属性
							bgOrder.setOrderSerial(order.getSerial());
							//设置实体对象的“订单时间”属性
							bgOrder.setCtime(order.getCtime());
							bgOrder.setLtime(order.getLtime());
							//设置实体对象“订单总金额”属性
							bgOrder.setTotalPrice(order.getTotalprice());
							//设置实体对象的收货人信息
//							TMallUser user = userService.selectByPrimaryKey(order.getUserid());
							TMallOrderAddress orderAddress = new TMallOrderAddress();
							orderAddress.setOrderserial(order.getSerial());
							orderAddress = orderAddressService.selectOne(orderAddress);
							bgOrder.setReceiver(orderAddress.getUsername());
							
							/*下面是设置实体对象的“订单产品集合”属性全过程*/
							//1.创建“订单产品”对象，一个订单可能会对应多个订单产品对象
							TMallOrderProduct tMallOrderProduct = new TMallOrderProduct();
							//2.设置“订单产品”的“订单序列号”属性
							tMallOrderProduct.setOrderserial(order.getSerial());
							//3.根据订单产品的序列号属性获取该订单对应的所有订单产品集合
							List<TMallOrderProduct> list = orderProductService.select(tMallOrderProduct);
							//4.遍历该订单产品集合
							for(TMallOrderProduct orderProduct : list) {
								//将订单产品增加颜色、图片、名称属性并存入到订单产品集合中
								TMallProduct tMallProduct = productService.selectBySerial(orderProduct.getProductserial());
								orderProduct.setProductImg(tMallProduct.getImgurl());
								orderProduct.setProductColor(tMallProduct.getColor());
								orderProduct.setProductname(tMallProduct.getName());
								orderproductList.add(orderProduct);
							}
							//5.设置实体对象的“订单产品集合”属性
							bgOrder.setOrderProductList(orderproductList);
							
							//将获得的实体对象添加到实体对象集合中
							bgOrderList.add(bgOrder);
							
							//6.查询订单历史记录中状态为1的记录
							TMallOrderHistory orderHistory = new TMallOrderHistory();
							orderHistory.setOrderserial(order.getSerial());
							orderHistory.setStatus(1);
							orderHistory = orderHistoryService.selectOne(orderHistory);
							bgOrder.setComment(orderHistory.getComments());
						}
						
					} catch(Exception e){
						e.printStackTrace();
					}
					PageInfo<BackgroundOrder> pageInfo = new PageInfo<BackgroundOrder>(bgOrderList);
					mav.addObject("bgOrderList",pageInfo);
					mav.addObject("page",page);
					mav.addObject("send", send);
					mav.addObject("index", index);
					mav.addObject("pageNum", pagenum);
					return mav;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		msg = "很抱歉，您无此权限！";
		model.addAttribute("authorityList",authorityList);
		model.addAttribute("bgRole",bgRole);
		return new ModelAndView("background/bg_index" ,"msg",msg);
	}
	
	@RequestMapping(value="order/checkOrder")
	public String checkOrder(HttpServletRequest request) {
		String orderSerial = request.getParameter("orderSerial");
		TMallOrderHistory orderHistory = new TMallOrderHistory();
		orderHistory.setOrderserial(orderSerial);
		orderHistory.setStatus(1);
		try {
			orderHistory = orderHistoryService.selectOne(orderHistory);
			orderHistory.setComments("订单已审核");
			orderHistoryService.updateByPrimaryKey(orderHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/manage/order/orderManage";
	}
	
	/**
	 * 该请求的功能是对订单进行确认到账操作，将订单的状态从 已付款 改变为 已到账（05，0504）
	 * @param referer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="order/comfirmToAccount")
	
	public String comfirmToAccount(@RequestHeader("Referer") String referer,HttpServletRequest request,Model model){
		String msg = "";
				
		List<BgAuthority> authorityList=Lists.newArrayList();	
		/* 以下部分是获取管理员的权限  */
		BgManager bgManager = (BgManager) request.getSession().getAttribute(Bg_Constants.MANAGER);
		Integer roleId = bgManager.getRollid();
		BgRole bgRole = new BgRole();
		bgRole.setRoleid(roleId);
		try {
			
			bgRole = bgRoleService.selectOne(bgRole);
			String[] manageAuthority = bgRole.getRoleauthority().split(",");
			for(int i=0;i<manageAuthority.length;i++) {
				BgAuthority bgAuthoritys=new BgAuthority();
				bgAuthoritys.setAuthorityid(manageAuthority[i]);
				bgAuthoritys=bgAuthorityService.selectOne(bgAuthoritys);
				authorityList.add(bgAuthoritys);
				
				String str = manageAuthority[i];
			    if(str.equals("05") || str.equals("0504")){ 	
					TMallOrder order = new TMallOrder();
					String orderSerial = request.getParameter("orderSerial");
					
					try {
						
						order.setSerial(orderSerial);
						order = orderService.selectOne(order);
						//状态:1,提交订单;2,付款成功;3,货款到账;4,商品出库;20,订单完成;
						order.setStatus(3);
						orderService.updateByPrimaryKey(order);
						
					    /*在数据库表t_mall_order_history中插入一条该订单新状态记录*/
					    //1.创建一个实体对象
						TMallOrderHistory entity = new TMallOrderHistory();
						//2.设置实体对象的订单序列号和订单状态
						entity.setOrderserial(orderSerial);
						entity.setStatus(1);
						//3.根据订单序列号和订单状态进行查询，找出订单状态为1的订单历史记录
						TMallOrderHistory orderHistory = orderHistoryService.selectOne(entity);
						//4.根据查询出的订单历史记录来设置实体对象的除订单序列号和id的其它属性
						entity.setUserid(orderHistory.getUserid());
						entity.setOrderid(orderHistory.getOrderid());
						Date date = new Date();
						entity.setCtime(date);
						entity.setLtime(date);
						entity.setStatus(3);
						//5.向表t_mall_order_history中插入一条该订单的新状态记录
						orderHistoryService.insert(entity);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return "redirect:" + getRequestPathFromReferer(referer);
			    	
			    }
			 }
				
		 }catch(Exception ex){
				
				ex.printStackTrace();
		 }
		 msg="抱歉，您无此权限！";
		 model.addAttribute("authorityList",authorityList);
		 model.addAttribute("bgRole",bgRole);
		 model.addAttribute("msg", msg);
		 return "background/bg_index";
	}
	
	/**
	 * 该请求的功能是：进行发货请求
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="order/delivery")
	public String delivery(HttpServletRequest request,Model model) {
		String index = request.getParameter("index");
		String pageNum = request.getParameter("pageNum");
		
		String msg = "";
		
		List<BgAuthority> authorityList=Lists.newArrayList();
		/* 以下部分是获取管理员的权限  */
		BgManager bgManager = (BgManager) request.getSession().getAttribute(Bg_Constants.MANAGER);
		Integer roleId = bgManager.getRollid();
		BgRole bgRole = new BgRole();
		bgRole.setRoleid(roleId);
		try {
			bgRole = bgRoleService.selectOne(bgRole);
			String[] manageAuthority = bgRole.getRoleauthority().split(",");
			for(int i=0;i<manageAuthority.length;i++) {
				BgAuthority bgAuthoritys=new BgAuthority();
				bgAuthoritys.setAuthorityid(manageAuthority[i]);
				bgAuthoritys=bgAuthorityService.selectOne(bgAuthoritys);
				authorityList.add(bgAuthoritys);
				
				String str = manageAuthority[i];
			    if(str.equals("05") || str.equals("0507")){
			    	return "redirect:orderManage?send=true&pageNum="+new Integer(pageNum)+"&index="+new Integer(index);
			    }
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		msg = "很抱歉，您无此权限！";
		model.addAttribute("authorityList",authorityList);
		model.addAttribute("bgRole",bgRole);
		model.addAttribute("msg", msg);
		return "background/bg_index";
	}
	
	
	@RequestMapping(value="order/receipt")
	@Transactional(rollbackFor = Exception.class)
	public String receipt(HttpServletRequest request) {
		//获取订单序列号和发票物流号
		String orderSerial = request.getParameter("orderSerial");
		String receiptSerial = request.getParameter("receiptSerial");
		Date date = new Date();
		try {
			//更新订单历史中状态为1的评论为"发票物流已编辑"
			TMallOrderHistory orderHistory = new TMallOrderHistory();
			orderHistory.setOrderserial(orderSerial);
			orderHistory.setStatus(1);
			orderHistory = orderHistoryService.selectOne(orderHistory);
			orderHistory.setComments("发票物流已编辑");
			orderHistory.setLtime(date);
			orderHistoryService.updateByPrimaryKey(orderHistory);
			
			//将发票物流单号入库
			TMallReceipt receipt = new TMallReceipt();
			receipt.setOrderserial(orderSerial);
			receipt = receiptService.selectOne(receipt);
			receipt.setReceiptserial(receiptSerial);
			receipt.setLtime(date);
			receiptService.updateByPrimaryKeySelective(receipt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/manage/order/orderManage";
	}
	
	
	/**
	 * 该请求的功能是：创建物流信息并进行发货，将订单状态从 已到账 改变为 已发货（05，0507）
	 * @param request
	 * @param response
	 * @param referer
	 */
 	@RequestMapping(value = "order/logistics", method = RequestMethod.POST)
 	@ResponseBody
 	@Transactional(rollbackFor = Exception.class)
	public void logistics(HttpServletRequest request,HttpServletResponse response,@RequestHeader("Referer") String referer){
 		response.setContentType("text/html; charset=utf-8");
 		PrintWriter out = null;
 		String msg="";
 		String logisticsSerial = request.getParameter("logisticsSerial");
 		TMallLogistics logisti = new TMallLogistics();
 		try {
 			out = response.getWriter();
 			String logisticsName = request.getParameter("logisticsName");
 			String orderSerial=request.getParameter("orderSerial"); 
 			String logisticscompany=request.getParameter("logisticscompany");
 			Date date = new Date();
 			if(StringUtils.isBlank(logisticsName)){
				msg="姓名不能为空";
				out.println("<script language='javascript'>");
				out.println("alert('"+msg+"')");
				out.println("location.href='"+getRequestPathFromReferer(referer)+"'");
				out.println("</script>");
				return;
 			}
 			if(StringUtils.isBlank(logisticsSerial)){
				msg="物流不能为空";
				out.println("<script language='javascript'>");
				out.println("alert('"+msg+"')");
				out.println("location.href='"+getRequestPathFromReferer(referer)+"'");
				out.println("</script>");
				return;
 			}
 			if(StringUtils.isBlank(logisticscompany)){
				msg="物流公司不能为空";
				out.println("<script language='javascript'>");
				out.println("alert('"+msg+"')");
				out.println("location.href='"+getRequestPathFromReferer(referer)+"'");
				out.println("</script>");
				return;
 			}
	 		//判断是否已有发货信息
			logisti.setOrderserial(orderSerial);
	//		logisti = logisticsService.selectOne(logisti);
			//如果为空，新建物流信息
			if(logisticsService.selectOne(logisti) == null){
	//			logisti = new TMallLogistics();	
				logisti.setOrderserial(orderSerial);
				logisti.setLogisticsname(logisticsName);
				logisti.setLogisticsserial(logisticsSerial);
				logisti.setLogisticscompany(logisticscompany);
				logisti.setCtime(date); 
				logisti.setLtime(date);
				logisticsService.insert(logisti);
				//更新数据库表t_tall_order中的订单状态为已发货状态
			    TMallOrder ored=new TMallOrder();
			    ored.setSerial(orderSerial);
			    ored=orderService.selectOne(ored);
			    ored.setStatus(4);
			    ored.setLtime(date);
			    ored.setCtime(date);
			    orderService.updateByPrimaryKey(ored);
			    
			    /*在数据库表t_mall_order_history中插入一条该订单新状态记录*/
			    //1.创建一个实体对象
				TMallOrderHistory entity = new TMallOrderHistory();
				//2.设置实体对象的订单序列号和订单状态
				entity.setOrderserial(orderSerial);
				entity.setStatus(1);
				//3.根据订单序列号和订单状态进行查询，找出订单状态为1的订单历史记录
				TMallOrderHistory orderHistory = orderHistoryService.selectOne(entity);
				//4.根据查询出的订单历史记录来设置实体对象的除订单序列号和id的其它属性
				entity.setUserid(orderHistory.getUserid());
				entity.setOrderid(orderHistory.getOrderid());
				entity.setCtime(date);
				entity.setLtime(date);
				entity.setStatus(4);
				//5.向表t_mall_order_history中插入一条该订单的新状态记录
				orderHistoryService.insert(entity);
			
			}
			//如果不为空，更新物流信息
			else{
				logisti.setLogisticsname(logisticsName);
				logisti.setLogisticsserial(logisticsSerial);
				logisti.setLogisticscompany(logisticscompany);
				logisti.setLtime(date); 
				logisticsService.updateByPrimaryKey(logisti);
			}
	 		msg = "保存发货信息成功";
			out.println("<script language='javascript'>");
			out.println("alert('"+msg+"')");
	
			out.println("location.href='/manage/order/orderManage'");
			out.println("</script>");
			return;
		} catch (Exception e) {
			msg = "保存发货信息失败";
			logger.error("order/logistics error >>> ", e);
			try {
				out = response.getWriter();
				out.println("<script language='javascript'>");
				out.println("alert('"+msg+"')");

				out.println("location.href='/manage/order/orderManage'");
				out.println("</script>");
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
			
	} 

 	
 	
 	/**
 	 * 该请求的功能是：查看订单详情
 	 * @param request
 	 * @param model
 	 * @return
 	 */
 	@RequestMapping(value="order/orderDetail")
	public String orderDetail(HttpServletRequest request,Model model){
 		 
 		//获取页面传过来的订单序列号
 		String orderSerial = request.getParameter("orderSerial");
 		TMallOrderAddress orderAddress = new TMallOrderAddress();//订单详情收货人信息
 		orderAddress.setOrderserial (orderSerial);
 		
 		//物流详情
 		TMallLogistics logistics=new TMallLogistics();
 		logistics.setOrderserial(orderSerial);
 		
 	     //所属订单商品详情 		
 		TMallOrder tMallOrder=new TMallOrder();	
 	    tMallOrder.setSerial(orderSerial);
 	    
 	    //发票抬头
 	    TMallReceipt receipt=new TMallReceipt();
 	    receipt.setOrderserial(orderSerial);
 	    
 	    //根据订单号找商品序列号
 		List<TMallOrderProduct> orderProductList=null;
 		List<TMallOrderProduct> tMallOrderProducts = Lists.newArrayList();
 		TMallOrderProduct entity=new TMallOrderProduct();
 		entity.setOrderserial(orderSerial);
 		
 		
	     TMallProduct tMallProduct=new TMallProduct(); 
	     
	     //创建订单号创建时间，付款时间
	     List<TMallOrderHistory> orderHistoryList=null;
	     TMallOrderHistory orderHistory=new TMallOrderHistory();
	     orderHistory.setOrderserial(orderSerial);
 			
 		
		try {
			orderAddress = orderAddressService.selectOne(orderAddress);		 	
		 	model.addAttribute("orderAddress", orderAddress);
		 	
		 	logistics=logisticsService.selectOne(logistics);
		 	model.addAttribute("logistics",logistics);
		 	
		 	tMallOrder=orderService.selectOne(tMallOrder);
		 	model.addAttribute("tMallOrder",tMallOrder);
		 	
		 	receipt=receiptService.selectOne(receipt);
		 	model.addAttribute("receipt",receipt);
		 	
		 	orderProductList=orderProductService.select(entity);

		 	for(TMallOrderProduct tMallOrderProduct : orderProductList) {
		 		  tMallProduct=productService.selectBySerial(tMallOrderProduct.getProductserial()); 
				  tMallOrderProduct.setProductImg(tMallProduct.getImgurl());
				  tMallOrderProduct.setProductColor(tMallProduct.getColor());
				  tMallOrderProducts.add(tMallOrderProduct);
		 	}
		 	model.addAttribute("orderproduct",tMallOrderProducts);
		  
		 	orderHistoryList=orderHistoryService.select(orderHistory);
		 	model.addAttribute("orderHistoryList",orderHistoryList);
		 	
			TMallUser tMallUser = userService.selectByPrimaryKey(tMallOrder.getUserid());
			tMallUser=userService.selectOne(tMallUser);
			model.addAttribute("tMallUser",tMallUser);
		 	 			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "background/bg_order/bg_order_detail";
 	}
}









