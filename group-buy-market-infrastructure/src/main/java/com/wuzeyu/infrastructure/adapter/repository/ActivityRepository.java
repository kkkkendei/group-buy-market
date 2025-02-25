package com.wuzeyu.infrastructure.adapter.repository;

import com.wuzeyu.domain.activity.adapter.repository.IActivityRepository;
import com.wuzeyu.domain.activity.model.entity.UserGroupBuyOrderDetailEntity;
import com.wuzeyu.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.wuzeyu.domain.activity.model.valobj.SCSkuActivityVO;
import com.wuzeyu.domain.activity.model.valobj.SkuVO;
import com.wuzeyu.domain.activity.model.valobj.TeamStatisticVO;
import com.wuzeyu.infrastructure.dao.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wuzeyu
 * @description 活动仓储
 * @github github.com/kkkkendei
 */
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private IGroupBuyActivityDao groupBuyActivityDao;

    @Resource
    private IGroupBuyDiscountDao groupBuyDiscountDao;

    @Resource
    private ISkuDao skuDao;

    @Resource
    private ISCSkuActivityDao scSkuActivityDao;

    @Resource
    private IGroupBuyOrderDao groupBuyOrderDao;

    @Resource
    private IGroupBuyOrderListDao groupBuyOrderListDao;

    @Override
    public GroupBuyActivityDiscountVO queryGroupBuyActivityDiscountVO(Long activityId) {
        return null;
    }

    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {
        return null;
    }

    @Override
    public SCSkuActivityVO querySCSkuActivityBySCGoodsId(String source, String channel, String goodsId) {
        return null;
    }

    @Override
    public boolean isTagCrowdRange(String tagId, String userId) {
        return false;
    }

    @Override
    public boolean downgradeSwitch() {
        return false;
    }

    @Override
    public boolean cutRange(String userId) {
        return false;
    }

    @Override
    public List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByOwner(Long activityId, String userId, Integer ownerCount) {
        return null;
    }

    @Override
    public List<UserGroupBuyOrderDetailEntity> queryInProgressUserGroupBuyOrderDetailListByRandom(Long activityId, String userId, Integer randomCount) {
        return null;
    }

    @Override
    public TeamStatisticVO queryTeamStatisticByActivityId(Long activityId) {
        return null;
    }

}
