package com.tensquare.base.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述：统一异常处理类
 *
 * @author xielei
 * @date 2019/07/17
 */

@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {
    
    /**
     * 处理 Exception.class类型异常,这里图省事这么干,应该捕获具体异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        log.error(e.toString());
        return new Result<>(false, StatusCode.ERROR.getCode(), e.getMessage(), null);
    }
}
