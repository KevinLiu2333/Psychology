package com.klsw.crosswaylive.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.common.live.model.Ret;
import com.klsw.common.live.model.TbLiveopern;
import com.klsw.common.live.model.TbLiveuser;
import com.klsw.crosswaylive.service.OpernService;
import com.klsw.crosswaylive.util.MyFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 曲谱相关
 * Created by liukun on 2017/5/18.
 */
@RestController
@RequestMapping(value = "opern")
public class OpernController extends _BaseContoller {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpernController.class);
    @Resource
    private MyFileUtils myFileUtils;

    @Resource
    private OpernService opernService;

    /**
     * 教师上传曲谱列表
     *
     * @param userId 用户id
     * @param operns 曲谱
     * @return 上传结果
     */
    @RequestMapping("upload")
    public Ret uploadOpern(
            @RequestParam("userId") Integer userId,
            @RequestParam("file") MultipartFile[] operns) {
        try {
            TbLiveuser liveuser = userService.selectByPrimaryKey(userId);
            if (liveuser == null || !"tea".equals(liveuser.getType())) {
                return Ret.warn("您没有权限上传曲谱");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String packagePath = "teacher/opern/" + liveuser.getUsername() + "/" + sdf.format(new Date()) + "/";
            Ret ret = myFileUtils.uploadfile(operns, "image", packagePath);
            if ("S".equals(ret.getStatus())) {
                int i = 0;
                String paths[] = ret.getdata().toString().split(";");
                for (MultipartFile file : operns) {
                    TbLiveopern opern = new TbLiveopern();
                    opern.setCtime(new Date());
                    opern.setTitle(file.getName());
                    opern.setPath(paths[i++]);
                    opern.setUserid(userId);
                    opernService.insert(opern);
                }
                return Ret.success("S", "添加成功", null);
            }
        } catch (Exception e) {
            LOGGER.error("msg", e);
        }
        return Ret.error("添加失败");

    }

    /**
     * 查询用户曲谱列表
     *
     * @param userId   用户id
     * @param pageNum  页码数
     * @param pageSize 每页显示数目
     * @return 曲谱列表
     */
    @RequestMapping("list")
    public Ret list(@RequestParam("userId") Integer userId,
                    @RequestParam("pageNum") Integer pageNum,
                    @RequestParam("pageSize") Integer pageSize) {
        try {
            TbLiveuser liveuser = userService.selectByPrimaryKey(userId);
            if (liveuser == null || !"tea".equals(liveuser.getType())) {
                return Ret.warn("用户信息有误");
            }
            PageHelper.startPage(pageNum, pageSize);
            TbLiveopern liveopern = new TbLiveopern();
            liveopern.setUserid(userId);
            List<TbLiveopern> operns = opernService.select(liveopern);
            PageInfo<TbLiveopern> pageInfo = new PageInfo<>(operns);
            return Ret.success("pageInfo", pageInfo);
        } catch (Exception e) {
            LOGGER.error("msg", e);
            return Ret.error("查询失败");
        }

    }

}












