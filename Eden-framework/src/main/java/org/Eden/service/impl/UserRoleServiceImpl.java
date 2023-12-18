package org.Eden.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.Eden.domain.entity.UserRole;
import org.Eden.mapper.UserRoleMapper;
import org.Eden.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}