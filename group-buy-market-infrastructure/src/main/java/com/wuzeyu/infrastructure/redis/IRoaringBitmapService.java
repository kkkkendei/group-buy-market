package com.wuzeyu.infrastructure.redis;

import org.roaringbitmap.RoaringBitmap;

import java.util.Collection;
import java.util.Set;

public interface IRoaringBitmapService {

    /**
     * 添加用户到人群标签
     *
     * @param tagId 人群标签ID
     * @param userId 用户ID
     */
    void addUserToCrowdTag(String tagId, String userId);

    /**
     * 批量添加用户到标签
     *
     * @param tagId
     * @param userIds
     */
    void batchAddUserToCrowdTag(String tagId, Collection<String> userIds);

    /**
     * 获取标签中的用户数量
     *
     * @param tagId 标签ID
     * @return 用户数量
     */
    int getTagUserCount(String tagId);

    /**
     * 计算两个标签的交集
     *
     * @param tagId1 标签1
     * @param tagId2 标签2
     * @return 交集用户ID
     */
    Set<String> getTagIntersection(String tagId1, String tagId2);

    /**
     * 计算两个标签的并集
     *
     * @param tagId1 标签1
     * @param tagId2 标签2
     * @return 并集用户ID
     */
    Set<String> getTagUnion(String tagId1, String tagId2);

    /**
     * 获取 RoaringBitmap
     *
     * @param key Redis 中的键
     */
    RoaringBitmap getRoaringBitmap(String key);

}
