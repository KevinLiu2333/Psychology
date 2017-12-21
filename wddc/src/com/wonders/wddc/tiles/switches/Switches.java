package com.wonders.wddc.tiles.switches;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.log.Logs;

import com.wonders.wddc.tiles.switches.entity.SwitchInfoBo;


/**
 * 开关管理类
 * @author wonders
 *
 */
public class Switches {
	
	private static Map<String, Boolean> swMap = new HashMap<String,Boolean>();
	
	private static Switches instance = null;
	
	private Switches(){
		
	}
	
	/**
	 * 获取单例
	 * @return
	 */
	public static Switches getInstance(){
		if(instance == null){
			instance = new Switches();
		}
		return instance;

	}
	
	/**
	 * 获取开关状态,如果不存在该code的开关
	 * 或者该开关已被删除，则默认返回false
	 * @param code
	 * @return
	 */
	public boolean getState(String code){
		if(swMap.containsKey(code))
			return swMap.get(code);
		return false;
		
	}
	
	/**
	 * 改变开关状态
	 * @param info
	 */
	public void ChangeState(SwitchInfoBo info){
		addSwitch(info);
	}
	
	/**
	 * 添加开关
	 * @param info
	 */
	public void addSwitch(SwitchInfoBo info){
		boolean state = false;
		if("1".equals(info.getValue()))
			state = true;
		swMap.put(info.getCode(), state);
	}
	
	/**
	 * 移除开关
	 * @param info
	 */
	public void removeSwitch(SwitchInfoBo info){
		swMap.remove(info.getCode());
	}
	
	/**
	 * 初始化加载
	 * @param switchs
	 */
	public void loadSwitch(List<SwitchInfoBo> switchs){
		for(SwitchInfoBo infoBo : switchs){
			addSwitch(infoBo);
		}
		Logs.get().info("开关配置初始化完毕！");
	}

}
