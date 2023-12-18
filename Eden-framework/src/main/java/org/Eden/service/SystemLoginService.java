package org.Eden.service;

import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.User;

public interface SystemLoginService {

    //登录
    ResponseResult login(User user);

    //退出登录
    ResponseResult logout();
}