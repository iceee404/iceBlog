package com.ice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.domain.ResponseResult;
import com.ice.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-03-02 16:57:00
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

