package com.klsw.website.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.klsw.common.mall.model.TMallNews;
import com.klsw.website.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 新闻动态入口
 *
 * @author liukun
 */
@Controller
@RequestMapping("/")
public class NewsController extends _BaseController {


    @Autowired
    private NewsService newsService;
    static final int PAGESIZE = 10;
    static final int HOTESTPAGESIZE = 5;
    static final int LATESTPAGESIZE = 5;

    /**
     * 新闻主页面
     *
     * @param request
     * @return
     */
    @RequestMapping("news")
    public ModelAndView newsList(Model model, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("news/news");
        List<TMallNews> hotestList = null;
        List<TMallNews> list = null;

        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            PageHelper.startPage(1, PAGESIZE);
        } else {
            PageHelper.startPage(Integer.parseInt(pageNum), PAGESIZE);
        }

        PageHelper.orderBy("ctime desc");
        try {
            list = newsService.selectAll();
            PageHelper.startPage(1, HOTESTPAGESIZE);
            hotestList = newsService.selectAll();
        } catch (Exception e) {

        }
        PageInfo<TMallNews> pageInfo = new PageInfo<>(list);
        PageInfo<TMallNews> hotestPageInfo = new PageInfo<>(hotestList);
        mav.addObject("newsList", pageInfo);
        mav.addObject("hotestNewsList", hotestPageInfo);

        return mav;
    }

    @RequestMapping("news/{newsId}")
    public ModelAndView news(HttpServletRequest request, @PathVariable("newsId") Integer newsId) {
        ModelAndView mav = new ModelAndView("news/news_info");
        List<TMallNews> latestList = null;
        TMallNews news = null;
        try {
            news = newsService.selectByPrimaryKey(newsId);
            PageHelper.orderBy("ctime desc");
            PageHelper.startPage(1, LATESTPAGESIZE);
            latestList = newsService.selectAll();
        } catch (Exception e) {
            return mav;
        }
        PageInfo<TMallNews> latestPageInfo = new PageInfo<>(latestList);
        mav.addObject("news", news);
        mav.addObject("latestNewsList", latestPageInfo);
        return mav;

    }


}
