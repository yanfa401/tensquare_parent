package com.tensquare.article.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.tensquare.article.dao.ColumnDao;
import com.tensquare.article.pojo.Column;
import com.tensquare.article.service.ColumnService;
import com.tensquare.common.util.IdWorker;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/23
 */
public class ColumnServiceImpl implements ColumnService {
    
    @Autowired
    private ColumnDao columnDao;
    
    @Autowired
    private IdWorker idWorker;
    
    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<Column> findAll() {
        return columnDao.findAll();
    }
    
    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    @Override
    public Column findById(String id) {
        return columnDao.findById(id).get();
    }
    
    /**
     * 新增
     *
     * @param column
     */
    @Override
    public void add(Column column) {
        String id = String.valueOf(idWorker.nextId());
        column.setId(id);
        columnDao.save(column);
    }
    
    /**
     * 根据 id删除
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        columnDao.deleteById(id);
    }
    
    /**
     * 修改
     *
     * @param column
     */
    @Override
    public void update(Column column) {
        columnDao.save(column);
    }
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    @Override
    public List<Column> searchMap(Map whereMap) {
        Specification<Column> specification = createSpecification(whereMap);
        return columnDao.findAll(specification);
    }
    
    /**
     * 分页+条件查询
     *
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<Column> searchMap(Map whereMap, int pageIndex, int pageSize) {
        Specification<Column> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize);
        return columnDao.findAll(specification, pageRequest);
    }
    
    
    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<Column> createSpecification(Map searchMap) {
        
        return new Specification<Column>() {
            
            @Override
            public Predicate toPredicate(Root<Column> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 专栏名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 专栏简介
                if (searchMap.get("summary") != null && !"".equals(searchMap.get("summary"))) {
                    predicateList.add(cb.like(root.get("summary").as(String.class), "%" + (String) searchMap.get("summary") + "%"));
                }
                // 用户ID
                if (searchMap.get("userid") != null && !"".equals(searchMap.get("userid"))) {
                    predicateList.add(cb.like(root.get("userid").as(String.class), "%" + (String) searchMap.get("userid") + "%"));
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
