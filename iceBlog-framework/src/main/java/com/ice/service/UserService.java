package com.ice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.domain.ResponseResult;
import com.ice.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-03-13 18:55:59
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

