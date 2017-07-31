package com.wonders.gis.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.esri.sde.sdk.client.SeColumnDefinition;
import com.esri.sde.sdk.client.SeConnection;
import com.esri.sde.sdk.client.SeCoordinateReference;
import com.esri.sde.sdk.client.SeDelete;
import com.esri.sde.sdk.client.SeException;
import com.esri.sde.sdk.client.SeInsert;
import com.esri.sde.sdk.client.SeLayer;
import com.esri.sde.sdk.client.SeQuery;
import com.esri.sde.sdk.client.SeQueryInfo;
import com.esri.sde.sdk.client.SeRow;
import com.esri.sde.sdk.client.SeShape;
import com.esri.sde.sdk.client.SeSqlConstruct;
import com.esri.sde.sdk.client.SeTable;
import com.esri.sde.sdk.client.SeUpdate;
import com.esri.sde.sdk.geom.SeGeometryException;
import com.ibm.icu.text.SimpleDateFormat;
import com.wonders.gis.db.conn.PostGISDBConnectionManager;
import com.wonders.gis.db.conn.SDEDBConnectionManager;
import com.esri.arcgis.interop.Dispatch;

/**
 * Servlet implementation class GetOracleCountData
 */
public class GetOracleCountData extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String dbName = "SDE.";
	private String colUmn=""; 
	private String whereClause="";
	private void closeCon(SeConnection seConn){
		if (seConn != null){
			try{
				seConn.close();
			}
			catch (SeException ex) {
				ex.printStackTrace();
			}
			seConn = null;
		}
	}
	
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// 返回结果字符串
		String strResult = "";
		// 业务流转
		String dataBaseString=request.getParameter("database");
		String action = request.getParameter("action");
		colUmn = new String(request.getParameter("column").getBytes("ISO-8859-1"),"utf-8");
		whereClause = new String(request.getParameter("whereClause").getBytes("ISO-8859-1"),"utf-8");
		if(dataBaseString.equalsIgnoreCase("sde")){
			if (action.equalsIgnoreCase("query")) {
				strResult = queryFromSDE(request, response);
			}
		}
		response.setCharacterEncoding("utf-8");
		response.getOutputStream().write(strResult.getBytes("utf-8"));

	}
	/**从sde里面读取数据
	 * @param req
	 * @param resp
	 * @return
	 */
	private String queryFromSDE(ServletRequest req, ServletResponse resp){
		
		SeConnection seConn=SDEDBConnectionManager.getConn();
		String tablename = dbName + req.getParameter("tablename");
		tablename = (tablename == null) ? "" : tablename.trim();
		SeQuery seQuery=null;
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		SeRow row=null;
		try {
			String[] columnsStrings;
			columnsStrings=new String[1];
			columnsStrings[0]=colUmn;
			SeSqlConstruct seSqlConstruct=new SeSqlConstruct(tablename);
			seSqlConstruct.setWhere(whereClause);
			seQuery=new SeQuery(seConn,columnsStrings, seSqlConstruct);
		    seQuery.prepareQuery();
			seQuery.execute();
			row=seQuery.fetch();
			if(row==null)
			{
				System.out.println("No record fetched!");
			}
			while (row!=null) {
				JSONObject jo=new JSONObject();
				jo.put(colUmn,row.getString(0));	
				ja.add(jo);
				row=seQuery.fetch();
			}
		} catch (SeException e) {
			e.printStackTrace();
			
		}finally {
			 try {
				 if(seQuery!=null){
					 seQuery.close();
				 }
				closeCon(seConn);
			} catch (SeException e) {
				e.printStackTrace();
			}
		
		}
		System.out.println("ja.size()"+ja.size());
		return Integer.toString(ja.size());
	}
	
	public static void queryLayerVersionedFeatureCount(){    
	   try {  
	        SeConnection conn = SDEDBConnectionManager.getConn();
	        SeTable table = new SeTable(conn, "C101_XZFW");
	        
	        SeColumnDefinition[] tableDef = table.describe();  
	        String[] cols = new String[tableDef.length];  
	        for (int j = 0; j < cols.length; j++)  
	        {  
            cols[j] = tableDef[j].getName();  
        }  
	        SeSqlConstruct sqlCons = new SeSqlConstruct("C101_XZFW");  
	        SeQuery query = new SeQuery(conn, cols, sqlCons);  
	          
	  
	        	SeQueryInfo queryInfo = new SeQueryInfo();  
	            queryInfo.setQueryType(SeQueryInfo.SE_QUERYTYPE_ATTRIBUTE_FIRST);  
	            queryInfo.setColumns(cols);  
	            queryInfo.setConstruct(sqlCons);  
	              
	                SeTable.SeTableStats stats=query.calculateTableStatistics("*", SeTable.SeTableStats.SE_COUNT_STATS, 

queryInfo, 0);  
	                int count= stats.getCount();  
	                System.out.println("------featureclass feature count -----------------");  
	                System.out.println(count);  
	                System.out.println("--------------------------------------------------");  
	             
         
          System.out.println("let count:"+count);
    } catch (Exception ex) {  
	        ex.printStackTrace();  
   }  
} 



}