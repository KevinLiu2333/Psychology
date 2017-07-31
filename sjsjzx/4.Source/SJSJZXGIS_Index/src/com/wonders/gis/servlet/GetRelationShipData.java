package com.wonders.gis.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;

import com.wonders.gis.db.conn.DBSDEConnectionManager;

/**
 * Servlet implementation class GetRelationShipData
 */
public class GetRelationShipData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn = null;
	
	private ResultSet rs  = null;
	
	private Statement st = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetRelationShipData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		requestProcess(request, response);
	}

	private void requestProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fields = request.getParameter("fields");
		String table = request.getParameter("table");
		String whereClause = request.getParameter("where");
		String layerName= request.getParameter("layerName");
		String planCode= request.getParameter("planCode");
		fields = (fields == null) ? "" : fields.trim();
		table = (table == null) ? "" : table.trim();
		whereClause = (whereClause == null) ? "" : whereClause.trim();

		fields = new String(fields.getBytes("ISO-8859-1"), "UTF-8");
		table = new String(table.getBytes("ISO-8859-1"), "UTF-8");
		
		if(layerName!=null)
		{
			layerName = new String(layerName.getBytes("ISO-8859-1"), "UTF-8");
		}else {
			layerName="layerName";
		}
		if(planCode!=null)
		{
			planCode = new String(planCode.getBytes("ISO-8859-1"), "UTF-8");
		}else {
			planCode="planCode";
		}
		whereClause = new String(whereClause.getBytes("ISO-8859-1"), "UTF-8");

		StringBuffer sb = new StringBuffer();
		String sqlStr = "";
		sb.append("{\"layerName\":\""+layerName+"\",\"planCode\":\""+planCode+"\",\"datas\":[");
		if (table.length() > 0) {
			if (fields.length() > 0) {
				sqlStr = "select " + fields + " from " + table;
			} else {
				sqlStr = "select * from " + table;
			}
			if (whereClause.length() > 0) {
				sqlStr += " " + whereClause;
			}
			try {
				conn = DBSDEConnectionManager.getInstance().getDBConnection();
				st = conn.createStatement();
				rs = st.executeQuery(sqlStr);
				ResultSetMetaData rsmd = rs.getMetaData();
				int colSize = rsmd.getColumnCount();
				String[] columnName = getColumnName(rsmd);
				int icount = 0;
				while (rs.next()) {
					if (icount > 0) {
						sb.append(",");
					}
					sb.append("{");
					int fieldCount = colSize;

					for (int i = 0; i < fieldCount; i++) {
						int _type = rsmd.getColumnType(i + 1);
						String fieldName = columnName[i];
						if (i > 0) {
							sb.append(", ");
						}
						if (fieldName.toLowerCase().equals("geometry")) {
							sb.append("\"");
							sb.append(fieldName);
							sb.append("\":");
							sb.append(rs.getString(i + 1).trim());
							sb.append("");
						} else if (_type == Types.SMALLINT
								|| _type == Types.INTEGER
								|| _type == Types.BIGINT
								|| _type == Types.DECIMAL
								|| _type == Types.DOUBLE
								|| _type == Types.FLOAT || _type == Types.REAL
								|| _type == Types.NUMERIC || _type == Types.BIT
								|| _type == Types.BOOLEAN) {
							sb.append("\"");
							sb.append(fieldName);
							sb.append("\":");
							sb.append(rs.getString(i + 1).trim());
							sb.append("");
						} else {
							sb.append("\"");
							sb.append(fieldName);
							sb.append("\":\"");
							if(rs.getString(i + 1)!=null){
								sb.append(rs.getString(i + 1).trim());
							}else{
								sb.append(rs.getString(i + 1));
							}
							sb.append("\"");
						}
					}
					sb.append("}");
					icount++;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(rs != null){
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(st!= null){
					try {
						st.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(conn!= null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				DBSDEConnectionManager.getInstance().freeDBConnection(conn);
			}
		}
		sb.append("]}");
		response.setContentType("text/html");
		ServletOutputStream outServ = response.getOutputStream();
		OutputStreamWriter ow = new OutputStreamWriter(outServ, "utf-8");
		
		 Pattern p = Pattern.compile("\\s*|\t|\r|\n");    
	     Matcher m = p.matcher(sb.toString().trim());    
	     String after = m.replaceAll("");    
	     
		ow.write(after);
		ow.flush();
		ow.close();
	}

	/**
	 * 获取字段名称
	 * 
	 * @param rsmd
	 *            ResultSetMetaData
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