package com.klsw.piano.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.klsw.piano.service.MusicService;
import com.klsw.piano.util.Constants;
import com.klsw.piano.util.MyFileUtils;
import com.klsw.pianoCommon.api.model.Ret;
import com.klsw.pianoCommon.api.model.TbMusic;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/music")
public class MusicController {

    private static final Logger logger = LoggerFactory.getLogger(MusicController.class);
    private static final String BAIDU_MUSIC_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting?";

    @Resource
    private MusicService musicService;

    @Resource
    private MyFileUtils fileUtils;


    /********************************  接口部分   ***********************************/

    /**
     * 接口：通过参数歌名、歌手获取对应的在线音乐列表
     *
     * @param request 请求
     * @return 请求结果
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "getSongList")
    @ResponseBody
    public Ret getSongList(HttpServletRequest request) {

        List<Map<String, String>> mapList = Lists.newArrayList();

        //获取参数
        String query = request.getParameter("query");

        try {
            //调用百度音乐搜索接口并获取响应信息
            Map<String, String> map1 = new HashMap<>();
            map1.put("format", "json");
            map1.put("callback", "");
            map1.put("from", "webapp_music");
            map1.put("method", "baidu.ting.search.catalogSug");
            map1.put("query", query);
            List<String> list1 = Lists.newArrayList("format", "callback", "from", "method", "query");
            Ret ret = getResponse(BAIDU_MUSIC_URL, list1, map1);

            //如果从百度音乐搜索接口没有获取到数据，先将"*"替换成"的"再搜索一次，如果搜不到，再到自定义歌曲库进行查找
            if ("E".equals(ret.getStatus())) {
                String query2 = query.replace("*", "的");
                map1 = new HashMap<>();
                map1.put("format", "json");
                map1.put("callback", "");
                map1.put("from", "webapp_music");
                map1.put("method", "baidu.ting.search.catalogSug");
                map1.put("query", query2);
                ret = getResponse(BAIDU_MUSIC_URL, list1, map1);

                if ("E".equals(ret.getStatus())) {
                    Ret ret2 = getSongByCustom(query);
                    if ("E".equals(ret2.getStatus())) {
                        return getSongByCustom(query2);
                    } else {
                        return getSongByCustom(query);
                    }
                }
            }

            //调用百度音乐播放接口并获取响应信息
            Object object = ret.getdata();
            List<String> songIdList = (List<String>) object;
            Map<String, String> map2 = null;
            List<String> list2 = Lists.newArrayList("format", "callback", "from", "method", "songid");
            for (int i = 0; i < songIdList.size(); i++) {
                map2 = new HashMap<>();
                map2.put("format", "json");
                map2.put("callback", "");
                map2.put("from", "webapp_music");
                map2.put("method", "baidu.ting.song.play");
                map2.put("songid", songIdList.get(i));
                ret = getResponse(BAIDU_MUSIC_URL, list2, map2);
                mapList.add(i, (Map<String, String>) ret.getdata());
            }
            logger.info("歌曲播放信息：" + mapList.get(0).toString());
            return Ret.success(mapList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("msg", e);
            return Ret.error(e.getMessage());
        }
    }


    /***********************************  后台部分   ***********************************/


    /**
     * 后台：获取自定义歌曲库列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "songList")
    public String songList(HttpServletRequest request, Model model) {

        String pageNum = request.getParameter("pageId");
        Integer pageId = 1;
        if (!StringUtils.isEmpty(pageNum)) {
            pageId = new Integer(pageNum);
        }

        try {
            //进行分页设置
            PageHelper.startPage(pageId, Constants.PAGE_SIZE);
            List<TbMusic> songList = musicService.selectAll();

            PageInfo<TbMusic> pageInfo = new PageInfo<>(songList);

            //绑定信息到页面
            model.addAttribute("songList", pageInfo);
            return "songList";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("msg", e);
            return null;
        }
    }

    /**
     * 后台：进入到上传界面
     *
     * @return
     */
    @RequestMapping(value = "toUpload")
    public String toUpload() {
        return "music";
    }


