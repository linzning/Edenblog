package org.Eden.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.Eden.domain.entity.User;
import org.Eden.mapper.UserMapper;
import org.Eden.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    //已增加UserServiceImpl实现类，此时应该是没有实现方法的
}
