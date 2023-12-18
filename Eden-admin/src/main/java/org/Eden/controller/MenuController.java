package org.Eden.controller;

import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Menu;
import org.Eden.domain.vo.MenuVo;
import org.Eden.service.MenuService;
import org.Eden.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //---------------------------------查询菜单列表--------------------------------------

    @GetMapping("/list")
    public ResponseResult list(Menu menu) {
        List<Menu> menus = menuService.selectMenuList(menu);
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        return ResponseResult.okResult(menuVos);
    }

    //-----------------------------------新增菜单---------------------------------------


    @PostMapping
    public ResponseResult add(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }


}
