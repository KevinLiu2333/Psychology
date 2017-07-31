/**
 * 
 */
package com.wonders.gis.servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.axis.AxisFault;
import com.esri.arcgisws.*;
import com.wonders.gis.utils.Util;
import com.wonders.gis.utils.XMLReader;

/**
 * @author XiaQiuQin
 *
 */
public class IntersectingRoads extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7698235508471053206L;
	private static String layerName = "dl";
	private static String fieldName = "name";
	private static String urlStr = "";
	private static boolean isInitConfig = false;
	
	/**
	 * Constructor of the object.
	 */
	public IntersectingRoads() {
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
		//response.setHeader("Cache-Control", "no-cache");
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
			layerName = reader.getValueByXPath("IntersectingRoads/layerName");
			fieldName = reader.getValueByXPath("IntersectingRoads/fieldName");
			List list = reader.getListByXPath("mapServices/mapService");
			for(int i = 0; i < list.size(); i++){
				org.dom4j.Element e = (org.dom4j.Element)list.get(i);
				String name = e.attributeValue("name");
				if(name.equalsIgnoreCase("IntersectMapService")){
					urlStr = e.attributeValue("url").trim();
					
					break;
				}
			}
			System.out.println("url=" + urlStr);
			isInitConfig = true;
		}
	}
	
	private void requestProcess(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String returnType = request.getParameter("returnType");
		String roadName = request.getParameter("roadName");
        roadName = new String(roadName.getBytes("ISO-8859-1"), "UTF-8");
        if("xml".equals(returnType)){
        	System.out.println("startaroadNameXML=" + roadName);
        	String resultRoads = IntersectRoadsXML(roadName);
        	response.setCharacterEncoding("utf-8");
        	response.getOutputStream().write(resultRoads.getBytes("utf-8"));
//        	response.getWriter().write(resultRoads);
    	}
        else{
        	System.out.println("startaroadNameStr=" + roadName);
        	String resultRoads = IntersectRoads(roadName);
        	response.setCharacterEncoding("utf-8");
        	response.getOutputStream().write(resultRoads.getBytes("utf-8"));
        }
		return;
	}
	
	private String IntersectRoadsXML(String roadName){
		String resultRoads = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n<crossRoadInfos>\n";
		if(urlStr.length() > 0){
			try {
				URL url = new URL(urlStr);
				MapServerBindingStub mapServer = new MapServerBindingStub(url, null);
				String mapName = mapServer.getDefaultMapName();
			    MapServerInfo mapServerInfo = mapServer.getServerInfo(mapName);
			    int layerId = Util.getLayerIdByName(mapServerInfo, layerName);
			    if (layerId == -1)
			    	return resultRoads;
			    
			    QueryFilter queryFilter = new QueryFilter();
			    queryFilter.setWhereClause(fieldName + "='" + roadName + "'");
			    RecordSet recordSet = mapServer.queryFeatureData(mapName, layerId, queryFilter);
			    int count = recordSet.getRecords().length;
			    for (int i = 0; i < count; i++)
			    {
			    	Record record = recordSet.getRecords()[i];
			    	PolylineN p1 = (PolylineN)record.getValues()[4];
			    	SpatialFilter spFilter = new SpatialFilter();
			    	spFilter.setFilterGeometry(p1);
			    	spFilter.setGeometryFieldName("SHAPE");
			    	spFilter.setWhereClause(fieldName + "<>'" + roadName + "'");
			    	spFilter.setSearchOrder(EsriSearchOrder.esriSearchOrderAttribute);
			    	spFilter.setSpatialRelDescription("");
			    	spFilter.setSpatialRel(EsriSpatialRelEnum.esriSpatialRelIntersects);
			    	RecordSet subRecordSet = mapServer.queryFeatureData(mapName, layerId, spFilter);
			    	int roadsCount = subRecordSet.getRecords().length;
			    	for (int m = 0; m < roadsCount; m++)
			    	{
			    		Record roadRec = subRecordSet.getRecords()[m];
		                PolylineN p2 = (PolylineN)roadRec.getValues()[4];
	                	PointN pt = Util.getCrossPoint(p1, p2);
	                	if (pt != null)
	                	{
	                		double x = pt.getX();
	                		double y = pt.getY();
	                		resultRoads += "<crossRoad>";
	                		resultRoads += "<roadName>" + roadRec.getValues()[2].toString() + "</roadName>";
	                		resultRoads += "<X>" + x + "</X>";
	                		resultRoads += "<Y>" + y + "</Y>";
	                		resultRoads += "</crossRoad>\n";
	                	}
			    	}
			    }
			} catch (AxisFault e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		resultRoads += "</crossRoadInfos>";
		return resultRoads;
	}
	private String IntersectRoads(String roadName){
		String resultRoads = "";
		try {
			URL url = new URL(urlStr);
			MapServerBindingStub mapServer = new MapServerBindingStub(url, null);
			String mapName = mapServer.getDefaultMapName();
		    MapServerInfo mapServerInfo = mapServer.getServerInfo(mapName);
		    int layerId = Util.getLayerIdByName(mapServerInfo, layerName);
		    if (layerId == -1)
		    	return resultRoads;
		    
		    QueryFilter queryFilter = new QueryFilter();
		    queryFilter.setWhereClause(fieldName + "='" + roadName + "'");
		    RecordSet recordSet = mapServer.queryFeatureData(mapName, layerId, queryFilter);
		    int count = recordSet.getRecords().length;
		    for (int i = 0; i < count; i++)
		    {
		    	Record record = recordSet.getRecords()[i];
		    	PolylineN p1 = (PolylineN)record.getValues()[4];
		    	SpatialFilter spFilter = new SpatialFilter();
		    	spFilter.setFilterGeometry(p1);
		    	spFilter.setGeometryFieldName("SHAPE");
		    	spFilter.setWhereClause(fieldName + "<>'" + roadName + "'");
		    	spFilter.setSearchOrder(EsriSearchOrder.esriSearchOrderAttribute);
		    	spFilter.setSpatialRelDescription("");
		    	spFilter.setSpatialRel(EsriSpatialRelEnum.esriSpatialRelIntersects);
		    	RecordSet subRecordSet = mapServer.queryFeatureData(mapName, layerId, spFilter);
		    	int roadsCount = subRecordSet.getRecords().length;
		    	String tmp = "";
		    	for (int m = 0; m < roadsCount; m++)
		    	{
		    		Record roadRec = subRecordSet.getRecords()[m];
	                PolylineN p2 = (PolylineN)roadRec.getValues()[1];
                	PointN pt = Util.getCrossPoint(p1, p2);
                	if (pt != null)
                	{
                		double x = pt.getX();
                		double y = pt.getY();
                		tmp = roadRec.getValues()[3].toString() + "@" + x + " " + y;
                		if(resultRoads.length() > 0){
                			resultRoads += "," + tmp;
                		}
                		else{
                			resultRoads = tmp;
                		}
                	}
		    	}
		    }
		    return resultRoads;
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return resultRoads;
	}
}
