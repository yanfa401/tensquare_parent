package com.tensquare.article.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.article.pojo.Article;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/23
 */
public interface ArticleService {
    /**
     * 查询全部
     *
     * @return
     */
    List<Article> findAll();
    
    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    Article findById(String id);
    
    /**
     * 新增
     *
     * @param article
     */
    void add(Article article);
    
    /**
     * 根据 id删除
     *
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 修改
     *
     * @param article
     */
    void update(Article article);
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    List<Article> searchMap(Map whereMap);
    
    /**
     * 分页+条件查询
     *
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Article> searchMap(Map whereMap, int pageIndex, int pageSize);
    
    /**
     * 文章审核
     *
     * @param id 文章id
     */
    void examine(String id);
    
    /**
     * 点赞
     *
     * @param id 文章id
     * @return
     */
    int updateThumbup(String id);
}