    /**
     * 后台：上传自定义在线歌曲
     *
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadSong")
    @ResponseBody
    public Ret uploadSong(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        TbMusic tbMusic = new TbMusic();

        //获取参数
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String albumTitle = request.getParameter("albumTitle");
        Date date = new Date();

        //判断参数
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(author)) {
            return Ret.warn("歌名或歌手不能为空");
        }
        if (file.isEmpty()) {
            return Ret.warn("文件还未上传");
        }

        try {
            //将文件上传至阿里云OSS
            Ret ret = fileUtils.uploadfile("/static/music/online/" + file.getOriginalFilename(), file, true);

            if ("S".equals(ret.getStatus())) {
                tbMusic.setTitle(title);
                tbMusic.setAuthor(author);
                tbMusic.setAlbumTitle(albumTitle);
                tbMusic.setCreateTime(date);
                tbMusic.setFileLink("http://klsw-piano.oss-cn-hangzhou.aliyuncs.com/static/music/online/" +
                        file.getOriginalFilename());

                try {
                    //文件上传成功，往数据库tb_music表插入一条记录
                    musicService.insert(tbMusic);
                    return Ret.success("S", "插入成功", null);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Ret.error(e.getMessage());
                }
            }

            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("msg", e);
            return Ret.error(e.getMessage());
        } catch (FileUploadException e) {
            e.printStackTrace();
            logger.error("msg", e);
            return Ret.error(e.getMessage());
        }
    }


    /**
     * 后台：进入音乐修改界面
     *
     * @return
     */
    @RequestMapping(value = "toModify")
    public String toModify(HttpServletRequest request, Model model) {
        //获取参数
        String songId = request.getParameter("songId");

        try {
            //获取数据
            TbMusic tbMusic = musicService.selectByPrimaryKey(Integer.valueOf(songId));

            //绑定数据到页面
            model.addAttribute("tbMusic", tbMusic);
            return "music_modify";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("msg", e);
            return null;
        }
    }

    /**
     * 后台：修改音乐歌名、歌手、专辑等信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "modify")
    @ResponseBody
    public Ret modify(HttpServletRequest request) {
        //获取参数
        String songId = request.getParameter("songId");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String albumTitle = request.getParameter("albumTitle");

        //判断参数
        if (StringUtils.isEmpty(title)) {
            return Ret.warn("歌曲名不能为空");
        }
        if (StringUtils.isEmpty(author)) {
            return Ret.warn("歌手名不能为空");
        }

        try {
            //进行更新
            TbMusic tbMusic = musicService.selectByPrimaryKey(Integer.valueOf(songId));
            tbMusic.setTitle(title);
            tbMusic.setAuthor(author);
            tbMusic.setAlbumTitle(albumTitle);

            if (musicService.updateByPrimaryKey(tbMusic) != 1) {
                return Ret.error("更新不成功");
            }

            return Ret.success("S", "更新成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("msg", e);
            return Ret.error(e.getMessage());
        }
    }


    /***********************************  方法部分   ***********************************/


