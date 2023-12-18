package org.Eden.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Tag;
import org.Eden.domain.vo.PageVo;
import org.Eden.dto.TagListDto;

public interface TagService extends IService<Tag> {
    //查询标签列表
    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
}