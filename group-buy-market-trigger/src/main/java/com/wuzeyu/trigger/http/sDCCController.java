package com.wuzeyu.trigger.http;

import com.wuzeyu.api.IDCCService;
import com.wuzeyu.api.response.Response;
import com.wuzeyu.types.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/dcc")
public class sDCCController implements IDCCService {

    @Resource
    private RTopic sdcctopic;

    @RequestMapping("/update_config")
    @Override
    public Response<Boolean> updateConfig(@RequestParam String key, @RequestParam String value) {
        try {
            log.info("DCC 动态配置值变更 key: {} value: {}", key, value);
            sdcctopic.publish(key + "," + value);
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
