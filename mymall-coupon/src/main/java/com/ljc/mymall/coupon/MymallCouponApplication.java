package com.ljc.mymall.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 使用nacos作为配置中心统一管理配置
 * 1.引入依赖
 * 2.创建bootstrap.properties
 * spring.application.name=mymall-coupon
 * spring.cloud.nacos.config.server-addr=127.0.0.1:8848
 * 3.给配置中心默认添加一个 应用名.properties(dataId,又叫数据集)  (mymall-coupon.properties)
 * 4.在这个配置文件中添加任何配置
 * 5.@RefreshScope可以动态获取并刷新配置，无需重启服务
 * 6.@Value("${配置项的名字}")获取配置值
 * 7.如果配置中心和当前的本地配置文件中有相同配置，优先使用配置中心
 *
 * 细节
 * 1.命名空间：配置隔离
 * 默认：public（保留空间）：默认新增的所有配置都在public空间
 *    开发，测试，生产:利用命名空间来做环境隔离
 *    注意：在bootstrap.properties上配置我们需要使用哪个命名空间下的配置
 *    spring.cloud.nacos.config.namespace=7c868de2-6fe2-4a7e-bfbc-9bfdd7c62cf1
 *    可以每一个微服务之间相互隔离配置，每一个微服务都创建自己的命名空间，只加载自己命名空间下的所有配置
 * 2.配置集：所有配置的集合
 * 3.配置集ID：类似配置文件名
 *     Data ID：类似配置文件名
 * 4.配置分组：
 *     默认所有的配置集都属于：DEFAULT_GROUP
 *     1111，618，1212等，可以随意定义组名
 *
 * 5.本项目分组：
 *     每个微服务创建自己的命名空间，使用配置分组区分环境，dev,test,prod
 *
 * 同时加载多个配置集
 *   微服务任何配置文件，配置信息都可以放在配置中心里
 *   只需在bootstrap.properties中声明加载配置中心中哪些配置文件
 *   //@Value和@ConfigurationProperties。。等方式都可以获取配置文件里的值
 *   配置中心优先级大于本地配置文件
 *
 */

@SpringBootApplication
//开启服务的注册与发现功能
@EnableDiscoveryClient
public class MymallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(MymallCouponApplication.class, args);
    }

}
