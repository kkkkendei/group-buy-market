package com.wuzeyu.infrastructure.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.roaringbitmap.RoaringBitmap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author wuzeyu
 * @description RoaringBitmap 服务
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class RoaringBitmapService implements IRoaringBitmapService {

    private static final String TAG_PREFIX = "roaring_tag_";

    private static final String LOCK_PREFIX = "roaring_lock_";

    @Resource
    private IRedisService redisService;

    @Resource(name = "byteArrayRedissonClient")
    private RedissonClient redissonClient;

    @Override
    public void addUserToCrowdTag(String tagId, String userId) {
        String key = TAG_PREFIX + tagId;
        RLock lock = redissonClient.getLock(LOCK_PREFIX + tagId);

        try {
            lock.lock();
            RoaringBitmap roaringBitmap = getRoaringBitmap(key);
            roaringBitmap.add(redisService.getIndexFromUserId(userId));
            saveRoaringBitmap(key, roaringBitmap);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void batchAddUserToCrowdTag(String tagId, Collection<String> userIds) {

    }

    @Override
    public int getTagUserCount(String tagId) {
        return 0;
    }

    @Override
    public Set<String> getTagIntersection(String tagId1, String tagId2) {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getTagUnion(String tagId1, String tagId2) {
        return Collections.emptySet();
    }

    /**
     * 获取 RoaringBitmap
     *
     * @param key Redis 中的键
     * @return RoaringBitmap 实例
     */
    @Override
    public RoaringBitmap getRoaringBitmap(String key) {
        if (! redissonClient.getBucket(key).isExists()) {
            return new RoaringBitmap();
        }

        RBucket<byte[]> bucket = redissonClient.getBucket(key);
        byte[] data = bucket.get();
        if (data == null) {
            return new RoaringBitmap();
        }

        try {
            RoaringBitmap roaringBitmap = new RoaringBitmap();
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            roaringBitmap.deserialize(new DataInputStream(bis)); // byte 数组反序列化写入 roaringbitmap
            return roaringBitmap;
        } catch (Exception e) {
            log.error("反序列化 RoaringBitmap 失败 ", e);
            return new RoaringBitmap();
        }
    }

    private void saveRoaringBitmap(String key, RoaringBitmap roaringBitmap) {
        RBucket<byte[]> bucket = redissonClient.getBucket(key);
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(bos);
            roaringBitmap.serialize(dataOutputStream); // roaringbitmap 序列化成 byte 数组
            bucket.set(bos.toByteArray());
        } catch (IOException e) {
            log.error("序列化 RoaringBitmap 失败", e);
            throw new RuntimeException("保存 RoaringBitmap 失败", e);
        }
    }

}
