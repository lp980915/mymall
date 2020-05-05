package com.ljc.mymall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 逻辑删除
 * 1.配置全局逻辑删除规则（可省略）
 * 2.配置逻辑删除bean（新版本不需要了）
 * 3.给实体类相应字段加上注解@TableLogic
 */
@MapperScan("com.ljc.mymall.product.dao")
@SpringBootApplication
@EnableDiscoveryClient
public class MymallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymallProductApplication.class, args);
    }

}
