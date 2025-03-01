package com.wuzeyu.domain.trade.model.entity;

import com.wuzeyu.domain.trade.model.valobj.TradeOrderStatusEnumVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author wuzeyu
 * @description 拼团 预购订单营销实体对象
 * @github github.com/kkkkendei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketPayOrderEntity {

    /** 拼单组队ID */
    private String teamId;

    /** 预购订单ID */
    private String orderId;

    /** 原始价格 */
    private BigDecimal originalPrice;

    /** 折扣金额 */
    private BigDecimal deductionPrice;

    /** 支付金额 */
    private BigDecimal payPrice;

    /** 交易订单状态枚举 */
    private TradeOrderStatusEnumVO tradeOrderStatusEnumVO;


}
