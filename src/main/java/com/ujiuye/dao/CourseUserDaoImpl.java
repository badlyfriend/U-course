package com.ujiuye.dao;

import com.ujiuye.pojo.Course;
import com.ujiuye.pojo.CourseUser;
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
 * @Create 2021-07-26-11:34
 */
public class CourseUserDaoImpl implements CourseUserDao{
    /**
     * 查询总条数
     * @param search
     * @return
     */
    @Override
    public int querySumCountBySearch(String search) {
        String sql = "select count(*) from course_user cu inner join user u inner join course c on cu.uid = u.uid and cu.cid = c.cid";
        if(search != null && !"".equals(search)) {
            sql += " and u.name like '%"+ search +"%'";
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
    public List<CourseUser> findAllByPage(PageUtils pu, String search) {
        String sql = "select * from course_user cu inner join user u on cu.uid = u.uid ";
        if(search != null && !"".equals(search)) {
            sql += " and u.name like '%"+ search +"%'";
        }
        sql += " limit ?,?";
        try {
            return MyUtils.QR.query(sql,new BeanListHandler<>(CourseUser.class),pu.getIndex(),pu.getPageCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Course queryCourseByCid(int cid) {
        String sql = "select * from course where cid = ?";
        try {
            return MyUtils.QR.query(sql , new BeanHandler<>(Course.class) , cid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 通过uid查询user
     * @param uid
     * @return
     */
    @Override
    public User queryUserByUid(int uid) {
        String sql = "select * from user where uid = ?";
        try {
            return MyUtils.QR.query(sql , new BeanHandler<>(User.class) , uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 通过id修改cid
     * @param cid
     * @param id
     * @return
     */
    @Override
    public int updateCidById(String cid, String id) {
        String sql = "update course_user set cid = ? where id = ?";
        try {
            return MyUtils.QR.update(sql , cid , id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 通过uid修改name
     * @param uid
     * @param name
     * @return
     */
    @Override
    public int updateNameByUid(String uid, String name) {
        String sql = "update user set name = ? where uid = ?";
        try {
            return MyUtils.QR.update(sql , name , uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 批量删除
     * @param arr
     * @return
     */
    @Override
    public int[] delCourseUser(Object[][] arr) {
        String sql = "delete from course_user where id = ?";
        try {
            return MyUtils.QR.batch(sql , arr);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new int[0];
    }
}
