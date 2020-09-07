package com.hynet.mall.service.impl;

import com.hynet.mall.exception.MallErrorEnum;
import com.hynet.mall.exception.MallException;
import com.hynet.mall.model.dao.UserMapper;
import com.hynet.mall.model.pojo.User;
import com.hynet.mall.service.IUser;
import com.hynet.mall.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService implements IUser {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void register(String username, String password) throws MallException {
        //1. 通过username先在数据库中进行查询，保证数据库不存在此用户。
        User result = userMapper.selectByUsername(username);
        if (result != null) {
            //说明数据库中已经存在该用户，向上级controller层抛出一个异常
            throw new MallException(MallErrorEnum.USER_EXIST);
        }

        //如果可以，执行插入操作。
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        Integer count = userMapper.insertSelective(user);
        if (count == 0) {
            //插入失败,需要抛出异常
            throw new MallException(MallErrorEnum.INSERT_DATABASE_FAIL);
        }
    }

    @Override
    public User login(String username, String password) throws NoSuchAlgorithmException,MallException {
        String md5Password = MD5Utils.md5(password);
        //登录: 通过username 和 password同时在数据库中查找，如果能找到则说明当前用户存在并可以登录。
        User user = userMapper.selectLogin(username,md5Password);
        if (user == null) {
            throw new MallException(MallErrorEnum.PASSWORD_ERROR);
        }
        return user;
    }
    @Override
    public void updateUserInfo(User currentUser){

        Integer count = userMapper.updateByPrimaryKeySelective(currentUser);
        if (count > 1) {
            throw  new MallException(MallErrorEnum.UPDATE_USER_INFO_ERROR);
        }
    }

    @Override
    public User adminLogin(String username, String password) throws NoSuchAlgorithmException,MallException {
        System.out.println("password: "+ password);
        String encrypt = MD5Utils.md5(password);
        System.out.println("encrypt: " +encrypt);
        User user = userMapper.selectLogin(username,encrypt);
        if (user == null) {
            //密码错误。
            throw new MallException(MallErrorEnum.PASSWORD_ERROR);
        }
        if (user.getRole() != 2) {
            //不是管理员，
            throw new MallException(MallErrorEnum.NO_ADMIN_ROLE);
        }
        return user;
    }
}
