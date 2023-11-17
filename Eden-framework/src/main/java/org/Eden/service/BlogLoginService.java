package org.Eden.service;

import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);
}