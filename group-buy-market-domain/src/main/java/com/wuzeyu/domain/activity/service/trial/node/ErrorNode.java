package com.wuzeyu.domain.activity.service.trial.node;

import com.alibaba.fastjson2.JSON;
import com.wuzeyu.domain.activity.model.entity.MarketProductEntity;
import com.wuzeyu.domain.activity.model.entity.TrialBalanceEntity;
import com.wuzeyu.domain.activity.service.trial.AbstractGroupBuyMarketSupport;
import com.wuzeyu.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import com.wuzeyu.types.design.framework.tree.StrategyHandler;
import com.wuzeyu.types.enums.ResponseCode;
import com.wuzeyu.types.exception.AppException;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wuzeyu
 * @description 异常节点处理 无营销、流程降级、超时调用等，都可以路由到 ErrorNode 节点统一处理
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class ErrorNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicParameter) throws Exception {
        log.info("拼团商品查询试算服务-NoMarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), JSON.toJSONString(requestParameter));


        // 无营销配置
        if (dynamicParameter.getGroupBuyActivityDiscountVO() == null) {
            log.info("商品无拼团营销配置 {}", requestParameter.getGoodsId());
            throw new AppException(ResponseCode.E0002.getCode(), ResponseCode.E0002.getInfo());
        }

        return TrialBalanceEntity.builder().build();
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParam, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }

}
