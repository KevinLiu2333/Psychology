package wsclient.test;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import wsclient.domain.OperationInfo;
import wsclient.domain.ParameterInfo;
import wsclient.domain.ServiceInfo;
import wsclient.util.ComponentBuilder;

public class WSClient {
	private static Logger	log	= Logger.getLogger(WSClient.class);

	public static void testWSDL4J() {
		int i = 0, j = 0;

		try {
			ComponentBuilder builder = new ComponentBuilder();
			ServiceInfo serviceInfo = new ServiceInfo();
			String wsdllocation = "http://jcjg.mlr.gov.cn/Module/DataInteractive/GisqStandard.asmx?WSDL";
			// String wsdllocation = "http://localhost:8080/nutz_dwz_hr/services/EchoService?wsdl";
			serviceInfo.setWsdllocation(wsdllocation);
			serviceInfo = builder.buildserviceinformation(serviceInfo);
			log.info("===targetnamespace===:" + serviceInfo.getTargetnamespace());
			Iterator iter = serviceInfo.getOperations();
			log.info("现在可以查看远端Web服务对象的有关情况了(对应本地Web服务类,ServiceInfo)");
			log.info(serviceInfo.getName() + "提供的操作有:");
			while (iter.hasNext()) {
				i++;
				OperationInfo oper = (OperationInfo) iter.next();
				log.info("");
				log.info("操作:" + i + " " + oper.getTargetMethodName());
				List inps = oper.getInparameters();
				List outps = oper.getOutparameters();
				if (inps.size() == 0) {
					log.info("此操作所需的输入参数为:");
					log.info("执行此操作不需要输入任何参数!");
				} else {
					log.info("此操作所需的输入参数为:");
					for (Iterator iterator1 = inps.iterator(); iterator1.hasNext();) {
						ParameterInfo element = (ParameterInfo) iterator1.next();
						log.info("参数名为:" + element.getName());
						log.info("参数类型为:" + element.getKind());
					}
				}
				if (outps.size() == 0) {
					log.info("执行此操作不返回任何参数!");
				} else {
					log.info("此操作的输出参数为:");
					for (Iterator iterator2 = outps.iterator(); iterator2.hasNext();) {
						ParameterInfo element = (ParameterInfo) iterator2.next();
						log.info("参数名:" + element.getName());
						log.info("类型为:" + element.getKind());
					}
				}
				log.info("");
			}
		}

		catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static void main(String[] args) {
		WSClient.testWSDL4J();
	}

}
