package org.Eden.controller;

import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.LoginUser;
import org.Eden.domain.entity.User;
import org.Eden.domain.vo.AdminUserInfoVo;
import org.Eden.domain.vo.UserInfoVo;
import org.Eden.enums.AppHttpCodeEnum;
import org.Eden.exception.SystemException;
import org.Eden.service.MenuService;
import org.Eden.service.RoleService;
import org.Eden.service.SystemLoginService;
import org.Eden.utils.BeanCopyUtils;
import org.Eden.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private SystemLoginService systemLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return systemLoginService.login(user);
    }

    //-----------------------------查询(超级管理员|非超级管理员)的权限和角色信息------------------------------------------

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户。SecurityUtils是我们在huanf-framework写的类
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        //BeanCopyUtils是我们在huanf-framework写的类
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        //封装响应返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }
}