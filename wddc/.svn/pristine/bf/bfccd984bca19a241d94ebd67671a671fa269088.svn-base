package com.wonders.wddc.suite.data.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;

import com.wonders.sjfw.platform.FwConstants;
import com.wonders.wddc.suite.data.entity.DataFileCustomJsonBo;
import com.wonders.wddc.suite.data.entity.DataFileDataBo;
import com.wonders.wddc.suite.data.entity.DataFileInfoBo;
import com.wonders.wddc.suite.data.entity.DataFileLinkBo;

import net.sf.json.JSONObject;
/**
 * 组装服务对外的核心的服务.
 *
 */
@IocBean
public class DataFileCoreService {

	@Inject
	DataCoreService dataCoreService;
	
	@Inject
	Dao dao;
	
	/**
	 * 生成组装服务结果，不缓存 .
	 * @param datafileid
	 * @param paramsMap
	 * @return
	 */
	public Map<String, Object> genDataFileResult(String datafileid,Map<String, String> paramsMap){
		//最终结果的map
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		//自定义指标
		List<DataFileCustomJsonBo> customList = dao.query(DataFileCustomJsonBo.class, Cnd.where("datafileid","=",datafileid));
		//自动定义的指标map
		Map<String, Object> customMap = new LinkedHashMap<String, Object>();

		//装载自定义指标
		for(DataFileCustomJsonBo link:customList){
			customMap.put(link.getCustomkey(), link.getCustomvalue());
		}
		
		//配置指标
		List<DataFileLinkBo> linkList = dao.query(DataFileLinkBo.class, Cnd.where("datafileid","=",datafileid));
		
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		Map<String, Object> resultMap= new LinkedHashMap<String, Object>();
		
		int countSum = 0;
		for (DataFileLinkBo link:linkList){
			data = (Map<String, Object>) dataCoreService.gainresult(link.getDataid() ,paramsMap, link.getType());
			countSum += Integer.parseInt((String)data.get(FwConstants.RS_MAP_COUNT));
			if ("ERROR".equals(data.get("ERROR"))) {
				result.put("ERRORDATA", data);
			}else {
				resultMap.put("custom_json",customMap);
				resultMap.put(link.getName(),data.get(FwConstants.RS_MAP_DATA));
			}
		}
		result.put(FwConstants.RS_MAP_DATA, resultMap);
		result.put(FwConstants.RS_MAP_COUNT, ""+countSum);
		
		return result;
		
	}
	
	/**
	 * 缓存到文件
	 * @param id
	 */
	public void createCacheType2(String id,Map<String, String> paramsMap){
		Map<String, Object> result = genDataFileResult(id,paramsMap);
		DataFileInfoBo dataFileInfoBo = dao.fetch(DataFileInfoBo.class, id);
		JSONObject json =JSONObject.fromObject(result);
		
		//获取文件路径
		File cacheFile = new File(dataFileInfoBo.getFilepath()+File.separator+dataFileInfoBo.getId()+".json");
		if(Strings.isNotBlank(dataFileInfoBo.getFilepath()) && dataFileInfoBo.getFilepath().endsWith(File.separator)){
			cacheFile = new File(dataFileInfoBo.getFilepath()+dataFileInfoBo.getId()+".json");
		}
        

		BufferedWriter writer = null;
		//创建目录
        File dirname = new File(dataFileInfoBo.getFilepath()); 
        if (!dirname.isDirectory())
        { //目录不存在
             dirname.mkdir(); //创建目录
        }  
        //如果文件不存在，则新建一个
        if(!cacheFile.exists()){
            try {
            	cacheFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer = new BufferedWriter(new FileWriter(cacheFile));
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 缓存到数据库
	 * @param id
	 */
	public void createCacheType1(String id,Map<String, String> paramsMap){

		Map<String, Object> result = genDataFileResult(id,paramsMap);
		result.remove("info");
		String jsonString =Json.toJson(result);
		
		DataFileDataBo fileDataBo = dao.fetch(DataFileDataBo.class,id);
		if (fileDataBo == null) {
			DataFileDataBo fileDataBo1 = new DataFileDataBo();
			fileDataBo1.setDatafileid(id);
			fileDataBo1.setUpdatetime(new Date());
			fileDataBo1.setData(jsonString);
			dao.insert(fileDataBo1);
		}else {
			fileDataBo.setData(jsonString);
			fileDataBo.setUpdatetime(new Date());
			dao.update(fileDataBo);
		}
		
	}
	
	/**
	 * 读取缓存文件内容.
	 * @param id
	 * @param cacheFile
	 * @return
	 */
	public Map<String, Object> getCacheType2(String id,File cacheFile){
		//最终结果的map
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		int countSum = 0;
		result.put(FwConstants.RS_MAP_DATA, JSONObject.fromObject(this.readfile(cacheFile)).get("DATA"));
		countSum ++;
		result.put(FwConstants.RS_MAP_COUNT, ""+countSum);
		return result;
	}
	
	/**
	 * 读取缓存到数据库的内容.
	 * @param resultDataCache
	 * @return
	 */
	public Map<String, Object> getCacheType1(DataFileDataBo resultDataCache){
		//最终结果的map
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		int countSum = 0;
		result.put(FwConstants.RS_MAP_DATA, JSONObject.fromObject(resultDataCache.getData()).get("DATA"));
		countSum ++;
		result.put(FwConstants.RS_MAP_COUNT, ""+countSum);
		return result;
	}
	
	/**
	 * 获取缓存文件路径.
	 * @param dataFileInfoBo
	 * @return
	 */
	public File getCacheFile(DataFileInfoBo dataFileInfoBo){
		File cacheFile = new File(dataFileInfoBo.getFilepath()+File.separator+dataFileInfoBo.getId()+".json");
		if(Strings.isNotBlank(dataFileInfoBo.getFilepath()) && dataFileInfoBo.getFilepath().endsWith(File.separator)){
			cacheFile = new File(dataFileInfoBo.getFilepath()+dataFileInfoBo.getId()+".json");
		}
		return cacheFile;
	}
	
	/**
	 * 获取缓存文件路径.
	 * @param dataFileInfoBo
	 * @return
	 */
	public File getCacheFile(String id){
		DataFileInfoBo dataFileInfoBo = this.getDataFileInfoBo(id);
		File cacheFile = new File(dataFileInfoBo.getFilepath()+File.separator+dataFileInfoBo.getId()+".json");
		if(Strings.isNotBlank(dataFileInfoBo.getFilepath()) && dataFileInfoBo.getFilepath().endsWith(File.separator)){
			cacheFile = new File(dataFileInfoBo.getFilepath()+dataFileInfoBo.getId()+".json");
		}
		return cacheFile;
	}
	
	/**
	 * 获取组装缓存的信息
	 * @param id
	 * @return
	 */
	public DataFileDataBo getDataFileDataBo(String id){
		return dao.fetch(DataFileDataBo.class, id);
	}
	
	/**
	 * 获取组装对象的信息
	 * @param id
	 * @return
	 */
	public DataFileInfoBo getDataFileInfoBo(String id){
		return dao.fetch(DataFileInfoBo.class, id);
	}
	
	/**
	 * 
	 *读取文件信息.
	 * @param file
	 * @return
	 */
	private String readfile(File file){
	    String str="";
	    try {
	        FileInputStream in=new FileInputStream(file);
	        // size  为字串的长度 ，这里一次性读完
	        int size=in.available();
	        byte[] buffer=new byte[size];
	        in.read(buffer);
	        in.close();
	        str=new String(buffer,"GB2312");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return str;
	}


}
