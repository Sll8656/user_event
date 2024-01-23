package com.sll.user_event.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sll.user_event.model.domain.User;

import javax.servlet.http.HttpServletRequest;


/**
 * @author 邵龙凌
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-01-20 10:15:48
 */
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User getSafeUser(User user);

    void UserLogout(HttpServletRequest request);

}
