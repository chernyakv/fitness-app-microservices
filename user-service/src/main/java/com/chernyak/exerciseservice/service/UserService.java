package com.chernyak.exerciseservice.service;

import com.chernyak.exerciseservice.entity.User;
import com.chernyak.exerciseservice.entity.UserParameters;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User findByLogin(String login);
    User findById(String id);
    List<User> getAll();
    List<UserParameters> getUserParametersHistory(String id, LocalDate from, LocalDate to);
    User save(User user);
    User update(String id, User pUser);
    User updateUserParameters(String id, UserParameters userParameters);
    void resetPassword(User user, String newPassword);
    void delete(String id);
    User updateUserPhoto(String id, MultipartFile file) throws IOException;
}
