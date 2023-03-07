package com.ice.service;

import com.ice.domain.ResponseResult;
import com.ice.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}