package org.Eden.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.Eden.domain.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    //查询普通用户的权限信息
    List<String> selectPermsByOtherUserId(Long userId);
}