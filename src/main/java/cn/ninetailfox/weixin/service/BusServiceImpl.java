package cn.ninetailfox.weixin.service;

import cn.ninetailfox.weixin.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BusServiceImpl implements IBusService {

    @Override
    public Map<String, Object> lineDetail() {
        Map<String, Object> getResult =  HttpClientUtil.get(
                "http://bus.wuhancloud.cn:9087/website//web/420100/line/027-715-1.do?Type=LineDetail",
                new HashMap<>());
        return JSON.parseObject(getResult.get("entity").toString());
    }
}
