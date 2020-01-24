package com.chernyak.authservice.client;

import com.chernyak.authservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {

    @GetMapping(value = "/{id}")
    User getUser(@PathVariable String id);

    @GetMapping(value = "/login/{login}")
    User getByLogin(@PathVariable String login);

    @PostMapping(value = "")
    User save(@RequestBody User user);
}
