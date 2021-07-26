package com.ujiuye.servlet;

import com.ujiuye.pojo.Course;
import com.ujiuye.pojo.ResultVo;
import com.ujiuye.service.CourseService;
import com.ujiuye.service.CourseServiceImpl;
import com.ujiuye.servlet.base.BaseServlet;
import com.ujiuye.utils.DelFileUtils;
import com.ujiuye.utils.PageUtils;
import com.ujiuye.utils.UploadUtils;
import com.ujiuye.utils.WriteJsonUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Author Bob
 * @Create 2021-07-23-11:08
 */
@MultipartConfig
@WebServlet("/courseServlet")
public class CourseServlet extends BaseServlet {

    private ResultVo vo;

    private CourseService cs = new CourseServiceImpl();

    /**
     * 上传文件到服务器
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void uploadFile(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

        Part part = req.getPart("file");

        // 上传文件到服务器  返回服务器存储的文件名
        String filename = UploadUtils.uploadFiles(part);

        // 上传成功后将上传成功的信息返回到前台
        if(filename == null || "".equals(filename)) {
            vo = new ResultVo(500 , "上传文件失败");
        }else if(filename.endsWith(".jpg") || filename.endsWith(".png")) {
            vo = new ResultVo(200 , "上传图片成功" , filename);
        }else {
            vo = new ResultVo(200 , "上传视频成功" , filename);
        }

        WriteJsonUtils.writeJson(vo,resp);

    }

    /**
     * 删除上传的图片
     * @param req
     * @param resp
     */
    public void deleteFile(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {

        String fileName = req.getParameter("fileName");

        System.out.println("fileName = " + fileName);

        boolean b = DelFileUtils.delFile(fileName);

        if(b) {
            vo = new ResultVo(200 , "删除成功");
        }else {
            vo = new ResultVo(500 , "删除失败");
        }

        WriteJsonUtils.writeJson(vo,resp);
    }

    /**
     * 添加课程
     * @param req
     * @param resp
     */
    public void addCourse(HttpServletRequest req , HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException {
//        使用beanuitls工具类方法快速处理参数
        Map<String, String[]> map = req.getParameterMap();
        Course course = new Course();
        BeanUtils.populate(course,map);

        String courseImage = req.getParameter("courseImage");
        String courseVideo = req.getParameter("courseVideo");

        course.setCourseImage(courseImage);
        course.setCourseVideo(courseVideo);

        int i = cs.addCourse(course);

        if(i > 0) {
            vo = new ResultVo(200 , "添加课程成功");
        }else {
            vo = new ResultVo(500 , "添加课程失败");
        }

        WriteJsonUtils.writeJson(vo,resp);
    }

    /**
     * 查询全部课程信息
     * @param req
     * @param resp
     */
    public void findAll(HttpServletRequest req , HttpServletResponse resp) {
        String pageSize = req.getParameter("pageSize");
        String currentPage = req.getParameter("currentPage");
        String search = req.getParameter("search");

        PageUtils pu = cs.findAll(pageSize , currentPage , search);

        vo = new ResultVo(200 , "查询成功" , pu);

        WriteJsonUtils.writeJson(vo,resp);
    }

    /**
     * 修改课程信息
     * @param req
     * @param resp
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void updateCourses(HttpServletRequest req , HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = req.getParameterMap();
        Course c = new Course();
        BeanUtils.populate(c,map);
        // 获取传入的图片和视频
        String newCourseImage = req.getParameter("newCourseImage");
        String newCourseVideo = req.getParameter("newCourseVideo");
        // 获取旧的图片和视频
        String oldCourseImage = c.getCourseImage();
        String oldCourseVideo = c.getCourseVideo();

        if(newCourseImage != null && !"".equals(newCourseImage) && !oldCourseImage.equals(newCourseImage)) {
            // 修改了图片  在服务器把旧图片删除 course实体类里面存放新的图片
            DelFileUtils.delFile(oldCourseImage);
            c.setCourseImage(newCourseImage);
        }
        if (newCourseVideo != null && !"".equals(newCourseVideo) && !oldCourseVideo.equals(newCourseVideo)) {
            // 修改了视频  在服务器把旧视频删除 course实体类里面存放新的视频
            DelFileUtils.delFile(oldCourseVideo);
            c.setCourseVideo(newCourseVideo);
        }

        int i = cs.updateCourses(c);

        if(i > 0) {
            vo = new ResultVo(200 , "修改成功");
        }else {
            vo = new ResultVo(500 , "修改失败");
        }

        WriteJsonUtils.writeJson(vo,resp);
    }

    /**
     * 批量删除
     * @param req
     * @param resp
     */
    public void delAll(HttpServletRequest req , HttpServletResponse resp) {

        String cids = req.getParameter("cids");
//        System.out.println(cids);//11,12,13
        boolean b = cs.delAll(cids);

        if(b) {
            vo = new ResultVo(500 , "删除成功");
        }else {
            vo = new ResultVo(500 , "删除失败");
        }

        WriteJsonUtils.writeJson(vo,resp);
    }

    public void findAllCourses(HttpServletRequest req , HttpServletResponse resp) {

        List<Course> list = cs.findAllCourses();

        vo = new ResultVo(200 , "查询成功" , list);

        WriteJsonUtils.writeJson(vo,resp);

    }
}
