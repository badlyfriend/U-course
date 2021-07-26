package com.ujiuye.service;

import com.ujiuye.dao.CourseUserDao;
import com.ujiuye.dao.CourseUserDaoImpl;
import com.ujiuye.pojo.Course;
import com.ujiuye.pojo.CourseUser;
import com.ujiuye.pojo.User;
import com.ujiuye.utils.PageUtils;

import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-26-11:33
 */
public class CourseUserServiceImpl implements CourseUserService{
    private CourseUserDao courseUserDao = new CourseUserDaoImpl();
    /**
     * 查询全部
     * @param pageSize
     * @param currentPage
     * @param search
     * @return
     */
    @Override
    public PageUtils findAllByPage(String pageSize, String currentPage, String search) {
        // 处理每页显示条数
        int ps = 3;
        if(pageSize != null && !"".equals(pageSize)) {
            ps = Integer.parseInt(pageSize);
        }
        // 处理当前页
        int cp = 1;
        if(currentPage != null && !"".equals(currentPage)) {
            cp = Integer.parseInt(currentPage);
        }
        // 查询总条数
        int sumCount = courseUserDao.querySumCountBySearch(search);

        // 分页工具类处理其他分页信息
        PageUtils pu = new PageUtils(ps,cp,sumCount);

        // 分页查询信息
        List<CourseUser> lists = courseUserDao.findAllByPage(pu,search);

        for (CourseUser cu : lists) {
            int cid = cu.getCid();
            int uid = cu.getUid();

            // 通过cid查询course  通过uid查询user放入到集合中
            Course c = courseUserDao.queryCourseByCid(cid);
            User u = courseUserDao.queryUserByUid(uid);
            // 将查询出来的 user 和 course 存入到CourseUserList集合中
            cu.setCourse(c);
            cu.setUser(u);
        }

        pu.setCourseUserList(lists);

        return pu;
    }

    /**
     * 修改CourseUser
     * @param id
     * @param cid
     * @param uid
     * @param name
     * @return
     */
    @Override
    public int updateCourseUser(String id, String cid, String uid, String name) {

        // 通过id修改cid  course_user表中
        int i = courseUserDao.updateCidById(cid,id);
        // 通过uid修改name  user表中
        int j = courseUserDao.updateNameByUid(uid,name);

        return i + j;
    }

    /**
     * 批量删除选中
     * @param ids
     * @return
     */
    @Override
    public boolean delCourseUser(String ids) {
        // 用,分割成数组
        String[] idsArr = ids.split(",");
        // 创建一个二维数组
        Object[][] arr = new Object[idsArr.length][];
        // 遍历  将数据写到二维数组里面去
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Object[1];
            arr[i][0] = idsArr[i];
        }
        // 调用dao层查询删除
        int[] i = courseUserDao.delCourseUser(arr);

        for (int i1 : i) {
            if(i1 == 0) {
                return false;
            }
        }

        return true;
    }
}
