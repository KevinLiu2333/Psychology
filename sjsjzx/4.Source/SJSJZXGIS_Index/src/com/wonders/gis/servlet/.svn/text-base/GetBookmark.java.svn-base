/**
 * 
 */
package com.wonders.gis.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.wonders.gis.db.conn.DBConnectionManager;
import com.wonders.gis.db.conn.DBConnectionPool;
import com.wonders.gis.db.conn.PostGISDBConnectionManager;

/**
 * @author Apple
 *
 */
public class GetBookmark extends HttpServlet {

	/**
	 * 构造函数
	 */
	public GetBookmark() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestProcess(req,resp);
	}

	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestProcess(req,resp);
	}


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestProcess(req,resp);
	}


	/**
	 * 处理请求
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	private  void requestProcess(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, IOException{
		
		String action=request.getParameter("action");
		String strResult="";
		if (action.equalsIgnoreCase("query")) {
			strResult = query(request, response);
		} else if (action.equalsIgnoreCase("add")) {
			strResult = save(request, response);
		} else if (action.equalsIgnoreCase("delete")) {
			strResult = delete(request, response);
		}
		
		response.setCharacterEncoding("utf-8");
		response.getOutputStream().write(strResult.getBytes("utf-8"));	
	}
	
	private String query(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		try {
			obj = new JSONObject();
			ja = new JSONArray();
			String userId=request.getParameter("userid");
			userId = (userId == null) ? "" : userId.trim();
			userId = new String(userId.getBytes("ISO-8859-1"), "UTF-8");
			String sql = "select *  from  BOOKMARK where userid = '" + userId + "'";
			conn=DBConnectionManager.getInstance().getDBConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				JSONObject jsonObject=new JSONObject();
				String bmName=rs.getString("name");
				jsonObject.put("bmname", bmName);
				String minX=rs.getString("xmin");
				jsonObject.put("xmin", minX);
				String minY=rs.getString("ymin");
				jsonObject.put("ymin", minY);
				String maxX=rs.getString("xmax");
				jsonObject.put("xmax", maxX);
				String maxY=rs.getString("ymax");
				jsonObject.put("ymax", maxY);
				ja.add(jsonObject);
			}	
			obj.put("result", "true");
			obj.put("message", ja.toString());
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			obj.put("result", "false");
			e.printStackTrace();
		}
		finally{
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
			DBConnectionManager.getInstance().freeDBConnection(conn);
		}
		
		return obj.toString();
	}
	private String delete(HttpServletRequest request,HttpServletResponse response){
		Connection conn = null;
		Statement st = null;
		String userId=request.getParameter("userid");
		String bmName=request.getParameter("bmname");
		String resultStr = "0";
		try {
			conn=DBConnectionManager.getInstance().getDBConnection();
			st=conn.createStatement();
			userId = new String(userId.getBytes("ISO-8859-1"), "UTF-8");
			bmName = new String(bmName.getBytes("ISO-8859-1"), "UTF-8");
			String sql="delete from BOOKMARK where userid='"+userId+"' and name='"+bmName+"'";
//			System.out.println("sql=" + sql);
			st.execute(sql);
			resultStr = "1";
			st.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			resultStr = "0";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			resultStr = "0";
			e.printStackTrace();
		}
		finally{
			if(st!=null){
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					 resultStr = "0";
					e.printStackTrace();
				}
			}
			DBConnectionManager.getInstance().freeDBConnection(conn);
		}
		return resultStr;
	}
	private String save(HttpServletRequest request,HttpServletResponse response){
		Connection conn = null;
		PreparedStatement ps = null;
		String resultStr = "0";
		String userId=request.getParameter("userid");
		String bmName=request.getParameter("bmname");
		String minX=request.getParameter("xmin");
		String minY=request.getParameter("ymin");
		String maxX=request.getParameter("xmax");
		String maxY=request.getParameter("ymax");
		
		userId = (userId == null) ? "" : userId.trim();
		bmName = (bmName == null) ? "" : bmName.trim();
		minX = (minX == null) ? "" : minX.trim();
		minY = (minY == null) ? "" : minY.trim();
		maxX = (maxX == null) ? "" : maxX.trim();
		maxY = (maxY == null) ? "" : maxY.trim();
		
		try {
			userId = new String(userId.getBytes("ISO-8859-1"), "UTF-8");
			bmName = new String(bmName.getBytes("ISO-8859-1"), "UTF-8");
			minX = new String(minX.getBytes("ISO-8859-1"), "UTF-8");
			minY = new String(minY.getBytes("ISO-8859-1"), "UTF-8");
			maxX = new String(maxX.getBytes("ISO-8859-1"), "UTF-8");
			maxY = new String(maxY.getBytes("ISO-8859-1"), "UTF-8");
			conn=DBConnectionManager.getInstance().getDBConnection();
			String sql="insert into BOOKMARK (name,userid,xmin,ymin,xmax,ymax) values (?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, bmName);
			ps.setString(2, userId);
			ps.setString(3, minX);
			ps.setString(4, minY);
			ps.setString(5, maxX);
			ps.setString(6, maxY);
			ps.execute();
			ps.close();
			 resultStr = "1";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			 resultStr = "0";
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			 resultStr = "0";
			e.printStackTrace();
		}finally{
			if(ps!=null){
				try {
					ps.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					 resultStr = "0";
					e.printStackTrace();
				}
			}
			DBConnectionManager.getInstance().freeDBConnection(conn);
		}
		return resultStr;
	}
}
