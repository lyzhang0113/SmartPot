package com.doby2333.IED.service;

import com.doby2333.IED.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // returns true if the given username and password is valid
    public boolean validateUser(String username, String password) {
        return userMapper.validate(username, password) != 0;
    }

    public Long getID(String username, String password) {
        return userMapper.getID(username, password);
    }

    public boolean findID(String id) {
        if (id == null) return false;
        return userMapper.findID(Long.parseLong(id)) != 0;
    }

    // return true if the user registers successfully
    // return false if registration fail: duplicate username
    public boolean register(String username, String password) {
        // find if username exists
        int countCurrUser = userMapper.countUser(username);
        if (countCurrUser != 0) return false;
        userMapper.register(username, password);
        return true;
    }
}
