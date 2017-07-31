package com.wonders.zymlgx.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.zymlgx.entity.ApplyNumber;

@IocBean
public class IncrementNumService {
	/**申请编号前缀**/
	public static final String APPLY_NUMBER_PREFIX = "APP_";
	/**资源申请ID名称**/
	public static final String ID_NAME = "ZYSQ";
	/**资源项排序**/
	public static final String RESOURCE_ORDER_NUM = "ZYPX";
	
	@Inject
	private Dao dao;
	
	/**
	 * 根据编号名称获得唯一序列编号，步长为1.
	 * @param idName
	 * 		编号名称
	 * @return
	 */
	public Long getNum(String idName){
        return getNum(idName, 1);
    }

	/**
	 * 根据编号名称获得唯一序列编号.
	 * 
	 * @param idName
	 * 		编号名称
	 * @param increment
	 * 		步长
	 * @return  取得编号
	 */
	private Long getNum(String idName, int increment) {
		ApplyNumber applyNumber = null;
		long nextId = 0;
		applyNumber = dao.fetch(ApplyNumber.class, idName);
		// 唯一序列对象存在则根据步长更新唯一序列号，否则新增唯一序列对象
		if(applyNumber != null){
			applyNumber.setCurrentValue(applyNumber.getCurrentValue() + increment);
			dao.update(applyNumber);
			nextId = applyNumber.getCurrentValue();
		}else{
			nextId = 1;
			applyNumber = new ApplyNumber();
			applyNumber.setIdName(idName);
			applyNumber.setCurrentValue(nextId);
			dao.insert(applyNumber);
		}
		
		return (new Long(nextId));
	}
}
