package com.tensquare.qa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tensquare.common.util.IdWorker;
import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/19
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    
    @Autowired
    private ProblemDao problemDao;
    
    @Autowired
    private IdWorker idWorker;
    
    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<Problem> findAll() {
        return problemDao.findAll();
    }
    
    /**
     * 根据id查
     *
     * @param id
     * @return
     */
    @Override
    public Problem findById(String id) {
        return problemDao.findById(id).get();
    }
    
    /**
     * 新增
     *
     * @param problem
     */
    @Override
    public void add(Problem problem) {
        problem.setId( idWorker.nextId()+"" );
        problem.setCreatetime(new Date());
        problem.setVisits(0L);
        problemDao.save(problem);
    }
    
    /**
     * 根据 id删除
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        problemDao.deleteById(id);
    }
    
    /**
     * 修改
     *
     * @param problem
     */
    @Override
    public void update(Problem problem) {
        problemDao.save(problem);
    }
    
    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    @Override
    public List<Problem> searchMap(Map whereMap) {
        Specification<Problem> specification = createSpecification(whereMap);
        return problemDao.findAll(specification);
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
    public Page<Problem> searchMap(Map whereMap, int pageIndex, int pageSize) {
        Specification<Problem> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(pageIndex-1, pageSize);
        return problemDao.findAll(specification, pageRequest);
    }
    
    /**
     * 根据标签ID查询问题列表
     *
     * @param lableId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<Problem> findNewListByLabelId(String lableId, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
        return problemDao.findNewListByLabelId(lableId,pageable);
    }
    
    /**
     * 根据标签ID查询热门问题列表
     *
     * @param lableId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<Problem> findHotListByLabelId(String lableId, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
        return problemDao.findHotListByLabelId(lableId, pageable);
    }
    
    /**
     * 根据标签ID查询等待回答问题列表
     *
     * @param lableId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Page<Problem> findWaitListByLabelId(String lableId, int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
        return problemDao.findWaitListByLabelId(lableId, pageable);
    }
    
    /**
     * 动态条件构建
     * @param searchMap
     * @return
     */
    private Specification<Problem> createSpecification(Map searchMap) {
        
        return new Specification<Problem>() {
            
            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 标题
                if (searchMap.get("title")!=null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%"+(String)searchMap.get("title")+"%"));
                }
                // 内容
                if (searchMap.get("content")!=null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%"+(String)searchMap.get("content")+"%"));
                }
                // 用户ID
                if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
                    predicateList.add(cb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
                }
                // 昵称
                if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
                    predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
                }
                // 是否解决
                if (searchMap.get("solve")!=null && !"".equals(searchMap.get("solve"))) {
                    predicateList.add(cb.like(root.get("solve").as(String.class), "%"+(String)searchMap.get("solve")+"%"));
                }
                // 回复人昵称
                if (searchMap.get("replyname")!=null && !"".equals(searchMap.get("replyname"))) {
                    predicateList.add(cb.like(root.get("replyname").as(String.class), "%"+(String)searchMap.get("replyname")+"%"));
                }
                
                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
                
            }
        };
        
    }
}
