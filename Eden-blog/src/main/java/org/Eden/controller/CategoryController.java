package org.Eden.controller;

import org.Eden.domain.ResponseResult;
import org.Eden.service.ArticleService;
import org.Eden.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    //注入公共模块的ArticleService接口
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}
