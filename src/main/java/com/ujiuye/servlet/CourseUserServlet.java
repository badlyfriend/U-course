package com.ujiuye.servlet;

import com.ujiuye.pojo.ResultVo;
import com.ujiuye.service.CourseUserService;
import com.ujiuye.service.CourseUserServiceImpl;
import com.ujiuye.servlet.base.BaseServlet;
import com.ujiuye.utils.PageUtils;
import com.ujiuye.utils.WriteJsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Bob
 * @Create 2021-07-26-11:30
 */
@WebServlet("/courseUserServlet")
public class CourseUserServlet extends BaseServlet {

    private ResultVo vo;
    private CourseUserService courseUserService = new CourseUserServiceImpl();

    // 查询全部
    public void findAllByPage(HttpServletRequest req , HttpServletResponse resp) {

        String pageSize = req.getParameter("pageSize");
        String currentPage = req.getParameter("currentPage");
        String search = req.getParameter("search");

        PageUtils pu = courseUserService.findAllByPage(pageSize,currentPage,search);

        vo = new ResultVo(200 , "查询成功" , pu);

        WriteJsonUtils.writeJson(vo,resp);
    }

    public void updateCourseUser(HttpServletRequest req , HttpServletResponse resp) {

        String id = req.getParameter("id");
        String cid = req.getParameter("cid");
        String uid = req.getParameter("uid");
        String name = req.getParameter("name");

        int i = courseUserService.updateCourseUser(id,cid,uid,name);

        if(i > 1) {
            vo = new ResultVo(200 , "修改成功");
        }else {
            vo = new ResultVo(500 , "修改失败");
        }

        WriteJsonUtils.writeJson(vo , resp);
    }

    public void delCourseUser(HttpServletRequest req , HttpServletResponse resp) {

        String ids = req.getParameter("ids");

        boolean b = courseUserService.delCourseUser(ids);

        if(b) {
            vo = new ResultVo(200 , "批量删除成功");
        }else {
            vo = new ResultVo(500 , "批量删除失败");
        }

        WriteJsonUtils.writeJson(vo,resp);
    }
}
