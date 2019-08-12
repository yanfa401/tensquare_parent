package com.tensquare.spit.service;

import java.util.List;

import com.tensquare.spit.pojo.Spit;

/**
 * 描述：吐槽服务层接口
 *
 * @author xielei
 * @date 2019/08/12
 */
public interface SpitService {
    
    /**
     * 查询全部记录
     *
     * @return
     */
    List<Spit> findAll();
    
    
    /**
     * 根据主键查询实体类
     *
     * @param id
     * @return
     */
    Spit findById(String id);
    
    /**
     * 新增
     *
     * @param spit
     */
    void add(Spit spit);
    
    /**
     * 修改
     *
     * @param spit
     */
    void update(Spit spit);
    
    /**
     * 根据主键删除
     *
     * @param id
     */
    void deleteById(String id);
}
