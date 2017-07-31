package wsclient.test;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class TestService {

	/**
	 * @param args
	 * @throws ServiceException
	 * @throws MalformedURLException
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws ServiceException, MalformedURLException, RemoteException {
		String url = "http://jcjg.mlr.gov.cn/Module/DataInteractive/GisqStandard.asmx?wsdl";
		String namespace = "http://jcjg.mlr.gov.cn";
		String methodName = "GetUserXZQ";
		String soapActionURI = "http://jcjg.mlr.gov.cn/GetUserXZQ";

		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(url));
		call.setUseSOAPAction(true);
		call.setSOAPActionURI(soapActionURI);
		call.setOperationName(new QName(namespace, methodName));
		call.addParameter(new QName(namespace, "username"), XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter(new QName(namespace, "password"), XMLType.XSD_STRING, ParameterMode.IN);
		//call.addParameter(new QName(namespace, "requestXML"), XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnType(XMLType.XSD_STRING);
		
//		String[] str = new String[2];  
//		str[0] = "test010";  
//		str[1] = "";  
//		  
//		Object obj = call.invoke(str);  

		String rquestXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><GisqRequest>	<Action>31</Action>	<BusinessType>11</BusinessType>	<BusinessWhere>to_char(FB_SJ,\"yyyy-MM-dd\")=\"2012-09-27\"</BusinessWhere>	<DATA><PRI_TABLE_DATA tableName=\"T_GYJH\" queryFields=\"BIAOTI,XZQ_DM\"/>		<SUB_TABLE_DATA tableName=\"T_GYJH_YTMX\" queryFields=\"\"/>	</DATA></GisqRequest>";
		
		String ret = (String) call.invoke(new Object[] { "landmarket","landmarket@mlr" });
		 

		// org.apache.axis.description.OperationDesc oper;
		// org.apache.axis.description.ParameterDesc param;
		//
		// oper = new org.apache.axis.description.OperationDesc();
		// oper.setName("AnalyzeRequest2");
		// param = new org.apache.axis.description.ParameterDesc(
		// new javax.xml.namespace.QName("http://jcjg.mlr.gov.cn/",
		// "userName"),
		// org.apache.axis.description.ParameterDesc.IN,
		// new javax.xml.namespace.QName(
		// "http://www.w3.org/2001/XMLSchema", "string"),
		// java.lang.String.class, false, false);
		// param.setOmittable(true);
		// oper.addParameter(param);
		//
		// param = new org.apache.axis.description.ParameterDesc(
		// new javax.xml.namespace.QName("http://jcjg.mlr.gov.cn/",
		// "password"),
		// org.apache.axis.description.ParameterDesc.IN,
		// new javax.xml.namespace.QName(
		// "http://www.w3.org/2001/XMLSchema", "string"),
		// java.lang.String.class, false, false);
		// param.setOmittable(true);
		// oper.addParameter(param);
		//
		// param = new org.apache.axis.description.ParameterDesc(
		// new javax.xml.namespace.QName("http://jcjg.mlr.gov.cn/",
		// "requestXML"),
		// org.apache.axis.description.ParameterDesc.IN,
		// new javax.xml.namespace.QName(
		// "http://www.w3.org/2001/XMLSchema", "string"),
		// java.lang.String.class, false, false);
		// param.setOmittable(true);
		// oper.addParameter(param);
		//
		// oper.setReturnType(new javax.xml.namespace.QName(
		// "http://www.w3.org/2001/XMLSchema", "string"));
		// oper.setReturnClass(java.lang.String.class);
		// oper.setReturnQName(new javax.xml.namespace.QName(
		// "http://jcjg.mlr.gov.cn/", "AnalyzeRequest2Result"));
		//
		// oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
		// oper.setUse(org.apache.axis.constants.Use.LITERAL);
		//
		// call.setOperation(oper);
		// call.setOperationName(new javax.xml.namespace.QName("http://jcjg.mlr.gov.cn/", "AnalyzeRequest2"));

		 //String rquestXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><GisqRequest>	<Action>31</Action>	<BusinessType>11</BusinessType>	<BusinessWhere>to_char(FB_SJ,\"yyyy-MM-dd\")=\"2012-09-27\"</BusinessWhere>	<DATA><PRI_TABLE_DATA tableName=\"T_GYJH\" queryFields=\"BIAOTI,XZQ_DM\"/>		<SUB_TABLE_DATA tableName=\"T_GYJH_YTMX\" queryFields=\"\"/>	</DATA></GisqRequest>";
		 //String ret = (String) call.invoke(new Object[] { "landmarket","landmarket@mlr", rquestXml });
		 //String ret = (String) call.invoke(new Object[] { "landmarket","landmarket@mlr", rquestXml });

		//String ret = (String) call.invoke(new Object[] { "nihao gaodong" });
		System.out.println("·µ»Ø½á¹û---> " + ret);

	}

}