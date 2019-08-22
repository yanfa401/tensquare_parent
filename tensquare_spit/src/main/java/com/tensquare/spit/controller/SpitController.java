package com.tensquare.spit.controller;

import java.util.List;

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

import com.tensquare.common.entity.HttpReturnMessage;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/08/12
 */
@RestController
@RequestMapping("/spit")
public class SpitController {
    
    @Autowired
    private SpitService spitService;
    
    @GetMapping
    public Result findAll() {
        List<Spit> spitList = spitService.findAll();
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, spitList);
    }
    
    
    @GetMapping("/{id}")
    public Result findOne(@PathVariable("id") String id) {
        Spit result = spitService.findById(id);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, result);
    }
    
    @PostMapping
    public Result add(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    @DeleteMapping("/{ud}")
    public Result deleteById(@PathVariable("id") String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    @PutMapping("/{id}")
    public Result update(@RequestBody Spit spit, @PathVariable String id) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 根据上级id查询吐槽分页数据
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/comment/{parentId}/{pageIndex}/{pageSize}")
    public Result findByParentid(@PathVariable("parentId") String parentId,
                                 @PathVariable("pageIndex") int pageIndex,
                                 @PathVariable("pageSize") int pageSize) {
        Page<Spit> spitPage = spitService.findByParentid(parentId, pageIndex, pageSize);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, spitPage.getContent());
    }
    
    /**
     * 点赞
     *
     * @param id
     * @return
     */
    @PutMapping("/thumbup/{id}")
    public Result updateThumbup(@PathVariable("id") String id) {
        spitService.updateThumbup(id);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
}
