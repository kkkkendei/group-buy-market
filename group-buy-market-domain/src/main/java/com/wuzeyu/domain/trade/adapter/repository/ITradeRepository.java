package com.wuzeyu.domain.trade.adapter.repository;

import com.wuzeyu.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.wuzeyu.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import com.wuzeyu.domain.trade.model.entity.GroupBuyActivityEntity;
import com.wuzeyu.domain.trade.model.entity.GroupBuyTeamEntity;
import com.wuzeyu.domain.trade.model.entity.MarketPayOrderEntity;
import com.wuzeyu.domain.trade.model.entity.NotifyTaskEntity;
import com.wuzeyu.domain.trade.model.valobj.GroupBuyProgressVO;

import java.util.List;

/**
 * @author wuzeyu
 * @description 交易仓储服务接口
 * @github github.com/kkkkendei
 */
public interface ITradeRepository {

    MarketPayOrderEntity queryMarketPayOrderEntityByOutTradeNo(String userId, String outTradeNo);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    GroupBuyActivityEntity queryGroupBuyActivityEntityByActivityId(Long activityId);

    Integer queryOrderCountByActivityId(Long activityId, String userId);

    GroupBuyTeamEntity queryGroupBuyTeamByTeamId(String teamId);

    boolean settlementMarketPayOrder(GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate);

    boolean isSCBlackIntercept(String source, String channel);

    List<NotifyTaskEntity> queryUnExecutedNotifyTaskList();

    List<NotifyTaskEntity> queryUnExecutedNotifyTaskList(String teamId);

    int updateNotifyTaskStatusSuccess(String teamId);

    int updateNotifyTaskStatusError(String teamId);

    int updateNotifyTaskStatusRetry(String teamId);

}
