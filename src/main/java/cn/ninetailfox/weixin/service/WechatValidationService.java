package cn.ninetailfox.weixin.service;

import cn.ninetailfox.weixin.enumeration.LoggerType;
import cn.ninetailfox.weixin.util.HttpClientUtil;
import cn.ninetailfox.weixin.util.LoggerUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WechatValidationService {

    private static Logger LOGGER = LoggerFactory.getLogger(WechatValidationService.class);

    private Map<String, String> infoMap = new ConcurrentHashMap<>();

    public boolean checkSignature(String signature, String timestamp, String nonce, String token) {
        String[] params = new String[] {
                token,
                timestamp,
                nonce
        };
        Arrays.sort(params, String::compareTo);
        return signature.equals(DigestUtils.sha1Hex(params[0] + params[1] + params[2]));
    }

    public String getAccessToken() {
        return infoMap.get("access_token");
    }

    public void requestAccessToken (String appid, String secret) {
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", appid);
        params.put("secret", secret);
        Map<String, Object> getResult =  HttpClientUtil.get(url, params);
        if (getResult.get("status").toString().contains("200")) {
            JSONObject entity =  JSON.parseObject(getResult.get("entity").toString());
            String accessToken = entity.getString("access_token");
            if (accessToken != null) {
                //notice: save to db
                infoMap.put("access_token", accessToken);
            } else {
                LOGGER.error(entity.toString());
            }
        } else {
            LoggerUtil.error(LOGGER, LoggerType.GET_FAILURE, url);
        }
    }

}
