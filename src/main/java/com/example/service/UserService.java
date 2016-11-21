package com.example.service;

/**
 * Created by hhy on 11/19/16.
 */

import com.example.entity.UserEntity;

import java.util.List;

/**
 * UserService 接口类
 **/
public interface UserService {
    // 创建新用户
    boolean createUser(UserEntity userEntity);
    // 根据主键id查找用户
    UserEntity findUserById(int id);

    List<UserEntity> getAll();
}