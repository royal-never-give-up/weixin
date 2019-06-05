package cn.ninetailfox.weixin.entity.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息基本字段
 * @author chengq
 * @date 6/4/2019
 */
public abstract class BaseMessage {
    private String toUserName;
    private String fromUserName;
    private Long createTime;
    private String msgType;
    private Long msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    /**
     * 将类字段转换为map
     * @return map
     */
    public abstract Map<String, Object> getMsgMap();

    public Map<String, Object> getBaseMap() {
        Map<String, Object> baseMap = new HashMap<>();
        baseMap.put("ToUserName", getToUserName());
        baseMap.put("FromUserName", getFromUserName());
        baseMap.put("CreateTime", getCreateTime());
        baseMap.put("MsgType", getMsgType());
        baseMap.put("MsgId", getMsgId());
        return baseMap;
    }

    public BaseMessage(String toUserName, String fromUserName, String msgType) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.msgType = msgType;
        this.createTime = System.currentTimeMillis();
    }
}
