package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用于User相关的数据库操作
 */
// Mapper可以使mybatis-spring-boot-starter自动映射
@Mapper
@Component
public interface UserDao  {
    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    UserEntity findUserById(@Param("id") int id);                                              //    @Param中定义的id对应了SQL中的#{id}

    /**
     * 获得全部用户
     *
     * @return
     */
    @Select("SELECT * FROM user")
    List<UserEntity> findAllUser();

    /**
     * 传入User对象 增加user
     *
     * @param userEntity
     * @return
     */
    @Insert("INSERT INTO user(create_time,password,nick_name,true_name,age,mobile,email) " +
            "VALUES(#{create_time},#{password},#{nick_name},#{true_name},#{age},#{mobile},#{email})")
    int addUser(UserEntity userEntity);

    /**
     * 以修改昵称为例进行update
     *
     * @param userEntity
     */
    @Update("UPDATE user SET id=#{id} WHERE nick_name=#{nickName}")
    int update(UserEntity userEntity);

    /**
     * 根据用户id删除用户
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM user WHERE id =#{id}")
    int delete(Long id);

    /**
     * 通过手机号查找到用户
     * @param phone
     * @return
     */
    @Select("SELECT * FROM user WHERE mobile = #{phone}")
    UserEntity findUserByPhone(String phone);
}
