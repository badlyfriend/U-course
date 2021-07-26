package com.ujiuye.service;

import com.ujiuye.dao.CourseDao;
import com.ujiuye.dao.CourseDaoImpl;
import com.ujiuye.pojo.Course;
import com.ujiuye.utils.DelFileUtils;
import com.ujiuye.utils.PageUtils;

import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-23-11:08
 */
public class CourseServiceImpl implements CourseService{

    private CourseDao cd = new CourseDaoImpl();

    /**
     * 添加课程
     * @param course
     * @return
     */
    @Override
    public int addCourse(Course course) {
        return cd.addCourse(course);
    }

    /**
     * 模糊+分页查询课程信息
     * @param pageSize
     * @param currentPage
     * @param search
     * @return
     */
    @Override
    public PageUtils findAll(String pageSize, String currentPage, String search) {
        // 处理参数
        int ps = 3;
        if(!(pageSize == null || "".equals(pageSize))) {
            ps = Integer.parseInt(pageSize);
        }

        int cp = 1;
        if(!(currentPage == null || "".equals(currentPage))) {
            cp = Integer.parseInt(currentPage);
        }
        // 数据库查询返回总页数
        int sumCount = cd.querySumCount(ps,cp,search);

        PageUtils pu = new PageUtils(ps,cp,sumCount);

        //分页查询
        List<Course> list = cd.findAll(pu,search);

        pu.setCourseList(list);

        return pu;
    }

    /**
     * 修改课程信息
     * @param c
     * @return
     */
    @Override
    public int updateCourses(Course c) {
        return cd.updateCourses(c);
    }

    /**
     * 批量删除
     * @param cids
     */
    @Override
    public boolean delAll(String cids) {
        // 删除图片和视频  从后台查询
        List<Course> list = cd.queryAllByCids(cids);
        for (Course c : list) {
            String courseImage = c.getCourseImage();
            String courseVideo = c.getCourseVideo();
            DelFileUtils.delFile(courseImage);
            DelFileUtils.delFile(courseVideo);
        }
        /**
         * jdbc  addBatch executeBatch clearBatch
         *
         */
        String[] cidArr = cids.split(",");

        Object[][] arr = new Object[cidArr.length][];
        for(int i = 0 ; i < arr.length ; i++) {
            arr[i] = new Object[1];
            arr[i][0] = cidArr[i];
        }
        int[] i = cd.delAll(arr);
        for (int j = 0; j < i.length; j++) {
            if(i[j] == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 查询所有课程信息
     * @return
     */
    @Override
    public List<Course> findAllCourses() {
        return cd.findAllCourses();
    }
}
