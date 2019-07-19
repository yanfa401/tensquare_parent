package com.tensquare.qa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.tensquare.qa.pojo.Reply;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/18
 */
public interface ReplyService {
    
    /**
     * 查询全部
     * @return
     */
    List<Reply> findAll();
    
    /**
     * 根据id查
     * @param id
     * @return
     */
    Reply findById(String id);
    
    /**
     * 新增
     * @param reply
     */
    void add(Reply reply);
    
    /**
     * 根据 id删除
     * @param id
     */
    void deleteById(String id);
    
    /**
     * 修改
     * @param reply
     */
    void update(Reply reply);
    
    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    List<Reply> searchMap(Map whereMap);
    
    /**
     * 分页+条件查询
     * @param whereMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page<Reply> searchMap(Map whereMap, int pageIndex, int pageSize);
}
