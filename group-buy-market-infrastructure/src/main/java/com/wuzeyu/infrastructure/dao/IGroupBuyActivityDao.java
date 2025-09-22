package com.wuzeyu.infrastructure.dao;

import com.wuzeyu.infrastructure.dao.po.GroupBuyActivity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author wuzeyu
 * @description 拼团活动Dao
 * @github github.com/kkkkendei
 */
@Mapper
public interface IGroupBuyActivityDao {

    List<GroupBuyActivity> queryGroupBuyActivityList();

    GroupBuyActivity queryValidGroupBuyActivity(GroupBuyActivity groupBuyActivityReq);

    GroupBuyActivity queryValidGroupBuyActivityId(Long activityId);

    GroupBuyActivity queryGroupBuyActivityByActivityId(Long activityId);

}
