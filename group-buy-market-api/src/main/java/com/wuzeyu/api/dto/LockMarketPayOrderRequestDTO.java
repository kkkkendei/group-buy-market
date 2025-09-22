package com.wuzeyu.api.dto;

import lombok.Data;

/**
 * @author wuzeyu
 * @description 营销支付锁单请求
 * @github github.com/kkkkendei
 */
@Data
public class LockMarketPayOrderRequestDTO {

    // 用户ID
    private String userId;

    // 拼单组队ID - 可为空，为空则创建新组队ID
    private String teamId;

    // 活动ID
    private Long activityId;

    // 商品ID
    private String goodsId;

    // 渠道
    private String source;

    // 来源
    private String channel;

    // 外部交易单号
    private String outTradeNo;

    // 回调地址
    private String notifyUrl;


}
