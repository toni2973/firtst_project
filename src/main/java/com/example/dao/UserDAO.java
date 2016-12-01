package com.example.dao;


import com.example.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
@Mapper
@Repository (value = "UserDAO")

public interface UserDAO {

    // 插入一行新数据

    void insertUser(UserEntity userEntity);


    // 根据id查询用户
    UserEntity findById(@Param("id") int id);


    List<UserEntity> getAll();



    void deleteUser(@Param("id") int id);


    void update(UserEntity userEntity);


    UserEntity findByopenId(@Param("openid") String openid);


    void login(UserEntity userEntity);

}