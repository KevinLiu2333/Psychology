package com.klsw.piano.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klsw.piano.service.UpdateService;
import com.klsw.pianoCommon.api.model.TbUpdateHistory;

/**
 * Created by liukun on 2017/3/28.
 * 升级相关
 */
@Controller
@RequestMapping(value = "Update")
public class UpdateController {
	
	private static final Logger log = LoggerFactory.getLogger(UpdateController.class);
	
	@Autowired
	private UpdateService updateService;

    @RequestMapping(value = "CheckUpdate")
    @ResponseBody
    public String CheckUpdate(HttpServletRequest request) {
    	String versionNo = request.getParameter("VersionNo");
    	String uid = request.getParameter("Uid");
    	
    	try {
        	TbUpdateHistory updateHistory = new TbUpdateHistory();
        	updateHistory.setVersionNo(versionNo);
        	updateHistory.setUid(uid);
    		
        	if(updateService.select(updateHistory).isEmpty()) {
        		updateHistory.setCtime(new Date());
        		updateService.insert(updateHistory);
        	}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("msg", e);
		}
    	
        return "Fail:error";
    }
}
