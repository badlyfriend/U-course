package com.ujiuye.service;

import com.ujiuye.pojo.User;
import com.ujiuye.utils.PageUtils;

import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-21-11:22
 */
public interface UserService {

    User userLogin(String username, String password);

    PageUtils findByPage(String pageSize, String currentPage, String search);

    int addUsers(User user);

    int updateUsers(User user);

    Boolean delAll(String uid);
}
