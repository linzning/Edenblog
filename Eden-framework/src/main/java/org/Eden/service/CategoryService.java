package org.Eden.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.Eden.domain.ResponseResult;
import org.Eden.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-11-13 21:47:22
 */
public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();
}
