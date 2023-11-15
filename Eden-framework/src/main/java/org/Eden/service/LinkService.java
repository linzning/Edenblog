package org.Eden.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Link;

public interface LinkService extends IService<Link> {

    //查询友链
    ResponseResult getAllLink();
}