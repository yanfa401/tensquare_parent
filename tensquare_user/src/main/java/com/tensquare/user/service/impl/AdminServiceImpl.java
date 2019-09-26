package com.tensquare.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tensquare.common.util.IdWorker;
import com.tensquare.user.dao.AdminDao;
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/09/26
 */
@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminDao adminDao;
    
    @Autowired
    private IdWorker idWorker;
    
    /**
     * 增
     *
     * @param admin
     */
    @Override
    public void add(Admin admin) {
        admin.setId(String.valueOf(idWorker.nextId()));
        adminDao.save(admin);
    }
    
    /**
     * 删
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        adminDao.deleteById(id);
    }
    
    /**
     * 改
     *
     * @param admin
     */
    @Override
    public void update(Admin admin) {
        adminDao.save(admin);
    }
    
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Admin findById(String id) {
        return adminDao.findById(id).get();
    }
    
    /**
     * 查询全部列表
     *
     * @return
     */
    @Override
    public List<Admin> findAll() {
        return adminDao.findAll();
    }
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    @Override
    public List<Admin> findSearch(Map whereMap) {
        Specification<Admin> specification = createSpecification(whereMap);
        return adminDao.findAll(specification);
    }
    
    /**
     * 分页 + 条件查询
     *
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<Admin> searchMap(Map whereMap, int pageIndex, int pageSize) {
        Specification<Admin> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize);
        return adminDao.findAll(specification, pageRequest);
    }
    
    
    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Admin> createSpecification(Map searchMap) {
        
        return new Specification<Admin>() {
            
            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 登陆名称
                if (searchMap.get("loginname") != null && !"".equals(searchMap.get("loginname"))) {
                    predicateList.add(cb.like(root.get("loginname").as(String.class), "%" + (String) searchMap.get("loginname") + "%"));
                }
                // 密码
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
                }
                // 状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }
                
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
                
            }
        };
        
    }
}
