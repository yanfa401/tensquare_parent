package com.tensquare.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/09/26
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    /**
     * 查询全部
     *
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<Admin> adminList = adminService.findAll();
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, adminList);
    }
    
    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@Param("id") String id) {
        Admin admin = adminService.findById(id);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, admin);
    }
    
    /**
     * 新增
     *
     * @param admin
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(String id) {
        adminService.deleteById(id);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 修改
     *
     * @param admin
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, null);
    }
    
    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @PostMapping(value = "/search")
    public Result findSearch(@RequestBody Map searchMap) {
        List<Admin> res = adminService.findSearch(searchMap);
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, res);
    }
    
    
    /**
     * 根据条件查询 + 分页
     *
     * @param pageIndex 页码
     * @param pageSize  页大小
     * @param searchMap 筛选条件
     * @return
     */
    @PostMapping(value = "/page/{pageIndex}/{pageSize}")
    public Result findPage(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize, @RequestBody Map searchMap) {
        Page<Admin> page = adminService.searchMap(searchMap, pageIndex, pageSize);
        PageResult<Admin> res = new PageResult<>();
        res.setTotal(page.getTotalElements());
        res.setRows(page.getContent());
        return new Result(true, StatusCode.OK.getCode(), HttpReturnMessage.SUCCESS, res);
    }
    
}
