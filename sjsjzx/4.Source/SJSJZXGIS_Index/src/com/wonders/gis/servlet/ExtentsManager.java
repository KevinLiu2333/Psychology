package com.wonders.gis.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;


import javax.servlet.ServletException;
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
import com.esri.sde.sdk.client.SeRow;
import com.esri.sde.sdk.client.SeShape;
import com.esri.sde.sdk.client.SeSqlConstruct;
import com.esri.sde.sdk.client.SeUpdate;
import com.esri.sde.sdk.geom.SeGeometryException;
import com.ibm.icu.text.SimpleDateFormat;
import com.wonders.gis.db.conn.PostGISDBConnectionManager;
import com.wonders.gis.db.conn.SDEDBConnectionManager;

public class ExtentsManager extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String dbName = "SDE.";
	
	private String layerNameString="WFYD";
	private String shapeFieldName = "SHAPE";
	private String labelFieldName="PLAN_NAME";
	private SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy");
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
		if(dataBaseString.equalsIgnoreCase("postgis"))
		{
			if (action.equalsIgnoreCase("query")) {
				strResult = query(request, response);
			} else if (action.equalsIgnoreCase("save")) {
				strResult = save(request, response);
			} else if (action.equalsIgnoreCase("delete")) {
				strResult = delete(request, response);
			}
		}
		else if(dataBaseString.equalsIgnoreCase("sde")){
			if (action.equalsIgnoreCase("query")) {
				strResult = queryFromSDE(request, response);
			}else if(action.equalsIgnoreCase("save"))
			{
				strResult=saveToSDE(request, response);
			}else if (action.equalsIgnoreCase("delete")) {
				strResult = deleteFromSDE(request, response);
			}
		}
		response.setCharacterEncoding("utf-8");
		response.getOutputStream().write(strResult.getBytes("utf-8"));
	}
	
	private String deleteFromSDE(ServletRequest req,ServletResponse resp){
		SeConnection seConn=SDEDBConnectionManager.getConn();
		layerNameString=req.getParameter("tablename");
		String misid = req.getParameter("misid");
		String resultStr = "0";
		String where="misid='"+misid+"'";
		SeDelete seDelete=null;
		SeQuery seQuery=null;
		String[] columnsStrings=new String[1];;
		columnsStrings[0]="misid";
		SeRow row=null;
		SeSqlConstruct seSqlConstruct=new SeSqlConstruct(layerNameString);
		seSqlConstruct.setWhere(where);
		
		try {
			
			seQuery=new SeQuery(seConn,columnsStrings, seSqlConstruct);
			seQuery.prepareQuery();
			seQuery.execute();
			row=seQuery.fetch();
			if(row==null)
			{
				resultStr="1";
				return resultStr;
			}
			seDelete=new SeDelete(seConn);
			seDelete.fromTable(layerNameString,where);
			resultStr="1";
			
		} catch (SeException e) {
			e.printStackTrace();
		}finally{
			try {
				if(seDelete!=null){
					
					seDelete.close();
				}
				closeCon(seConn);
			} catch (SeException e) {
				e.printStackTrace();
			}
		}
		
		return resultStr;
	}
	
	/**
	 * 向sde里面保存绘制的数据
	 * @param req
	 * @param resp
	 * @return
	 */
	private String saveToSDE(ServletRequest req,ServletResponse resp){
		
		String resultStr = "0";
		String queryStr = queryFromSDE(req, resp);
		JSONObject obj = JSONObject.fromObject(queryStr);
		JSONArray ja = obj.getJSONArray("message");
		if (ja.size() == 0) {
			resultStr = insertToSDE(req, resp);
		} else {
			resultStr = updateFromSDE(req, resp);
		}
		return resultStr;
		
	}
	/**
	 * 向sde插入绘制的新数据
	 * @param req
	 * @param resp
	 * @return
	 */
	private String insertToSDE(ServletRequest req, ServletResponse resp){
		
		SeConnection seConn=SDEDBConnectionManager.getConn();
		layerNameString=req.getParameter("tablename");
		String geomstr = req.getParameter("geomstr");
		geomstr=createGeoString(geomstr);
		String queryField=req.getParameter("queryfield");
		queryField = (queryField == null) ? "misid" : queryField.trim();
		String misid =req.getParameter("misid");
		String misName="";
		String date="";
		String misNo="";
		String misType="";
		Date misDate=new Date();
		String resultStr = "0";
		SeInsert insert=null;
		try {
			misName=(req.getParameter("misname")==null)?"":req.getParameter("misname").trim();
			misName=new String(misName.getBytes("ISO-8859-1"),"utf-8");
			date=(req.getParameter("misdate")==null)?"":req.getParameter("misdate").trim();
			date=new String(date.getBytes("ISO-8859-1"), "utf-8");
			if(!"".equalsIgnoreCase(date)){
				try {
					misDate = dateformat2.parse(date);
					misDate.setMonth(2);
				} catch (ParseException e1) {
					try {
						
						misDate = dateformat1.parse(date);
						misDate.setHours(11);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
			
			misNo=(req.getParameter("misno")==null)?"":req.getParameter("misno").trim();
			misNo=new String(misNo.getBytes("ISO-8859-1"), "utf-8");
			misType=(req.getParameter("mistype")==null)?"":req.getParameter("mistype").trim();
			misType=new String(misType.getBytes("ISO-8859-1"), "utf-8");
			insert=new SeInsert(seConn);
			SeLayer layer = new SeLayer(seConn, layerNameString,"SHAPE");
			SeCoordinateReference seCoordinateReference=layer.getCoordRef();
			SeShape shape=new SeShape(seCoordinateReference);
			insert.setWriteMode(true);
			if("C401_JHFW".equalsIgnoreCase(layerNameString)){
				insert.intoTable(layerNameString,new String[]{queryField,"MISNAME","MISNO","MISTYPE","MISDATE","SHAPE"});

				SeRow row=insert.getRowToSet();
				row.setNString(0, misid);
				row.setNString(1, misName);
				row.setNString(2, misNo);
				row.setNString(3, misType);
				row.setDate(4, misDate);
				row.setShape(5,generateSDEStrFromPostgis(geomstr,shape));
			}else{
				
				insert.intoTable(layerNameString,new String[]{queryField,"SHAPE"});

				SeRow row=insert.getRowToSet();
				row.setNString(0, misid);
				row.setShape(1,generateSDEStrFromPostgis(geomstr,shape));
			}
			insert.execute();
			insert.flushBufferedWrites();
			resultStr="1";
		} catch (SeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally
		{
			try {
				insert.close();
				closeCon(seConn);
			} catch (SeException e) {
				e.printStackTrace();
			}
		}
		
		return resultStr;

	}
	
	private String createGeoString(String geometryStr){
		
		String geoStr="";
		if(geometryStr.contains("POLYGON"))
		{
			geoStr=geometryStr;
		}else
		{
			geoStr="MULTIPOLYGON ("+geometryStr+")";
		}
		return geoStr;
	}
	private SeShape generateSDEStrFromPostgis(String geomstr,SeShape shape){
		
		try {
			shape.generateFromText(geomstr);
			
		} catch (SeException e) {
			e.printStackTrace();
		}
		return shape;
	}
	
	private  String updateFromSDE(ServletRequest req,ServletResponse resp) {
		SeConnection seConn=SDEDBConnectionManager.getConn();
		layerNameString=req.getParameter("tablename");
		String geomstr = req.getParameter("geomstr");
		geomstr=createGeoString(geomstr);
		String misid = req.getParameter("misid");
		String queryField=req.getParameter("queryfield");
		queryField = (queryField == null) ? "misid" : queryField.trim();
		String resultStr = "0";
		SeUpdate seUpdate=null;
		String misName="";
		String date="";
		String misNo="";
		String misType="";
		Date misDate=new Date();
		try {
			misName=(req.getParameter("misname")==null)?"":req.getParameter("misname").trim();
			misName=new String(misName.getBytes("ISO-8859-1"),"utf-8");
			date=(req.getParameter("misdate")==null)?"":req.getParameter("misdate").trim();
			date=new String(date.getBytes("ISO-8859-1"), "utf-8");
			System.out.print("\ndata:"+date);
			if(!"".equalsIgnoreCase(date)){
				try {
					misDate = dateformat2.parse(date);
					misDate.setMonth(2);
				} catch (ParseException e1) {
					try {
						
						misDate = dateformat1.parse(date);
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		
			misNo=(req.getParameter("misno")==null)?"":req.getParameter("misno").trim();
			misNo=new String(misNo.getBytes("ISO-8859-1"), "utf-8");
			misType=(req.getParameter("mistype")==null)?"":req.getParameter("mistype").trim();
			misType=new String(misType.getBytes("ISO-8859-1"), "utf-8");
			seUpdate=new SeUpdate(seConn);
			if("C401_JHFW".equalsIgnoreCase(layerNameString)){
				
				seUpdate.toTable(layerNameString,new String[]{queryField,"MISNAME","MISNO","MISTYPE","MISDATE","SHAPE"}, queryField+"='"+misid+"'");
				SeLayer seLayer=new SeLayer(seConn, layerNameString, "SHAPE");
				SeShape seShape=new SeShape(seLayer.getCoordRef());
				SeRow row=seUpdate.getRowToSet();
				row.setNString(0, misid);
				row.setNString(1, misName);
				row.setNString(2, misNo);
				row.setNString(3, misType);
				row.setDate(4, misDate);
				row.setShape(5,generateSDEStrFromPostgis(geomstr,seShape));
			}else{
				
				seUpdate.toTable(layerNameString, new String[]{"misid","SHAPE"}, queryField+"='"+misid+"'");
				SeLayer seLayer=new SeLayer(seConn, layerNameString, "SHAPE");
				SeShape seShape=new SeShape(seLayer.getCoordRef());
				SeRow row=seUpdate.getRowToSet();
				row.setNString(0, misid);
				row.setShape(1,generateSDEStrFromPostgis(geomstr,seShape));
			}
			seUpdate.execute();
			resultStr="1";
			
		} catch (SeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally
		{
			try {
				seUpdate.close();
				closeCon(seConn);
			} catch (SeException e) {
				e.printStackTrace();
			}
		}
		return resultStr;
	}
	
	/**从sde里面读取数据
	 * @param req
	 * @param resp
	 * @return
	 */
	private String queryFromSDE(ServletRequest req, ServletResponse resp){
		
		SeConnection seConn=SDEDBConnectionManager.getConn();
		layerNameString=req.getParameter("tablename");
		String misids = req.getParameter("misid");
		labelFieldName=req.getParameter("labelfield");
		String tablename = dbName + req.getParameter("tablename");
		String queryField=req.getParameter("queryfield");
		queryField = (queryField == null) ? "misid" : queryField.trim();
		tablename = (tablename == null) ? "" : tablename.trim();
		misids = (misids == null) ? "" : misids.trim();
		SeQuery seQuery=null;
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		SeRow row=null;
		try {
			String[] columnsStrings;
			if(labelFieldName!=null&&!"".equalsIgnoreCase(labelFieldName)){
				columnsStrings=new String[3];
				columnsStrings[0]=queryField;
				columnsStrings[1]=labelFieldName;
				columnsStrings[2]=shapeFieldName;
			}else{
				columnsStrings=new String[2];
				columnsStrings[0]=queryField;
				columnsStrings[1]=shapeFieldName;
			}
	 
			String whereClause=new String(queryField+" in ('" + misids + "')");
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
				SeColumnDefinition[] colDefs=row.getColumns();
				JSONObject jo=new JSONObject();
				for (int i = 0; i < colDefs.length; i++) {
					SeColumnDefinition colDef=colDefs[i];
					int dataType=colDef.getType();
					SeShape shape=null;
					switch (dataType) {
					case SeColumnDefinition.TYPE_NSTRING:
					{
						if(i==1){
							jo.put("Name",row.getString(i));	
						}
						break;
					}
					case SeColumnDefinition.TYPE_SHAPE:
					{
						if(row.getShape(i).toSeGeometry()!=null)
						{
							shape=row.getShape(i);
							jo.put("geom",generateStringFromSeshape(shape));
							ja.add(jo);
						}
						break;
					}

					default:
						break;
					}
				}
				row=seQuery.fetch();
			}
			obj.put("result", "true");
			obj.put("message", ja.toString());
			

		} catch (SeException e) {
			e.printStackTrace();
			obj.put("result", "false");
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
		return obj.toString().trim().replace(", ", ",").replace(" ((( ", "(((").replace("(( ", "((");
	}
	
	
	private  String generateStringFromSeshape(SeShape shape){
	
		StringBuffer shapeSf=new StringBuffer();
				
		try {
				
				String shapeString=shape.toSeGeometry().asText();
				if(shapeString.startsWith("MULTIPOLYGON"))
				{
					shapeSf=new StringBuffer(shape.toSeGeometry().asText());
				}else if(shapeString.startsWith("POLYGON")){
					shapeSf=new  StringBuffer(shapeString.replace("POLYGON ", "MULTIPOLYGON(").replace("))", ")))"));
				}
			    
			
		} catch (SeGeometryException e) {
			e.printStackTrace();
		} catch (SeException e) {
			e.printStackTrace();
		}
		
		return shapeSf.toString().trim();
	}

	// 根据id和tablename查询geom，返回geomStr
	private String query(ServletRequest req, ServletResponse resp)
			throws UnsupportedEncodingException {
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		String tablename = dbName + req.getParameter("tablename");
		String misids = req.getParameter("misid");
		try {
			conn = PostGISDBConnectionManager.getInstance().getDBConnection();
			st = conn.createStatement();
			String sql = "select ST_AsText(the_geom) as geom from " + tablename
					+ " where misid in (" + misids + ")";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				JSONObject jo = new JSONObject();
				String geom = rs.getString("geom");
				jo.put("geom", geom);
				ja.add(jo);
			}
			obj.put("result", "true");
			obj.put("message", ja.toString());
		} catch (SQLException e) {
			obj.put("result", "false");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					obj.put("result", "false");
					e.printStackTrace();
				}
			}
			PostGISDBConnectionManager.getInstance().freeDBConnection(conn);
		}
		return obj.toString();
	}

	private String save(ServletRequest req, ServletResponse resp)
			throws UnsupportedEncodingException {
		String resultStr = "0";
		String queryStr = query(req, resp);
		JSONObject obj = JSONObject.fromObject(queryStr);
		JSONArray ja = obj.getJSONArray("message");
		if (ja.size() == 0) {
			resultStr = insert(req, resp);
		} else {
			resultStr = update(req, resp);
		}
		return resultStr;
	}

	private String insert(ServletRequest req, ServletResponse resp)
			throws UnsupportedEncodingException {
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		String geomstr = req.getParameter("geomstr");
		String tablename = dbName + req.getParameter("tablename");
		String mid = req.getParameter("misid");
		String misid = mid.substring(1, mid.length() - 1);
		String resultStr = "0";
		try {
			conn = PostGISDBConnectionManager.getInstance().getDBConnection();
			st = conn.createStatement();
			String sql = "INSERT INTO " + tablename
					+ "(\"misid\", the_geom) VALUES ('" + misid
					+ "',ST_GeomFromText('" + geomstr + "'))";
			st.executeUpdate(sql);
			resultStr = "1";
		} catch (Exception e) {
			resultStr = "0";
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					resultStr = "0";
					e.printStackTrace();
				}
			}
			PostGISDBConnectionManager.getInstance().freeDBConnection(conn);
		}
		return resultStr;
	}

	private String update(ServletRequest req, ServletResponse resp)
			throws UnsupportedEncodingException {
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		String geomstr = req.getParameter("geomstr");
		String tablename = dbName + req.getParameter("tablename");
		String mid = req.getParameter("misid");
		String misid = mid.substring(1, mid.length() - 1);
		String resultStr = "0";
		try {
			conn = PostGISDBConnectionManager.getInstance().getDBConnection();
			st = conn.createStatement();
			String sql = "UPDATE " + tablename
					+ " SET the_geom=ST_GeomFromText('" + geomstr
					+ "') WHERE misid = '" + misid + "'";
			st.executeUpdate(sql);
			resultStr = "1";
		} catch (Exception e) {
			resultStr = "0";
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					resultStr = "0";
					e.printStackTrace();
				}
			}
			PostGISDBConnectionManager.getInstance().freeDBConnection(conn);
		}
		return resultStr;
	}

	private String delete(ServletRequest req, ServletResponse resp)
			throws UnsupportedEncodingException {
		Statement st = null;
		ResultSet rs = null;
		Connection conn = null;
		String tablename = dbName + req.getParameter("tablename");
		String misid = req.getParameter("misid");
		String resultStr = "0";
		try {
			conn = PostGISDBConnectionManager.getInstance().getDBConnection();
			st = conn.createStatement();
			String sql = "DELETE FROM " + tablename + " where misid = '"
					+ misid + "'";
			st.executeUpdate(sql);
			resultStr = "1";
		} catch (Exception e) {
			resultStr = "0";
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					resultStr = "0";
					e.printStackTrace();
				}
			}
			PostGISDBConnectionManager.getInstance().freeDBConnection(conn);
		}
		return resultStr;
	}

}
