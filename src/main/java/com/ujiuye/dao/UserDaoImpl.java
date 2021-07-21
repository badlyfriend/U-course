package com.ujiuye.dao;

import com.ujiuye.pojo.User;
import com.ujiuye.utils.MyUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @Author Bob
 * @Create 2021-07-21-11:44
 */
public class UserDaoImpl implements UserDao{
    @Override
    public User userLogin(String username, String password) {
        String sql = "select * from user where username = ? and password = ? and status = 1";
        try {
            return MyUtils.QR.query(sql , new BeanHandler<>(User.class),username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
