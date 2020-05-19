package cn.itcast.client;

import cn.itcast.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author dhp
 * @Date 2020/5/13 14:41
 * @Version 1.0
 */

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    String queryById(@PathVariable("id") Long id);
}
