package com.wuzeyu.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wuzeyu
 * @description 试算结果实体对象
 * @github github.com/kkkkendei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrialBalanceEntity {

    /** 商品ID */
    private String goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 原始价格 */
    private BigDecimal originalPrice;

    /** 折扣价格 */
    private BigDecimal deductionPrice;

    /** 拼团目标数量 */
    private Integer targetCount;

    /** 拼团开始时间 */
    private Date startTime;

    /** 拼团结束时间 */
    private Date endTime;

    /** 是否可见拼团 */
    private Boolean isVisible;

    /** 是否可参与进团 */
    private Boolean isEnable;

}
