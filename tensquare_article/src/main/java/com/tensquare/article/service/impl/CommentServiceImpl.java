package com.tensquare.article.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tensquare.article.dao.CommentRepository;
import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import com.tensquare.common.util.IdWorker;

/**
 * 描述：业务层实现
 *
 * @author xielei
 * @date 2019/08/23
 */

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private IdWorker idWorker;
    
    @Autowired
    private CommentRepository commentRepository;
    
    /**
     * 新增评论
     *
     * @param comment
     */
    @Override
    public void addComment(Comment comment) {
        comment.set_id(String.valueOf(idWorker.nextId()));
        commentRepository.save(comment);
    }
    
    /**
     * 根据文章id查询评论列表
     *
     * @param id
     * @return
     */
    @Override
    public List<Comment> findByArticleid(String id) {
        return commentRepository.findByArticleid(id);
    }
    
    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public void deleteBy_id(String id) {
        commentRepository.deleteBy_id(id);
    }
}
