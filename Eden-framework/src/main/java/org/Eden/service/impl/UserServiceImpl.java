package org.Eden.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.User;
import org.Eden.domain.vo.UserInfoVo;
import org.Eden.mapper.UserMapper;
import org.Eden.service.UserService;
import org.Eden.utils.BeanCopyUtils;
import org.Eden.utils.SecurityUtils;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    //已增加UserServiceImpl实现类，此时应该是没有实现方法的
    @Override
    //查询个人信息
    public ResponseResult userInfo() {

        //获取当前用户的用户id。SecurityUtils是我们在huanf-framework工程写的类
        Long userId = SecurityUtils.getUserId();

        //根据用户id查询用户信息
        User user = getById(userId);

        //封装成UserInfoVo(我们在huanf-framework工程写的类)，然后返回
        UserInfoVo vo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }
}
