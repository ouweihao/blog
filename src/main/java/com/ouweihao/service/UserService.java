package com.ouweihao.service;

import com.ouweihao.po.User;

public interface UserService {

    User checkUser(String userName, String password);

}
