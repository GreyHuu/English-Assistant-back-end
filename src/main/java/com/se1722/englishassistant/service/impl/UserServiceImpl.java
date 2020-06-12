package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.UserDao;
import com.se1722.englishassistant.entity.UserEntity;
import com.se1722.englishassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<UserEntity> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public UserEntity findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public UserEntity findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    @Override
    public int addUser(UserEntity userEntity) {
        return userDao.addUser(userEntity);
    }

    @Override
    public int update(UserEntity userEntity) {
        return userDao.update(userEntity);
    }

    @Override
    public int delete(Long id) {
        return userDao.delete(id);
    }
}
