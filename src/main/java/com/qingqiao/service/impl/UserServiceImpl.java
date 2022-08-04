package com.qingqiao.service.impl;

import com.qingqiao.dao.UserDao;
import com.qingqiao.entity.User;
import com.qingqiao.service.UserService;
import com.qingqiao.utils.PageUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDao();

    @Override
    public List<User> queryAll(PageUtil pageUtil,String mohu) {
        return userDao.queryAll(pageUtil,mohu);
    }

    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }

    @Override
    public int getCount(String mohu) {
        return userDao.getCount(mohu);
    }
}
