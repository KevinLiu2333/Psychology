package com.klsw.piano.controller;

import com.klsw.piano.service.TbCwkService;
import com.klsw.piano.service.TbGameBubbleService;
import com.klsw.pianoCommon.api.model.TbCwk;
import com.klsw.pianoCommon.api.model.TbGamebubble;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by liukun on 2016/12/12.
 * 泡泡游戏相关
 */
@Controller
@RequestMapping(value = "/game")
public class GameController {

    private final Logger logger = LoggerFactory.getLogger(GameController.class);
    @Resource
    private TbCwkService tbCwkService;

    @Resource
    private TbGameBubbleService tbGameBubbleService;

    /**
     * 获取泡泡游戏分数
     *
     * @param params 参数
     * @return 返回值
     */
    @RequestMapping(value = "/GetBubbleScore", method = RequestMethod.POST)
    @ResponseBody
    public Object GetBubbleScore(@RequestParam Map<String, String> params) {

        try {
            //威客用户名
            String cwkname = params.get("cwkname");
            if (StringUtils.isBlank(cwkname)) {
                return "1, cwkname is null";
            }
            TbCwk cwk = tbCwkService.findByName(cwkname);
            //威客用户不存在
            if (cwk == null) {
                return "2, cwk user does not exist";
            }
            TbGamebubble tbGamebubble = new TbGamebubble();
            tbGamebubble.setCwkid(cwk.getId());
            tbGamebubble = tbGameBubbleService.selectOne(tbGamebubble);
            //如果没有记录,则插入记录
            if (tbGamebubble == null) {
                tbGamebubble = new TbGamebubble();
                tbGamebubble.setCwkid(cwk.getId());
                tbGamebubble.setScore(0);
                tbGameBubbleService.insert(tbGamebubble);
            }
            return "0, ok, nickname=" + cwk.getNickname() + ", hightscore=" + tbGamebubble.getScore();
        } catch (Exception e) {
            logger.error("msg",e);
            return "3, unknown error: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/GetBubbleRanking", method = RequestMethod.POST)
    @ResponseBody
    public Object GetBubbleRanking(@RequestParam Map<String, String> params) {
        String cwkname = params.get("cwkname");
        try {
            List<TbGamebubble> rankings = tbGameBubbleService.selectInfoList();
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (TbGamebubble entity : rankings) {
                sb.append(i).append(",").append(entity.getName()).append(",").append(entity.getNickname()).append(",").append(entity.getScore()).append("|");
                i++;
            }
            //威客用户参数存在
            if (!StringUtils.isBlank(cwkname)) {
                TbCwk cwk = tbCwkService.findByName(cwkname);
                //如果能查出结果
                if (cwk != null) {
                    TbGamebubble tbGamebubble = tbGameBubbleService.selectByPrimaryKey(cwk.getId());
                    //如果没有记录在排行榜中,插入该条记录
                    if (tbGamebubble == null) {
                        tbGamebubble = new TbGamebubble();
                        tbGamebubble.setCwkid(cwk.getId());
                        tbGamebubble.setScore(0);
                        tbGameBubbleService.insert(tbGamebubble);
                    }
                    boolean flag = false;
                    for (TbGamebubble entity : rankings) {
                        if (entity.getCwkid().equals(cwk.getId())) {
                            flag = true;
                            break;
                        }
                    }
                    //如果排行列表不包括该用户
                    if (!flag) {
                        Integer rank = tbGameBubbleService.selectRank(tbGamebubble.getScore()) + 1;
                        sb.append(rank).append(",").append(cwk.getName()).append(",").append(cwk.getNickname()).append(",").append(tbGamebubble.getScore()).append("|");

                    }
                }
            }
            //返回最后字符串
            return sb.toString().substring(0, sb.length() - 1);

        } catch (Exception e) {
            logger.error("msg",e);
            return "3, unknown error: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/SetBubbleRanking", method = RequestMethod.POST)
    @ResponseBody
    public Object setBubbleRanking(@RequestParam Map<String, String> params) {
        try {
            String cwkname = params.get("cwkname");
            String scoreString = params.get("score");
            if (StringUtils.isBlank(cwkname)) {
                return "1, cwkname is null";
            }
            if (StringUtils.isBlank(scoreString) || !StringUtils.isNumeric(scoreString)) {
                return "2, scoreString is null";
            }
            Integer score = Integer.parseInt(scoreString);
            TbCwk tbCwk = tbCwkService.findByName(cwkname);
            if (tbCwk == null) {
                return "3, cwk user does not exist";
            }
            TbGamebubble tbGamebubble = tbGameBubbleService.selectByPrimaryKey(tbCwk.getId());
            if (tbGamebubble == null) {
                tbGamebubble = new TbGamebubble();
                tbGamebubble.setCwkid(tbCwk.getId());
                tbGamebubble.setScore(score);
                tbGameBubbleService.insert(tbGamebubble);
                return "0, ok";
            }
            tbGamebubble.setScore(score);
            tbGameBubbleService.updateByPrimaryKey(tbGamebubble);
            return "0, ok";
        } catch (Exception e) {
            logger.error("msg",e);
            return "3, unknown error: " + e.getMessage();
        }

    }

}
