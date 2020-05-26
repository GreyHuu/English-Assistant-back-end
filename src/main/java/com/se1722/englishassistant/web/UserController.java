package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.UserEntity;
import com.se1722.englishassistant.dao.UserDao;
import com.se1722.englishassistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获得全部的用户
     * @return
     */
    @GetMapping("/get-all-users")
    public List<UserEntity> getAllUser(){
        return userService.findAllUser();
    }
}
