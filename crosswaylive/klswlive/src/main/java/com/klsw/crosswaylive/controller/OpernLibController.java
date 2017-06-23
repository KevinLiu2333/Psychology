package com.klsw.crosswaylive.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.common.live.model.Ret;
import com.klsw.common.live.model.TbLivecollection;
import com.klsw.crosswaylive.service.OpernCollectionService;
import com.klsw.crosswaylive.service.OpernLibService;
import com.klsw.pianoCommon.api.model.DbOpern;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 曲谱库相关操作
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/1
 * Time: 10:04
 */
@RestController
@RequestMapping("opernlib")
public class OpernLibController {


    @Resource
    private OpernCollectionService collectionService;

    @Resource
    private OpernLibService opernLibService;

    /**
     * 用户收藏曲谱
     *
     * @param opernlibId 曲谱库id
     * @param userId     用户id
     * @return 收藏结果
     */
    @RequestMapping("collection")
    public Ret collection(@RequestParam("opernlibId") Integer opernlibId,
                          @RequestParam("userId") Integer userId) {
        TbLivecollection collection = new TbLivecollection();
        collection.setId(userId);
        collection.setOpernid(opernlibId);
        collection.setCtime(new Date());
        try {
            if (collectionService.insert(collection) == 1) {
                return Ret.success("S", "收藏成功", null);
            } else {
                return Ret.warn("您已经收藏过该曲谱");
            }
        } catch (Exception e) {
            return Ret.error("收藏失败");
        }
    }

    /**
     * @param collectionId 收藏id
     * @param userId       用户id
     * @return 删除结果
     */
    @RequestMapping("remove")
    public Ret remove(@RequestParam("collectionId") Integer collectionId,
                      @RequestParam("userId") Integer userId) {
        try {
            TbLivecollection collection = collectionService.selectByPrimaryKey(collectionId);
            if (collection == null || !collection.getUserid().equals(userId)) {
                return Ret.warn("删除失败");
            } else {
                collectionService.deleteByPrimaryKey(collectionId);
                return Ret.warn("删除成功");
            }
        } catch (Exception e) {
            return Ret.error("删除失败");
        }
    }

    /**
     * @param userId   用户id
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return 列表
     */
    @RequestMapping("list")
    public Ret list(@RequestParam("userId") Integer userId,
                    @RequestParam("pageNum") Integer pageNum,
                    @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DbOpern> opernList = opernLibService.getCollection(userId);
        PageInfo<DbOpern> pageInfo = new PageInfo<>(opernList);
        return Ret.success(pageInfo);
    }


}












