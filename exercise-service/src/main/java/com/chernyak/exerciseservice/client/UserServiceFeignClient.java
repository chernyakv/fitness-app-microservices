package com.chernyak.exerciseservice.client;

import com.chernyak.exerciseservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {

    @GetMapping(value = "/{id}")
    User getUser(@PathVariable String id);
}
