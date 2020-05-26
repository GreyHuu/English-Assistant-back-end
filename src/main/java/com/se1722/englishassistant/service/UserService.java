package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<UserEntity> findAllUser();

    UserEntity findUserById(int id);

    int addUser(UserEntity userEntity);

    int update(UserEntity userEntity);

    int delete(Long id);
}
