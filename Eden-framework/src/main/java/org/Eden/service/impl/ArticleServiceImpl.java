package org.Eden.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Article;
import org.Eden.domain.entity.Category;
import org.Eden.domain.vo.ArticleListVo;
import org.Eden.domain.vo.PageVo;
import org.Eden.mapper.ArticleMapper;
import org.Eden.service.ArticleService;
import org.Eden.service.CategoryService;
import org.Eden.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.Eden.domain.vo.HotArticleVo;
import java.util.function.Function;
import org.Eden.constants.SystemConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
//ServiceImpl是mybatisPlus官方提供的
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public ResponseResult hotArticleList() {

        //查询热门文章，封装成ResponseResult返回。把所有查询条件写在queryWrapper里面
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //查询的不能是草稿。也就是Status字段不能是0
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序。也就是根据ViewCount字段降序排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只能查询出来10条消息。当前显示第一页的数据，每页显示10条数据
        Page<Article> page = new Page<>(
                SystemConstants.ARTICLE_STATUS_CURRENT,SystemConstants.ARTICLE_STATUS_SIZE);
        page(page,queryWrapper);

        //获取最终的查询结果，把结果封装在Article实体类里面会有很多不需要的字段
        List<Article> articles = page.getRecords();

        //解决: 把结果封装在HotArticleVo实体类里面，在HotArticleVo实体类只写我们要的字段
        /*List<HotArticleVo> articleVos = new ArrayList<>();
        for (Article xxarticle : articles) {
            HotArticleVo xxvo = new HotArticleVo();
            //使用spring提供的BeanUtils类，来实现bean拷贝。第一个参数是源数据，第二个参数是目标数据，把源数据拷贝给目标数据
            BeanUtils.copyProperties(xxarticle,xxvo); //xxarticle就是Article实体类，xxvo就是HotArticleVo实体类
            //把我们要的数据封装成集合
            articleVos.add(xxvo);
        }*/

        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(vs);
    }

    //----------------------------------分页查询文章的列表---------------------------------
    @Autowired
    //注入我们写的CategoryService接口
    private CategoryService categoryService;

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {

        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        //判空。如果前端传了categoryId这个参数，那么查询时要和传入的相同。第二个参数是数据表的文章id，第三个字段是前端传来的文章id
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);

        //只查询状态是正式发布的文章。Article实体类的status字段跟0作比较，一样就表示是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);

        //对isTop字段进行降序排序，实现置顶的文章(isTop值为1)在最前面
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        /**
         * 解决categoryName字段没有返回值的问题。在分页之后，封装成ArticleListVo之前，进行处理。用stream流的方式
         */
        //用categoryId来查询categoryName(category表的name字段)，也就是查询'分类名称'
        List<Article> articles = page.getRecords();
        articles.stream()
                //Function泛型，第一个参数是传入的，第二个是得到的
                .map(new Function<Article, Article>() {
                    @Override
                    public Article apply(Article article) {
                        //'article.getCategoryId()'表示从article表获取category_id字段，然后作为查询category表的name字段
                        Category category = categoryService.getById(article.getCategoryId());
                        String name = category.getName();
                        //把查询出来的category表的name字段值，也就是article，设置给Article实体类的categoryName成员变量
                        article.setCategoryName(name);
                        //把查询出来的category表的name字段值，也就是article，设置给Article实体类的categoryName成员变量
                        return article;
                    }
                })
                .collect(Collectors.toList());


        //把最后的查询结果封装成ArticleListVo(我们写的实体类)。BeanCopyUtils是我们写的工具类
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);


        //把上面那行的查询结果和文章总数封装在PageVo(我们写的实体类)
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
