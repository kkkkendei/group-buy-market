package com.wuzeyu.domain.trade.adapter.repository;

import com.wuzeyu.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.wuzeyu.domain.trade.model.entity.MarketPayOrderEntity;
import com.wuzeyu.domain.trade.model.valobj.GroupBuyProgressVO;

/**
 * @author wuzeyu
 * @description 交易仓储服务接口
 * @github github.com/kkkkendei
 */
public interface ITradeRepository {

    MarketPayOrderEntity queryMarketPayOrderEntityByOutTradeNo(String userId, String outTradeNo);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

}
