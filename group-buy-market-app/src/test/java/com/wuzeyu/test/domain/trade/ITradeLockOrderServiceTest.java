package com.wuzeyu.test.domain.trade;

import com.alibaba.fastjson.JSON;
import com.wuzeyu.domain.activity.model.entity.MarketProductEntity;
import com.wuzeyu.domain.activity.model.entity.TrialBalanceEntity;
import com.wuzeyu.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.wuzeyu.domain.activity.service.IIndexGroupBuyMarketService;
import com.wuzeyu.domain.trade.model.entity.MarketPayOrderEntity;
import com.wuzeyu.domain.trade.model.entity.PayActivityEntity;
import com.wuzeyu.domain.trade.model.entity.PayDiscountEntity;
import com.wuzeyu.domain.trade.model.entity.UserEntity;
import com.wuzeyu.domain.trade.service.ITradeLockOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wuzeyu
 * @description 交易订单服务测试
 * @github github.com/kkkkendei
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITradeLockOrderServiceTest {

    @Resource
    private IIndexGroupBuyMarketService indexGroupBuyMarketService;

    @Resource
    private ITradeLockOrderService tradeOrderService;

    @Test
    public void test_lockMarketPayOrder() throws Exception {
        // 入参信息
        Long activityId = 100123L;
        String userId = "test_zyt";
        String goodsId = "9890001";
        String source = "s01";
        String channel = "c01";
        String outTradeNo = RandomStringUtils.randomNumeric(12);

        // 1. 获取试算优惠，有【activityId】优先使用
        TrialBalanceEntity trialBalanceEntity = indexGroupBuyMarketService.indexMarketTrial(MarketProductEntity.builder()
                .userId(userId)
                .source(source)
                .channel(channel)
                .goodsId(goodsId)
                .activityId(activityId)
                .build());

        GroupBuyActivityDiscountVO groupBuyActivityDiscountVO = trialBalanceEntity.getGroupBuyActivityDiscountVO();

        // 查询 outTradeNo 是否已经存在交易记录
        MarketPayOrderEntity marketPayOrderEntityOld = tradeOrderService.queryNoPayMarketPayOrderByOutTradeNo(userId, outTradeNo);
        if (null != marketPayOrderEntityOld) {
            log.info("测试结果(Old):{}", JSON.toJSONString(marketPayOrderEntityOld));
            return;
        }

        // 2. 锁定，营销预支付订单；商品下单前，预购锁定。
        MarketPayOrderEntity marketPayOrderEntityNew = tradeOrderService.lockMarketPayOrder(
                UserEntity.builder().userId(userId).build(),
                PayActivityEntity.builder()
                        .teamId("77374570")
                        .activityId(groupBuyActivityDiscountVO.getActivityId())
                        .activityName(groupBuyActivityDiscountVO.getActivityName())
                        .startTime(groupBuyActivityDiscountVO.getStartTime())
                        .endTime(groupBuyActivityDiscountVO.getEndTime())
                        .validTime(1000)
                        .targetCount(groupBuyActivityDiscountVO.getTarget())
                        .build(),
                PayDiscountEntity.builder()
                        .source(source)
                        .channel(channel)
                        .goodsId(goodsId)
                        .goodsName(trialBalanceEntity.getGoodsName())
                        .originalPrice(trialBalanceEntity.getOriginalPrice())
                        .deductionPrice(trialBalanceEntity.getDeductionPrice())
                        .payPrice(trialBalanceEntity.getPayPrice())
                        .outTradeNo(outTradeNo)
                        .build());

        log.info("测试结果(New):{}",JSON.toJSONString(marketPayOrderEntityNew));
    }

}
