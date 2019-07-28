package com.tensquare.article.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.tensquare.article.dao.ChannelDao;
import com.tensquare.article.pojo.Channel;
import com.tensquare.article.service.ChannelService;
import com.tensquare.common.util.IdWorker;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/23
 */
public class ChannelServiceImpl implements ChannelService {
    
    @Autowired
    private ChannelDao channelDao;
    
    @Autowired
    private IdWorker idWorker;
    
    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<Channel> findAll() {
        return channelDao.findAll();
    }
    
    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    @Override
    public Channel findById(String id) {
        return channelDao.findById(id).get();
    }
    
    /**
     * 新增
     *
     * @param channel
     */
    @Override
    public void add(Channel channel) {
        String id = String.valueOf(idWorker.nextId());
        channel.setId(id);
        channelDao.save(channel);
    }
    
    /**
     * 根据 id删除
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        channelDao.deleteById(id);
    }
    
    /**
     * 修改
     *
     * @param channel
     */
    @Override
    public void update(Channel channel) {
        channelDao.save(channel);
    }
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    @Override
    public List<Channel> searchMap(Map whereMap) {
        Specification<Channel> specification = createSpecification(whereMap);
        return channelDao.findAll(specification);
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
    public Page<Channel> searchMap(Map whereMap, int pageIndex, int pageSize) {
        Specification<Channel> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize);
        return channelDao.findAll(specification, pageRequest);
    }
    
    
    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Channel> createSpecification(Map searchMap) {
        
        return new Specification<Channel>() {
            
            @Override
            public Predicate toPredicate(Root<Channel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 频道名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }
                
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
                
            }
        };
        
    }
}
