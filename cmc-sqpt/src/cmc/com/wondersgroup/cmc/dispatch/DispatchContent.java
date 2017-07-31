package com.wondersgroup.cmc.dispatch;

public class DispatchContent {
	/**
	 * 接口大类
	 */
	public static final String IFTYPE_IF = "01";	//条线接口
	public static final String IFTYPE_APP = "02";	//应用
	public static final String IFTYPE_TEL = "03";	//开放端口
	public static final String IFTYPE_SUBSYS = "04";//下级平台
	
	/**
	 * 接口小类
	 */
	public static final String IFSUBTYPE_WSDL = "01";	//WSDL
	public static final String IFSUBTYPE_REST = "02";	//REST
	public static final String IFSUBTYPE_APP = "03";	//应用
	public static final String IFSUBTYPE_TEL = "04";	//开放端口
	
	/**
	 * 有效标志
	 */
	public static final String VALID = "1";		//有效
	public static final String UNVALID = "0";	//无效
	
	/**
	 * 监控状态
	 */
	public static final String LSMONSTATUS_200 = "001";	//正常
	public static final String LSMONSTATUS_404 = "002";	//无法联通
	public static final String LSMONSTATUS_500 = "003";	//接口/应用有误
	
	/**
	 * 超时时间
	 */
	public static final int DEFALUT_CONNET_TO = 5000; //默认连接超时时间
	public static final int DEFALUT_READ_TO = 5000; //默认读取超时时间
}
