package com.ujiuye.dao;

import com.ujiuye.pojo.Course;
import com.ujiuye.pojo.CourseUser;
import com.ujiuye.pojo.User;
import com.ujiuye.utils.PageUtils;

import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-26-11:33
 */
public interface CourseUserDao {
    /**
     * 查询总条数
     * @param search
     * @return
     */
    int querySumCountBySearch(String search);

    /**
     * 分页查询
     * @param pu
     * @param search
     * @return
     */
    List<CourseUser> findAllByPage(PageUtils pu, String search);

    /**
     * 通过cid查询course
     * @param cid
     * @return
     */
    Course queryCourseByCid(int cid);

    /**
     * 通过uid查询user
     * @param uid
     * @return
     */
    User queryUserByUid(int uid);

    /**
     * 通过id修改cid
     * @param cid
     * @param id
     * @return
     */
    int updateCidById(String cid, String id);

    /**
     * 通过uid修改name
     * @param uid
     * @param name
     * @return
     */
    int updateNameByUid(String uid, String name);

    /**
     * 批量删除
     * @param arr
     * @return
     */
    int[] delCourseUser(Object[][] arr);
}
