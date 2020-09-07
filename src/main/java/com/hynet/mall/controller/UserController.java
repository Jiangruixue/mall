package com.hynet.mall.controller;

/*
 * @progrm:
 * @description: 用户Controller
 * @auther:
 * @create:
 */

import com.hynet.mall.common.ApiRestResponse;
import com.hynet.mall.common.Constant;
import com.hynet.mall.exception.MallErrorEnum;
import com.hynet.mall.exception.MallException;
import com.hynet.mall.model.pojo.User;
import com.hynet.mall.service.IUser;
import com.hynet.mall.utils.MD5Utils;
import com.sun.tools.corba.se.idl.ConstEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@RestController
public class UserController {

    @Autowired
    IUser service;

    //获取用户信息
    @GetMapping({"/info"})
    public User user(@RequestParam Integer userId) {
        User user = service.getUser(userId);
        return user;
    }

    //注册
    @PostMapping("/register")
    @ResponseBody //返回的实体自动转成json String
    public ApiRestResponse register(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password) throws MallException, NoSuchAlgorithmException {

        //1.对参数做有效性校验
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(MallErrorEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallErrorEnum.NEED_USER_PASSWORD);
        }
        password = MD5Utils.md5(password);
        if (password.length() < 8) {
            return ApiRestResponse.error(MallErrorEnum.PASSWORD_TOO_SHORT);
        }

        //调用注册接口
        service.register(userName, password);
        return ApiRestResponse.success();
    }

    //登录
    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(String userName,
                                 String password,
                                 HttpSession session) throws NoSuchAlgorithmException {
        //1.对参数做有效性校验
        if (StringUtils.isEmpty(userName)) {
            return ApiRestResponse.error(MallErrorEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallErrorEnum.NEED_USER_PASSWORD);
        }
        User user = service.login(userName, password);
        if (user.getRole() != 1) {
            return ApiRestResponse.error(MallErrorEnum.NO_NORMAL_ROLE);
        }

        //登录成功返回的实体中，不可以包括密码。
        user.setPassword(null);
        //登录成功后，将用户信息保存在session中
        session.setAttribute(Constant.SESSION_USER_KEY, user);
        return ApiRestResponse.success(user);
    }

    /**
     * 更新签名
     *
     * @param signature
     * @return
     */
    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestResponse updateUser(@RequestParam String signature, HttpSession session) {

        if (StringUtils.isEmpty(signature)) {
            return ApiRestResponse.error(MallErrorEnum.SIGNATURE_NOT_EMPTY);
        }
        //获取Session中的用户信息。
        User currentUser = (User) session.getAttribute(Constant.SESSION_USER_KEY);
        if (currentUser == null) {
            return ApiRestResponse.error(MallErrorEnum.USER_NOLOGIN);
        }

        //开始更新用户信息，
        User newUser = new User();
        newUser.setId(currentUser.getId());
        newUser.setPersonalizedSignature(signature);

        service.updateUserInfo(newUser);
        return ApiRestResponse.success();
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse<Object> logout(HttpSession session){

        //将session中的user删除。
        session.removeAttribute(Constant.SESSION_USER_KEY);
        return ApiRestResponse.success();
    }


    /**
     * 管理员后台登录
     *
     * @param username 管理员用户名
     * @param password 密码
     * @return 管理员用户信息实体信息。
     */
    @PostMapping("admin/login")
    @ResponseBody
    public ApiRestResponse<Object> adminLogin(@RequestParam String username,
                                              @RequestParam String password,
                                              HttpSession httpSession
    ) throws NoSuchAlgorithmException {

        if (StringUtils.isEmpty(username)) {
            return ApiRestResponse.error(MallErrorEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallErrorEnum.PASSWORD_ERROR);
        }

        User user = service.adminLogin(username, password);
        user.setPassword(null);
        //保存用户信息到session
        httpSession.setAttribute(Constant.SESSION_USER_KEY,user);
        return ApiRestResponse.success(user);
    }

}

