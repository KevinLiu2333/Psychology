package com.wonders.jh.at;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;


@At("/jh")
public class jhAt {

	/**
	 *
	 * @param session
	 */
	@At
	@Ok("jsp:jsp.jh.home")
	public void toHome(HttpSession session){
	}
	/**
	 *  交换节点监控
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.main")
	public Map<String, Object> toMain(){
		return null;
	}
	/**
	 * 交换节点监控 详情
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.detail")
	public Map<String, Object> toDetail(){
		return null;
	}
	/**
	 * 交换节点监控 房屋房产数据库
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.house")
	public Map<String, Object> toHouse(){
		return null;
	}
	/**
	 * 交换节点监控 人口数据库
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.people")
	public Map<String, Object> toPeople(){
		return null;
	}
	/**
	 * 交换节点监控 法人数据库
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.corporation")
	public Map<String, Object> toCorporation(){
		return null;
	}
	/**
	 * 交换节点监控 监控统计
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jh.jhjd.monitoring")
	public Map<String, Object> toMonitoring(){
		return null;
	}
}
