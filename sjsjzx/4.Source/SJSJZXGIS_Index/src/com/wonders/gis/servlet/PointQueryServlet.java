package com.wonders.gis.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.gis.db.DbProxy;
import com.wonders.gis.db.conn.DBConnectionManager;

/**
 * Servlet implementation class PointQueryServlet
 * 点查询处理 Servlet
 */
public class PointQueryServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5509805126975401580L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public PointQueryServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request, response);
	}
	
	/** 处理请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		//request.setCharacterEncoding("UTF-8");
		String layer = request.getParameter("layer");
		String fields = request.getParameter("fields");
		String whereClause = request.getParameter("where");
		String geometry = request.getParameter("geometry");
		String relations = request.getParameter("relations");
		String buffer = request.getParameter("buffer");
		
		layer = (layer == null )? "": layer.trim();
		fields = (fields == null )? "": fields.trim();
		whereClause = (whereClause == null )? "": whereClause.trim();
		geometry = (geometry == null )? "": geometry.trim();
		relations = (relations == null )? "": relations.trim();
		buffer = (buffer == null )? "": buffer.trim();
		
		//转换编码
		layer = new String(layer.getBytes("ISO-8859-1"), "UTF-8");
		fields = new String(fields.getBytes("ISO-8859-1"), "UTF-8");
		whereClause = new String(whereClause.getBytes("ISO-8859-1"), "UTF-8");
		
		StringBuffer sb = new StringBuffer();
		sb.append("{\"datas\":[");
		if(layer.length() > 0){
			if(whereClause.length() > 0 ||(geometry.length() > 0 && relations.length() > 0)){
				if(fields.length() == 0){
					fields = "ST_AsGeoJson(the_geom) as geometry";
				}
				else{
					fields += ", ST_AsGeoJson(the_geom) as geometry";
				}
				
				String sql = "select " + fields + " from " + layer + " where 1=1 ";
				if(whereClause.length() > 0){
					sql += " and " + whereClause;
				}
				if(geometry.length() > 0 && relations.length() > 0){
					if(buffer.length() > 0){
						try{
							buffer = String.valueOf(Double.parseDouble(buffer));
						}
						catch(Exception ex){
						}
						sql += " and " + relations + "( the_geom, ST_Buffer('" + geometry + "'," + buffer + "))";
					}
					else{
						sql += " and " + relations + "( the_geom, '" + geometry + "')";
					}
				}
				try {
					conn = DBConnectionManager.getInstance().getDBConnection();
					Statement statement = conn.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					ResultSetMetaData rsmd = rs.getMetaData();
					int colSize = rsmd.getColumnCount();
					String[] columnName = getColumnName(rsmd);
					int icount = 0;
					while (rs.next()) {
						if(icount > 0){
							sb.append(",");
						}
						sb.append("{");
						int fieldCount = colSize;
						
						for (int i = 0; i < fieldCount; i++) {
							int _type = rsmd.getColumnType(i + 1);
							String fieldName = columnName[i];
							if(i > 0){
								sb.append(", ");
							}
							if(fieldName.toLowerCase().equals("geometry")){
								sb.append("\"");
								sb.append(fieldName);
								sb.append("\":");
								sb.append(rs.getString(i + 1));
								sb.append("");
							}
							else if (_type == Types.SMALLINT || _type == Types.INTEGER ||_type == Types.BIGINT || _type == Types.DECIMAL 
									|| _type == Types.DOUBLE || _type == Types.FLOAT || _type == Types.REAL || _type == Types.NUMERIC
									|| _type == Types.BIT || _type == Types.BOOLEAN )
							{
								sb.append("\"");
								sb.append(fieldName);
								sb.append("\":");
								sb.append(rs.getString(i + 1));
								sb.append("");
							} else{
								sb.append("\"");
								sb.append(fieldName);
								sb.append("\":\"");
								sb.append(rs.getString(i + 1));
								sb.append("\"");
							}
						}
						sb.append("}");
						icount++;
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		sb.append("]}");
		response.setContentType("text/html");
		ServletOutputStream outServ = response.getOutputStream();
		OutputStreamWriter ow = new OutputStreamWriter(outServ, "utf-8");
		ow.write(sb.toString());
		ow.flush();
		ow.close();
	}
	
	/** 获取字段名称
	 * @param rsmd ResultSetMetaData
	 * @return 字段名称
	 */
	private String[] getColumnName(ResultSetMetaData rsmd) {
		String[] columnName = null;
		try {
			columnName = new String[rsmd.getColumnCount()];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				columnName[i] = rsmd.getColumnName(i + 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columnName;
	}

}
