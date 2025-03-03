package com.wuzeyu.domain.trade.service.settlement.filter;

import com.wuzeyu.domain.trade.model.entity.GroupBuyTeamEntity;
import com.wuzeyu.domain.trade.model.entity.TradeSettlementRuleCommandEntity;
import com.wuzeyu.domain.trade.model.entity.TradeSettlementRuleFilterBackEntity;
import com.wuzeyu.domain.trade.service.settlement.factory.TradeSettlementRuleFilterFactory;
import com.wuzeyu.types.design.framework.link.model2.handler.ILogicHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wuzeyu
 * @description 结束节点
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class EndRuleFilter implements ILogicHandler<TradeSettlementRuleCommandEntity, TradeSettlementRuleFilterFactory.DynamicContext, TradeSettlementRuleFilterBackEntity> {

    @Override
    public TradeSettlementRuleFilterBackEntity apply(TradeSettlementRuleCommandEntity requestParameter, TradeSettlementRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("结算规则过滤-结束节点{} outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());

        // 获取上下文对象
        GroupBuyTeamEntity groupBuyTeamEntity = dynamicContext.getGroupBuyTeamEntity();

        // 返回封装数据
        return TradeSettlementRuleFilterBackEntity.builder()
                .teamId(groupBuyTeamEntity.getTeamId())
                .activityId(groupBuyTeamEntity.getActivityId())
                .targetCount(groupBuyTeamEntity.getTargetCount())
                .completeCount(groupBuyTeamEntity.getCompleteCount())
                .lockCount(groupBuyTeamEntity.getLockCount())
                .status(groupBuyTeamEntity.getStatus())
                .validStartTime(groupBuyTeamEntity.getValidStartTime())
                .validEndTime(groupBuyTeamEntity.getValidEndTime())
                .notifyUrl(groupBuyTeamEntity.getNotifyUrl())
                .build();
    }

}
