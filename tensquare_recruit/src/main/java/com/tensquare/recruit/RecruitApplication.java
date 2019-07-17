package com.tensquare.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述：启动类
 *
 * @author xielei
 * @date 2019/07/17
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.tensquare"})
public class RecruitApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class);
    }
}
