package com.tensquare.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tensquare.common.util.IdWorker;
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
    
    @Autowired
    private IdWorker idWorker;
    
    /**
     * 新增文章
     *
     * @param article
     */
    @Override
    public void add(Article article) {
        article.setId(String.valueOf(idWorker.nextId()));
        searchRepository.save(article);
    }
    
    /**
     * 分页条件查询
     *
     * @param pageIndex
     * @param pageSize
     * @param keywords
     * @return
     */
    @Override
    public Page<Article> findByTitleLike(int pageIndex, int pageSize, String keywords) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return searchRepository.findByTitleOrContentLike(keywords, keywords, pageable);
    }
}
