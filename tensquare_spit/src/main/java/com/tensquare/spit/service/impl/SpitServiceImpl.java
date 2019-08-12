package com.tensquare.spit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
