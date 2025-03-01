package com.wuzeyu.domain.trade.service.lock;

import com.wuzeyu.domain.trade.model.entity.MarketPayOrderEntity;
import com.wuzeyu.domain.trade.model.entity.PayActivityEntity;
import com.wuzeyu.domain.trade.model.entity.PayDiscountEntity;
import com.wuzeyu.domain.trade.model.entity.UserEntity;
import com.wuzeyu.domain.trade.model.valobj.GroupBuyProgressVO;
import com.wuzeyu.domain.trade.service.ITradeLockOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wuzeyu
 * @description 交易订单服务
 * @github github.com/kkkkendei
 */
@Slf4j
@Service
public class TradeLockOrderService implements ITradeLockOrderService {
    @Override
    public MarketPayOrderEntity queryNoPayMarketPayOrderByOutTradeNo(String userId, String outTradeNo) {
        return null;
    }

    @Override
    public GroupBuyProgressVO queryGroupBuyProgress(String teamId) {
        return null;
    }

    @Override
    public MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) throws Exception {
        return null;
    }
}
