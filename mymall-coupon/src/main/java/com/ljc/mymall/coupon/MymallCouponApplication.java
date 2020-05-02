package com.ljc.mymall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//开启服务的注册与发现功能
@EnableDiscoveryClient
public class MymallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymallCouponApplication.class, args);
    }

}
