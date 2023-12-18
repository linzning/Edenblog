package org.Eden.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Tag;
import org.Eden.domain.vo.PageVo;
import org.Eden.dto.TagListDto;
import org.Eden.mapper.TagMapper;
import org.Eden.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Override
    //查询标签列表
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {

        //分页查询的条件。模糊+分页
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        //第二、三个参数是互相比较。第一个参数是判空，当用户没有查询条件时，就不去比较后面两个参数
        queryWrapper.like(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        //第二、三个参数是互相比较。第一个参数是判空，当用户没有查询条件时，就不去比较后面两个参数
        queryWrapper.like(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());

        //分页查询。Page是mybatisplus提供的类，Tag是我们在huanf-framework工程写的类
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        //封装数据返回。PageVo是我们在huanf-framework工程写的类
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}