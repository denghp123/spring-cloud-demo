package cn.itcast.web;

import cn.itcast.client.UserClient;
import cn.itcast.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * @Author dhp
 * @Date 2020/4/26 23:01
 * @Version 1.0
 */
@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "defaultAllFallback" )//在类上，指明统一失败降级的方法
public class UserContromerController {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private UserClient userClient;

//    @GetMapping("/{id}")
//    @HystrixCommand(fallbackMethod = "defaultAllFallback")
//    public String getUser(@PathVariable("id")Long id){
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//        //TODO 通过负载均衡算法获取服务实例
//        ServiceInstance serviceInstance = instances.get(0);
//        String url = "http://%s:%s/user/%s";
//        String u = String.format(url, serviceInstance.getHost(), serviceInstance.getPort(), id.toString());
//
//        return restTemplate.getForObject(u,String.class);
//
//    }

//    @GetMapping("/{id}")
//    @HystrixCommand(fallbackMethod = "defaultAllFallback")
//    public String getUser(@PathVariable("id")Long id){
//        String u = "http://user-service/user/" + id;
//
//        return restTemplate.getForObject(u,String.class);
//
//    }


    @GetMapping("/{id}")
//    @HystrixCommand(fallbackMethod = "defaultAllFallback")
    @HystrixCommand
    public String getUser(@PathVariable("id")Long id){

        return userClient.queryById(id);

    }


    //服务降级逻辑
//    public String defaultAllFallback (Long id){
//     return "对不急，网络太拥挤了！";
//    }


    //统一完成服务降级逻辑  参数不要求相同
       public String defaultAllFallback (){
    return "对不急，网络太拥挤了！";
    }


}
