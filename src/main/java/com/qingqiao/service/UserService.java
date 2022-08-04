package com.qingqiao.service;

import com.qingqiao.entity.User;
import com.qingqiao.utils.PageUtil;

import java.util.List;

public interface UserService {
    List<User> queryAll(PageUtil pageUtil,String mohu);

    User login(String username, String password);

    int getCount(String mohu);
}
