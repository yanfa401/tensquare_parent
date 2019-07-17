package com.tensquare.recruit.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.recruit.pojo.Enterprise;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/17
 */
public interface EnterpriseService {
    
    /**
     * 查询全部
     * @return
     */
    List<Enterprise> findAll();
    
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Enterprise findById(String id);
    
    /**
     * 增
     * @param enterprise
     */
    void add(Enterprise enterprise);
    
    /**
     * 删
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 改
     * @param enterprise
     */
    void update(Enterprise enterprise);
    
    /**
     * 条件 +分页查询
     * @param whereMap
     * @param page
     * @param pageSize
     * @return
     */
    Page<Enterprise> findSearch(Map whereMap, int page, int pageSize);
    
    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    List<Enterprise> findSearch(Map whereMap);
    
}
