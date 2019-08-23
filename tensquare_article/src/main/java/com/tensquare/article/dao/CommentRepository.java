package com.tensquare.article.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tensquare.article.pojo.Comment;

/**
 * 描述： 继承MongoRepository
 *
 * @author xielei
 * @date 2019/08/23
 */
public interface CommentRepository extends MongoRepository<Comment, String> {
    
    /**
     * 根据文章id查询评论列表
     *
     * @param id
     * @return
     */
    List<Comment> findByArticleid(String id);
}
