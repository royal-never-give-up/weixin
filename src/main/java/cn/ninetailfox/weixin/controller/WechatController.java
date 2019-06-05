package cn.ninetailfox.weixin.controller;

import cn.ninetailfox.weixin.entity.dto.BaseMessage;
import cn.ninetailfox.weixin.entity.dto.TextMessage;
import cn.ninetailfox.weixin.service.WechatValidationService;
import cn.ninetailfox.weixin.util.HttpClientUtil;
import cn.ninetailfox.weixin.util.MessageUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.DocumentException;
import org.dom4j.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin2.message.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatController.class);

    @Value("${wechat.token}")
    private String token;

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.app_secret}")
    private String secret;

    @Autowired
    private WechatValidationService wechatValidationService;

    @RequestMapping(value = "handle", method = RequestMethod.GET)
    public String checkSignature(@RequestParam() String signature,
                                 @RequestParam() String timestamp,
                                 @RequestParam() String nonce,
                                 @RequestParam() String echostr) {
        if (wechatValidationService.checkSignature(signature, timestamp, nonce, token)) {
            return echostr;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "handle", method = RequestMethod.POST)
    public void handleMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        Map<String, String> toMsg = MessageUtil.xmlToMap(request);
        TextMessage textMessage = new TextMessage(
                toMsg.get("FromUserName"),
                toMsg.get("ToUserName"),
                "你发送的消息是：" + toMsg.get("Content"));
        String msgStr = MessageUtil.messageToXml(textMessage);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(msgStr);
    }

    @RequestMapping("test")
    public String test() {
        return wechatValidationService.getAccessToken();
    }
}
