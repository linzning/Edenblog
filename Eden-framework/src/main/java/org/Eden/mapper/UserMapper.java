package org.Eden.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.Eden.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper extends BaseMapper<User> {
}