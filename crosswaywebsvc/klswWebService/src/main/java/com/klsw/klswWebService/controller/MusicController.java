package com.klsw.klswWebService.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.klsw.apiCommon.api.model.Ret;
import jersey.repackaged.com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/music")
public class MusicController {

	private static final Logger logger = LoggerFactory.getLogger(MusicController.class);
	private static final String BAIDU_MUSIC_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?";

	/**
	 * 接口：通过参数歌名、歌手获取对应的在线音乐列表
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getSongList")
	@ResponseBody
	public Ret getSongList(HttpServletRequest request) {
		
		List<Map<String, String>> mapList = Lists.newArrayList();
		
		try {
			Map<String, String> map1 = new HashMap<>();
			map1.put("format", "json");
			map1.put("callback", "");
			map1.put("from", "webapp_music");
			map1.put("method", "baidu.ting.search.catalogSug");
			map1.put("query", request.getParameter("query"));
			List<String> list1 = Lists.newArrayList("format", "callback", "from", "method", "query");

			Ret ret = getResponse(BAIDU_MUSIC_URL, list1, map1);
			if(ret.getStatus().equals("E")) {
				return ret;
			}
			Object object = ret.getdata();
			List<String> songIdList = (List<String>) object;
			
			Map<String, String> map2 = null;
			List<String> list2 = Lists.newArrayList("format", "callback", "from", "method", "songid");
			for(int i=0; i<songIdList.size(); i++) {
				map2 = new HashMap<>();
				map2.put("format", "json");
				map2.put("callback", "");
				map2.put("from", "webapp_music");
				map2.put("method", "baidu.ting.song.play");
				map2.put("songid", songIdList.get(i));
				ret = getResponse(BAIDU_MUSIC_URL, list2, map2);
				mapList.add(i, (Map<String, String>) ret.getdata());
			}
			logger.info("歌曲播放信息：" + mapList.get(0).toString());
			return Ret.success(mapList);
		} catch (Exception e) {
			e.printStackTrace();
			return Ret.error("没有搜到该歌曲");
		}
	}
	
	
	/**
	 * 获取解析过后的响应信息
	 * @param url 访问百度音乐的接口地址
	 * @param list 访问接口需要的参数列表
	 * @param map 参数名和值对应的哈希表
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	public static Ret getResponse(String url, List<String> list, Map<String, String> map) throws Exception {
		StringBuffer sb = new StringBuffer(url);
		
		//构造加密后的请求路径
		for (int i=0; i<list.size(); i++) {
			sb.append(list.get(i)).append("=").append(URLEncoder.encode(map.get(list.get(i)), "utf-8"));
			if(i != list.size()-1) {
				sb.append("&");
			}
		}

		//模拟客户端发送请求
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(sb.toString());
		getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpClient.executeMethod(getMethod);
		String response = getMethod.getResponseBodyAsString();//获取原始的响应信息
		logger.info("原始响应信息：" + response);
		response = response.substring(response.indexOf("(")+1,response.lastIndexOf(")"));
		logger.info("截取后的响应信息：" + response);
		//获取解析后的响应信息，并进行过滤
		if(map.get("method").equals("baidu.ting.song.play")) {
			return parseSongPlayResponse(response);
		} else {
			return parseCatalogSugResponse(response);
		}
	}
	
	/**
	 * 百度音乐：对通过建议进行搜索得到的响应信息进行解析
	 * @param response 原始响应信息
	 */
	public static Ret parseCatalogSugResponse(String response) throws Exception {
		List<String> songIdList = Lists.newArrayList();//定义歌曲id列表
		
		Object object = JSONObject.parse(response);
		logger.info("解析过后的响应信息" + object.toString());
		//将解析结果转换成Map对象
		@SuppressWarnings("unchecked")
		Map<String, Object> jsonMap = (Map<String, Object>) object;
		
		//获取歌曲id列表
		Object obj1 = jsonMap.get("song");
		if(obj1 == null) {
			return Ret.error("没有搜到该歌曲");
		}
		JSONArray jsonArray = new JSONArray(obj1.toString());
		
		if(jsonArray != null) {
			for(int i=0; i<jsonArray.length(); i++) {
				String jsonStr = jsonArray.get(i).toString();
				JSONObject jsonObject = JSONObject.parseObject(jsonStr);
				String songId = jsonObject.getString("songid");
				songIdList.add(songId);//添加歌曲id
			}
		}
		
		return Ret.success(songIdList);
	}
	
	/**
	 * 百度音乐：对通过播放得到的响应信息进行解析
	 * @param response 原始响应信息
	 */
	public static Ret parseSongPlayResponse(String response) throws Exception {
		
		Object object = JSONObject.parse(response);
		logger.info("解析过后的响应信息" + object.toString());
		//将解析结果转换成Map对象
		@SuppressWarnings("unchecked")
		Map<String, Object> jsonMap = (Map<String, Object>) object;
		
		//获取歌曲相关信息
		Object obj1 = jsonMap.get("songinfo");
		Object obj2 = jsonMap.get("bitrate");
		@SuppressWarnings("unchecked")
		Map<String, Object> map1 = (Map<String, Object>) obj1;
		@SuppressWarnings("unchecked")
		Map<String, Object> map2 = (Map<String, Object>) obj2;
		
		Map<String, String> map = new HashMap<>();
		map.put("title", map1.get("title").toString());
		map.put("author", map1.get("author").toString());
		map.put("album_title", map1.get("album_title").toString());
		map.put("pic_small", map1.get("pic_small").toString());
		map.put("file_link", map2.get("file_link").toString());
		
		return Ret.success(map);
	}
	
	
	/**
	 * 工具方法：UNicode 转字符串
	 */
	public static String unicode2String(String unicode) {
	    StringBuffer string = new StringBuffer();
	    String[] hex = unicode.split("\\\\u");
	    for (int i = 1; i < hex.length; i++) {
	        int data = Integer.parseInt(hex[i], 16);// 转换出每一个代码点
	        string.append((char) data);// 追加成string
	    }
	    return string.toString();
	}
	
}
