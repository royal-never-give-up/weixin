package cn.ninetailfox.weixin.controller;

import cn.ninetailfox.weixin.service.WechatValidationService;
import cn.ninetailfox.weixin.util.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chengq
 * @date 5/27/2019
 */
@RestController
@RequestMapping("wechat")
public class WechatController {

    @Value("${wechat.token}")
    private String token;

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.app_secret}")
    private String secret;

    @Autowired
    private WechatValidationService wechatValidationService;

    @RequestMapping(value = "handle", method = RequestMethod.GET)
    public String checkSignature(@RequestParam(required = true) String signature,
                                 @RequestParam(required = true) String timestamp,
                                 @RequestParam(required = true) String nonce,
                                 @RequestParam(required = true) String echostr) {
        if (wechatValidationService.checkSignature(signature, timestamp, nonce, token)) {
            return echostr;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "handle", method = RequestMethod.POST)
    public void handleMsg(HttpServletRequest request) {
        try (InputStream is = request.getInputStream()) {
            while (is.available() > 0) {
                System.out.print((char) is.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("test")
    public String test() {
        return wechatValidationService.getAccessToken();
    }
}
