package com.ujiuye.service;

import com.ujiuye.pojo.Course;
import com.ujiuye.utils.PageUtils;

import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-23-11:08
 */
public interface CourseService {
    /**
     * 添加课程
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 模糊+分页查询课程信息
     * @param pageSize
     * @param currentPage
     * @param search
     * @return
     */
    PageUtils findAll(String pageSize, String currentPage, String search);

    /**
     * 修改课程信息
     * @param c
     * @return
     */
    int updateCourses(Course c);

    /**
     * 批量删除
     * @param cids
     */
    boolean delAll(String cids);

    /**
     * 查询所有课程信息
     * @return
     */
    List<Course> findAllCourses();
}
