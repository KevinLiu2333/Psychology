package com.wonders.ws.receive.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.log.entity.InterfaceReceiveLog;
import com.wonders.ws.receive.bean.PtMzjYiliaoJiuzhuBean;
import com.wonders.ws.receive.bean.PtMzjYiliaoRenyuanBean;

@IocBean
public class MzjYiliaoTask {

	@Inject
	private Dao	dao;

	public void extract() {
		
			// 人员信息
			getDeliverPerson();
			// 人员救助信息
			getDeliverProvide();
		
	}

	@SuppressWarnings("unchecked")
	public void getDeliverPerson() {
		String url = "http://10.208.50.19:7001/ptyl/services/PtylWebService?wsdl";
		// 命名空间
		String soapaction = "http://ws.service.webservices.yzsyl.wondersgroup.com";
		try {
			// 创建服务
			Service service = new Service();
			// 创建调用句柄
			Call call = (Call) service.createCall();
			// 设置请求地址
			call.setTargetEndpointAddress(url);
			// 设置xml参数
			String xml = "<yzsyl>" + "<fqsj>20161201</fqsj>" + "<xmmc>ptyl</xmmc>" + "<ipdz>ip</ipdz>" + "</yzsyl>";
			// 设置调用的方法和方法的命名空间；
			call.setOperationName(new QName(soapaction, "deliverPerson"));
			// 调用方法并传递参数
			String result = (String) call.invoke(new Object[] { xml });
			Document document;
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(date.format(new Date()));
			log.setInterfaceName("getDeliverPerson");
			log.setServiceName("获取医疗救助人员信息");
			log.setReceiveName("医疗救助“一站式”信息");
			log.setDeptName("民政局");
			log.setDeptId("DT67");
			log.setCallNum(1);
			log.setTableName("PT_MZJ_YILIAO_RENYUAN");
			int i = 0;
			document = DocumentHelper.parseText(result);
			// 获取文档根节点
			Element root = document.getRootElement();
			Iterator<Element> iterator1 = root.elementIterator();
			while (iterator1.hasNext()) {
				Element ele1 = iterator1.next();
				Iterator<Element> iterator2 = ele1.elementIterator();
				while (iterator2.hasNext()) {
					Element ele2 = iterator2.next();
					if (ele2.getTextTrim().equals("")) {
						PtMzjYiliaoRenyuanBean jz = new PtMzjYiliaoRenyuanBean();
						jz.setId(UUID.randomUUID().toString().replaceAll("-", ""));
						jz.setSfzh(ele2.elementText("sfzh"));
						jz.setXm(ele2.elementText("xm"));
						jz.setXb(ele2.elementText("xb"));
						jz.setZjlx(ele2.elementText("zjlx"));
						jz.setHjlx(ele2.elementText("hjlx"));
						jz.setJzdz(ele2.elementText("jzdz"));
						jz.setRylx(ele2.elementText("rylx"));
						jz.setHjdz(ele2.elementText("hjdz"));
						jz.setGzdd(ele2.elementText("gzdd"));
						jz.setMz(ele2.elementText("mz"));
						jz.setHyzt(ele2.elementText("hyzt"));
						jz.setJycd(ele2.elementText("jycd"));
						jz.setSsjszt(ele2.elementText("ssjszt"));
						jz.setGxrq(ele2.elementText("gxrq"));
						dao.insert(jz);
						i++;
					}
				}
			}
			log.setInputNum(i);
			log.setEndDate(date.format(new Date()));
			dao.insert(log);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void getDeliverProvide() {
		String url = "http://10.208.50.19:7001/ptyl/services/PtylWebService?wsdl";
		// 命名空间
		String soapaction = "http://ws.service.webservices.yzsyl.wondersgroup.com";
		try {
			// 创建服务
			Service service = new Service();
			// 创建调用句柄
			Call call = (Call) service.createCall();
			// 设置请求地址
			call.setTargetEndpointAddress(url);
			// 设置xml参数
			String xml = "<yzsyl>" + "<fqsj>20161201</fqsj>" + "<xmmc>ptyl</xmmc>" + "<ipdz>ip</ipdz>" + "</yzsyl>";
			// 设置调用的方法和方法的命名空间；
			call.setOperationName(new QName(soapaction, "deliverProvide"));
			// 调用方法并传递参数
			String result = (String) call.invoke(new Object[] { xml });
			System.out.println(result);
			Document document;
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
			InterfaceReceiveLog log = new InterfaceReceiveLog();
			log.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			log.setInputDate(date.format(new Date()));
			log.setInterfaceName("getDeliverProvide");
			log.setServiceName("获取医疗救助人员救助信息");
			log.setReceiveName("医疗救助“一站式”信息");
			log.setDeptName("民政局");
			log.setDeptId("DT67");
			log.setCallNum(1);
			log.setTableName("PT_MZJ_YILIAO_JIUZHU");
			document = DocumentHelper.parseText(result);
			// 获取文档根节点
			int i = 0;
			Element root = document.getRootElement();
			Iterator<Element> iterator1 = root.elementIterator();
			while (iterator1.hasNext()) {
				Element ele1 = iterator1.next();
				Iterator<Element> iterator2 = ele1.elementIterator();
				while (iterator2.hasNext()) {
					Element ele2 = iterator2.next();
					if (ele2.getTextTrim().equals("")) {
						PtMzjYiliaoJiuzhuBean jz = new PtMzjYiliaoJiuzhuBean();
						jz.setId(UUID.randomUUID().toString().replaceAll("-", ""));
						jz.setJzje(ele2.elementText("jzje"));
						jz.setMzjz(ele2.elementText("mzjz"));
						jz.setSfzh(ele2.elementText("sfzh"));
						jz.setXm(ele2.elementText("xm"));
						jz.setZjmzsj(ele2.elementText("zjmzsj"));
						jz.setZjzysj(ele2.elementText("zjzysj"));
						jz.setZyjz(ele2.elementText("zyjz"));
						dao.insert(jz);
						i++;
					}
				}
			}
			log.setInputNum(i);
			log.setEndDate(date.format(new Date()));
			dao.insert(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
