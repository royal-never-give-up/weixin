package cn.ninetailfox.weixin.controller;

import cn.ninetailfox.weixin.service.IBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("bus")
public class BusController {

    @Autowired
    private IBusService busService;

    @RequestMapping()
    public String index() {
        return "bus";
    }

    @RequestMapping("bus")
    @ResponseBody
    public Map<String, Object> lineDetail() {
        return busService.lineDetail();
    }
}
