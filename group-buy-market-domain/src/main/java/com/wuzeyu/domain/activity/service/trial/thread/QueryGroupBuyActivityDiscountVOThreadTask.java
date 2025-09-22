package com.wuzeyu.domain.activity.service.trial.thread;

import com.wuzeyu.domain.activity.adapter.repository.IActivityRepository;
import com.wuzeyu.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.wuzeyu.domain.activity.model.valobj.SCSkuActivityVO;

import java.util.concurrent.Callable;

/**
 * @author wuzeyu
 * @description 营销配置任务
 * @github github.com/kkkkendei
 */
public class QueryGroupBuyActivityDiscountVOThreadTask implements Callable<GroupBuyActivityDiscountVO> {

    /**
     * 活动ID
     */
    private final Long activityId;

    /**
     * 来源
     */
    private final String source;

    /**
     * 渠道
     */
    private final String channel;

    /**
     * 商品ID
     */
    private final String goodsId;

    /**
     * 活动仓储
     */
    private final IActivityRepository activityRepository;

    public QueryGroupBuyActivityDiscountVOThreadTask(Long activityId, String source, String channel, String goodsId, IActivityRepository activityRepository) {
        this.activityId = activityId;
        this.source = source;
        this.channel = channel;
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }

    @Override
    public GroupBuyActivityDiscountVO call() throws Exception {

        // 判断是否存在可用的活动ID
        Long availableActivityId = activityId;
        if (activityId == null) {
            // 查询渠道商品活动配置关联配置
            SCSkuActivityVO  scSkuActivityVO = activityRepository.querySCSkuActivityBySCGoodsId(source, channel, goodsId);
            if (scSkuActivityVO == null) return null;
            availableActivityId = scSkuActivityVO.getActivityId();
        }
        // 查询活动配置
        return activityRepository.queryGroupBuyActivityDiscountVO(availableActivityId);

    }

}
