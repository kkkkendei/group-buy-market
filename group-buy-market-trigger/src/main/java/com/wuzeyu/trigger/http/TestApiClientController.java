package com.wuzeyu.trigger.http;

import com.alibaba.fastjson.JSON;
import com.wuzeyu.api.dto.NotifyRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuzeyu
 * @description 测试回调接口
 * @github github.com/kkkkendei
 */
@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/test")
public class TestApiClientController {

    @RequestMapping(value = "/group-buy-market", method = RequestMethod.POST)
    public String exec(@RequestBody NotifyRequestDTO notifyRequestDTO) {
        log.info("模拟测试第三方服务接收拼团回调 {}", JSON.toJSONString(notifyRequestDTO));

        return "success";
    }

}
