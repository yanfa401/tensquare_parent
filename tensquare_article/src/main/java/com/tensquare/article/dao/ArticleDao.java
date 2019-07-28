package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.tensquare.article.pojo.Article;

/**
 * article数据访问接口
 *
 * @author Administrator
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {
    
    /**
     * 文章审核
     */
    @Modifying
    @Query("update Article set state='1' where id=?1")
    void examine(String id);
    
    
    /**
     * 点赞
     *
     * @param id
     * @return
     */
    @Modifying
    @Query("update Article a set thumbup = thumbup + 1 where id =?1")
    int updateThumbup(String id);
}
