package com.tensquare.gathering.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.gathering.pojo.Gathering;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/30
 */
public interface GatheringService {
    
    /**
     * 查询全部
     *
     * @return
     */
    List<Gathering> findAll();
    
    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    Gathering findById(String id);
    
    /**
     * 新增
     *
     * @param gathering
     */
    void add(Gathering gathering);
    
    /**
     * 根据 id删除
     *
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 修改
     *
     * @param gathering
     */
    void update(Gathering gathering);
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    List<Gathering> searchMap(Map whereMap);
    
    /**
     * 分页+条件查询
     *
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Gathering> searchMap(Map whereMap, int pageIndex, int pageSize);
}
