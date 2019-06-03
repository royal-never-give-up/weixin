package cn.ninetailfox.weixin.controller;

import cn.ninetailfox.weixin.entity.User;
import cn.ninetailfox.weixin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 
 * @author chengq
 * @date 5/27/2019
 */
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("hello")
    public String hello() {
        return "hello world";
    }

    @RequestMapping("getUser")
    public List<User> getUser() {
        User user = new User();
//        user.setId("1");
        user.setUsername("zhangsan");
        user.setPassword("123");
        userMapper.insertUser(user);

        return userMapper.findAllUsers();
    }
}
