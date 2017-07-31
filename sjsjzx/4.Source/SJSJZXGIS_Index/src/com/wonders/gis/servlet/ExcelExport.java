/**
 * 
 */
package com.wonders.gis.servlet;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author XiaQiuQin
 *
 */
public class ExcelExport extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8469671981567456227L;
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * Constructor of the object.
	 */
	public ExcelExport() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestProcess(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestProcess(request, response);
	}
	 DocFlavor psInFormat = DocFlavor.INPUT_STREAM.PDF; 
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		
	}
	
	private void requestProcess(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("GBK");
		//response.setHeader("Cache-Control", "no-cache");
		String data = request.getParameter("htmltable");
		data = new String(data.getBytes("ISO-8859-1"), "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + df.format(new Date()) + ".xls" );           
		response.setContentType("application/vnd.ms-excel");
		response.getWriter().write(data);
		
		response.setStatus(200);
		
	}
}
