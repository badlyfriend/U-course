package com.ujiuye.dao;

import com.ujiuye.pojo.ResultVo;
import com.ujiuye.pojo.User;
import com.ujiuye.utils.MyUtils;
import com.ujiuye.utils.PageUtils;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-21-11:44
 */
public class UserDaoImpl implements UserDao{
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
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

    /**
     * 查询条数
     * @param search
     * @return
     */
    @Override
    public int querySumCount(String search) {
        String sql = "select count(*) from user ";
        if(search != null && !"".equals(search)) {
            sql += " where username like '%"+ search +"%' ";
        }
        try {
            return (int)((long)MyUtils.QR.query(sql,new ScalarHandler<>()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 分页查询
     * @param pu
     * @param search
     * @return
     */
    @Override
    public List<User> findByPage(PageUtils pu, String search) {
        String sql = "select * from user ";
        if(search != null && !"".equals(search)) {
            sql += " where username like '%"+ search +"%' ";
        }
        sql += " limit ?,?";
        try {
            return MyUtils.QR.query(sql, new BeanListHandler<>(User.class), pu.getIndex(), pu.getPageCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     * @param u
     * @return
     */
    @Override
    public int addUsers(User u) {
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?,?)";
        Object[] obj = {null,u.getName(),u.getPhone(),u.getAge(),u.getSex(),u.getUsername(),u.getPassword(),u.getStatus(),u.getCreatetime(),u.getRole(),null};
        try {
            return MyUtils.QR.update(sql,obj);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 修改
     * @param u
     * @return
     */
    @Override
    public int updateUsers(User u) {
        String sql = "update user set name = ? , phone = ? , age = ? , sex = ? , username = ? , password = ? , status = ? , role = ? where uid = ?";
        Object[] arr = {u.getName() , u.getPhone() , u.getAge() , u.getSex() , u.getUsername() , u.getPassword() , u.getStatus() , u.getRole() , u.getUid()};
        try {
            return MyUtils.QR.update(sql,arr);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public int delAll(String s) {
        String sql = "delete from user where uid = ?";
        try {
            return MyUtils.QR.update(sql , s);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
