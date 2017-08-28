package indi.zhuyst.retrofitlearn.server.service;

import indi.zhuyst.retrofitlearn.server.dao.UserDao;
import indi.zhuyst.retrofitlearn.server.pojo.Msg;
import indi.zhuyst.retrofitlearn.server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhuyst on 2017/7/8.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUser(String username){
        return userDao.selectByPrimaryKey(username);
    }

    public Msg login(User user) {
        Msg msg = new Msg();

        User queryUser = getUser(user.getUsername());
        if(queryUser != null && user.getPassword().equals(queryUser.getPassword())){
            msg.setSuccess(true);
            msg.setMessage("OK");
        }
        else {
            msg.setSuccess(false);
            msg.setMessage("用户名或密码错误");
        }

        return msg;
    }

    public Msg insertUser(User user){
        Msg msg = new Msg();
        User queryUser = getUser(user.getUsername());

        if(queryUser != null){
            msg.setSuccess(false);
            msg.setMessage("该用户名已存在");
        }
        else if(userDao.insertSelective(user) > 0){
            msg.setSuccess(true);
            msg.setMessage("OK");
        }
        else {
            msg.setSuccess(false);
            msg.setMessage("不明原因导致新增用户失败");
        }

        return msg;
    }

    public Msg updateUser(User user){
        Msg msg = new Msg();

        if(userDao.updateByPrimaryKeySelective(user) > 0){
            msg.setSuccess(true);
            msg.setMessage("更新用户信息成功！");
        }
        else {
            msg.setSuccess(false);
            msg.setMessage("更新用户信息失败！");
        }

        return msg;
    }
}
