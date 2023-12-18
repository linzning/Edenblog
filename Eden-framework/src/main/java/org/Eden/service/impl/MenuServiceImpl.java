package org.Eden.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.Eden.constants.SystemConstants;
import org.Eden.domain.entity.Menu;
import org.Eden.mapper.MenuMapper;
import org.Eden.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Override
    //查询用户的权限信息
    public List<String> selectPermsByUserId(Long id) {
        //根据用户id查询用户的权限信息。用户id为id代表管理员，如果是管理员就返回所有的权限
        if(id == 1L){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            //查询条件是permissions中需要有所有菜单类型为C或者F的权限。SystemCanstants是我们在huanf-framework工程写的类
            wrapper.in(Menu::getMenuType, SystemConstants.TYPE_MENU,SystemConstants.TYPE_BUTTON);
            //查询条件是permissions中需要有状态为正常的权限。SystemCanstants是我们在huanf-framework工程写的类
            wrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            //查询条件是permissions中需要未被删除的权限的权限
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }

        //如果不是管理员就返回对应用户所具有的权限
        List<String> otherPerms = getBaseMapper().selectPermsByOtherUserId(id);
        return otherPerms;
    }
}