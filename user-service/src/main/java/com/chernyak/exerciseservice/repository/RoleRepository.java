package com.chernyak.exerciseservice.repository;

import com.chernyak.exerciseservice.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByRole(String role);
}
