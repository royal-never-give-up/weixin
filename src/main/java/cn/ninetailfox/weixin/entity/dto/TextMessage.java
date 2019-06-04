package cn.ninetailfox.weixin.entity.dto;

import java.util.Map;

public class TextMessage extends BaseMessage {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        content = content;
    }

    @Override
    public Map<String, Object> getMsgMap() {
        Map<String, Object> resultMap = getBaseMap();
        resultMap.put("Content", getContent());
        return resultMap;
    }
}
