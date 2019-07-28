package com.tensquare.article.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.article.pojo.Channel;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/23
 */
public interface ChannelService {
    
    /**
     * 查询全部
     *
     * @return
     */
    List<Channel> findAll();
    
    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    Channel findById(String id);
    
    /**
     * 新增
     *
     * @param channel
     */
    void add(Channel channel);
    
    /**
     * 根据 id删除
     *
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 修改
     *
     * @param channel
     */
    void update(Channel channel);
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    List<Channel> searchMap(Map whereMap);
    
    /**
     * 分页+条件查询
     *
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Channel> searchMap(Map whereMap, int pageIndex, int pageSize);
}
