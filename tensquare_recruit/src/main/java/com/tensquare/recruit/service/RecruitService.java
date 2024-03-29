package com.tensquare.recruit.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.recruit.pojo.Recruit;

/**
 * 描述：业务接口定义
 *
 * @author xielei
 * @date 2019/07/18
 */
public interface RecruitService {
    
    /**
     * 查询全部
     * @return
     */
    List<Recruit> findAll();
    
    /**
     * 根据id查
     * @param id
     * @return
     */
    Recruit findById(String id);
    
    /**
     * 新增
     * @param recruit
     */
    void add(Recruit recruit);
    
    /**
     * 根据 id删除
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 修改
     * @param recruit
     */
    void update(Recruit recruit);
    
    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    List<Recruit> searchMap(Map whereMap);
    
    /**
     * 分页+条件查询
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Recruit> searchMap(Map whereMap, int pageIndex, int pageSize);
    
    /**
     * 查询最新4条职位列表,并按创建时间排序
     * @param state
     * @return
     */
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);
    
    /**
     * 最新职位列表:
     * 查询状态不为0并以创建日期降序排序，查询前12条记录
     * @param state
     * @return
     */
    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
    
}
