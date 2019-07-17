package com.tensquare.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.base.pojo.Label;

/**
 * 描述：标签Service
 *
 * @author xielei
 * @date 2019/07/17
 */
public interface LabelService {
    
    /**
     * 查询全部标签
     * @return
     */
    List<Label> findAll();
    
    
    /**
     * 根据条件查询全部标签
     * @return
     */
    Page<Label> findSearch(Map searchMap, int currentPage, int pageSize);
    
    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    Label findById(String id);
    
    /**
     * 增加标签
     * @param label
     */
    void add(Label label);
    
    /**
     * 修改标签
     * @param label
     */
    void update(Label label);
    
    /**
     * 删除标签
     * @param id
     */
    void deleteById(String id);
}
