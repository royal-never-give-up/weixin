package cn.ninetailfox.weixin.util;

import cn.ninetailfox.weixin.enumeration.LoggerType;
import org.slf4j.Logger;

public class LoggerUtil {
    public static void error(Logger logger, LoggerType type, String extra) {
        logger.error(type.info() + extra);
    }
}
