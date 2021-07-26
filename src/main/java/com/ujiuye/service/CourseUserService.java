package com.ujiuye.service;

import com.ujiuye.utils.PageUtils;

/**
 * @Author Bob
 * @Create 2021-07-26-11:33
 */
public interface CourseUserService {
    /**
     * 查询全部
     * @param pageSize
     * @param currentPage
     * @param search
     * @return
     */
    PageUtils findAllByPage(String pageSize, String currentPage, String search);

    /**
     * 修改CourseUser
     * @param id
     * @param cid
     * @param uid
     * @param name
     * @return
     */
    int updateCourseUser(String id, String cid, String uid, String name);

    /**
     * 批量删除选中
     * @param ids
     * @return
     */
    boolean delCourseUser(String ids);
}
