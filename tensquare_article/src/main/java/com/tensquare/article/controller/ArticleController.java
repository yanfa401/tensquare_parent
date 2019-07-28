package com.tensquare.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.article.service.ArticleService;
import com.tensquare.common.entity.HttpReturnMessage;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/23
 */

@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    /**
     * 文章审核
     *
     * @param id
     * @return
     */
    @PutMapping("/examine/{id}")
    public Result examine(@PathVariable("id") String id) {
        articleService.examine(id);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    
    /**
     * 点赞
     *
     * @param id
     * @return
     */
    @PutMapping("/thumbup/{id}")
    public Result thumbup(@PathVariable("id") String id) {
        articleService.updateThumbup(id);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
}
