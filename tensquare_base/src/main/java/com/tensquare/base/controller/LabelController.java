package com.tensquare.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;

/**
 * 描述：标签控制层
 *
 * @author xielei
 * @date 2019/07/17
 */
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;
    
    /**
     * 查询全部标签
     * @return
     */
    @GetMapping("/findAll")
    public Result<List> findAll() {
        List<Label> labelList = labelService.findAll();
        Result<List> result = new Result<>(true, StatusCode.OK.getCode(), "查询成功", labelList);
        //Result result = Result.builder().flag(true).code(20000).message("查询成功").data(labelList).build();
        return result;
    }
    
    
    /**
     * 根据id查询标签
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Label> findById(@PathVariable("id") String id) {
        Label label = labelService.findById(id);
        return new Result<>(true, StatusCode.OK.getCode(), "查询成功", label);
    }
    
    
    /**
     * 新增标签
     * @param label
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result<>(true, StatusCode.OK.getCode(), "新增成功", null);
    }
    
    
    /**
     * 根据id删除标签
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") String id) {
        labelService.deleteById(id);
        return new Result<>(true, StatusCode.OK.getCode(), "删除成功", null);
    }
    
    
    /**
     * 修改标签
     * @param label
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Label label) {
        labelService.update(label);
        return new Result<>(true, StatusCode.OK.getCode(), "修改成功", null);
    }
    
    /**
     * 条件查询
     * @param searchMap  查询条件
     * @param currentPage  当前页码
     * @param pageSize  每页展示数量
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result search(@RequestBody Map searchMap, @PathVariable("page") int currentPage, @PathVariable("size") int pageSize) {
        Page<Label> labelList = labelService.findSearch(searchMap, currentPage, pageSize);
        PageResult<Label> pageResult = new PageResult<>(labelList.getTotalElements(), labelList.getContent());
        return new Result<>(true, StatusCode.OK.getCode(), "查询成功", pageResult);
    }
    
    
    
}
