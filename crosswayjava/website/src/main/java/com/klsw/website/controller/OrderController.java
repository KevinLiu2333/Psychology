package com.klsw.website.controller;


import com.alipay.util.AlipaySubmit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.klsw.common.mall.model.*;
import com.klsw.website.service.*;
import com.klsw.website.service.background.LogisticsService;
import com.klsw.website.vo.OrderForm;
import com.klsw.website.vo.ShoppingCart;
import com.klsw.website.vo.ShoppingCartList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@Controller
@RequestMapping("/order")
public class OrderController extends _BaseController {


    @Autowired
    private AppointService appointService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private LogisticsService logisticsService;


    @Autowired
    private TMallOrderAddressService orderAddressService;


    @Autowired
    private TMallOrderProductService orderProductService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    static final int PAGESIZE = 2;

    //确认订单信息
    @RequestMapping(value = "confirm")
    //页面传递的参数以键值对的形式存入params中
    public ModelAndView info(HttpServletRequest request, @RequestParam Map<String, String> params) throws Exception {

        //请求来源参数
        String from = params.get("from");
        //如果请求来源参数为空
        if (StringUtils.isBlank(from)) {
            //默认为cart
            from = "cart";
        }
        //
        ShoppingCartList cartList = new ShoppingCartList();

        Map<String, Integer> productsMap = Maps.newHashMap();
        //如果是从购物车处跳转过来
        if ("cart".equals(from)) {
            productsMap = getShoppingCart(request);
        } else if ("pro".equals(from)) {
            //如果是从商品页面跳转过来
            Integer count = Integer.parseInt(params.get("count"));
            String serial = params.get("serial");
            //存入购买信息
            productsMap.put(serial, count);
        }
        //得到了信息
        if (productsMap.size() > 0) {
            //新建序列号list
            List<String> serialList = Lists.newArrayList();

            StringBuilder hiddenGoods = new StringBuilder();
            //遍历其中商品序列号，存入list
            for (String key : productsMap.keySet()) {
                serialList.add(key);
                //编辑String--hiddenGoods
                hiddenGoods.append(key).append(",").append(productsMap.get(key)).append("|");
            }
            //返回商品list
            List<TMallProduct> productList = productService.selectBySerialList(serialList);
            //如果存在
            if (productList != null && productList.size() > 0) {
                //每个商品存入shoppingcart中
                ShoppingCart shoppingCart = null;
                double totalM = 0d;
                List<ShoppingCart> shoppingCartList = Lists.newArrayList();
                //遍历,存入shoppingcartlist中
                for (TMallProduct product : productList) {
                    shoppingCart = new ShoppingCart();
                    shoppingCart.setProductColor(product.getColor());
                    shoppingCart.setProductImgurl(product.getImgurl());
                    shoppingCart.setProductName(product.getName());
                    shoppingCart.setProductPrice(product.getPrice());
                    shoppingCart.setProductSerial(product.getSerial());
                    shoppingCart.setProductCount(productsMap.get(shoppingCart.getProductSerial()));
                    Double total = shoppingCart.getProductCount() * shoppingCart.getProductPrice();
                    shoppingCart.setTotal(total);
                    totalM += total;
                    shoppingCartList.add(shoppingCart);
                }
                //设置shoppingcartList总数量与list
                cartList.setTotalM(totalM);
                cartList.setCart(shoppingCartList);

                ModelMap model = new ModelMap();

                model.addAttribute("cart", cartList);
                model.addAttribute("hiddenGoods", hiddenGoods);
                model.addAttribute("from", from);
                return new ModelAndView("mall/orderConfirm", model);

            }
        }

        return new ModelAndView("redirect:/cart");

    }

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public ModelAndView submit(HttpServletRequest request, HttpServletResponse response, OrderForm orderForm) {

        //收到的参数根据参数名存入orderForm中
        try {
            //获取session中存储的userId
            Integer userId = getUserId(request);

            //设置orderForm的userId
            orderForm.setUserId(userId);

            /**
             * 订单信息入库，涉及
             * 1.TMallOrder
             * 2.TMallOrderHistory
             * 3.TMallOrderProduct
             * 4.TMallOrderAddress四张表
             * submitOrder的返回值是TMallOrder，在
             * 方法中完成了对四张表的操作
             * 具体方法参考submitOrder
             */
            TMallOrder order = orderService.submitOrder(orderForm);

            String html = AlipaySubmit.buildGoodsPayRequest(order);

            if ("cart".equals(orderForm.getFrom())) {
                clearCart(response);
            }
            return new ModelAndView("common/blank", "html", html);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/cart");
        }
    }

