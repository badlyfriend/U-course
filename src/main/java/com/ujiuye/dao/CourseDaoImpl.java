package com.ujiuye.dao;

import com.ujiuye.pojo.Course;
import com.ujiuye.utils.MyUtils;
import com.ujiuye.utils.PageUtils;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-23-11:07
 */
public class CourseDaoImpl implements CourseDao{
    /**
     * 添加课程
     * @param c
     * @return
     */
    @Override
    public int addCourse(Course c) {
        String sql = "insert into course values(?,?,?,?,?,?,?,?,?)";
        Object[] arr = {null , c.getCourseName() , c.getDescs() , c.getCourseType() ,
        c.getCourseImage() , c.getCourseVideo() , c.getCoursePrice() , c.getStatus() , new Date()};
        try {
            return MyUtils.QR.update(sql , arr);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    /**
     * 查询总页数
     * @param ps
     * @param cp
     * @param search
     * @return
     */
    @Override
    public int querySumCount(int ps, int cp, String search) {
        String sql = "select count(*) from course ";
        if(search != null && !"".equals(search)) {
            sql += " where courseName like '%"+ search +"%'";
        }
        try {
            return (int)((long)MyUtils.QR.query(sql , new ScalarHandler<>()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Course> findAll(PageUtils pu, String search) {
        String sql = "select * from course ";
        if(search != null && !"".equals(search)) {
            sql += " where courseName like '%"+ search +"%' ";
        }
        sql += " limit ?,?";
        try {
            return MyUtils.QR.query(sql , new BeanListHandler<>(Course.class) , pu.getIndex() , pu.getPageCount());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 修改课程信息
     * @param c
     * @return
     */
    @Override
    public int updateCourses(Course c) {
        String sql = "update course set courseName = ? , descs = ? , " +
                "courseType = ? , courseImage = ? , courseVideo = ? , " +
                "coursePrice = ? , status = ? where cid = ?";
        Object[] arr = {c.getCourseName() , c.getDescs() , c.getCourseType(),
        c.getCourseImage() , c.getCourseVideo() , c.getCoursePrice() , c.getStatus() , c.getCid()};
        try {
            return MyUtils.QR.update(sql,arr);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Course> queryAllByCids(String cids) {
        String sql = "select * from course where cid in ('"+ cids +"')";
        try {
            return MyUtils.QR.query(sql,new BeanListHandler<>(Course.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 批量删除
     * @param arr
     * @return
     */
    @Override
    public int[] delAll(Object[][] arr) {
        String sql = "delete from course where cid = ?";
        try {
            return MyUtils.QR.batch(sql,arr);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new int[0];
    }
    /**
     * 查询所有课程信息
     * @return
     */
    @Override
    public List<Course> findAllCourses() {
        String sql = "select * from course";
        try {
            return MyUtils.QR.query(sql , new BeanListHandler<>(Course.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
