package com.ouweihao.service;

import com.ouweihao.dao.UserRepository;
import com.ouweihao.po.User;
import com.ouweihao.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String userName, String password) {
        User user = userRepository.findByUsernameAndPassword(userName, MD5Utils.code(password));
        return user;
    }
}
