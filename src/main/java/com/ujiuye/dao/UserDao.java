package com.ujiuye.dao;

import com.ujiuye.pojo.User;

/**
 * @Author Bob
 * @Create 2021-07-21-11:22
 */
public interface UserDao {
    User userLogin(String username, String password);
}
