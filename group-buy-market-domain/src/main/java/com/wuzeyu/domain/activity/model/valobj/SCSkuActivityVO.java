package com.wuzeyu.domain.activity.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author wuzeyu
 * @description 渠道商品活动配置值对象
 * @github github.com/kkkkendei
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SCSkuActivityVO {

    /** 渠道 */
    private String source;

    /** 来源 */
    private String channel;

    /** 活动ID */
    private Long activityId;

    /** 商品ID */
    private String goodsId;

}
