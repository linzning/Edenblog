package org.Eden.controller;

import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Category;
import org.Eden.domain.vo.CategoryVo;
import org.Eden.domain.vo.PageVo;
import org.Eden.dto.CategoryDto;
import org.Eden.service.CategoryService;
import org.Eden.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {

    //---------------------------写博文-查询文章分类的接口--------------------------------

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    //----------------------------分页查询文章的分类列表---------------------------------

    @GetMapping("/list")
    public ResponseResult list(Category category, Integer pageNum, Integer pageSize) {
        PageVo pageVo = categoryService.selectCategoryPage(category,pageNum,pageSize);
        return ResponseResult.okResult(pageVo);
    }

    //-----------------------------增加文章的分类--------------------------------------

    @PostMapping
    public ResponseResult add(@RequestBody CategoryDto categoryDto){
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseResult remove(@PathVariable(value = "id")Long id){
        categoryService.removeById(id);
        return ResponseResult.okResult();
    }
}