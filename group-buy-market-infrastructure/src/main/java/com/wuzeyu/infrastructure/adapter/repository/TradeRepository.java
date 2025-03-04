package com.wuzeyu.infrastructure.adapter.repository;

import com.alibaba.fastjson2.JSON;
import com.wuzeyu.domain.trade.adapter.repository.ITradeRepository;
import com.wuzeyu.domain.trade.model.aggregate.GroupBuyOrderAggregate;
import com.wuzeyu.domain.trade.model.aggregate.GroupBuyTeamSettlementAggregate;
import com.wuzeyu.domain.trade.model.entity.*;
import com.wuzeyu.domain.trade.model.valobj.GroupBuyProgressVO;
import com.wuzeyu.domain.trade.model.valobj.TradeOrderStatusEnumVO;
import com.wuzeyu.infrastructure.dao.IGroupBuyActivityDao;
import com.wuzeyu.infrastructure.dao.IGroupBuyOrderDao;
import com.wuzeyu.infrastructure.dao.IGroupBuyOrderListDao;
import com.wuzeyu.infrastructure.dao.INotifyTaskDao;
import com.wuzeyu.infrastructure.dao.po.GroupBuyActivity;
import com.wuzeyu.infrastructure.dao.po.GroupBuyOrder;
import com.wuzeyu.infrastructure.dao.po.GroupBuyOrderList;
import com.wuzeyu.infrastructure.dao.po.NotifyTask;
import com.wuzeyu.infrastructure.dcc.DCCService;
import com.wuzeyu.types.common.Constants;
import com.wuzeyu.types.enums.ActivityStatusEnumVO;
import com.wuzeyu.types.enums.GroupBuyOrderEnumVO;
import com.wuzeyu.types.enums.ResponseCode;
import com.wuzeyu.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;

/**
 * @author wuzeyu
 * @description 交易仓储服务
 * @github github.com/kkkkendei
 */
@Slf4j
@Repository
public class TradeRepository implements ITradeRepository {

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    @Resource
    private IGroupBuyOrderDao groupBuyOrderDao;

    @Resource
    private IGroupBuyOrderListDao groupBuyOrderListDao;

    @Resource
    private INotifyTaskDao notifyTaskDao;

    @Resource
    private DCCService dccService;

    @Override
    public MarketPayOrderEntity queryMarketPayOrderEntityByOutTradeNo(String userId, String outTradeNo) {
        GroupBuyOrderList groupBuyOrderListReq = new GroupBuyOrderList();
        groupBuyOrderListReq.setUserId(userId);
        groupBuyOrderListReq.setOutTradeNo(outTradeNo);
        GroupBuyOrderList groupBuyOrderListRes = groupBuyOrderListDao.queryGroupBuyOrderRecordByOutTradeNo(groupBuyOrderListReq);
        if (groupBuyOrderListRes == null) return null;

        return MarketPayOrderEntity.builder()
                .teamId(groupBuyOrderListRes.getTeamId())
                .orderId(groupBuyOrderListRes.getOrderId())
                .originalPrice(groupBuyOrderListRes.getOriginalPrice())
                .deductionPrice(groupBuyOrderListRes.getDeductionPrice())
//                .payPrice(groupBuyOrderListRes.getPayPrice())
                .tradeOrderStatusEnumVO(TradeOrderStatusEnumVO.valueOf(groupBuyOrderListRes.getStatus()))
                .build();
    }

