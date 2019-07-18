package com.tensquare.recruit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tensquare.common.util.IdWorker;
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
    
    @Autowired
    private IdWorker idWorker;
    
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
        enterprise.setId(String.valueOf(idWorker.nextId()));
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
        Specification<Enterprise> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page-1, pageSize);
        Page<Enterprise> pages = enterpriseDao.findAll(specification, pageRequest);
        return pages;
    }
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    @Override
    public List<Enterprise> findSearch(Map whereMap) {
        Specification<Enterprise> specification = createSpecification(whereMap);
        return enterpriseDao.findAll(specification);
    }
    
    
    /**
     * 动态创建 where条件
     * @param whereMap
     * @return
     */
    private Specification<Enterprise> createSpecification(Map whereMap){
        Specification<Enterprise> specification = new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                //ID
                if (!StringUtils.isEmpty((String)whereMap.get("id"))) {
                    predicateList.add(criteriaBuilder.like(root.get("id").as(String.class), "%"+(String)whereMap.get("id")+"%"));
                }
                //企业名称
                if (!StringUtils.isEmpty((String)whereMap.get("name"))) {
                    predicateList.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+(String)whereMap.get("name")+"%"));
                }
                //企业简介
                if (!StringUtils.isEmpty((String)whereMap.get("summary"))) {
                    predicateList.add(criteriaBuilder.like(root.get("summary").as(String.class), "%"+(String)whereMap.get("summary")+"%"));
                }
                //企业地址
                if (!StringUtils.isEmpty((String)whereMap.get("address"))) {
                    predicateList.add(criteriaBuilder.like(root.get("address").as(String.class), "%"+(String)whereMap.get("address")+"%"));
                }
                //标签列表
                if (!StringUtils.isEmpty((String)whereMap.get("labels"))) {
                    predicateList.add(criteriaBuilder.like(root.get("labels").as(String.class), "%"+(String)whereMap.get("labels")+"%"));
                }
                //是否热门
                if (!StringUtils.isEmpty((String)whereMap.get("ishot"))) {
                    predicateList.add(criteriaBuilder.like(root.get("ishot").as(String.class), "%"+(String)whereMap.get("ishot")+"%"));
                }
                //坐标
                if (!StringUtils.isEmpty((String)whereMap.get("coordinate"))) {
                    predicateList.add(criteriaBuilder.like(root.get("coordinate").as(String.class), "%"+(String)whereMap.get("coordinate")+"%"));
                }
                //LOGO
                if (!StringUtils.isEmpty((String)whereMap.get("logo"))) {
                    predicateList.add(criteriaBuilder.like(root.get("logo").as(String.class), "%"+(String)whereMap.get("logo")+"%"));
                }
                //URL
                if (!StringUtils.isEmpty((String)whereMap.get("url"))) {
                    predicateList.add(criteriaBuilder.like(root.get("url").as(String.class), "%"+(String)whereMap.get("url")+"%"));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return specification;
    }
}
