package org.Eden.controller;

import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Article;
import org.Eden.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    //注入公共模块的ArticleService接口
    private ArticleService articleService;


    //----------------------------------测试mybatisPlus---------------------------------
    @GetMapping("/list")
    //Article是公共模块的实体类
    public List<Article> test(){
        //查询数据库的所有数据
        return articleService.list();
    }

    //----------------------------------测试统一响应格式-----------------------------------
    @GetMapping("/hotArticleList")
    //ResponseResult是huanf-framework工程的domain目录的类
    public ResponseResult hotArticleList(){
        //查询热门文章，封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    //----------------------------------分页查询文章的列表---------------------------------
    @GetMapping("/articleList")
    //ResponseResult是huanf-framework工程的domain目录的类
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    //------------------------------------查询文章详情------------------------------------
    @GetMapping("/{id}") //路径参数形式的HTTP请求，注意下面那行只有加@PathVariable注解才能接收路径参数形式的HTTP请求
    //ResponseResult是huanf-framework工程的domain目录的类
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {//注解里指定的id跟上一行保持一致

        //根据id查询文章详情
        return articleService.getArticleDetail(id);

    }
}