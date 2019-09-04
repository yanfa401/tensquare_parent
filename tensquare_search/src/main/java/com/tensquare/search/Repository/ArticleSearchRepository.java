package com.tensquare.search.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tensquare.search.pojo.Article;

/**
 * 描述：文章 数据访问层
 *
 * @author xielei
 * @date 2019/09/04
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, String> {
    
    /**
     * 分页查询
     *
     * @param title
     * @param content
     * @param pageable
     * @return
     */
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
