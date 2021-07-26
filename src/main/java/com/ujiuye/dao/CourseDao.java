package com.ujiuye.dao;

import com.ujiuye.pojo.Course;
import com.ujiuye.utils.PageUtils;

import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-23-11:07
 */
public interface CourseDao {
    /**
     * 添加课程
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 查询出总页数
     * @param ps
     * @param cp
     * @param search
     * @return
     */
    int querySumCount(int ps, int cp, String search);

    /**
     * 模糊+分页查询全部
     * @param pu
     * @param search
     * @return
     */
    List<Course> findAll(PageUtils pu, String search);

    /**
     * 修改课程信息
     * @param c
     * @return
     */
    int updateCourses(Course c);

    /**
     * 批量删除
     * @param arr
     * @return
     */
    int[] delAll(Object[][] arr);

    /**
     * 通过cid查询相关课程信息
     * @param cids
     * @return
     */
    List<Course> queryAllByCids(String cids);

    /**
     * 查询所有课程信息
     * @return
     */
    List<Course> findAllCourses();
}
