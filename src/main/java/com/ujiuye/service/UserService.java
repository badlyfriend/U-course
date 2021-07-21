package com.ujiuye.service;

import com.ujiuye.pojo.User;

/**
 * @Author Bob
 * @Create 2021-07-21-11:22
 */
public interface UserService {
    User userLogin(String username, String password);
}
