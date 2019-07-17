package com.tensquare.base.service.impl;

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
import org.springframework.stereotype.Service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.common.util.IdWorker;

import lombok.NonNull;

/**
 * 描述：标签Service实现
 *
 * @author xielei
 * @date 2019/07/17
 */
@Service
public class LabelServiceImpl implements LabelService {
    
    @Autowired
    private LabelDao labelDao;
    
    @Autowired
    private IdWorker idWorker;
    
    /**
     * 查询全部标签
     *
     * @return
     */
    @Override
    public List<Label> findAll() {
        return labelDao.findAll();
    }
    
    /**
     * 根据条件查询全部标签
     *
     * @param searchMap
     * @return
     */
    @Override
    public Page<Label> findSearch(Map searchMap, int currentPage, int pageSize) {
        Specification specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(currentPage-1, pageSize);
        return labelDao.findAll(specification, pageRequest);
    }
    
    /**
     * 构建条件查询
     * @param searchMap
     * @return
     */
    private Specification<Label> createSpecification(Map searchMap) {
        
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("labelname") != null && !"".equals("labelname")) {
                    predicateList.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%"+(String)searchMap.get("labelname")+"%"));
                }
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class), (String)searchMap.get("state")));
                }
                if (searchMap.get("recommend") != null && !"".equals(searchMap.get("recommend"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class), (String)searchMap.get("recommend")));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
    
    /**
     * 根据id查询标签
     *
     * @param id
     * @return
     */
    @Override
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }
    
    /**
     * 增加标签
     *
     * @param label
     */
    @Override
    public void add(Label label) {
        String id = String.valueOf(idWorker.nextId());
        label.setId(id);
        labelDao.save(label);
    }
    
    /**
     * 修改标签
     *
     * @param label
     */
    @Override
    public void update(Label label) {
        labelDao.save(label);
    }
    
    /**
     * 删除标签
     *
     * @param id
     */
    @Override
    public void deleteById(@NonNull String id) {
        labelDao.deleteById(id);
    }
}
