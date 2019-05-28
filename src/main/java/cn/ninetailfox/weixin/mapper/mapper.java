package cn.ninetailfox.weixin.mapper;

import org.apache.ibatis.annotations.Insert;

public interface mapper {
    @Insert("")
    void saveMsg(String s);
}
