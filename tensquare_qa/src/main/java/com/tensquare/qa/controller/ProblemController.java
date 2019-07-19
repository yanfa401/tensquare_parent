package com.tensquare.qa.controller;

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
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;

/**
 * 描述：控制层
 *
 * @author xielei
 * @date 2019/07/19
 */

@RestController
@RequestMapping("/problem")
public class ProblemController {
    
    @Autowired
    private ProblemService problemService;
    
    /**
     * 查询全部
     * @return
     */
    @GetMapping
    public Result<List> findAll() {
        List<Problem> problemList = problemService.findAll();
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, problemList);
    }
    
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Problem> findById(@PathVariable("id") String id){
        Problem problem = problemService.findById(id);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, problem);
    }
    
    
    /**
     * 新增
     * @param problem
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Problem problem) {
        problemService.add(problem);
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
        problemService.deleteById(id);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 修改
     * @param problem
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Problem problem) {
        problemService.update(problem);
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
        Page<Problem> pageList = problemService.searchMap(searchMap, page, size);
        PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
        return new Result(true,StatusCode.OK.getCode(),HttpReturnMessage.SUCCESS, pageResult);
    }
    
    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @PostMapping(value="/search")
    public Result findSearch(@RequestBody Map searchMap){
        List<Problem> resultList = problemService.searchMap(searchMap);
        return new Result(true,StatusCode.OK.getCode(),HttpReturnMessage.SUCCESS, resultList);
    }
    
    /**
     * 根据标签ID查询最新问题列表
     * @param lableId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/newlist/{labelId}/{pageIndex}/{pageSize}")
    public Result findNewListByLabelId(@PathVariable("labelId") String lableId,
                                       @PathVariable("pageIndex")int pageIndex,
                                       @PathVariable("pageSize")int pageSize) {
        Page<Problem> page = problemService.findNewListByLabelId(lableId, pageIndex, pageSize);
        PageResult<Problem> pageResult = new PageResult<>(page.getTotalElements(), page.getContent());
        return new Result(true,StatusCode.OK.getCode(),HttpReturnMessage.SUCCESS, pageResult);
    }
    
    
    /**
     * 根据标签ID查询热门问题列表
     * @param lableId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/hotlist/{labelId}/{pageIndex}/{pageSize}")
    public Result findHotListByLabelId(@PathVariable("labelId") String lableId,
                                       @PathVariable("pageIndex")int pageIndex,
                                       @PathVariable("pageSize")int pageSize) {
        Page<Problem> page = problemService.findHotListByLabelId(lableId, pageIndex, pageSize);
        PageResult<Problem> pageResult = new PageResult<>(page.getTotalElements(), page.getContent());
        return new Result(true,StatusCode.OK.getCode(),HttpReturnMessage.SUCCESS, pageResult);
    }
    
    /**
     * 根据标签ID查询等待回答列表
     * @param lableId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/waitlist/{labelId}/{pageIndex}/{pageSize}")
    public Result findWaitListByLabelId(@PathVariable("labelId") String lableId,
                                       @PathVariable("pageIndex")int pageIndex,
                                       @PathVariable("pageSize")int pageSize) {
        Page<Problem> page = problemService.findWaitListByLabelId(lableId, pageIndex, pageSize);
        PageResult<Problem> pageResult = new PageResult<>(page.getTotalElements(), page.getContent());
        return new Result(true,StatusCode.OK.getCode(),HttpReturnMessage.SUCCESS, pageResult);
    }

}
