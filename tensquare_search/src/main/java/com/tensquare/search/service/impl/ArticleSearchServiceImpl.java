package com.tensquare.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tensquare.search.Repository.ArticleSearchRepository;
import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/09/04
 */
@Service
public class ArticleSearchServiceImpl implements ArticleSearchService {
    
    @Autowired
    private ArticleSearchRepository searchRepository;
    
    /**
     * 新增文章
     *
     * @param article
     */
    @Override
    public void add(Article article) {
        searchRepository.save(article);
    }
}
