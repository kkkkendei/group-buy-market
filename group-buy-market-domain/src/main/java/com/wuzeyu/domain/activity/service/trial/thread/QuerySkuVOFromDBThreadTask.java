package com.wuzeyu.domain.activity.service.trial.thread;

import com.wuzeyu.domain.activity.adapter.repository.IActivityRepository;
import com.wuzeyu.domain.activity.model.valobj.SkuVO;

import java.util.concurrent.Callable;

/**
 * @author wuzeyu
 * @description 查询商品信息任务
 * @github github.com/kkkkendei
 */
public class QuerySkuVOFromDBThreadTask implements Callable<SkuVO> {

    private final String goodsId;

    private final IActivityRepository activityRepository;

    public QuerySkuVOFromDBThreadTask(String goodsId, IActivityRepository activityRepository) {
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }

    @Override
    public SkuVO call() throws Exception {
        return activityRepository.querySkuByGoodsId(goodsId);
    }

}
