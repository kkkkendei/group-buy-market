package com.wuzeyu.trigger.http;

import com.wuzeyu.api.IDCCService;
import com.wuzeyu.api.response.Response;
import com.wuzeyu.types.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description 动态配置管理
 * @github github.com/kkkkendei
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/gbm/dcc/")
public class DCCController implements IDCCService {

    @Resource
    private RTopic dccTopic;

    /**
     * 动态值变更，即发布
     * <p>
     * curl http://localhost:8091/api/v1/gbm/dcc/update_config?key=downgradeSwitch&value=1
     * curl http://localhost:8091/api/v1/gbm/dcc/update_config?key=cutRange&value=0
     */
    @RequestMapping(value = "update_config", method = RequestMethod.GET)
    @Override
    public Response<Boolean> updateConfig(@RequestParam String key, @RequestParam String value) {
        try {
            log.info("DCC 动态配置值变更 key: {} value: {}", key, value);
            dccTopic.publish(key + "," + value);
            return Response.<Boolean>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("DCC 动态配置变更失败 key: {} value: {}", key, value, e);
            return Response.<Boolean>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

}
