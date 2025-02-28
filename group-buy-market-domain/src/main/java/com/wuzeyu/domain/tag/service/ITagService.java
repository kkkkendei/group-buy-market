package com.wuzeyu.domain.tag.service;

/**
 * @author wuzeyu
 * @description 人群标签服务接口
 * @github github.com/kkkkendei
 */
public interface ITagService {

    /**
     * 执行人群标签
     *
     * @param tagId
     * @param batchId
     */
    void execTagBatchJob(String tagId, String batchId);

}
