package com.wondersgroup.cmc.utils;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.collections.CollectionUtils;

public class WebServiceCall {
	@SuppressWarnings("unchecked")
	public <T> T callService(String endpointAddress,String operationName,List<WebServiceCallArgument> args,QName rettype) {
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();

			call.setTargetEndpointAddress(endpointAddress);// 远程调用路径
			call.setOperationName(operationName);// 调用的方法名
			List<Object> argList = new ArrayList<Object>();
			if (CollectionUtils.isNotEmpty(args)) {
				// 设置参数名:
				for (WebServiceCallArgument arg : args) {
					call.addParameter(arg.getArgName(), // 参数名
							arg.getArgType(),// 参数类型
							javax.xml.rpc.ParameterMode.IN);// 参数模式：'IN' or 'OUT'
					argList.add(arg.getArgValue());
				}
			}
			// 设置返回值类型：
			call.setReturnType(rettype);
			T resultstr = (T) call.invoke(argList.toArray());
			return resultstr;
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
