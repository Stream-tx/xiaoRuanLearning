package com.example.onlinetoolsservice.feign;

import com.example.onlinetoolsservice.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname accountInterface
 * @Description TODO
 * @Author asus
 * @Date 2022-05-10 15:30
 * @Version 1.0
 **/
@FeignClient("accountservice")
public interface AccountInterface {
    @PostMapping("account/user/accountInfo")
    public Result accountInfo(@RequestParam Long userId);
}
