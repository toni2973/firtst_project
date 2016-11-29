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
@Repository
public interface UserDAO {

    // 插入一行新数据
    @Insert("INSERT INTO user(username,phone,password,address) VALUES(#{username},#{phone},#{password},#{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 将自动生成的主键重新设置到实体中，便于业务逻辑处理
    void insertUser(UserEntity userEntity);


    // 根据id查询用户
    @Select("SELECT * FROM user WHERE id = #{id} Limit 1")
    @ResultType(UserEntity.class)
    UserEntity findById(@Param("id") int id);

    @Select("SELECT * FROM user Limit 1,5")
    @ResultType(List.class)
    List<UserEntity> getAll();


    @Delete("delete from user where id=#{id}")
    void deleteUser(@Param("id") int id);

    @Update( {"update user set username=#{username},phone=#{phone},password=#{password},address=#{address}",
            "where id=#{id}" })
    void update(UserEntity userEntity);

    @Select("SELECT * FROM user WHERE openid = #{openid}")
    @ResultType(UserEntity.class)
    UserEntity findByopenId(@Param("openid") String openid);

    @Insert("INSERT INTO user(openid,nickname) VALUES(#{openid},#{nickname})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 将自动生成的主键重新设置到实体中，便于业务逻辑处理
    void login(UserEntity userEntity);

}