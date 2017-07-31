package com.wondersgroup.cmc.dispatch.message.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sheca.safeengine.javasafeengine;
import com.wondersgroup.cmc.codec.HsmDecryptor;
import com.wondersgroup.cmc.codec.HsmEncryptor;
import com.wondersgroup.cmc.dispatch.DispatchContent;
import com.wondersgroup.cmc.dispatch.message.service.MessageRouterService;
import com.wondersgroup.cmc.dispatch.message.service.strategy.WSStrategy;
import com.wondersgroup.cmc.dispatch.model.bo.Ifdefine;
import com.wondersgroup.cmc.dispatch.model.bo.Iftransdetail;
import com.wondersgroup.cmc.dispatch.model.dto.HttpClientDTO;
import com.wondersgroup.cmc.dispatch.model.dto.WebServiceDataSource;
import com.wondersgroup.cmc.utils.Base64Utils;
import com.wondersgroup.cmc.utils.MessageUtils;
import com.wondersgroup.cmc.utils.StringUtils;
import com.wondersgroup.cmc.xml.MsgHeader;
import com.wondersgroup.cmc.xml.OrganDesc;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.util.StringTools;

public class SQY_MessageRouterServiceImpl implements MessageRouterService{
	private static final String DefalutXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg><head><version>1.0</version><ref>-1</ref><code>ANY</code><src><code>ANY</code><uid>ANY</uid><uname>ANY</uname><uexpinf></uexpinf></src><dest><code>CI</code><uid>10001</uid><uname>admin</uname><uexpinf></uexpinf></dest><time>%s</time><ext></ext><sign></sign><rst><syscode>00</syscode><buscode>DFFFFF</buscode><errmsg>%s</errmsg></rst><affairno></affairno></head><body></body></msg>";
	private static final byte DefalutPlain = (byte) 0x00;
	
	private static Log logger = LogFactory.getLog(SQY_MessageRouterServiceImpl.class);
	private MessageRouterServiceImpl messageRouterServiceImpl;
	private WSStrategy wsstrategy;
	
	public void setMessageRouterServiceImpl(MessageRouterServiceImpl messageRouterServiceImpl) {
		this.messageRouterServiceImpl = messageRouterServiceImpl;
	}

	public void setWsstrategy(WSStrategy wsstrategy) {
		this.wsstrategy = wsstrategy;
	}

