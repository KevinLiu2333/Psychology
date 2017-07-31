package com.wonders.gis.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import com.esri.arcgisws.MapLayerInfo;
import com.esri.arcgisws.MapServerInfo;
import com.esri.arcgisws.Path;
import com.esri.arcgisws.Point;
import com.esri.arcgisws.PointN;
import com.esri.arcgisws.PolygonN;
import com.esri.arcgisws.PolylineN;
import com.esri.arcgisws.Ring;

/**
 * @author XiaQiuQin
 *
 */
public final class Util {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static Random random ;
	/**
	 * 
	 * @param mapServerInfo
	 * @param layerName
	 * @return
	 */
	public static int getLayerIdByName(MapServerInfo mapServerInfo, String layerName)
    {
        MapLayerInfo[] mapLayerInfos = mapServerInfo.getMapLayerInfos();
        int layerCount = mapLayerInfos.length;
        for (int i = 0; i < layerCount; i++)
        {
            MapLayerInfo mapLayerInfo = mapLayerInfos[i];
            if (mapLayerInfo.getName().equalsIgnoreCase(layerName))
            {
                return mapLayerInfo.getLayerID();
            }
        }
        return -1;
    }
    
	/**
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static PointN getCrossPoint(PolylineN p1, PolylineN p2)
    {
        PointN crossPoint = null;
        for(int k = 0; k < p1.getPathArray().length; k++)
        {
        	Path path = p1.getPathArray()[k];
            int pointCount = path.getPointArray().length;
            for (int i = 0; i < pointCount - 1; i++)
            {
                PointN pt1 = (PointN)path.getPointArray()[i];
                PointN pt2 = (PointN)path.getPointArray()[i + 1];
                for(int m=0; m < p2.getPathArray().length; m++)
                {
                	Path path2 = p2.getPathArray()[m];
                    int pointCount2 = path2.getPointArray().length;
                    for (int j = 0; j < pointCount2 - 1; j++)
                    {
                        PointN pt3 = (PointN)path2.getPointArray()[j];
                        PointN pt4 = (PointN)path2.getPointArray()[j + 1];
                        crossPoint = getCrossPoint(pt1, pt2, pt3, pt4);
                        if (crossPoint != null)
                        {
                            return crossPoint;
                        }
                    }
                }
            }
        }
        return crossPoint;
    }
    
	//求线段交点，无交点返回null
	/**
	 * 
	 */
	public static PointN getCrossPoint(PointN pt0, PointN pt1, PointN pt2, PointN pt3)
	{
        double t0, t1;
        // (1-t0)*pt0+t0*pt1=(1-t1)*pt2+t1*pt3
        // 两个方程，两个未知数，求解t0和t1
        t0 = pt2.getY() * (pt3.getX() - pt2.getX()) - pt2.getX() * (pt3.getY() - pt2.getY()) - (pt0.getY() * (pt3.getX() - pt2.getX()) - pt0.getX() * (pt3.getY() - pt2.getY()));
        t0 /= ((pt1.getY() - pt0.getY()) * (pt3.getX() - pt2.getX()) - (pt1.getX() - pt0.getX()) * (pt3.getY() - pt2.getY()));
        t1 = pt0.getX() + t0 * (pt1.getX() - pt0.getX()) - pt2.getX();
        t1 /= (pt3.getX() - pt2.getX());
        // 检查t0和t1以判断交点是否在线段上
        if (t0 < 0 || t0 > 1 || t1 < 0 || t1 > 1)
        {
            return null;
        }
        PointN pt = new PointN();
        pt.setX((1 - t0) * pt0.getX() + t0 * pt1.getX());
        pt.setY((1 - t0) * pt0.getY() + t0 * pt1.getY());
        return pt;
    }
	/**
	 * 
	 * @param num
	 * @param cx
	 * @param cy
	 * @param widthRange
	 * @param heightRange
	 * @return
	 */
	public static String createDynamicPoints(int pointNum, double cx, double cy, double widthRange, double heightRange)
    {
        int count = pointNum;
        double x = 0;
        double y = 0;
        if(random == null) random = new Random();
        StringBuilder info = new StringBuilder();
        info.append("{");
        info.append("\"geometryType\" : \"esriGeometryPoint\",\"spatialReference\" : {\"wkid\" : 4326},\"features\" : [");
        for(int i = 0; i < count; i++){
        	x = random.nextInt((int)widthRange) - widthRange / 2 + cx + random.nextDouble();
            y = random.nextInt((int)heightRange) - heightRange / 2 + cy + random.nextDouble();
            if(i > 0){
            	info.append(",");
            }
            info.append("{\"attributes\" : {");
            info.append("\"id\": \"");
            info.append(i);
            info.append("\",");
            info.append("\"time\": \"");
            info.append(df.format(new Date()));
            info.append("\"},");
            info.append("\"geometry\" : { \"x\" : ");
            info.append(x);
            info.append(", \"y\" : ");
            info.append(y);
            info.append(" }}");
        }
        info.append("]}");
        return info.toString();
    }
	
