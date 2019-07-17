package com.tensquare.recruit.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;

/**
 * 描述： 实现
 *
 * @author xielei
 * @date 2019/07/17
 */

@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    
    @Autowired
    private EnterpriseDao enterpriseDao;
    
    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<Enterprise> findAll() {
        return enterpriseDao.findAll();
    }
    
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Enterprise findById(String id) {
        Optional<Enterprise> opt = enterpriseDao.findById(id);
        if (!opt.isPresent()) {
            return new Enterprise();
        }
        return opt.get();
    }
    
    /**
     * 增
     *
     * @param enterprise
     */
    @Override
    public void add(Enterprise enterprise) {
        enterpriseDao.save(enterprise);
    }
    
    /**
     * 删
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        enterpriseDao.deleteById(id);
    }
    
    /**
     * 改
     *
     * @param enterprise
     */
    @Override
    public void update(Enterprise enterprise) {
        enterpriseDao.save(enterprise);
    }
    
    /**
     * 条件 +分页查询
     *
     * @param whereMap
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public Page<Enterprise> findSearch(Map whereMap, int page, int pageSize) {
        return null;
    }
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    @Override
    public List<Enterprise> findSearch(Map whereMap) {
        return null;
    }
}
