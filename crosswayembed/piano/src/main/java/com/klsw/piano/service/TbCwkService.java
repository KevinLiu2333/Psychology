package com.klsw.piano.service;

import com.klsw.piano.util.Constants;
import com.klsw.piano.util.PasswdEncryption;
import com.klsw.piano.util._StringUtils;
import com.klsw.pianoCommon.api.mapper.TbCwkMapper;
import com.klsw.pianoCommon.api.model.Ret;
import com.klsw.pianoCommon.api.model.TbCwk;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TbCwkService extends _BaseService<TbCwk> {

    private final Logger logger = LoggerFactory.getLogger(TbCwk.class);
    @Resource
    private TbCwkMapper tbCwkMapper;


    /**
     *
     * @Description: 通过用户名和类型查询用户id
     * @param @param username
     * 				用户名
     * @param @param type
     * 				用户类型
     * @param @return
     * @return Integer
     * @throws
     * @author LiuKun
     * @date 2016年8月25日
     */
//	public Integer selectId(String username,String type){
//		return tbCwkMapper.selectId(username, type);
//	}

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return TbCwk
     * @author LiuKun
     * @date 2016年8月26日
     */
    public TbCwk findByName(String username) {
        TbCwk tbCwk = new TbCwk();
        tbCwk.setName(username);
        try {
            tbCwk = super.selectOne(tbCwk);
        } catch (Exception e) {
            logger.error("msg",e);
        }
        return tbCwk;
    }


    /**
     * 将生成的token存入数据库
     *
     * @param username 用户名
     * @param token    token
     */
    public void saveToken(String username, String token) {
        TbCwk tbCwk = findByName(username);
        tbCwk.setToken(token);
        tbCwkMapper.updateByPrimaryKey(tbCwk);
    }

    /**
     * 生成ticket并保存
     *
     * @param username  用户名
     * @param timestamp 时间戳
     * @return
     */
    public String saveTicket(String username, String timestamp) {
        TbCwk tbCwk = findByName(username);
        String ticket = PasswdEncryption.createTicket(timestamp, username);
        tbCwk.setTicket(ticket);
        tbCwk.setLogintimestamp(Long.parseLong(timestamp));
        tbCwkMapper.updateByPrimaryKey(tbCwk);
        return ticket;
    }

    /**
     * 验证用户登录
     *
     * @param timestamp 时间戳
     * @param username  用户名
     * @param password  密码
     * @return
     * @throws Exception
     */
    public Ret userLogin(String timestamp, String username, String password) throws Exception {
        TbCwk tbCwk = findByName(username);
        if (tbCwk == null) {
            return Ret.warn("用户名或用户类型错误");
        }
        Integer loginfail = tbCwk.getLoginfail() == null ? 0 : tbCwk.getLoginfail();
        if (loginfail == 5) {
            Date locktime = tbCwk.getLocktime();
            Date date = new Date();
            //如果在锁定时间内,返回警告
            if (date.getTime() - locktime.getTime() < Constants.LOCKTIME) {
                return Ret.warn("您登陆失败次数过多，请稍后再试");
            }
            //如果已解锁
            tbCwk.setLoginfail(0);
            //更新数据库
            super.updateByPrimaryKey(tbCwk);
        }

        //检查账号密码
        boolean flag = PasswdEncryption.checkPasswd(
                tbCwk.getName(), tbCwk.getPwd(), tbCwk.getToken(), timestamp, password);
        //账号密码验证通过
        if (flag) {
            //存入ticket和登录时间
            String ticket = saveTicket(username, timestamp);
            tbCwk.setTicket(ticket);
            //返回ticket和成功状态
            return Ret.success(tbCwk);


        }
        tbCwk.setLoginfail(tbCwk.getLoginfail() + 1);
        tbCwk.setToken(_StringUtils.getToken(tbCwk.getPwd()));
        //如果已经失败5次,添加上锁时间
        if (tbCwk.getLoginfail() + 1 == 5) {
            tbCwk.setLocktime(new Date());

        }
        //更新数据库
        super.updateByPrimaryKey(tbCwk);

        return Ret.warn("账号或密码错误");

    }

    /**
     * ticket验证
     *
     * @return
     */
    public Ret checkTicket(HttpServletRequest request) {
        String username = request.getParameter("username");
        String type = request.getParameter("type");
        String ticket = request.getParameter("ticket");
        if (StringUtils.isBlank(username)) {
            return Ret.warn("用户名不能为空");
        }
        if (StringUtils.isBlank(type)) {
            return Ret.warn("用户类型不能为空");
        }
        if (StringUtils.isBlank(ticket)) {
            return Ret.warn("ticket不能为空");
        }
//			TbCwk tbCwk = findByName(username,type);
//			if(tbCwk==null){
//				return Ret.warn("用户信息有误");
//			}
//			if(tbCwk.getTicket().equals(ticket)){
//				return Ret.success(tbCwk.getId());
//			}

        return Ret.warn("ticket错误");

    }


}

