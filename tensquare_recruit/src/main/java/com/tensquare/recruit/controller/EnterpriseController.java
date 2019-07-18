package com.tensquare.recruit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.common.entity.HttpReturnMessage;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;

/**
 * 描述：控制层
 *
 * @author xielei
 * @date 2019/07/18
 */

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;
    
    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result<List> findAll() {
        List<Enterprise> enterpriseList = enterpriseService.findAll();
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, enterpriseList);
    }
    
    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Enterprise> findById(@PathVariable("id") String id) {
        Enterprise enterprise = enterpriseService.findById(id);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, enterprise);
    }
    
    /**
     * 新增
     * @param enterprise
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Enterprise enterprise) {
        enterpriseService.add(enterprise);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping
    public Result deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.FAIL, null);
        }
        enterpriseService.deleteById(id);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 修改
     * @param enterprise
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Enterprise enterprise) {
        enterpriseService.update(enterprise);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    
    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value="/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Enterprise> pageList = enterpriseService.findSearch(searchMap, page, size);
        PageResult<Enterprise> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
        return new Result(true,StatusCode.OK.getCode(),HttpReturnMessage.SUCCESS, pageResult);
    }
    
    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @PostMapping(value="/search")
    public Result findSearch( @RequestBody Map searchMap){
        List<Enterprise> enterpriseList = enterpriseService.findSearch(searchMap);
        return new Result(true,StatusCode.OK.getCode(),HttpReturnMessage.SUCCESS, enterpriseList);
    }
    
    
}
