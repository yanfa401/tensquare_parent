package com.tensquare.article.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.common.redis.CommonKey;
import com.tensquare.common.util.IdWorker;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/23
 */

@Service
public class ArticleServiceImpl implements ArticleService {
    
    @Autowired
    private ArticleDao articleDao;
    
    @Autowired
    private IdWorker idWorker;
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }
    
    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    @Override
    public Article findById(String id) {
        //从缓存中提取
        String key = CommonKey.KEY_ARTICLE_ID + id;
        String articleStr = redisTemplate.opsForValue().get(key);
        Article article = JSONObject.parseObject(articleStr, Article.class);
        if (article == null) {
            //缓存没有从库里查,并写入缓存
            article = articleDao.findById(id).get();
            //设置5分钟失效时间
            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(article), 5, TimeUnit.MINUTES);
        }
        return article;
    }
    
    /**
     * 新增
     *
     * @param article
     */
    @Override
    public void add(Article article) {
        String id = String.valueOf(idWorker.nextId());
        article.setId(id);
        articleDao.save(article);
    }
    
    /**
     * 根据 id删除
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        String key = CommonKey.KEY_ARTICLE_ID + id;
        redisTemplate.delete(key);
        articleDao.deleteById(id);
    }
    
    /**
     * 修改
     *
     * @param article
     */
    @Override
    public void update(Article article) {
        String key = CommonKey.KEY_ARTICLE_ID + article.getId();
        redisTemplate.delete(key);
        articleDao.save(article);
    }
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    @Override
    public List<Article> searchMap(Map whereMap) {
        Specification<Article> specification = createSpecification(whereMap);
        return articleDao.findAll(specification);
    }
    
    /**
     * 分页+条件查询
     *
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<Article> searchMap(Map whereMap, int pageIndex, int pageSize) {
        Specification<Article> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize);
        return articleDao.findAll(specification, pageRequest);
    }
    
    /**
     * 文章审核
     *
     * @param id
     */
    @Override
    public void examine(String id) {
        articleDao.examine(id);
    }
    
    /**
     * 点赞
     *
     * @param id 文章id
     * @return
     */
    @Override
    public int updateThumbup(String id) {
        return articleDao.updateThumbup(id);
    }
    
    
    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Article> createSpecification(Map searchMap) {
        
        return new Specification<Article>() {
            
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 专栏ID
                if (searchMap.get("columnid") != null && !"".equals(searchMap.get("columnid"))) {
                    predicateList.add(cb.like(root.get("columnid").as(String.class), "%" + (String) searchMap.get("columnid") + "%"));
                }
                // 用户ID
                if (searchMap.get("userid") != null && !"".equals(searchMap.get("userid"))) {
                    predicateList.add(cb.like(root.get("userid").as(String.class), "%" + (String) searchMap.get("userid") + "%"));
                }
                // 标题
                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
                }
                // 文章正文
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
                }
                // 文章封面
                if (searchMap.get("image") != null && !"".equals(searchMap.get("image"))) {
                    predicateList.add(cb.like(root.get("image").as(String.class), "%" + (String) searchMap.get("image") + "%"));
                }
                // 是否公开
                if (searchMap.get("ispublic") != null && !"".equals(searchMap.get("ispublic"))) {
                    predicateList.add(cb.like(root.get("ispublic").as(String.class), "%" + (String) searchMap.get("ispublic") + "%"));
                }
                // 是否置顶
                if (searchMap.get("istop") != null && !"".equals(searchMap.get("istop"))) {
                    predicateList.add(cb.like(root.get("istop").as(String.class), "%" + (String) searchMap.get("istop") + "%"));
                }
                // 审核状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }
                // 所属频道
                if (searchMap.get("channelid") != null && !"".equals(searchMap.get("channelid"))) {
                    predicateList.add(cb.like(root.get("channelid").as(String.class), "%" + (String) searchMap.get("channelid") + "%"));
                }
                // URL
                if (searchMap.get("url") != null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(cb.like(root.get("url").as(String.class), "%" + (String) searchMap.get("url") + "%"));
                }
                // 类型
                if (searchMap.get("type") != null && !"".equals(searchMap.get("type"))) {
                    predicateList.add(cb.like(root.get("type").as(String.class), "%" + (String) searchMap.get("type") + "%"));
                }
                
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        
    }
}
