package com.tensquare.qa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.qa.pojo.Problem;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/19
 */
public interface ProblemService {
    
    /**
     * 查询全部
     * @return
     */
    List<Problem> findAll();
    
    /**
     * 根据id查
     * @param id
     * @return
     */
    Problem findById(String id);
    
    /**
     * 新增
     * @param problem
     */
    void add(Problem problem);
    
    /**
     * 根据 id删除
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 修改
     * @param problem
     */
    void update(Problem problem);
    
    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    List<Problem> searchMap(Map whereMap);
    
    /**
     * 分页+条件查询
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Problem> searchMap(Map whereMap, int pageIndex, int pageSize);
    
    /**
     * 根据标签ID查询问题列表
     * @param lableId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Problem> findNewListByLabelId(String lableId, int pageIndex, int pageSize);
    
    /**
     * 根据标签ID查询热门问题列表
     * @param lableId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Problem> findHotListByLabelId(String lableId, int pageIndex, int pageSize);
    
    
    /**
     * 根据标签ID查询等待回答问题列表
     * @param lableId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Problem> findWaitListByLabelId(String lableId, int pageIndex, int pageSize);
}
