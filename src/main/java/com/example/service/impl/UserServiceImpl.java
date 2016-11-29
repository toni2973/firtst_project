package com.example.service.impl;

import com.example.dao.UserDAO;
import com.example.entity.UserEntity;
import com.example.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hhy on 11/19/16.
 */
@Service("userService") // 命名bean别名
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    /**
     *  创建新用户
     * @param userEntity
     * @return
     */
    @Override
    public boolean createUser(UserEntity userEntity) {
        userDAO.insertUser(userEntity);
        if (userEntity.getId() > 0) {
            return true;
        }
        return false;
    }


    /**
     *  根据id查询用户
     * @param id
     * @return
     */
    @Override
    public UserEntity findUserById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public List<UserEntity> getAll() {
        return userDAO.getAll();
    }

    @Override
    public int login(JSONObject json) {
        String openid=json.getString("openid");
        String nickname=json.getString("nickname");
        UserEntity user=userDAO.findByopenId(openid);
        if (user==null){
            user=new UserEntity();
            user.setOpenid(openid);
            user.setNickname(nickname);
            userDAO.login(user);
        }
        return user.getId();
    }

}
