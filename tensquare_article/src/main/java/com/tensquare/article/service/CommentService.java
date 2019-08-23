package com.tensquare.article.service;

import java.util.List;

import com.tensquare.article.pojo.Comment;

/**
 * 描述： Comment业务层
 *
 * @author xielei
 * @date 2019/08/23
 */
public interface CommentService {
    
    /**
     * 新增评论
     *
     * @param comment
     */
    void addComment(Comment comment);
    
    /**
     * 根据文章id查询评论列表
     *
     * @param id
     * @return
     */
    List<Comment> findByArticleid(String id);
    
    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteBy_id(String id);
}
