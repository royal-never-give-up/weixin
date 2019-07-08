package cn.ninetailfox.weixin.service;

import cn.ninetailfox.weixin.feign.IBusFeign;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BusServiceImpl implements IBusService {

    @Autowired
    IBusFeign busFeign;

    @Override
    public JSONObject lineDetail() {
        JSONObject lineInfo = JSON.parseObject(busFeign.lineDetail("420100", "715", "1"));
        return lineInfo;
    }
}
