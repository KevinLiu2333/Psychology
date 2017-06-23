package com.klsw.website.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.klsw.common.mall.mapper.TMallOrderAddressMapper;
import com.klsw.common.mall.mapper.TMallOrderHistoryMapper;
import com.klsw.common.mall.mapper.TMallOrderMapper;
import com.klsw.common.mall.mapper.TMallOrderProductMapper;
import com.klsw.common.mall.model.TMallOrder;
import com.klsw.common.mall.model.TMallOrderAddress;
import com.klsw.common.mall.model.TMallOrderHistory;
import com.klsw.common.mall.model.TMallOrderProduct;
import com.klsw.common.mall.model.TMallProduct;
import com.klsw.common.mall.model.TMallReceipt;
import com.klsw.common.utils.RandomStringUtil;
import com.klsw.website.vo.OrderForm;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class OrderService extends _BaseService<TMallOrder> {
	
	@Autowired
	private TMallOrderAddressMapper orderAddressService;
	
	@Autowired
	private TMallOrderHistoryMapper orderHistoryService;
	
	@Autowired
	private TMallOrderProductMapper orderProductService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TMallOrderMapper orderMapper;
	
	@Autowired
	private ReceiptService receiptService;
	
	//事务注解，遇到runtimeException会回滚
	@Transactional(rollbackFor = Exception.class)
	public TMallOrder submitOrder(OrderForm orderForm) throws Exception {
		Date date = new Date();
		
		String hiddenGoods = orderForm.getHiddenGoods();
		//将隐藏商品字符串解析出来
		//1.分割
		String[] goodsArray = hiddenGoods.split("\\|");
		int totalCount = 0;
		double totalPrice = 0d;
		//生成订单序列号
		String orderSerial = RandomStringUtil.genSerial();
		
		// order product
		List<TMallOrderProduct> orderProductList = Lists.newArrayList();
		TMallOrderProduct orderProduct = null;
		//2.遍历
		for(String goodsInfo : goodsArray) {
			orderProduct = new TMallOrderProduct();
			//","分割
			String[] goodsInfoArray = goodsInfo.split(",");
			//获得商品序列号
			String goodsSerial = goodsInfoArray[0];
			//获得商品数目
			int goodsCount = Integer.parseInt(goodsInfoArray[1]);
			//商品总数更新
			totalCount += goodsCount;
			//更新订单
			TMallProduct product = productService.selectBySerial(goodsSerial);
			orderProduct.setCtime(date);
			orderProduct.setLtime(date);
			orderProduct.setOrderserial(orderSerial);
			orderProduct.setProductcount(goodsCount);
			orderProduct.setProductid(product.getId());
			orderProduct.setProductprice(product.getPrice());
			orderProduct.setProductserial(product.getSerial());
			orderProduct.setUserid(orderForm.getUserId());
			orderProduct.setProductname(product.getName());
			orderProductList.add(orderProduct);
			//总价更新
			totalPrice += product.getPrice() * goodsCount;
		}
		// order信息入库
		TMallOrder order = new TMallOrder();
		order.setCtime(date);
		order.setLtime(date);
		order.setSerial(orderSerial);
		order.setTotalcount(totalCount);
		order.setTotalprice(totalPrice);
		order.setUserid(orderForm.getUserId());
		
		super.insertSelective(order);
		
		// order product
		
		for(TMallOrderProduct op : orderProductList) {
			//为订单产品设置统一的订单id
			op.setOrderid(order.getId());
		}
		
		orderProductService.insertList(orderProductList);
		
		// order history
		TMallOrderHistory history = new TMallOrderHistory();
		history.setCtime(date);
		history.setLtime(date);
		history.setOrderid(order.getId());
		history.setOrderserial(orderSerial);
		history.setUserid(orderForm.getUserId());
		orderHistoryService.insertSelective(history);
		
		// order address
		TMallOrderAddress address = new TMallOrderAddress();
		address.setCtime(date);
	/*	address.setDefaultaddress(orderForm.getDefaultAdd());*/
		address.setElevator(orderForm.getElevator());
		address.setFloor(orderForm.getFloor());
		address.setLtime(date);
		address.setOrderid(order.getId());
		address.setOrderserial(order.getSerial());
		address.setUseraddress(orderForm.getUseraddress());
		address.setUserid(orderForm.getUserId());
		address.setUsername(orderForm.getUsername());
		address.setUserphone(orderForm.getUserphone());
		address.setAreaId(orderForm.getAreaid());
		address.setCityid(orderForm.getCityid());
		address.setProvinceid(orderForm.getProvinceid());
		address.setStreet(orderForm.getStreet());
		orderAddressService.insert(address);
		
		//receipt
		//获取发票抬头信息,并将发票信息入库
		TMallReceipt receipt = new TMallReceipt();
		receipt.setOrderserial(orderSerial);
		receipt.setCtime(date);
		receipt.setLtime(date);
		receipt.setHeader(orderForm.getHeader());
		receiptService.insertSelective(receipt);
		
		return order;
	}
	
	/**
	 * 更新订单为已支付
	 * 
	 * @param orderSerial
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int updateToPay(String orderSerial, Integer userId, Integer orderId) throws Exception {
		
		int result = 0;
		
		Date date = new Date();
		
		TMallOrder order = new TMallOrder();
		//设置状态为已支付
		order.setStatus(TMallOrder.ORDER_STATUS_PAY);
		//设置修改时间
		order.setLtime(date);
		
		Example example = new Example(TMallOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("id", orderId);
		criteria.andEqualTo("userid", userId);
		criteria.andEqualTo("serial", orderSerial);
		criteria.andEqualTo("status", TMallOrder.ORDER_STATUS_NOT_PAY);
		//选出上述属性的订单，为未支付状态
		result = super.updateByExampleSelective(order, example);
		
		//如果存在这个未支付的订单，记录订单历史状态，存入数据库
		if(result > 0) {
			TMallOrderHistory orderHistory = new TMallOrderHistory();
			orderHistory.setCtime(date);
			orderHistory.setLtime(date);
			orderHistory.setOrderid(orderId);
			orderHistory.setOrderserial(orderSerial);
			orderHistory.setStatus(TMallOrder.ORDER_STATUS_PAY);
			orderHistory.setUserid(userId);
			orderHistoryService.insert(orderHistory);
		}
		//返回更新条数
		return result;
	}
	
	public List<TMallOrder> selectByUserId(Integer userId) throws Exception {
		
		List<TMallOrder> resultList = Lists.newArrayList();
		
		List<Map<String, Object>> orderList = this.orderMapper.selectByUserId(userId);
		
		if(orderList != null && orderList.size() > 0) {
			TMallOrderProduct product = null;
			TMallOrder order = null;
			for(Map<String, Object> map : orderList) {
				order = new TMallOrder();
				order.setSerial((String)map.get("orderSerial"));
				int index = resultList.indexOf(order);
				if(index > -1) {
					order = resultList.get(index);
				} else {
					order.setCtime((Date) map.get("ctime"));
					order.setStatus((Integer) map.get("status"));
					order.setTotalprice((Double) map.get("totalPrice"));
					order.setReceiver((String) map.get("userName"));
					resultList.add(order);
				}
				if(order.getProductList() == null) order.setProductList(new ArrayList<TMallOrderProduct>());
				
				// 设置订单商品信息
				product = new TMallOrderProduct();
				product.setProductcount((Integer) map.get("productCount"));
				product.setProductname((String) map.get("name"));
				product.setProductColor((String) map.get("color"));
				product.setProductImg((String) map.get("imgUrl"));
				product.setProductserial((String) map.get("productSerial"));
				
				order.getProductList().add(product);
				
			}
		}
		
		return resultList;
	}
	
	public void closeOrderBySerial(String serial, Integer userId, Integer orderId) throws Exception {
		Example example = new Example(TMallOrder.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("serial", serial);
		criteria.andEqualTo("status", TMallOrder.ORDER_STATUS_NOT_PAY);
		criteria.andEqualTo("userid", userId);
		criteria.andEqualTo("id", orderId);
		
		TMallOrder entity = new TMallOrder();
		entity.setStatus(TMallOrder.ORDER_STATUS_CLOSED);
		
		super.updateByExample(entity, example);
		
	}
}