    @RequestMapping(value = "pay/{orderSerial}")
    public ModelAndView pay(HttpServletRequest request, @PathVariable("orderSerial") String orderSerial) {

        try {

            Integer userId = getUserId(request);
            TMallOrder entity = new TMallOrder();
            entity.setUserid(userId);
            entity.setSerial(orderSerial);
            entity.setStatus(TMallOrder.ORDER_STATUS_NOT_PAY);

            TMallOrder order = orderService.selectOne(entity);

            String html = "";

            if (order != null) {
                html = AlipaySubmit.buildGoodsPayRequest(order);
                return new ModelAndView("common/blank", "html", html);
            }

            return new ModelAndView("redirect:/cart");

        } catch (Exception e) {
            return new ModelAndView("redirect:/cart");
        }
    }

    @RequestMapping(value = "list")
    public ModelAndView list(HttpServletRequest request) {

        ModelMap model = new ModelMap();
        model.addAttribute("index", 0);

        String status = request.getParameter("status");
        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            PageHelper.startPage(1, PAGESIZE);
        } else {
            PageHelper.startPage(Integer.parseInt(pageNum), PAGESIZE);

        }
        List<TMallOrder> orderList = Lists.newArrayList();
        try {
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
            return new ModelAndView("mall/myorder", model);

        } catch (Exception e) {
            logger.error("order list error >>> ", e);
            return new ModelAndView("redirect:/cart");
        }
    }


    @RequestMapping(value = "toAppoint")
    public ModelAndView toAppoint(HttpServletRequest request, HttpSession session) {
        try {
            String serial = request.getParameter("serial");
            String count = request.getParameter("count");
            session.setAttribute("serial", serial);
            session.setAttribute("count", count);
        } catch (Exception e) {
            logger.error("order toAppoint error >>> ", e);
            return new ModelAndView("mall/appointment");
        }
        return new ModelAndView("mall/appointment");


    }

