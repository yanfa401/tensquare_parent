package com.tensquare.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;

import lombok.NonNull;
import util.IdWorker;

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
