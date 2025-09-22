package com.wuzeyu.config.policy;

import com.alibaba.fastjson.JSON;
import com.wuzeyu.domain.activity.service.trial.thread.QueryGroupBuyActivityDiscountVOThreadTask;
import com.wuzeyu.domain.activity.service.trial.thread.QuerySkuVOFromDBThreadTask;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.UUID;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wuzeyu
 * @description 自定义拒绝策略
 * @github github.com/kkkkendei
 */
@Slf4j
@Component
public class MyRejectedPolicy implements RejectedExecutionHandler {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String REJECTED_TASK_KEY_PREFIX = "thread_pool:rejected_task:";


    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // 线程池是否被关闭
        if (executor.isShutdown()) {
            return;
        }

        log.info("==========>1. 执行自定义线程池拒绝策略<==========");
        String str = JSON.toJSONString(r);
        stringRedisTemplate.opsForList().leftPush(REJECTED_TASK_KEY_PREFIX, str);
    }



}
