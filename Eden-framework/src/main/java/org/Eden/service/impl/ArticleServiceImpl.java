package org.Eden.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.Eden.domain.entity.Article;
import org.Eden.mapper.ArticleMapper;
import org.Eden.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
//ServiceImpl是mybatisPlus官方提供的
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
