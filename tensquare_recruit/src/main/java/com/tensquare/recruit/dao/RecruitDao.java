package com.tensquare.recruit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/17
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {
    
    /**
     * 职位推荐列表:
     * 查询最新4条职位列表,并按创建时间排序
     * @param state 状态
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
