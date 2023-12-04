package org.Eden.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Comment;

public interface CommentService extends IService<Comment> {
    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);
}