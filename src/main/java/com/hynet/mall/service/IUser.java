package com.hynet.mall.service;

import com.hynet.mall.exception.MallException;
import com.hynet.mall.model.pojo.User;

import java.security.NoSuchAlgorithmException;

public interface IUser {

    //获取用户信息
    User getUser(Integer userId);

    //注册
    void register(String username,String password) throws MallException;

    //登录
    User login(String username, String password) throws NoSuchAlgorithmException;

    void updateUserInfo(User currentUser);

    //管理员登录
    User adminLogin(String username,String password) throws NoSuchAlgorithmException;
}
