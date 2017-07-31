package com.wonders.sjic.at;

import com.wonders.sjic.IFCheckUtils;
import com.wonders.sjic.entity.HttpClientDTO;
import com.wonders.sjic.entity.InterfaceInfoBo;
import com.wonders.sjic.entity.InterfaceTransferBo;
import com.wonders.wddc.tiles.sn.SnCreator;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.ServerRedirectView;

import java.util.*;

/**
 * 接口管理
 * @author Wanda
 *
 */
@At("/sjic/jkgl")
@IocBean
public class JkglAct {
	
	@Inject
	private Dao dao;
	
	/**
	 * 首页
	 * @return
	 */
	@At
	@Ok("jsp:sjic.jkgl.index")
	public Map<String, Object> toIndex(){
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		List<InterfaceInfoBo> infoList = dao.query(InterfaceInfoBo.class,Cnd.where("status","=","1"));
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		for (InterfaceInfoBo  info: infoList) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			InterfaceTransferBo transferBo = dao.fetch(InterfaceTransferBo.class,info.getId());
			map.put("info", info);
			map.put("detail", transferBo);
			list.add(map);
		}
		result.put("list", list);
		return result;
	}
	
	/**
	 * 编辑&新增
	 * @param id
	 * @return
	 */
	@At
	@Ok("jsp:sjic.jkgl.edit_view")
	public Map<String, Object> edit(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(id)) {
			InterfaceInfoBo info = dao.fetch(InterfaceInfoBo.class,id);
			result.put("info", info);
		}
		return result;
	}
	
	/**
	 * 失效
	 * @param id
	 */
	@At
	public void deleteInfo(String id){
		InterfaceInfoBo infoBo = dao.fetch(InterfaceInfoBo.class,id);
		infoBo.setStatus("0");
		dao.update(infoBo);
	}
	
	/**
	 * @param info
	 * @return
	 * 保存修改
	 */
	@At
	public View toSaveInfo(@Param("::info.") InterfaceInfoBo info){
		if (Strings.isEmpty(info.getId())) {
			InterfaceTransferBo transferBo = new InterfaceTransferBo();
			info.setId(SnCreator.getInstance().getSN("sjic", 5, "JK"));
			info.setStatus("1");
			info.setUpdateTime(new Date());
			transferBo.setJkId(info.getId());
			transferBo.setTransferSuccess(0);
			transferBo.setTransferSum(0);
			transferBo.setTransferFailed(0);
			dao.insert(transferBo);
			dao.insert(info);
		}else{
			info.setUpdateTime(new Date());
			dao.update(info);
		}
		return new ServerRedirectView("/sjic/jkgl/toIndex");
	}
	
	/**
	 * @param id
	 * @return
	 * 测试连接
	 */
	@At
	@Ok("json")
	public HttpClientDTO checkUrl(String id){
		InterfaceInfoBo info = dao.fetch(InterfaceInfoBo.class,id);
		if(info!=null){
			HttpClientDTO status =IFCheckUtils.checkInterface(info.getType(),info.getAddress());
			return status;
		}
		return null;
		
	}
}
