package com.wonders.wddc.tiles.calendar.at;

import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/kernel/calendar")
@IocBean(fields = "dao")
public class CalendarAct {

	private Dao dao;
	
	/**
    * 日历首页 
    */
   @At
   @Ok("jsp:wddc.tiles.calendar.index")
   public  Object toIndex(){
       //获取所有标签
   	Map<String,Object>  result= new HashMap<String,Object>();
      
       return result;
   }

}
