package com.wuzeyu.domain.tag.model.aggregate.repository;

import com.wuzeyu.domain.tag.model.entity.CrowdTagsJobEntity;

/**
 * @author wuzeyu
 * @description 人群标签仓储接口
 * @github github.com/kkkkendei
 */
public interface ITagRepository {

    CrowdTagsJobEntity queryCrowdTagsJobEntity(String tagId, String batchId);

    void addCrowdTagsUserId(String tagId, String userId);

    void updateCrowdTagsStatistics(String tagId, int count);

}
