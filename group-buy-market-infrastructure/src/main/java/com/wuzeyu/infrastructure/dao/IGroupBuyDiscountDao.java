package com.wuzeyu.infrastructure.dao;

import com.wuzeyu.infrastructure.dao.po.GroupBuyDiscount;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author wuzeyu
 * @description 折扣配置Dao
 * @github github.com/kkkkendei
 */
@Mapper
public interface IGroupBuyDiscountDao {

    List<GroupBuyDiscount> queryGroupBuyDiscountList();

    GroupBuyDiscount queryGroupBuyActivityDiscountByDiscountId(String discountId);

}
