package com.klsw.piano.controller;

import com.klsw.piano.service.TbEventmidiService;
import com.klsw.piano.util.Constants;
import com.klsw.piano.util.FileUploadConfig;
import com.klsw.piano.util.MyFileUtils;
import com.klsw.pianoCommon.api.model.Ret;
import com.klsw.pianoCommon.api.model.TbEventmidi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;

/**
 * Created by liukun on 2016/12/30.
 * 特殊活动相关
 */
@Controller
@RequestMapping(value = "/event")
public class EventController {

    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Resource
    private ExecutorService executor;

    @Resource
    private MyFileUtils myFileUtils;

    @Resource
    private TbEventmidiService tbEventmidiService;

    @Resource
    private FileUploadConfig fileUploadConfig;

    /**
     * @param author    midi作者
     * @param eventname 活动名称
     * @param serialnum 钢琴序列号
     * @param versionno 版本号
     * @param rec       midi文件
     * @return 是否保存成功
     */
    @RequestMapping(value = "/uploadMidi")
    @ResponseBody
    public String uploadMidi(@RequestParam("author") String author,
                             @RequestParam("eventname") String eventname,
                             @RequestParam("serialnumber") String serialnum,
                             @RequestParam("versionno") String versionno,
                             @RequestParam("rec") MultipartFile rec,
                             @RequestParam("midname") String midname) {
        try {
            logger.info(author);
            logger.info(eventname);
            logger.info(versionno);
            logger.info(midname);
//            TbSpecialversion tbSpecialversion = tbSpecialVersionService.selectByPrimaryKey(eventno);
//            if (tbSpecialversion == null) {
//                return "1,该活动不存在";
//            }
            //存储mid模块
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSSS");
            String dirName = sdf.format(new Date());
//            eventname = eventname.replace("[ '\"`!%$()*\\:&]","");
            eventname = eventname.replace(" ", "");
            String pathUrl = "/static/event/" + eventname + "/" + dirName + "/";
            String pathForSaving = fileUploadConfig.getSavePath() + "/static/event/" + eventname + "/" + dirName + "/";
            //mid转MP3模块
            String midiPath = pathForSaving + "record.mid";
            Ret ret = myFileUtils.uploadfile(midiPath, rec, false);
            if (!"S".equals(ret.getStatus())) {
                return "fail";
            }

            final String mp3Path = pathForSaving + "record.mp3";
            final String fullPara = midiPath + " " + mp3Path;
            executor.execute(() -> {
                try {
                    int convert = myFileUtils.midtoMp3(fullPara);
                    logger.info("mid转mp3:" + convert);
                    File mp3 = new File(mp3Path);
                    if (convert == 0 && mp3.exists()) {
                        myFileUtils.uploadFileToOss(mp3, mp3Path.substring(mp3Path.lastIndexOf("static")), Constants.pianoBucket);
                        mp3.delete();
                    }
                } catch (Exception e) {
                    logger.error("msg",e);
                }
            });

            Thread.sleep(3000);
//            int convert = myFileUtils.midtoMp3(fullPara);
//            File mp3 = new File(mp3Path);
//            if (convert != 0) {
//                return "转换文件失败";
//            } else if (mp3.exists()) {
//                OssUtils.uploadFile(mp3, mp3Path.substring(mp3Path.lastIndexOf("static")), Constants.pianoBucket);
//                mp3.delete();
//            } else {
//                return "mp3文件不存在";
//            }
            Long timestamp = System.currentTimeMillis();
            TbEventmidi tbEventmidi = new TbEventmidi();
            tbEventmidi.setSerialnumber(serialnum);
            tbEventmidi.setAuthor(author);
            tbEventmidi.setMidpath(pathUrl + "record.mid");
            tbEventmidi.setMp3path(pathUrl + "record.mp3");
            tbEventmidi.setTimestamp(timestamp);
            tbEventmidi.setVersionno(versionno);
            tbEventmidi.setMidname(midname);
            tbEventmidi.setAddtime(new Date());
            if (tbEventmidiService.insertUseGeneratedKeys(tbEventmidi) != 1) {
                return "fail";
            }
            //二维码模块 /open/event/时间戳
            String shareUrl = Constants.WEIXIN_URL + "/open/event/" + timestamp.toString();
            String qrcodepDir = fileUploadConfig.getSavePath() + "/static/event/" + eventname + "/" + "qrcode/";
            String qrcodeUrl = Constants.WEIXINPIC_URL + "/static/event/" + eventname + "/" + "qrcode/" + timestamp.toString() + ".png";
            File dir = new File(qrcodepDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String qrcodePath = qrcodepDir + timestamp.toString() + ".png";
            ret = myFileUtils.createQRCode(shareUrl, qrcodePath, 288);
            if (!"S".equals(ret.getStatus())) {
                return "fail";
            }
            return "0," + qrcodeUrl;
        } catch (Exception e) {
            logger.error("msg",e);
            return "fail";
        }


    }
//
//    public static void main(String[] args) {
//        System.out.println("dsaa  dasda  ".trim());
//    }

}
