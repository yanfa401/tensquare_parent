package com.tensquare.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import com.tensquare.common.entity.HttpReturnMessage;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/08/23
 */

@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @PostMapping
    public Result addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 根据文章ID查询评论列表
     *
     * @param articleid
     * @return
     */
    @GetMapping(value = "/article/{articleid}")
    public Result findByArticleid(@PathVariable String articleid) {
        List<Comment> commentList = commentService.findByArticleid(articleid);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, commentList);
    }
}
