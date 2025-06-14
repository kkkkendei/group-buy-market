package com.wuzeyu.domain.activity.service.discount;

import com.wuzeyu.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import java.math.BigDecimal;

/**
 * @author wuzeyu
 * @description 折扣计算服务接口
 * @github github.com/kkkkendei
 */
public interface IDiscountCalculateService {

    /**
     * 折扣计算
     *
     * @param userId 用户ID
     * @param originalPrice 商品原始价格
     * @param groupBuyDiscount 折扣计划配置
     * @return 商品优惠价格
     */
    BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount);

}
