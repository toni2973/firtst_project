package com.example.controller;

import com.example.entity.UserEntity;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by hhy on 11/19/16.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public HttpEntity getUserById(@PathVariable("id") Integer id) {
        UserEntity userEntity = userService.findUserById(id);

        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public HttpEntity add(){
        UserEntity userEntity=new UserEntity();
        userEntity.setId(0);
        userEntity.setPassword("123456");
        userEntity.setPhone("12345678902");
        userEntity.setUsername("admin");
        userService.createUser(userEntity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public HttpEntity getAll() {
//        List<UserEntity> userlist = userService.getAll();
//
//        return new ResponseEntity<>(userlist, HttpStatus.OK);
//    }

    /**
     * 创建用户
     * @param userEntity
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity createUser(@RequestBody UserEntity userEntity ) {

        boolean created = userService.createUser(userEntity);
        if (!created) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
