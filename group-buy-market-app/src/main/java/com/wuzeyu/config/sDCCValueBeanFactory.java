package com.wuzeyu.config;

import com.wuzeyu.types.annotations.DCCValue;
import com.wuzeyu.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.swing.*;
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

    @Bean("dccObjGroup")
    public RTopic dccRedisTopicListener(RedissonClient redissonClient) {
        RTopic topic = redissonClient.getTopic("group_buy_market_dcc_");
        topic.addListener(String.class, (charSequence, s) -> {
            String[] split = s.split(Constants.SPLIT);

            // 获取值
            String key = BASE_CONFIG_PATH + split[0];
            String value = split[1];

            // 设置值
            RBucket<String> bucket = redissonClient.getBucket(key);
            if (! bucket.isExists()) {
                return;
            }
            bucket.set(value);

            Object objBean = dccObjGroup.get(key);
            if (objBean == null) {
                return;
            }
            Class<?> targetBeanClass = objBean.getClass();
            // 检查是否为 AOP 代理对象
            if (AopUtils.isAopProxy(objBean)) {
                // 获取代理对象的目标类
                targetBeanClass = AopUtils.getTargetClass(objBean);
            }
            try {
                Field field = targetBeanClass.getDeclaredField(split[0]);
                field.setAccessible(true);
                field.set(objBean, value);
                field.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log.error("Failed to update field {} in class {}", split[0], targetBeanClass.getName(), e);
            }
        });

        return topic;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 增加 AOP 代理后，获得类的方式要通过 AopProxyUtils.getTargetClass(bean); 不能直接 bean.class 因为代理后类的结构发生变化，这样不能获得到自己的自定义注解了。
        Class<?> targetBeanClass = bean.getClass();  // 用于反射操作
        Object targetBeanObject = bean;  // 用于字段值设置，作为反射操作的目标对象
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

                dccObjGroup.put(key, targetBeanObject);
            }
        }

                return bean;
    }

}
