package com.chernyak.zuulservice.client;

import com.chernyak.zuulservice.dto.User;
import com.chernyak.zuulservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {

    @GetMapping(value = "/{id}")
    User getUser(@PathVariable String id);

    @GetMapping(value = "/login/{login}")
    UserDTO getByLogin(@PathVariable String login);
}
