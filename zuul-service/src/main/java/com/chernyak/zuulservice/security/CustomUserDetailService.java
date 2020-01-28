package com.chernyak.zuulservice.security;

import com.chernyak.zuulservice.client.UserServiceFeignClient;
import com.chernyak.zuulservice.dto.UserDTO;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("customUserDetailsService")
public class CustomUserDetailService implements UserDetailsService {

    UserServiceFeignClient userServiceFeignClient;

    @Autowired
    public CustomUserDetailService(UserServiceFeignClient userServiceFeignClient) {
        this.userServiceFeignClient = userServiceFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException, FeignException {
        UserDTO user = userServiceFeignClient.getByLogin(s);
        return new User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserDTO user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getRole()));
        return authorities;
    }
}
