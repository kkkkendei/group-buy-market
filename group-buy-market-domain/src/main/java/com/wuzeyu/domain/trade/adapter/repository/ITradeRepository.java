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

    /**
     * 根据外部交易号查询市场支付订单实体
     * @param userId 用户ID
     * @param outTradeNo 外部交易号
     * @return 市场支付订单实体
     */
    MarketPayOrderEntity queryMarketPayOrderEntityByOutTradeNo(String userId, String outTradeNo);

    /**
     * 锁定市场支付订单
     * @param groupBuyOrderAggregate 拼团订单聚合
     * @return 市场支付订单实体
     */
    MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate);

    /**
     * 查询团队拼购进度
     * @param teamId 团队ID
     * @return 拼购进度值对象
     */
    GroupBuyProgressVO queryGroupBuyProgress(String teamId);

    /**
     * 根据活动ID查询拼团活动实体
     * @param activityId 活动ID
     * @return 拼团活动实体
     */
    GroupBuyActivityEntity queryGroupBuyActivityEntityByActivityId(Long activityId);

    /**
     * 查询用户在特定活动中的订单数量
     * @param activityId 活动ID
     * @param userId 用户ID
     * @return 订单数量
     */
    Integer queryOrderCountByActivityId(Long activityId, String userId);

    /**
     * 占用团队库存
     * @param teamStockKey 团队库存键
     * @param recoveryTeamStockKey 恢复团队库存键
     * @param target 目标数量
     * @param validTime 有效时间（秒）
     * @return 是否成功占用库存
     */
    Boolean OccupyTeamStock(String teamStockKey, String recoveryTeamStockKey, Integer target, Integer validTime);

    /**
     * 根据团队ID查询拼团团队实体
     * @param teamId 团队ID
     * @return 拼团团队实体
     */
    GroupBuyTeamEntity queryGroupBuyTeamByTeamId(String teamId);

    /**
     * 结算市场支付订单
     * @param groupBuyTeamSettlementAggregate 拼团团队结算聚合
     * @return 结算是否成功
     */
    boolean settlementMarketPayOrder(GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate);

    /**
     * 检查渠道和来源是否在黑名单拦截中
     * @param source 来源
     * @param channel 渠道
     * @return 是否被拦截
     */
    boolean isSCBlackIntercept(String source, String channel);

    /**
     * 查询所有未执行的通知任务列表
     * @return 未执行的通知任务实体列表
     */
    List<NotifyTaskEntity> queryUnExecutedNotifyTaskList();

    /**
     * 根据团队ID查询未执行的通知任务列表
     * @param teamId 团队ID
     * @return 未执行的通知任务实体列表
     */
    List<NotifyTaskEntity> queryUnExecutedNotifyTaskList(String teamId);

    /**
     * 更新通知任务状态为成功
     * @param teamId 团队ID
     * @return 更新影响的记录数
     */
    int updateNotifyTaskStatusSuccess(String teamId);

    /**
     * 更新通知任务状态为失败
     * @param teamId 团队ID
     * @return 更新影响的记录数
     */
    int updateNotifyTaskStatusError(String teamId);

    /**
     * 更新通知任务状态为重试
     * @param teamId 团队ID
     * @return 更新影响的记录数
     */
    int updateNotifyTaskStatusRetry(String teamId);

}
