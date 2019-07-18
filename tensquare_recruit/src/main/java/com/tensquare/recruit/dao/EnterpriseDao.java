package com.tensquare.recruit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Enterprise;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/17
 */
public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {
    
    /**
     * 根据热门状态获取企业列表
     * @param ishot
     * @return
     */
    List<Enterprise> findByIshot(String ishot);
}
