package cn.ninetailfox.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author chengq
 * @date 5/27/2019
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @RequestMapping("hello")
    public String hello() {
        return "hello world";
    }
}
