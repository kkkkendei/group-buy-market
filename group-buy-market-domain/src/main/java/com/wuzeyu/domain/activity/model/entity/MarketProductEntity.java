package com.wuzeyu.domain.activity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuzeyu
 * @description 营销商品实体信息
 * @github github.com/kkkkendei
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketProductEntity {

    /** 用户ID */
    private String userId;

    /** 商品ID */
    private String goodsId;

    /** 渠道 */
    private String source;

    /** 来源 */
    private String channel;

}
