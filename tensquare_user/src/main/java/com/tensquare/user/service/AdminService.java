package com.tensquare.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.user.pojo.Admin;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/09/26
 */
public interface AdminService {
    
    /**
     * 增
     *
     * @param admin
     */
    void add(Admin admin);
    
    /**
     * 删
     *
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 改
     *
     * @param admin
     */
    void update(Admin admin);
    
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Admin findById(String id);
    
    /**
     * 查询全部列表
     *
     * @return
     */
    List<Admin> findAll();
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    List<Admin> findSearch(Map whereMap);
    
    /**
     * 分页 + 条件查询
     *
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Admin> searchMap(Map whereMap, int pageIndex, int pageSize);
}
