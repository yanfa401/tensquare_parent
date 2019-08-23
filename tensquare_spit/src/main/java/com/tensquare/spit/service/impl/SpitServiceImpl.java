package com.tensquare.spit.service.impl;

import java.util.Date;
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
import org.springframework.util.StringUtils;

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
    
    /**
     * 新增评论
     */
    @Override
    public void publishSpit(Spit spit) {
        spit.set_id(String.valueOf(idWorker.nextId()));
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setThumbup(0);//分享数
        spit.setShare(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        
        //如果是回复别人的评论,那么父评论要回复数+1
        if (!StringUtils.isEmpty(spit.getParentid())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        //保存
        spitRepository.save(spit);
    }
    
    /**
     * 新增浏览量
     *
     * @param id
     */
    @Override
    public void updateVisits(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("visits", 1);
        mongoTemplate.updateFirst(query, update, Spit.class);
    }
    
    /**
     * 新增分享数
     *
     * @param id
     */
    @Override
    public void updateShare(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("share", 1);
        mongoTemplate.updateFirst(query, update, Spit.class);
    }
}
