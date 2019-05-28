package cn.ninetailfox.weixin.enumeration;

/**
 *
 * @author chengq
 * @date 5/27/2019
 */
public enum LoggerType {
    /**
     * get请求失败
     */
    GET_FAILURE("GET请求失败：");

    private final String info;

    LoggerType(String info) {
        this.info = info;
    }

    public String info() {
        return this.info;
    }
}
