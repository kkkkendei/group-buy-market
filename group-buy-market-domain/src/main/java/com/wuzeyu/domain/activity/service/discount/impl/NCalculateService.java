package com.wuzeyu.domain.activity.service.discount.impl;

import com.wuzeyu.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import com.wuzeyu.domain.activity.service.discount.AbstractDiscountCalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author wuzeyu
 * @description N元购优惠计算
 * @github github.com/kkkkendei
 */
@Slf4j
@Service("N")
public class NCalculateService extends AbstractDiscountCalculateService {

    @Override
    protected BigDecimal doCalculate(BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {
        log.info("优惠策略折扣计算: {}", groupBuyDiscount.getDiscountType().getCode());

        // 折扣表达式 - 直接优惠后的金额
        String marketExpr = groupBuyDiscount.getMarketExpr();
        // n元购
        return new BigDecimal(marketExpr);
    }

}
