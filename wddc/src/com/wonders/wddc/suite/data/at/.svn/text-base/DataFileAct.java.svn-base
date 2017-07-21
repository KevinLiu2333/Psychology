package com.wonders.wddc.suite.data.at;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import com.wonders.sjfw.platform.FwConstants;
import com.wonders.wddc.suite.data.entity.DataFileCustomJsonBo;
import com.wonders.wddc.suite.data.entity.DataFileDataBo;
import com.wonders.wddc.suite.data.entity.DataFileInfoBo;
import com.wonders.wddc.suite.data.entity.DataFileLinkBo;
import com.wonders.wddc.suite.data.entity.MultStatInfoBo;
import com.wonders.wddc.suite.data.service.DataCoreService;
import com.wonders.wddc.tiles.sn.SnCreator;

/**
 * 用于将数据缓存存储于文件的组件
 * @author wonders
 *
 */
@At("/suite/data/df")
@IocBean
public class DataFileAct {

	@Inject
	private Dao dao;
	@Inject
	private DataCoreService service;
	
	/**
	 * 首页
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.df.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		List<DataFileInfoBo> list = dao.query(DataFileInfoBo.class, null);
		result.put("list", list);
		return result;
	}
	/**
	 * 编辑
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.df.edit")
	public Map<String, Object> edit(String id){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		if(!Strings.isEmpty(id)){
			DataFileInfoBo info = dao.fetch(DataFileInfoBo.class,id);
			info.setDatas(dao.query(MultStatInfoBo.class, Cnd.where("info_id","=",id)));
			result.put("info", info);
		}
		return result;
	}
	
	
	
	/**文件缓存编辑
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:wddc.suite.data.df.infoedit")
	public Map<String, Object> toInfoEdit(String id){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		DataFileInfoBo info = null;
		if(!Strings.isEmpty(id)){
			info=dao.fetch(DataFileInfoBo.class,id);
			List<DataFileLinkBo> list =dao.query(DataFileLinkBo.class,Cnd.where("datafileid", "=", info.getId()));
			List<DataFileCustomJsonBo> customList =dao.query(DataFileCustomJsonBo.class,Cnd.where("datafileid", "=", info.getId()));
			result.put("info", info);
			result.put("list", list);
			result.put("customList", customList);
		}
		
		return result;
		
	}
	
	/**
	 * 保存文件缓存主体
	 * @param info
	 * @return
	 */
	@At
	public View toSavaInfo(@Param("::info.") DataFileInfoBo info){
		if (Strings.isEmpty(info.getId())) {
			info.setId(SnCreator.getInstance().getSN("csrq_report", 5, "F"));
		}
		dao.execute(Sqls.create("delete from suite_datafile_info where id='"+info.getId()+"'"));
		dao.execute(Sqls.create("delete from suite_datafile_link where datafileid='"+info.getId()+"'"));
		dao.execute(Sqls.create("delete from suite_datafile_customjson where datafileid='"+info.getId()+"'"));
		Collections.reverse(info.getFileList());
		Collections.reverse(info.getCustomList());
		for(DataFileLinkBo databo:info.getFileList()){
			DataFileLinkBo link = new DataFileLinkBo();
			link.setId(UUID.randomUUID().toString().replace("-", ""));// UUID
			link.setDatafileid(info.getId());   //关联主表ID
			link.setName(databo.getName());		//keyName
			link.setDataid(databo.getDatafileid());		//关联的ID
			link.setType(databo.getType());		//类型
			dao.insert(link);
		}
		for(DataFileCustomJsonBo customJsonBo:info.getCustomList()){
			DataFileCustomJsonBo link = new DataFileCustomJsonBo();
			link.setId(UUID.randomUUID().toString().replace("-", ""));// UUID
			link.setDatafileid(info.getId());
			if (customJsonBo.getCustomkey() != "" && customJsonBo.getCustomvalue() != "") {
				link.setCustomkey(customJsonBo.getCustomkey());
				link.setCustomvalue(customJsonBo.getCustomvalue());
				dao.insert(link);
			}
		}
		info.setUpdatetime(new Date());
		dao.insert(info);
		return new ServerRedirectView("/suite/data/df/toIndex");
		
	}
	
	/**
	 * 删除缓存实体
	 * @param id
	 */
	@At
	@Ok("json")
	public void deleteInfo(String id){
	    dao.delete(DataFileInfoBo.class, id);
		dao.execute(Sqls.create("delete from suite_datafile_link where datafileid='"+id+"'"));
		dao.execute(Sqls.create("delete from suite_datafile_customjson where datafileid='"+id+"'"));
	}
	
