package cn.ninetailfox.weixin.controller;

import cn.ninetailfox.weixin.entity.User;
import cn.ninetailfox.weixin.mapper.UserMapper;
import cn.ninetailfox.weixin.service.IBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private IBusService busService;

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

    @RequestMapping("bus")
    public Map<String, Object> bus() {
        return busService.lineDetail();
    }
}
