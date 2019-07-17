package com.tensquare.common.entity;

/**
 * 描述：
 *
 * @author xielei
 * @date 2019/07/17
 */
public enum StatusCode {
    
    //成功
    OK(20000),
    
    //失败
    ERROR(20001),
    
    //用户名或密码错误
    LOGINERROR(20002),
    
    //权限不足
    ACCESSERROR(20003),
    
    //远程调用失败
    EMOTEERROR(20004),
    
    //重复操作
    REPERROR(20005),
    ;
    
    private Integer code;
    
    public Integer getCode() {
        return code;
    }
    
    private StatusCode(Integer code) {
        this.code = code;
    }
    
}
