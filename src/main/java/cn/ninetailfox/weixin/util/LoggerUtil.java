package cn.ninetailfox.weixin.util;

import cn.ninetailfox.weixin.enumeration.LoggerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author chengq
 * @date 6/4/2019
 */
public class LoggerUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtil.class);

    public static void error(Logger logger, LoggerType type, String extra) {
        logger.error(type.info() + extra);
    }
}
