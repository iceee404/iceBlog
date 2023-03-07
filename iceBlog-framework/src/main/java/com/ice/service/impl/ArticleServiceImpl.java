package com.ice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.constants.SystemConstants;
import com.ice.domain.ResponseResult;
import com.ice.domain.entity.Article;
import com.ice.domain.entity.Category;
import com.ice.domain.vo.ArticleDetailVo;
import com.ice.domain.vo.ArticleListVo;
import com.ice.domain.vo.HotArticleVo;
import com.ice.domain.vo.PageVo;
import com.ice.mapper.ArticleMapper;
import com.ice.service.ArticleService;
import com.ice.service.CategoryService;
import com.ice.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Package:com.ice.service.impl
 * @ClassName:ArticleServiceImpl
 * @Auther:iceee
 * @Date:2022/2/28
 * @Description:
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList() {

        //自定义一个wrapper规则去匹配
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        //使用了函数接口直接去找到字段名，生的写错或者对不上
        lqw.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        lqw.orderByDesc(Article::getViewCount);

        Page<Article> page = new Page(1,10);
        page(page,lqw);

        //去取排序好的articles保存在list，然后把这个处理好的结果（是个链表对象）扔给负责序列化的ResponseResult
        List<Article> articles = page.getRecords();

        //vo优化一下扔给前端的jason，因为不需要那么多字段
        //bean拷贝
        List<HotArticleVo> articleVos = new ArrayList<>();
        for (Article article : articles) {
            HotArticleVo vo = new HotArticleVo();
            //遍历上面排好序的articles存到新的articleVos，新的articleVo对象只有我们需要的字段值
            //注意这里bean拷贝一定要保证字段名字和；类型一样
            BeanUtils.copyProperties(article,vo);
            //把拷贝好的bean都扔进去集合里
            articleVos.add(vo);
        }
        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0 ,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        //查询categoryName
//        articles.stream()
//                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
//                .collect(Collectors.toList());
        //articleId去查询articleName进行设置
        for (Article article : articles) {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }

        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }
}


