package com.wuzeyu.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuzeyu
 * @description 结算应答对象
 * @github github.com/kkkkendei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SettlementMarketPayOrderResponseDTO {

    /** 用户ID */
    private String userId;

    /** 拼单组队ID */
    private String teamId;

    /** 活动ID */
    private Long activityId;

    /** 外部交易单号 */
    private String outTradeNo;

}
