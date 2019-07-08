package cn.ninetailfox.weixin.feign;

import cn.ninetailfox.weixin.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "intellectualBus", url = "http://bus.wuhancloud.cn:9087/website/web", configuration = FeignConfiguration.class)
public interface IBusFeign {

    /**
     * 获取线路站点信息和实时公交位置
     * @param code 行政编号
     * @param busId 公交id，如715
     * @param direction 公交行驶方向 1或者2
     * @return json str
     */
    @GetMapping("/{code}/line/027-{busId}-{direction}.do?Type=LineDetail")
    String lineDetail(@PathVariable String code, @PathVariable String busId, @PathVariable String direction);
}