/*	@RequestMapping(value = "findLogistics/{orderSerial}")
    public ModelAndView findLogistics(HttpServletRequest request, @PathVariable("orderSerial") String orderSerial) {
		ModelMap model = new ModelMap();
		
		try {
			Integer userId = getUserId(request);
			List<TMallOrder> orderList = orderService.selectByUserId(userId);
			model.addAttribute("orderList", orderList);
			
			TMallLogistics entity = new TMallLogistics();
			entity.setOrderserial(orderSerial);
			TMallLogistics logistics = logisticsService.selectOne(entity);
			model.addAttribute("logistics", logistics);
			
			return new ModelAndView("mall/myorder", model);
			
			
		}catch(Exception e) {
			logger.error("findLogistics error >>> ", e);
			return new ModelAndView("redirect:/cart");
		}
	}*/

    @RequestMapping(value = "appoint", method = RequestMethod.POST)
    public ModelAndView appoint(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String msg = null;
        try {
            String serial = (String) session.getAttribute("serial");
            String count = (String) session.getAttribute("count");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String telephone = request.getParameter("telephone");
            if (StringUtils.isBlank(name)) {
                msg = "姓名不能为空";
                return new ModelAndView("mall/appointment", "msg", msg);
            }
            if (StringUtils.isBlank(email)) {
                msg = "邮箱不能为空";
                return new ModelAndView("mall/appointment", "msg", msg);
            }
            if (StringUtils.isBlank(address)) {
                msg = "邮箱地址不能为空";
                return new ModelAndView("mall/appointment", "msg", msg);
            }
            if (StringUtils.isBlank(telephone)) {
                msg = "手机号不能为空";
                return new ModelAndView("mall/appointment", "msg", msg);
            }
            TMallAppoint entity = new TMallAppoint();
            entity.setName(name);
            entity.setAddress(address);
            entity.setCtime(new Date());
            entity.setEmail(email);
            entity.setLtime(new Date());
            entity.setProductcount(count);
            entity.setProductserial(serial);
            entity.setTelephone(telephone);
            appointService.insert(entity);
            msg = "保存预约信息成功";
        } catch (Exception e) {
            msg = "保存预约信息失败";
            logger.error("order appoint error >>> ", e);
            return new ModelAndView("mall/appointment", "msg", msg);
        }
        return new ModelAndView("mall/appointConfirm", "msg", msg);
    }

    /***
     * 订单详情
     */
    @RequestMapping(value = "orderDetail")
    public String orderDetail(HttpServletRequest request, Model model) {

        //获取页面传过来的订单序列号
        String orderSerial = request.getParameter("orderSerial");
        TMallOrderAddress orderAddress = new TMallOrderAddress();//订单详情收货人信息
        orderAddress.setOrderserial(orderSerial);

        //所属订单商品详情
        TMallOrder tMallOrder = new TMallOrder();
        tMallOrder.setSerial(orderSerial);

        //根据订单号找商品序列号
        TMallProduct tMallProduct = new TMallProduct();
        List<TMallOrderProduct> orderProductList = null;
        List<TMallOrderProduct> tMallOrderProducts = Lists.newArrayList();
        TMallOrderProduct entity = new TMallOrderProduct();
        entity.setOrderserial(orderSerial);

        try {

            //查询订单状态
            tMallOrder = orderService.selectOne(tMallOrder);
            model.addAttribute("tMallOrder", tMallOrder);

            //查询发货信息
            TMallLogistics logistics = new TMallLogistics();
            logistics.setOrderserial(orderSerial);
            logistics = logisticsService.selectOne(logistics);
            model.addAttribute("logistics", logistics);

            //查询收获信息
            orderAddress = orderAddressService.selectOne(orderAddress);
            model.addAttribute("orderAddress", orderAddress);


            orderProductList = orderProductService.select(entity);

            for (TMallOrderProduct tMallOrderProduct : orderProductList) {
                tMallProduct = productService.selectBySerial(tMallOrderProduct.getProductserial());
                tMallOrderProduct.setProductImg(tMallProduct.getImgurl());
                tMallOrderProduct.setProductColor(tMallProduct.getColor());
                tMallOrderProducts.add(tMallOrderProduct);
            }
            model.addAttribute("orderproducts", tMallOrderProducts);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "mall/orderDetail";
    }

    /***
     * 确认收货
     * @param request 请求
     * @return 模板
     */
    @RequestMapping(value = "receive")
    @Transactional
    public ModelAndView receive(HttpServletRequest request) {

        Date date = new Date();//获取系统时间
        ModelMap model = new ModelMap();
        TMallOrder order = new TMallOrder();
        TMallOrder tMallOrder = new TMallOrder();
        TMallOrderHistory entity = new TMallOrderHistory();
        TMallOrderHistory orderHistory = new TMallOrderHistory();

        //获取页面穿过来的订单序列号
        String orderSerial = request.getParameter("orderSerial");

        try {
            //查询订单历史中状态为1的记录
            entity.setOrderserial(orderSerial);
            entity.setStatus(1);
            entity = orderHistoryService.selectOne(entity);

            //设置orderHistory的属性
            orderHistory.setOrderid(entity.getOrderid());
            orderHistory.setOrderserial(orderSerial);
            orderHistory.setUserid(entity.getUserid());
            orderHistory.setStatus(5);
            orderHistory.setCtime(date);
            orderHistory.setLtime(date);

            //查询订单历史中有没有订单状态为5的记录
            entity = new TMallOrderHistory();
            entity.setOrderserial(orderSerial);
            entity.setStatus(5);
            entity = orderHistoryService.selectOne(entity);

            if (entity == null) {//当没有订单为5的历史记录时插入一条
                orderHistoryService.insert(orderHistory);
            }

            //查询订单表中该订单序列号对应的订单记录
            order.setSerial(orderSerial);
            order = orderService.selectOne(order);

            tMallOrder = order;
            tMallOrder.setStatus(5);
            tMallOrder.setCtime(date);
            tMallOrder.setLtime(date);
            orderService.updateByPrimaryKey(tMallOrder);

            //获取用户id
            Integer userId = getUserId(request);
            //根据用户id查询对应的订单记录
            List<TMallOrder> orderList = orderService.selectByUserId(userId);
            //绑定查出的订单记录到页面中
            model.addAttribute("orderList", orderList);

            return new ModelAndView("mall/myorder", model);

        } catch (Exception e) {
            logger.error("order receive error >>> ", e);
            return new ModelAndView("redirect:/cart");
        }

    }

}
