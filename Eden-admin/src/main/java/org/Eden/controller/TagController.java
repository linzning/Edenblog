package org.Eden.controller;

import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Tag;
import org.Eden.domain.vo.PageVo;
import org.Eden.domain.vo.TagVo;
import org.Eden.dto.AddTagDto;
import org.Eden.dto.EditTagDto;
import org.Eden.dto.TagListDto;
import org.Eden.service.TagService;
import org.Eden.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    //TagService是我们在huanf-framework工程的接口
    private TagService tagService;

    //查询标签列表
    @GetMapping("/list")
    //ResponseResult是我们在huanf-framework工程的实体类
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        //pageTagList是我们在huanf-framework工程写的方法
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    //-------------------------------新增标签------------------------------------

    @PostMapping
    public ResponseResult add(@RequestBody AddTagDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    //-------------------------------删除标签------------------------------------

    @DeleteMapping("/{id}")
    //pageTagList是我们在huanf-framework工程写的方法
    public ResponseResult deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }

    //-------------------------------修改标签------------------------------------


    @GetMapping("/{id}")
    //①根据标签的id来查询标签
    public ResponseResult getLableById(@PathVariable Long id){
        return tagService.getLableById(id);
    }

    @PutMapping
    //②根据标签的id来修改标签
    public ResponseResult updateById(@RequestBody TagVo tagVo){
        return tagService.myUpdateById(tagVo);
    }


    //---------------------------写博文-查询文章标签的接口---------------------------

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}