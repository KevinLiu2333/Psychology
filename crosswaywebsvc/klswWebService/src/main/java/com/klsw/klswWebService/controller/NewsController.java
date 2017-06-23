package com.klsw.klswWebService.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.apiCommon.api.model.TbCwkfavorite;
import com.klsw.apiCommon.api.model.TbCwknews;
import com.klsw.klswWebService.service.FavoriteService;
import com.klsw.klswWebService.service.NewsService;
import com.klsw.klswWebService.service.TbCwkService;
import com.klsw.klswWebService.util.Constants;
import com.klsw.klswWebService.util.MyFileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * ClassName: NewsController
 *
 * @author LiuKun
 * @Description: 威客新闻相关接口
 * @date 2016年8月23日
 */
@RestController
@RequestMapping(value = "/news")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    public static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("text", 0);
        map.put("image", 1);
        map.put("media", 2);

    }

    @Autowired
    private MyFileUtils myFileUtils;

    @Autowired
    private NewsService newsService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private TbCwkService tbCwkService;

    /**
     * @param request  包含新闻上传的文件
     * @param type     用户类型
     * @param title    标题
     * @param contents 内容
     * @param dirName  上传文件类型
     * @param @return
     * @return Ret
     * @throws
     * @Description: 新闻上传
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Ret newsUplaod(HttpServletRequest request,
                          @RequestParam("type") String type,
                          @RequestParam("title") String title,
                          @RequestParam("contents") String contents,
                          @RequestParam("dirName") String dirName,
                          @RequestParam("file") MultipartFile[] fileList) {
        TbCwknews tbCwkNews = new TbCwknews();
        Date date = new Date();
        //参数验证
        if (StringUtils.isBlank(title)) {
            return Ret.warn("标题不能为空");
        }
        if (StringUtils.isBlank(contents)) {
            return Ret.warn("内容不能为空");
        }
        if (!map.containsKey(dirName)) {
            return Ret.warn("新闻类型错误");
        }
        try {
            Ret ret1 = tbCwkService.checkTicket(request);
            if (!"S".equals(ret1.getStatus())) {
                return ret1;
            }
            Integer id = (Integer) ret1.getdata();
            //接收上传的文件
            Ret ret2 = myFileUtils.uploadfile(fileList, dirName, Constants.NEWS_UPLOAD,Constants.wkBucket);
            //验证接收文件的过程是否成功
            String status = ret2.getStatus();
            //如果上传文件成功
            if ("S".equals(status)) {
                String data = (String) ret2.getdata();
                //将上传内容存入数据库
                //存入时间
                tbCwkNews.setAddtime(date);
                //存入新闻内容
                tbCwkNews.setContents(contents);
                //存入标题
                tbCwkNews.setTitle(title);
                //存入文件存储路径--判断文件类型
                if ("image".equals(dirName)) {
                    tbCwkNews.setImg(data);
                } else {
                    tbCwkNews.setVideo(data);
                }
                //存入上传人信息
                tbCwkNews.setUsertype(type);
                tbCwkNews.setCwkid(id);
                //存入其他信息
                tbCwkNews.setPraise(0);
                tbCwkNews.setViewcount(0);
                Integer newstype = map.get(dirName);
                tbCwkNews.setNewstype(newstype);
                tbCwkNews.setCollection(0);
                newsService.insertUseGeneratedKeys(tbCwkNews);
                return Ret.success("保存上传信息成功");
            }
            //如果不成功，返回不成功的原因
            return ret2;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("发布新闻失败");
        }
    }

    /**
     * @param username 用户名
     * @param type     用户类型
     * @param pageNum  页数
     * @param
     * @return Ret
     * @throws
     * @Description: 查询个人收藏新闻
     * @author LiuKun
     * @date 2016年8月23日
     */
    @ResponseBody
    @RequestMapping(value = "/favorite")
    public Ret favoriteNews(@RequestParam("username") String username,
                            @RequestParam("type") String type,
                            @RequestParam("pageNum") Integer pageNum) {
        try {
            PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
            List<TbCwknews> list = newsService.selectFavoriteNewsByTbCwkId(username, type);
            PageHelper.startPage(1, Constants.PAGE_SIZE);
            PageInfo<TbCwknews> pageInfo = new PageInfo<>(list);
            return Ret.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.warn("获取收藏新闻失败");
        }

    }

    /**
     * @param pageNum 页数
     * @param
     * @return Ret
     * @throws
     * @Description: 查询最热新闻
     * @author LiuKun
     * @date 2016年8月23日
     */
    @ResponseBody
    @RequestMapping(value = "/hottest")
    public Ret hotestNews(@RequestParam("pageNum") Integer pageNum) {
        try {
            PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
            List<TbCwknews> list = newsService.selectNewsOrderByAddtime();
            PageInfo<TbCwknews> pageInfo = new PageInfo<>(list);
            return Ret.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.warn("获取最热新闻失败");
        }

    }

    /**
     * @param pageNum 页数
     * @param
     * @return Ret
     * @throws
     * @Description: 最新新闻
     * @author LiuKun
     * @date 2016年8月23日
     */
    @ResponseBody
    @RequestMapping(value = "/latest")
    public Ret latestNews(@RequestParam("pageNum") Integer pageNum) {
        try {
            PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
            List<TbCwknews> list = newsService.selectNewsOrderByPraise();
            PageInfo<TbCwknews> pageInfo = new PageInfo<>(list);
            return Ret.success(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.warn("获取最新新闻失败");
        }

    }

    /**
     * @param newsId   新闻id
     * @param username 用户名
     * @param type     用户类型
     * @param @return
     * @return Ret
     * @throws
     * @Description: 收藏新闻
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/collectionNews")
    @ResponseBody
    public Ret collectionNews(@RequestParam("newsId") Integer newsId,
                              @RequestParam("username") String username,
                              @RequestParam("type") String type) {
        TbCwkfavorite tcf = new TbCwkfavorite();
        try {
            TbCwk tbCwk = tbCwkService.findByName(username, type);
            TbCwknews tbCwknews = newsService.selectByPrimaryKey(newsId);
            if (tbCwknews == null) {
                return Ret.warn("新闻编号错误");
            }
            Integer id = tbCwk.getId();
            tcf.setCwkid(id);
            tcf.setCwknewsid(newsId);
            List<TbCwkfavorite> tcf1 = favoriteService.select(tcf);
            if (tcf1.size() > 0) {
                return Ret.warn("您已收藏过该新闻");
            }
            favoriteService.insert(tcf);
            tbCwknews.setCollection(tbCwknews.getCollection() + 1);
            return Ret.success("S", "收藏新闻成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("收藏新闻失败");
        }
    }

    /**
     * @param newsId 新闻编号
     * @return Ret
     * @throws
     * @Description: 新闻详情
     * @author LiuKun
     * @date 2016年8月23日
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Ret newsDetails(@RequestParam("newsId") Integer newsId) {
        try {
            TbCwknews tbCwknews = newsService.selectByPrimaryKey(newsId);
            if (tbCwknews == null) {
                return Ret.warn("新闻编号错误");
            }
            return Ret.success(tbCwknews);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Ret.error("获取新闻详情失败");
        }
    }

}




