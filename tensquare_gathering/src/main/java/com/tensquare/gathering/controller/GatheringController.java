package com.tensquare.gathering.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tensquare.common.entity.HttpReturnMessage;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.service.GatheringService;

/**
 * gathering控制器层
 *
 * @author Administrator
 */
@RestController
@CrossOrigin
@RequestMapping("/gathering")
public class GatheringController {
    
    @Autowired
    private GatheringService gatheringService;
    
    
    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Gathering> gatheringList = gatheringService.findAll();
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, gatheringList);
    }
    
    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") String id) {
        Gathering gathering = gatheringService.findById(id);
        return new Result<>(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, gathering);
    }
    
    
    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Gathering> pageList = gatheringService.searchMap(searchMap, page, size);
        PageResult<Gathering> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, pageResult);
    }
    
    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap) {
        List<Gathering> result = gatheringService.searchMap(searchMap);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, result);
    }
    
    /**
     * 增加
     *
     * @param gathering
     */
    @PostMapping
    public Result add(@RequestBody Gathering gathering) {
        gatheringService.add(gathering);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 修改
     *
     * @param gathering
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Gathering gathering, @PathVariable String id) {
        gathering.setId(id);
        gatheringService.update(gathering);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        gatheringService.deleteById(id);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
}