    @Transactional(timeout = 500)
    @Override
    public MarketPayOrderEntity lockMarketPayOrder(GroupBuyOrderAggregate groupBuyOrderAggregate) {
        // 聚合对象信息
        UserEntity userEntity = groupBuyOrderAggregate.getUserEntity();
        PayActivityEntity payActivityEntity = groupBuyOrderAggregate.getPayActivityEntity();
        PayDiscountEntity payDiscountEntity = groupBuyOrderAggregate.getPayDiscountEntity();
        Integer userTakeOrderCount = groupBuyOrderAggregate.getUserTakeOrderCount();

        // 判断是否有团 - teamId 为空 - 新团、为不空 - 老团
        String teamId = payActivityEntity.getTeamId();
        if (StringUtils.isBlank(teamId)) {

            // 使用 RandomStringUtils.randomNumeric 替代公司里使用的雪花算法UUID
            teamId = RandomStringUtils.randomNumeric(8);

            // 日期处理
            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.MINUTE, payActivityEntity.getValidTime());

            // 构建拼团订单
            GroupBuyOrder groupBuyOrder = GroupBuyOrder.builder()
                    .teamId(teamId)
                    .activityId(payActivityEntity.getActivityId())
                    .source(payDiscountEntity.getSource())
                    .channel(payDiscountEntity.getChannel())
                    .originalPrice(payDiscountEntity.getOriginalPrice())
                    .deductionPrice(payDiscountEntity.getDeductionPrice())
                    .payPrice(payDiscountEntity.getPayPrice())
                    .targetCount(payActivityEntity.getTargetCount())
                    .completeCount(0)
                    .lockCount(1)
                    .validStartTime(currentDate)
                    .validEndTime(calendar.getTime())
                    .notifyUrl("http://127.0.0.1:8091/api/v1/test/group_buy_notify")
                    .build();
            // 写入记录
            groupBuyOrderDao.insert(groupBuyOrder);
        } else {
            // 更新记录 - 如果更新记录不等于1，则表示拼团已满，抛出异常
            int updateAddTargetCount = groupBuyOrderDao.updateAddLockCount(teamId);
            if (1 != updateAddTargetCount) {
                throw new AppException(ResponseCode.E0005);
            }
        }
        // 使用 RandomStringUtils.randomNumeric 替代公司里使用的雪花算法UUID
        String orderId = RandomStringUtils.randomNumeric(12);
        GroupBuyOrderList groupBuyOrderListReq = GroupBuyOrderList.builder()
                .userId(userEntity.getUserId())
                .teamId(teamId)
                .orderId(orderId)
                .activityId(payActivityEntity.getActivityId())
                .startTime(payActivityEntity.getStartTime())
                .endTime(payActivityEntity.getEndTime())
                .goodsId(payDiscountEntity.getGoodsId())
                .source(payDiscountEntity.getSource())
                .channel(payDiscountEntity.getChannel())
                .originalPrice(payDiscountEntity.getOriginalPrice())
                .deductionPrice(payDiscountEntity.getDeductionPrice())
                .status(TradeOrderStatusEnumVO.CREATE.getCode())
                .outTradeNo(payDiscountEntity.getOutTradeNo())
                .bizId(payActivityEntity.getActivityId() + Constants.UNDERLINE + userEntity.getUserId() + Constants.UNDERLINE + (userTakeOrderCount + 1))
                .build();
        try {
            // 写入拼团记录
            groupBuyOrderListDao.insert(groupBuyOrderListReq);
        } catch (DuplicateKeyException e) {
            throw new AppException(ResponseCode.INDEX_EXCEPTION);
        }
        return MarketPayOrderEntity.builder()
                .orderId(orderId)
                .deductionPrice(payDiscountEntity.getDeductionPrice())
                .tradeOrderStatusEnumVO(TradeOrderStatusEnumVO.CREATE)
                .build();
    }

    @Override
    public GroupBuyProgressVO queryGroupBuyProgress(String teamId) {
        GroupBuyOrder groupBuyOrder = groupBuyOrderDao.queryGroupBuyProgress(teamId);
        if (groupBuyOrder == null) return null;
        return GroupBuyProgressVO.builder()
                .completeCount(groupBuyOrder.getCompleteCount())
                .targetCount(groupBuyOrder.getTargetCount())
                .lockCount(groupBuyOrder.getLockCount())
                .build();
    }

    @Override
    public GroupBuyActivityEntity queryGroupBuyActivityEntityByActivityId(Long activityId) {
        GroupBuyActivity groupBuyActivity = groupBuyActivityDao.queryGroupBuyActivityByActivityId(activityId);
        return GroupBuyActivityEntity.builder()
                .activityId(groupBuyActivity.getActivityId())
                .activityName(groupBuyActivity.getActivityName())
                .discountId(groupBuyActivity.getDiscountId())
                .groupType(groupBuyActivity.getGroupType())
                .takeLimitCount(groupBuyActivity.getTakeLimitCount())
                .target(groupBuyActivity.getTarget())
                .validTime(groupBuyActivity.getValidTime())
                .status(ActivityStatusEnumVO.valueOf(groupBuyActivity.getStatus()))
                .startTime(groupBuyActivity.getStartTime())
                .endTime(groupBuyActivity.getEndTime())
                .tagId(groupBuyActivity.getTagId())
                .tagScope(groupBuyActivity.getTagScope())
                .build();
    }

    @Override
    public Integer queryOrderCountByActivityId(Long activityId, String userId) {
        GroupBuyOrderList groupBuyOrderListReq = new GroupBuyOrderList();
        groupBuyOrderListReq.setActivityId(activityId);
        groupBuyOrderListReq.setUserId(userId);
        return groupBuyOrderListDao.queryOrderCountByActivityId(groupBuyOrderListReq);
    }

    @Override
    public GroupBuyTeamEntity queryGroupBuyTeamByTeamId(String teamId) {
        GroupBuyOrder groupBuyOrder = groupBuyOrderDao.queryGroupBuyTeamByTeamId(teamId);
        return GroupBuyTeamEntity.builder()
                .teamId(groupBuyOrder.getTeamId())
                .activityId(groupBuyOrder.getActivityId())
                .targetCount(groupBuyOrder.getTargetCount())
                .completeCount(groupBuyOrder.getCompleteCount())
                .lockCount(groupBuyOrder.getLockCount())
                .status(GroupBuyOrderEnumVO.valueOf(groupBuyOrder.getStatus()))
                .validStartTime(groupBuyOrder.getValidStartTime())
                .validEndTime(groupBuyOrder.getValidEndTime())
                .notifyUrl(groupBuyOrder.getNotifyUrl())
                .build();
    }

    @Transactional(timeout = 500)
    @Override
    public boolean settlementMarketPayOrder(GroupBuyTeamSettlementAggregate groupBuyTeamSettlementAggregate) {

        UserEntity userEntity = groupBuyTeamSettlementAggregate.getUserEntity();
        GroupBuyTeamEntity groupBuyTeamEntity = groupBuyTeamSettlementAggregate.getGroupBuyTeamEntity();
        TradePaySuccessEntity tradePaySuccessEntity = groupBuyTeamSettlementAggregate.getTradePaySuccessEntity();

        // 1. 更新拼团订单明细状态
        GroupBuyOrderList groupBuyOrderListReq = new GroupBuyOrderList();
        groupBuyOrderListReq.setUserId(userEntity.getUserId());
        groupBuyOrderListReq.setOutTradeNo(tradePaySuccessEntity.getOutTradeNo());
        groupBuyOrderListReq.setOutTradeTime(tradePaySuccessEntity.getOutTradeTime());

        int updateOrderListStatusCount = groupBuyOrderListDao.updateOrderStatus2COMPLETE(groupBuyOrderListReq);
        if (updateOrderListStatusCount != 1) {
            throw new AppException(ResponseCode.UPDATE_ZERO);
        }

        // 2. 更新拼团达成数量
        int updateAddCount = groupBuyOrderDao.updateAddCompleteCount(groupBuyTeamEntity.getTeamId());
        if (1 != updateAddCount) {
            throw new AppException(ResponseCode.UPDATE_ZERO);
        }

        // 3. 更新拼团完成状态
        if (groupBuyTeamEntity.getTargetCount() - groupBuyTeamEntity.getCompleteCount() == 1) {
            int updateOrderStatusCount = groupBuyOrderDao.updateOrderStatus2COMPLETE(groupBuyTeamEntity.getTeamId());
            if (updateOrderStatusCount != 1) {
                throw new AppException(ResponseCode.UPDATE_ZERO);
            }

            // 查询拼团交易完成外部单号列表
            List<String> outTradeNoList = groupBuyOrderListDao.queryGroupBuyCompleteOrderOutTradeNoListByTeamId(groupBuyTeamEntity.getTeamId());

            // 拼团完成写入回调任务记录
            NotifyTask notifyTask = new NotifyTask();
            notifyTask.setActivityId(groupBuyTeamEntity.getActivityId());
            notifyTask.setTeamId(groupBuyTeamEntity.getTeamId());
            notifyTask.setNotifyUrl(groupBuyTeamEntity.getNotifyUrl());
            notifyTask.setNotifyCount(1);
            notifyTask.setNotifyStatus(1);

            notifyTask.setParameterJson(JSON.toJSONString(new HashMap<String, Object>() {{
                put("teamId", groupBuyTeamEntity.getTeamId());
                put("outTradeNoList", outTradeNoList);
            }}));

            notifyTaskDao.insert(notifyTask);

            return true;
        }

        return false;
    }

    @Override
    public boolean isSCBlackIntercept(String source, String channel) {
        return dccService.isSCBlackIntercept(source, channel);
    }

    @Override
    public List<NotifyTaskEntity> queryUnExecutedNotifyTaskList() {
        List<NotifyTask> notifyTaskList = notifyTaskDao.queryUnExecutedNotifyTaskList();
        if (notifyTaskList.isEmpty()) return new ArrayList<>();

        List<NotifyTaskEntity> notifyTaskEntities = new ArrayList<>();
        for (NotifyTask notifyTask : notifyTaskList) {
            NotifyTaskEntity notifyTaskEntity = NotifyTaskEntity.builder()
                    .teamId(notifyTask.getTeamId())
                    .notifyUrl(notifyTask.getNotifyUrl())
                    .notifyCount(notifyTask.getNotifyCount())
                    .parameterJson(notifyTask.getParameterJson())
                    .build();

            notifyTaskEntities.add(notifyTaskEntity);
        }

        return notifyTaskEntities;
    }

    @Override
    public List<NotifyTaskEntity> queryUnExecutedNotifyTaskList(String teamId) {
        NotifyTask notifyTask = notifyTaskDao.queryUnExecutedNotifyTaskByTeamId(teamId);
        if (notifyTask == null) return new ArrayList<>();
        return Collections.singletonList(NotifyTaskEntity.builder()
                .teamId(notifyTask.getTeamId())
                .notifyUrl(notifyTask.getNotifyUrl())
                .notifyCount(notifyTask.getNotifyCount())
                .parameterJson(notifyTask.getParameterJson())
                .build());
    }

    @Override
    public int updateNotifyTaskStatusSuccess(String teamId) {
        return notifyTaskDao.updateNotifyTaskStatusSuccess(teamId);
    }

    @Override
    public int updateNotifyTaskStatusError(String teamId) {
        return notifyTaskDao.updateNotifyTaskStatusError(teamId);
    }

    @Override
    public int updateNotifyTaskStatusRetry(String teamId) {
        return notifyTaskDao.updateNotifyTaskStatusRetry(teamId);
    }

}
