package com.wonders.tiles.logger.service;

import java.lang.reflect.Field;
import java.util.Date;

import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.logger.annotation.ColLog;
import com.wonders.tiles.logger.entity.LoggerCol;

@IocBean(fields = "dao")
public class LoggerService {
	
	private Dao dao;
	
	public void logCol(String busId,Object newObj, Object oldObj,User sessionUser){
		try{
			Field[] fields = Class.forName(newObj.getClass().getName()).getDeclaredFields();
			//获取表的信息
			Table table = Class.forName(newObj.getClass().getName()).getAnnotation(Table.class);
			for (Field field : fields) {
				//获取日志的信息
				ColLog colLog = field.getAnnotation(ColLog.class);
				if(colLog != null){
					//获取字段信息
					Column column = field.getAnnotation(Column.class);
					field.setAccessible(true);
					Object newValue = field.get(newObj);
					Object oldValue = field.get(oldObj);
					if(newValue!= null && !newValue.equals(oldValue)){
						LoggerCol gzzOpLog = new LoggerCol();
						//是否是字典，如果是字典则翻译字典值
						if(Strings.isBlank(colLog.dic())){
				    		gzzOpLog.setNewValue(newValue.toString());
				    		gzzOpLog.setOldValue(oldValue.toString());
						}else{
				    		gzzOpLog.setNewValue(DicDataUtils.getInstance().getDicData(Integer.parseInt(colLog.dic()), (String)newValue));
				    		gzzOpLog.setOldValue(DicDataUtils.getInstance().getDicData(Integer.parseInt(colLog.dic()), (String)oldValue));
						}
						//根据变量设置参数值
			    		gzzOpLog.setLogCatalog(table.value());
			    		gzzOpLog.setOpCol(column.value());
			    		gzzOpLog.setOpContent(colLog.content());
			    		gzzOpLog.setOpTpye(colLog.type());
			    		//设置固定值
			    		gzzOpLog.setOpBusId(busId);
			    		gzzOpLog.setOpStatus("1");
			    		gzzOpLog.setOpTime(new Date());
			    		gzzOpLog.setOpUserId(sessionUser.getUserId());
			    		gzzOpLog.setOpUserName(sessionUser.getLogonName());
			    		dao.insert(gzzOpLog);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}  
	

}
