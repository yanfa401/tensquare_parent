package com.tensquare.search.service;

import com.tensquare.search.pojo.Article;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/09/04
 */
public interface ArticleSearchService {
    
    /**
     * 新增文章
     *
     * @param article
     */
    void add(Article article);
}
