package com.ice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.domain.ResponseResult;
import com.ice.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-03-13 18:46:05
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String articleComment, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);

}

