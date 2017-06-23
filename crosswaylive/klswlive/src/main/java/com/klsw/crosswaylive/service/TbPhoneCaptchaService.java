package com.klsw.crosswaylive.service;

import com.klsw.common.live.model.Ret;
import com.klsw.common.live.model.TbLivephonecaptcha;
import com.klsw.crosswaylive.util.PasswdEncryption;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by liukun on 2017/3/11.
 * 验证码
 */
@Service
public class TbPhoneCaptchaService extends _BaseService<TbLivephonecaptcha> {

    public Ret saveCaptcha(String username, String captcha) {
        TbLivephonecaptcha phonecaptcha = new TbLivephonecaptcha();
        phonecaptcha.setCaptcha(PasswdEncryption.storeCaptcha(username, captcha));
        phonecaptcha.setCreatetime(new Date());
        phonecaptcha.setPhonenum(username);
        try {
            insert(phonecaptcha);
            return Ret.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Ret.error("插入验证码失败");
        }
    }

    /**
     * 验证用户注册验证码是否匹配
     *
     * @param storeCaptcha 存储的验证码
     * @return 检测结果
     */
    public boolean selectBystoreCaptcha(String storeCaptcha, String phonenum) {
        TbLivephonecaptcha captcha = new TbLivephonecaptcha();
        captcha.setCaptcha(storeCaptcha);
        captcha.setPhonenum(phonenum);
        try {
            captcha = selectOne(captcha);
            if (captcha != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