	@Override
	public Object route(String wsName, String methodName, Object[] params) {
		this.log(null, "调用");
		//1.校验入参
		byte[] pererror = null;

		pererror = this.check(wsName, methodName, params);
		if(pererror != null){
			return pererror;
		}
		//根据params初始化flag(加密标志)和报文
		byte[] xmlBytes = (byte[]) params[1];
		Object flaghex = params[0];
		
		//2.判断WSNAME是否存在、接口定义是否正确
		Ifdefine ifdefine = null;
		try {
			ifdefine = messageRouterServiceImpl.getIfdefine(wsName, DispatchContent.IFTYPE_IF, DispatchContent.VALID);
		} catch (Exception e) {
			e.printStackTrace();
			return this.createDefalutFailPlainRspHex("CI:接口代码["+wsName+"]配置多条有效数据,请联系管理员确认配置情况。");
		}
		if(ifdefine == null){
			return this.createDefalutFailPlainRspHex("CI:接口代码["+wsName+"]未配置,请联系管理员配置。");
		}
		
		WebServiceDataSource wsDs = new WebServiceDataSource();
		try {
			wsDs = messageRouterServiceImpl.transWebServiceDataSource(ifdefine);
		} catch (BusinessException e) {
			return this.createDefalutFailPlainRspHex(e.getMessage());
		} catch (Exception e) {
			return this.createDefalutFailPlainRspHex("CI:未知错误["+e.getMessage()+"]。");
		}
		
		//3.根据第一个参数判断，0为非加密,1为加密
		Boolean flag = true;
		if(flaghex instanceof String){
			if("0".equals(flaghex)){
				flag = false;
			}
		} else if (flaghex instanceof Byte){
			if((Byte) flaghex ==  DefalutPlain){
				flag = false;
			}
		}
		
		//4.初始化交易明细
		Iftransdetail iftransdetail = new Iftransdetail();
		iftransdetail.setIfdefineid(ifdefine.getIfdefineid());	//接口定义配置ID
		iftransdetail.setReqflag(flag?"1":"0");					//发送加密标志
		iftransdetail.setReqenxml(xmlBytes==null?null:Base64Utils.encodeString(xmlBytes));	//发送加密报文
		iftransdetail.setTransfunc(methodName);					//交易方法
		MsgHeader reqmsgHeader = null;
		//5.如果是非加密的报文，解析报文 ，赋值交易明细
		if(!flag){
			String xml = null;
			try {
				xml = new String(xmlBytes,"UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				//根据报文创建MsgHeader、赋值iftransdetail
				reqmsgHeader = this.doCreateReqHeader(xml, iftransdetail, methodName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//判断是否要走加密机
			try {
				if("true".equals(wsDs.getFlag()) && StringTools.hasText(wsDs.getPath())){
					this.log(null, "加密开始");
					this.doEncry(params, wsDs, xml, flaghex, iftransdetail);
					this.log(iftransdetail.getTransno(), "加密结束");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		Object object = null;
		String errormsg = null;
		Date reqtime = new Date();
		iftransdetail.setReqtime(reqtime);
		
		//6.校验连通性
		this.log(iftransdetail.getTransno(), "联通性测试开始");
		HttpClientDTO httpClientDTO =messageRouterServiceImpl.checkWS(ifdefine);
		this.log(iftransdetail.getTransno(), "联通性测试结束,状态["+httpClientDTO.getCode()+"],描述["+httpClientDTO.getDetailmsg()+"]");
		if(!DispatchContent.LSMONSTATUS_200.equals(httpClientDTO.getCode())){
			errormsg = "CI:["+wsDs.getWsdesc()+"]服务无法访问，详细信息["+httpClientDTO.getDetailmsg()+"]";
		}
		
		//7.如果联通调用webservice
		Boolean sucflag = StringTools.isEmpty(errormsg);
		if(sucflag) {
			this.log(iftransdetail.getTransno(), "接口调用开始");
			try {
				object = wsstrategy.execute(wsDs, methodName, params);
			} catch (BusinessException e) {
				e.printStackTrace();
				errormsg = e.getMessage();
			} catch (Exception e) {
				e.printStackTrace();
				errormsg = "CI:未知错误["+e.getMessage()+"]。";
			}
			this.log(iftransdetail.getTransno(), "接口调用结束");
			
			Date rsptime = new Date();
			iftransdetail.setRsptime(rsptime);
			iftransdetail.setTranstime(new BigDecimal(rsptime.getTime() -reqtime.getTime()).setScale(2).divide(new BigDecimal(1000l), BigDecimal.ROUND_HALF_EVEN));
			
			//8.解析object
			sucflag = StringTools.isEmpty(errormsg);
			if(sucflag){
				if(object != null){
					try {
						//根据第一个字节解析出flag（加密标志）,后续部分解析成报文
						byte[] respBytes = (byte[]) object;
						byte respflag = respBytes[0];
						iftransdetail.setRspflag(respflag == (byte)0x01?"1":"0");	//接收加密标志
						byte[] respMsgBytes = new byte[respBytes.length - 1];
						System.arraycopy(respBytes, 1, respMsgBytes, 0, respMsgBytes.length);
						iftransdetail.setRspenxml(Base64Utils.encodeString(respMsgBytes));	//接收加密报文
						
						if(!flag || respflag == (byte)0x00){
							String respXml = null;
							try {
								//解密
								this.log(iftransdetail.getTransno(), "解密开始");
								if(respflag == (byte)0x01){
									try {
										HsmDecryptor hsmDecryptor = new HsmDecryptor();
										respXml = hsmDecryptor.doDecry((byte)0x01, null, respMsgBytes);
									} catch (Exception e) {
										e.printStackTrace();
										errormsg = "CI:返回报文解密失败，错误信息["+e.getMessage()+"]。";
									}
								} else {
									try {
										respXml = new String(respMsgBytes,"UTF-8");
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
								this.log(iftransdetail.getTransno(), "解密结束");
								//赋值返回报文
								iftransdetail.setRspxml(respXml);
								//根据返回报文创建MsgHeader、赋值iftransdetail、生成并生成正式返回报文
								this.doCreateRspHeader(respXml, object, iftransdetail);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			Date rsptime = new Date();
			iftransdetail.setRsptime(rsptime);
			iftransdetail.setTranstime(new BigDecimal(rsptime.getTime() -reqtime.getTime()).setScale(2).divide(new BigDecimal(1000l), BigDecimal.ROUND_HALF_EVEN));
		}
		//9.处理联通性、调用时、解密时错误
		if(StringTools.hasText(errormsg)){
			iftransdetail.setTransflag1("00");
			iftransdetail.setTransflag2("DFFFFF");
			iftransdetail.setErrmsg(errormsg);
			if (reqmsgHeader != null){
				reqmsgHeader.setSysErrCode(iftransdetail.getTransflag1());
				reqmsgHeader.setBusErrCode(iftransdetail.getTransflag2());
				reqmsgHeader.setErrMsg(iftransdetail.getErrmsg());
				object = this.createPlainRspHex(MessageUtils.toXml(reqmsgHeader, null));
			} else {
				object = this.createDefalutFailPlainRspHex(errormsg);
			}
		}
		
		//10.保存交易更新汇总
		//保存交易明细
		CommonHibernateDaoUtils.save(iftransdetail);
		//更新交易汇总
		messageRouterServiceImpl.updateTranssum(sucflag, ifdefine.getIfdefineid());
		this.log(iftransdetail.getTransno(), "调用结束");
		return object;
	}
	
	/**
	 * 校验入参，根据params初始化flag(加密标志)和报文
	 * @param wsName
	 * @param methodName
	 * @param params
	 * @param xmlBytes
	 * @param flaghex
	 * @return
	 */
	private byte[] check (String wsName,String methodName,Object[] params){
		//1.判断入参是否为空
		if(StringTools.isEmpty(wsName)){
			return this.createDefalutFailPlainRspHex("CI:请求参数[wsName]为空,请检查REST调用。");
		}
		if(StringTools.isEmpty(methodName)){
			return this.createDefalutFailPlainRspHex("CI:请求参数[methodName]为空,请检查REST调用。");
		}
		if(params == null){
			return this.createDefalutFailPlainRspHex("CI:请求参数[params]为空,请检查REST调用。");
		}
		
		if(params.length != 2){
			return this.createDefalutFailPlainRspHex("CI:请求参数[params]数组长度不为2,请检查REST调用。");
		}
		
		byte[] xmlBytes = null;
		Object flaghex = params[0];
		if(flaghex == null){
			return this.createDefalutFailPlainRspHex("CI:请求加密标志为空,请检查REST调用。");
		}
		
		try {
			xmlBytes = (byte[]) params[1];
			if(xmlBytes == null){
				return this.createDefalutFailPlainRspHex("CI:请求参数中报文为空,请检查REST调用。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.createDefalutFailPlainRspHex("CI:请求参数中报文不为byte[],请检查REST调用。");
		}
		return null;
	}
	
	/**
	 * 生成错误报文
	 * @param msg
	 * @return
	 */
	private String createDefalutFail(String msg) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return String.format(DefalutXml,format.format(new Date()),msg);
	}
	
	/**
	 * 生成明文组装生成返回byte[]
	 * @param xml
	 * @return
	 */
	public byte[] createPlainRspHex(String xml) {
		return this.wrap((byte)DefalutPlain, StringUtils.createBytes(xml));
	}
	
	/**
	 * 生成明文组装生成返回byte[]
	 * @param msg
	 * @return
	 */
	public byte[] createDefalutFailPlainRspHex(String msg) {
		return this.wrap((byte)DefalutPlain, StringUtils.createBytes(this.createDefalutFail(msg)));
	}
	
	/**
	 * 拼接byte
	 * @param flag
	 * @param respBytes
	 * @return
	 */
	private byte[] wrap(byte flag, byte[] respBytes) {
	    ByteBuffer buffer = ByteBuffer.allocate(respBytes.length + 1);
	    buffer.put(flag);
	    buffer.put(respBytes);
	    return buffer.array();
	}
	
	/**
	 * 根据发起报文创建MsgHeader、赋值iftransdetail
	 * @param xml
	 * @param iftransdetail
	 * @param methodName
	 * @return 
	 */
	private MsgHeader doCreateReqHeader(String xml,Iftransdetail iftransdetail,String methodName){
		HashMap<String, Serializable> map = MessageUtils.toMap(xml);
		MsgHeader msgHeader = (MsgHeader) map.get("_");
		if(msgHeader!=null){
			iftransdetail.setTransno(msgHeader.getRef());	//交易标识
			iftransdetail.setTranssubfunc(msgHeader.getCode());	//交易明细方法
			OrganDesc src = msgHeader.getSrc();
			if(src != null){
				iftransdetail.setReqcode(src.getCode());	//发起方代码
				iftransdetail.setRequsrid(src.getUid());	//发起方用户
				iftransdetail.setRequsrname(src.getUname());//发起方用户名
			}
			OrganDesc dest = msgHeader.getDest();
			if(dest != null){
				iftransdetail.setRspcode(dest.getCode());	//接收方代码
				iftransdetail.setRspusrid(dest.getUid());	//接收方用户
				iftransdetail.setRspusrname(dest.getUname());	//接收方用户名
			}
			iftransdetail.setReqxml(xml);			//发送报文
		}
		return msgHeader;
	}
	
	/**
	 * 加密
	 * @param params
	 * @param webServiceDataSource
	 * @param xml
	 * @param flaghex
	 * @param iftransdetail
	 */
	private void doEncry(Object[] params,WebServiceDataSource webServiceDataSource,String xml,Object flaghex,Iftransdetail iftransdetail){
		//获取密钥
		String pubKey = "";
		FileInputStream inputStream = null;
		try {
			File file = new File((String)webServiceDataSource.getPath());
			inputStream = new FileInputStream(file);
			byte[] b = new byte[inputStream.available()];
			inputStream.read(b);
			javasafeengine jse = new javasafeengine();
			pubKey = jse.getCertDetail(20, b);
		} catch(Exception e){
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info("CI:获取["+webServiceDataSource.getWsdesc()+"]密钥失败。");
			}
		} finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//加密
		if(StringTools.hasText(pubKey)){
			HsmEncryptor hsmEncryptor = new HsmEncryptor();
			byte[] encrybyte = null;
			try {
				encrybyte = hsmEncryptor.doEncry((byte)0x01, pubKey, xml);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (encrybyte == null) {
				if (logger.isInfoEnabled()) {
					logger.info("CI:["+webServiceDataSource.getWsdesc()+"]加密发送报文失败，转为明文......");
				}
			} else {
				params[1] = encrybyte;
				if(flaghex instanceof String){
					params[0] = String.format("%X", new Object[] { (byte)0x01});
				} else {
					params[0] = (byte)0x01;
				}
				iftransdetail.setReqflag("1");
				iftransdetail.setReqenxml(Base64Utils.encodeString(encrybyte));
			}
		}
	}
	
	
	/**
	 * 根据返回报文创建MsgHeader、赋值iftransdetail、生成并生成正式返回报文
	 * @param respXml
	 * @param object
	 * @param iftransdetail
	 */
	private void doCreateRspHeader(String respXml,Object object,Iftransdetail iftransdetail){
		object = this.wrap((byte)0x00, StringUtils.createBytes(respXml));
		HashMap<String, Serializable> map = MessageUtils.toMap(respXml);
		MsgHeader msgHeader = (MsgHeader) map.get("_");
		if(msgHeader!=null){
			iftransdetail.setTransflag1(msgHeader.getSysErrCode());
			iftransdetail.setTransflag2(msgHeader.getBusErrCode());
			iftransdetail.setErrmsg(msgHeader.getErrMsg());
		}
	}
	
	/**
	 * 记录日志
	 */
	private void log(String trsnsno,String msg){
		if (logger.isDebugEnabled()) {
			Date date = new Date();
			logger.debug(String.format("CI:refid[%S]%S[%tF %tT]",trsnsno,msg,date,date));
		}
	}
}
