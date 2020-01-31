package com.chernyak.userservice.service.impl;

import com.chernyak.userservice.entity.Goal;
import com.chernyak.userservice.entity.Role;
import com.chernyak.userservice.entity.User;
import com.chernyak.userservice.entity.UserParameters;
import com.chernyak.userservice.exception.EntityNotFoundException;
import com.chernyak.userservice.repository.RoleRepository;
import com.chernyak.userservice.repository.UserParameterRepository;
import com.chernyak.userservice.repository.UserRepository;
import com.chernyak.userservice.service.ImageStore;
import com.chernyak.userservice.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private ImageStore imageStore;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserParameterRepository userParameterRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           ImageStoreInFileSystem imageStoreInFileSystem,
                           UserParameterRepository userParameterRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.imageStore = imageStoreInFileSystem;
        this.userParameterRepository = userParameterRepository;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", id));
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "login", login));
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        if (users.size() <= 0) {
            throw new EntityNotFoundException(User.class, "all", "");
        }
        return users;
    }

    @Override
    public User update(String id, User pUser) {
        User user = findById(id);
        BeanUtils.copyProperties(pUser, user, "id", "password");
        userParameterRepository.save(new UserParameters(id, pUser.getWeight(), pUser.getHeight()));
        return userRepository.save(user);
    }

    @Override
    public User save(User pUser) {
        Role role = roleRepository.findByRole("USER").orElseThrow(() -> new EntityNotFoundException(Role.class, "role", "USER"));
        pUser.setRole(role);
        String password = pUser.getPassword() != null ? pUser.getPassword() : User.DEFAULT_USER_PASSWORD;
        pUser.setPassword(bCryptPasswordEncoder.encode(password));
        User createdUser = userRepository.save(pUser);
        userParameterRepository.save(new UserParameters(createdUser.getId(), pUser.getWeight(), pUser.getHeight()));
        return createdUser;
    }

    @Override
    public void delete(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", id));
        userRepository.delete(user);
    }

    @Override
    public User updateUserPhoto(String id, MultipartFile file) throws IOException {
        String imageName = UUID.randomUUID().toString() + ".jpg";
        Resource resource = imageStore.save(file, imageName);
        User user = findById(id);
        user.setAvatar(resource.getURL().toString());
        return userRepository.save(user);
    }

    @Override
    public void resetPassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public List<UserParameters> getUserParametersHistory(String id, LocalDate from, LocalDate to) {
        Sort sort = Sort.by("date");
        return userParameterRepository.getUserParametersForPeriod(id, from, to, sort);
    }

    @Override
    public User updateUserParameters(String id, UserParameters userParameters) {
        User user = findById(id);
        user.setHeight(userParameters.getHeight());
        user.setWeight(userParameters.getWeight());
        userParameterRepository.save(userParameters);
        return userRepository.save(user);
    }

    @Override
    public User setUserGoal(String id, Goal goal) {
        User user = findById(id);
        user.setGoal(goal);
        return userRepository.save(user);
    }
}
