package com.wuzeyu.infrastructure.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author wuzeyu
 * @description Redisson
 * @github github.com/kkkkendei
 */
@Service("redissonService")
public class RedissonService implements IRedisService {

    @Resource
    private RedissonClient redissonClient;


    @Override
    public <T> void setValue(String key, T value) {
        redissonClient.<T>getBucket(key).set(value);
    }

    @Override
    public <T> void setValue(String key, T value, long expired) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value, Duration.ofMillis(expired));
    }

    @Override
    public <T> T getValue(String key) {
        return null;
    }

    @Override
    public <T> RQueue<T> getQueue(String key) {
        return null;
    }

    @Override
    public <T> RBlockingQueue<T> getBlockingQueue(String key) {
        return null;
    }

    @Override
    public <T> RDelayedQueue<T> getDelayedQueue(RBlockingQueue<T> rBlockingQueue) {
        return null;
    }

    @Override
    public void setAtomicLong(String key, long value) {

    }

    @Override
    public Long getAtomicLong(String key) {
        return null;
    }

    @Override
    public long incr(String key) {
        return 0;
    }

    @Override
    public long incrBy(String key, long delta) {
        return 0;
    }

    @Override
    public long decr(String key) {
        return 0;
    }

    @Override
    public long decrBy(String key, long delta) {
        return 0;
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public boolean isExists(String key) {
        return false;
    }

    @Override
    public void addToSet(String key, String value) {

    }

    @Override
    public boolean isSetMember(String key, String value) {
        return false;
    }

    @Override
    public void addToList(String key, String value) {

    }

    @Override
    public String getFromList(String key, int index) {
        return null;
    }

    @Override
    public <K, V> RMap<K, V> getMap(String key) {
        return null;
    }

    @Override
    public void addToMap(String key, String field, String value) {

    }

    @Override
    public String getFromMap(String key, String field) {
        return null;
    }

    @Override
    public <K, V> V getFromMap(String key, K field) {
        return null;
    }

    @Override
    public void addToSortedSet(String key, String value) {

    }

    @Override
    public RLock getLock(String key) {
        return null;
    }

    @Override
    public RLock getFairLock(String key) {
        return null;
    }

    @Override
    public RReadWriteLock getReadWriteLock(String key) {
        return null;
    }

    @Override
    public RSemaphore getSemaphore(String key) {
        return null;
    }

    @Override
    public RPermitExpirableSemaphore getPermitExpirableSemaphore(String key) {
        return null;
    }

    @Override
    public RCountDownLatch getCountDownLatch(String key) {
        return null;
    }

    @Override
    public <T> RBloomFilter<T> getBloomFilter(String key) {
        return null;
    }

    @Override
    public Boolean setNx(String key) {
        return null;
    }

    @Override
    public Boolean setNx(String key, long expired, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public RBitSet getBitSet(String key) {
        return null;
    }
}
