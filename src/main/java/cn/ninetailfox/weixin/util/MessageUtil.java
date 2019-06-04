package cn.ninetailfox.weixin.util;

import cn.ninetailfox.weixin.entity.dto.BaseMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtil.class);

    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<>();
        InputStream is = request.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);

        Element root = document.getRootElement();
        List<Element> list = root.elements();

        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }

        is.close();
        return map;
    }

    public static String messageToXml(BaseMessage message) {
        Map<String, Object> messageMap = message.getMsgMap();
        StringBuilder xmlBuilder = new StringBuilder("<xml>");
        for (Map.Entry<String, Object> entry : messageMap.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            String key = entry.getKey();
            switch (key) {
                case "ToUserName":
                case "FromUserName":
                case "MsgType":
                    xmlBuilder.append("<").append(key)
                            .append("><![CDATA[").append(entry.getValue())
                            .append("]]></").append(key).append(">");
                    break;
                case "CreateTime":
                    xmlBuilder.append("<").append(key).append(">")
                            .append(entry.getValue())
                            .append("</").append(key).append(">");
                    break;
                case "Content":
                    xmlBuilder.append("<").append(key)
                            .append("><![CDATA[").append(entry.getValue())
                            .append("]]></").append(key).append(">");
                    break;
                default:
                    LOGGER.info("不添加元素：" + key);
            }
        }
        return null;
    }
}
