package com.wuzeyu.config;

import com.wuzeyu.types.annotations.DCCValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzeyu
 * @description
 * @github github.com/kkkkendei
 */
@Slf4j
@Configuration
public class sDCCValueBeanFactory implements BeanPostProcessor {

    private static final String BASE_CONFIG_PATH = "group_buy_market_dcc_";

    private final RedissonClient redissonClient;

    private final Map<String, Object> dccObjGroup = new HashMap<>();

    public sDCCValueBeanFactory(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 增加 AOP 代理后，获得类的方式要通过 AopProxyUtils.getTargetClass(bean); 不能直接 bean.class 因为代理后类的结构发生变化，这样不能获得到自己的自定义注解了。
        Class<?> targetBeanClass = bean.getClass();
        Object targetBeanObject = bean;
        if (AopUtils.isAopProxy(bean)) {  // 是 AOP 代理对象
            targetBeanClass = AopUtils.getTargetClass(bean);
            targetBeanObject = AopProxyUtils.getSingletonTarget(bean);
        }

        Field[] fields = targetBeanClass.getDeclaredFields();
        for (Field field : fields) {
            // 检查是否有 DCCValue 注解
            if (field.isAnnotationPresent(DCCValue.class)) {
                DCCValue dccValue = field.getAnnotation(DCCValue.class);
                String value = dccValue.value();
                if (StringUtils.isBlank(value)) {
                    throw new RuntimeException(field.getName() + "@DCCValue is not config value config case 「isSwitch/isSwitch:1」");
                }

                String[] splits = value.split(":");
                String key = BASE_CONFIG_PATH.concat(splits[0]);
                String defaultValue = splits.length == 2 ? splits[1] : null;
                // 设置值
                String setValue = defaultValue;

                try {
                    if (StringUtils.isBlank(defaultValue)) {
                        throw new RuntimeException("dcc config error" + key + " is not null - 请配置默认值！");
                    }

                    // Redis 操作，判断配置Key是否存在，不存在则创建，存在则获取最新值
                    RBucket<String> bucket = redissonClient.getBucket(key);
                    if (! bucket.isExists()) {
                        bucket.set(defaultValue);
                    } else {
                        setValue = bucket.get();
                    }

                    field.setAccessible(true);
                    field.set(targetBeanObject, setValue);
                    field.setAccessible(false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
