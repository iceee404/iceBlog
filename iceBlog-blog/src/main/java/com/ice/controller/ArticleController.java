package com.ice.controller;

import com.ice.domain.ResponseResult;
import com.ice.domain.entity.Article;
import com.ice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package:com.ice.controller
 * @ClassName:ArticleController
 * @Auther:iceee
 * @Date:2022/2/28
 * @Description:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {


    //注入ArticleService
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public List<Article> test(){
        return articleService.list();
    }

    //热门文章的排行功能
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){

        //把序列化好的数据扔回前端
        return articleService.hotArticleList();
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }
}
