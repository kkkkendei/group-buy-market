package com.wuzeyu.domain.activity.service.discount;

import com.wuzeyu.domain.activity.adapter.repository.IActivityRepository;
import com.wuzeyu.domain.activity.model.valobj.DiscountTypeEnum;
import com.wuzeyu.domain.activity.model.valobj.GroupBuyActivityDiscountVO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author wuzeyu
 * @description 折扣计算服务抽象类
 * @github github.com/kkkkendei
 */
@Slf4j
public class AbstractDiscountCalculateService implements IDiscountCalculateService {

    @Resource
    protected IActivityRepository repository;

    @Override
    public BigDecimal calculate(String userId, BigDecimal originalPrice, GroupBuyActivityDiscountVO.GroupBuyDiscount groupBuyDiscount) {

        // 人群标签过滤
        if (DiscountTypeEnum.TAG.equals(groupBuyDiscount.getDiscountType())) {

        }

    }

    private boolean filterTagId(String userId, String tagId) {
        return repository.is
    }

}
