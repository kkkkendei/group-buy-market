package com.wuzeyu.api.dto;

import lombok.Data;

/**
 * @author wuzeyu
 * @description 商品营销请求对象
 * @github github.com/kkkkendei
 */
@Data
public class GoodsMarketRequestDTO {

    // 用户ID
    private String userId;

    // 渠道
    private String source;

    // 来源
    private String channel;

    // 商品ID
    private String goodsId;

}
