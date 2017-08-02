package com.wonders.wddc.suite.service.at;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.wddc.suite.service.entity.ForeignBo;
import com.wonders.wddc.tiles.sn.SnCreator;

/**
 * 对外服务act
 * @author wonders
 *
 */
@At("/suite/service/foreign")
@IocBean
public class ForeignAct {
	@Inject
	private Dao dao;
	private Log log = Logs.get();
	/**
	 * 首页
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.service.foreign.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result = new HashMap<String, Object>();
		List<ForeignBo> list = dao.query(ForeignBo.class, null);
		result.put("list", list);
		return result;
	}
	/**
	 * 编辑
	 * @param id 实体类id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.service.foreign.edit")
	public Map<String, Object> edit(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		if(null!=id){
			ForeignBo bo = dao.fetch(ForeignBo.class,id);
			result.put("bo", bo);
		}
		return result;
	}
	/**
	 * 保存
	 * @param bo
	 * @return
	 */
	@At
	public View save(@Param("::bo.") ForeignBo bo){
		if(bo!=null){
			if(Strings.isEmpty(bo.getId())){
				bo.setId(SnCreator.getInstance().getSN("foreign", 5, "FS"));
				dao.insert(bo);
			}else {
				dao.update(bo);
			}
		}
		return new ServerRedirectView("/suite/service/foreign/toIndex");
	}
	/**
	 * 删除
	 */
	@At
	public void delete(String id){
		ForeignBo bo = dao.fetch(ForeignBo.class,id);
		if(bo!=null){
			dao.delete(bo);
		}
	}
	/**
	 * 服务接口页面
	 * @param id
	 */
	@At
	@Ok("jsp:wddc.suite.service.foreign.page")
	public Map<String, Object> service(String id,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		Enumeration<?> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
            if("id".equals(paramName)){
            	continue;
            }
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                	data.put(paramName, paramValue);  
                }  
            }  
        }
		result.put("id", id);
		result.put("data", JSONObject.fromObject(data).toString());
		return result;
	}
	/**
	 * 获取HTML数据并返回。
	 * @param id
	 * @param request
	 * @return
	 */
	@At
	@Ok("json")
	public Object getData(String id,String data){
		ForeignBo bo = dao.fetch(ForeignBo.class,id);
		String url = bo.getUrl()+"?";
		JSONObject json = JSONObject.fromObject(data);
		Iterator<?> it = json.keys(); 
		while (it.hasNext()) {
			String key = (String) it.next();  
			String value = json.getString(key); 
			url=url+key+"="+value+"&";
		}
		log.info("url:"+url);
		try {
			String content="";
			URL address_url = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) address_url.openConnection();
			System.setProperty("sun.net.client.defaultConnectTimeout","30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            int response_code = connection.getResponseCode();
            if (response_code == HttpURLConnection.HTTP_OK) {
            	InputStream in = connection.getInputStream();
            	BufferedReader reader = new BufferedReader(new InputStreamReader(in, bo.getCharset()));
            	String line = null;
                while ((line = reader.readLine()) != null) {
                    content+=line;
                }
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("head", Jsoup.parse(content).head().html());
                result.put("body", Jsoup.parse(content).body().html());
                return result;
            }
		}catch (Exception e) {
			log.error(e);
		}
		return "";
	}
}
