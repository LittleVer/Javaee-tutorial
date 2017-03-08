package com.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entity.User;
import com.system.dao.UserDao;
import com.system.service.AccountBiz;
import com.util.PasswordHelper;

/**
 * Created by c0de8ug on 16-2-14.
 */
@Service
public class AccountBizImpl implements AccountBiz {

    @Resource
    UserDao userDao;
    
    @Resource
    private PasswordHelper passwordHelper;

    @Override
    public User findByIdAndPassword(String username, String password) {
        return userDao.findByIdAndPassword(username, password);
    }


    @Override
    public void updatePassword(String id, String password) {
    	User user = userDao.findById(id);
    	password = passwordHelper.encryptPassword(password,user.getCredentialsSalt());
    	userDao.updatePassword(id, password);
    }
}
