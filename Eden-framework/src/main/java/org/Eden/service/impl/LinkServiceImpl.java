package org.Eden.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.Eden.constants.SystemConstants;
import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Link;
import org.Eden.domain.vo.LinkVo;
import org.Eden.mapper.LinkMapper;
import org.Eden.service.LinkService;
import org.Eden.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {

        //查询所有审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        //要求查的是友链表status字段的值为0，注意SystemCanstants是我们写的一个常量类，用来解决字面值的书写问题
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //把最后的查询结果封装成LinkListVo(我们写的实体类)。BeanCopyUtils是我们写的工具类
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);

        //封装响应返回。ResponseResult是我们在huanf-framework工程的domain目录写的实体类
        return ResponseResult.okResult(linkVos);
    }
}
