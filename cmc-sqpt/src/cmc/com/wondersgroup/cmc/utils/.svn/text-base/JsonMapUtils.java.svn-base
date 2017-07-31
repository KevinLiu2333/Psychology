package com.wondersgroup.cmc.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.cmc.utils.json.JsonDateProcessor;
import com.wondersgroup.framework.core.web.vo.JsonDateValueProcessor;
import com.wondersgroup.framework.core.web.vo.VOUtils;

public class JsonMapUtils {

	private static Log logger = LogFactory.getLog(JsonMapUtils.class);

	@SuppressWarnings({ "unchecked" })
	public static String getJsonFromXmlMap(HashMap<String, Serializable> jsonMap) {
		HashMap<String, Serializable> newJsonMap = new LinkedHashMap<String, Serializable>();
		for (Entry<String, Serializable> entry : jsonMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if(value ==null)
				continue;
			
			if (value instanceof Collection) {
				String trueKey = null;
				ArrayList<Map<String, Serializable>> jsonArrayCollection = new ArrayList<Map<String, Serializable>>();
				for (Object obj : (Collection<?>) value) {
					Map<String, Object> jsonMapLink = (Map<String, Object>) obj;
					for (String mapKey : jsonMapLink.keySet()) {
						if (mapKey != null && !mapKey.equals("null")) {
							if (trueKey == null) {
								trueKey = mapKey;
								jsonArrayCollection.add((HashMap<String, Serializable>) (jsonMapLink.get(mapKey)));
							}
							else {
								if (mapKey.equals(trueKey)) {
									jsonArrayCollection.add((HashMap<String, Serializable>) (jsonMapLink.get(mapKey)));
								}
							}

						}
					}
				}
				if (trueKey != null) {
					newJsonMap.put(trueKey, jsonArrayCollection);
				}
			}
			else {
				if (value instanceof Serializable) {
					newJsonMap.put(key, (Serializable) value);
				}
				else {
					logger.error("type [" + value.getClass() + "] must be implements Serializable.");
				}
				
			}
		}
		return VOUtils.getJsonData(newJsonMap);

	}

