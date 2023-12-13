package org.Eden.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.User;

public interface UserService extends IService<User> {
    //个人信息查询
    ResponseResult userInfo();
}