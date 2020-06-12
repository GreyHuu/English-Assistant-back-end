package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAllUser();



    UserEntity findUserById(int id);

    UserEntity findUserByPhone(String phone);

    int addUser(UserEntity userEntity);

    int update(CurrentUser currentUser);

    int delete(Long id);
}
