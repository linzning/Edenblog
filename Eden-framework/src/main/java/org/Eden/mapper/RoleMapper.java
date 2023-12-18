package org.Eden.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.Eden.domain.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    //查询普通用户的角色权限
    List<String> selectRoleKeyByOtherUserId(Long userId);
}
