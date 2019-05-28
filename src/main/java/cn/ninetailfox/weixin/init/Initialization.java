package cn.ninetailfox.weixin.init;

import cn.ninetailfox.weixin.enumeration.LoggerType;
import cn.ninetailfox.weixin.service.WechatValidationService;
import cn.ninetailfox.weixin.util.HttpClientUtil;
import cn.ninetailfox.weixin.util.LoggerUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author chengq
 * @date 5/27/2019
 */
@Component
public class Initialization implements ApplicationRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(Initialization.class);

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.app_secret}")
    private String secret;

    @Autowired
    private WechatValidationService wechatValidationService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        setAccessTokenTimer();
    }

    /**
     * 定时获取access_token
     */
    private void setAccessTokenTimer() {
        new Thread(() -> {
            wechatValidationService.requestAccessToken(appid, secret);
            try {
                Thread.sleep(6000 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).run();
    }
}
