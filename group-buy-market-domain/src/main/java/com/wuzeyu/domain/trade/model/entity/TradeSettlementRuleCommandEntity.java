package com.wuzeyu.domain.trade.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wuzeyu
 * @description 拼团交易结算规则命令
 * @github github.com/kkkkendei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeSettlementRuleCommandEntity {

    /** 渠道 */
    private String source;

    /** 来源 */
    private String channel;

    /** 用户ID */
    private String userId;

    /** 外部交易单号 */
    private String outTradeNo;

    /** 外部交易时间 */
    private Date outTradeTime;

}
