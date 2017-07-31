/**
 * 
 */
package com.wonders.gis.db.conn;

import com.esri.sde.sdk.client.SeConnection;
import com.esri.sde.sdk.client.SeException;
import com.wonders.gis.utils.XMLReader;

/**
 * @author zhangchuanguo
 *松江数据中心项目sde数据库连接
 */
public class SDEDBConnectionManager {
	private static  String serverString="";
	private static  String dataBaseString="";
	private static  int instance=5151;
	private static  String userNameString="";
	private static  String passWordString="";
	private static SeConnection  seConnection=null;
	
	static {
		XMLReader xmlReader=null;
		
		xmlReader=new XMLReader(SDEDBConnectionManager.class.getClassLoader().getResourceAsStream("config.xml"));
		serverString=xmlReader.getValueByXPath("SDE/server");
		dataBaseString=xmlReader.getValueByXPath("SDE/database");
		userNameString=xmlReader.getValueByXPath("SDE/user");
		passWordString=xmlReader.getValueByXPath("SDE/pass");
		try {
			instance = Integer.parseInt(xmlReader
					.getValueByXPath("SDE/service"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println("Resource File Error."+e.getMessage());
		}
	}

	public SDEDBConnectionManager()
	{
		initConn();
	}
	/**
	 * sde连接初始化
	 */
	public static void initConn() {

		try {
			seConnection=new SeConnection(serverString, instance, dataBaseString, userNameString, passWordString);
			
		} catch (SeException e) {
			// TODO Auto-generated catch block
			System.out.println("无法创建数据库连接，请检查config.xml文件的sde连接配置");
			e.printStackTrace();
		}
	}
	/**
	 * 测试连接是否成功，如果成功返回true,否则返回false
	 * @return
	 */
	public Boolean testConn() {
		
		if(seConnection!=null){
			return true;
		}
		else {
			return false;
		}
		
	}
	/**
	 * 关闭sde连接
	 */
	public void closeConn() {
		
		if(seConnection!=null){
			try {
				seConnection.close();
			} catch (SeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	public static  SeConnection  getConn() {
		
//		if(seConnection==null)
//		{
//			initConn();
//		}
		try {
			seConnection=new SeConnection(serverString, instance, dataBaseString, userNameString, passWordString);
		} catch (SeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seConnection;
	}

}