    /**
     * 方法：获取解析过后的响应信息
     *
     * @param url  访问百度音乐的接口地址
     * @param list 访问接口需要的参数列表
     * @param map  参数名和值对应的哈希表
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static Ret getResponse(String url, List<String> list, Map<String, String> map) throws Exception {
        StringBuffer sb = new StringBuffer(url);

        //构造加密后的请求路径
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append("=").append(URLEncoder.encode(map.get(list.get(i)), "utf-8"));
            if (i != list.size() - 1) {
                sb.append("&");
            }
        }

        //模拟客户端发送请求
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(sb.toString());
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        httpClient.executeMethod(getMethod);
        String response = getMethod.getResponseBodyAsString();//获取原始的响应信息
        logger.info("原始响应信息：" + response);
        response = response.substring(response.indexOf("(") + 1, response.lastIndexOf(")"));
        logger.info("截取后的响应信息：" + response);
        //获取解析后的响应信息，并进行过滤
        if (map.get("method").equals("baidu.ting.song.play")) {
            return parseSongPlayResponse(response);
        } else {
            return parseCatalogSugResponse(response);
        }
    }

    /**
     * 方法：对通过访问百度音乐搜索接口得到的响应信息进行解析
     *
     * @param response 原始响应信息
     */
    public static Ret parseCatalogSugResponse(String response) throws Exception {
        List<String> songIdList = Lists.newArrayList();//定义歌曲id列表

        Object object = JSONObject.parse(response);
        logger.info("解析过后的响应信息" + object.toString());
        //将解析结果转换成Map对象
        @SuppressWarnings("unchecked")
        Map<String, Object> jsonMap = (Map<String, Object>) object;

        //获取歌曲id列表
        Object obj1 = jsonMap.get("song");
        if (obj1 == null) {
            return Ret.error("没有搜到该歌曲");
        }
        JSONArray jsonArray = new JSONArray(obj1.toString());

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                String jsonStr = jsonArray.get(i).toString();
                JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                String songId = jsonObject.getString("songid");
                songIdList.add(songId);//添加歌曲id
            }
        }

        return Ret.success(songIdList);
    }

    /**
     * 方法：对通过访问百度音乐播放接口得到的响应信息进行解析
     *
     * @param response 原始响应信息
     */
    public static Ret parseSongPlayResponse(String response) throws Exception {

        Object object = JSONObject.parse(response);
        logger.info("解析过后的响应信息" + object.toString());
        //将解析结果转换成Map对象
        @SuppressWarnings("unchecked")
        Map<String, Object> jsonMap = (Map<String, Object>) object;

        //获取歌曲相关信息
        Object obj1 = jsonMap.get("songinfo");
        Object obj2 = jsonMap.get("bitrate");
        @SuppressWarnings("unchecked")
        Map<String, Object> map1 = (Map<String, Object>) obj1;
        @SuppressWarnings("unchecked")
        Map<String, Object> map2 = (Map<String, Object>) obj2;

        Map<String, String> map = new HashMap<>();
        map.put("title", map1.get("title").toString());
        map.put("author", map1.get("author").toString());
        map.put("album_title", map1.get("album_title").toString());
        map.put("pic_small", map1.get("pic_small").toString());
        map.put("file_link", map2.get("file_link").toString());

        return Ret.success(map);
  
    }

    /**
     * 方法：根据传入的搜索参数从数据库中查找歌曲并返回
     *
     * @param query
     * @return
     * @throws Exception
     */
    public Ret getSongByCustom(String query) throws Exception {
        List<Map<String, String>> mapList = Lists.newArrayList();
        TbMusic tbMusic = new TbMusic();
        TbMusic tbMusic2 = null;
        Map<String, String> map = null;

        String[] songInfoArray = query.split("\\*");
        if (songInfoArray.length == 1) {
            tbMusic.setTitle(songInfoArray[0]);
        } else if (songInfoArray.length == 2) {
            tbMusic.setTitle(songInfoArray[1]);
            tbMusic.setAuthor(songInfoArray[0]);
        } else {
            return Ret.error("参数有误");
        }

        List<TbMusic> musicList = musicService.select(tbMusic);

        if (musicList == null || musicList.isEmpty()) {
            return Ret.error("没有搜到该歌曲");
        }

        for (int i = 0; i < musicList.size(); i++) {
            map = new HashMap<>();
            tbMusic2 = musicList.get(i);
            map.put("title", tbMusic2.getTitle());
            map.put("author", tbMusic2.getAuthor());
            map.put("album_title", tbMusic2.getAlbumTitle());
            map.put("pic_small", "");
            map.put("file_link", tbMusic2.getFileLink());
            mapList.add(i, map);
        }
        return Ret.success(mapList);
    }


    /**
     * 工具方法：UNicode 转字符串
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);// 转换出每一个代码点
            string.append((char) data);// 追加成string
        }
        return string.toString();
    }

}
