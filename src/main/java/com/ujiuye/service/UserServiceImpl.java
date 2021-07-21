package com.ujiuye.service;

import com.ujiuye.dao.UserDao;
import com.ujiuye.dao.UserDaoImpl;
import com.ujiuye.pojo.User;

/**
 * @Author Bob
 * @Create 2021-07-21-11:44
 */
public class UserServiceImpl implements UserService{

    private UserDao ud = new UserDaoImpl();

    @Override
    public User userLogin(String username, String password) {
        return ud.userLogin(username,password);
    }
}
