package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tensquare.qa.pojo.Problem;

/**
 * problem数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
    
    /**
     * 根据标签ID查询最新问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "SELECT * FROM tb_problem WHERE id IN (SELECT problemid FROM tb_pl WHERE labelid = ?1) ORDER BY replytime DESC",
            nativeQuery = true)
    Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);
    
    /**
     * 根据标签ID查询热门问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in ( select problemid from Pl where labelid = :labelId ) order by p.reply desc ")
    Page<Problem> findHotListByLabelId(@Param("labelId") String labelId, Pageable pageable);
    
    /**
     * 根据标签ID查询等待回答问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where id in ( select problemid from Pl where labelid=?1 ) and p.reply = 0  order by p.createtime desc ")
    Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);
    
}
