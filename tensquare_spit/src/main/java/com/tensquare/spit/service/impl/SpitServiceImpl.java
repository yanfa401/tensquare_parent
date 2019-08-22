package com.tensquare.spit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.tensquare.common.util.IdWorker;
import com.tensquare.spit.dao.SpitRepository;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/08/12
 */
@Service
public class SpitServiceImpl implements SpitService {
    
    @Autowired
    private SpitRepository spitRepository;
    
    @Autowired
    private IdWorker idWorker;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    /**
     * 查询全部记录
     *
     * @return
     */
    @Override
    public List<Spit> findAll() {
        return spitRepository.findAll();
    }
    
    /**
     * 根据主键查询实体类
     *
     * @param id
     * @return
     */
    @Override
    public Spit findById(String id) {
        return spitRepository.findById(id).get();
    }
    
    /**
     * 新增
     *
     * @param spit
     */
    @Override
    public void add(Spit spit) {
        spit.set_id(String.valueOf(idWorker.nextId()));
        spitRepository.save(spit);
    }
    
    /**
     * 修改
     *
     * @param spit
     */
    @Override
    public void update(Spit spit) {
        spitRepository.save(spit);
    }
    
    /**
     * 根据主键删除
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        spitRepository.deleteById(id);
    }
    
    /**
     * 根据上级ID查询吐槽列表（分页）
     *
     * @param parentId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<Spit> findByParentid(String parentId, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return spitRepository.findByParentid(parentId, pageable);
    }
    
    /**
     * 点赞
     * 使用 mongoTemplate直接对collection中的数据进行加一操作
     *
     * @param id
     */
    @Override
    public void updateThumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
