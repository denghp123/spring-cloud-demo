package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author dhp
 * @Date 2020/4/26 22:58
 * @Version 1.0
 */
//@SpringBootApplication
//@EnableDiscoveryClient//开启注册中心客户端
//@EnableCircuitBreaker//开启熔断器
@SpringCloudApplication
@EnableFeignClients
public class UserConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class);
    }

//    @Bean
//    @LoadBalanced //开启负载均衡
//    public RestTemplate getRestTemplate(){
//
//        return new RestTemplate();
//    }
}
