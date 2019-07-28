package com.tensquare.article.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.article.pojo.Column;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/23
 */
public interface ColumnService {
    
    /**
     * 查询全部
     *
     * @return
     */
    List<Column> findAll();
    
    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    Column findById(String id);
    
    /**
     * 新增
     *
     * @param column
     */
    void add(Column column);
    
    /**
     * 根据 id删除
     *
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 修改
     *
     * @param column
     */
    void update(Column column);
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    List<Column> searchMap(Map whereMap);
    
    /**
     * 分页+条件查询
     *
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Column> searchMap(Map whereMap, int pageIndex, int pageSize);
    
}
