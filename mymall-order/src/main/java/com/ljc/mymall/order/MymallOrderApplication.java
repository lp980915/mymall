package com.ljc.mymall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MymallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymallOrderApplication.class, args);
    }

}
