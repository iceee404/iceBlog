package com.ice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.domain.ResponseResult;
import com.ice.domain.entity.Article;

/**
 * @Package:com.ice.service
 * @ClassName:ArticleService
 * @Auther:iceee
 * @Date:2022/2/28
 * @Description:
 */

public interface ArticleService extends IService<Article> {


    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
