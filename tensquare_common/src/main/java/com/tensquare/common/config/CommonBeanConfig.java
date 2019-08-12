package com.tensquare.common.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tensquare.common.util.IdWorker;


/**
 * 描述：通用一些 bean配置
 *
 * @author xielei
 * @date 2019/07/17
 */
@SpringBootConfiguration
public class CommonBeanConfig {
    
    /**
     * 分布式Id生成器
     * @return
     */
    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker(1, 1);
    }

}
