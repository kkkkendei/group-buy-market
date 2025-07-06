package com.wuzeyu.config.policy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Slf4j
@Order(value = Integer.MAX_VALUE)
@Component
public class RejectedThreadLister {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ThreadPoolExecutor executor;

    private static final String REJECTED_TASK_KEY_PREFIX = "thread_pool:rejected_task:";

    // 最大循环次数
    private int maxNumber = 20;

    @PostConstruct
    public void rejectedThreadLister() {
        new Thread(() -> {
            int count = 0;
            while (!executor.isShutdown()) {
                // 阻塞队列
                ArrayBlockingQueue<Runnable> queue = (ArrayBlockingQueue<Runnable>) executor.getQueue();
                // 从缓存中获取被拒绝的任务
                String taskJson= stringRedisTemplate.opsForList().leftPop(REJECTED_TASK_KEY_PREFIX);
                if (taskJson != null) {
                    count = 0;
                    // 反序列化任务
                    Callable<?> task = (Callable<?>) stringRedisTemplate.getValueSerializer().deserialize(taskJson.getBytes());
                    if (queue.size() == 5000) {
                        executor.submit(task);
                    } else {
                        try {
                            queue.put(new FutureTask<>(task));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    count++;
                    // 如果多次循环后并没有拒绝的任务，说明任务此时数据量并不大，那么就可以减少循环次数
                    if (count >= maxNumber) {
                        try {
                            Thread.sleep(10000L);
                            count = 0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "rejected-lister").start();
    }

}
