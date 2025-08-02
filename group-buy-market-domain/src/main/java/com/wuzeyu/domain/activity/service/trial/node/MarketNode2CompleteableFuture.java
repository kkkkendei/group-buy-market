package com.wuzeyu.domain.activity.service.trial.node;

import com.wuzeyu.domain.activity.model.entity.MarketProductEntity;
import com.wuzeyu.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.wuzeyu.domain.activity.model.valobj.SCSkuActivityVO;
import com.wuzeyu.domain.activity.model.valobj.SkuVO;
import com.wuzeyu.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeoutException;

/**
 * @author wuzeyu
 * @description 多线程重新编排
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class MarketNode2CompleteableFuture extends MarketNode {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws ExecutionException, InterruptedException, TimeoutException {
        // 异步查询活动配置
        CompletableFuture<GroupBuyActivityDiscountVO> groupBuyActivityDiscountVOCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Long avaiableActivityId = requestParameter.getActivityId();
                if (null == avaiableActivityId) {
                    SCSkuActivityVO scSkuActivityVO = repository.querySCSkuActivityBySCGoodsId(requestParameter.getSource(), requestParameter.getChannel(), requestParameter.getGoodsId());
                    if (null != scSkuActivityVO) {
                        return null;
                    }
                    avaiableActivityId = scSkuActivityVO.getActivityId();
                }
                return repository.queryGroupBuyActivityDiscountVO(avaiableActivityId);
            } catch (Exception e) {
                log.error("异步查询活动配置异常", e);
                return null;
            }
        }, threadPoolExecutor);

        // 异步查询商品信息
        CompletableFuture<SkuVO> skuVOCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return repository.querySkuByGoodsId(requestParameter.getGoodsId());
            } catch (Exception e) {
                log.error("异步查询商品信息异常", e);
                return null;
            }
        }, threadPoolExecutor);

        // 等待所有异步任务完成并写入上下文
        CompletableFuture.allOf(groupBuyActivityDiscountVOCompletableFuture, skuVOCompletableFuture)
                .thenRun(() -> {
                    dynamicContext.setGroupBuyActivityDiscountVO(groupBuyActivityDiscountVOCompletableFuture.join());
                    dynamicContext.setSkuVO(skuVOCompletableFuture.join());
                }).join();

        log.info("拼团商品查询试算服务-MarketNode userId:{} 异步线程加载数据「GroupBuyActivityDiscountVO、SkuVO」完成", requestParameter.getUserId());
    }

}
