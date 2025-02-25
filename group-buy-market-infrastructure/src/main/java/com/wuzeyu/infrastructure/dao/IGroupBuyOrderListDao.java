package com.wuzeyu.infrastructure.dao;

import com.wuzeyu.infrastructure.dao.po.GroupBuyOrderList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wuzeyu
 * @description 用户拼单明细
 * @github github.com/kkkkendei
 */
@Mapper
public interface IGroupBuyOrderListDao {

    void insert(GroupBuyOrderList groupBuyOrderListReq);

    GroupBuyOrderList queryGroupBuyOrderRecordByOutTradeNo(GroupBuyOrderList groupBuyOrderListReq);

    Integer queryOrderCountByActivityId(GroupBuyOrderList groupBuyOrderListReq);

    int updateOrderStatus2COMPLETE(GroupBuyOrderList groupBuyOrderListReq);

    List<String> queryGroupBuyCompleteOrderOutTradeNoListByTeamId(String teamId);

    List<GroupBuyOrderList> queryInProgressUserGroupBuyOrderDetailListByUserId(GroupBuyOrderList groupBuyOrderListReq);

    List<GroupBuyOrderList> queryInProgressUserGroupBuyOrderDetailListByRandom(GroupBuyOrderList groupBuyOrderListReq);

    List<GroupBuyOrderList> queryInProgressUserGroupBuyOrderDetailListByActivityId(Long activityId);

}
