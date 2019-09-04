package com.tensquare.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.common.entity.HttpReturnMessage;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;

/**
 * 描述：控制层
 *
 * @author xielei
 * @date 2019/09/04
 */

@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Autowired
    private ArticleSearchService searchService;
    
    /**
     * 新增文章
     *
     * @param article
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Article article) {
        searchService.add(article);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 关键字分页搜索
     *
     * @param keywords
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/search/{keywords}/{pageIndex}/{pageSize}")
    public Result findByTitleLike(@PathVariable("keywords") String keywords, @PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        Page<Article> page = searchService.findByTitleLike(pageIndex, pageSize, keywords);
        PageResult<Article> pageResult = new PageResult<>(page.getTotalElements(), page.getContent());
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, pageResult);
    }
    
}
