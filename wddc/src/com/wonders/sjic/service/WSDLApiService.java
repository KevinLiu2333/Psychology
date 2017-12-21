package com.wonders.sjic.service;

import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wonders.sjic.entity.InterfaceLogBo;
import com.wonders.sjic.entity.InterfaceTransferBo;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

@IocBean
public class WSDLApiService implements _BaseService {

	@Inject
	private Dao dao;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static Service service;
	private static Call call;

	static {
		service = new Service();
		try {
			call = (Call) service.createCall();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Ret execute(InterfaceInfoBo infoBo, Map<String, Object> params) {
		InterfaceLogBo logBo = new InterfaceLogBo();
		// 日志记录:接口id
		logBo.setJkId(infoBo.getId());
		// 日志记录:id
		logBo.setId(UUID.randomUUID().toString());
		// 日志记录:请求参数
		logBo.setReqMsg(Json.toJson(params));
		InterfaceTransferBo transferBo = dao.fetch(InterfaceTransferBo.class, infoBo.getId());
		if (transferBo == null) {
			transferBo = new InterfaceTransferBo();
			transferBo.setJkId(infoBo.getId());
			transferBo.setTransferFailed(0);
			transferBo.setTransferSuccess(0);
			transferBo.setTransferSum(0);
			dao.insert(transferBo);
		}
		transferBo.setTransferSum(transferBo.getTransferSum() + 1);
		final InterfaceTransferBo finalTransferBo = transferBo;
		URL url = null;
		String responseString = null;
		Date now = new Date();
		Ret ret = null;
		try {
			// 处理endpoint
			String endpoint = infoBo.getAddress();
			endpoint = endpoint.endsWith("?wsdl") || endpoint.endsWith("?WSDL")
					? endpoint.substring(0, endpoint.lastIndexOf("?")) : endpoint;
			url = new URL(endpoint);
			// 设置endpoint
			call.setTargetEndpointAddress(url);
			// 设置WSDL里面描述的接口名称(要调用的方法)
			call.setOperationName(new QName(infoBo.getSpaceName(), infoBo.getServiceName()));
			// 设置被调用方法的返回值类型
			call.setReturnType(XMLType.XSD_STRING);
			// 设置超时时间
			call.setTimeout(3000);
			List<Object> valueList = new ArrayList<Object>();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				call.addParameter(entry.getKey(), XMLType.XSD_STRING, ParameterMode.IN);
				valueList.add(entry.getValue());
			}
			Object[] valueArray = valueList.toArray();
			now = new Date();
			logBo.setStartTime(sdf.format(now));
			responseString = (String) call.invoke(valueArray);
			// 成功,返回成功结果
			logBo.setResMsg(responseString);
			finalTransferBo.setTransferSuccess(finalTransferBo.getTransferSuccess() + 1);
			ret = Ret.success(responseString);
		} catch (MalformedURLException e) {
			finalTransferBo.setTransferFailed(finalTransferBo.getTransferFailed() + 1);
			logBo.setErrMsg("接口地址不符合url规范");
			ret = Ret.error("接口地址不符合url规范");
		} catch (RemoteException e) {
			// 通讯错误
			finalTransferBo.setTransferFailed(finalTransferBo.getTransferFailed() + 1);
			logBo.setErrMsg(e.getMessage());
			logBo.setTransferTime(String.valueOf(System.currentTimeMillis() - now.getTime()));
			ret = Ret.error("通讯错误:" + e.getMessage());
		} finally {
			Trans.exec(new Atom() {
				public void run() {
					dao.update(finalTransferBo);
					dao.insert(logBo);
				}
			});
		}
		return ret;
	}
}