	/**
	 * 
	 * @param requestString
	 * @return
	 */
	public static Hashtable<String, String> parseQueryString(String requestString){
    	Hashtable<String, String> keyValColl = new Hashtable<String, String>();
    	if(requestString != null ){
    		requestString = requestString.trim();
    		String[] items = requestString.split("&");
    		for(int i = 0; i < items.length; i++){
    			String item = items[i];
    			String[] kvs = item.split("=");
    			String key = kvs[0];
				String value = kvs.length > 1 ? kvs[1] : "";
				value = value == null? "" : value.trim();;
				if(key != null){
					key = key.trim();
					if(key.length() > 0){
						keyValColl.put(key, value);
					}
				}
    		}
    	}
    	return keyValColl;
    }
	/**
	 * 
	 * @param polyline
	 * @return
	 */
	public static String ConverPolylineJson(PolylineN polyline)
	{
		String strPolyline = "{ \"paths\" : [";
		String strPath = "";
		for(Path path : polyline.getPathArray())
		{
			if (strPath != "")
			{
				strPath += ",";
			}
			strPath += "[";
			String strPts = "";
			for (Point point : path.getPointArray())
			{
				if (strPts != "")
				{
					strPts += ",";
				}
				PointN pt = (PointN)point;
				strPts += "[" + pt.getX() + "," + pt.getY() + "]";
			}
			strPath += strPts;
			strPath += "]";
		}
		strPolyline += strPath;
		strPolyline += "]}";
		return strPolyline;
	}
	/**
	 * 
	 * @param polygon
	 * @return
	 */
	public static String ConverPolygonJson(PolygonN polygon)
	{
		String tt = "{ \"rings\" : [";
		String strRings = "";
		for (Ring ring : polygon.getRingArray())
		{
			if (strRings != "")
			{
				strRings += ",";
			}
			strRings += "[";
			String strPts = "";
			for (Point point : ring.getPointArray())
			{
				if (strPts != "")
				{
					strPts += ",";
				}
				PointN pt = (PointN)point;
				strPts += "[" + pt.getX() + "," + pt.getY() + "]";
			}
			strRings += strPts;
			strRings += "]";
		}
		tt += strRings;
		tt += "]}";
		return tt;
	}
	/**
	 * 
	 * @param dsName
	 * @param tableName
	 * @param list
	 * @return
	 */
	public static String getXml(String dsName, String tableName, List<Hashtable<String, Object>> list){
    	String returnXml = "";
    	if(dsName == null || dsName.trim().length()==0){
    		dsName = "NewDataSet";
    	}
    	if(tableName == null || tableName.trim().length()==0){
    		tableName = "Table";
    	}
    	returnXml = "<" + dsName + ">";
    	if(list != null){
	    	int iCount = list.size();
	    	for(int i = 0; i < iCount; i++){
	    		returnXml += "<" + tableName + ">";
	    		Hashtable<String, Object> map = list.get(i);
	    		Enumeration<String> e = map.keys();
	    		while(e.hasMoreElements()){
	    			String key = e.nextElement();
	    			String value = map.get(key).toString();
	    			key = key.toUpperCase();
	    			returnXml += "<" + key + ">" + value +"</" + key + ">";
	    		}
	    		returnXml += "</" + tableName + ">";
	    	}
    	}
    	returnXml += "</" + dsName + ">";
    	return returnXml;
    }
	/**
	 * 
	 * @param dsName
	 * @param tableName
	 * @param rs
	 * @return
	 */
	public static String getXml(String dsName, String tableName, ResultSet rs){
    	String returnXml = "";
    	if(dsName == null || dsName.trim().length()==0){
    		dsName = "NewDataSet";
    	}
    	if(tableName == null || tableName.trim().length()==0){
    		tableName = "Table";
    	}
    	returnXml = "<" + dsName + ">";
    	if(rs != null){
			try {
				ResultSetMetaData metaData = rs.getMetaData();
				int colsCount = metaData.getColumnCount();
	    		String[] cols = new String[colsCount + 1];
	    		for(int i = 1; i <= colsCount; i++){
	    			cols[i] = metaData.getColumnName(i);
	    		}
	    		while(rs.next()){
	    			returnXml += "<" + tableName + ">";
	    			for(int i = 1; i <= colsCount; i++){
		    			String key = cols[i];
		    			String value = rs.getString(i);
		    			if(value == null){
		    				value = "";
		    			}
		    			key = key.toUpperCase();
		    			returnXml += "<" + key + ">" + value +"</" + key + ">";
	        		}
	    			returnXml += "</" + tableName + ">";
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	returnXml += "</" + dsName + ">";
    	return returnXml;
    }
	
}
