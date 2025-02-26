package com.wuzeyu.domain.activity.service.trial.factory;

import com.wuzeyu.domain.activity.model.entity.MarketProductEntity;
import com.wuzeyu.domain.activity.model.entity.TrialBalanceEntity;
import com.wuzeyu.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.wuzeyu.domain.activity.model.valobj.SkuVO;
import com.wuzeyu.domain.activity.service.trial.node.RootNode;
import com.wuzeyu.types.design.framework.tree.StrategyHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wuzeyu
 * @description 活动策略工厂
 * @github github.com/kkkkendei
 */
@Service
public class DefaultActivityStrategyFactory {

    private final RootNode rootNode;

    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext{

        // 拼团活动营销配置值对象
        private GroupBuyActivityDiscountVO groupBuyActivityDiscountVO;

        // 商品信息
        private SkuVO skuVO;

        // 折扣金额
        private BigDecimal deductionPrice;

        // 支付金额
        private BigDecimal payPrice;

        // 活动可见性限制
        private boolean visible;

        // 活动
        private boolean enable;

    }

}
