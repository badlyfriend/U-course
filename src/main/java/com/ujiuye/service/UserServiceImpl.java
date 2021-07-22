package com.ujiuye.service;

import com.ujiuye.dao.UserDao;
import com.ujiuye.dao.UserDaoImpl;
import com.ujiuye.pojo.ResultVo;
import com.ujiuye.pojo.User;
import com.ujiuye.utils.PageUtils;

import java.util.List;

/**
 * @Author Bob
 * @Create 2021-07-21-11:44
 */
public class UserServiceImpl implements UserService{

    private UserDao ud = new UserDaoImpl();

    @Override
    public User userLogin(String username, String password) {
        return ud.userLogin(username,password);
    }

    @Override
    public PageUtils findByPage(String pageSize, String currentPage, String search) {
        //处理pageSize 和 currentPage
        int ps = 3;
        if(pageSize != null && !"".equals(pageSize)) {
            ps = Integer.parseInt(pageSize);
        }

        int cp = 1;
        if(currentPage != null && !"".equals(currentPage)) {
            cp = Integer.parseInt(currentPage);
        }

        // 获取总条数
        int sumCount = ud.querySumCount(search);

        // 使用分页工具类处理参数
        PageUtils pu = new PageUtils(ps,cp,sumCount);

        // 分页查询
        List<User> list = ud.findByPage(pu,search);

        pu.setUserList(list);

        return pu;
    }

    @Override
    public int addUsers(User user) {
        return ud.addUsers(user);
    }

    @Override
    public int updateUsers(User user) {
        return ud.updateUsers(user);
    }

    @Override
    public Boolean delAll(String uid) {
        int i = 0;
        String[] uids = uid.split(",");
        for (String s : uids) {
            i += ud.delAll(s);
        }
        return i == uids.length;
    }
}
