package com.klsw.klswWebService.controller;

import com.klsw.klswWebService.service.TbCwkVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by liukun on 2016/12/1.
 * 威客版本相关
 */
@Controller
@RequestMapping(value = "/cwkVersion")
public class CWKVersionController {

    @Resource
    private TbCwkVersionService tbCwkVersionService;

    @RequestMapping(value = "/getVersionInfo")
    @ResponseBody
    public Object getVersionInfo(@RequestParam Map<String, String> params) {
        String type = params.get("type");
        if (type == null) {
            return "fail";
        }
        return tbCwkVersionService.selectByType(type);
    }
}



