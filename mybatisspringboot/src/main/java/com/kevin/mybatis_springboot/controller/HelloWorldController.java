package com.kevin.mybatis_springboot.controller;

import com.kevin.common.mapper.TbCwkMapper;
import com.kevin.common.mapper.TbUpdateHistoryMapper;
import com.kevin.common.model.Ret;
import com.kevin.common.model.TbCwk;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/13
 * Time: 10:10
 */
@Api(value = "swagger测试", description = "swagger测试用例")
@RestController
public class HelloWorldController {
    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @Resource
    private TbCwkMapper cwkMapper;

    @Resource
    private TbUpdateHistoryMapper mapper;

    @Resource
    private RestTemplate restTemplate;

    @ApiOperation(value = "第一个接口", notes = "第一个接口")
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public Object helloWorld(@RequestParam("userId") @ApiParam(name = "userId", value = "用户Id") Integer userId) {
        TbCwk tbCwk = cwkMapper.selectByPrimaryKey(userId);
        return tbCwk;
    }

    @ApiOperation(value = "下载", notes = "下载apk")
    @RequestMapping(value = "download", method = RequestMethod.GET)
    @ResponseBody
    public Object download() {
        String url = "http://localhost:8021/getUserInfo?userId=8";
        Ret ret = restTemplate.getForObject(url, Ret.class);
        return ret;
    }

//    @RequestMapping("test")
//    @ResponseBody
//    public Object test() throws Exception {
//        File dir = new File("E:/log");
//        File[] files = dir.listFiles();
//        BufferedReader br = null;
//        StringBuilder sb = null;
//        String line = null;
//        String dateString = null;
//        String infos[] = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss", Locale.ENGLISH);
//        Date date = null;
//        String versionNo = null;
//        String uid = null;
//        for (File file : files) {
//            br = new BufferedReader(new FileReader(file));
//            while (true) {
//                line = br.readLine();
//                if (line == null) {
//                    break;
//                }
//                if (line.contains("VersionNo")) {
//                    TbUpdateHistory updateHistory = new TbUpdateHistory();
//                    dateString = line.substring(line.indexOf("[") + 1, line.indexOf("+") - 1);
//                    date = sdf.parse(dateString);
//                    updateHistory.setCtime(date);
//                    line = line.substring(line.indexOf("VersionNo"), line.indexOf("HTTP")-1);
//                    infos = line.split("&");
//                    versionNo = infos[0].substring(infos[0].indexOf("=") + 1);
//                    uid = infos[2].substring(infos[2].indexOf("=") + 1);
//                    updateHistory.setUid(uid);
//                    updateHistory.setVersionNo(versionNo);
//                    try {
//                        mapper.insert(updateHistory);
//                    } catch (Exception ignore) {
//                    }
//                }
//            }
//            br.close();
//        }
//        return "success";
//    }

}