	/**
	 * 预览
	 * @param id
	 * @return
	 * 
	 */
	@At
	@Ok("jsp:wddc.suite.data.df.infoview")
	public Map<String, Object> viewInfo(String id){
		Map<String, Object> result =new LinkedHashMap<String, Object>();
		DataFileInfoBo fileinfo =dao.fetch(DataFileInfoBo.class,id);
		DataFileDataBo fileDataBo = dao.fetch(DataFileDataBo.class,fileinfo.getId());
		if(fileDataBo != null){
			result.put("data", fileDataBo);
			result.put("info",fileinfo);
			
		}else {
			result.put("info",fileinfo);
		}
		return result;
	}
	
	/**
	 * 缓存到本地文件
	 * @param id
	 * @return
	 * TODO 两种缓存方式
	 */
	@At
	@Ok("json")
	public boolean cacheData(String id){
		boolean flag = true;
		//根据id查询相关联的dataid进而查到相应的sql语句
		DataFileInfoBo fileinfo =dao.fetch(DataFileInfoBo.class,id);
		List<DataFileLinkBo> linklist =dao.query(DataFileLinkBo.class, Cnd.where("datafileid","=",fileinfo.getId()));
		List<DataFileCustomJsonBo> customList = dao.query(DataFileCustomJsonBo.class, Cnd.where("datafileid","=",id));
		Map<String, Object> data =new LinkedHashMap<String, Object>();
		Map<String, Object> customMap = new LinkedHashMap<String, Object>();
		if (customList.size() > 0) {
			for(DataFileCustomJsonBo customLink:customList){
				customMap.put(customLink.getCustomkey(), customLink.getCustomvalue());
			}
			data.put("custom_json", customMap);
		}
		for(DataFileLinkBo link:linklist){
			MultStatInfoBo statInfoBo = dao.fetch(MultStatInfoBo.class,link.getDataid());
			if (statInfoBo.getUpdatetime() == null) {
				data.put(link.getName(), "当前数据项含有动态字典无法缓存");
			}else {
				data.put(link.getName(), ((Map<String,Object>) service.gainresult(link.getDataid(), null, link.getType())).get(FwConstants.RS_MAP_DATA));
			}
		}
		Map<String, Object> jsonMap = new LinkedHashMap<String,Object>();
//		jsonMap.put(FwConstants.RS_MAP_DATA, data);
		jsonMap.putAll(data);
		JSONObject json =JSONObject.fromObject(jsonMap);
		//System.out.println(Json.toJson(jsonMap));
		//缓存json到数据库
		DataFileDataBo fileDataBo = dao.fetch(DataFileDataBo.class,id);
		if (fileDataBo == null) {
			DataFileDataBo fileDataBo1 = new DataFileDataBo();
			fileDataBo1.setDatafileid(id);
			fileDataBo1.setUpdatetime(new Date());
			fileDataBo1.setData(json.toString());
			dao.insert(fileDataBo1);
		}else {
			fileDataBo.setData(json.toString());
			fileDataBo.setUpdatetime(new Date());
			dao.update(fileDataBo);
		}
		char index = fileinfo.getFilename().charAt(fileinfo.getFilename().length()-1);
		char line = '\\';
		if(line!=index){
			fileinfo.setFilepath(fileinfo.getFilepath()+"\\");
		}
		BufferedWriter writer = null;
        File file = new File(fileinfo.getFilepath() + fileinfo.getFilename() + ".json");
        
        File dirname = new File(fileinfo.getFilepath()); 
        if (!dirname.isDirectory())
        { //目录不存在
             dirname.mkdir(); //创建目录
        }  
        //如果文件不存在，则新建一个
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
            	flag = false;
                e.printStackTrace();
            }
        }
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(json.toString());
        } catch (IOException e) {
        	flag = false;
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
		return flag;
	}
	
	/**
	 * 配置项是否被使用
	 * @param id
	 * @return
	 */
	@At
	@Ok("json")
	public boolean isUsing(String id){
		boolean isUsing = false;
		int count =dao.count(DataFileLinkBo.class,Cnd.where("dataid", "=", id));
		if(count>0){
			isUsing = true;
		}
		return isUsing;
		
	}
	
	/**
	 * 删除配置项
	 * @param id
	 */
	@At
	public void delete(String id){
		dao.delete(DataFileDataBo.class,id);
	}
	
}
