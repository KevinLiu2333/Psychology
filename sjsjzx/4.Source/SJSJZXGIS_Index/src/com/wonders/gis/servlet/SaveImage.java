/**
 * 
 */
package com.wonders.gis.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wonders.gis.utils.XMLReader;

/**
 * @author XiaQiuQin
 *
 */
public class SaveImage extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6402871915718931725L;
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private static String conClass = "oracle.jdbc.driver.OracleDriver";
	private static String conURL = "";
	private static String dbuser = "";
	private static String dbpass = "";
	private static String filePathStr;
	private static boolean isInitConfig = false;
	
	/**
	 * Constructor of the object.
	 */
	public SaveImage() {
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		if(!isInitConfig){
			XMLReader reader = new XMLReader(this.getClass().getClassLoader().getResourceAsStream("config.xml"));
			conClass = reader.getValueByXPath("Database/class");
			conURL = reader.getValueByXPath("Database/url");
			dbuser = reader.getValueByXPath("Database/user");
			dbpass = reader.getValueByXPath("Database/pass");
			filePathStr = reader.getAtributeValue("SaveImage", "ImagePathString");
			isInitConfig = true;
		}
	}
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(conClass);
		Connection conn = DriverManager.getConnection(conURL, dbuser, dbpass);
		return conn;
	}
	
	private void requestProcess(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("GBK");
		response.setHeader("Cache-Control", "no-cache");
		int len = request.getContentLength();
		if (len > 0)
        {
			String imgname = request.getParameter("imgname");
			imgname = new String(imgname.getBytes("ISO-8859-1"), "UTF-8");
            String eventid = request.getParameter("eventid");
            BufferedImage image = ImageIO.read(request.getInputStream());
            image.flush();
            //时间
            String filedate = df.format(new Date());
            String fullFileName = filePathStr + imgname + "_" + filedate + ".jpg";
            ImageIO.write(image, "JPG", new File(fullFileName));
            ImageIO.write(image, "JPG", response.getOutputStream());
        }
	}
	
}