	@SuppressWarnings({ "unchecked" })
	public static void getMapDataFromJsonObject(JSONObject jsonObject, HashMap<String, Serializable> xmlMap) {
		for (Object entrySet : jsonObject.entrySet()) {
			Entry<String, Object> entry = (Entry<String, Object>) entrySet;
			String key = entry.getKey();
			Object value = entry.getValue();
			if (!JSONUtils.isNull(value)) {
				if ((value instanceof JSONArray)) {
					ArrayList<Map<String, Serializable>> jsonArrayCollection = new ArrayList<Map<String, Serializable>>();
					JSONArray jsonArray = (JSONArray) value;
					int size = jsonArray.size();
					for (int i = 0; i < size; i++) {
						JSONObject jsonArrayObj = (JSONObject) jsonArray.get(i);
						LinkedHashMap<String, Serializable> jsonArrayMap = new LinkedHashMap<String, Serializable>();
						for (Object arrayEntrySet : jsonArrayObj.entrySet()) {
							Entry<String, Object> arrayEntry = (Entry<String, Object>) arrayEntrySet;
							String arrayKey = arrayEntry.getKey();
							Object arrayValue = arrayEntry.getValue();
							if (arrayValue instanceof Serializable) {
								jsonArrayMap.put(arrayKey, (Serializable) arrayValue);
							}
							else {
								logger.error("type [" + arrayValue.getClass() + "] must be implements Serializable.");
							}
						}
						jsonArrayCollection.add(jsonArrayMap);
					}
					xmlMap.put(key + "s", jsonArrayCollection);
				}
				else {
					if (value instanceof JSONObject) {
						 LinkedHashMap<String, Serializable> xmlMaps = new LinkedHashMap<String, Serializable>();
						 JsonMapUtils.getMapDataFromJsonObject((JSONObject)value, xmlMaps);
						 xmlMap.put(key, xmlMaps);
					} else if (value instanceof Serializable) {
						xmlMap.put(key, (Serializable) value);
					} else {
						logger.error("type [" + value.getClass() + "] must be implements Serializable.");
					}
				}
			}
			else {
				xmlMap.put(key, null);
			}
		}

	}
	public static<R> void getMapDataFromJsonObject(List<R> list, String name, HashMap<String, Serializable> xmlMap) {
		ArrayList<HashMap<String, Serializable>> mlist = new ArrayList<HashMap<String, Serializable>>();
		
		for(int i = 0; i < list.size(); i++) {
			HashMap<String, Serializable> map = new HashMap<String, Serializable>();
			
			 JsonConfig jsonConfig = new JsonConfig();
		        jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateProcessor());
		        JSONObject jsonObject = JSONObject.fromObject(list.get(i), jsonConfig);
			JsonMapUtils.getMapDataFromJsonObject(jsonObject, map);
			mlist.add(map);
		}
		xmlMap.put(name, mlist);
		
	}
	@SuppressWarnings({ "unchecked" })
	public static void getMapDataFromJsonObject(String keyName, JSONArray jsonArray, HashMap<String, Serializable> xmlMap) {
		ArrayList<HashMap<String, Serializable>> jsonArrayCollection = new ArrayList<HashMap<String, Serializable>>();
		int size = jsonArray.size();
		for (int i = 0; i < size; i++) {
			JSONObject jsonArrayObj = (JSONObject) jsonArray.get(i);
			HashMap<String, Serializable> jsonMapLink = new LinkedHashMap<String, Serializable>();
			HashMap<String, Serializable> jsonArrayMap = new LinkedHashMap<String, Serializable>();
			for (Object arrayEntrySet : jsonArrayObj.entrySet()) {
				Entry<String, Object> arrayEntry = (Entry<String, Object>) arrayEntrySet;
				String arrayKey = arrayEntry.getKey();
				Object arrayValue = arrayEntry.getValue();
				
				if (arrayValue instanceof Serializable) {
					xmlMap.put(arrayKey, (Serializable) arrayValue);
				}
				else {
					logger.error("type [" + arrayValue.getClass() + "] must be implements Serializable.");
				}
			}
			jsonMapLink.put(keyName, jsonArrayMap);
			jsonArrayCollection.add(jsonMapLink);
		}
		xmlMap.put(keyName + "s", jsonArrayCollection);

	}

	public static JsonConfig getJsonConfig(String dateFormat) {
		JsonDateValueProcessor beanProcessor = new JsonDateValueProcessor();
		DateFormat df = new SimpleDateFormat(dateFormat);
		beanProcessor.setDateFormat(df);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, beanProcessor);
		return jsonConfig;
	}

	public static void getXmlMapFromObject(Object object, HashMap<String, Serializable> xmlMap, JsonConfig jsonConfig,
			String keyName) {
		if (object instanceof Collection || object instanceof List) {
			JSONArray jsonArray = JSONArray.fromObject(object, jsonConfig);
			JsonMapUtils.getMapDataFromJsonObject(keyName, jsonArray, xmlMap);
		}
		else {
			JSONObject jsonObject = JSONObject.fromObject(object, jsonConfig);
			JsonMapUtils.getMapDataFromJsonObject(jsonObject, xmlMap);
		}

	}

	public static void getXmlMapFromObject(Object object, HashMap<String, Serializable> xmlMap) {
		getXmlMapFromObject(object, xmlMap, getJsonConfig("yyyy-MM-dd"), null);
	}

	public static void getXmlMapFromList(String keyName, Object object, HashMap<String, Serializable> xmlMap) {
		getXmlMapFromObject(object, xmlMap, getJsonConfig("yyyy-MM-dd"), keyName);
	}
}
