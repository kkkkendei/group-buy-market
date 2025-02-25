package com.wuzeyu.domain.activity.adapter.repository;

import com.wuzeyu.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.wuzeyu.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.wuzeyu.domain.activity.model.valobj.SCSkuActivityVO;
import com.wuzeyu.domain.activity.model.valobj.SkuVO;
import com.wuzeyu.domain.activity.model.valobj.TeamStatisticVO;

import java.util.List;

/**
 * @author wuzeyu
 * @description 活动仓储
 * @github github.com/kkkkendei
 */
public interface IActivityRepository {

    GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId);

    SkuVO querySkuByGoodsId(String goodsId);

    SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId);

    boolean isTagCrowdRange(String tagId, String userId);

    boolean downgradeSwitch();

    boolean cutRange(String userId);

    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByOwner(Long activityId, String userId, Integer ownerCount);

    List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByRandom(Long activityId, String userId, Integer randomCount);

    TeamStatisticVO queryTeamStatisticByActivityId(Long activityId);

}
