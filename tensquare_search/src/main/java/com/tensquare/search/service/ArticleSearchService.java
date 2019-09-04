package com.tensquare.search.service;

import org.springframework.data.domain.Page;

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
    
    /**
     * 分页条件查询
     *
     * @param pageIndex
     * @param pageSize
     * @param keywords
     * @return
     */
    Page<Article> findByTitleLike(int pageIndex, int pageSize, String keywords);
}
