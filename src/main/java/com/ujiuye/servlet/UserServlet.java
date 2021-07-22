package com.ujiuye.servlet;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.ujiuye.pojo.ResultVo;
import com.ujiuye.pojo.User;
import com.ujiuye.service.UserService;
import com.ujiuye.service.UserServiceImpl;
import com.ujiuye.servlet.base.BaseServlet;
import com.ujiuye.utils.PageUtils;
import com.ujiuye.utils.WriteJsonUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @Author Bob
 * @Create 2021-07-21-11:22
 */
@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {

    // 创建相应信息对象
    private ResultVo vo;
    private UserService us = new UserServiceImpl();

    /**
     * 登录功能
     * @param req
     * @param resp
     */
    public void userLogin(HttpServletRequest req , HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String imageCode = req.getParameter("imageCode");

        // 从session中获取验证码
        HttpSession session = req.getSession();
        String verCode = (String) session.getAttribute("verCode");
        // 判断与前台传过来的验证码是否相等 和前台传过来的验证码是否为空
        if(imageCode == null || !imageCode.toLowerCase().equals(verCode)) {
            vo = new ResultVo(500 , "验证码错误");
        }else {
            // 验证码正确,判断账号和密码是否正确
            User user = us.userLogin(username,password);
            if(user == null) { // 查询到的账号密码为空
                vo = new ResultVo(500 , "账号或密码错误");
            }else if(user.getRole() == 1) { // 登录成功,把名字放到session作用域中
                session.setAttribute("name",user.getName());
                vo = new ResultVo(200 , "登陆成功" , user);
            }else { // 非管理员登录
                vo = new ResultVo(500 , "非管理员,登录后台失败");
            }
        }
        WriteJsonUtils.writeJson(vo,resp);
    }

    /**
     * 拿取用户名返回给前台
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getSessionName(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        String name = (String)req.getSession().getAttribute("name");
        if(name == null) {
            vo = new ResultVo(500 , "没有登录");
        }else {
            vo = new ResultVo(200 , "拿取名字成功" , name);
        }
        // 转成json格式并将参数传递给前台
        WriteJsonUtils.writeJson(vo,resp);
    }

    /**
     * 退出操作
     * @param req
     * @param resp
     */
    public void userLogout(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        // 抹除用户的name
        req.getSession().removeAttribute("name");
        // 抹除成功后返回一个true
        resp.getWriter().print(true);
    }

    /**
     * 分页+模糊查询
     * @param req
     * @param resp
     */
    public void findByPage(HttpServletRequest req , HttpServletResponse resp) {
        // 获取参数
        String pageSize = req.getParameter("pageSize");
        String currentPage = req.getParameter("currentPage");
        String search = req.getParameter("search");

        // 进行查询操作
        PageUtils pu = us.findByPage(pageSize,currentPage,search);

        // 查询成功将pu封装到vo对象中 返回给前台
        vo = new ResultVo(200 , "查询成功",pu);

        WriteJsonUtils.writeJson(vo,resp);
    }

    public void addUsers(HttpServletRequest req , HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException {
        // 使用BeanUtils类处理数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,parameterMap);

        // 添加数据
        int i = us.addUsers(user);

        if(i > 0) {
            vo = new ResultVo(200 , "添加成功");
        }else {
            vo = new ResultVo(500 , "添加失败");
        }

        WriteJsonUtils.writeJson(vo,resp);
    }

    public void updateUsers(HttpServletRequest req , HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,parameterMap);
        int i = us.updateUsers(user);
        if(i > 0) {
            vo = new ResultVo(200 , "修改成功");
        }else {
            vo = new ResultVo(500 , "修改失败");
        }
        WriteJsonUtils.writeJson(vo,resp);
    }

    public void delAll(HttpServletRequest req , HttpServletResponse resp) {
        String uid = req.getParameter("uid");
        Boolean i = us.delAll(uid);
        if(i) {
            vo = new ResultVo(200 , "删除成功");
        }else {
            vo = new ResultVo(500 , "删除失败");
        }
        WriteJsonUtils.writeJson(vo,resp);
    }
}
