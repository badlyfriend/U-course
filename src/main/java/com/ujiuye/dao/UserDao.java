package com.ujiuye.dao;

import com.ujiuye.pojo.ResultVo;
import com.ujiuye.pojo.User;
import com.ujiuye.utils.PageUtils;

import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-21-11:22
 */
public interface UserDao {
    User userLogin(String username, String password);

    int querySumCount(String search);

    List<User> findByPage(PageUtils pu, String search);

    int addUsers(User user);

    int updateUsers(User user);

    int delAll(String s);
}
