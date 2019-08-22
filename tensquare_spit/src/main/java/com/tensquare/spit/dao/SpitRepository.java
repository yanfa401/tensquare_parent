package com.tensquare.spit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tensquare.spit.pojo.Spit;

/**
 * 描述：吐槽数据访问层
 *
 * @author xielei
 * @date 2019/08/12
 */
public interface SpitRepository extends MongoRepository<Spit, String> {
    
    /**
     * 根据上级ID查询吐槽列表（分页）
     *
     * @param parentId
     * @param pageable
     * @return
     */
    Page<Spit> findByParentid(String parentId, Pageable pageable);
    
    
}
