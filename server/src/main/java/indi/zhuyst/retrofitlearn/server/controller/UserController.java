package indi.zhuyst.retrofitlearn.server.controller;

import indi.zhuyst.retrofitlearn.server.pojo.Msg;
import indi.zhuyst.retrofitlearn.server.pojo.User;
import indi.zhuyst.retrofitlearn.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuyst on 2017/7/8.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Msg login(@RequestBody User user){
        return userService.login(user);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Msg insert(@RequestBody User user){
        return userService.insertUser(user);
    }

    @RequestMapping(value = "/{username}/update",method =  RequestMethod.PUT)
    public Msg update(@RequestBody User user){
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/{username}/query",method = RequestMethod.GET)
    public Msg<User> query(@PathVariable String username){
        User user = userService.getUser(username);

        Msg<User> msg = new Msg<>(user);
        msg.setSuccess(true);
        msg.setMessage("OK");

        return msg;
    }
}
