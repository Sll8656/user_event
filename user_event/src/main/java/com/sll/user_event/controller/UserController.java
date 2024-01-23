package com.sll.user_event.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.sll.user_event.common.BaseResponse;
import com.sll.user_event.common.ErrorCode;
import com.sll.user_event.common.ResultUtils;
import com.sll.user_event.exception.BusinessException;
import com.sll.user_event.model.domain.User;
import com.sll.user_event.model.request.UserLoginRequest;
import com.sll.user_event.model.request.UserRegisterRequest;
import com.sll.user_event.service.UserService;
import com.sll.user_event.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sll.user_event.constant.UserConstant.ADMIN_ROLE;
import static com.sll.user_event.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            return null;
        }
        long res = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(res);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse userLogout(HttpServletRequest request) {
        if(request == null) return null;
        userService.UserLogout(request);
        return ResultUtils.success("ok");
    }


    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null) {
            return null;
        }
        long userId = currentUser.getId();
        User user = userService.getById(userId);
        User safeUser = userService.getSafeUser(user);
        return ResultUtils.success(safeUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        //只有管理员才能查询用户
        if (!isAdmin(request)) {
            return ResultUtils.success(new ArrayList<>());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> res = userService.list(queryWrapper)
                .stream()
                .map(user -> userService.getSafeUser(user))
                .collect(Collectors.toList());
        return ResultUtils.success(res);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        //只有管理员才能删除用户
        if (!isAdmin(request)) {
            return null;
        }
        if (id <= 0) {
            return null;
        }
        //如果开启了逻辑删除，MP会自动做更新，而不是真正的删除
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 判断是否为管理员
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        Object userObject = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObject;
        if (user == null || user.getUserRole() != ADMIN_ROLE) {
            return false;
        }
        return true;
    }

}
