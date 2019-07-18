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
import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;

/**
 * 描述：控制层
 *
 * @author xielei
 * @date 2019/07/18
 */

@RestController
@RequestMapping("/recruit")
public class RecruitController {
    
    @Autowired
    private RecruitService recruitService;
    
    /**
     * 查询全部
     * @return
     */
    @GetMapping
    public Result<List> findAll() {
        List<Recruit> recruitList = recruitService.findAll();
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, recruitList);
    }
    
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Recruit> findById(@PathVariable("id") String id){
        Recruit recruit = recruitService.findById(id);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, recruit);
    }
    
    
    /**
     * 新增
     * @param recruit
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Recruit recruit) {
        recruitService.add(recruit);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**r
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping
    public Result deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.FAIL, null);
        }
        recruitService.deleteById(id);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 修改
     * @param recruit
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Recruit recruit) {
        recruitService.update(recruit);
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
        Page<Recruit> pageList = recruitService.searchMap(searchMap, page, size);
        PageResult<Recruit> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
        return new Result(true,StatusCode.OK.getCode(),HttpReturnMessage.SUCCESS, pageResult);
    }
    
    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @PostMapping(value="/search")
    public Result findSearch( @RequestBody Map searchMap){
        List<Recruit> resultList = recruitService.searchMap(searchMap);
        return new Result(true,StatusCode.OK.getCode(),HttpReturnMessage.SUCCESS, resultList);
    }
}
