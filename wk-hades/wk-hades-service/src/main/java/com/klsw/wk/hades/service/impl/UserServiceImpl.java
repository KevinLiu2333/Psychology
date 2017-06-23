package com.klsw.wk.hades.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klsw.wk.hades.dao.UserMapper;
import com.klsw.wk.hades.domain.User;
import com.klsw.wk.hades.exception.ServiceException;
import com.klsw.wk.hades.service.AbstractService;
import com.klsw.wk.hades.service.IUserService;

@Service("userService")
public class UserServiceImpl extends AbstractService<User, Long> implements IUserService {

    @Autowired
    private UserMapper userDao;

  /*  @Autowired
    private EmailUtil emailUtil;
*/
    //这句必须要加上。不然会报空指针异常，因为在实际调用的时候不是BaseMapper调用，而是具体的mapper，这里为userMapper
    @Autowired
    public void setBaseMapper() {
        super.setBaseMapper(userDao);
    }

    /**
     * 重写用户插入，逻辑：
     * 1、插入用户
     * 2、插入用户和角色的对应关系
     * 3、插入用户的个人资料信息
     */
    public int insert(User userEntity, String password) {
        try {
            if (userDao.insert(userEntity) == 1) {
                if (userDao.insertUserRole(userEntity) == 1) {
                    //userEntity.getUserInfo().setId(userEntity.getId());
                    int cnt = userDao.insertUserInfo(userEntity);
                    //发送邮件
                    //  emailUtil.send126Mail(userEntity.getAccountName(), "系统消息通知", "您好,您的账户已创建,账户名:" + userEntity.getAccountName() + " ,密码:" + password);
                    return cnt;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    /**
     * 重写用户更新逻辑：
     * 1、更新用户
     * 2、更新用户和角色的对应关系
     * 3、更新用户个人资料信息
     */
    public int update(User userEntity) {
        try {
            if (userDao.update(userEntity) == 1) {
                if (userDao.updateUserRole(userEntity) == 1) {
                    return userDao.updateUserInfo(userEntity);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 重写用户删除逻辑：
     * 1、删除用户和角色的对应关系
     * 2、删除用户
     */
    public int deleteBatchById(List<Long> userIds) {
        try {
            int result = userDao.deleteBatchUserRole(userIds);
            if (result == userIds.size()) {
                return userDao.deleteBatchById(userIds);
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int updateOnly(User userEntity, String password) throws ServiceException {
        try {
            int cnt = userDao.update(userEntity);
            //发送邮件
           // emailUtil.send126Mail(userEntity.getAccountName(), "系统密码重置", "您好，您的密码已重置，新密码是：" + password);
            return cnt;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

	@Override
	public List<User> listUser() {
		return userDao.queryAllUser();
	}

}
