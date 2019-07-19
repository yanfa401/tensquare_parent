package com.tensquare.article.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述：web启动通用配置
 *
 * @author xielei
 * @date 2019/07/19
 */
@SpringBootConfiguration
@ComponentScan(basePackages = "com.tensquare")
public class WebConfig {

}
